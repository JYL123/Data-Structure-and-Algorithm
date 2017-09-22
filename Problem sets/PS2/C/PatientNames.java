// Copy paste this Java Template and save it as "PatientNames.java"
import java.util.*;
import java.io.*;

// write your matric number here:A0160257J
// write your name here: Li JiaYao
// write list of collaborators here:
// year 2017 hash code: DZAjKugdE9QiOQKGFbut (do NOT delete this line)


//Every vertext in this AVL is a Java Class
class AVLVertex
{
	AVLVertex(String v) {key=v; parent=left=right=null; height=0; size=1;}
	public AVLVertex parent, left, right;
	public String key; 
	public int height;
	public int size;
}

//This is a sample implementation 
//There are other ways to implement AVL concepts 
class AVL
{
	protected AVLVertex root;
	
	public AVL() {root=null;}
	
   protected void updateHeight(AVLVertex T) {
	        int leftHeight = -1;
	        int rightHeight = -1;
	        if (T.left != null) {
	            leftHeight = T.left.height;
	        }
	        if (T.right != null) {
	            rightHeight = T.right.height;
	        }
	        T.height=Math.max(leftHeight, rightHeight) + 1;
	    }

   protected void updateSize(AVLVertex T) {
        if (T == null) {
            T.size=0;
        }
        int leftSize = 0;
        int rightSize = 0;
        
        if (T.left != null) {
            leftSize = T.left.size;
        }
        if (T.right != null) {
            rightSize = T.right.size;
        }
        T.size=leftSize + rightSize + 1;
    }
	
	public void insert(String v) {root=insert(root, v);}
	
	protected AVLVertex insert(AVLVertex T, String v)
	{
	    if (T==null) return new AVLVertex(v);
	    
	    if (T.key.compareTo(v)<0) //T.key<v
	    {
	      T.right=insert(T.right, v);
	      T.right.parent=T;
          T.size+=1;
          
            
	      if(T.left!=null)
	      {
              T.height=Math.max(T.left.height, T.right.height)+1;
              //T.size=T.left.size + T.right.size+1;
          }
	      else 
	      {
              T.height=T.right.height+1;
              //T.size=T.right.size+1;
          }
          
	    }
	    else 
	    {
	        	
	      T.left=insert(T.left, v);
	      T.left.parent=T;
          T.size+=1;
          
	      if(T.right!=null)
		  {T.height=Math.max(T.left.height, T.right.height)+1; }//T.size=T.left.size+ T.right.size+1;
		  else 
		  {T.height=T.left.height+1; }	//T.size=T.left.size+1;
          
	      
	    }
        //updateHeight(T);  //update height after insertion
	   
	    //check balance factor and rotate four cases  ----------------------other possibilities --------------------
	    if(rebalanceNode(T)!=null) // need to rotate
	    {
	     
	     if(bf(T)==-2 && -1<=bf(T.right) && bf(T.right)<=0){
           T=rotateLeft(T);
	     }
	     else if (bf(T)==-2 && bf(T.right)<=1){
	       T.right=rotateRight(T.right);
	       T=rotateLeft(T);
	     }
	     else if(bf(T)==2 && 0<=bf(T.left) && bf(T.left)<= 1){
           T=rotateRight(T);
	     }
	     else if(bf(T)==2 && bf(T.left)==-1){
           T.left=rotateLeft(T.left);
           T=rotateRight(T);
	     }
	     
	    } 
	    
	    return T;
	}
	//calculate balance factor
    protected int bf(AVLVertex T)
  {
	  
	  if(T==null) 
      {	 return 0;}
	  
  	if(T.left!=null && T.right!=null)
      return T.left.height-(T.right.height);
    else if(T.left==null && T.right!=null)
      return (T.right.height+1)*-1;
  	else if(T.left!=null && T.right==null)
      return T.left.height+1;
  	else return 0;
  	
  }
  
