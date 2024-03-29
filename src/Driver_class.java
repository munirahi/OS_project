import java.util.ArrayList;
import java.util.Scanner;

public class Driver_class {
    public static ArrayList<PCB> Q1;
    public static ArrayList<PCB> Q2;
    public static int processCounterRR;
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

                        ArrayList<PCB> q1 = null;
                        ArrayList<PCB> q2 = null;
                        for (int i = 0; i < numProcesses; i++) {

                            System.out.print("Enter priority (1 or 2): ");
                            int priority = scanner.nextInt();
                            System.out.print("Enter arrival time : ");
                            int arrival_time = scanner.nextInt();
                            System.out.print("Enter CPU burst: ");
                            int cpu_burst = scanner.nextInt();
                            scanner.nextLine();
                            PCB process = new PCB(priority, arrival_time, cpu_burst);
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


            } while (choice != 3);


        }


    public static void Round_Robin(ArrayList processes) {
            int processINdex = 0;//
            int Psize =processes.size() ;
            PCB process;
        int cpu_time;
        int current_time=0;

//
//        while (!processes.isEmpty()){
//            process = (PCB) processes.get(processINdex);
//            if (process.getCPU_burst()> 3){
//                process.setCPU_burst(process.getCPU_burst()-3);
//                processes.add(process);
//                processINdex++;
//            }else {
//                int p = process.getCPU_burst();
//
//                process.setCPU_burst(0);
//                processCounterRR--;
//                if(processes.remove(p)){
//
//                } }}


            for (int i=0 ; processes.size()>i ;i++){
               process = (PCB) processes.get(i);

                    if (process.getTemp_CPU_burst()> 3){
                        process.setCPU_burst(process.getCPU_burst()-3);

                    }else {
                        if (process.getTemp_CPU_burst() <= 3) {
                            process.setTermination_time(current_time + process.getCPU_burst());
                            process.setTurnaround_time(current_time - process.getArrival_time());
                            current_time += process.getCPU_burst();
                            process.setWaiting_time(process.getTurnaround_time()-process.getCPU_burst());  //;
                           process.setCPU_burst(0);
                            processes.remove(i);}
                        processCounterRR--;
                    }
            }
    }


}