package lab4;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Tile
{
    private int number;
    public int getNumber() { return this.number; }
    public void setNumber(int number)
    {
        this.number = number;
        tileView.updateView(number);
    }

    public boolean isBlank() { return number == 0; }


    private Point position;
    public Point getPosition() { return this.position; }

    private Board owningBoard;
    private ITileView tileView;
    public ITileView getTileView() { return tileView; }

    public Tile(int number,Point position,Board board)
    {
        this.number = number;
        this.position = position;
        owningBoard = board;
        tileView = new ImageTileView();
        tileView.initView(this);
        tileView.updateView(number);
    }

    // Попытаться передвинуть клетку при нажатии мышки
    public boolean tryMoveTileOnBoard()
    {
        if (owningBoard.tryMoveTile(Tile.this))
        {
            tileView.updateView(number);
            return true;
        }
        return false;
    }

    // Проверка, является ли предоставленная клетка соседом
    public boolean isNeighbour(Tile tile)
    {
        if (tile.getPosition().equals(new Point(position.x + 1, position.y)) ||
            tile.getPosition().equals(new Point(position.x - 1, position.y)) ||
            tile.getPosition().equals(new Point(position.x, position.y + 1)) ||
            tile.getPosition().equals(new Point(position.x, position.y - 1)))
        {
            return true;
        }
        return false;
    }

    // Находим список доступных для перемещения клеток
    public ArrayList<Integer> findAvailableMovements() {
        ArrayList<Integer> availableMove = new ArrayList<>();
        availableMove.add(KeyEvent.VK_LEFT);
        availableMove.add(KeyEvent.VK_UP);
        availableMove.add(KeyEvent.VK_RIGHT);
        availableMove.add(KeyEvent.VK_DOWN);
        if (this.getPosition().y == 0) availableMove.remove(Integer.valueOf(KeyEvent.VK_RIGHT));
        if (this.getPosition().x == 0) availableMove.remove(Integer.valueOf(KeyEvent.VK_DOWN));
        if (this.getPosition().y == 3) availableMove.remove(Integer.valueOf(KeyEvent.VK_LEFT));
        if (this.getPosition().x == 3) availableMove.remove(Integer.valueOf(KeyEvent.VK_UP));
        return availableMove;
    }

    // Меняем местами клетки
    public void SwapNumberWithTile(Tile tile)
    {
        if (tile != null)
        {
            int tmpNumber = tile.getNumber();
            tile.setNumber(number);
            this.setNumber(tmpNumber);
        }
    }
}