	//protected method called to return trouble node
	protected AVLVertex rebalanceNode(AVLVertex T)
	{
	if(T==null) return null;
		
	  if(bf(T)>=-1 && bf(T)<=1) 
	  {
		  if(T.parent!=null)
		  {
			  T=T.parent; 
		      rebalanceNode(T);
		  }
		  else return null;
	  }
	  else 
		  return T;
	  
	  return null;
	  
	}
	
	//protected method to rotateLeft
	protected AVLVertex rotateLeft(AVLVertex T) 
	{
	  
	  AVLVertex W=T.right;	
	  
	  W.parent=T.parent;
	  T.parent=W;
	  T.right=W.left;
	  if(W.left != null) W.left.parent=T;
	  
	  W.left=T;
	  
	  updateHeight(T);
	  updateHeight(W);
	  updateSize(T);
	  updateSize(W);
	  
	  return W;
		
		 
	}
	//protected method to rotateRight
	protected AVLVertex rotateRight(AVLVertex T) 
	{
		AVLVertex W=T.left;
		
		W.parent=T.parent;
		T.parent=W;
		T.left=W.right;
		if(W.right!=null) W.right.parent=W;
		W.right=T;
		
		updateHeight(T);
		updateHeight(W);
		updateSize(T);
		updateSize(W);
		
		return W;
	}
	//public method called to perform in-order traversal 
	public void inorder()
	{
		inorder(root);
		System.out.println();
	}
	
	//overload method to perform in-order traversal
	protected void inorder(AVLVertex T)
	{
		if(T==null) return;
		inorder(T.left);
		System.out.print(T.key+" "); //+" "+"Height: "+T.height+" Size: "+T.size
		inorder(T.right);
	}
	
	//public method called to find minimum key value in AVL
	public String findMin() {return findMin(root);}
	//over loaded recursive method to perform findMin
	protected String findMin(AVLVertex T)
	{
	  if(T==null) throw new NoSuchElementException("AVL is empty, no minimum");
	  else if (T.left==null) return T.key;
	  else return findMin(T.left);
	}
	
	
	//public method called to find Maximum key value in AVL
	public String findMax() {return findMax(root);}
	//overloaded recursive method to call findMax
	protected String findMax(AVLVertex T)
	{
		if (T==null) throw new NoSuchElementException("AVL is emoty, no maximum");
		else if(T.right==null) return T.key;
		else return findMax(T.right);
	}
	//method called to search for a value v
	public String search (String v)
	{
			AVLVertex res=search(root, v);
			
			//return res.size;
			return res==null? "null":res.key;
	}
		
	protected AVLVertex search(AVLVertex T, String v)
	{
			if(T==null) return null;                      //not found 
			else if (T.key.compareTo(v)==0) return T;                  // found
			else if(T.key.compareTo(v)<0) return search(T.right, v);   //search to the right 
			else return search(T.left, v);                //search to the left
	}
	
