import weka.core.Attribute; 
import weka.core.Instance;
import weka.core.Instances;
import weka.core.DenseInstance;

import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.Math;

public class InstanceProcessor {
	private ArrayList attributes;
	private Attribute age; 

	private List genderLabels;
	private List valueLabels;
	private List classLabels;
	
	private Instances data; 

	private int clsOption;
	private HashMap<String, Double> inputs; 

	public InstanceProcessor() throws IOException{
		attributes = new ArrayList();

		genderLabels = new ArrayList();
		genderLabels.add("Female");
		genderLabels.add("Male");
		valueLabels = new ArrayList();
		valueLabels.add("No");
		valueLabels.add("Yes");
		classLabels = new ArrayList();
		classLabels.add("Negative");
		classLabels.add("Positive");

		age = new Attribute("Age"); 
		attributes.add(age); 
		attributes.add(new Attribute("Gender", genderLabels)); 

		/*Iterating through symptoms prior to attribute addition */
		List<String> symptomsList = DataHandler.getSymptoms();
		for (int i = 0; i < symptomsList.size(); i++) {       
			attributes.add(new Attribute(symptomsList.get(i), valueLabels));
			//System.out.println("@adding attributes/expect symptoms/expect 14 values/"+symptomsList.get(i)+" is added");	         
		} 
		attributes.add(new Attribute("class", classLabels));   

	 	data = new Instances("testInstances", attributes, 1);
	 	data.setClassIndex(data.numAttributes() - 1);
	 			//System.out.println(attributes.size());
	}  
	public void setInputsAndOption(HashMap<String, Double>  inputs, int clsOption) {
		this.inputs = inputs;
		this.clsOption = clsOption;
	}
	public void createInstance() throws IOException {  
	 	int count = 0;
	 	data.clear();
	 	double[] instanceValues = convertHashMap(inputs);
	 	Instance inst = new DenseInstance(1.0, instanceValues);
	 	data.add(inst); 
		 	//System.out.println("The instance : " + inst);
		 	//System.out.println("data.firstInstance: " + data.firstInstance());
	}

	private double[] convertHashMap(HashMap<String, Double> input) throws IOException{
		double[] values = new double[17];
		List<String> symptoms = DataHandler.getSymptoms();
		values[0] = input.get("Age");
		values[1] = input.get("Gender");
		for (int i = 2, j = 0; i < 16; i++, j++) {
			values[i] = input.get(symptoms.get(j));
		} 
		values[16] = 0.0;
		return values; 
	}

	public Instances getData() {
		return data; 
	} 

	private double[] trydata() {
		int yes = 0, no = 0;
		double[] values = new double[17];
		for (int i = 0; i < 17; i++) {
			double num = Math.round(Math.random());
			if (num == 0.0) {
				no++;
			} else {
				yes ++;
			}
			values[i] = num;
			System.out.print(" "+values[i]+" ");
		} 
			//System.out.println("array size: " + values.length);
			//System.out.println("Yes: "+ yes+" - "+ ((yes/values.length)*100)+"%"+"\tNo: "+no+" - "+((no/values.length)*100)+"%");
		return values;
	}
	//method returns label
	public String classifyInput() throws IOException{
		//null inputs to be handled @main
		//input should be in 1s or 0s   
		double prediction = 0.0;
		double[] values = new double[3];
		createInstance(); 
		switch(clsOption) {
			case 0 : { 
				values = InstanceClassifier.classifyMLP(data);
				prediction = values[2];
				System.out.println("--classifying with Multilayer Perceptron: "+prediction);
				break;
			}
			case 1 : { 
				values = InstanceClassifier.classifyJ48(data);
				prediction = values[2];
				System.out.println("--classifying with Decision Tree: "+prediction);
				break;
			}
			case 2 : {
				values = InstanceClassifier.classifySMO(data);
				prediction = values[2];
				System.out.println("--classifying with SMO: "+prediction);
				break;
			}
			case 3 : {
				values = InstanceClassifier.classifyLog(data);
				prediction = values[2];
				System.out.println("--classifying with Logistic Regression: "+prediction);
				break;
			}
		}
		if (prediction == 0.0) {
			return "Negative\n" + String.format("%.2f", (values[0] * 100)) + "% N\t" + String.format("%.2f", (values[1] * 100)) + "% P";
		}
		if (prediction == -1.0) {
			return "unable to process";
		}
		return "Positive\n" + String.format("%.2f", (values[0] * 100)) + "% N\t" + String.format("%.2f", (values[1] * 100)) + "% P";
		 
	}
 
}















