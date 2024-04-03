
public class PCB {
	 String ProcessID ;
	   private int priority ;
	private int arrival_time ; // not sure of the type
	private final int CPU_burst ;
	private int temp_CPU_burst;
	private int Start_Time;
	private int termination_time;
	private int Turnaround_time ;
	private int Waiting_time;
	private  int  Response_time;
	private int processCount = 0;
	   public PCB( int priority, int arrival_time, int CPU_burst) {
		   
		   // processCount++; // wrong cause it keeps being 1
		  //  this.ProcessID = "P" + processCount;
		    this.priority = priority;
		    this.arrival_time = arrival_time;
		    this.CPU_burst = CPU_burst;
			this.temp_CPU_burst = CPU_burst;
		    this.Start_Time = -1;
		    this.termination_time = 0;
		    this.Turnaround_time = 0;
		    this.Waiting_time = 0;
		    this.Response_time = 0;
		   
	   }
		public void setProcessID(String processID) {
			ProcessID = processID;
		}

		public String getProcessID() {
			return ProcessID;
		}

		public int getProcessCount() {
			return processCount;
		}
		public int getStart_Time() {
			return Start_Time;
		}
		public void setStart_Time(int start_Time) {
			Start_Time = start_Time;
		}
		public int getTermination_time() {
			return termination_time;
		}
		public void setTermination_time(int termination_time) {
			this.termination_time = termination_time;
		}
		public int getTurnaround_time() {
			return Turnaround_time;
		}
		public void setTurnaround_time(int turnaround_time) {
			if (turnaround_time<0){
				Turnaround_time = arrival_time;
			}else
			Turnaround_time = turnaround_time;
		}
		public int getWaiting_time() {
			return Waiting_time;
		}
		public void setWaiting_time(int waiting_time) {
						if(waiting_time< 0){
							Waiting_time =0;
						}else
			Waiting_time = waiting_time;
		}
		public int getResponse_time() {
			return Response_time;
		}
		public void setResponse_time(int response_time) {
			Response_time = response_time;
		}

		public int getCPU_burst() {
			return CPU_burst;
		}

		public int getTemp_CPU_burst() {
			return temp_CPU_burst;
		}

		public void setTemp_CPU_burst(int CPU_burst) {
			this.temp_CPU_burst = CPU_burst;
		}

		public int getArrival_time() {
			return arrival_time;
		}

		public void setArrival_time(int arrival_time) {
			this.arrival_time = arrival_time;
		}
		@Override
		public String toString() {
			return "PCB [ProcessID=" + ProcessID + ", priority=" + priority + ", arrival_time=" + arrival_time
					+ ", CPU_burst=" + CPU_burst + ", temp_CPU_burst=" + temp_CPU_burst + ", Start_Time=" + Start_Time
					+ ", termination_time=" + termination_time + ", Turnaround_time=" + Turnaround_time
					+ ", Waiting_time=" + Waiting_time + ", Response_time=" + Response_time + ", processCount="
					+ processCount + "]\n";
		}
	public int getPriority() {
        return priority;
    }


	}
