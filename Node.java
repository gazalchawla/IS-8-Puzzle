package eightPuzzleFinal;

import java.util.Scanner;

public class Node {
	
	private int nodeState[][] = new int[3][3];		//used to enter configuration at any node
	private int hVal;								//heuristic value
	private int gVal;								//node depth
	private int fVal;								//evaluation function value
	private int nodeRight[][] = new int[3][3];
	private int nodeLeft[][] = new int[3][3];
	private int nodeUp[][] = new int[3][3];
	private int nodeDown[][] = new int[3][3];
	private int zero[][] = {{0,0,0},{0,0,0},{0,0,0}};
	Node parentNode;
	
	
	//getters and setters
	public int[][] getNodeState() {
		return nodeState;
	}
	public void setNodeState(int[][] nodeState) {
		this.nodeState = nodeState;
	}
	public int gethVal() {
		return hVal;
	}
	public void sethVal(int hVal) {
		this.hVal = hVal;
	}
	public int getgVal() {
		return gVal;
	}
	public void setgVal(int gVal) {
		this.gVal = gVal;
	}
	public int getfVal() {
		return fVal;
	}
	public void setfVal(int fVal) {
		this.fVal = fVal;
	}
	public int[][] getNodeRight() {
		return nodeRight;
	}
	public void setNodeRight(int[][] nodeRight) {
		this.nodeRight = nodeRight;
	}
	public int[][] getNodeLeft() {
		return nodeLeft;
	}
	public void setNodeLeft(int[][] nodeLeft) {
		this.nodeLeft = nodeLeft;
	}
	public int[][] getNodeUp() {
		return nodeUp;
	}
	public void setNodeUp(int[][] nodeUp) {
		this.nodeUp = nodeUp;
	}
	public int[][] getNodeDown() {
		return nodeDown;
	}
	public void setNodeDown(int[][] nodeDown) {
		this.nodeDown = nodeDown;
	}

	
	//Method reads and returns the user inputs for initial and goal states
	public int[][] inputConfiguration()
	{
		Scanner scanObj = new Scanner(System.in);
		int[][] state = new int[3][3];
		//System.out.println("Please input configuration as a list and use 0 where there is no tile.");
		for(int row=0;row<3;row++)
		{
			for(int col=0;col<3;col++)
			{
				state[row][col] = scanObj.nextInt();
			}
		}
		return state;
	}
	
	//function to calculate Manhattan distance
	public int calculateManhattanDist(int[][] currentArr,int[][] goalArr)
	{
		int man[]=new int[8];
		int sum = 0;
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				int currentNum = currentArr[i][j];
				if(currentNum!=0)
				{
					for(int k=0;k<3;k++)
					{
						for(int l=0;l<3;l++)
						{
							if(goalArr[k][l]==currentNum)
							{
								man[currentNum-1] = Math.abs(i-k)+Math.abs(j-l);
							}
						}
					}
				}
			}
		}
		
		for(int i=0;i<man.length;i++)
		{
			sum+=man[i];
		}
		return sum;
	}
	
	
	//Method to generate children nodes
	public void generateCandidateNodes(int[][] current)
	{
		int[][] temp;
		int rowNew;
		int colNew;
		
		//find out location of element '0'
		for(int row=0;row<3;row++)
		{
			for(int col=0;col<3;col++)
			{
				if (current[row][col]==0)
				{
					//generating x-1
					rowNew = row-1;

					//the new nodes generated can have indices between 0 and 2
					if((rowNew>=0)&&(rowNew<=2))
					{
						temp = new int[3][3];
						temp = Function.copyMatrix(current);
						nodeUp = Function.swapElements(temp,row,col,rowNew,col);
					}
					
					if(Function.areEqual(nodeUp,zero))
					{
						nodeUp = null;
					}
					
					//generating x+1
					rowNew = row+1;
					
					//the new nodes generated can have indices between 0 and 2
					if((rowNew>=0)&&(rowNew<=2))
					{
						temp = new int[3][3];
						temp = Function.copyMatrix(current);
						nodeDown = Function.swapElements(temp,row,col,rowNew,col);					
					}
					
					if(Function.areEqual(nodeDown,zero))
					{
						nodeDown = null;
					}
					
					//generating y-1
					colNew = col-1;
					
					//the new nodes generated can have indices between 0 and 2
					if((colNew>=0)&&(colNew<=2))
					{
						temp = new int[3][3];
						temp = Function.copyMatrix(current);
						nodeLeft = Function.swapElements(temp,row,col,row,colNew);
					}	
					
					if(Function.areEqual(nodeLeft,zero))
					{
						nodeLeft = null;
					}
					
					//generating y+1
					colNew = col+1;
					
					//the new nodes generated can have indices between 0 and 2
					if((colNew>=0)&&(colNew<=2))
					{
						temp = new int[3][3];
						temp = Function.copyMatrix(current);
						nodeRight = Function.swapElements(temp,row,col,row,colNew);
					}
					
					if(Function.areEqual(nodeRight,zero))
					{
						nodeRight = null;
					}

				}
			}
		}
	}
}
