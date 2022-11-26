package app.ui.sensors;

import app.ui.Window;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener {
    private Window window;
    private KeyHandler handler;

    public static boolean MOUSE_DOWN = false;

    public Mouse(KeyHandler handler, Window window) {
        this.window = window;
        this.handler = handler;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        MOUSE_DOWN = true;

        handler.handle(6, window);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        MOUSE_DOWN = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
