package lab4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingButtonTileView extends JButton implements ITileView
{
    public void initView(Tile tile)
    {
        this.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                tile.tryMoveTileOnBoard();
            }
        });
    }

    public void updateView(int number)
    {
        if (number == 0)
        {
            this.setText("");
            return;
        }
        this.setText(Integer.toString(number));
    }


}
