import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataParserCSV {
	
	String[] header = new String[0];

	public Meal parseFrom(String data) {
		
		String[] columns = data.split(",");
		Double mealCode = Double.parseDouble(columns[0]);
		String name = columns[1];
		Integer categoryNumber = Integer.parseInt(columns[2]); 
		String description = columns[3];

		return new Meal (mealCode, name, categoryNumber,
					  description, compressMealContents(columns));
	}
 
	public List<Meal> parseLines(List<String> lines) {
		
		ArrayList<Meal> foodList = new ArrayList<>();
		header = lines.get(0).split(",");
		lines.remove(0);
		for (String line : lines) {
			
			foodList.add(parseFrom(line));
		
		}

		return foodList;
	}

	private HashMap<String, Double> compressMealContents(String[] columns) {
		/*nutritional values index start at 4 up to the last column*/
		HashMap<String, Double> contents = new HashMap<>();
		for (int i = 4; i < columns.length; i++) {
			contents.put(header[i], Double.parseDouble(columns[i]));
		}
		return contents;
	}
}