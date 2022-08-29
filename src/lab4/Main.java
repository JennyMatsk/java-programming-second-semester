package lab4;

import javax.swing.*;
import java.awt.*;

public class Main
{
    public static void main(String[] args)
    {
        Board board = new Board(4,4);
        JFrame frame = new JFrame("GameOfFifteen");

        //2. Optional: What happens when the frame closes?
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //3. Create components and put them in the frame.
            //...create emptyLabel...
        frame.getContentPane().add((Component) board.getBoardView(), BorderLayout.CENTER);

        //4. Size the frame.
        frame.pack();
        frame.setSize(new Dimension(1024,960));
        frame.setLocationRelativeTo(null);

        //5. Show it.
        frame.setVisible(true);

    }
}