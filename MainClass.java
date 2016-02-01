package eightPuzzleFinal;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MainClass {
	 
	//comparator for priority queue
	public static Comparator<Node> fValueComparator = new Comparator<Node>()
	 
	{
		@Override
		public int compare(Node n1, Node n2)
		{
			return (int) (n1.getfVal()-n2.getfVal());
		}
	
	};
	
	private int generatedNode;
	private int expandedNode;
	private PriorityQueue<Node> pq = new PriorityQueue<Node>(11,fValueComparator);
	private Node nodeGoal = new Node();

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MainClass mainObj = new MainClass();
		Node nodeStart = new Node();
		nodeStart.parentNode = null;
		int[][] current = new int[3][3];
		System.out.println("Input the initial state.");
		
		current = nodeStart.inputConfiguration();
		nodeStart.setNodeState(current);
		
		
		//System.out.println(Arrays.deepToString(nodeStart.getNodeState()));
		
		System.out.println("Input the goal state.");
		current = mainObj.nodeGoal.inputConfiguration();
		mainObj.nodeGoal.setNodeState(current);

		int m = nodeStart.calculateManhattanDist(nodeStart.getNodeState(), mainObj.nodeGoal.getNodeState());
		nodeStart.sethVal(m);
		nodeStart.setgVal(0);
		nodeStart.setfVal(nodeStart.getgVal()+nodeStart.gethVal());
		//System.out.println(m);

		
		mainObj.pq.add(nodeStart);
		
		mainObj.search();
	}
	
	private void search ()
	{	
		//System.out.println("hey i am check");
		Node currentNode;
		Node goal=null;
		int s=0;
		
		//when priority queue still has a node in it
		while((currentNode=pq.poll())!=null)
		{	
			currentNode.generateCandidateNodes(currentNode.getNodeState());
			
			
			//candidates generated and add them to priority queue only if node not equal to null
			//if the candidate node generated is null then we dont add it to the priority queue
			if(currentNode.getNodeLeft()!=null)
			{
				//pass left node to local node because hVal attribute one for one object
				Node leftNode = new Node();
				generatedNode++;
				leftNode.setNodeState(currentNode.getNodeLeft());
				leftNode.sethVal(leftNode.calculateManhattanDist(leftNode.getNodeState(), nodeGoal.getNodeState()));
				leftNode.setgVal(currentNode.getgVal()+1);
				leftNode.setfVal(leftNode.gethVal()+leftNode.getgVal());
				if(leftNode.gethVal()!=0)
				{
					leftNode.parentNode = currentNode;
					pq.add(leftNode);
				}
				
				//for when the goal is reached
				else
				{
					leftNode.parentNode = currentNode;
					goal = leftNode;
					s = 1;
					break;
					
				}
			}
			
			if(currentNode.getNodeRight()!=null)
			{
				Node rightNode = new Node();
				generatedNode++;
				rightNode.setNodeState(currentNode.getNodeRight());
				rightNode.sethVal(rightNode.calculateManhattanDist(rightNode.getNodeState(), nodeGoal.getNodeState()));
				rightNode.setgVal(currentNode.getgVal()+1);
				rightNode.setfVal(rightNode.gethVal()+rightNode.getgVal());
				if(rightNode.gethVal()!=0)
				{
					rightNode.parentNode = currentNode;
					pq.add(rightNode);
				}
				else
				{
					rightNode.parentNode = currentNode;

					goal = rightNode;
					s = 1;
					break;
				}
			}
			
			if(currentNode.getNodeUp()!=null)
			{
				Node upNode = new Node();
				generatedNode++;
				upNode.setNodeState(currentNode.getNodeUp());
				upNode.sethVal(upNode.calculateManhattanDist(upNode.getNodeState(), nodeGoal.getNodeState()));
				upNode.setgVal(currentNode.getgVal()+1);
				upNode.setfVal(upNode.gethVal()+upNode.getgVal());
				if(upNode.gethVal()!=0)
				{
					upNode.parentNode = currentNode;
					pq.add(upNode);
				}
				else
				{
					upNode.parentNode = currentNode;
					goal = upNode;
					s = 1;
					break;
				}
			}
			
			if(currentNode.getNodeDown()!=null)
			{
				Node downNode = new Node();
				generatedNode++;
				downNode.setNodeState(currentNode.getNodeDown());
				downNode.sethVal(downNode.calculateManhattanDist(downNode.getNodeState(), nodeGoal.getNodeState()));
				downNode.setgVal(currentNode.getgVal()+1);
				downNode.setfVal(downNode.gethVal()+downNode.getgVal());
				if(downNode.gethVal()!=0)
				{
					downNode.parentNode = currentNode;
					pq.add(downNode);
				}
				else
				{					
					downNode.parentNode = currentNode;
					goal = downNode;
					s = 1;
					break;		
				}
			}
			expandedNode++;
		}
		
		if (s==1)
		{
			System.out.println("Number of generated nodes (excluding the root node): "+generatedNode);
			System.out.println("Number of nodes expanded: "+expandedNode);
			while(goal!=null)
			{
			Function.returnConfiguration(goal.getNodeState());			
			goal = goal.parentNode;
			}
		}
	}
		
}
