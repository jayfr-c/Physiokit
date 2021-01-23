import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import javax.swing.*;
import java.util.ArrayList;

public class InfoModels extends Applet{
    private Frame window; 
    JTextArea textarea_1;
    JScrollPane sp_textarea_1;
    ButtonGroup cbg;
    JButton visualize;
    JButton stats; 
    String currentModel = "";

    public void init() {
        app_detailsLayout customLayout = new app_detailsLayout(); 

        setFont(new Font("Helvetica", Font.PLAIN, 12));
        setLayout(customLayout);

        textarea_1 = new JTextArea("");
        textarea_1.setEditable(false);
        sp_textarea_1 = new JScrollPane(textarea_1);
        add(sp_textarea_1);
        
        textarea_1.setText("MLP"); 

        visualize = new JButton("Visualize");   //component 5
        visualize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                try {
                    Visualizer.visualizeROC(ModelGenerator.crossValidateModel(InstanceClassifier.getClassifier(currentModel),DataHandler.loadDataset()));
                } catch (Exception error) {}
                
            }
        });
        add(visualize);

        stats = new JButton("Statistics");  //component 6
        stats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textInfo = textarea_1.getText();
                try {
                    textInfo += "\n\n==========================================" + 
                    ModelGenerator.getEvaluationDetails(InstanceClassifier.getClassifier(currentModel),DataHandler.loadDataset());
                    textarea_1.setText(textInfo);
                } catch (Exception error) {}
                 
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
        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+37,88,24);}
    }
}
