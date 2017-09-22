// Copy paste this Java Template and save it as "HospitalRenovation.java"
import java.util.*;
import java.io.*;

// write your matric number here:
// write your name here:
// write list of collaborators here:
// year 2017 hash code: AlaYnzmQ65P4x2Uc559u (do NOT delete this line)

class HospitalRenovation {
  private int V; // number of vertices in the graph (number of rooms in the hospital)
  private int[] RatingScore; // the weight of each vertex (Rating score of each room)
  private ArrayList<ArrayList<Integer>> adjList;
    
  public HospitalRenovation() {
    
  }

  
    private int bfs(int u) {
    	// if a vertex is disconnected and there are only 1 vertices in total, then no vertices can be visited
    	if (V == 1)
    		return 0;
    	 //System.out.println("Iterator to pair:"+deletePair.first()+","+deletePair.second());
    	int start = (u != 0) ? 0 : 1;
    	int nVisited = 0; // total number of vertices visited    	
    	boolean[] visited = new boolean[V];
    	for (int i=0; i<V; i++)
    		visited[i] = false;
    	//visit the vertex one by one
    	Queue<Integer> q = new LinkedList<Integer>();
    	// visit the start vertex
    	q.offer(start);
    	visited[start] = true;
    	nVisited++;
    	
        //if there is no critical room
    	while (q.isEmpty() == false) {
    		int curVertex = q.peek();
    		q.poll();
    		for (int i=0; i<adjList.get(curVertex).size(); i++) {
    			int neighbour = adjList.get(curVertex).get(i);
    			if (neighbour != u && visited[neighbour] == false) {
    				visited[neighbour] = true;
                    //System.out.println(neighbor)
    				nVisited++;
    				q.offer(neighbour);
    			}
    				
    		}
    	}
    	
    	return nVisited;
    }
    
  int Query() {
    int ans = 0;
      
      
    // You have to report the rating score of the important room (vertex)
    // with the lowest rating score in this hospital
    //
    // or report -1 if that hospital has no important room
      
    ArrayList<IntegerPair> criticalRooms = new ArrayList<IntegerPair>();
    
for (int i=0; i < V; i++) {
	if (bfs(i) < V-1)
		criticalRooms.add(new IntegerPair(RatingScore[i], i));
}

if (criticalRooms.isEmpty())
	return -1; // if there are no articulation points, there are no important rooms

    Collections.sort(criticalRooms);
    return criticalRooms.get(0).first();
  }

    void run() throws Exception {
    // for this PS3, you can alter this method as you see fit

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    int TC = Integer.parseInt(br.readLine()); // there will be several test cases
    while (TC-- > 0) {
      br.readLine(); // ignore dummy blank line
      V = Integer.parseInt(br.readLine());

      StringTokenizer st = new StringTokenizer(br.readLine());
      // read rating scores,
      RatingScore = new int[V];
      for (int i = 0; i < V; i++)
        RatingScore[i] = Integer.parseInt(st.nextToken());

      // clear the graph and read in a new graph as Adjacency list
      adjList = new ArrayList<ArrayList<Integer>>(V);
      
      for (int vertex = 0; vertex < V; vertex++) {
        st = new StringTokenizer(br.readLine());
        int nNeighbours = Integer.parseInt(st.nextToken());
        ArrayList<Integer> neighbours = new ArrayList<Integer>(nNeighbours); // neighbours of adjList[vertex]
        
        while (nNeighbours-- > 0) {
          int neighbour = Integer.parseInt(st.nextToken());
          neighbours.add(neighbour);
        }
        adjList.add(neighbours);
      }

      pr.println(Query());
    }
    pr.close();
  }

  public static void main(String[] args) throws Exception {
    // do not alter this method
    HospitalRenovation ps3 = new HospitalRenovation();
    ps3.run();
  }
}



class IntegerPair implements Comparable < IntegerPair > {
  Integer _first, _second;

  public IntegerPair(Integer f, Integer s) {
    _first = f;
    _second = s;
  }

  public int compareTo(IntegerPair o) {
    if (!this.first().equals(o.first()))
      return this.first() - o.first();
    else
      return this.second() - o.second();
  }

  Integer first() { return _first; }
  Integer second() { return _second; }
}
