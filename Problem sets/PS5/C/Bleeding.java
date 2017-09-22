// Copy paste this Java Template and save it as "Bleeding.java"
import java.util.*;
import java.io.*;

// write your matric number here:
// write your name here:
// write list of collaborators here:
// year 2017 hash code: x8DYWsALaAzykZ8dYPZP (do NOT delete this line)

class Bleeding {
  private int V; // number of vertices in the graph (number of junctions in Singapore map)
  private int Q; // number of queries
  private ArrayList < ArrayList < IntegerPair> > AdjList; // the weighted graph (the Singapore map), the length of each edge (road) is stored here too, as the weight of edge
  private int INF=10000000;
  private PriorityQueue<IntegerTriple> PQ;
  private ArrayList<Integer> D;
  private int[][] distance;
  // if needed, declare a private data structure here that
  // is accessible to all methods in this class
  // --------------------------------------------
  // --------------------------------------------

  public Bleeding() {
    // Write necessary code during construction
    //
    // write your answer here
  }

  void PreProcess() {
    // Write necessary code to preprocess the graph, if needed
    //
    // write your answer here
    //-------------------------------------------------------------------------     
    D=new ArrayList<Integer>();
    PQ=new PriorityQueue<IntegerTriple>();
    int counter=0;
  }

  void initSSSP (int s)
  {}

  int Query(int s, int t, int k) {
    int ans = -1;

    // You have to report the shortest path from Ket Fah's current position s
    // to reach the chosen hospital t, output -1 if t is not reachable from s
    // with one catch: this path cannot use more than k vertices      
    //
    // write your answer here
    //initSSSP(s);
    PQ.clear();
    PQ.offer(new IntegerTriple(0,1,s));//cost; numJunction; vertex
    distance=new int[V][V+2];// k+1 column with index k; junction=0 is not applicable
    for(int i=0; i<V; i++)
    {
        for(int j=0; j<V+2; j++)
        {
            distance[i][j]=INF;//initialize
        }
    }
    distance[s][1]=0;
    //System.out.println("source "+s+" destination "+t+" junctions allowed "+k);
    while(!PQ.isEmpty())
    {
        IntegerTriple least=PQ.poll();
        int leastDistance=least.first();
        int numJunction=least.second();
        int vertex=least.third();
       
        if(leastDistance > distance[vertex][numJunction]) continue; //the important check: with least distance so far from the source
        //more than K junctions, discard the copy
        if(numJunction>k) continue;
        if(vertex==t ) 
        {
            return leastDistance;
        }
        //just K juncitons, but vertex is not the destination, discard
        //the copy
        for(int i=0; i<AdjList.get(vertex).size(); i++)
        {
            //can be relaxed
            if(distance[AdjList.get(vertex).get(i).first()][numJunction+1]>distance[vertex][numJunction]+AdjList.get(vertex).get(i).second())
            {
                distance[AdjList.get(vertex).get(i).first()][numJunction+1]=distance[vertex][numJunction]+AdjList.get(vertex).get(i).second();
                PQ.offer(new IntegerTriple(distance[AdjList.get(vertex).get(i).first()][numJunction+1],numJunction+1, AdjList.get(vertex).get(i).first()));
            }
        }
    }
    return ans;
  }

  // You can add extra function if needed
  // --------------------------------------------



  // --------------------------------------------

  void run() throws Exception {
    // you can alter this method if you need to do so
    IntegerScanner sc = new IntegerScanner(System.in);
    PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    int TC = sc.nextInt(); // there will be several test cases
    while (TC-- > 0) {
      V = sc.nextInt();

      // clear the graph and read in a new graph as Adjacency List
      AdjList = new ArrayList < ArrayList < IntegerPair > >();
      for (int i = 0; i < V; i++) {
        AdjList.add(new ArrayList < IntegerPair >());

        int k = sc.nextInt();
        while (k-- > 0) {
          int j = sc.nextInt(), w = sc.nextInt();
          AdjList.get(i).add(new IntegerPair(j, w)); // edge (road) weight (in minutes) is stored here
        }
      }

      PreProcess(); // optional

      Q = sc.nextInt();
      while (Q-- > 0)
        pr.println(Query(sc.nextInt(), sc.nextInt(), sc.nextInt()));

      if (TC > 0)
        pr.println();
    }

    pr.close();
  }

  public static void main(String[] args) throws Exception {
    // do not alter this method
    Bleeding ps5 = new Bleeding();
    ps5.run();
  }
}



class IntegerScanner { // coded by Ian Leow, using any other I/O method is not recommended
  BufferedInputStream bis;
  IntegerScanner(InputStream is) {
    bis = new BufferedInputStream(is, 1000000);
  }
  
  public int nextInt() {
    int result = 0;
    try {
      int cur = bis.read();
      if (cur == -1)
        return -1;

      while ((cur < 48 || cur > 57) && cur != 45) {
        cur = bis.read();
      }

      boolean negate = false;
      if (cur == 45) {
        negate = true;
        cur = bis.read();
      }

      while (cur >= 48 && cur <= 57) {
        result = result * 10 + (cur - 48);
        cur = bis.read();
      }

      if (negate) {
        return -result;
      }
      return result;
    }
    catch (IOException ioe) {
      return -1;
    }
  }
}

class IntegerTriple implements Comparable < IntegerTriple > {
	  Integer _first, _second, _third;

	  public IntegerTriple(Integer f, Integer s, Integer t) {
	    _first = f;
	    _second = s;
	    _third = t;
	  }

	  public int compareTo(IntegerTriple o) {
	    if (!this.first().equals(o.first()))
	      return this.first() - o.first();
	    else if (!this.second().equals(o.second()))
	      return this.second() - o.second();
	    else
	      return this.third() - o.third();
	  }

	  Integer first() { return _first; }
	  Integer second() { return _second; }
	  Integer third() { return _third; }
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
