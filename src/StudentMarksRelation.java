import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Vector;

/**
 * 
 */

/**
 * @author sipadhi
 *
 */
public class StudentMarksRelation {

	/**
	 * 
	 */
	
	List<Student> students;
	Map<Integer, List<Integer>> studentGraph = new HashMap<Integer, List<Integer>>();
	
	
	public StudentMarksRelation(List<Student> inputStudents) {
		this.students = inputStudents;
		inputStudents.forEach(value->{
			this.studentGraph.put(value.getIndex(), new ArrayList<Integer>());
		});
	}
	
	private boolean isAllGreater(Student a, Student b) {
		boolean ans = true;
		for (int i=0; i<a.getMarks().size(); i++){
			ans = ans && (a.getMarks().get(i) > b.getMarks().get(i));
		}
		return ans;
	}
	
	private boolean isAllSmaller(Student a, Student b) {
		boolean ans = true;
		for (int i=0; i<a.getMarks().size(); i++){
			ans = ans && (a.getMarks().get(i) < b.getMarks().get(i));
		}
		return ans;
	}
	
	
	public int isComparable (Student a, Student b) {
		if (a.getMarks().size() == b.getMarks().size() && a.getMarks().size()!= 0) {
			if (isAllGreater(a, b)) {
				return -1;
			} else if(isAllSmaller(a, b)){
				return 1;
			} else
				return 0;
			
		}
		return 0;
	}
	
	public void addToGraph (Student a, Student b) {
		if (this.studentGraph.containsKey(a.index) && !this.studentGraph.get(a.index).contains(b.index)) {
			this.studentGraph.get(a.index).add(b.index);
		} else {
			this.studentGraph.put(a.index, new ArrayList<Integer>(Arrays.asList(b.index)));
		}
		
	}
	
	public void createStudentGraph() {
		for(int i=0;i<students.size();i++){
			for(int j=i+1;j<students.size();j++){
				
				System.out.println(students.get(i).getName() + " | " + students.get(j).getName() + " | " + isComparable(students.get(i), students.get(j)));
				if(isComparable(students.get(i), students.get(j))== 1){
					addToGraph(students.get(i), students.get(j));
				}else if(isComparable(students.get(i), students.get(j))==-1){
					addToGraph(students.get(j), students.get(i));
				}
			}
		}
		
		this.studentGraph.forEach((key, val)->{
			System.out.println(key + "--->" + val.size());
			val.forEach(value->System.out.println(value));
		});
	}
	public void topologicalSort()
    {
		int V = studentGraph.keySet().size();
		System.out.println(V);
        int indegree[] = new int[V];
        for(int i = 0; i < V; i++)
        {
            ArrayList<Integer> temp = (ArrayList<Integer>) studentGraph.get(i);
            for(int node : temp)
                indegree[node]++;
        }
         
        Queue<Integer> q = new LinkedList<Integer>();
        for(int i = 0;i < V; i++)
            if(indegree[i]==0)
                q.add(i);
        Vector<Integer> topOrder=new Vector<Integer>();
        while(!q.isEmpty()){
            int u=q.poll();
            topOrder.add(u);

            for(int node : studentGraph.get(u))
                if(--indegree[node] == 0)
                    q.add(node);
        }   
        for(int i : topOrder)
            System.out.print(i+" ");
    }

	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Student> students = new ArrayList<>();
		Student tmpStudent;

		tmpStudent = new Student("A", 0);
		tmpStudent.addMarks(Arrays.asList(12, 14, 16));
		students.add(tmpStudent);
		
		tmpStudent = new Student("B", 1);
		tmpStudent.addMarks(Arrays.asList(5, 6, 7));
		students.add(tmpStudent);
		
		tmpStudent = new Student("C", 2);
		tmpStudent.addMarks(Arrays.asList(17, 20, 23));
		students.add(tmpStudent);
		
		tmpStudent = new Student("D", 3);
		tmpStudent.addMarks(Arrays.asList(2, 40, 12));
		students.add(tmpStudent);
		
		tmpStudent = new Student("E", 4);
		tmpStudent.addMarks(Arrays.asList(3, 41, 13));
		students.add(tmpStudent);
		
		tmpStudent = new Student("F", 5);
		tmpStudent.addMarks(Arrays.asList(7, 8, 9));
		students.add(tmpStudent);
		
		tmpStudent = new Student("G", 6);
		tmpStudent.addMarks(Arrays.asList(4, 5, 6));
		students.add(tmpStudent);
		
		StudentMarksRelation studentMarksRelation = new StudentMarksRelation(students);
		studentMarksRelation.createStudentGraph();
		
		studentMarksRelation.topologicalSort();
		
		
		
		
		
		
	}

}
