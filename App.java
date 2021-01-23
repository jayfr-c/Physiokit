import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import javax.swing.*; 
import javax.swing.text.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class App extends JApplet implements ActionListener{
    static JFrame window;   
    ArrayList<JRadioButton> symptoms; 
    ArrayList<String> queue; 
    int queueIndex;
    String previousFile; 

    String file = ""; 
    InstanceProcessor ip; 
    JTextPane predictionResult;  
    JTextArea definition; 
    JLabel toDefine;
    JLabel ageLabel;
    JLabel genderLabel; 
    JTextArea instructionLabel; 
    JComboBox furtherDetails;
    JTextArea detailsArea;
    JScrollPane spDetailsArea;
    JButton predict;
    JButton modelDetails;
    JButton detailsExpand;
    JButton clear;
    JButton backNav;
    JButton forwardNav;
    JButton homeNav;
    JTextField age;
    JComboBox gender; 
    JLabel cls; 
 
    public void init() {
        AppComponents ac = new AppComponents();   
        symptoms = ac.appRadioButtons();
        queue = new ArrayList<>();
        queueIndex = -1;
        previousFile = "";
        file = "Prediabetes";
        AppLayout customLayout = new AppLayout(); 
        getContentPane().setFont(new Font("Monaco", Font.BOLD, 20));
        getContentPane().setLayout(customLayout);

        predictionResult = ac.appTextPane(); 

        furtherDetails = ac.appComboBox(AppOptions.getDetailsOptions(), "Further Details");
        furtherDetails.addActionListener(this);
        try {
            ip = new InstanceProcessor(); 
            definition = ac.appTextArea("Definition", AppOptions.boxContent(file)); 
            
            detailsArea = ac.appTextArea("further details",
                          AppOptions.boxContent(furtherDetails, file)); 
        } catch (Exception e) {}


        toDefine = ac.appLabel("Prediabetes");
        ageLabel = ac.newLabel("Age");
        genderLabel = ac.newLabel("Gender");
        instructionLabel = ac.appBasicText("instructions","Fill up personal information and check present symptoms.");
        instructionLabel.setOpaque(false);

        predict = ac.appButton("Predict");
        predict.addActionListener(this);

        age = ac.appTextField("Enter Age");

        gender = ac.appComboBox(AppOptions.getGenderOptions(), "Gender"); 

        //cls = ac.appComboBox(AppOptions.getModelOptions(), "classifier options");
        cls = ac.appLabel("Multilayer Perceptron"); 
         
        modelDetails = ac.appButton(AppOptions.getIcon("qIcon"));           
        modelDetails.addActionListener(this);

        detailsExpand = ac.appButton(AppOptions.getIcon("expIcon"));         
        detailsExpand.addActionListener(this);

        clear = ac.appButton(AppOptions.getIcon("clearIcon"));
        clear.addActionListener(this);

        backNav = ac.appButton(AppOptions.getIcon("backIcon"));
        backNav.addActionListener(this);

        forwardNav = ac.appButton(AppOptions.getIcon("forwardIcon"));
        forwardNav.addActionListener(this);

        homeNav = ac.appButton(AppOptions.getIcon("homeIcon"));
        homeNav.addActionListener(this);

        getContentPane().add(ac.appScrollPane(predictionResult));               //component 0
        getContentPane().add(ac.appScrollPane(definition));                     //component 1
        getContentPane().add(toDefine);                                         //component 2
        getContentPane().add(predict);                                          //component 3
        getContentPane().add(furtherDetails);                                   //component 4
        getContentPane().add(ac.appScrollPane(detailsArea));                    //component 5  
        getContentPane().add(age);                                              //component 6
        getContentPane().add(gender);                                           //component 7
        for (JRadioButton b : symptoms) {
            getContentPane().add(b);
            b.setFont(new Font("Serif", Font.BOLD, 14));
            b.addActionListener(this);
        }  
        getContentPane().add(cls);                                        //component 22 
        getContentPane().add(modelDetails);                                     //component 23
        getContentPane().add(detailsExpand);                                    //component 24
        getContentPane().add(clear);                                            //component 25		
        getContentPane().add(ageLabel);                                         //component 26
        getContentPane().add(genderLabel);                                      //component 27
        getContentPane().add(backNav);                                          //component 28
        getContentPane().add(forwardNav);                                       //component 29
        getContentPane().add(homeNav);                                          //component 30
        getContentPane().add(instructionLabel);                                          //component 31
        setSize(getPreferredSize());

    } 

    @Override 
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == predict) { 
            if (age.getText().equals("")) {
                JOptionPane.showMessageDialog(window, "Age is required.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try { 
                    HashMap<String, Double> inputs = mapInputs(); 
                    ip.setInputsAndOption(inputs, 0);
                    predictionResult.setText(ip.classifyInput()); 
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } 

        if (e.getSource() == modelDetails) {
            window.setEnabled(false);
            System.out.println("details clicked");
            InfoModels details = new InfoModels();
            Frame modelsWindow = new Frame("Model Details");


            modelsWindow.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    window.setEnabled(true);
                    modelsWindow.dispose();
                }
            });

            details.setWindow(modelsWindow);
            details.init();
            modelsWindow.add("Center", details);
            modelsWindow.pack();
            modelsWindow.setVisible(true);
        }

        if (e.getSource() == detailsExpand) {
            window.setEnabled(false);
            System.out.println("symptom details clicked");
            InfoSymptoms applet = new InfoSymptoms();
            Frame symptomsWindow = new Frame("Symptoms Details");

            symptomsWindow.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    window.setEnabled(true);
                    symptomsWindow.dispose();
                }
            });

            applet.init();
            symptomsWindow.add("Center", applet);
            symptomsWindow.pack();
            symptomsWindow.setVisible(true);
        }
        if (e.getSource() instanceof JRadioButton) {
                JRadioButton b = (JRadioButton) e.getSource();
                file = b.getText();  
                if (!file.equals(previousFile)) {    //prevents consequtive duplications in queue 
                    previousFile = file; 
                    queue.add(file);
                    queueIndex = queue.size() - 1; 
                }
                setDefinitions(file); 
        }
        if (e.getSource() instanceof JComboBox) {
            try {
                JComboBox b = (JComboBox) e.getSource();
                detailsArea.setText(AppOptions.boxContent(b, file));
            } catch (IOException io) {io.printStackTrace();}
        }
        if (e.getSource() == clear) {
        	for (JRadioButton b : symptoms) {
        		b.setSelected(false);
        	}
        }
        if (e.getSource() == backNav) { 
            if (queueIndex > 0) { 
                queueIndex -= 1; 
                setDefinitions(queue.get(queueIndex));
            } else if (queueIndex == 0){
                setDefinitions("Prediabetes");
                queueIndex = -1; 
            } else {
                JOptionPane.showMessageDialog(window, "No back items available", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        if (e.getSource() == forwardNav) { 
            if ((queueIndex + 1) < queue.size()) {
                queueIndex++;
                setDefinitions(queue.get(queueIndex));
            } else {
                JOptionPane.showMessageDialog(window, "Latest clicked item reached", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        if (e.getSource() == homeNav) {
            setDefinitions("Prediabetes");
            queueIndex = queue.size() - 2; 
            //option to clear array in this event
        }
    }
    private void setDefinitions(String file) {
        try {
            toDefine.setText(file); 
            definition.setText(DataHandler.openInfo(file)); 
            detailsArea.setText(AppOptions.boxContent(furtherDetails, file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private HashMap<String, Double> mapInputs() {
        HashMap<String, Double> inputs = new HashMap<>();  
        try {
            inputs.put("Age", Double.parseDouble(age.getText()));   
        } catch (Exception e) {
            JOptionPane.showMessageDialog(window, "Inappropriate value for age.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        if (gender.getSelectedItem() == "Female") {
            inputs.put("Gender", 0.0);
        } else {
            inputs.put("Gender", 1.0);
        }
        for (JRadioButton b : symptoms) {
            if (b.isSelected()) {
                inputs.put(b.getText(), 1.0);
            } else {
                inputs.put(b.getText(), 0.0);
            }
        } 
        inputs.put("class", 0.0);  
        return inputs;
    }

    public static void main(String args[]) throws IOException {
        App app = new App();
        window = new JFrame("Prediabetes Prediction Application");  

        window.addWindowListener(new WindowAdapter() { 
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        }); 
        app.init(); 
        window.getContentPane().add("Center", app);
        window.pack();
        window.setVisible(true);
    }
}

