import weka.classifiers.Classifier;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.trees.J48;
import weka.classifiers.functions.SMO; 
import weka.classifiers.functions.SimpleLogistic;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;

import java.util.logging.Level;
import java.util.logging.Logger;

public class InstanceClassifier { 

	public static Classifier getClassifier(String name) {
		try {
			return (Classifier) SerializationHelper.read("res/models/"+name+".model");
		} catch (Exception e) {
			Logger.getLogger(ModelGenerator.class.getName()).log(Level.SEVERE, null, e);
			return null;
		}
	}

	private static double[] getResults(Classifier c, Instances instance) { 
		double[] results = new double[3];
		try {
			double[] distribution = c.distributionForInstance(instance.firstInstance());
			results[0] = distribution[0];
			results[1] = distribution[1];
			results[2] = c.classifyInstance(instance.firstInstance());
		} catch (Exception e) {
			Logger.getLogger(ModelGenerator.class.getName()).log(Level.SEVERE, null, e);
			results[2] = -1; 
		}
		return results;
	}

	public static double[] classifyMLP(Instances instance) {
		try {
			MultilayerPerceptron mlp = (MultilayerPerceptron) getClassifier("MLP");  
			return getResults(mlp, instance);
		} catch (Exception e) {
			Logger.getLogger(ModelGenerator.class.getName()).log(Level.SEVERE, null, e);
			return null;
		}
	}

	public static double[] classifyJ48(Instances instance) {
		try {
			J48 dtree = (J48) getClassifier("Decision Tree");
			return getResults(dtree, instance);
		} catch (Exception e) {
			Logger.getLogger(ModelGenerator.class.getName()).log(Level.SEVERE, null, e);
			return null;
		}
	}

	public static double[] classifySMO(Instances instance) {
		try {
			SMO smo = (SMO) getClassifier("SMO");
			return getResults(smo, instance);
		} catch (Exception e) {
			Logger.getLogger(ModelGenerator.class.getName()).log(Level.SEVERE, null, e);
			return null;
		}
	}
	
	public static double[] classifyLog(Instances instance) {
		try {
			SimpleLogistic log = (SimpleLogistic) getClassifier("Logistic");
			return getResults(log, instance);
		} catch (Exception e) {
			Logger.getLogger(ModelGenerator.class.getName()).log(Level.SEVERE, null, e);
			return null;
		}
	}
}