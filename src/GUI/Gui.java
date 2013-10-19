package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Ivan
 * Date: 10/18/13
 * Time: 4:52 PM
 * To change this template use File | Settings | File Templates.
 */
public  class Gui extends JFrame {
    private final static Double SCREENRATIO = 0.7;
    private static  Gui mainWindow;
    private  Gui(){
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setNormalWindowsize(this);
        this.setLayout(new BorderLayout());
        this.add(new RunningSettingsPanel(), BorderLayout.WEST);
    }

    private void setNormalWindowsize(JFrame window){
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int windowHeight = new Double(screenSize.getHeight() * SCREENRATIO).intValue();
        int windowWidth = new Double(screenSize.getWidth() * SCREENRATIO).intValue();
        int locationX = new Double((screenSize.getWidth() - windowWidth) / 2).intValue();
        int locationY = new Double((screenSize.getHeight() - windowHeight) / 2).intValue();
        window.setBounds(locationX, locationY, windowWidth, windowHeight);
    }

    public static Dimension getPreferredWidth(Double screenRatio){
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = new Double(screenSize.getHeight() * SCREENRATIO).intValue();
        int width = new Double(screenSize.getWidth() * screenRatio).intValue();

        return new Dimension(width, height);
    }

    public static Gui createGui(){
        if (mainWindow == null) return new Gui();
        else return mainWindow;
    }

}