	public String successor(String v)
	{
		AVLVertex vPos =search(root, v);
		return vPos==null? "null": successor(vPos);
	}
	protected String successor(AVLVertex T)
	{
		if(T.right!=null) return findMin(T.right);
		else
		{
			AVLVertex par=T.parent;
			AVLVertex cur=T;
			
			while((par!=null) && (cur.equals(par.right)))
			{
				cur=par;
				par=cur.parent;
			}
			return par==null? "null":par.key;
		}
	}
	
	
	public String predecessor(String v)
	{
		AVLVertex vPos=search(root, v);
		return vPos==null? "null":predecessor(vPos);
	}
	protected String predecessor(AVLVertex T)
	{
		if(T.left!=null) return findMax(T.left);
		else 
		{
			AVLVertex par =T.parent;
			AVLVertex cur =T;
			while((par!=null) && (cur.equals(par.left)))
			{
				cur=par; 
				par=cur.parent;
			}
			return par ==null? "null": par.key;
		}
	}
	
	
	public void delete (String v) {root=delete(root, v);}
	protected AVLVertex delete(AVLVertex T, String v)
	{
		if(T==null) return T;
		else if(T.key.compareTo(v)<0)   //T.key<v
		{	
			T.right=delete(T.right, v);		
		}
		else if(T.key.compareTo(v)>0) //T.key>v
		{	
			T.left=delete(T.left, v);			
		}
		else 
		{		
			//delete node
			if(T.left == null && T.right == null) //leaf node
			{
				
				T.height=-1;  //change the node height
				AVLVertex N=T; //get another pointer point to the node to be deleted
				
				while(N!=null)
				{   
					N=N.parent;
					if(N==null) break;
					if(N.right==null) N.height=Math.max(N.left.height, -1)+1;
					else if(N.left==null) N.height=Math.max(-1, N.right.height)+1;
					else  N.height=Math.max(N.left.height, N.right.height)+1;
					
					N.size--;
					
				}
				
				T=null;
			}
			else if (T.left ==null && T.right !=null)
			{
                T.height--;
				
                AVLVertex N=T; //get another pointer point to the node to be deleted
				
				while(N!=null)
				{   
					N=N.parent;
					if(N==null) break;
					if(N.right==null) N.height=Math.max(N.left.height, -1)+1;
					else if(N.left==null) N.height=Math.max(-1, N.right.height)+1;
					else  N.height=Math.max(N.left.height, N.right.height)+1;
					
					N.size--;
					
				}
				
				T.right.parent=T.parent;
				T=T.right;
				
			}
			else if(T.left !=null && T.right ==null)
			{

                T.height--;
				
                AVLVertex N=T; //get another pointer point to the node to be deleted
				
				while(N!=null)
				{   
					N=N.parent;
					if(N==null) break;
					if(N.right==null) N.height=Math.max(N.left.height, -1)+1;
					else if(N.left==null) N.height=Math.max(-1, N.right.height)+1;
					else  N.height=Math.max(N.left.height, N.right.height)+1;
					
					N.size--;
					
				}
				
				T.left.parent=T.parent;
				T=T.left;
			}
			else 
			{
				String successorV=successor(v);
				T.key=successorV;
				T.right=delete(T.right, successorV);
				
			}
		}
				
		//check balance factor and rotate four cases  
	    if(rebalanceNode(T)!=null) // need to rotate
	    {
	     
	     
	     if(bf(T)==-2 && -1<=bf(T.right) && bf(T.right)<=0) 
	     { 
	    	 T.height=T.height-2;
	    	 
	    	 if(T.right.left!=null)
	    	 T.size=T.size-T.right.size+T.right.left.size;
	    	 else T.size=T.size-T.right.size;
	    	 T.right.size=T.size+T.right.right.size+1;
	    	 
	    	 T=rotateLeft(T);
	    	 
	     }// T.height-math.abs(bf(T)) T.right.height+1
	     else if (bf(T)==-2 && bf(T.right)==1)
	     {
	       T.right.height--;
	       T.right.left.height++;
	       
	      if(T.right.left.right!=null) T.right.size=T.right.size-T.right.left.size+T.right.left.right.size;
	      else T.right.size=T.right.size-T.right.left.size;
	       
	      if(T.right.left.right!=null) T.right.left.size=T.right.left.size-T.right.left.right.size+T.right.size;
	      else T.right.left.size=T.right.left.size+T.right.size;
	      
	       
	       T.right=rotateRight(T.right); 
	       //System.out.println("After right rotation: "+T.right.key+" height is "+T.right.height);
	       T.height=T.height-2;
	       
	       if(T.right.left!=null)
		   T.size=T.size-T.right.size+T.right.left.size;
		   else T.size=T.size-T.right.size;
		   T.right.size=T.size+T.right.right.size+1;
	       
	       T=rotateLeft(T);   
	       //System.out.println("T is "+T.key+" height is "+T.height);
	       
	     }
	     else if(bf(T)==2 && 0<=bf(T.left) && bf(T.left)<= 1) 
	     { 	 
	    	 T.height=T.height-2;
	    	 
	    	 if(T.left.right!=null) T.size=T.size-T.left.size+T.left.right.size;
	    	 else T.size=T.size-T.left.size;
	    	 T.left.size=T.size+T.left.left.size+1;
	    	 
	    	 T=rotateRight(T);
	    	 
	     }
	     else if(bf(T)==2 && bf(T.left)==-1)
	     {
	    	T.left.height--;
		    T.left.right.height++;
		    
		    if(T.left.right.left!=null)
		    T.left.size=T.left.size-T.left.right.size+T.left.right.left.size;
		    else T.left.size=T.left.size-T.left.right.size;
		    
		    if (T.left.right.left!=null)
		    T.left.right.size=T.left.right.size-T.left.right.left.size+T.left.size;
		    else  T.left.right.size=T.left.right.size+T.left.size;
		    
	    	T.left=rotateLeft(T.left);
	    	T.height=T.height-2;
	    	
	    	 if(T.left.right!=null) T.size=T.size-T.left.size+T.left.right.size;
	    	 else T.size=T.size-T.left.size;
	    	 T.left.size=T.size+T.left.left.size+1;
	    	
	    	T=rotateRight(T);
	    	
	     }
	    	       
	    }
		 
		return T;
	}
	
