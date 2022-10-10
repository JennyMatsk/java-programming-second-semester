package lab4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ImageTileView extends JPanel implements ITileView
{
    private Tile tile;
    private int tileSize = 220;
    @Override
    public void initView(Tile tile)
    {
        this.tile = tile;
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                tile.tryMoveTileOnBoard();
            }
        });
    }

    @Override
    public void updateView(int number)
    {
        repaint();
    }

    // Отрисовка клетки
    public void drawTile(Graphics2D g)
    {
        g.setColor(getForeground());
        g.fillRoundRect(tile.getPosition().x  , tile.getPosition().y , tileSize, tileSize, 25, 25);
        g.setColor(Color.GREEN);
        g.drawRoundRect(tile.getPosition().x  , tile.getPosition().y , tileSize, tileSize, 25, 25);
        g.setColor(Color.WHITE);

        if (tile.getNumber() == 0)
        {
            drawCenteredString(g, "", tile.getPosition().x, tile.getPosition().y);
        }
        else
        {
            drawCenteredString(g, String.valueOf(tile.getNumber()), tile.getPosition().x, tile.getPosition().y);
        }
    }

    private void drawCenteredString(Graphics2D g, String s, int x, int y)
    {
        // Рисуем строку с большим шрифтом
        g.setFont(new Font("TimesRoman", Font.PLAIN, 80));
        FontMetrics fm = g.getFontMetrics();
        int asc = fm.getAscent();
        int desc = fm.getDescent();
        g.drawString(s,  x + (tileSize - fm.stringWidth(s)) / 2,
                y + (asc + (tileSize - (asc + desc)) / 2));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawTile(g2D);
    }
}
