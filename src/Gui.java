import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Ivan
 * Date: 10/18/13
 * Time: 4:52 PM
 * To change this template use File | Settings | File Templates.
 */
public  class Gui extends JFrame {
    private static  Gui mainWindow;
    private  Gui(){
        super();
        this.setSize(400,300);
        this.getContentPane().setLayout(null);
        JButton knopka = new JButton();
        knopka.setBounds(100, 100, 100, 40);
        knopka.setText("Hello");
        this.add(knopka);
        knopka.setActionCommand("We pushed first button");
        knopka.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print(e.getActionCommand());
            }
        });
        JSpinner spinner = new JSpinner();
        this.add(spinner);
        spinner.setBounds(150,150,80,30);
        spinner.setModel();


    }

    class MySpinnerModel implements SpinnerModel {

        @Override
        public Object getValue() {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void setValue(Object value) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public Object getNextValue() {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public Object getPreviousValue() {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void addChangeListener(ChangeListener l) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void removeChangeListener(ChangeListener l) {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    }


    public static Gui createGui(){
        if (mainWindow == null) return new Gui();
        else return mainWindow;
    }

}