	public int getHeight() {return getHeight(root);}
	protected int getHeight(AVLVertex T)
	{
		if(T==null) return -1; 
		else return Math.max(getHeight(T.left), getHeight(T.right))+1;
	}
	
	public int rank(String v)
	{
		int rank=0;
		rank=rank(root, v);
		//System.out.println("Rank  Start is "+rank);
		return rank;	
	}
	
    protected int rank(AVLVertex T, String v)
	{
		//int rank=0;
		
		if(T==null) return 0;
		else if(v.compareTo(T.key)<=0)   //v<T.key
		{
			//System.out.println("Get the rank of 1 "+T.key);
			return rank(T.left, v);
			//rank=rank(T.left, v);
		}
		else //(v.compareTo(T.key)>0)  //v>T.key
		{
			//System.out.println("Get the rank of 2 "+T.key);
			if(T.left!=null){
				return 1+T.left.size+rank(T.right,v);
			//rank=1+T.left.size+rank(T.right,v);
			}
			else {
				return 1+rank(T.right,v);
				//rank=1+rank(T.right,v);
			}
		}
	}
    	
    
	public boolean isEmpty()
	{
		if(root==null) return true;
		else return false;
	}
	
	
}


class PatientNames {
  // if needed, declare a private data structure here that
  // is accessible to all methods in this class

  // --------------------------------------------
	AVL TM;
	AVL TF;
	HashMap<String, Integer> hm;

  // --------------------------------------------

  public PatientNames() {
    // Write necessary code during construction;
    //
    // write your answer here

    // --------------------------------------------
	  TM=new AVL();
	  TF=new AVL();
	  hm=new HashMap<String, Integer>();
 
    // --------------------------------------------
  }

  void AddPatient(String patientName, int gender) {
    // You have to insert the information (patientName, gender)
    // into your chosen data structure
    //
    // write your answer here
    // -----------------------------------
	if(gender==1) TM.insert(patientName);
	else TF.insert(patientName);
    
	hm.put(patientName, gender); 
	  
	  
    // --------------------------------------------
  }

  void RemovePatient(String patientName) {
    // You have to remove the patientName from your chosen data structure
    //
    // write your answer here

    // --------------------------------------------
	  int gender=hm.get(patientName);
	  if (gender==1)
	  TM.delete(patientName);
	  else TF.delete(patientName);
	  
	  
	  //System.out.println("Removed: "+patientName);
	  hm.remove(patientName);
	  
    // --------------------------------------------
  }
  
