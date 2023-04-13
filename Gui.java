import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Gui {
    private JFrame frame;
    private static JPanel output;
    private static int row = 0, column = 0;
    private static ArrayList<JLabel> labels = new ArrayList<JLabel>();
    static JTextField field;
    static JButton addButton, select, remove, sort, save, load;
    final static String darkGray = "#222222", lightGray = "#252526", lighterGray = "#40444b", labelFont = "#bdbdbd", 
    buttonColor = "#b9bbbe", blue = "#303f9f";

    public Gui() {
        frame = new JFrame();
        frame.setTitle("Ronald Ng MP3 Project");
        frame.setSize(1200, 800);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.decode(darkGray)); 
        frame.setLayout(new FlowLayout());

        field = new JTextField();
        field.setPreferredSize(new Dimension(300,40));
        field.setBackground(Color.decode(lighterGray));
        field.setForeground(Color.decode(labelFont));
        field.setBorder(BorderFactory.createLineBorder(Color.decode(lighterGray)));
        field.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                field.setText("");
            }
        });

        addButton = new JButton("Add");
        addButton.setPreferredSize(new Dimension(100,40));
        addButton.setBackground(Color.decode(buttonColor));
        addButton.addActionListener((e) -> {
           addLabel(field.getText());
        });

        select = new JButton("Select Random");
        select.setPreferredSize(new Dimension(150,40));
        select.setBackground(Color.decode(buttonColor));
        select.addActionListener((e) -> {
            if (labels.size() > 0) {
                if (Main.tmpHold != -1) labels.get(Main.tmpHold).setBackground(Color.decode(blue));
                save.setEnabled(false);
                addButton.setEnabled(false);
                select.setEnabled(false);
                remove.setEnabled(false);
                field.setEditable(false);
                sort.setEnabled(false);
                Main.chooseRandom();
            }
        });

        remove = new JButton("Remove");
        remove.setPreferredSize(new Dimension(100,40));
        remove.setBackground(Color.decode(buttonColor)); 
        remove.addActionListener((e) -> {
            if (labels.size() > 0) {
                if (Main.tmpHold != -1) labels.get(Main.tmpHold).setBackground(Color.decode(blue));
                for (int i = 0; i < labels.size(); i++) {
                    if (labels.get(i).getText().toLowerCase().equals(field.getText().toLowerCase()) && !field.getText().equals("")) {
                        output.remove(labels.get(i));
                        labels.remove(i);
                        field.setText("");
                        output.validate();
                        output.repaint();
                        break;
                    } 
                }
            }
        });

        sort = new JButton("Alphabetical");
        sort.setPreferredSize(new Dimension(140,40));
        sort.setBackground(Color.decode(buttonColor));
        sort.addActionListener((e) -> {
            field.setText("");
            Main.selectionSort();
        });

        save = new JButton("Save");
        save.setPreferredSize(new Dimension(100,40));
        save.setBackground(Color.decode(buttonColor));
        save.addActionListener((e) -> {
            SaveData.makeFile();
            SaveData.editFile();
        });

        load = new JButton("Load");
        load.setPreferredSize(new Dimension(100,40));
        load.setBackground(Color.decode(buttonColor));
        load.addActionListener((e) -> {
            load.setEnabled(false);
            SaveData.readFile();
        });

        JPanel input = new JPanel();
        input.add(load);
        input.add(save);
        input.add(field);
        input.add(addButton);
        input.add(remove);
        input.add(sort);
        input.add(select);
        input.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        input.setBackground(Color.decode(lightGray));

        output = new JPanel();
        output.setLayout(new FlowLayout(FlowLayout.CENTER));
        output.setPreferredSize(new Dimension(900,515));
        output.setBackground(Color.decode(lightGray));
        output.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

        frame.add(input);
        frame.add(output);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    protected static void addLabel(String text) {
        if (!text.equals("")) {
            if (column == 7) {
                column = 0;
                row++;
            }

            // recolors the previous selected label from chooseRandom() method back to blue
            if (Main.tmpHold != -1) labels.get(Main.tmpHold).setBackground(Color.decode(blue));
            
            load.setEnabled(false);
            JLabel addLabel = new JLabel(text, SwingConstants.CENTER);
            addLabel.setPreferredSize(new Dimension(123,80));
            addLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            addLabel.setBackground(Color.decode(blue));
            addLabel.setForeground(Color.decode(labelFont));
            addLabel.setOpaque(true);

            column++;

            labels.add(addLabel);
            output.add(addLabel);
            output.validate();
            output.repaint();
        }
    }
    
    public ArrayList<JLabel> getArrList() {
        return labels;
    }
}
