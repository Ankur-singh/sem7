import java.util.*;

class AstarTools{

	public class node{
		ArrayList<Integer> path = new ArrayList<Integer>();
		int cost, pathLength;	

		node(ArrayList<Integer> path, int cost, int pathLength){
			this.path = path; 
			this.cost = cost;
			this.pathLength = pathLength;
		}

		public String toString(){
			int i;
			StringBuilder result = new StringBuilder();
			for(i = 0; i < this.path.size(); i++)
				result.append(this.path.get(i).toString());

			return result+" cost: "+this.cost+" path length: "+this.pathLength;
		}

		public Boolean goalState(int[] h){
			//node min = getMin();	
			if(h[this.path.get(this.path.size() -1)] == 0){
				return true;
			}
			else
				return false;
		}
		
	}

	public class Visited{
		ArrayList<node> visited = new ArrayList<node>();
		
		public Boolean add(node n){
			node temp;
			int i;
			System.out.println(visited.size());
			if(visited.size() == 0){
				visited.add(n);
				return true;
			}
			//Iterator itr = visited.iterator();
			for(i= 0; i < visited.size(); i++){
				//temp = itr.next();
				System.out.println(n.path.size());
				if(n.path.get(n.path.size() - 1) == visited.get(i).path.get(visited.get(i).path.size() - 1)){
					if(n.cost < visited.get(i).cost){    //node cost < already visited cost.
						visited.set(i,n);
						return true;
					}
				}else{
					visited.add(n);
					return true;
				}

			}
			return false;
		}

		public String print(){
			Iterator itr = visited.iterator();
			node temp;
			//string result;
			StringBuilder sb = new StringBuilder();
			while(itr.hasNext()){
				temp =(node) itr.next();
				sb.append(temp.toString());
				//System.out.println(temp.print());				
			}
			return sb.toString();
		}	


	}

	public class Generated{
		ArrayList<node> generated = new ArrayList<node>();
		
		public void add(node n){
			generated.add(n);
		}

		public node getMin(){
			node min, temp;
			Iterator itr = generated.iterator();
			min = (node) itr.next();
			while(itr.hasNext()){
				temp = (node) itr.next();
				if(min.cost > (temp.cost)){
					min = temp;
				}

			}
				
			return min;
		}

		
		public String print(){
			Iterator itr = generated.iterator();
			node temp;
			//string result;
			StringBuilder sb = new StringBuilder();
			while(itr.hasNext()){
				temp =(node) itr.next();
				sb.append(temp.toString()+"\t");
				//System.out.println(temp.print());				
			}
			return sb.toString();
		}	

	}

}
