
import java.util.ArrayList;
import java.util.Scanner;


public class driverClass {

	 public static ArrayList<PCB> Q1;  
	 public static ArrayList<PCB> Q2;
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(System.in);
		
		int choice;
		
		do {
			System.out.println("1. Enter process’ information:");
			System.out.println("2. Report detailed information about each process and different scheduling criteria.");
			System.out.println("3. Exit the program.");
			
			choice = scanner.nextInt();
			
			
			switch(choice) {
			case 1:
				  System.out.print("Number of processes: ");
				    int numProcesses = scanner.nextInt();
				     
				     ArrayList<PCB> q1=null;  
					 ArrayList<PCB> q2=null;
				    for (int i = 0; i < numProcesses; i++) {
				    	
				      System.out.print("Enter priority (1 or 2): ");
				      int priority = scanner.nextInt();
				      System.out.print("Enter arrival time : ");
				      int arrival_time = scanner.nextInt();
				      System.out.print("Enter CPU burst: ");
				      int cpu_burst = scanner.nextInt();
				      scanner.nextLine();  
				      PCB process = new PCB ( priority, arrival_time, cpu_burst);
				      if (priority == 1) 
				    	  q1.add(process);
				      else 
				    	  q2.add(process);
				    }
				    
				break;
			case 2:
				
				break;
				
			case 3:
				break;
			 
			  default:
		          System.out.println("Invalid choice. Please try again.");
			}
			
			
			
			
		}while(choice != 3);
		

	}
	
	

	
	

}
