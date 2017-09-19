import java.util.*;


public class dfs {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		System.out.println(n);
		int[][] graph = input(n);
		print(graph);
	//	static int[] visited = new int[n]; 
		


	}

	public static int[][] input(int n){
		Scanner s = new Scanner(System.in);
		int[][] graph = new int[n][n];	
		int i,j;

		for(i=0;i<n; i++){
			for(j=0;j<n; j++)
				graph[i][j] = s.nextInt();
		}

		return graph;
	}

	public static void print(int[][] g){
		int i,j;
		for(i=0;i<g.length; i++){
			for(j=0;j<g.length; j++)
				System.out.print(g[i][j]);
			System.out.println();
		}
	}

}
