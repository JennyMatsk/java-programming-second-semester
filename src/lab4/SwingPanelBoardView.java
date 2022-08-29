package lab4;

import java.awt.*;

public class SwingPanelBoardView extends Panel implements IBoardView
{
    public void initView(Board board)
    {
        GridLayout gridLayout = new GridLayout(board.getRows() ,board.getColumns() );
        this.setLayout(gridLayout);
        for (var tile : board.getTiles())
        {
            this.add((Component)tile.getTileView());
        }
    }
}
