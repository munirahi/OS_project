import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Driver_class {
    public static Queue<PCB> Q1 = new LinkedList<>();
    public static ArrayList<PCB> Q2;
    public static int processCounterRR;
    public static int current_time;
public static final int Quantum = 3;
    public static ArrayList<PCB> readyQ ;
 public static Queue<PCB> ComQ1 = new LinkedList<>();
    public static Queue<PCB> ComQ2 = new LinkedList<>();
	
        public static void main (String[]args){
            // TODO Auto-generated method stub
            Scanner scanner = new Scanner(System.in);
           int choice;
        int numProcesses = 0;
        int pID;

            do {
                System.out.println("1. Enter process’ information:");
                System.out.println("2. Report detailed information about each process and different scheduling criteria.");
                System.out.println("3. Exit the program.");

                choice = scanner.nextInt();


                switch (choice) {
                    case 1:
                        System.out.print("Number of processes: ");
                    numProcesses = scanner.nextInt();

                    Queue<PCB> q1 = new LinkedList<>();
                    // ArrayList<PCB> q1= new ArrayList<>();
                    ArrayList<PCB> q2 = new ArrayList<>();
                    // for (int i = 0; i < numProcesses; i++) {
                    for (pID = 1; pID <= numProcesses; pID++) {//REPORT

                        System.out.print("Enter priority (1 or 2): ");
                        int priority = scanner.nextInt();
                        System.out.print("Enter arrival time : ");
                        int arrival_time = scanner.nextInt();
                        System.out.print("Enter CPU burst: ");
                        int cpu_burst = scanner.nextInt();
                        scanner.nextLine();

                        PCB process = new PCB(priority, arrival_time, cpu_burst);
                        process.setProcessID(pID);//convert it to int report
                        if (priority == 1) {
                            q1.add(process);
                            ComQ1.add(process);//for generate report
                            // process.setProcessID("P" + ++processCounterRR);
                        } else {
                            q2.add(process);
                            ComQ2.add(process);//for generate report
                        }
                        //  }
                    }//end for loop /report
                    //Q1 = Round_Robin((Queue<PCB>) q1);
                    Round_Robin(q1);
                    System.out.println(q1);
                    System.out.println(print(Q1));
                    
                        break;
                    case 2:
  int count=0; 
              Queue<PCB> p = new LinkedList<>();
              Queue<PCB> p1 = new LinkedList<>();
              Queue<PCB> p2 = new LinkedList<>();


                 if (ComQ1.isEmpty() && ComQ2.isEmpty())
                    System.out.print("Please Enter Process first");
                 else{
                  
                   try {
                PCB process ;
            FileWriter fileWriter = new FileWriter("Report.txt");
            // Print scheduling order
            // System.out.print("Scheduling Order: [");
            fileWriter.write("Scheduling Order: [");
            System.out.print("Scheduling Order: [");

            while (!ComQ1.isEmpty()) {
                
                process = ComQ1.poll();
                p1.add(process);
                count++;
                
                System.out.print( process.getProcessID() + " | ");
                fileWriter.write( process.getProcessID() + " | ");
            }
            while (!ComQ2.isEmpty()) {
                
                process = ComQ2.poll();
                p2.add(process);
                 count++;
                 System.out.print("p"+ count + " | ");
                 fileWriter.write("p"+count+ " | ");
               // System.out.print( process.getProcessID() + " | ");
               // fileWriter.write( process.getProcessID() + " | ");
            }
           
            fileWriter.write("]\n");
            System.out.print("]\n");
            
            
            
            //Print detailed information for each process
            System.out.println("Process Information:\n");
            fileWriter.write("Process Information:\n");
            while (!p1.isEmpty()) {
                process = p1.poll();
                ComQ1.add(process);
                printProcessDetails(process, fileWriter);
            }
            //System.out.println("Process Information:\n");
           // fileWriter.write("Process Information:\n");
            while (!p2.isEmpty()) {
                process = p2.poll();
                ComQ2.add(process);
                printProcessDetails(process, fileWriter);
            }
       
            
            // Calculate and print average turnaround time, waiting time, and response time
            double totalTurnaroundTime1 = 0;
            double totalWaitingTime1 = 0;
            double totalResponseTime1 = 0;
            double totalTurnaroundTime2 = 0;
            double totalWaitingTime2 = 0;
            double totalResponseTime2 = 0;
            
             while (!ComQ1.isEmpty()) {
                process = ComQ1.poll();
                p.add(process);
                totalTurnaroundTime1 += process.getTurnaround_time();
                totalWaitingTime1 += process.getWaiting_time();
                totalResponseTime1 += process.getResponse_time();
            }
              while (!ComQ2.isEmpty()) {
                process = ComQ2.poll();
                p.add(process);
                totalTurnaroundTime2 += process.getTurnaround_time();
                totalWaitingTime2 += process.getWaiting_time();
                totalResponseTime2 += process.getResponse_time();
            }
         double totalTurnaroundTime=totalTurnaroundTime2+totalTurnaroundTime1;
         double totalWaitingTime=totalWaitingTime2+totalWaitingTime1;
         double totalResponseTime=totalResponseTime2+totalResponseTime1;

            double averageTurnaroundTime = totalTurnaroundTime / numProcesses;
            double averageWaitingTime = totalWaitingTime / numProcesses;
            double averageResponseTime = totalResponseTime / numProcesses;

            fileWriter.write("Average Turnaround Time: " + averageTurnaroundTime + "\n");
            System.out.print("Average Turnaround Time: " + averageTurnaroundTime + "\n");
            fileWriter.write("Average Waiting Time: " + averageWaitingTime + "\n");
            System.out.print("Average Waiting Time: " + averageWaitingTime + "\n");
            fileWriter.write("Average Response Time: " + averageResponseTime + "\n");
            System.out.print("Average Response Time: " + averageResponseTime + "\n");
            fileWriter.close();
        } 
            
            catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
  }  }
          
                    break;

                case 3:
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }


            } while (choice != 3);


        }

	/*
	 public static void Round_Robin(ArrayList<PCB> processes) {
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
                     process.setCPU_burst(process.getCPU_burst()-Quantum);
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
                         process.setCPU_burst(0);

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

          }else {
              if (process.getTemp_CPU_burst() <= 3) {
                  //  System.out.println(process.getProcessID() +" is done \n");
                  process.setTermination_time(current_time + process.getCPU_burst());
                  current_time += process.getTemp_CPU_burst();
                  process.setTurnaround_time(current_time - process.getArrival_time());
                  current_time += process.getCPU_burst();
                  process.setWaiting_time(process.getTurnaround_time()-process.getCPU_burst());  //;
                  process.setTemp_CPU_burst(0);
                  if(process.getCPU_burst()<=3){
                      Q1.add(process);// since it's the first time it enters the thing
                  }
                  PQueue.remove(process);
                  processCounterRR--;
              }


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

public static void printProcessDetails(PCB process, FileWriter fileWriter) throws IOException {

        fileWriter.write("Process ID: " + process.getProcessID() + "\n");
        System.out.print("Process ID: " + process.getProcessID() + "\n");

        fileWriter.write("Priority: " + process.getPriority() + "\n");
        System.out.print("Priority: " + process.getPriority() + "\n");

        fileWriter.write("Arrival Time: " + process.getArrival_time() + "\n");
        System.out.print("Arrival Time: " + process.getArrival_time() + "\n");

        fileWriter.write("CPU Burst Time: " + process.getCPU_burst() + "\n");
        System.out.print("CPU Burst Time: " + process.getCPU_burst() + "\n");

        fileWriter.write("Start Time: " + process.getStart_Time() + "\n");
        System.out.print("Start Time: " + process.getStart_Time() + "\n");

        fileWriter.write("Termination Time: " + (process.getTermination_time()) + "\n");
        System.out.print("Termination Time: " + (process.getTermination_time()) + "\n");

        fileWriter.write("Turnaround Time: " + (process.getTurnaround_time()) + "\n");
        System.out.print("Turnaround Time: " + (process.getTurnaround_time()) + "\n");
       

        fileWriter.write("Waiting Time: " + (process.getWaiting_time()) + "\n");
        System.out.print("Waiting Time: " + (process.getWaiting_time())+"\n");

        fileWriter.write("Response Time: " + (process.getResponse_time()) + "\n");
        System.out.print("Response Time: " + (process.getResponse_time()) + "\n");

     
      //  fileWriter.close();

    }

	 
	 


}
