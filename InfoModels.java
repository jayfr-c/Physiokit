import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import javax.swing.*;
import java.util.ArrayList;

public class InfoModels extends Applet{
    private Frame window;
    ArrayList<JRadioButton> modelchoices;
    JTextArea textarea_1;
    JScrollPane sp_textarea_1;
    ButtonGroup cbg;
    JButton visualize;
    JButton stats;
    JRadioButton mlpButton;
    JRadioButton dtButton;
    JRadioButton smoButton;
    JRadioButton logButton;
    String currentModel = "";

    public void init() {
        app_detailsLayout customLayout = new app_detailsLayout();
        modelchoices = new ArrayList<>();

        setFont(new Font("Helvetica", Font.PLAIN, 12));
        setLayout(customLayout);

        textarea_1 = new JTextArea("");
        textarea_1.setEditable(false);
        sp_textarea_1 = new JScrollPane(textarea_1);
        add(sp_textarea_1);

        cbg = new ButtonGroup();

        mlpButton = new JRadioButton("MLP", false);
        cbg.add(mlpButton);  
        
        add(mlpButton);
        modelchoices.add(mlpButton);

        dtButton = new JRadioButton("Decision Tree", false);
        cbg.add(dtButton);
        add(dtButton);
        modelchoices.add(dtButton);

        smoButton = new JRadioButton("SMO", false);
        cbg.add(smoButton);
        add(smoButton);
        modelchoices.add(smoButton);

        logButton = new JRadioButton("Logistic", false);
        cbg.add(logButton);
        add(logButton);
        modelchoices.add(logButton);

        //groupadding action listeners
        for (JRadioButton b : modelchoices) {
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    textarea_1.setText(b.getText());
                    currentModel = b.getText();
                }
            });
        }

        visualize = new JButton("Visualize");   //component 5
        visualize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentModel.equals("")) {
                    JOptionPane.showMessageDialog(window, "Choose from the selection", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        Visualizer.visualizeROC(ModelGenerator.crossValidateModel(InstanceClassifier.getClassifier(currentModel),DataHandler.loadDataset()));
                    } catch (Exception error) {}
                }
            }
        });
        add(visualize);

        stats = new JButton("Statistics");  //component 6
        stats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentModel.equals("")) {
                    JOptionPane.showMessageDialog(window, "Choose from the selection", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    String textInfo = textarea_1.getText();
                    try {
                        textInfo += "\n\n==========================================" + 
                        ModelGenerator.getEvaluationDetails(InstanceClassifier.getClassifier(currentModel),DataHandler.loadDataset());
                        textarea_1.setText(textInfo);
                    } catch (Exception error) {}
                }
                
            }
        });
        add(stats);

        setSize(getPreferredSize());

    }
    public void setWindow(Frame window) {
        this.window = window;
    }
   
}

class app_detailsLayout implements LayoutManager {

    public app_detailsLayout() {
    }

    public void addLayoutComponent(String name, Component comp) {
    }

    public void removeLayoutComponent(Component comp) {
    }

    public Dimension preferredLayoutSize(Container parent) {
        Dimension dim = new Dimension(0, 0);

        Insets insets = parent.getInsets();
        dim.width = 403 + insets.left + insets.right;
        dim.height = 373 + insets.top + insets.bottom;

        return dim;
    }

    public Dimension minimumLayoutSize(Container parent) {
        Dimension dim = new Dimension(0, 0);
        return dim;
    }

    public void layoutContainer(Container parent) {
        Insets insets = parent.getInsets();

        Component c;
        c = parent.getComponent(0);
        if (c.isVisible()) {c.setBounds(insets.left+104,insets.top+0,296,360);}
        c = parent.getComponent(1);
        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+8,88,24);}
        c = parent.getComponent(2);
        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+40,88,24);}
        c = parent.getComponent(3);
        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+72,88,24);}
        c = parent.getComponent(4);
        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+104,88,24);}
        c = parent.getComponent(5);
        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+300,88,24);} //visualizer button
        c = parent.getComponent(6);
        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+330,88,24);} //stats button
    }
}
