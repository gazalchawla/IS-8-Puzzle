package eightPuzzleFinal;

import java.util.Arrays;

public class Function {
	
	//to check if some node already exists
	public static boolean areEqual(int[][]a1, int[][]a2)
	{
		if(a1==null)
		{
			return (a2==null);
		}
		if(a2==null)
		{
			return false;
		}
		
		for(int i=0;i<a1.length;i++)
		{
			if(!Arrays.equals(a1[i], a2[i]))
			{
				return false;
			}
		}
		return true;
	}
	
	
	public static void returnConfiguration(int[][] a)
	{
		System.out.println("Solution Path:\n");
		for(int row=0;row<3;row++)
		{
			for(int col=0;col<3;col++)
			{
				System.out.print("\t"+a[row][col]);
			}
			System.out.println();
		}
	}
	
	
	//function to swap two elements of a matrix
	// swaps a[x][y] and a[xNew][yNew]
	public static int[][] swapElements(int[][] arr, int x, int y, int xNew, int yNew)
	{
		int temp = arr[x][y];
		arr[x][y] = arr[xNew][yNew];
		arr[xNew][yNew] = temp;
		return arr;
	}
	
	
	//function to copy elements of one matrix into another
	public static int[][] copyMatrix(int[][] current)
	{
		int[][] temp = new int[3][3];
		for(int row=0;row<3;row++)
		{
			for(int col=0;col<3;col++)
			{
				temp[row][col] = current[row][col];
			}
		}
		return temp;
	}
	
	
	
}
