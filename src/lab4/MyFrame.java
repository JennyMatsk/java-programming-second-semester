package lab4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame {

    public MyFrame(Board board) {
        JFrame frame = new JFrame("GameOfFifteen");
        frame.addKeyListener(new KeyboardListener(board));
        JDialog aboutDialog = new JDialog(frame);
        JLabel label = new JLabel("<html>Мацканюк Евгения<br>P32693<br>2022 год</html>", SwingConstants.CENTER);

        // Установка диалога
        aboutDialog.add(label);
        aboutDialog.setModal(true);
        aboutDialog.setSize(200, 200);
        aboutDialog.setLocationRelativeTo(frame);

        // Добавление меню
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        JMenuItem newMenu = new JMenuItem("New");
        JMenuItem exitMenu = new JMenuItem("Exit");
        fileMenu.add(newMenu);
        fileMenu.add(exitMenu);

        JMenu helpMenu = new JMenu("Help");

        JMenuItem aboutMenu = new JMenuItem("About");
        helpMenu.add(aboutMenu);


        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        // Опция при нажатии на кнопку закрыть
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add((Component) board.getBoardView(), BorderLayout.CENTER);

        frame.setJMenuBar(menuBar);
        // Установка размера окна
        frame.pack();
        frame.setSize(new Dimension(1024,960));
        frame.setLocationRelativeTo(null);

        // Отображение окна
        frame.setVisible(true);

        // Слушатели нажатия на меню
        exitMenu.addActionListener(e -> System.exit(0));
        newMenu.addActionListener(e -> board.newGame(board.getRows(), board.getColumns()));
        aboutMenu.addActionListener(e -> aboutDialog.setVisible(true));
    }
}
