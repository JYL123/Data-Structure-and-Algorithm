
// Copy paste this Java Template and save it as "EmergencyRoom.java"
import java.util.*;
import java.io.*;

// write your matric number here:
// write your name here:
// write list of collaborators here:
// year 2017 hash code: oIxT79fEI2IQdQqvg1rx (do NOT delete this line)

class Patient2{
	
	private String name;
	private int level; 
	private int time;
	
	public Patient2(String name, int level, int time){
		this.name=name; 
		this.level=level; 
		this.time=time;
	}
	
	public String getName() {return name;}
	public int getLevel() {return level;}
	public int getTime(){return time;}
	
	public void setLevel(int level) {this.level=level;}
	public void setTime(int time) {this.time=time;}
}


class EmergencyRoom {
  // if needed, declare a private data structure here that
  // is accessible to all methods in this class
     
	  PriorityQueue<Patient2> pq; 
	
  public EmergencyRoom() {
    // Write necessary code during construction
    //
    // write your answer here
     pq=new PriorityQueue<Patient2>(11, newComparator);


  }
  
  public static Comparator<Patient2> newComparator=new Comparator<Patient2>(){
	  
	  public int compare(Patient2 p1, Patient2 p2)
	  {
		  if(p1.getLevel()>p2.getLevel()) return (int)p2.getLevel()-p1.getLevel();
		  else if(p1.getLevel()==p2.getLevel()) return p1.getTime()-p2.getTime();
		  
		  else return p2.getLevel()-p1.getLevel();
	  }
	  
  };

  void ArriveAtHospital(String patientName, int emergencyLvl) {
    // You have to insert the information (patientName, emergencyLvl)
    // into your chosen data structure
    //
    // write your answer here
	  
    Patient2 patient2=new Patient2(patientName, emergencyLvl, pq.size());
    pq.add(patient2);

  }

  void UpdateEmergencyLvl(String patientName, int incEmergencyLvl) {
    // You have to update the emergencyLvl of patientName to
    // emergencyLvl += incEmergencyLvl
    // and modify your chosen data structure (if needed)
    //
    // write your answer here
	//for(Patient2 pt2: pq) System.out.print(pt2.getName()+" ");
	 Iterator<Patient2>  it=pq.iterator();
	 Patient2 toBeUpdate=new Patient2("", 0,0);
	 while(it.hasNext())
	 {
	    Patient2 p2=it.next();
	    if(p2.getName().equals(patientName))  toBeUpdate=p2;
	 }
	 pq.remove(toBeUpdate);
	 toBeUpdate.setLevel(toBeUpdate.getLevel()+incEmergencyLvl);
	 pq.add(toBeUpdate);
  }

  void Treat(String patientName) {
    // This patientName is treated by the doctor
    // remove him/her from your chosen data structure
    //
    // write your answer here
    Iterator<Patient2>  it=pq.iterator();
    Patient2 toBeRemove=new Patient2("", 0,0);
    while(it.hasNext())
    {
    	Patient2 p2=it.next();
    	if(p2.getName().equals(patientName))  toBeRemove=p2;
    }

    pq.remove(toBeRemove);
  }

  String Query() {
    String ans = "The emergency room is empty";

    // You have to report the name of the patient that the doctor
    // has to give the most attention to currently. If there is no more patient to
    // be taken care of, return a String "The emergency room is empty"
    //
    // write your answer here
    if(!pq.isEmpty()) ans=pq.peek().getName();
    

    return ans;
  }

  void run() throws Exception {
    // do not alter this method

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    int numCMD = Integer.parseInt(br.readLine()); // note that numCMD is >= N
    while (numCMD-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int command = Integer.parseInt(st.nextToken());
      switch (command) {
        case 0: ArriveAtHospital(st.nextToken(), Integer.parseInt(st.nextToken())); break;
        case 1: UpdateEmergencyLvl(st.nextToken(), Integer.parseInt(st.nextToken())); break;
        case 2: Treat(st.nextToken()); break;
        case 3: pr.println(Query()); break;
      }
    }
    pr.close();
    

	  

  }

  public static void main(String[] args) throws Exception {
    // do not alter this method
    EmergencyRoom ps12 = new EmergencyRoom();
    ps12.run();
  }
}
