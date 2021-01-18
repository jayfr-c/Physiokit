import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class InfoSymptoms extends Applet{
    JTextArea textarea_1;
    JScrollPane sp_textarea_1;
    ButtonGroup cbg; 
    JRadioButton polyuria;
    JRadioButton polydipsia;
    JRadioButton suddenWeightLoss;
    JRadioButton fatigue;
    JRadioButton polyphagia;
    JRadioButton genitalThrush;
    JRadioButton visualBlurring;
    JRadioButton itching;
    JRadioButton irritability;
    JRadioButton delayedHealing;
    JRadioButton partialParesis;
    JRadioButton muscleStiffness;
    JRadioButton alopecia;
    JRadioButton obesity; 
    ArrayList<JRadioButton> buttons;

    public void init(){
        SymptomsDetailsLayout customLayout = new SymptomsDetailsLayout();
        buttons = new ArrayList<>();

        setFont(new Font("Helvetica", Font.PLAIN, 12));
        setLayout(customLayout);

        textarea_1 = new JTextArea("");
        textarea_1.setEditable(false);
        sp_textarea_1 = new JScrollPane(textarea_1);
        add(sp_textarea_1);

        cbg = new ButtonGroup();
 
        polyuria = new JRadioButton("Polyuria", false);
        polydipsia = new JRadioButton("Polydipsia", false);
        suddenWeightLoss = new JRadioButton("Weight Loss", false);
        fatigue = new JRadioButton("Fatigue", false);
        polyphagia = new JRadioButton("Polyphagia", false);
        genitalThrush = new JRadioButton("Genital Thrush", false);
        visualBlurring = new JRadioButton("Visual Blurring", false);
        itching = new JRadioButton("Itching", false);
        irritability = new JRadioButton("Irritability", false);
        delayedHealing = new JRadioButton("Delayed Healing", false);
        partialParesis = new JRadioButton("Partial Paresis", false);
        muscleStiffness = new JRadioButton("Muscle Stiffness", false);
        alopecia = new JRadioButton("Alopecia", false);
        obesity = new JRadioButton("Obesity", false); 

        cbg.add(polyuria);
        cbg.add(polydipsia);
        cbg.add(suddenWeightLoss);
        cbg.add(fatigue);
        cbg.add(polyphagia);
        cbg.add(genitalThrush);
        cbg.add(visualBlurring);
        cbg.add(itching);
        cbg.add(irritability);
        cbg.add(delayedHealing);
        cbg.add(partialParesis);
        cbg.add(muscleStiffness);
        cbg.add(alopecia);
        cbg.add(obesity); 

        /*to ArrayList for iterative addition of actionListeners*/
        buttons.add(polyuria);
        buttons.add(polydipsia);
        buttons.add(suddenWeightLoss);
        buttons.add(fatigue);
        buttons.add(polyphagia);
        buttons.add(genitalThrush);
        buttons.add(visualBlurring);
        buttons.add(itching);
        buttons.add(irritability);
        buttons.add(delayedHealing);
        buttons.add(partialParesis);
        buttons.add(muscleStiffness);
        buttons.add(alopecia);
        buttons.add(obesity); 

        add(polyuria);
        add(polydipsia);
        add(suddenWeightLoss);
        add(fatigue);
        add(polyphagia);
        add(genitalThrush);
        add(visualBlurring);
        add(itching);
        add(irritability);
        add(delayedHealing);
        add(partialParesis);
        add(muscleStiffness);
        add(alopecia);
        add(obesity); 

        for (JRadioButton b : buttons) {
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { 
                    try {
                        textarea_1.setText(DataHandler.openInfo(b.getText()));
                    } catch (IOException io) {
                        io.printStackTrace();
                    } 
                }
            });
        } 
        setSize(getPreferredSize());
    }
}

class SymptomsDetailsLayout implements LayoutManager {

    public SymptomsDetailsLayout() {
    }

    public void addLayoutComponent(String name, Component comp) {
    }

    public void removeLayoutComponent(Component comp) {
    }

    public Dimension preferredLayoutSize(Container parent) {
        Dimension dim = new Dimension(0, 0);

        Insets insets = parent.getInsets();
        dim.width = 440 + insets.left + insets.right;
        dim.height = 475 + insets.top + insets.bottom;

        return dim;
    }

    public Dimension minimumLayoutSize(Container parent) {
        Dimension dim = new Dimension(0, 0);
        return dim;
    }

    public void layoutContainer(Container parent) {
        Insets insets = parent.getInsets();

        Component c;     
        /*
            Total components = 1(textarea) + no.of symptoms
        */
        c = parent.getComponent(0);
        if (c.isVisible()) {c.setBounds(insets.left+104,insets.top+0,320,380);}
        c = parent.getComponent(1);
        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+8,88,24);}
        c = parent.getComponent(2);
        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+40,88,24);}
        c = parent.getComponent(3);
        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+72,88,24);}
        c = parent.getComponent(4);
        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+104,88,24);}
        c = parent.getComponent(5);
        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+136,88,24);}
        c = parent.getComponent(6);
        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+168,88,24);}
        c = parent.getComponent(7);
        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+200,88,24);}
        c = parent.getComponent(8);
        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+232,88,24);}
        c = parent.getComponent(9);
        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+264,88,24);}
        c = parent.getComponent(10);
        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+296,88,24);}
        c = parent.getComponent(11);
        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+328,88,24);}
        c = parent.getComponent(12);
        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+360,88,24);}
        c = parent.getComponent(13);
        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+392,88,24);}
        c = parent.getComponent(14);
        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+424,88,24);} 
    }
}
