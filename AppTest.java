import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import weka.classifiers.Classifier;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.trees.J48;
import weka.classifiers.functions.SMO; 
import weka.classifiers.functions.supportVector.PolyKernel;
import weka.classifiers.functions.SimpleLogistic;
import weka.core.Instances; 
import weka.core.Utils; 

public class AppTest {
 	
 	private int numOfFolds;
 	private int numOfGroups;
 	private Instances dataset;
 	private Instances testInstance;
 	private ModelGenerator mg;
 	private InstanceProcessor proc;


 	public AppTest(Instances dataset, int numOfFolds, int numOfGroups) throws IOException {
 		this.dataset = dataset;
 		this.numOfFolds = numOfFolds;
 		this.numOfGroups = numOfGroups;
 		this.mg = new ModelGenerator();
 		this.proc = new InstanceProcessor();
 	}
	/*dataset testing*/
	private void testReadDataset() {
		Assert.assertEquals(DataHandler.loadDataset() instanceof Instances, true);
	}

	private void checkNumOfLoadedInstances() {
		Assert.assertEquals(dataset.numInstances(), 520);
	}

	private void checkNumOfAttributes() {
		Assert.assertEquals(dataset.numAttributes(), 17);
	}

	/*model build testing*/
	public void testSerializedModels() {
	
		Classifier dt = InstanceClassifier.getClassifier("Decision Tree");
		Classifier mlp = InstanceClassifier.getClassifier("MLP");
		Classifier smo = InstanceClassifier.getClassifier("SMO");
		Classifier log = InstanceClassifier.getClassifier("Logistic");

		Assert.assertEquals(dt instanceof J48, true);
		Assert.assertEquals(mlp instanceof MultilayerPerceptron, true);
		Assert.assertEquals(smo instanceof SMO, true);
		Assert.assertEquals(log instanceof SimpleLogistic, true);
	}

	/*crossvalidation testing*/
	public void testCrossValidationSplit() { 
		Instances[][] insts = mg.crossValidationSplit(dataset, 10);
		Instances[] instRow = insts[0];
		Instances instFoldTraining = insts[0][0];
		Instances instFoldTesting = insts[1][0];

		Assert.assertEquals(instRow.length, numOfFolds);
		Assert.assertEquals(insts.length, numOfGroups);
		//one group of 9 training folds
		Assert.assertEquals(instFoldTraining.numInstances(), 468);
		//one testing fold
		Assert.assertEquals(instFoldTesting.numInstances(), 52);
	} 

	/*instance inputs testing*/ 
	private HashMap<String, Double> createData() {
		HashMap<String, Double> testMap = new HashMap<String, Double>();
		testMap.put("Age", 40.0);
		testMap.put("Gender", 1.0);
		testMap.put("Polyuria", 0.0);
		testMap.put("Polydipsia", 1.0);
		testMap.put("Sudden Weight Loss", 0.0); 
		testMap.put("Fatigue", 1.0);
		testMap.put("Polyphagia", 0.0);
		testMap.put("Genital Thrush", 0.0);
		testMap.put("Visual Blurring", 0.0);
		testMap.put("Itching", 1.0);
		testMap.put("Irritability", 0.0);
		testMap.put("Delayed Healing", 1.0);
		testMap.put("Partial Paresis", 0.0);
		testMap.put("Muscle Stiffness", 1.0);
		testMap.put("Alopecia", 1.0);
		testMap.put("Obesity", 1.0);
		testMap.put("class", 1.0);
		//on unclassified instances, the value of the class attribute is default 0.0
		return testMap;

	}
	public void testInputInstances() throws IOException{
		 
		HashMap<String, Double> testMap = createData();
		proc.setInputsAndOption(testMap, 0);
		proc.createInstance();
		testInstance = proc.getData();

		//testing first attribute
		Assert.assertEquals(testInstance.firstInstance().value(0), 40.0, 0.0d);
		//testing middle attribute
		Assert.assertEquals(testInstance.firstInstance().value(8), 0.0, 0.0d);
		//testing last attribute
		Assert.assertEquals(testInstance.firstInstance().value(16), 0.0, 0.0d);
	}

	/*outputs testing*/
	public void testClassifierOutput() throws IOException {
		double[] valueMLP = InstanceClassifier.classifyMLP(testInstance);
		double[] valueJ48 = InstanceClassifier.classifyJ48(testInstance);
		double[] valueSMO = InstanceClassifier.classifySMO(testInstance);
		double[] valueLog = InstanceClassifier.classifyLog(testInstance);

		Assert.assertEquals(valueMLP[2] == 0.0 || valueMLP[2] == 1.0, true);
		Assert.assertEquals(valueJ48[2] == 0.0 || valueJ48[2] == 1.0, true);
		Assert.assertEquals(valueSMO[2] == 0.0 || valueSMO[2] == 1.0, true);
		Assert.assertEquals(valueLog[2] == 0.0 || valueLog[2] == 1.0, true);
	}


	public static void main(String[] args) throws Exception {

		AppTest test = new AppTest(DataHandler.loadDataset(), 10, 2);
		test.testReadDataset();
		test.checkNumOfLoadedInstances();
		test.checkNumOfAttributes();

		test.testSerializedModels();

		test.testCrossValidationSplit();

		test.testInputInstances();

		test.testClassifierOutput();
	}
}