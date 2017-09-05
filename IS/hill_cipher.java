class hill_cipher{
	public static void main(String[] args){
		int[][] a = new int[3][3];
		int[][] b = new int[3][1];
		int count = 1;
		for(int i = 0; i<3; i++){
			for(int j=0; j<3; j++){
				a[i][j] = count;
				count++;
			}
		}
	//	b= {{1},{1},{1}};
		b[0][0] = 1;
		b[1][0] = 1;
		b[2][0] = 1;
		display(a);
		display(b);
		display(mult(a, b));
		System.out.println(a.length);
		System.out.println(a[0].length);
		
		
	}

	public static void display(int[][] a){
		for(int i = 0; i < a.length; i++){
			for(int j=0; j < a[0].length; j++)	
				System.out.print(a[i][j]);
		System.out.println();
		}
	}

	public static int[][] mult(int[][] a, int[][] b){
		if(a[0].length == b.length){
			int sum = 0;
			int[][] c = new int[a.length][b[0].length];
			for(int i=0; i < a.length; i++){	
				for(int j=0; j < b[0].length; j++){
					for(int k=0; k < a[0].length; k++){
					c[i][j] = c[i][j] + a[i][k]*b[k][j];
					}			
				}
			}
			return c;
		}	
		else
			return null;

	}
}
