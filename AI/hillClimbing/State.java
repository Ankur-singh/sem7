package hillClimbing;
import java.util.*;


public class State {
	 List<Stack<String>> state;
	 int heuristics;
	 
	 public State(Stack<String> s) {
		 this.state = new ArrayList<Stack<String>>();
		 this.state.add(s);
	 }
	 
	 
	 State(List<Stack<String>> state, int heuristics){
		 this.state = state;
		 this.heuristics = heuristics;
	 }
	 
	 State(State s){
		this.state = s.state;
		this.heuristics = s.heuristics;
	 }

	
}
