import java.io.*;
import java.util.Scanner;

public class trail {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int roll = sc.nextInt();
		String res = binary(roll);
		System.out.println(res);
		System.out.println(count(res));
		
	}
	
	public static String binary(int n){
		int q = 0;
		String result = "";
		while(n > 1){
			q = n%2;
			n = n/2;
			result = Integer.toString(q) + result;
		}
		if (n == 1){ result = "1"+result;}
		else{ result = "0"+result;}
		return result;
	}
	
	public static int count(String s){
		String flag = "0";
		int i = 0, count = 0;
		for(i=s.length()-1; i >= 0; i--){
			if(s.charAt(i) == '0'){
				count++;
				flag = "0";

			}
			if(s.charAt(i)=='1' && flag == "0"){
				flag = "1";
				//count = 0;
			}
		}
		return count;
	
	}
}
