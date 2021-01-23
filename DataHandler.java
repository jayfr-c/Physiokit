import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List; 
import java.util.logging.Level;
import java.util.logging.Logger;

import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Normalize;
import weka.core.Debug;
import weka.core.converters.ConverterUtils.DataSource; 

public class DataHandler {

	public static List<String> getSymptoms() throws IOException {
		return DataExtractor.fileToList(DataPaths.SYMPTOMS_LIST);
	}	 
    public static String openInfo(String filename) throws IOException {
        return DataExtractor.fileToString(DataPaths.INFOPATH + filename + "/definition.txt");    
    }
    public static String openTreatment(String filename) throws IOException {
        return DataExtractor.fileToString(DataPaths.INFOPATH + filename + "/Treatment.txt");
    }
    public static String openDiagnosis(String filename) throws IOException { 
        return DataExtractor.fileToString(DataPaths.INFOPATH + filename + "/Diagnosis.txt");
    }
    public static String openCauses(String filename) throws IOException { 
        return DataExtractor.fileToString(DataPaths.INFOPATH + filename + "/Causes.txt");
    }
    public static String openReferences(String filename) throws IOException { 
        return DataExtractor.fileToString(DataPaths.INFOPATH + filename + "/References.txt");
    }

    public static Instances loadDataset() {
        Instances dataset = null;
        try {
            dataset = DataSource.read(DataPaths.DATASETPATH);
            if (dataset.classIndex() == -1) {
                dataset.setClassIndex(dataset.numAttributes() - 1);
            }
        } catch (Exception e) {
            Logger.getLogger(ModelGenerator.class.getName()).log(Level.SEVERE, null, e);
        }
        return dataset;
    }

    public static Instances normalizeData(Instances dataset) throws Exception {
        Filter filter = new Normalize();
        dataset.randomize(new Debug.Random(1)); 
        filter.setInputFormat(dataset);
        return Filter.useFilter(dataset, filter);
    } 
 
    public static void writeLines(String filePath, List<String> lines) throws IOException {
        Path path = Paths.get(filePath);
        Files.write(path, lines, StandardOpenOption.WRITE);
    }
}