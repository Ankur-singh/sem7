/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;

/**
 *
 * @author ankur
 */
public class Astar {
    
    	public static void main(String[] args){
		int[][] graph = {{0,5,9,0,6,0,0,0,0,0},
                                 {0,0,3,0,0,0,0,9,0,0},
                                 {0,2,0,1,0,0,0,0,0,0},
                                 {6,0,0,0,0,0,7,0,5,0},
                                 {1,0,0,2,0,2,0,0,0,0},
                                 {0,0,0,0,0,0,0,0,0,7},
                                 {0,0,0,0,2,0,0,0,0,8},
                                 {0,0,0,0,0,0,0,0,0,0},
                                 {0,0,0,0,0,0,0,0,0,0},
                                 {0,0,0,0,0,0,0,0,0,0}
                                }; 
                //{{0,2,0,2,0,0},{0,0,2,0,0,0},{0,0,0,0,0,4},{0,0,0,0,3,0},{0,0,0,0,0,2},{0,0,0,0,0,0}};
		//int[][] graph = {{0,1,2},{1,0,1},{2,1,0}};
		int[] h = {5,7,3,4,6,5,6,0,0,0};//{5,4,4,4,2,0};//{5, 2, 0};
		int gx, hx;
		AstarTools at = new AstarTools();
		ArrayList<Integer> path = new ArrayList<Integer>();
		AstarTools.Visited v = at.new Visited();
		AstarTools.Generated g = at.new Generated();
		

		path.add(0);
		gx = 0;
		hx = h[0];
		
		AstarTools.node start = at.new node(path, hx, gx);	
		AstarTools.node temp = start;
		v.add(start);
		int i, count=0;
		
		//System.out.println(g.print());
		while(true){
				
			for(i = 0; i < graph.length; i++){
				//System.out.println(i+" outside if "+temp.path.get(temp.path.size()-1));
				if(graph[temp.path.get(temp.path.size()-1)][i] != 0){
					gx = temp.pathLength + graph[temp.path.get(temp.path.size()-1)][i];
					hx = gx + h[i];		
					path = new ArrayList<Integer>(temp.path);
					path.add(i);
					//System.out.println(i+" last node: "+temp.path.get(temp.path.size()-1)+" "+ i);
					//System.out.println(i+" matrix value: "+graph[temp.path.get(temp.path.size()-1)][i]);
					//System.out.println(gx);
					AstarTools.node child = at.new node(path, hx, gx);
					g.add(child);	
					
				}
                                

			}
                        System.out.println("generated\n"+g.print());
			//System.out.println(g.print());
			System.out.println();
			temp = at.new node(g.getMin());
			if(temp.goalState(h)){
				System.out.println("goal reached");
				System.out.println(temp.toString());
				return ;
			}
                        g.remove(g.getMin());
                        System.out.println("temp "+temp);
                        System.out.println();
                        System.out.println("generated (After removing temp)\n"+g.print());
                        System.out.println();
                        v.add(temp);
		}

		//System.out.println(v.add(start));
		//System.out.println(v.print());
		//start = at.new node(path,6 );
		//System.out.println(v.add(start));
		//System.out.println(v.print());
	}
    
}
