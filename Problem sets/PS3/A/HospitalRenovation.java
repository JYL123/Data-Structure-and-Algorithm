import java.util.*;
import java.io.*;

class HospitalRenovation
{
    private int V;//number of vertices 
    private int[][] AdjMatrix;
    private int[] RatingScore;
    private int[] Visited;
    private int[] NumComponent;

    public HospitalRenovation()
    {
        AdjMatrix=new int[V][V];
        RatingScore=new int[V];
        Visited=new int[V];
        NumComponent=new int[V];
    }

    void DFSrec(int u)
    {
        Visited[u]=1;
        for(int i=0; i<V; i++)
        {
            if(AdjMatrix[u][i]==1)
            {
                if(Visited[i]==0) DFSrec(i);
            }
        }
    }

    int identifyComponent()
    {
        int cc=0;
        //System.out.println("V is "+V);
        for(int i=0; i<V; i++)
        {
            //System.out.println("i is "+i);
            Visited[i]=0;
        }
        for(int i=0; i<V; i++)
        {
            if(Visited[i]==0)
            {
                cc=cc+1;
                DFSrec(i);
            }
        }
        return cc;
    }

    int Query()
    {   //report -1 if that hospital has no critical room
        int ans=0;
        if(V==1) return -1;
        //delete each vertex and its associated edges, run identify componet
          int[][] adjMatrix=new int[V][V];
          //System.arraycopy(AdjMatrix, 0, adjMatrix, V);
          //adjMatrix=AdjMatrix;
          for(int m=0; m<V; m++)
          {
              for(int n=0; n<V; n++)
              {
                adjMatrix[m][n]=AdjMatrix[m][n];
              }
          }
          
          for(int i=0; i<V; i++)
          {
             
            for(int j=0; j<V; j++)   
            {
                //System.out.println("i is "+i+" and j is "+j);
                if(AdjMatrix[i][j]==1) AdjMatrix[j][i]=0;
                AdjMatrix[i][j]=0;
            }

            NumComponent[i]=identifyComponent();
            for(int x=0; x<V; x++)
            {
                for(int y=0; y<V; y++)
                {
                    AdjMatrix[x][y]=adjMatrix[x][y];
                }
            }
          }

          for(int i=0; i<V; i++)
          {
              if(ans<RatingScore[i])
                  ans=RatingScore[i];
          }
          Boolean haveRoom=false;
          for(int i=0; i<V; i++)
          {
              //System.out.print("Rating score is "+RatingScore[i]+" ");
              if(NumComponent[i]>2) 
              { 
                  haveRoom=true;
                  if(RatingScore[i]<ans)
                  ans=RatingScore[i];
              }
          }
          if(haveRoom==false) return -1;
          return ans;
    }

    void run() throws Exception
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pr=new PrintWriter(new BufferedWriter(new
        OutputStreamWriter(System.out)));
        
        int TC=Integer.parseInt(br.readLine());
        while(TC-- >0)
        {
            br.readLine();
            V=Integer.parseInt(br.readLine());

            StringTokenizer st=new StringTokenizer(br.readLine());

            RatingScore=new int[V];
            for(int i=0; i<V; i++)
            {
                RatingScore[i]=Integer.parseInt(st.nextToken());
            }

            AdjMatrix = new int[V][V];
            for (int i = 0; i < V; i++) 
            {
              st = new StringTokenizer(br.readLine());
              int k =Integer.parseInt(st.nextToken());
              while (k-- > 0)
              {
                int j =Integer.parseInt(st.nextToken());
                AdjMatrix[i][j]= 1; //edge weight is always 1 (the weight is on vertices now)
              }
            }

            Visited =new int[V];
            NumComponent=new int[V];
            for(int i=0; i<V; i++)
            {  
                NumComponent[i]=0;
                Visited[i]=-1;
            }


            pr.println(Query());

        }
        pr.close();

    }

    public static void main (String[] args) throws Exception
    {
        HospitalRenovation ps3=new HospitalRenovation();
        ps3.run();
    }
}

class IntegerPair implements Comparable <IntegerPair>
{
    Integer _first, _second;

    public IntegerPair(Integer f, Integer s)
    {
        _first=f; 
        _second=s;
    }

    public int compareTo(IntegerPair o)
    {
        if(!this.first().equals(o.first()))
            return this.first() - o.first();
        else 
            return this.second()-o.second();
    }

    Integer first() {return _first;}
    Integer second() {return _second;}
}
