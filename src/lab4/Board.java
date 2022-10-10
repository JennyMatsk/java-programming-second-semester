package lab4;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class Board {
    private int rows;

    public int getRows() {
        return rows;
    }

    private int columns;

    public int getColumns() {
        return columns;
    }

    private ArrayList<Tile> tiles;

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    private int nonEmptyTilesNumber;

    private IBoardView boardView;

    public IBoardView getBoardView() {
        return boardView;
    }

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        int size = rows * columns;
        nonEmptyTilesNumber = size - 1;
        tiles = new ArrayList<>(size);
        boardView = new ImageBoardView();
        int counter = 0;
        for (int i = 0; i < columns; i++) {
            for (int k = 0; k < rows; k++) {
                if (counter < nonEmptyTilesNumber) {
                    tiles.add(new Tile(counter+1, new Point(i, k), this));
                    counter++;
                }
                else {
                    tiles.add(new Tile(0, new Point(i, k), this));
                }
            }
        }
        System.out.println(tiles);
        do
        {
            shuffle();
        } while (!isSolvable());
        boardView.initView(this);
    }

    // Функция для запуска новой игры
    public void newGame(int rows, int columns) {
        Tile lastTile = getTiles().get(columns*rows-1);
        Tile emptyTile = findEmptyTile();
        lastTile.SwapNumberWithTile(emptyTile);
        do
        {
            shuffle();
        } while (!isSolvable());
    }

    // Функция перемешивания
    private void shuffle() {
        int n = nonEmptyTilesNumber;
        Random random = new Random();
        while (n > 1) {
            int r = random.nextInt(n--);
            tiles.get(r).SwapNumberWithTile(tiles.get(n));
        }
    }

    // Функция проверки возможности решить текущую конфигурацию
    private boolean isSolvable() {
        int countInversions = 0;
        for (int i = 0; i < nonEmptyTilesNumber; i++) {
            int row = i / rows;
            int column = i % rows;
            if (tiles.get(column).getNumber() > tiles.get(row).getNumber())
                countInversions++;
        }
        return countInversions % 2 == 0;
    }

    // Функция проверки завершенности игры
    private boolean isSolved() {
        if (tiles.get(tiles.size() - 1).getNumber() != 0)
            return false;
        for (int i = nonEmptyTilesNumber - 1; i >= 0; i--) {
            if (tiles.get(i).getNumber() != i + 1)
                return false;
        }
        return true;
    }

    // Функция поиска пустой клетки
    private Tile findEmptyTile() {
        for (Tile tile : tiles) {
            if (tile.getNumber() == 0) return tile;
        }
        return null;
    }

    // Функция поиска клетки по позиции
    private Tile findTileWithPosition(int x, int y) {
        for (Tile tile : tiles) {
            if (tile.getPosition().x == x && tile.getPosition().y == y) return tile;
        }
        return null;
    }

    // Функция для передвижения клеток мышью
    public boolean tryMoveTile(Tile tileToMove) {
        for (var tile : tiles) {
            if (tile.isBlank()) {
                if (tile.isNeighbour(tileToMove)) {
                    tileToMove.SwapNumberWithTile(tile);
                    GameOverCheck();
                }
                return true;
            }
        }
        return false;
    }

    // Функция для передвижения клеток кнопками
    public void tryMoveTileWithKeyboard(int keyKode) {
        Tile emptyTile = findEmptyTile();
        ArrayList<Integer> availableMovements = emptyTile.findAvailableMovements();
        Tile tileToMove = null;
        System.out.println(availableMovements);
        if (keyKode == KeyEvent.VK_LEFT && availableMovements.contains(KeyEvent.VK_LEFT)) {
            tileToMove = findTileWithPosition(emptyTile.getPosition().x, emptyTile.getPosition().y + 1);
            tileToMove.SwapNumberWithTile(emptyTile);
            System.out.println("left");
        }
        if (keyKode == KeyEvent.VK_RIGHT && availableMovements.contains(KeyEvent.VK_RIGHT)) {
            tileToMove = findTileWithPosition(emptyTile.getPosition().x, emptyTile.getPosition().y - 1);
            tileToMove.SwapNumberWithTile(emptyTile);
            System.out.println("right");
        }
        if (keyKode == KeyEvent.VK_UP && availableMovements.contains(KeyEvent.VK_UP)) {
            tileToMove = findTileWithPosition(emptyTile.getPosition().x + 1, emptyTile.getPosition().y);
            tileToMove.SwapNumberWithTile(emptyTile);
            System.out.println("up");
        }
        if (keyKode == KeyEvent.VK_DOWN && availableMovements.contains(KeyEvent.VK_DOWN)) {
            tileToMove = findTileWithPosition(emptyTile.getPosition().x - 1, emptyTile.getPosition().y);
            tileToMove.SwapNumberWithTile(emptyTile);
            System.out.println("down");
        }

    }

    // Функция окончания игры
    public void GameOverCheck() {
        if (isSolved()) {
            System.out.println("WIN");
        }
    }
}
