package head;

import com.sun.javafx.webkit.KeyCodeMap;
import head.interfaces.GlobalKeyboardListener;
import head.interfaces.GlobalMouseListener;
import javafx.scene.input.KeyCode;
import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

import static java.util.logging.Level.OFF;
import static java.util.logging.Logger.getLogger;
import static org.jnativehook.GlobalScreen.*;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;
import org.jnativehook.mouse.NativeMouseMotionListener;

public class BackgroundListener implements NativeKeyListener, NativeMouseMotionListener, NativeMouseInputListener{

    private final CountDownLatch mLatch = new CountDownLatch(100);

    public boolean running = false;

    public boolean isRunning() {
        return running;
    }

    public static ArrayList<GlobalKeyboardListener> keyListeners = new ArrayList<>();
    public static ArrayList<GlobalMouseListener> mouseListeners = new ArrayList<>();

    public static void addKeyboardListener(GlobalKeyboardListener listener) {
        keyListeners.add(listener);
    }

    public static void addMouseListener(GlobalMouseListener listener){
        mouseListeners.add(listener);
    }

    public BackgroundListener() {
        try {
            running = true;
            disableNativeHookLogger();
            registerNativeHook();
            addNativeKeyListener(this);
            addNativeMouseListener(this);
            addNativeMouseMotionListener(this);
            
            running = false;
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }

    }

    public void nativeKeyPressed(final NativeKeyEvent e) {
        try{
            System.out.println(e.getKeyCode()+" "+
                    NativeKeyEvent.getKeyText(e.getKeyCode())+" "+
                    KeyCode.getKeyCode(NativeKeyEvent.getKeyText(e.getKeyCode())).name()
            );
        } catch (Exception ex){
            ex.printStackTrace();
        }
        if (isRunning()) {
            keyListeners.forEach(listener -> listener.keyEvent(true, e.getKeyCode()));

        }

    }

    public void nativeKeyReleased(final NativeKeyEvent e) {
        if (isRunning()) {
            keyListeners.forEach(listener -> listener.keyEvent(false, e.getKeyCode()));
        }
    }

    public void nativeKeyTyped(final NativeKeyEvent e) {

    }

    public void start() {
        try {
            running = true;
            System.out.println("Awaiting keyboard events.");
            mLatch.await();
            running = false;
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
    }

    public void stop() {
        removeNativeKeyListener(this);
    }

    private static void disableNativeHookLogger() {
        Logger logger = getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(OFF);
        logger.setUseParentHandlers(false);
    }

    @Override
    public void nativeMouseMoved(NativeMouseEvent nme) {
        mouseMove(nme.getX(), nme.getY());
    }

    @Override
    public void nativeMouseDragged(NativeMouseEvent nme) {
        
    }

    @Override
    public void nativeMouseClicked(NativeMouseEvent nme) {
        
    }

    @Override
    public void nativeMousePressed(NativeMouseEvent nme) {
        mouseClick(true, nme.getButton(), nme.getX(), nme.getY());
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent nme) {
        mouseClick(false, nme.getButton(), nme.getX(), nme.getY());
    }
    
    public void mouseClick(boolean b, int i, int x, int y){
        if (isRunning()) {
            mouseListeners.forEach(listener -> listener.mouseClick(b, i, x,y));
        }
    }

    public void mouseMove(int x, int y){
        if (isRunning()) {
            mouseListeners.forEach(listener -> listener.mouseMove(x,y));
        }
    }

}