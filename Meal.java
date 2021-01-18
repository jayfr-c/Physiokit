import java.util.HashMap;

public class Meal { 

	private HashMap<String, Double> contents; 
	private Double mealCode;
	private String name; 
	private Integer categoryNumber; 
	private String description;

	public Meal(Double mealCode, String name, Integer categoryNumber, 
		String description, HashMap<String, Double> contents) {
		this.mealCode = mealCode;
		this.name = name;  
		this.categoryNumber = categoryNumber; 
		this.contents = contents; 
		this.description = description;
	}


	public boolean editContent(String key, Double value) {
		//test editing
		if (this.contents.containsKey(key)) {
			this.contents.remove(key);
			this.contents.put(key, value);
			return true;
		} 
		return false; 
	}
	public Double getCode() {
		return this.mealCode;
	}
	public String getName() {
		return this.name; 
	} 
	public Integer getCategoryNum() {
		return this.categoryNumber;
	} 
	public String getDescription() {
		return this.description;
	}
	
	public HashMap<String, Double> getContents() {
		return this.contents;
	}

	public void printName() {
		System.out.println("name: "+name);
	}

	public void printContent() {
		//change
		System.out.println(contents);
	} 

}