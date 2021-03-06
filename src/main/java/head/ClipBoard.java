package head;


import head.interfaces.CopyListener;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ClipBoard {
    
    
    static ArrayList<CopyListener> copyListenerTargets = new ArrayList<>();
    
    static String oldClip = "";
    static boolean onInit = true;
    static boolean onChangeCopy = false;


    private static TimerTask copyChecker = new TimerTask(){
        @Override
        public void run() {

            if(onChangeCopy){
                return;
            }

            String curClip = getClipBoard();
            
            if(hasSameBoard()){
                for (CopyListener listener : copyListenerTargets) {
                   listener.copyAction(curClip, false);
                }
                return;
            }

            oldClip = curClip;

            if(onInit){
                onInit = false;
                return;
            }

            for (CopyListener listener : copyListenerTargets) {
                listener.copyAction(curClip, true);
            }
            
        }
        
    };

    public static boolean hasSameBoard(){
        return getClipBoard().equals(oldClip);
    }

    private static Timer copyTimer = new Timer();
    private static boolean isRunningCopyChecker = false;
    
    
    public static void addCopyListener(Object target){

        if(!isRunningCopyChecker && copyListenerTargets.size() == 0){
            copyTimer.schedule(copyChecker, 100l, 100l);
            isRunningCopyChecker = true;
        }
        if(target instanceof CopyListener)
            copyListenerTargets.add((CopyListener)target);
        
    }
    
    public static String getClipBoard() {
        try {
            String s = Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor).toString();
            return s;
        } catch (Exception e) {

        }


        return "";
    }

    public static void setClipBoard(String str, boolean naturalCopy) {
        onChangeCopy = true;

        Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection testData;
        testData = new StringSelection(str);
        c.setContents(testData, testData);

        if(!naturalCopy){
            oldClip = str;
        }

        onChangeCopy = false;
    }

    public static final String
            FORMAT_ENTER = "</n>",
            FORMAT_TAB = "</t>";
    public static String getClipBoardFormated() {
        try {
            String s = Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor).toString();
            s.replaceAll("\n", FORMAT_ENTER);
            s.replaceAll("\t", FORMAT_TAB);
            System.out.println(s);
            return s;
        } catch (Exception e) {

        }


        return "";
    }


    public static void setClipBoardFormatted(String str, boolean naturalCopy){
        onChangeCopy = true;
        System.out.println(System.currentTimeMillis());
        System.out.println("\nBefore: "+str);
        str = str.replaceAll(FORMAT_ENTER, "\n");
        str = str.replaceAll(FORMAT_TAB, "\t");
        str = str.replaceAll(" (?=</->)", "");
        str = str.replaceAll("</->", "");

        System.out.println("after: "+str);
        Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection testData;
        testData = new StringSelection(str);
        try {
            c.setContents(testData, testData);
        } catch (Exception e){

        }

        if(!naturalCopy){
            oldClip = str;
        }

        onChangeCopy = false;
    }

    
}
