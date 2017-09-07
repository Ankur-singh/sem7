public class matrix_operations{

	public static int[][] inverse(int[][] a){
		int[][] ct = transpose(cofactor_matrix(a));
		int det = det(a);
		int i , j;
		for(i = 0; i < a.length; i++){
			for(j = 0; j <a.length; j++){
				ct[i][j] = (int)ct[i][j]/det;
			}
		}
		return ct;
	}

	public static int[][] mod_inverse(int[][] a){
		int[][] ct = transpose(cofactor_matrix(a));
		int det = det(a);
		int i , j;
		for(i = 0; i < a.length; i++){
			for(j = 0; j <a.length; j++){
				ct[i][j] = ((int)ct[i][j]/det)%26;
			}
		}
		return ct;
	}

	public static int[][] cofactor(int[][] a, int i, int j){	
		int r, p, x = 0, y = 0;
		int[][] q = new int[2][2];
		for(r = 0; r < a.length; r++){
			if(r != i){
				for(p = 0; p < a.length; p++){
					if(p != j){
						q[x][y++] = a[r][p];
						if(y == 2){
							x++;
							y = 0;
						}
						if(x==2)
							break;
					}
				}
			}
		}
		return q;
	}


	public static int det2(int[][] a){
		int res = a[0][0]*a[1][1] - a[0][1]*a[1][0];
		return res;
	}
	
	public static int[][] cofactor_matrix(int[][] a){
		int[][] c = new int[3][3];
		int i, j;
		for(i = 0; i < a.length; i++){
			for(j = 0; j < a[0].length; j++){
				c[i][j] = ((int)Math.pow(-1,i+j))*det2(cofactor(a, i, j));
			}
		}
		return c;
	}

	public static int[][] transpose(int[][] a){
		int[][] t = new int[3][3];
		int i, j;
		for(i = 0; i < a.length; i++){
			for(j = 0; j< a[0].length; j++){
				t[j][i] = a[i][j];
			}
		}
		return t;
	}

	public static int det(int[][] a){
		int i ,j , det = 0;
		for(i = 0; i < 1; i++){
			for(j = 0; j < a.length; j++){
				det  = det + ((int)Math.pow(-1, i+j))*a[i][j]*det2(cofactor(a, i, j));
			
			}
		}	
		return det;
	}

	public static void print(int[][] a){
		int i, j;
		for(i = 0; i < a.length; i++){
			for(j = 0; j < a.length; j++)
				System.out.print(a[i][j]);
			System.out.println("\n");
		}
	}


}
