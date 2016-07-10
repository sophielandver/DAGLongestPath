import java.util.LinkedList;
import java.util.Scanner;


public class countLongPaths {
	
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		String str_num_graphs = input.nextLine(); 
		int num_graphs = Integer.parseInt(str_num_graphs.trim());
		DAG[] graphs = new DAG[num_graphs];
		
		for (int i=0; i<num_graphs; i++)
		{
			String line = input.nextLine(); //get n and m
			String[] parts = line.trim().split(" ");
			int n = Integer.parseInt(parts[0]);
			int m = Integer.parseInt(parts[1]);
			//now get adjlist of the graph
			@SuppressWarnings("unchecked") LinkedList<AdjNode>[] adjList = new LinkedList[n];
			for (int j=0; j<m; j++)
			{
				String edge_desc = input.nextLine();
				String[] edge_parts = edge_desc.trim().split(" ");
				int I = Integer.parseInt(edge_parts[0]);
				int J = Integer.parseInt(edge_parts[1]);
				int W = Integer.parseInt(edge_parts[2]);
				AdjNode node = new AdjNode(J-1, W);
				if (adjList[I-1] == null)
				{
					adjList[I-1] = new LinkedList<AdjNode>();
				}
				adjList[I-1].add(node);
			}
			graphs[i] = new DAG(adjList, n, m);
		}
		
		input.close();
		
		for (int i=0; i<num_graphs; i++)
		{
			int[] path_info = graphs[i].LongestPathFromSource(1);
			System.out.println("graph number: " + (i+1));
			System.out.println("longest path: " + path_info[0]);
			System.out.println("number of longest paths: " + path_info[1]);
			System.out.println();
			
		}
	}

}
