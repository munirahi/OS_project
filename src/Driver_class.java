import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Driver_class {
    public static Queue<PCB> Q1 = new LinkedList<>();
    public static ArrayList<PCB> Q2;
    public static int processCounterRR;
    public static int current_time;
public static final int Quantum = 3;
    public static ArrayList<PCB> readyQ ;

        public static void main (String[]args){
            // TODO Auto-generated method stub
            Scanner scanner = new Scanner(System.in);

            int choice;

            do {
                System.out.println("1. Enter process’ information:");
                System.out.println("2. Report detailed information about each process and different scheduling criteria.");
                System.out.println("3. Exit the program.");

                choice = scanner.nextInt();


                switch (choice) {
                    case 1:
                        System.out.print("Number of processes: ");
                        int numProcesses = scanner.nextInt();

                        Queue<PCB> q1= new LinkedList<>();
                        //ArrayList<PCB> q1= new ArrayList<>();
					 ArrayList<PCB> q2= new ArrayList<>();
                        for (int i = 0; i < numProcesses; i++) {

                            System.out.print("Enter priority (1 or 2): ");
                            int priority = scanner.nextInt();
                            System.out.print("Enter arrival time : ");
                            int arrival_time = scanner.nextInt();
                            System.out.print("Enter CPU burst: ");
                            int cpu_burst = scanner.nextInt();
                            scanner.nextLine();

                            PCB process = new PCB(priority, arrival_time, cpu_burst);
                            if (priority == 1){
                                q1.add(process);
                            process.setProcessID("P" + ++processCounterRR);}
                            else
                                q2.add(process);
                        }
                        //Q1 = Round_Robin((Queue<PCB>) q1);

                        Round_Robin(q1);
                        System.out.println(q1);
                         System.out.println(print(Q1));

                        break;
                    case 2:

                        break;

                    case 3:
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }


            } while (choice != 3);


        }


	 /*public static void Round_Robin(ArrayList<PCB> processes) {
         int processINdex = 0;//

     int current_time=0;

     Queue<PCB> readyQueue = new LinkedList<>(processes);

     PCB process = null ;
     while (!readyQueue.isEmpty()) {
          process = readyQueue.poll();

         if (process.getStart_Time() == -1) {
        	 process.setStart_Time(current_time);
         }
                 if (process.getCPU_burst()> Quantum){
                     process.setTemp_CPU_burst(process.getCPU_burst()-Quantum);
                     current_time += Quantum;
                     //System.out.println("num of process: " +processes.size() +"\nID:"+process.getProcessID()+"\nindex:"+ processes.indexOf(process) + " is not done\n");
                    // processes.add(process);
                     readyQueue.add(process);
                 }else {
                    // if (process.getTemp_CPU_burst() <= Quantum) {
                        // System.out.println(process.getProcessID() +" is done \n");
                         process.setTermination_time(current_time + process.getCPU_burst());
                        // process.setTurnaround_time(current_time - process.getArrival_time());
                         current_time += process.getCPU_burst();
                        // process.setWaiting_time(process.getTurnaround_time()-process.getCPU_burst());  //;
                         process.setTemp_CPU_burst(0);

                          //processes.remove(i);
                        // }
                     processCounterRR--;
                 }

         }


 }*/
	 
	 
	  public static void Round_Robin(Queue processes) {
          PCB process;
       current_time=0;
          Queue<PCB> PQueue = new LinkedList<>(processes);
      while (processCounterRR != 0){
                    process =(PCB) PQueue.poll();

          if (process.getStart_Time() == -1) {
              process.setStart_Time(current_time);
          }

          if (process.getTemp_CPU_burst()> 3){
              process.setTemp_CPU_burst((process.getTemp_CPU_burst()-Quantum));
              // System.out.println("num of process: " +processes.size() +"\nID:"+process.getProcessID()+"\nindex:"+ processes.indexOf(process) + " is not done\n");
              current_time += Quantum;
              PQueue.add(process);

              Q1.add(process);

          }

          else {
                //  System.out.println(process.getProcessID() +" is done \n");
                  process.setTermination_time(current_time + process.getTemp_CPU_burst());
                  current_time += process.getTemp_CPU_burst();
                  process.setTurnaround_time(current_time - process.getArrival_time());
                  process.setWaiting_time(process.getTurnaround_time()-process.getCPU_burst());  //;
                  process.setTemp_CPU_burst(0);
                  if(process.getTemp_CPU_burst()<=3){
                      Q1.add(process);// since it's the first time it enters the thing
                  }
                  PQueue.remove(process);
                  processCounterRR--;
          }}

      }










public static String print(Queue<PCB> Q){
    PCB process ;
    int ff= Q.size();
    String output="[ ";
    while (!Q.isEmpty()){
        process = Q.poll();
        output += process.getProcessID()+ " | ";
    }
    output += "]";
    return output ;
}



	 
	 


}
