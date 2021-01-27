import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import javax.swing.*; 

public class AppLayout implements LayoutManager {

    public AppLayout() {}

    public void addLayoutComponent(String name, Component comp) {}

    public void removeLayoutComponent(Component comp) {}

    public Dimension preferredLayoutSize(Container parent) {
        Dimension dim = new Dimension(0, 0);

        Insets insets = parent.getInsets();
        dim.width = 636 + insets.left + insets.right;
        dim.height = 520 + insets.top + insets.bottom;

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
        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+460,245,50);}   //prediction result (under button)
        c = parent.getComponent(1);
        if (c.isVisible()) {c.setBounds(insets.left+272,insets.top+42,352,100);} //Definition Area
        c = parent.getComponent(2);         
        if (c.isVisible()) {c.setBounds(insets.left+272,insets.top+8,352,32);} //toDefine Label
        c = parent.getComponent(3);
        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+420,245,32);}  //predictor button
        c = parent.getComponent(4);
        if (c.isVisible()) {c.setBounds(insets.left+272,insets.top+150,176,32);} //further details option box
        c = parent.getComponent(5);
        if (c.isVisible()) {c.setBounds(insets.left+272,insets.top+190,352,320);} //further details area
        c = parent.getComponent(6);
        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+65,72,24);}  //age textfield
        c = parent.getComponent(7);
        if (c.isVisible()) {c.setBounds(insets.left+88,insets.top+65,112,24);} //gender combo box
        c = parent.getComponent(8);
        if (c.isVisible()) {c.setBounds(insets.left+32,insets.top+89,100,24);} //polyuria
        c = parent.getComponent(9);
        if (c.isVisible()) {c.setBounds(insets.left+32,insets.top+109,100,24);} //polydipsia
        c = parent.getComponent(10);
        if (c.isVisible()) {c.setBounds(insets.left+32,insets.top+129,200,24);} //sudden weight loss
        c = parent.getComponent(11);
        if (c.isVisible()) {c.setBounds(insets.left+32,insets.top+149,200,24);} //fatigue
        c = parent.getComponent(12);
        if (c.isVisible()) {c.setBounds(insets.left+32,insets.top+169,200,24);} //polyphagia
        c = parent.getComponent(13);
        if (c.isVisible()) {c.setBounds(insets.left+32,insets.top+189,200,24);} //genital thrush
        c = parent.getComponent(14);
        if (c.isVisible()) {c.setBounds(insets.left+32,insets.top+209,200,24);} //visualblurring
        c = parent.getComponent(15);
        if (c.isVisible()) {c.setBounds(insets.left+32,insets.top+229,200,24);} //itching
        c = parent.getComponent(16);
        if (c.isVisible()) {c.setBounds(insets.left+32,insets.top+249,200,24);} //irritability
        c = parent.getComponent(17);
        if (c.isVisible()) {c.setBounds(insets.left+32,insets.top+269,200,24);} //delayed healing
        c = parent.getComponent(18);
        if (c.isVisible()) {c.setBounds(insets.left+32,insets.top+289,200,24);} //partial paresis
        c = parent.getComponent(19);
        if (c.isVisible()) {c.setBounds(insets.left+32,insets.top+309,200,24);} //muscle stiffness
        c = parent.getComponent(20);
        if (c.isVisible()) {c.setBounds(insets.left+32,insets.top+329,200,24);} //alopecia
        c = parent.getComponent(21);
        if (c.isVisible()) {c.setBounds(insets.left+32,insets.top+349,200,24);} //obesity 
        c = parent.getComponent(22);
        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+8,220,32);} //classifier text area
        c = parent.getComponent(23);                                                    
        if (c.isVisible()) {c.setBounds(insets.left+230,insets.top+10,24,24);} //models details
        c = parent.getComponent(24);
        if (c.isVisible()) {c.setBounds(insets.left+455,insets.top+150,32,32);} //details expand
        c = parent.getComponent(25);
        if (c.isVisible()) {c.setBounds(insets.left+205,insets.top+65,24,24);} //clear button
        c = parent.getComponent(26);
        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+37,240,32);} //age label
        c = parent.getComponent(27);
        if (c.isVisible()) {c.setBounds(insets.left+88,insets.top+37,240,32);} //gender label
        c = parent.getComponent(28);         
        if (c.isVisible()) {c.setBounds(insets.left+530,insets.top+10,24,24);} //back nav
        c = parent.getComponent(29);         
        if (c.isVisible()) {c.setBounds(insets.left+560,insets.top+10,24,24);} //forward nav
        c = parent.getComponent(30);         
        if (c.isVisible()) {c.setBounds(insets.left+590,insets.top+10,24,24);} //home nav
        c = parent.getComponent(31);         
        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+375,245,44);} //instructions label
    }
}
