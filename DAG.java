import java.util.LinkedList;


public class DAG {
	
	public LinkedList<AdjNode>[] _adjList;
	public int _n; //number of vertices
	public int _m; //number of edges
	
	/*
	 * Constructor for DAG class
	 */
	public DAG(LinkedList<AdjNode>[] adjList, int num_vertices, int num_edges)
	{
		_adjList = adjList;
		_n = num_vertices;
		_m = num_edges;
	}

	public int[] LongestPathFromSource(int source)
	{
		int[][] dist_count = new int[_n][2];
		source = source - 1;
		for (int i=0; i<_n; i++)
		{
			dist_count[i][0] = Integer.MIN_VALUE; //distance from source
			dist_count[i][1] = 0; //how many paths there are from i to node 1 of distance dist_count[i][0]
			
		}
		dist_count[source][0] = 0;
		dist_count[source][1] = 1;
		
		//iterate through the adjacency list and fill dist_count
		for (int i=0; i<_n; i++)
		{
			if (_adjList[i] == null) //that vertex has no outgoing edges
			{
				continue;
			}
			LinkedList<AdjNode> edge_list = _adjList[i];
			for (int k=0; k<_adjList[i].size(); k++)
			{
				AdjNode node = edge_list.get(k);
				int u = i;
				int v = node._data;
				int weight_uv = node._weight;
				int dist1 = dist_count[u][0] + weight_uv;
				int dist2 = dist_count[v][0];
				if (dist1 > dist2)
				{
					dist_count[v][0] = dist1;
					dist_count[v][1] = dist_count[u][1];
					
				}
				else if (dist1 == dist2)
				{
					dist_count[v][1] = dist_count[v][1] + dist_count[u][1];
				}
				
			}
		}
		
		int max_dist = 0;
		int max_count = 0;
		for (int j=0; j<_n; j++)
		{
			if (dist_count[j][0] > max_dist)
			{
				max_dist = dist_count[j][0];
				max_count = dist_count[j][1];
			}
		}
		
		int[] solution = {max_dist, max_count};
		return solution;
		
	}
}
