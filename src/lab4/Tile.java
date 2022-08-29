package lab4;

import java.awt.*;

public class Tile //extends JButton
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
       // tileView = new SwingButtonTileView();
        tileView = new ImageTileView();
        tileView.initView(this);
        tileView.updateView(number);
//        this.addActionListener(new ActionListener()
//        {
//            @Override
//            public void actionPerformed(ActionEvent e)
//            {
//                tryMoveTileOnBoard();
//            }
//        });
    }

    public boolean tryMoveTileOnBoard()
    {
        if (owningBoard.tryMoveTile(Tile.this))
        {
            tileView.updateView(number);
            return true;
        }
        return false;
    }

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
