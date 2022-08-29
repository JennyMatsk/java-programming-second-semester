package lab4;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Board
{
    private int rows;
    public int getRows() { return rows; }

    private int columns;
    public int getColumns() { return columns; }

    private final ArrayList<Tile> tiles;
    public ArrayList<Tile> getTiles() { return tiles; }

    private int nonEmptyTilesNumber = 15;

    private IBoardView boardView;
    public IBoardView getBoardView() { return boardView; }

    public Board(int rows,int columns)
    {
        this.rows = rows;
        this.columns = columns;

        int size = rows * columns;

        tiles = new ArrayList<>(size);
        boardView = new ImageBoardView();

        for (int i = 0; i < size; i++)
        {
            int number = i % size;
            int row = i / rows;
            int column = i % rows;
            Tile tile = new Tile(number,new Point(row,column),this);
            tiles.add(tile);
        }

        do
        {
            shuffle();
        } while (!isSolvable());

        boardView.initView(this);
    }

    private void shuffle()
    {
        int n = nonEmptyTilesNumber;
        Random random = new Random();
        while (n > 1)
        {
            int r = random.nextInt(n--);
            tiles.get(r).SwapNumberWithTile(tiles.get(n));
        }
    }

    private boolean isSolvable()
    {
        int countInversions = 0;
        for (int i = 0; i < nonEmptyTilesNumber; i++)
        {
            int row = i / rows;
            int column = i % rows;
            if (tiles.get(column).getNumber() > tiles.get(row).getNumber())
                countInversions++;
        }
        return countInversions % 2 == 0;
    }

    private boolean isSolved() {
        if (tiles.get(tiles.size() - 1).getNumber() != 0)
            return false;
        for (int i = nonEmptyTilesNumber; i >= 0; i--)
        {
            if (tiles.get(i).getNumber() != i)
                return false;
        }
        return true;
    }

    public boolean tryMoveTile(Tile tileToMove)
    {
        for (var tile : tiles)
        {
           if (tile.isBlank())
           {
                if (tile.isNeighbour(tileToMove))
                {
                    tileToMove.SwapNumberWithTile(tile);
                    GameOverCheck();
                }
               return true;
           }
        }
        return false;
    }

    public void GameOverCheck()
    {
        if (isSolved())
        {
            System.out.println("WIN");
        }
    }
}
