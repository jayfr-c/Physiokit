import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import javax.swing.*; 
import javax.swing.text.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class AppComponents {
	private Font titleFont = new Font("Serif", Font.BOLD, 22);
	private Font regular = new Font("Serif", Font.BOLD, 14);
	private Font contentFont = new Font("Monaco", Font.PLAIN, 14);

	public JLabel appLabel(String label) {
		JLabel comp = new JLabel(label); 
        comp.setFont(titleFont);
        return comp;
	}
        public JLabel newLabel(String label) {
                JLabel comp = new JLabel(label);
                comp.setFont(contentFont);
                return comp; 
        }
	public JTextField appTextField(String toolTipText) {
		JTextField comp =  new JTextField(); 
        comp.setFont(regular);
        comp.setToolTipText(toolTipText);
        return comp;
	}
	public JTextArea appTextArea(String toolTipText, String content) {
		JTextArea comp = new JTextArea("");
        comp.setToolTipText(toolTipText);
        /*Text Area Settings Source: https://alvinalexander.com/source-code/java/how-set-font-caret-position-and-margins-insets-jtextarea-component/*/
        comp.setFont(contentFont); 
        comp.setLineWrap(true);
        comp.setWrapStyleWord(true);
        comp.setMargin(new Insets(10, 10, 10, 10)); 
        comp.setText(content);  
        comp.setEditable(false);
        DefaultCaret caret = (DefaultCaret) comp.getCaret();
        caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE); 
        return comp;
	}

        public JTextArea appBasicText(String toolTipText, String content) {
                JTextArea comp = new JTextArea("");
        comp.setToolTipText(toolTipText);
        /*Text Area Settings Source: https://alvinalexander.com/source-code/java/how-set-font-caret-position-and-margins-insets-jtextarea-component/*/
        comp.setFont(regular);   
        comp.setLineWrap(true);
        comp.setText(content);  
        comp.setEditable(false);  
        return comp;
        }

	public JTextPane appTextPane() {
		JTextPane comp = new JTextPane();
        comp.setToolTipText("Prediction Result");
        comp.setText("Results");
        comp.setFont(new Font("Monaco", Font.PLAIN, 14)); 
        comp.setEditable(false);
        StyledDocument doc = comp.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        return comp;
	}
	public JScrollPane appScrollPane(JTextPane comp) {
		return new JScrollPane(comp);
	}
	public JScrollPane appScrollPane(JTextArea comp) {
		return new JScrollPane(comp);
	}
	public JComboBox appComboBox(List<String> options, String toolTipText) {
		JComboBox comp = new JComboBox(); 
        comp.setToolTipText(toolTipText);
        comp.setFont(regular); 
        for (String option : options) {
        	comp.addItem(option);
        }
        return comp;
	}
	public JButton appButton(String name) {
		JButton comp = new JButton("Predict");
        comp.setFont(regular);
        return comp;
	}
	public JButton appButton(Icon icon) {
		JButton comp = new JButton(icon);
		return comp;
	}
	public ArrayList<JRadioButton> appRadioButtons() {
		ArrayList<JRadioButton> symptoms = new ArrayList<>();
		JRadioButton radio_1 = new JRadioButton("Polyuria", false); //component 8  
        JRadioButton radio_2 = new JRadioButton("Polydipsia", false); //component 9  
        JRadioButton radio_3 = new JRadioButton("Sudden Weight Loss", false); //component 10  
        JRadioButton radio_4 = new JRadioButton("Fatigue", false); //component 11  
        JRadioButton radio_5 = new JRadioButton("Polyphagia", false); //component 12  
        JRadioButton radio_6 = new JRadioButton("Genital Thrush", false); //component 13  
        JRadioButton radio_7 = new JRadioButton("Visual Blurring", false); //component 14  
        JRadioButton radio_8 = new JRadioButton("Itching", false); //component 15  
        JRadioButton radio_9 = new JRadioButton("Irritability", false); //component 16  
        JRadioButton radio_10 = new JRadioButton("Delayed Healing", false); //component 17  
        JRadioButton radio_11 = new JRadioButton("Partial Paresis", false); //component 18  
        JRadioButton radio_12 = new JRadioButton("Muscle Stiffness", false); //component 19  
        JRadioButton radio_13 = new JRadioButton("Alopecia", false); //component 20  
        JRadioButton radio_14 = new JRadioButton("Obesity", false); //component 21  
		symptoms.add(radio_1);
        symptoms.add(radio_2);
        symptoms.add(radio_3);
        symptoms.add(radio_4);
        symptoms.add(radio_5);
        symptoms.add(radio_6);
        symptoms.add(radio_7);
        symptoms.add(radio_8);
        symptoms.add(radio_9);
        symptoms.add(radio_10);
        symptoms.add(radio_11);
        symptoms.add(radio_12);
        symptoms.add(radio_13);
        symptoms.add(radio_14);
        return symptoms;
	}
}