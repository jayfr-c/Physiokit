import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.trees.J48;
import weka.classifiers.functions.SMO; 
import weka.classifiers.functions.supportVector.PolyKernel;
import weka.classifiers.functions.SimpleLogistic;
import weka.core.Debug;
import weka.core.Instances; 
import weka.core.Utils;
//source code link: http://emaraic.com/blog/weka-java-example
//source code edited

public class ModelBuilder {

	public static final String MODELPATH = "res/models/";

	public static void main(String[] args) throws Exception {

		ModelGenerator mg = new ModelGenerator();
 		
 		Instances dataset = DataHandler.loadDataset(); 
 		dataset.randomize(new Debug.Random(1)); 
 		
		/*----------------------Stratified Cross Validation-------------------------*/
		//MLP
		MultilayerPerceptron m = new MultilayerPerceptron(); 
		m.setValidationSetSize(0);
		m.setBatchSize("1");
		m.setLearningRate(0.001);
		m.setSeed(0);
		m.setMomentum(0.2);
		m.setTrainingTime(0); 
		m.setNormalizeAttributes(true);

		m = (MultilayerPerceptron) mg.buildClassifier(m, dataset);
		System.out.println("\nMULTILAYER PERCEPTRON\n");
		mg.crossValidateModel(m, dataset); 

		//Decision Tree 
		J48 j48 = new J48();
		j48.setOptions(new String[] { "-C", "0.25", "-M", "2" }); 
		j48 = (J48) mg.buildClassifier(j48, dataset);
		System.out.println("\nDECISION TREE\n");
		mg.crossValidateModel(j48, dataset);

		//SimpleLogistic Regression 
		/*
			SimpleLogistic handles determining the optimal number of logitboost iterations.
			LogitBoost is fitting the logistic models.
		*/
		SimpleLogistic log = new SimpleLogistic();
		log.setUseCrossValidation(true);
		log = (SimpleLogistic) mg.buildClassifier(log, dataset);
		System.out.println("\nLOGISTIC\n");
		mg.crossValidateModel(log, dataset);

		//SMO
		SMO smo = new SMO(); 
        smo.setOptions(Utils.splitOptions("-C 1.0 -L 0.001 -P 1.0E-12 -N 0 -V -1 -W 1 -K \"weka.classifiers.functions.supportVector.PolyKernel -C 250007 -E 1.0\""));
		smo.turnChecksOff();  

		smo = (SMO) mg.buildClassifier(smo, dataset);
		System.out.println("\nSMO\n");
		mg.crossValidateModel(smo, dataset);   

		/*---------------------Manual Cross Validation------------------------------*/

		mg.crossValidateModel2(j48, dataset);
		mg.crossValidateModel2(m, dataset);
		mg.crossValidateModel2(smo, dataset); 
		mg.crossValidateModel2(log, dataset);
		mg.writeResults();

		//========================================================SAVING MODEL======================================================
		/*---------------------Save Model--------------------------------------------*/
		mg.saveModel(m, MODELPATH, "MLP");
		mg.saveModel(j48, MODELPATH, "Decision Tree");
		mg.saveModel(smo, MODELPATH, "SMO");
		mg.saveModel(log, MODELPATH, "Logistic");		
	}
}