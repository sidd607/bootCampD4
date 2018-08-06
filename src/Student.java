import java.util.List;

public class Student {

	String name;
	List<Integer> marks;
	Integer index;
	
	
	public Student(String inputName, Integer inputIndex) {
		// TODO Auto-generated constructor stub
		name = inputName;
		index = inputIndex;
	}
	
	public void addMarks (List<Integer> inputMarks) {
		marks = inputMarks;
	}
	
	public String getName() {
		return this.name;
	}
	
	public List<Integer> getMarks() {
		return this.marks;
	}
	
	public Integer getIndex() {
		return index;
	}
	
	

}
