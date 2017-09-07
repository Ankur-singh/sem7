import java.util.*;
import java.io.*;

public class hill{
	static String alpha = "abcdefghijklmnopqrstuvwxyz";
	static int[][] key = {{1,2,3},{0,1,4},{5,6,0}};
	public static void main(String[] args){
		String cipher = encry("ankursingham");	
		System.out.println(cipher);
		System.out.println(decry(cipher));
		
	}

	public static String encry(String s){
		ArrayList<String> tokens = token(s);
		System.out.println(tokens);
		
		ListIterator<String> itr = tokens.listIterator();
		ArrayList<String> return_tokens = new ArrayList<String>(); 
		int[][] c = new int[3][1];
		//int[][] a = {{1,0,0},{0,1,0},{0,0,1}};
		while(itr.hasNext()){
			c = create_matrix(itr.next());
			c = mult(key, c);
			return_tokens.add(create_string(c));
		}
		System.out.println(return_tokens);
		String encrypted_string = join(return_tokens);
		return encrypted_string;
	}
	
	public static String decry(String s){
		ArrayList<String> tokens = token(s);
		System.out.println(tokens);
		
		ListIterator<String> itr = tokens.listIterator();
		ArrayList<String> return_tokens = new ArrayList<String>(); 
		int[][] c = new int[3][1];
		int[][] inverse_key = matrix_operations.mod_inverse(key);
		while(itr.hasNext()){
			c = create_matrix(itr.next());
			c = mult(inverse_key, c);
			return_tokens.add(create_string(c));
		}
		System.out.println(return_tokens);
		String encrypted_string = join(return_tokens);
		return encrypted_string;
	}

	public static ArrayList<String> token(String s){
		ArrayList<String> list = new ArrayList<String>();
		int i;
		for(i = 0; i < s.length(); i = i+3){
			list.add(s.substring(i,i+3));
		}
		return list;

	}
	
	public static String join(ArrayList<String> l){
		ListIterator<String> itr = l.listIterator();
		String s = new String();
		while(itr.hasNext()){
			s = s + itr.next();
		}
		return s;
	}

	public static int[][] create_matrix(String str){
		int[][] m = new int[3][1];
		int i;
		char c;
		//System.out.println(str);
		for(i = 0; i < str.length(); i++){
			c = str.charAt(i);
			m[i][0] = alpha.indexOf(c);	 	
		}
		return m;
	}

	public static String create_string(int[][] m){
		int i;
		char c;
		String s = "";
		for(i = 0; i < m.length; i++){
			c = alpha.charAt(m[i][0]);
			s = s+c;
		}
		return s;
	}

	public static int[][] mult(int[][] a, int[][] b){
                if (a[0].length == b.length){
			int[][] c = new int[a.length][b[0].length]; 
                        int i, j, k, temp;
                        for(i = 0; i < a.length; i++){
                                for(j = 0; j < b[0].length; j++){
                                        for(k = 0; k < a[0].length; k++){
						c[i][j] = c[i][j] + a[i][k]*b[k][j];
                                        }
					while(c[i][j] < 0){
						c[i][j] = 26+c[i][j];
					}
					c[i][j] = c[i][j] % 26;
                                }
                        }
			return c;
                }
		return null;
        }
        
        public static void print_matrix(int[][] a){
                int i, j;
                for(i = 0; i < a.length; i++){
                        for(j = 0; j < a[0].length; j++){
                                System.out.println(a[i][j]);
                        }
                }


        }

///***c++ code***///
/*
	static public void getCofactor(int A[][], int temp[][], int p, int q)
	{
	    int i = 0, j = 0;
	 	int row, col;
	    // Looping for each element of the matrix
	    for (int row = 0; row < a.length; row++)
	    {
		for (int col = 0; col < a.length; col++)
		{
		    //  Copying into temporary matrix only those element
		    //  which are not in given row and column
		    if (row != p && col != q)
		    {
			temp[i][j++] = A[row][col];
	 
			// Row is filled, so increase row index and
			// reset col index
			if (j == n - 1)
			{
			    j = 0;
			    i++;
			}
		    }
		}
	    }
	}
	 
	// Recursive function for finding determinant of matrix.
	//   n is current dimension of A[][].
	public static int determinant(int A[][])
	{
	    int D = 0; // Initialize result
	 
	    //  Base case : if matrix contains single element
	    if (A.length == 1)
		return A[0][0];
	 
	    int[][] temp = new int[a.length][a[0].length]; // To store cofactors
	 
	    int sign = 1;  // To store sign multiplier
	 
	     // Iterate for each element of first row
	    for (int f = 0; f < A.length; f++)
	    {
		// Getting Cofactor of A[0][f]
		getCofactor(A, temp, 0, f);
		D =D + sign * A[0][f] * determinant(temp);
	 
		// terms are to be added with alternate sign
		sign = -sign;
	    }
	 
	    return D;
	}
	 
	// Function to get adjoint of A[N][N] in adj[N][N].
	void adjoint(int A[][],int adj[][])
	{
	    if (A.length == 1)
	    {
		adj[0][0] = 1;
		return;
	    }
	 
	    // temp is used to store cofactors of A[][]
	    int sign = 1;
		int[][] temp = new int[A.length][A.length];
	 
	    for (int i=0; i<A.length; i++)
	    {
		for (int j=0; j<A.length; j++)
		{
		    // Get cofactor of A[i][j]
		    getCofactor(A, temp, i, j);
	 
		    // sign of adj[j][i] positive if sum of row
		    // and column indexes is even.
		    sign = ((i+j)%2==0)? 1: -1;
	 
		    // Interchanging rows and columns to get the
		    // transpose of the cofactor matrix
		    adj[j][i] = (sign)*(determinant(temp));
		}
	    }
	}
	 
	// Function to calculate and store inverse, returns false if
	// matrix is singular
	bool inverse(int A[][], float inverse[][])
	{
	    // Find determinant of A[][]
	    int det = determinant(A);
	    if (det == 0)
	    {
		cout << "Singular matrix, can't find its inverse";
		return false;
	    }
	 
	    // Find adjoint
	    int[][] adj = new int[A.length][A.length];
	    adjoint(A, adj);
	 
	    // Find Inverse using formula "inverse(A) = adj(A)/det(A)"
	    for (int i=0; i<A.length; i++)
		for (int j=0; j<A.length; j++)
		    inverse[i][j] = adj[i][j]/float(det);
	 
	    return true;
	}
*/

}