  int Query(String START, String END, int gender) {
	    int ans = 0;

	    // You have to answer how many patient name starts
	    // with prefix that is inside query interval [START..END)
	    //
	    // write your answer here
	    // --------------------------------------------
	    
	    //get the returned vertex 
	    
	    if(gender==2){
          ans=TF.rank(END)-TF.rank(START);
	    }
	    else if(gender==1){
          ans=TM.rank(END)-TM.rank(START);
	    }
	    else{
	    	ans=Query(START, END, 1)+Query(START, END, 2);
	    }
	   
	    return ans;  
	  }
  
  

  void run() throws Exception {
    // do not alter this method to avoid unnecessary errors with the automated judging
   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  //InputStreamReader(System.in);
    PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    while (true) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int command = Integer.parseInt(st.nextToken());
      if (command == 0) // end of input
        break;
      else if (command == 1) // AddPatient
        AddPatient(st.nextToken(), Integer.parseInt(st.nextToken()));
      else if (command == 2) // RemovePatient
        RemovePatient(st.nextToken());
      else // if (command == 3) // Query
        pr.println(Query(st.nextToken(), // START
                         st.nextToken(), // END
                         Integer.parseInt(st.nextToken()))); // GENDER
    }
    pr.close(); 
       
	/*  AddPatient("CXXLVNZCR", 1);
	  AddPatient("XPDKYGHCVQMBFHISNIC", 1);
	  AddPatient("RAEGYEKIAOSVJOSGIAEZWVBIN", 1);
	  AddPatient("HCHBDDBLKEGFALA", 2);
	  AddPatient("RDABAHEOPEISLSRA", 2);
	  AddPatient("PTCRVQROAQNKDHXTMBMYNEIR", 2);
	  AddPatient("QHPVGCAK", 2);
	  AddPatient("JASKNVCHKA", 1);
	  AddPatient("IBUIKZ", 2);
	  AddPatient("TOIQRHO", 2);
	  AddPatient("IVPGGDAUQQFNGQVZBWQJSX", 2);
	  AddPatient("LNFWQG", 2);
	  AddPatient("HTGI", 2);
	  AddPatient("TKGBDBMV", 1);
	  AddPatient("MVY", 1);
	  AddPatient("TQYCRBJVHLXKCNIWXNBBXBKSL", 1);
	  AddPatient("AWLYWZCQQWDRUMYFLI", 2);
	  AddPatient("QZGFALCWEXKEFJWRI", 1);
	  AddPatient("IXXWQ", 1);
	  AddPatient("GRQYTIZ", 2);
	  AddPatient("KMAIGJMBTPOQ", 1);
	  AddPatient("VBEMNFPKVVP", 2);
	  AddPatient("NAFEAIHFIHRDJDDDCZ", 1);
	  AddPatient("KEZ", 2);
	  AddPatient("YHBUJHGNGPLINCOQ", 2);
	  AddPatient("ZYQWPAXPWDUPEUAAFMOPAI", 1);
	  AddPatient("JQIGQMOBEDNULKZBEHDVZSFIEGIFY", 2);
	  AddPatient("TDFCRFYIKEJTPYXAINR", 2);
	  AddPatient("KGPFQRWFWWDM", 1);
	  AddPatient("UCITYWVKKVVVFJVS", 2);
	  AddPatient("VCDYZRAGNJMIHY", 1);
	  AddPatient("ANCZ", 1);
	  AddPatient("VXWNPPRMLUKCMAQPCEL", 2);
	  AddPatient("HJABJJNHZYDRYT", 1);
	  AddPatient("KKEDWGCUHGKSAMKMSSKETBYFKGVF", 1);
	  AddPatient("HBFJVKZHAPPYCPBKXQFHRFIOE", 1);
	  AddPatient("YGRBDXSOWUZSQBYBYQACTQNGUZ", 1);
	  AddPatient("JVWOTKTZGVKGNCVVZ", 2);
	  AddPatient("RUKSUSJS", 1);
	  AddPatient("ZOAXXQZFKVQVCQCMRIEOQKZOW", 1);
	  System.out.println(Query("PGA","Z", 1));
	  //RemovePatient("MVY");
	  AddPatient("DTDM", 2);
	  AddPatient("HOWCXOYCFVRJZXUY", 1);
	  System.out.println(Query("GZ", "NCUE", 2));
	  System.out.println(Query("EVB", "M", 2));
	  //RemovePatient("JQIGQMOBEDNULKZBEHDVZSFIEGIFY");
	  System.out.println(Query("JPCQ","V", 1));
	  System.out.println(Query("GQF", "SF", 1));
	  AddPatient("DLTMTDCMMWIBSTBKB", 1);
	  AddPatient("SAEIXDZXEMNYIV", 2);
	  System.out.println(Query("ROX", "TK", 1));
	  System.out.println(Query("AH", "MEU", 2));
	  //RemovePatient("IXXWQ");
	  System.out.println(Query("JU", "SJ", 2));  //7
	  //RemovePatient("KMAIGJMBTPOQ");
	  //RemovePatient("KGPFQRWFWWDM");
	  AddPatient("FBMZSEDVKBJPFNNOBCLWIVEPXBX", 2);
	  //RemovePatient("ANCZ");
	  AddPatient("JIBNGNGCIIBREKLNVEXT", 1);
	  //RemovePatient("VCDYZRAGNJMIHY");
	  AddPatient("BHIW", 2);
	  System.out.println(Query("OFZG", "TEWB", 2));//5
	  //RemovePatient("RDABAHEOPEISLSRA");
	  AddPatient("KWDKDXJLSQYFIXWQC", 1);
	  System.out.println(Query("BSY", "UXXH", 0)); //
	  AddPatient("TPDTKWSQYVCJ", 2);
	  //RemovePatient("QHPVGCAK");
	  System.out.println(Query("G","GMZ", 2));//0
	  //RemovePatient("SAEIXDZXEMNYIV");
	  AddPatient("PANIQKAAZOTOKYLBKSMD", 2);
	  //RemovePatient("ZOAXXQZFKVQVCQCMRIEOQKZOW");
	  AddPatient("JOWSRJPPYDLRA", 1);
	  System.out.println(Query("SEKG", "WVZB", 0));//8
	  System.out.println(Query("KI","VMN", 2));//8
	  System.out.println(Query("OXZ","T", 1));//3
	  //RemovePatient("QZGFALCWEXKEFJWRI");
	  AddPatient("TBAUJYBWFSDTAKANJ", 2);
	  System.out.println(Query("F", "M", 2));//
	  //RemovePatient("XPDKYGHCVQMBFHISNIC");
	  AddPatient("VOROCYMVWBIBOMNHAIHOJWSPRJ", 2);
	  //RemovePatient("TDFCRFYIKEJTPYXAINR");
	  AddPatient("DEPD", 2);
	  AddPatient("VHJXHAWJNRCRDBFVYDPE", 1);
	  System.out.println(Query("IXE", "S", 2)); //5
	  AddPatient("XQCCZ", 1);
	  //RemovePatient("BHIW");
	  //RemovePatient("TBAUJYBWFSDTAKANJ");
	  //RemovePatient("XQCCZ");
	  AddPatient("MLF", 1);
	  System.out.println(Query("JH", "KYN", 1)); //4
	  //RemovePatient("KWDKDXJLSQYFIXWQC");
	  AddPatient("APCKKYQHRVPTXMLSLEFUCJ", 2);
	  //RemovePatient("TPDTKWSQYVCJ");
	  AddPatient("MCDMGEXSCPTBLPPJ", 2);
	  AddPatient("KHHINQBMMWJFCP", 1);
	
	  //System.out.println("Male: ");
	  //TM.inorder();
	  //System.out.println("Female: ");
	  //TF.inorder();*/
	  
  }

  public static void main(String[] args) throws Exception {
    // do not alter this method to avoid unnecessary errors with the automated judging
    PatientNames ps2 = new PatientNames();
    ps2.run();
  }
}
