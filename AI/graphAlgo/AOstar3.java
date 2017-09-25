
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class AOstar3 {
    static Stack st = new Stack();
    
    static class Node{
        int vertex;
        int minWork;
	int vertex2 = -1; 
        
        Node(int v, int w){
            this.minWork = w;
            this.vertex = v;
        }
        
        
    }
    
    public static void main(String[] args) {
        int[][] g = {{0,22,21,0,0,0,0,0,0,0,0},
                                 {0,0,0,12,11,0,0,0,0,0,0},
                                 {0,0,0,0,0,0,0,0,0,10,10},
                                 {0,0,0,0,0,21,20,0,0,0,0},
                                 {0,0,0,0,0,0,0,20,20,0,0},
                                 {0,0,0,0,0,0,0,0,0,0,0},
                                 {0,0,0,0,0,0,0,0,0,0,0},
                                 {0,0,0,0,0,0,0,0,0,0,0},
                                 {0,0,0,0,0,0,0,0,0,0,0},
                                 {0,0,0,0,0,0,0,0,0,0,0},
                                 {0,0,0,0,0,0,0,0,0,0,0}
                                }; 
        int[] h = {7,4,3,2,5,3,10,3,4,9,7};
        Node root = new Node(0,h[0]);
        System.out.println(ao(root,g,h));
        //System.out.println(st);
	filter(st, g);
        //System.out.println(min(options));
    }

    public static void filter(Stack st, int[][] g){
	int i = 0, j, temp;
	Stack second = new Stack();
	ArrayList<Integer> visited = new ArrayList<Integer>();
	ArrayList<Integer> generated = new ArrayList<Integer>();

	visited.add(0);
	temp = 0;
	while(true){
		//System.out.println("inside while");
		for(j = 0; j< g.length; j++){
			//System.out.println("inside for");
			if(g[temp][j] != 0 && st.search(j) != -1){
				generated.add(j);
			}
		}
		if(!generated.isEmpty()){
			//System.out.println("generated: "+generated);
			//System.out.println("visited: "+visited);
			temp = generated.get(0);
			generated.remove(0);
			visited.add(temp);
		}
		else{
			System.out.println(visited.toString());
			return;
		}
	}

    }
    
    
    public static int ao(Node parent, int[][] g, int[] h){
        System.out.println("parent: "+ parent.vertex+"\n");
        if(!morePathPossible(parent,g)){
            System.out.println("minimum work: "+parent.minWork);
            st.push(parent.vertex);
            return parent.minWork;
        }
        
        ArrayList<Integer> children = new ArrayList<Integer>();
	ArrayList<Node> childrenNode = new ArrayList<Node>();
        int i, hax = 0, hx;
        Node child, andChild = null;
        for(i=0; i < g.length; i++){
	    child = new Node(i,h[i]);
            if(g[parent.vertex][i] >= 10){
                if(g[parent.vertex][i] >= 20){
		    if(andChild == null){
			andChild = new Node(i, h[i]);
		    }else{
			andChild.vertex2 = i;
		    }
                    System.out.println("andNode: "+g[parent.vertex][i]%10+" ao("+i+")");
                    hax = hax + (g[parent.vertex][i]%10) + ao(child,g, h);
                }
                else{
                    System.out.println("node: "+g[parent.vertex][i]%10+" ao("+i+")");
                    hx = (g[parent.vertex][i]%10) + ao(child,g, h);
                    children.add(hx);                    
		    childrenNode.add(child);
                }
            }  
        }
        //System.out.println("node: "+g[parent.vertex][i]%10+" ao("+i+")");
        if(hax != 0){
            children.add(hax);
	    childrenNode.add(andChild);
	}
        //printchildren(children);
	i = min(children);
        parent.minWork = children.get(i);
	if(st.search(childrenNode.get(i).vertex) == -1){
		st.push(childrenNode.get(i).vertex);
		if(childrenNode.get(i).vertex2 != -1 && st.search(childrenNode.get(i).vertex2) == -1){
			st.push(childrenNode.get(i).vertex2);
		}
	}
        //h[parent.vertex] = parent.minWork;
        System.out.println("minimum work: "+parent.minWork);
        return parent.minWork;
    
    }
    
    public static void printchildren(ArrayList<Integer> children){
        Iterator itr = children.iterator();
        while(itr.hasNext()){
            System.out.print(itr.next());
        }
        System.out.println();
    }
    
    public static Boolean morePathPossible(Node n, int[][] g){
        int i;
        for(i = 0; i < g.length; i++){
            if(g[n.vertex][i] >= 10){
                return true;
            }
        }
        return false;
    }
    
    public static int min(ArrayList<Integer> options){
        int minimum = 0;
        //minimum = options.get(0);
        int i;
        for(i = 0; i < options.size(); i++){
            if(options.get(minimum) > options.get(i))
                minimum = i;
        
        }
        //st.push(minimum.vertex);
        return minimum;
    }
    
}
