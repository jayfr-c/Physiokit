import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import javax.swing.*; 

public class AppLayout implements LayoutManager {

    public AppLayout() {
    }

    public void addLayoutComponent(String name, Component comp) {
    }

    public void removeLayoutComponent(Component comp) {
    }

    public Dimension preferredLayoutSize(Container parent) {
        Dimension dim = new Dimension(0, 0);

        Insets insets = parent.getInsets();
        dim.width = 636 + insets.left + insets.right;
        dim.height = 462 + insets.top + insets.bottom;

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
        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+8,240,32);}   //prediction result
        c = parent.getComponent(1);
        if (c.isVisible()) {c.setBounds(insets.left+272,insets.top+42,352,100);} //Definition Area
        c = parent.getComponent(2);         
        if (c.isVisible()) {c.setBounds(insets.left+272,insets.top+8,352,32);} //toDefine Label
        c = parent.getComponent(3);
        if (c.isVisible()) {c.setBounds(insets.left+56,insets.top+420,144,32);}  //predictor button
        c = parent.getComponent(4);
        if (c.isVisible()) {c.setBounds(insets.left+272,insets.top+150,176,32);} //further details option box
        c = parent.getComponent(5);
        if (c.isVisible()) {c.setBounds(insets.left+272,insets.top+190,352,263);} //further details area
        c = parent.getComponent(6);
        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+48,72,24);}  //age textfield
        c = parent.getComponent(7);
        if (c.isVisible()) {c.setBounds(insets.left+88,insets.top+48,112,24);} //gender combo box
        c = parent.getComponent(8);
        if (c.isVisible()) {c.setBounds(insets.left+32,insets.top+80,100,24);} //polyuria
        c = parent.getComponent(9);
        if (c.isVisible()) {c.setBounds(insets.left+32,insets.top+100,100,24);} //polydipsia
        c = parent.getComponent(10);
        if (c.isVisible()) {c.setBounds(insets.left+32,insets.top+120,200,24);} //sudden weight loss
        c = parent.getComponent(11);
        if (c.isVisible()) {c.setBounds(insets.left+32,insets.top+140,200,24);} //fatigue
        c = parent.getComponent(12);
        if (c.isVisible()) {c.setBounds(insets.left+32,insets.top+160,200,24);} //polyphagia
        c = parent.getComponent(13);
        if (c.isVisible()) {c.setBounds(insets.left+32,insets.top+180,200,24);} //genital thrush
        c = parent.getComponent(14);
        if (c.isVisible()) {c.setBounds(insets.left+32,insets.top+200,200,24);} //visualblurring
        c = parent.getComponent(15);
        if (c.isVisible()) {c.setBounds(insets.left+32,insets.top+220,200,24);} //itching
        c = parent.getComponent(16);
        if (c.isVisible()) {c.setBounds(insets.left+32,insets.top+240,200,24);} //irritability
        c = parent.getComponent(17);
        if (c.isVisible()) {c.setBounds(insets.left+32,insets.top+260,200,24);} //delayed healing
        c = parent.getComponent(18);
        if (c.isVisible()) {c.setBounds(insets.left+32,insets.top+280,200,24);} //partial paresis
        c = parent.getComponent(19);
        if (c.isVisible()) {c.setBounds(insets.left+32,insets.top+300,200,24);} //muscle stiffness
        //pane 
        c = parent.getComponent(20);
        if (c.isVisible()) {c.setBounds(insets.left+32,insets.top+320,200,24);} //alopecia
        c = parent.getComponent(21);
        if (c.isVisible()) {c.setBounds(insets.left+32,insets.top+340,200,24);} //obesity 
        c = parent.getComponent(22);
        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+375,220,32);} //options combo box 
        c = parent.getComponent(23);                                                    
        if (c.isVisible()) {c.setBounds(insets.left+235,insets.top+375,20,32);} //models details
        c = parent.getComponent(24);
        if (c.isVisible()) {c.setBounds(insets.left+455,insets.top+150,32,32);} //details expand
        c = parent.getComponent(25);
        if (c.isVisible()) {c.setBounds(insets.left+205,insets.top+48,24,24);} //details expand
    }
}
