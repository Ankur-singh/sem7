package hillClimbing;

import java.util.*;

public class Hill {
	
	public static void main(String args[]) {
		Stack<String> stack = new Stack<String>();
		//Stack<String> stack2 = new Stack<String>();
		//Stack<String> stack3 = new Stack<String>();
		Stack<String> goal = new Stack<String>();
		List<Stack<String>> list = new ArrayList<Stack<String>>();
		stack.push("B");
		stack.push("C");
		stack.push("D");
		stack.push("A");
		
		//stack2.push("A");
		//stack2.push("D");
		
		list.add(stack);
		//list.add(stack2);
		//list.add(stack3);
		
		goal.push("A");
		goal.push("B");
		goal.push("C");
		goal.push("D");
		
		System.out.println(list);
		//System.out.println(getStateHeuristic(list, goal));
		int count = 0;
		while(count <= 5) {
			list = findNextState(list, goal);
			System.out.println("find "+list);
			count++;
		}
	}
	
	public static int getStateHeuristic(List<Stack<String>> currentState, Stack<String> goal) {
		int stateHeuristic = 0;
		for(Stack<String> s: currentState) {
			stateHeuristic = stateHeuristic + getStackHeuristic(s, goal);	
		}
		
		return stateHeuristic;
		
	}
	
	public static int getStackHeuristic(Stack<String> stack, Stack<String> goal) {
		int stackHeuristic = 0;
		for(String block : stack ) {
			//System.out.println(block);
			//System.out.println((stack.size()-stack.search(block))+" "+(goal.size()-goal.search(block)) );
			if(stack.size()-stack.search(block) == goal.size()-goal.search(block)) {   //block in stack and goal stack have same index
				stackHeuristic += 1;
			}
			else {
				stackHeuristic -= 1;
			}
		}
		//System.out.println(stackHeuristic);
		return stackHeuristic;
	}
	
	public static List<Stack<String>> findNextState(List<Stack<String>> currentState, Stack<String> goal) {
		int currentHeuristic = getStateHeuristic(currentState, goal);
		int updated = 0;
		for(int i = 0; i  < currentState.size(); i++) {
			updated = pushIntoNewStack(currentState, currentHeuristic,currentState.get(i), goal);
			System.out.println("newStack "+ currentState);
			if(updated == 1) {
				break;
			}
			else {
				updated = pushIntoExistStack(currentState, currentHeuristic,currentState.get(i), goal);
				System.out.println("existStack "+ currentState);
			}
			if(updated == 1)
				break;
			
		}
		
		return currentState;
	}
	
	public static int pushIntoNewStack(List<Stack<String>> currentState, int currentHeuristic, Stack<String> s, Stack<String> goal) {
		int updated = 0;
		
		if(s.size() <= 1) {
			//s.push(block);
			return updated;
		}
		String block = s.pop();
		//System.out.println(block);
		Stack<String> newStack = new Stack<String>();
		newStack.push(block);
		currentState.add(newStack);
		//System.out.println(currentState);
		System.out.println(getStateHeuristic(currentState, goal));
		if(getStateHeuristic(currentState,goal) >= currentHeuristic) {
			//System.out.println("updated");
			updated = 1;
		}
		else {
			//System.out.println("inside else");
			currentState.remove(newStack);
			s.push(block);
		}
		//System.out.println(s);
		//System.out.println(currentState);
		return updated;
	}
	
	public static int pushIntoExistStack(List<Stack<String>> currentState, int currentHeuristic, Stack<String> s, Stack<String> goal) {
		int updated= 0;
		
		if(s.size() == 0) {
			currentState.remove(s);
			return updated;
		}
		String block = s.pop();
		for(Stack<String> s2 : currentState) {
			if(s2 != s) {
				s2.push(block);
				//currentState.add(s2);
				System.out.println(getStateHeuristic(currentState, goal));
				if(getStateHeuristic(currentState,goal) > currentHeuristic) {
					updated = 1;
					break;
				}
				else {
					s2.pop();
				}
			}
		}
		if(updated == 0)
			s.push(block);
		else if(s.size() == 0) {
			currentState.remove(s);
		}
			
		
		return updated;
	}
	
}
