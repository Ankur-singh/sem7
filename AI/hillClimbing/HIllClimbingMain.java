package hillClimbing;

import java.util.*;

public class HIllClimbingMain {
	/*
	public static void main(String args[]) throws Exception {
		Stack<String> initState = new Stack<String>();
		initState.push("B");
		initState.push("C");
		initState.push("D");
		initState.push("A");
		
		Stack<String> goalState = new Stack<String>();
		goalState.push("A");
		goalState.push("B");
		goalState.push("C");
		goalState.push("D");
		System.out.println(initState);
		System.out.println(goalState);
		
		List<State> Result = getRouteWithHillClimbing(initState, goalState);
		
		System.out.println(Result);
		
	}
	*/
	public static List<State> getRouteWithHillClimbing(
			  	Stack<String> initStateStack, Stack<String> goalStateStack) throws Exception {
			    // instantiate initState with initStateStack
		
				State initState = new State(initStateStack);
				
				System.out.println("inside getRouteWithHillClimbing");
				initState.state.add(initStateStack);
			    // ...
				
			    List<State> resultPath = new ArrayList<>();
			    resultPath.add(new State(initState));
			 
			    State currentState = initState;
			    boolean noStateFound = false;
			     System.out.println(getHeuristicsValue(initState.state, goalStateStack));
			     
			    while (
			      !currentState.state.get(0).equals(goalStateStack)
			      || noStateFound) {
			        noStateFound = true;
			        State nextState = findNextState(currentState, goalStateStack);
			        //System.out.println(nextState.state);
			        if (nextState != null) {
			            noStateFound = false;
			            currentState = nextState;
			            System.out.println(nextState.state);
			            resultPath.add(new State(nextState));
			            
			        }
			    }
			    
			   
			    return resultPath;
			}
	

	public static int getHeuristicsValue(
			  List<Stack<String>> currentState, Stack<String> goalStateStack) {
			  
			    Integer heuristicValue;
			    heuristicValue = currentState.stream()
			      .mapToInt(stack -> {
			          return getHeuristicsValueForStack(
			            stack, currentState, goalStateStack);
			    }).sum();
			  
			    return heuristicValue;
			}
	
	public static int getHeuristicsValueForStack(
			  Stack<String> stack,
			  List<Stack<String>> currentState,
			  Stack<String> goalStateStack) {
			 
				System.out.println(stack);
			    int stackHeuristics = 0;
			    boolean isPositioneCorrect = true;
			    int goalStartIndex = 0;
			    for (String currentBlock : stack) {
			        if (isPositioneCorrect 
			          && currentBlock.equals(goalStateStack.get(goalStartIndex))) {
			            stackHeuristics += goalStartIndex;
			        } else {
			            stackHeuristics -= goalStartIndex;
			            isPositioneCorrect = false;
			        }
			        goalStartIndex++;
			    }
			    return stackHeuristics;
			}
	
	public static State pushElementToNewStack(
			  List<Stack<String>> currentStackList,
			  String block,
			  int currentStateHeuristics,
			  Stack<String> goalStateStack) {
			  
			    State newState = null;
			    Stack<String> newStack = new Stack<>();
			    newStack.push(block);
			    currentStackList.add(newStack);
			    
			    int newStateHeuristics 
			      = getHeuristicsValue(currentStackList, goalStateStack);
			    System.out.println(newStateHeuristics+" "+currentStateHeuristics);
			    if (newStateHeuristics > currentStateHeuristics) {
			        newState = new State(currentStackList, newStateHeuristics);
			    } else {
			        currentStackList.remove(newStack);
			    }
			    return newState;
			}
	
	public static State pushElementToExistingStacks(
			  Stack<String> currentStack,
			  List<Stack<String>> currentStackList,
			  String block,
			  int currentStateHeuristics,
			  Stack<String> goalStateStack) {
			  System.out.println("inside pushElementToExistingStacks");
			  State tmp = null;
			  for(Stack s : currentStackList) {
				  tmp = pushElementToStack(s, block, currentStackList,
				            currentStateHeuristics, goalStateStack);
				  if(tmp != null) {
					  return tmp;
				  }
			  }
			  return tmp;
			  
			  /*
			    return currentStackList.stream()
			      .filter(stack -> stack != currentStack)
			      .map(stack -> {
			    	  System.out.println("going to return");
			          return pushElementToStack(
			            stack, block, currentStackList,
			            currentStateHeuristics, goalStateStack);
			        })
			      .filter(Objects::nonNull)
			      .findFirst()
			      .orElse(null);
				*/
			  
			}
			 
			private static State pushElementToStack(
			  Stack<String> stack,
			  String block,
			  List<Stack<String>> currentStackList,
			  int currentStateHeuristics,
			  Stack<String> goalStateStack) {
			 
			    stack.push(block);
			    
			    System.out.println("inside pushElementToStack");
			    
			    int newStateHeuristics 
			      = getHeuristicsValue(currentStackList, goalStateStack);
			    System.out.println("push to new Stack "+newStateHeuristics+" "+currentStateHeuristics);
			    if (newStateHeuristics > currentStateHeuristics) {
			        return new State(currentStackList, newStateHeuristics);
			    }
			    stack.pop();
			    return null;
			}
			
			
			
			public static State findNextState(State currentState, Stack<String> goalStateStack) {
			    List<Stack<String>> listOfStacks = currentState.state;
			    int currentStateHeuristics = currentState.heuristics;
			 
			    
			     return listOfStacks.stream()
			      .map(stack -> {
			          return applyOperationsOnState(
			            listOfStacks, stack, currentStateHeuristics, goalStateStack);
			      })
			      .filter(Objects::nonNull)
			      .findFirst()
			      .orElse(null);
			}
			
			
			public static State applyOperationsOnState(
					  List<Stack<String>> listOfStacks,
					  Stack<String> stack,
					  int currentStateHeuristics,
					  Stack<String> goalStateStack) {
					 
					    State tempState;
					    List<Stack<String>> tempStackList 
					      = new ArrayList<>(listOfStacks);
					    String block = stack.pop();
					    if (stack.size() == 0) {
					      tempStackList.remove(stack);
					    }
					    tempState = pushElementToNewStack(
					      tempStackList, block, currentStateHeuristics, goalStateStack);
					    //System.out.println("push element working!!");
					    if (tempState == null) {
					        tempState = pushElementToExistingStacks(
					          stack, tempStackList, block,
					          currentStateHeuristics, goalStateStack);
					        stack.push(block);
					    }
					    //System.out.println("tempstate result by pushExistingStack "+tempState);
					    return tempState;
					}
}
