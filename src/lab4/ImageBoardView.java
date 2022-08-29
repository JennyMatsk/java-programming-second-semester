package lab4;

import javax.swing.*;
import java.awt.*;

public class ImageBoardView extends JPanel implements IBoardView
{
    @Override
    public void initView(Board board)
    {
        GridLayout gridLayout = new GridLayout(board.getRows(), board.getColumns());
        this.setLayout(gridLayout);
        for (var tile : board.getTiles())
        {
            this.add((Component)tile.getTileView());
        }
    }
}
