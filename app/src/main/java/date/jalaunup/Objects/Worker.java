package date.jalaunup.Objects;

public class Worker {

    private String workerId;
    private String workerName;
    private String workerMobileNo;
    private String workerEmail;

    public Worker(){}

    public Worker(String complainId, String complainNo,String complainText,String complainLodgedBy)
    {
        this.workerId = complainId;
        this.workerName = complainNo;
        this.workerMobileNo = complainText;
        this.workerEmail = complainLodgedBy;
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

    public void setWorkerEmail(String workerEmail)
    {
        this.workerEmail = workerEmail;
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

    public String getWorkerEmail(){
        return this.workerEmail;
    }
}
