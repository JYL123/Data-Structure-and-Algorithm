// Copy paste this Java Template and save it as "HospitalRenovation.java"
import java.util.*;
import java.io.*;

// write your matric number here:
// write your name here:
// write list of collaborators here:
// year 2017 hash code: AlaYnzmQ65P4x2Uc559u (do NOT delete this line)

class HospitalRenovation {
  private int V; // number of vertices in the graph (number of rooms in the hospital)
  private int[][] AdjMatrix; // the graph (the hospital)
  private int[] RatingScore; // the weight of each vertex (rating score of each room)
  private int[] result; // store componenting result
  private int[] visited;
  private ArrayList<ArrayList<IntegerPair>> AdjList=new ArrayList<ArrayList<IntegerPair>>();  
  int disconnectedVertex=-1;
  
  //private int cc=0;
  // if needed, declare a private data structure here that
  // is accessible to all methods in this class

  public HospitalRenovation() {
    // Write necessary code during construction
    //
    // write your answer here
  }

 //create an Adjacency List
 ArrayList<ArrayList<IntegerPair>> loadAdjList()
 {
     ArrayList<ArrayList<IntegerPair>> getList= new ArrayList<ArrayList<IntegerPair>>();

     for(int i=0; i<V; i++)
     {
         ArrayList<IntegerPair> row=new ArrayList<IntegerPair>();
         for(int j=0; j<V; j++)
         {
             if(AdjMatrix[i][j]==1)
                 row.add(new IntegerPair(i, j));
                 
         }
         getList.add(row);
     
     }
     return getList;
  }

  //count components
  int identifyComponents()
  {
      int cc=0;
      //initialization to 0
      for(int p=0; p<V; p++) visited[p]=0;
      for(int x=0; x<V; x++)
      {
          if (visited[x]==0)
          {
              cc=cc+1;

              if(disconnectedVertex==x) continue;
              DFSrec(x);
          }
      }
      return cc;
  }

  void DFSrec(int u)
  {
      visited[u]=1;

      for(int y=0; y<AdjList.get(u).size(); y++)
      {
          //reverse to the neighbors && neighbor is not the node to be
          //disconnected 
          if((AdjList.get(u).get(y)).second()!=disconnectedVertex && visited[(AdjList.get(u).get(y)).second()]==0)
              DFSrec((AdjList.get(u).get(y)).second());
      }
  }
  int Query() {
    int ans = -1;
    if(V==1) return ans;

    // You have to report the rating score of the critical room (vertex)
    // with the lowest rating score in this hospital
    //
    // or report -1 if that hospital has no critical room
    //
    // write your answer here

    AdjList=loadAdjList();
       
    for(int i=0; i<V; i++)
    {
        //ans has the largest rating score
        if(RatingScore[i]>ans) ans=RatingScore[i];
    }

    Boolean connectivity=true;
    for(int i=0; i<V; i++)
    {
        disconnectedVertex=i;

        if(identifyComponents()>2)// is not connected
        {
            connectivity=false;
            if(ans>=RatingScore[i]) 
            {    
                ans=RatingScore[i];
            }      
        }
    }
    //no critical room 
    if(connectivity==true) return -1;
    return ans;
  }

  // You can add extra function if needed
  // --------------------------------------------



  // --------------------------------------------

  void run() throws Exception {
    // for this PS3, you can alter this method as you see fit

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    int TC = Integer.parseInt(br.readLine()); // there will be several test cases
    while (TC-- > 0) {
      br.readLine(); // ignore dummy blank line
      V = Integer.parseInt(br.readLine());

      StringTokenizer st = new StringTokenizer(br.readLine());
      // read rating scores, A (index 0), B (index 1), C (index 2), ..., until the V-th index
      RatingScore = new int[V];
      for (int i = 0; i < V; i++)
        RatingScore[i] = Integer.parseInt(st.nextToken());

      // clear the graph and read in a new graph as Adjacency Matrix
      AdjMatrix = new int[V][V];
      for (int i = 0; i < V; i++) {
        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        while (k-- > 0) {
          int j = Integer.parseInt(st.nextToken());
          AdjMatrix[i][j] = 1; // edge weight is always 1 (the weight is on vertices now)
        }
      }
      
      visited=new int[V];
      result=new int[V];
      for(int m=0; m<V; m++)
      {
          visited[m]=-1;
          result[m]=0;
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
