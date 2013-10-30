package GUI;

import GUI.TableModels.UsersTableDataModel;
import MainMethods.AsteriskStats;
import Statistics.Call;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class RunningSettingsPanel extends JPanel {
    public static Double SIZE_RATIO = 0.25;
    private static int PREFERRED_WIDTH;
    private static JDateChooser FROM_DATE;
    private static JDateChooser TO_DATE;
    private static JCheckBox FROM_CHECK_BOX;
    private static JCheckBox TO_CHECK_BOX;
    private static PanelTable USERS_TABLE;
    private static NumberComboBox COMBO_SRC;
    private static NumberComboBox COMBO_DST;

    RunningSettingsPanel(){
        super();
        this.setLayout(new FlowLayout());
        this.setPreferredSize(Gui.getPreferredWidth(SIZE_RATIO));
        PREFERRED_WIDTH = Gui.getPreferredWidth(SIZE_RATIO).width;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        PanelLabel description = new PanelLabel("Параметры выборки данных.");
        this.add(description);

        FROM_CHECK_BOX = new PanelCheckbox("Фильтровать от:");
        FROM_CHECK_BOX.setSelected(true);
        this.add(FROM_CHECK_BOX);

        Calendar startDate = Calendar.getInstance();
        startDate.set(Calendar.DAY_OF_MONTH, 1);
        FROM_DATE = new PanelCalendar(startDate.getTime());
        this.add(FROM_DATE);

        TO_CHECK_BOX = new PanelCheckbox("Фильтровать до:");
        this.add(TO_CHECK_BOX);


        TO_DATE = new PanelCalendar(Calendar.getInstance().getTime());
        this.add(TO_DATE);


        JPanel panelFilterSrc = new JPanel();
        panelFilterSrc.setLayout(new BorderLayout());
            JLabel labelSrc = new JLabel("Фильтр по исходящим: ");
            labelSrc.setPreferredSize(new Dimension(130, labelSrc.getPreferredSize().height));
            panelFilterSrc.add(labelSrc, BorderLayout.WEST);


            COMBO_SRC = new NumberComboBox();
            panelFilterSrc.add(COMBO_SRC, BorderLayout.CENTER);
        panelFilterSrc.setPreferredSize(new Dimension(PREFERRED_WIDTH - 10, 20));

        this.add(panelFilterSrc);


        JPanel panelFilterDst = new JPanel();
        panelFilterDst.setLayout(new BorderLayout());
            JLabel labelDst = new JLabel("Фильтр по назначению: ");
            labelDst.setPreferredSize(new Dimension(130, labelDst.getPreferredSize().height));
            panelFilterDst.add(labelDst, BorderLayout.WEST);

            COMBO_DST = new NumberComboBox();
            panelFilterDst.add(COMBO_DST, BorderLayout.CENTER);
        panelFilterDst.setPreferredSize(new Dimension(PREFERRED_WIDTH - 10,20));
        this.add(panelFilterDst);

        USERS_TABLE = new PanelTable();
        this.add(new UsersPanel(USERS_TABLE));

    }

    public class NumberComboBox extends JComboBox<String>{
        NumberComboBox(){
            super();
            this.setModel(new NumberComboBoxModel());
            this.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getActionCommand().equals("comboBoxChanged")) AsteriskStats.updateInfo();
                }
            });
        }

        public String getPattern(){
            NumberComboBoxModel model = (NumberComboBoxModel)this.getModel();
            return model.getPattern();
        }
    }

    class NumberComboBoxModel implements ComboBoxModel<String>{
        String[][] data = {{"Внутренние", "Внешние", "Мобильные", "Все"},{"1__", "_____%", "0_________", "%%"}};
        int selectedItemId = 0;
        @Override
        public void setSelectedItem(Object anItem) {
            String selected = (String)anItem;
            if (selected.equals("Внутренние"))selectedItemId = 0;
            if (selected.equals("Внешние"))selectedItemId = 1;
            if (selected.equals("Мобильные"))selectedItemId = 2;
            if (selected.equals("Все"))selectedItemId = 3;
        }

        @Override
        public Object getSelectedItem() {
            return new String(data[0][selectedItemId]);
        }

        @Override
        public int getSize() {
            return data[0].length;
        }

        @Override
        public String getElementAt(int index) {
            return data[0][index];
        }

        @Override
        public void addListDataListener(ListDataListener l) {
        }

        @Override
        public void removeListDataListener(ListDataListener l) {

        }

        public String getPattern(){
            return data[1][selectedItemId];
        }
    };

    class UsersPanel extends JScrollPane{
        UsersPanel(){
            this(null);
        }

        UsersPanel(PanelTable table){
            super(table);
            this.setPreferredSize(new Dimension(RunningSettingsPanel.PREFERRED_WIDTH - 6, 550));
        }
    }

    public static Date getFromDate(){
        return FROM_DATE.getDate();
    }

    public static Date getToDate(){
        return TO_DATE.getDate();
    }

    public static NumberComboBox getCOMBO_SRC(){
        return COMBO_SRC;
    }

    public static NumberComboBox getCOMBO_DST(){
        return COMBO_DST;
    }

    public static boolean getFromCheckBoxState(){
        return FROM_CHECK_BOX.isSelected();
    }

    public static boolean getToCheckBoxState(){
        return TO_CHECK_BOX.isSelected();
    }

    public static JTable getUsersTable(){
        return USERS_TABLE;
    }

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
            this.setBackground(Color.LIGHT_GRAY);
            this.getTableHeader().setForeground(new Color(0,0,160));

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

    public static String[] getTimePeriod(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String[] out = new String[2];
        if (RunningSettingsPanel.getFromCheckBoxState())out[0] = dateFormat.format(RunningSettingsPanel.getFromDate());
        else out[0] = "2013-01-01";

        if (RunningSettingsPanel.getToCheckBoxState())out[1] = dateFormat.format(RunningSettingsPanel.getToDate());
        else {
            Calendar dateTo = Calendar.getInstance();
            dateTo.add(Calendar.DAY_OF_MONTH, 1);
            out[1] = dateFormat.format(dateTo.getTime());
        }
        return out;
    }


}


