package GUI;

import GUI.TableModels.UsersTableDataModel;
import Statistics.Call;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.Date;


public class RunningSettingsPanel extends JPanel {
    private static Double MINIMUM_SIZE_RATIO = 0.15;
    private static Double SIZE_RATIO = 0.2;
    private static Double MAXIMUM_SIZE_RATIO = 0.25;
    private static int PREFERRED_WIDTH;
    private static JDateChooser FROM_DATE;
    private static JDateChooser TO_DATE;
    private static JCheckBox FROM_CHECK_BOX;
    private static JCheckBox TO_CHECK_BOX;
    private static JTable USERS_TABLE;

    RunningSettingsPanel(){
        super();
        this.setLayout(new FlowLayout());
        this.setPreferredSize(Gui.getPreferredWidth(SIZE_RATIO));
        this.PREFERRED_WIDTH = new Double(Gui.getPreferredWidth(SIZE_RATIO).getWidth()).intValue();
        this.setMinimumSize(Gui.getPreferredWidth(MINIMUM_SIZE_RATIO));
        this.setMaximumSize(Gui.getPreferredWidth(MAXIMUM_SIZE_RATIO));

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        PanelLabel opisanie = new PanelLabel("Параметры выборки данных.");
        PanelCheckbox filterStartDate = new PanelCheckbox("Фильтровать от:");
        FROM_CHECK_BOX = filterStartDate;
        PanelCheckbox filterEndDate = new PanelCheckbox("Фильтровать до:");
        TO_CHECK_BOX = filterEndDate;
        Calendar startDate = Calendar.getInstance();
        startDate.set(Calendar.DAY_OF_MONTH, 1);
        JDateChooser fromDate = new PanelCalendar(startDate.getTime());
        FROM_DATE = fromDate;
        JDateChooser toDate = new PanelCalendar(Calendar.getInstance().getTime());
        TO_DATE = toDate;
        PanelTable users = new PanelTable();
        users.setBackground(this.getBackground());
        USERS_TABLE = users;

        this.add(opisanie);
        this.add(filterStartDate);
        this.add(fromDate);
        filterStartDate.setSelected(true);
        this.add(filterEndDate);
        this.add(toDate);
        this.add(new UsersPanel(users));

    }

    class UsersPanel extends JScrollPane{
        UsersPanel(){
            this(null);
        }

        UsersPanel(PanelTable table){
            super(table);
            this.setPreferredSize(new Dimension(RunningSettingsPanel.PREFERRED_WIDTH, 500));
        }
    }

    public static Date getFromDate(){
        return FROM_DATE.getDate();
    }

    public static Date getToDate(){
        return TO_DATE.getDate();
    }

    public static boolean getFromCheckBoxState(){
        return FROM_CHECK_BOX.isSelected();
    }

    public static boolean getToCheckBoxState(){
        return TO_CHECK_BOX.isSelected();
    }

    public static JTable getUsersTable(){
        return USERS_TABLE;
    };

    class PanelCalendar extends JDateChooser{
        PanelCalendar(){
            this(Calendar.getInstance().getTime());

        }

        PanelCalendar(java.util.Date time){
            super(time);
            this.setPreferredSize(new Dimension(110, this.getFont().getSize() + 7));
            this.setAlignmentX(JDateChooser.CENTER_ALIGNMENT);
            this.setAlignmentY(JDateChooser.CENTER_ALIGNMENT);
            this.addPropertyChangeListener(new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    if ((evt.getOldValue() != null) && (!evt.getOldValue().equals(evt.getNewValue())))
                    MainMethods.AsteriskStats.updateInfo();
                }
            });
        }
    }

    class PanelLabel extends JLabel{
        private PanelLabel(){}

        PanelLabel(String text){
            super(text);
            this.setPreferredSize(new Dimension(PREFERRED_WIDTH, this.getFont().getSize()));
            this.setHorizontalAlignment(SwingConstants.CENTER);

        }


    }

    class PanelCheckbox extends JCheckBox{
        PanelCheckbox(){
            super();
           // this.setPreferredSize(new Dimension(PREFERRED_WIDTH - 5, this.getFont().getSize() + 5));
        }

        PanelCheckbox(String text){
            super(text);
            this.setPreferredSize(new Dimension(150, this.getFont().getSize() + 5));
            this.setHorizontalAlignment(SwingConstants.LEFT);
            this.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MainMethods.AsteriskStats.updateInfo();
                }
            });
        }
    }

    class PanelTable extends JTable{
        PanelTable(){
            super();
            this.setPreferredSize(new Dimension(PREFERRED_WIDTH - 2, 500));
            this.setModel(new UsersTableDataModel());
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            this.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    int row = getSelectedRow();
                    String user = (String)getValueAt(row, 0);
                    PopNumbersPanel.getSRCTable().setData(Gui.CallLogStats.getPopNumber(Call.SRC, user));
                    PopNumbersPanel.getDSTTable().setData(Gui.CallLogStats.getPopNumber(Call.DST, user));
                }
            });

        }
    }

    public static Double getSizeRatio(){
        return SIZE_RATIO;
    }
}


