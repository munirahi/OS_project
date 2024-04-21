
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;

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
                            Queue<PCB> q1 = new LinkedList<>();
                                                ArrayList<PCB> q2 = new ArrayList<>();



            do {
                System.out.println("1. Enter process' information:");
                System.out.println("2. Report detailed information about each process and different scheduling criteria.");
                System.out.println("3. Exit the program.");

                choice = scanner.nextInt();


                switch (choice) {
                    case 1:
                        System.out.print("Number of processes: ");
                    numProcesses = scanner.nextInt();

                    // ArrayList<PCB> q1= new ArrayList<>();
                    // for (int i = 0; i < numProcesses; i++) {
                    for (pID = 1; pID <= numProcesses; pID++) {//REPORT

                        System.out.print("Enter priority (1 or 2) of P" + pID +": ");
                        int priority = scanner.nextInt();
                        System.out.print("Enter arrival time of P" + pID +": ");
                        int arrival_time = scanner.nextInt();
                        System.out.print("Enter CPU burst of P" + pID +": ");
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
                    SJF(q2);
                    System.out.println(q1);
                    System.out.println((Q1).toString());
                    
                        break;

               
                         case 2:
                       //int count=0; 
                    Queue<PCB> p = new LinkedList<>();
                    Queue<PCB> A = new LinkedList<>();
                    Queue<PCB> A1 = new LinkedList<>();

                    if (q1.isEmpty() && q2.isEmpty()) {
                        System.out.print("Please Enter Process first");
                    } else {
                        try {

                            PCB process;
                            PCB process1;

                            FileWriter fileWriter = new FileWriter("Report.txt");
                            fileWriter.write("Schedule Order: [");
                            System.out.print("Schedule Order: [");
                            while (!q1.isEmpty()) {
                                process = q1.poll();
                                A1.add(process);
                                if (!q1.isEmpty()) {
                                    process1 = q1.poll();
                                    A1.add(process1);

                                    if (process.getArrival_time() < process1.getArrival_time()) {
                                        if (process.getCPU_burst() <= 3) {
                                            System.out.print("P" + process.getProcessID() + " | ");
                                            fileWriter.write("P" + process.getProcessID() + " | ");
                                            process.setTemp_CPU_burst(0);
                                            q1.remove(process);
                                            q1.add(process1);

                                        } else if (process.getCPU_burst() > 3) {
                                            System.out.print("P" + process.getProcessID() + " | ");
                                            fileWriter.write("P" + process.getProcessID() + " | ");
                                            int a = process.getCPU_burst();
                                            process.setTemp_CPU_burst(a - 3);
                                            A1.add(process);
                                            q1.add(process1);

                                            continue;
                                        }
                                        if (process.getCPU_burst() <= 0) {
                                            q1.remove(process);
                                            q1.add(process1);

                                        }

                                    } else {
                                        if (process1.getCPU_burst() <= 3) {
                                            System.out.print("P" + process1.getProcessID() + " | ");
                                            fileWriter.write("P" + process1.getProcessID() + " | ");
                                            process1.setTemp_CPU_burst(0);
                                            q1.remove(process1);
                                            A1.add(process);
                                            q1.add(process);

                                        } else if (process1.getCPU_burst() > 3) {
                                            System.out.print("P" + process1.getProcessID() + " | ");
                                            fileWriter.write("P" + process1.getProcessID() + " | ");
                                            int a = process1.getCPU_burst();
                                            process1.setTemp_CPU_burst(a - 3);
                                            A1.add(process1);
                                            q1.add(process);
                                            q1.add(process);

                                            continue;
                                        }
                                        if (process1.getCPU_burst() <= 0) {
                                            q1.remove(process1);
                                            A1.add(process);

                                        }
                                    }
                                } else {
                                    System.out.print("P" + process.getProcessID() + " | ");
                                    fileWriter.write("P" + process.getProcessID() + " | ");
                                    A1.add(process);
                                    q1.remove(process);

                                }
                            }
                             while (!q2.isEmpty()) {
                                int i = 0;
                                process = q2.get(i);
                                q2.remove(i);

                                if (i != q2.size()) {

                                    process1 = q2.get(i);
                                    q2.remove(i);

                                    if (process.getArrival_time() < process1.getArrival_time()) {
                                        if (process.getCPU_burst() < process1.getCPU_burst()) {

                                            System.out.print("P" + process.getProcessID() + " | ");//
                                            fileWriter.write("P" + process.getProcessID() + " | ");//
                                            A.add(process);
                                            A.add(process1);
                                            q2.add(process1);
                                        } else {
                                            System.out.print("P" + process1.getProcessID() + " | ");//
                                            fileWriter.write("P" + process1.getProcessID() + " | ");//  
                                            A.add(process);
                                            A.add(process1);
                                            q2.add(process);

                                        }
                                    } else if (process.getCPU_burst() < process1.getCPU_burst()) {

                                        System.out.print("P" + process.getProcessID() + " | ");//
                                        fileWriter.write("P" + process.getProcessID() + " | ");//
                                        A.add(process);
                                        A.add(process1);
                                        q2.add(process1);
                                    } else {
                                        System.out.print("P" + process1.getProcessID() + " | ");//
                                        fileWriter.write("P" + process1.getProcessID() + " | ");//  
                                        A.add(process);
                                        A.add(process1);
                                        q2.add(process);

                                    }
                                } else{
                                    System.out.print("P" + process.getProcessID() + " | ");//
                                    fileWriter.write("P" + process.getProcessID() + " | ");//
                                    A.add(process);

                                    break;
                                }
                                    if (i == q2.size() - 1) {
                                        break;
                                    }
                                }
                            

                            fileWriter.write("]\n");
                            System.out.print("]\n");

                            //Print detailed information for each process
                            while (!A1.isEmpty()) {
                                int time = 0;
                                int t=0;
                                int ter=0;
                                int st=0;
                                int c=0;
                                process = A1.poll();
                                ComQ1.add(process);
                                System.out.println("\n Process Information:\n");
                                fileWriter.write("\n Process Information:\n");
                                time += process.getCPU_burst();
                                process.setStart_Time(time);
                                 t += process.getArrival_time();
                                process.setTermination_time(time + t);
                                ter += process.getTermination_time();
                                 st += process.getStart_Time();
                                process.setResponse_time(st - t);
                                 c += process.getCPU_burst();
                                process.setWaiting_time(ter - c);
                                process.setTurnaround_time(ter - t);
                                printProcessDetails(process, fileWriter);

                            }
                            //System.out.println("Process Information:\n");
                            // fileWriter.write("Process Information:\n");
                            while (!A.isEmpty()) {
                                int time = 0;
                                int t=0;
                                int ter=0;
                                int st=0;
                                int c=0;
                                process = A.poll();
                                ComQ2.add(process);
                                System.out.println("\n Process Information:\n");
                                fileWriter.write("\n Process Information:\n");
                                time += process.getCPU_burst();
                                process.setStart_Time(time);
                                 t += process.getArrival_time();
                                process.setTermination_time(time + t);
                                 ter += process.getTermination_time();
                                 st += process.getStart_Time();
                                process.setResponse_time(st - t);
                                 c += process.getCPU_burst();
                                process.setTurnaround_time(ter - t);
                                process.setWaiting_time(ter - c);
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
                            double totalTurnaroundTime = totalTurnaroundTime2 + totalTurnaroundTime1;
                            double totalWaitingTime = totalWaitingTime2 + totalWaitingTime1;
                            double totalResponseTime = totalResponseTime2 + totalResponseTime1;

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
                        } catch (IOException e) {
                            System.out.println("An error occurred while writing to the file.");
                        }
                    }

              

                    break;


                case 3:
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }


            } while (choice != 3);


        }

     
     
      public static void Round_Robin(Queue processes) {
          PCB process;
          current_time=0;
          
          Queue<PCB> readyQueue = new LinkedList<>(processes);
          
      while (!readyQueue.isEmpty()){
                    process =(PCB) readyQueue.poll();

          if (process.getStart_Time() == -1) {
              process.setStart_Time(current_time);
          }
          if (process.getTemp_CPU_burst()> 3){
              process.setTemp_CPU_burst((process.getTemp_CPU_burst()-Quantum));
              current_time += Quantum;
              readyQueue.add(process);

              Q1.add(process);

          }else {
              
                  process.setTermination_time(current_time + process.getTemp_CPU_burst());
                  current_time += process.getTemp_CPU_burst();
                  process.setTurnaround_time(current_time - process.getArrival_time());
                 
                  process.setWaiting_time(process.getTurnaround_time()-process.getCPU_burst());  //;
                  process.setTemp_CPU_burst(0);
                  if(process.getCPU_burst()<=3){
                      Q1.add(process);
                  }
                  readyQueue.remove(process);
                  processCounterRR--;
              


          }}

      }



      public static void SJF(ArrayList processes){
        int current_time=0;
        Collections.sort(processes);
        int turnaround_time=0;
        int waiting_time=0;
        int averageTurnAround =0;
        int averageWaiting =0;
        ArrayList<PCB> allProcess = new ArrayList<>();
        for (int i=0 ; processes.size()>i ;i++){
            PCB process = (PCB)processes.get(i);
            System.out.println("CPU burst of P" + (i+1) +": "  + process.getCPU_burst());
            System.out.println("Arrival time of P" + (i+1) +": " +process.getArrival_time());
            //turnaround
            process.setTermination_time(current_time + process.getCPU_burst());
            process.setTurnaround_time(process.getTermination_time() - process.getArrival_time());
            System.out.println("Turnaround time of P"+ (i+1) +": " +process.getTurnaround_time());
            current_time += process.getCPU_burst();
            //waiting time
            process.setWaiting_time(process.getTurnaround_time() - process.getCPU_burst());
            System.out.println("waiting time of P"+ (i+1) +": " + process.getWaiting_time());
            allProcess.add(process);
        }
         //average turnaround time
        //  for(int i=0 ; allProcess.size()>i ;i++){
        //     PCB process = (PCB)allProcess.get(i);
        //     turnaround_time+=process.getTurnaround_time();
        //     averageTurnAround = turnaround_time/allProcess.size();
        //     waiting_time += process.getWaiting_time();
        //     averageWaiting = waiting_time/allProcess.size();
        //  }
        // System.out.println("Average turnaround time: "+ averageTurnAround);
        // System.out.println("Average waiting time: "+ averageWaiting);
    }













public static void printProcessDetails(PCB process, FileWriter fileWriter) throws IOException {

       fileWriter.write("Process ID: " + process.getProcessID());
        System.out.print("Process ID: " + process.getProcessID());

        fileWriter.write(" Priority: " + process.getPriority());
        System.out.print(" Priority: " + process.getPriority());

        fileWriter.write(" Arrival Time: " + process.getArrival_time());
        System.out.print(" Arrival Time: " + process.getArrival_time());

        fileWriter.write(" CPU Burst Time: " + process.getCPU_burst());
        System.out.print(" CPU Burst Time: " + process.getCPU_burst());

        fileWriter.write(" Start Time: " + process.getStart_Time());
        System.out.print(" Start Time: " + process.getStart_Time());

        fileWriter.write(" Termination Time: " + (process.getTermination_time()));
        System.out.print(" Termination Time: " + (process.getTermination_time()));

        fileWriter.write(" Turnaround Time: " + (process.getTurnaround_time()));
        System.out.print(" Turnaround Time: " + (process.getTurnaround_time()));

        fileWriter.write(" Waiting Time: " + (process.getWaiting_time()));
        System.out.print(" Waiting Time: " + (process.getWaiting_time()));

        fileWriter.write(" Response Time: " + (process.getResponse_time()));
        System.out.print(" Response Time: " + (process.getResponse_time()));

        fileWriter.write("]");
        System.out.print("]");

    }

     
     


}
