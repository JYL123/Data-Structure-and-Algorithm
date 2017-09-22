// Copy paste this Java Template and save it as "GettingFromHereToThere.java"
import java.util.*;
import java.io.*;

// write your matric number here:
// write your name here:
// write list of collaborators here:
// year 2017 hash code: x4gxK7xzMSlNvFsMEUVn (do NOT delete this line)

class GettingFromHereToThere {
  private int V; // number of vertices in the graph (number of rooms in the building)
  private ArrayList < ArrayList < IntegerPair > > AdjList; // the weighted graph (the building), effort rating of each corridor is stored here too
  //private ArrayList < ArrayList < IntegerTriple > > AdjList2=new ArrayList<ArrayList<IntegerTriple>>();//convert AdjList to IntegerTriple
  private ArrayList < ArrayList < IntegerPair > > AdjList3;
  private ArrayList<Boolean> taken;//for Prime's
  private PriorityQueue<IntegerTriple> pq;//for Prime's
  //private ArrayList<IntegerTriple> selectedEdges;//to store edges in primes' 
  //private HashMap<IntegerPair, Integer> hm=new HashMap<IntegerPair,Integer>();// to store queries 
  //private ArrayList<ArrayList<IntegerPair>> tree=new ArrayList<ArrayList<IntegerPair>>();//to store MST
  private int[] Visited;//for DFS
  private ArrayList<Integer> edges=new ArrayList<Integer>();//DFS traversal
  private int[][] storeAns;
  private int source2; 
  // if needed, declare a private data structure here that
  // is accessible to all methods in this class
  // --------------------------------------------
  // --------------------------------------------

  public GettingFromHereToThere() {
    // Write necessary codes during construction;
    //
    // write your answer here
  }

  void PreProcess() {
    // write your answer here
    // you can leave this method blank if you do not need it

    //To find MST
    findTree(0);
    //To store all possible queries into matrix 
    storeQuery();

  }


  //add all the neighbors of this vertex
  //into a priority queue by weight
  void process(int vertex)  
  {
      taken.set(vertex, true);
      for(int j=0; j<AdjList.get(vertex).size(); j++)
      {
          IntegerPair v=AdjList.get(vertex).get(j);
          if(!taken.get(v.first()))//if neighbor is not taken
          {
              //process neighbor
              pq.offer(new IntegerTriple(v.second(), vertex, v.first())); //weight; from; to
          }
      }
  }

  //build MST for the entire graph
  void findTree(int source) {
    
    //initialization
    pq=new PriorityQueue<IntegerTriple>();
    taken=new ArrayList<Boolean>();
    taken.addAll(Collections.nCopies(V, false));
    //selectedEdges=new ArrayList<IntegerTriple>();
    AdjList3=new ArrayList<ArrayList<IntegerPair>>();
     //initialize AdjList3 to store MST with triple
     for(int i=0; i<V; i++)
     {
         ArrayList<IntegerPair> addRow=new ArrayList<IntegerPair>();
         AdjList3.add(addRow);
     }

    // You have to report the weight of a corridor (an edge)
    // which has the highest effort rating in the easiest path from source to destination for the wheelchair bound
    //
    // write your answer here
    
    process(source);

    while(!pq.isEmpty())
    {
        IntegerTriple front=pq.poll();

        if(!taken.get(front.third()))//if neighbor s not taken before
        {
            process(front.third());
            AdjList3.get(front.second()).add(new IntegerPair(front.third(), front.first()));
            AdjList3.get(front.third()).add(new IntegerPair(front.second(), front.first()));
        }
    }

  }

  int Query(int source, int destination)
  {
      return storeAns[source][destination];
  }
  
  void storeQuery()
  {      
      int numSource= (V>=10)? 10:V; 
      storeAns=new int[numSource][V];

      for(int x=0; x<numSource; x++)//from 0 to 10/V
      {
          for(int i=0; i<V; i++) Visited[i]=0;
          source2=x;
          DFSrec(x, 0);//when it starts, max0;
      }

  }

  
  void DFSrec(int x, int max)
  {
      //System.out.println("Visited: "+x);
      Visited[x]=-1; 
        //System.out.println("x: "+x);
      for(int i=0; i<AdjList3.get(x).size(); i++)
      { 
           if(Visited[AdjList3.get(x).get(i).first()]==0)
           {   
               int weight=Math.max(max, AdjList3.get(x).get(i).second());
               storeAns[source2][AdjList3.get(x).get(i).first()]=weight;
               DFSrec(AdjList3.get(x).get(i).first(), weight);
            }
       }
  }

  // You can add extra function if needed
  // --------------------------------------------



  // --------------------------------------------

  void run() throws Exception {
    // do not alter this method
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
          AdjList.get(i).add(new IntegerPair(j, w)); // edge (corridor) weight (effort rating) is stored here
        }
      }
      Visited=new int[V];
      PreProcess(); // you may want to use this function or leave it empty if you do not need it

      int Q = sc.nextInt();
      while (Q-- > 0)
        pr.println(Query(sc.nextInt(), sc.nextInt()));
      pr.println(); // separate the answer between two different graphs
    }

    pr.close();
  }

  public static void main(String[] args) throws Exception {
    // do not alter this method
    GettingFromHereToThere ps4 = new GettingFromHereToThere();
    ps4.run();
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

  @Override
  public int hashCode()
  {
      int result=(int)_first*11+_second*11*11;
      return result;
  }
  @Override
  public boolean equals(Object obj)
  {
     if(obj==this) return true;
     if(obj==null || obj.getClass()!=this.getClass()) return false;

     IntegerPair ip=(IntegerPair) obj;
     return _first==ip._first && _second==ip._second;
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
