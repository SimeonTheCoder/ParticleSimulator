package app.ui.sensors;

import app.rendering.Renderer;
import app.ui.Window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
    private KeyHandler handler;
    private Window window;

    public Keyboard(KeyHandler handler, Window window) {
        this.handler = handler;
        this.window = window;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char c = e.getKeyChar();

        if(c == 'a') handler.handle(1, window);
        if(c == 'd') handler.handle(2, window);
        if(c == 'w') handler.handle(3, window);
        if(c == 's') handler.handle(4, window);
        if(c == '1') handler.handle(5, window);
        if(c == '2') handler.handle(6, window);
        if(c == '3') handler.handle(7, window);
        if(c == '4') handler.handle(8, window);
        if(c == 'e') handler.handle(9, window);
        if(c == '5') handler.handle(10, window);
        if(c == '6') handler.handle(11, window);
        if(c == 'p') handler.handle(12, window);
        if(c == 't') handler.handle(13, window);
        if(c == 'r') handler.handle(14, window);
        if(c == 'k') handler.handle(15, window);

        if(c == 'b') Renderer.DEBUG = !Renderer.DEBUG;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
