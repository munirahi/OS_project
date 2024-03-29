
public class PCB {
	 String ProcessID ;
	   int priority ;
	   int arrival_time ; // not sure of the type
	   int CPU_burst ;
	   int Start_Time;
	   int termination_time;
	   int Turnaround_time ;
	   int Waiting_time;
	   int  Response_time;
	   int processCount = 0;
	   public PCB( int priority, int arrival_time, int CPU_burst) {
		   
		    processCount++;
		    this.ProcessID = "P" + processCount; 
		    this.priority = priority;
		    this.arrival_time = arrival_time;
		    this.CPU_burst = CPU_burst;
		    this.Start_Time = 0;
		    this.termination_time = 0;
		    this.Turnaround_time = 0;
		    this.Waiting_time = 0;
		    this.Response_time = 0;
		   
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
		Turnaround_time = turnaround_time;
	}
	public int getWaiting_time() {
		return Waiting_time;
	}
	public void setWaiting_time(int waiting_time) {
		Waiting_time = waiting_time;
	}
	public int getResponse_time() {
		return Response_time;
	}
	public void setResponse_time(int response_time) {
		Response_time = response_time;
	}
	   
	   


	}
