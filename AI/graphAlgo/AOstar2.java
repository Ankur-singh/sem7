
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class AOstar2 {
    static Stack st = new Stack();
    
    static class Node{
        int vertex;
        int minWork;
	int vertex2 = null;
        
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
        
        
        //System.out.println(min(options));
    }
    
    
    public static int ao(Node parent, int[][] g, int[] h){
        System.out.println("parent: "+ parent.vertex+"\n");
        if(!morePathPossible(parent,g)){
            System.out.println("minimum work: "+parent.minWork);
            st.push(parent.vertex);
            return parent.minWork;
        }
        
        ArrayList<Node> children = new ArrayList<Node>();
        int i, hax = 0, hx;
        Node child, andChild = new Node();
        for(i=0; i < g.length; i++){
            if(g[parent.vertex][i] >= 10){
                if(g[parent.vertex][i] >= 20){
		    if(andChild.vertex == null){
			andChild.vertex = i;
			andChild.minWork = h[i]+g[parent.vertex][i]%10;
		    }
			else{
				andChild.vertex2 = i;
				andChild.minWork = andChild.minWork + h[i] + g[parent.vertex][i]%10;
				
			}
                    System.out.println("andNode: "+g[parent.vertex][i]%10+" ao("+i+")");
                    //hax = hax + ao(child,g, h);
                }
                else{
		    child = new Node(i,h[i]+g[parent.vertex][i]%10);
                    System.out.println("node: "+g[parent.vertex][i]%10+" ao("+i+")");
                    hx = ao(child,g, h);
                    children.add(child);                    
                }
            }  
        }
        //System.out.println("node: "+g[parent.vertex][i]%10+" ao("+i+")");
        if(hax != 0)
	    child = new Node()
            children.add(andChilde);
        //printchildren(children);
        parent.minWork = min(children).minWork;
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
    
    public static int min(ArrayList<Node> options){
        Node minimum;
        minimum = options.get(0);
        int i;
        for(i = 0; i < options.size(); i++){
            if(minimum.minWork > options.get(i).minWork)
                minimum = options.get(i);
        
        }
        st.push(minimum.vertex);
        return minimum;
    }
    
}
