// Copy paste this Java Template and save it as "PatientNames.java"
import java.util.*;
import java.io.*;

// write your matric number here:A0160257J
// write your name here: Li JiaYao
// write list of collaborators here:
// year 2017 hash code: DZAjKugdE9QiOQKGFbut (do NOT delete this line)

class Patient{
	
	private String name;
	private int gender; 
	
	
	public Patient(String name, int gender){
		this.name=name; 
		this.gender=gender; 
		
	}
	
	public String getName() {return name;}
	public int getGender() {return gender;}
	
	public void setName(String name) {this.name=name;}
	public void setGender(int gender) {this.gender=gender;}
}



class PatientNames {
  // if needed, declare a private data structure here that
  // is accessible to all methods in this class

  // --------------------------------------------

  TreeMap<String, Patient> patientMap;

  // --------------------------------------------

  public PatientNames() {
    // Write necessary code during construction;
    //
    // write your answer here

    // --------------------------------------------

   patientMap=new TreeMap<String, Patient>();

    // --------------------------------------------
  }

  void AddPatient(String patientName, int gender) {
    // You have to insert the information (patientName, gender)
    // into your chosen data structure
    //
    // write your answer here

    // --------------------------------------------
	Patient newPatient=new Patient(patientName, gender);
    patientMap.put(patientName, newPatient);

    // --------------------------------------------
  }

  void RemovePatient(String patientName) {
    // You have to remove the patientName from your chosen data structure
    //
    // write your answer here

    // --------------------------------------------
    patientMap.remove(patientName);


    // --------------------------------------------
  }

  int Query(String START, String END, int gender) {
    int ans = 0;

    // You have to answer how many patient name starts
    // with prefix that is inside query interval [START..END)
    //
    // write your answer here

    // --------------------------------------------
    for(Map.Entry<String, Patient> entry: patientMap.entrySet())
    {
    	String patientName=entry.getKey();
    	Patient patient=entry.getValue();
    	
    	//check gender code
    	boolean genderFlag=false;
    	if(gender==0) genderFlag=true;
    	else if(gender==patient.getGender()) genderFlag=true;
    	
    	if(genderFlag)
    	 {
    		if (START.compareTo(patient.getName())<=0)
    		{
                if(END.compareTo(patient.getName())>0) ans++;
    		}
    	}
    		
    	
    	
    	
    }

    // --------------------------------------------
    //System.out.println(ans);
    return ans;
  }

  void run() throws Exception {
    // do not alter this method to avoid unnecessary errors with the automated judging
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
    
	  /*
	  AddPatient("JANE", 2);
	  AddPatient("JOSHUA", 1);
	  AddPatient("MARIA", 2);
	  AddPatient("PETER", 1);
	  Query("PET", "STE", 1);
	  Query("PET", "STE", 2);
	  Query("JA", "PETI", 0);
	  Query("JA", "PETA", 0);
	  Query("JOSH", "PET", 1);
	  Query("JANE", "MARIA", 2);
	  Query("JANE", "MARIANA", 2);
	  */
  }

  public static void main(String[] args) throws Exception {
    // do not alter this method to avoid unnecessary errors with the automated judging
    PatientNames ps2 = new PatientNames();
    ps2.run();
  }
}
