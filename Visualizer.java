import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import weka.core.Instances;
import weka.core.Utils;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.trees.J48;
import weka.classifiers.functions.SMO; 
import weka.classifiers.functions.Logistic;
import weka.classifiers.evaluation.ThresholdCurve;
import weka.classifiers.Evaluation; 
import weka.gui.visualize.PlotData2D;
import weka.gui.visualize.ThresholdVisualizePanel;

public class Visualizer {
	
	public static void visualizeROC(Evaluation eval) throws Exception { 
		ThresholdCurve tc = new ThresholdCurve();
		int classIndex = 0; // ROC for the 1st class label
		Instances curve = tc.getCurve(eval.predictions(), classIndex);

		PlotData2D plotdata = new PlotData2D(curve);
		plotdata.setPlotName(curve.relationName());
		plotdata.addInstanceNumberAttribute();

		ThresholdVisualizePanel tvp = new ThresholdVisualizePanel();
		tvp.setROCString("(Area under ROC = " +
		Utils.doubleToString(ThresholdCurve.getROCArea(curve),4)+")");
		tvp.setName(curve.relationName());
		tvp.addPlot(plotdata);

		final JFrame jf = new JFrame("WEKA ROC: " + tvp.getName());
		jf.setSize(500,400);
		jf.getContentPane().setLayout(new BorderLayout());
		jf.getContentPane().add(tvp, BorderLayout.CENTER);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setVisible(true);
	}
 
}
