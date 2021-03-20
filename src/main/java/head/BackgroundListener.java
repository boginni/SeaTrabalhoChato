package head;

import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

import static java.util.logging.Level.OFF;
import static java.util.logging.Logger.getLogger;
import static org.jnativehook.GlobalScreen.*;

public class BackgroundListener implements NativeKeyListener {

    private final CountDownLatch mLatch = new CountDownLatch(100);

    public boolean running = false;

    public boolean isRunning() {
        return running;
    }

    public static ArrayList<Object> listeners = new ArrayList<>();

    public static void addListener(Object listener) {
        if (listener instanceof BackgroundInputListener){
            listeners.add(listener);
        }
    }

    public BackgroundListener() {
        try {
            running = true;
            disableNativeHookLogger();
            registerNativeHook();
            addNativeKeyListener(this);
            running = false;
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }

    }

    public void nativeKeyPressed(final NativeKeyEvent e) {
        if (isRunning()) {
            for (Object listener: listeners
            ) {
                ((BackgroundInputListener)listener).keyEvent(true, e.getKeyCode());
            }

        }

    }

    public void nativeKeyReleased(final NativeKeyEvent e) {
        if (isRunning()) {
            for (Object listener: listeners
            ) {
                ((BackgroundInputListener)listener).keyEvent(false, e.getKeyCode());
            }

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
}