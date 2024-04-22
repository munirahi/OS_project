import java.util.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;


public class Driver_class {

        public static Queue<PCB> Q1 = new LinkedList<>();
        public static ArrayList<PCB> Q2= new ArrayList<>();

    //to test
    public static  Queue<PCB> q1 = new LinkedList<>();
    public static  Queue<PCB> q2_temp = new LinkedList<>();

    public static Queue<PCB> readyQForAll =new LinkedList<>();
        public static int processCounterRR;
        public static int current_time;
        public static final int Quantum = 3;
        public static ArrayList<PCB> readyQ;
        public static Queue<PCB> ComQ1 = new LinkedList<>();
        public static Queue<PCB> ComQ2 = new LinkedList<>();
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scanner = new Scanner(System.in);
        int choice;
        int numProcesses = 0;
        int pID;
       // Queue<PCB> q1 = new LinkedList<>();
        ArrayList<PCB> q2 = new ArrayList<>();

        //to test
       // Queue<PCB> q2_temp = new LinkedList<>();
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

                        System.out.print("Enter priority (1 or 2) of P" + pID + ": ");
                        int priority = scanner.nextInt();
                        System.out.print("Enter arrival time of P" + pID + ": ");
                        int arrival_time = scanner.nextInt();
                        System.out.print("Enter CPU burst of P" + pID + ": ");
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
                            q2_temp.add(process);
                            ComQ2.add(process);//for generate report
                        }
                        //  }
                    }//end for loop /report
                    //Q1 = Round_Robin((Queue<PCB>) q1);
                    Round_Robin(q1);
                    SJF(q2);
                    // System.out.println(q1);
                    // System.out.println((Q1).toString());

                    break;

                case 2:
