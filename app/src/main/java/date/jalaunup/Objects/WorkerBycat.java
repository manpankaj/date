package date.jalaunup.Objects;
public class WorkerBycat {
    private String WorkerId;
    private String WorkerName;
    private String WorkerMobileNo;
    private String WorkerSubcat;
    public WorkerBycat(){}
    public WorkerBycat(String WorkerId, String WorkerName, String WorkerMobileNo, String WorkerSubcat)
    {
        this.WorkerId = WorkerId;
        this.WorkerName = WorkerName;
        this.WorkerMobileNo = WorkerMobileNo;
        this.WorkerSubcat = WorkerSubcat;
    }
    public void setWorkerId(String WorkerId)
    {
        this.WorkerId = WorkerId;
    }
    public void setWorkerName(String WorkerName)
    {
        this.WorkerName = WorkerName;
    }
    public void setWorkerMobileNo(String WorkerMobileNo)
    {
        this.WorkerMobileNo = WorkerMobileNo;
    }
    public void setWorkerSubcat(String WorkerSubcat)
    {
        this.WorkerSubcat = WorkerSubcat;
    }
    public String getWorkerId()
    {
        return this.WorkerId;
    }
    public String getWorkerName(){
        return this.WorkerName;
    }
    public String getWorkerMobileNo(){
        return this.WorkerMobileNo;
    }
    public String getWorkerSubcat(){
        return this.WorkerSubcat;
    }
}
