package lab4;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

    Board board;

    public KeyboardListener(Board board) {
        this.board = board;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Отправляем полученное значение нажатой кнопки классу Board для обработки
        board.tryMoveTileWithKeyboard(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