//int count=0;
                    Queue<PCB> A = new LinkedList<>();
                    Queue<PCB> A1 = new LinkedList<>();



                    if (Q1.isEmpty() && Q2.isEmpty()) {
                        System.out.print("Please Enter Process first");
                    } else {
                        try {
                            PCB process;

                            FileWriter fileWriter = new FileWriter("Report.txt");
                            fileWriter.write("Schedule Order: [");
                            System.out.print("Schedule Order: [");

                            while (!Q1.isEmpty()) {
                                process = Q1.poll();
                                A1.add(process);
                                System.out.print("P" + process.getProcessID() + " | ");
                                fileWriter.write("P" + process.getProcessID() + " | ");

                            }
                            if( !Q2.isEmpty()){
                            for (int i = 0; i < Q2.size(); i++) {
                                process = Q2.get(i);
                                System.out.print("P" + process.getProcessID() + " | ");//
                                fileWriter.write("P" + process.getProcessID() + " | ");//

                            }
                            }
                            fileWriter.write("]\n");
                            System.out.print("]\n");

                            //Print detailed information for each process
                            System.out.println("\n Process Information:\n");
                            fileWriter.write("\n Process Information:\n");
                            while (!A1.isEmpty()) {
                               // Round_Robin(A1);
                                //SJF(q2);
                                process = A1.poll();
                                A.add(process);

                                //A.add(process);
                           
                                printProcessDetails( process,  fileWriter);
                            }
                                              for (int l = 0; l < Q2.size(); l++) {

                                process = (PCB) Q2.get(l);
                                process.setStart_Time(current_time);
                                process.setTermination_time(current_time + process.getCPU_burst());
                                process.setTurnaround_time(process.getWaiting_time() + process.getCPU_burst());
                                process.setResponse_time(process.getStart_Time() - process.getArrival_time());
                                current_time += process.getCPU_burst();
                                process.setWaiting_time(process.getTurnaround_time() - process.getCPU_burst());
                                printProcessDetails(process, fileWriter);

                            }

                            // Calculate and print average turnaround time, waiting time, and response time
                            double totalTurnaroundTime1 = 0;
                            double totalWaitingTime1 = 0;
                            double totalResponseTime1 = 0;
                            double totalTurnaroundTime2 = 0;
                            double totalWaitingTime2 = 0;
                            double totalResponseTime2 = 0;

                            while (!A.isEmpty()) {
                                process = A.poll();

                                totalTurnaroundTime1 += process.getTurnaround_time();
                                totalWaitingTime1 += process.getWaiting_time();
                                totalResponseTime1 += process.getResponse_time();
                                int i = 0;
                                i++;
                                numProcesses += i;

                            }
                            for (int i = 0; i < Q2.size(); i++) {
                                process = Q2.get(i);
                                totalTurnaroundTime2 += process.getTurnaround_time();
                                totalWaitingTime2 += process.getWaiting_time();
                                totalResponseTime2 += process.getResponse_time();
                                numProcesses += i;
                            }

                            double totalTurnaroundTime = totalTurnaroundTime2 + totalTurnaroundTime1;
                            double totalWaitingTime = totalWaitingTime2 + totalWaitingTime1;
                            double totalResponseTime = totalResponseTime2 + totalResponseTime1;
                            double averageTurnaroundTime = totalTurnaroundTime / numProcesses;
                            double averageWaitingTime = totalWaitingTime / numProcesses;
                            double averageResponseTime = totalResponseTime / numProcesses;

                            fileWriter.write("\nAverage Turnaround Time: " + averageTurnaroundTime + "\n");
                            System.out.print("\nAverage Turnaround Time: " + averageTurnaroundTime + "\n");
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
                case 4:

                   ((List<PCB>) q1).sort(Comparator.comparingInt(PCB::getArrival_time));
                    ((List<PCB>) q2_temp).sort(Comparator.comparingInt(PCB::getArrival_time));
                    readyQ(q1,q2_temp);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 3);

    }


    public static void readyQ(Queue Q1InReady,Queue Q2InReady){
       PCB P_from1InReady = (PCB) Q1InReady.poll();
       PCB  P_from2InReady = (PCB) Q2InReady.poll();

        if(P_from1InReady.getArrival_time() <= P_from2InReady.getArrival_time()){
           calc(P_from1InReady);
            readyQForAll.add(P_from1InReady);
           if (P_from1InReady.getTemp_CPU_burst()!= 0){
               addToBeginning(Q1InReady,P_from1InReady);

           }
        } else {
            int expectedEndTime = current_time +  P_from2InReady.getTemp_CPU_burst();
            if( ((PCB)Q1InReady.peek()).getArrival_time() <= expectedEndTime){ // not sure about the =
                // we should trim the sjf here

            }

            readyQForAll.add(P_from2InReady);
            calc(P_from2InReady);
            if (P_from2InReady.getTemp_CPU_burst()!= 0){
                addToBeginning(Q2InReady,P_from2InReady);

            }
        }


    }

    public static <PCB> void addToBeginning(Queue<PCB> queue, PCB element) {
        // Convert the Queue to a LinkedList
        LinkedList<PCB> list = new LinkedList<>(queue);
        // Add the element to the beginning of the LinkedList
        list.addFirst(element);
        // Clear the original Queue
        queue.clear();
        // Add all elements from the LinkedList back to the Queue
        queue.addAll(list);
    }

    public static void calc(PCB p){

        if (p.getStart_Time() == -1) {
            p.setStart_Time(current_time);
        }
        if (p.getTemp_CPU_burst() > 3) {
            p.setTemp_CPU_burst((p.getTemp_CPU_burst() - Quantum));
            current_time += Quantum;
            q1.add(p);

            Q1.add(p);

        } else {

            p.setTermination_time(current_time + p.getTemp_CPU_burst());
            current_time += p.getTemp_CPU_burst();
            p.setTurnaround_time(current_time - p.getArrival_time());

            p.setWaiting_time(p.getTurnaround_time() - p.getCPU_burst());
            if (p.getTemp_CPU_burst() <= 3) {
                Q1.add(p);
            }
            p.setTemp_CPU_burst(0);
            q1.remove(p);


        }
    }

    public static void Round_Robin(Queue processes) {
        PCB process;
        current_time = 0;

        Queue<PCB> readyQueue = new LinkedList<>(processes);
 ((List<PCB>) readyQueue).sort((p1, p2) -> Integer.compare(p1.getArrival_time(), p2.getArrival_time()));

        while (!readyQueue.isEmpty()) {
            process = (PCB) readyQueue.poll();

            if (process.getStart_Time() == -1) {
                process.setStart_Time(current_time);
            }
            if (process.getTemp_CPU_burst() > 3) {
                process.setTemp_CPU_burst((process.getTemp_CPU_burst() - Quantum));
                current_time += Quantum;
                readyQueue.add(process);

                Q1.add(process);

            } else {

                process.setTermination_time(current_time + process.getTemp_CPU_burst());
                current_time += process.getTemp_CPU_burst();
                process.setTurnaround_time(current_time - process.getArrival_time());

                process.setWaiting_time(process.getTurnaround_time() - process.getCPU_burst());  //;

                if (process.getTemp_CPU_burst() <= 3) {
                    Q1.add(process);
                }
                process.setTemp_CPU_burst(0);
                readyQueue.remove(process);
                processCounterRR--;

            }
        }

    }

    public static void SJF(ArrayList processes){
        int current_time= 0; //will be updated later
        Collections.sort(processes);
        
        for (int i=0 ; processes.size()>i ;i++){
            PCB process = (PCB)processes.get(i);
           
            //turnaround
            process.setTermination_time(current_time + process.getCPU_burst());
            process.setTurnaround_time(process.getTermination_time() - process.getArrival_time());
            
            current_time += process.getCPU_burst();
            //waiting time
            process.setWaiting_time(process.getTurnaround_time() - process.getCPU_burst());
           
            Q2.add(process);
        }
        
    }


    public static void printProcessDetails(PCB process, FileWriter fileWriter) throws IOException {

        fileWriter.write("\nProcess ID: " + process.getProcessID()+" | ");
        System.out.print("\nProcess ID: " + process.getProcessID()+" | ");

        fileWriter.write(" Priority: " + process.getPriority()+" | ");
        System.out.print(" Priority: " + process.getPriority()+" | ");

        fileWriter.write(" Arrival Time: " + process.getArrival_time()+" | ");
        System.out.print(" Arrival Time: " + process.getArrival_time()+" | ");

        fileWriter.write(" CPU Burst Time: " + process.getCPU_burst()+" | ");
        System.out.print(" CPU Burst Time: " + process.getCPU_burst()+" | ");

        fileWriter.write(" Start Time: " + process.getStart_Time()+" | ");
        System.out.print(" Start Time: " + process.getStart_Time()+" | ");

        fileWriter.write(" Termination Time: " + (process.getTermination_time())+" | ");
        System.out.print(" Termination Time: " + (process.getTermination_time())+" | ");

        fileWriter.write(" Turnaround Time: " + (process.getTurnaround_time())+" | ");
        System.out.print(" Turnaround Time: " + (process.getTurnaround_time())+" | ");

        fileWriter.write(" Waiting Time: " + (process.getWaiting_time())+" | ");
        System.out.print(" Waiting Time: " + (process.getWaiting_time())+" | ");

        fileWriter.write(" Response Time: " + (process.getResponse_time()));
        System.out.print(" Response Time: " + (process.getResponse_time()));

        fileWriter.write("]");
        System.out.print("]");

    }


    }


/*

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

*/
