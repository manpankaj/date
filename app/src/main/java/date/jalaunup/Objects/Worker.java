package date.jalaunup.Objects;
public class Worker {
    private String workerId;
    private String workerName;
    private String workerMobileNo;
    private String workerAddress;
    public Worker(){}
    public Worker(String workerId, String workerName,String workerMobileNo,String workerAddress)
    {
        this.workerId = workerId;
        this.workerName = workerName;
        this.workerMobileNo = workerMobileNo;
        this.workerAddress = workerAddress;
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
    public void setWorkerAddress(String workerEmail)
    {
        this.workerAddress = workerAddress;
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
    public String getWorkerAddress(){
        return this.workerAddress;
    }
}
