// Copy paste this Java Template and save it as "EmergencyRoom.java"
import java.util.*;
import java.io.*;

// write your matric number here:
// write your name here:
// write list of collaborators here:
// year 2017 hash code: oIxT79fEI2IQdQqvg1rx (do NOT delete this line)

class Patient3 {
	
	private String name;
	private int level; 
	private int time;
	
	public Patient3(String name, int level, int time){
		this.name=name; 
		this.level=level; 
		this.time=time;
	}
	
	public String getName() {return name;}
	public int getLevel() {return level;}
	public int getTime(){return time;}
	
	public void setLevel(int level) {this.level=level;}
	public void setTime(int time) {this.time=time;}
	
	@Override
    public String toString() {
	        return this.getName();
	
     }
	
	
}

class patientComparator implements Comparator<Patient3>
{

	
	public int compare(Patient3 p1, Patient3 p2) {
		// TODO Auto-generated method stub
		if(p1.getLevel()==p2.getLevel()) return p1.getTime()-p2.getTime();
		else return p2.getLevel()-p1.getLevel();
	
	}
	
}

class EmergencyRoom {
  // if needed, declare a private data structure here that
  // is accessible to all methods in this class

	TreeMap<Patient3, String> patientMap;
	
  public EmergencyRoom() {
    // Write necessary code during construction
    //
    // write your answer here
    patientMap=new TreeMap<Patient3, String>(new patientComparator());
    

  }
  

  void ArriveAtHospital(String patientName, int emergencyLvl) {
    // You have to insert the information (patientName, emergencyLvl)
    // into your chosen data structure
    //
    // write your answer here
    Patient3 newPatient=new Patient3(patientName, emergencyLvl, patientMap.size());
    patientMap.put(newPatient,patientName);
   

  }

  void UpdateEmergencyLvl(String patientName, int incEmergencyLvl) {
    // You have to update the emergencyLvl of patientName to
    // emergencyLvl += incEmergencyLvl
    // and modify your chosen data structure (if needed)
    //
    // write your answer here



  }

  void Treat(String patientName) {
    // This patientName is treated by the doctor
    // remove him/her from your chosen data structure
    //
    // write your answer here
	//System.out.println("treat name:  "+patientMap.firstKey().getName());
    patientMap.remove(patientMap.firstKey());


  }

  String Query() {
    String ans = "The emergency room is empty";

    // You have to report the name of the patient that the doctor
    // has to give the most attention to currently. If there is no more patient to
    // be taken care of, return a String "The emergency room is empty"
    //
    // write your answer here
    if(!patientMap.isEmpty())
    ans=patientMap.firstKey().getName();
    //System.out.println(ans);
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
   
    
	 /* ArriveAtHospital("GRACE", 31);
	  ArriveAtHospital("THOMAS", 55);
	  ArriveAtHospital("MARIA", 42);
	  Query();
	  ArriveAtHospital("CINDY", 77);
	  Query();
	  Treat(Query());
	  Query();
	  Treat(Query());
	  Query();
	  Treat(Query());
	  Query();
	  Treat(Query());
	  Query();
	  */
    
  }

  public static void main(String[] args) throws Exception {
    // do not alter this method
    EmergencyRoom ps13 = new EmergencyRoom();
    ps13.run();
  }
}
