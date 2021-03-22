package date.jalaunup.Objects;
public class WorkerBycat {
    private String workerId;
    private String workerName;
    private String workerMobileNo;
    private String workerSubcat;
    public WorkerBycat(){}
    public WorkerBycat(String workerId, String workerName, String workerMobileNo, String workerSubcat)
    {
        this.workerId = workerId;
        this.workerName = workerName;
        this.workerMobileNo = workerMobileNo;
        this.workerSubcat = workerSubcat;
    }
    public void setWorkerId(String workerId)
    {
        this.workerId = workerId;
    }
    public void setWorkerName(String workerName)
    {
        this.workerName = workerName;
    }
    public void setWorkerMobileNo(String workerMobileNo)
    {
        this.workerMobileNo = workerMobileNo;
    }
    public void setWorkerSubcat(String workerSubcat)
    {
        this.workerSubcat = workerSubcat;
    }
    public String getWorkerId()
    {
        return this.workerId;
    }
    public String getWorkerName(){
        return this.workerName;
    }
    public String getWorkerMobileNo(){
        return this.workerMobileNo;
    }
    public String getWorkerSubcat(){
        return this.workerSubcat;
    }
}
