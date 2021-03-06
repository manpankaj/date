package date.jalaunup.Objects;
public class Worker {
    private String workerId;
    private String workerName;
    private String workerMobileNo;
    private String workerEmail;
    private String workerAge;
    private String workerSex;
    private String workerField;
    private String workerWork;
    private String workerExp;
    public Worker(){}
    public Worker(String workerId, String workerName,String workerMobileNo,String workerEmail,
                  String workerAge, String workerSex,String workerField,String workerWork,String workerExp)
    {
        this.workerId = workerId;
        this.workerName = workerName;
        this.workerMobileNo = workerMobileNo;
        this.workerEmail = workerEmail;
        this.workerAge = workerAge;
        this.workerSex = workerSex;
        this.workerField = workerField;
        this.workerWork = workerWork;
        this.workerExp = workerExp;
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
    public void setworkerAge(String workerAge)
    {
        this.workerAge = workerAge;
    }
    public void setWorkerSex(String workerSex)
    {
        this.workerSex = workerSex;
    }
    public void setWorkerField(String workerField)
    {
        this.workerField = workerField;
    }
    public void setWorkerWork(String workerWork)
    {
        this.workerWork = workerWork;
    }
    public void setWorkerExp(String workerExp)
    {
        this.workerExp = workerExp;
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
    public String getWorkerAge()
    {
        return this.workerAge;
    }
    public String getWorkerSex(){
        return this.workerSex;
    }
    public String getWorkerField(){
        return this.workerField;
    }
    public String getWorkerWork(){
        return this.workerWork;
    }
    public String getWorkerExp(){
        return this.workerExp;
    }
}
