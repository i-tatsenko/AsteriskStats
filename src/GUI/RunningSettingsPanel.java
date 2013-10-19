package GUI;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;


public class RunningSettingsPanel extends JPanel {
    private static Double MINIMUMSIZERATIO = 0.15;
    private static Double SIZERATIO = 0.2;
    private static Double MAXIMUMSIZERATIO = 0.25;
    private static int PREFERREDWIDTH;

    RunningSettingsPanel(){
        super();
        this.setLayout(new FlowLayout());
        this.setPreferredSize(Gui.getPreferredWidth(SIZERATIO));
        this.PREFERREDWIDTH = new Double(Gui.getPreferredWidth(SIZERATIO).getWidth()).intValue();
        this.setMinimumSize(Gui.getPreferredWidth(MINIMUMSIZERATIO));
        this.setMaximumSize(Gui.getPreferredWidth(MAXIMUMSIZERATIO));

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        PanelLabel opisanie = new PanelLabel("Параметры выборки данных.");
        PanelCheckbox filterStartDate = new PanelCheckbox("Фильтровать от:");
        PanelCheckbox filterEndDate = new PanelCheckbox("Фильтровать до:");
        Calendar startDate = Calendar.getInstance();
        startDate.set(Calendar.DAY_OF_MONTH, 1);
        JDateChooser fromDate = new JDateChooser(startDate.getTime());
        JDateChooser toDate = new JDateChooser(Calendar.getInstance().getTime());
        PanelTable users = new PanelTable();
        users.setBackground(this.getBackground());

        this.add(opisanie);
        this.add(filterStartDate);
        this.add(fromDate);
        filterStartDate.setSelected(true);
        this.add(filterEndDate);
        this.add(toDate);
        this.add(users);


        System.out.print(fromDate.getSize());
    }


    class PanelLabel extends JLabel{
        private PanelLabel(){}

        PanelLabel(String text){
            super(text);
            this.setPreferredSize(new Dimension(PREFERREDWIDTH, this.getFont().getSize()));
            this.setHorizontalAlignment(SwingConstants.CENTER);
        }
    }

    class PanelCheckbox extends JCheckBox{
        PanelCheckbox(){
            super();
           // this.setPreferredSize(new Dimension(PREFERREDWIDTH - 5, this.getFont().getSize() + 5));
        }

        PanelCheckbox(String text){
            super(text);
            this.setPreferredSize(new Dimension(150, this.getFont().getSize() + 5));
            this.setHorizontalAlignment(SwingConstants.LEFT);

        }
    }

    class PanelTable extends JTable{
        PanelTable(){
            super();
            this.setPreferredSize(new Dimension(PREFERREDWIDTH - 2, 200));
            this.setModel(new UsersTableDataModel());
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            this.getColumnModel().getColumn(0).setPreferredWidth(10);

        }
    }


}
