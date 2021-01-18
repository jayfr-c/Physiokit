import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*; 

public class AppOptions { 
	public static List<String> getDetailsOptions() {
		ArrayList<String> detailsOptions = new ArrayList<>();
        detailsOptions.add("Treatment");
        detailsOptions.add("Diagnosis");
        detailsOptions.add("Causes");
        detailsOptions.add("References");
        return detailsOptions;
	}
	public static List<String> getGenderOptions() {
		ArrayList<String> options = new ArrayList<>();
        options.add("Male");
        options.add("Female");
        return options;
	}
	public static List<String> getModelOptions() {
		ArrayList<String> options = new ArrayList<>();
        options.add("Multilayer Perceptron");
        options.add("Decision Tree");
        options.add("SMO");
        options.add("Logistic");
        return options;
	}
	public static Icon getIcon(String name) {
		return new ImageIcon("res/icons/"+ name +".png"); 
	}
	public static String boxContent(String filename) throws IOException{
		return DataHandler.openInfo(filename);
	}
	public static String boxContent(JComboBox furtherDetails, String filename) throws IOException {
		if (furtherDetails.getSelectedItem().equals("Treatment")) {
			return DataHandler.openTreatment(filename);
		}
		if (furtherDetails.getSelectedItem().equals("Diagnosis")) {
			return DataHandler.openDiagnosis(filename);
		}
		if (furtherDetails.getSelectedItem().equals("Causes")) {
			return DataHandler.openCauses(filename);
		}
		if (furtherDetails.getSelectedItem().equals("References")) {
			return DataHandler.openReferences(filename);
		}
		return " ";
	}
}