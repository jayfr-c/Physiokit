import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.classifiers.Evaluation; 
import weka.classifiers.evaluation.Prediction;

import weka.core.Instances;
import weka.core.SerializationHelper;

import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Normalize;
import java.io.IOException; 
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Random;

//source code link: http://emaraic.com/blog/weka-java-example
//source code edited

public class ModelGenerator {
	String results = "";

	public Classifier buildClassifier(Classifier model, Instances trainingSet) { 
		try {
			model.buildClassifier(trainingSet);
		} catch (Exception e) {
			Logger.getLogger(ModelGenerator.class.getName()).log(Level.SEVERE, null, e);
		}
		return model; 
	}

	public void evaluateModel(Classifier model, Instances trainingSet, Instances testingSet) {
		Evaluation eval = null;
		try {
			eval = new Evaluation(trainingSet);
			eval.evaluateModel(model, testingSet);
		} catch (Exception e) {
			Logger.getLogger(ModelGenerator.class.getName()).log(Level.SEVERE, null, e);
		}
		System.out.println(eval.toSummaryString("", true));
	}

	public void saveModel(Classifier model, String modelpath, String name) {
		try {
			SerializationHelper.write(modelpath + name + ".model", model);
		} catch (Exception e) {
			Logger.getLogger(ModelGenerator.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	public static String getEvaluationDetails(Classifier model, Instances dataset) {
		try {
			Evaluation eval = new Evaluation(dataset);
			eval.crossValidateModel(model, dataset, 10, new Random(1)); 
			return eval.toSummaryString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//-------------------cross validate model-------------------------------------------  
	public static Evaluation crossValidateModel(Classifier model, Instances dataset) { 
		
		try {
			Evaluation eval = new Evaluation(dataset);
			eval.crossValidateModel(model, dataset, 10, new Random(1));
			System.out.println(eval.toSummaryString("\nResults\n\n", false));
			/*matrix*/
			System.out.println(eval.toMatrixString());
			/*TP/FP rates, precision, recall, F-measure, AUC*/
			System.out.println(eval.toClassDetailsString()); 
			/*testing precision and recall*/
			System.out.println("Weighted Precision: "+ eval.weightedPrecision());
			System.out.println("Weighted Recall: "+ eval.weightedRecall());
			System.out.println("AUROC : "+ eval.weightedAreaUnderROC());

			return eval;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Evaluation classify(Classifier model, Instances trainingSet, Instances testingSet) {
		Evaluation eval = null; 
		try {
			eval = new Evaluation(trainingSet);
			eval.evaluateModel(model, testingSet);  
			results +=  String.format("%.3f", eval.precision(0)) + "\t" 
					+ String.format("%.3f", eval.recall(0)) + "\t" 
					+ String.format("%.3f", eval.fMeasure(0)) + "\t"
					+ String.format("%.3f", eval.areaUnderROC(0)) + "\n"; 
			/*results +=  String.format("%.2f%%", eval.precision(1)) + "\t" 
					  + String.format("%.2f%%", eval.recall(1)) + "\t" 
					  + String.format("%.2f%%", eval.fMeasure(1)) + "\t" 
					  + String.format("%.2f%%", eval.areaUnderROC(1)) + "\n";*/
			return eval;
		}catch (Exception e) { 
			e.printStackTrace();
			return null;
		}
	}

	public double calculateAverageAccuracy(ArrayList<Object> predictions) {
		double correct = 0;
		double size = 0;

		for (Object n : predictions) { 
			ArrayList<Prediction> list = (ArrayList<Prediction>) n;
			for (Prediction p : list) {
				size++;
				if (p.predicted() == p.actual()) {
					correct++;
				}
			}
		}

		return 100 * correct / size;
	}

	public double calculateAccuracy(ArrayList<Prediction> predictions) {
		double correct = 0;
		double size = 0;
		for (Prediction p : predictions) {
			size++;
			if (p.predicted() == p.actual()) {
				correct++;
			}
		} 
		return 100 * correct / size; 
	}

	public Instances[][] crossValidationSplit(Instances data, int numberOfFolds) {

		Instances[][] split = new Instances[2][numberOfFolds];

		for (int i = 0; i < numberOfFolds; i++) {

			split[0][i] = data.trainCV(numberOfFolds, i);
			split[1][i] = data.testCV(numberOfFolds, i);

		}

		return split;
	}

	public Instances normalizeSet(Instances dataset) {
		try {
			Filter filter = new Normalize();
		 	filter.setInputFormat(dataset);
		 	Instances normalized = Filter.useFilter(dataset, filter);
		 	return normalized;
		} catch (Exception e) {
			Logger.getLogger(ModelGenerator.class.getName()).log(Level.SEVERE, null, e);
			return null;
		}
		
	}

	public void crossValidateModel2(Classifier model, Instances dataset) {
		//acknowledge source of code
		try { 

			dataset = normalizeSet(dataset); 

			Evaluation eval = null; 

			Instances[][] split = crossValidationSplit(dataset, 10);

			Instances[] trainingSplits = split[0]; System.out.println("Train\n"+trainingSplits[0].numInstances());
			Instances[] testingSplits = split[1]; System.out.println("Test\n"+testingSplits[0].numInstances());

			ArrayList<Object> predictions = new ArrayList<>();

	 		results += model + "\n";
			for (int i = 0; i < trainingSplits.length; i++) {
				//results += "Iteration no. " + i + "\n";
				eval = classify(model, trainingSplits[i], testingSplits[i]); 

				predictions.add(eval.predictions()); //eval.predictions instanceof ArrayList<Prediction>
				//results += "Accuracy: " + calculateAccuracy(eval.predictions()) +"\n"; 
			} 
			for (Object p : predictions) {
				results +=  String.format("%.2f", calculateAccuracy((ArrayList<Prediction>)p)) + "\n";
			}

			results += "================================================================\n";
			
			double accuracy = calculateAverageAccuracy(predictions); 

			System.out.println(" Accuracy of "+model.getClass().getSimpleName() + ": "
							+String.format("%.2f%%", accuracy));
		} catch (Exception e) {
			Logger.getLogger(ModelGenerator.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public void writeResults() throws IOException {
		ArrayList<String> list = new ArrayList<>();
		list.add(results);
		DataHandler.writeLines(DataPaths.RESULTS, list);
	}
}