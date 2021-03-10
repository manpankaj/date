package date.jalaunup.Objects;
public class Employer {
    private String EmployerId;
    private String EmployerName;
    private String EmployerMobileNo;
    private String EmployerEmail;
    private String EmployerAddress;
    public Employer(){}
    public Employer(String EmployerId, String EmployerName,String EmployerMobileNo,String EmployerEmail, String EmployerAddress )
    {
        this.EmployerId = EmployerId;
        this.EmployerName = EmployerName;
        this.EmployerMobileNo = EmployerMobileNo;
        this.EmployerEmail = EmployerEmail;
        this.EmployerAddress = EmployerAddress;
    }
    public void setEmployerId(String EmployerId)
    {
        this.EmployerId = EmployerId;
    }
    public void setEmployerName(String EmployerName)
    {
        this.EmployerName = EmployerName;
    }
    public void setEmployerMobileNo(String EmployerMobileNo)
    {
        this.EmployerMobileNo = EmployerMobileNo;
    }
    public void setEmployerEmail(String EmployerEmail)
    {
        this.EmployerEmail = EmployerEmail;
    }
    public void setEmployerAddress(String EmployerAddress)
    {
        this.EmployerAddress = EmployerAddress;
    }
    public String getEmployerId()
    {
        return this.EmployerId;
    }
    public String getEmployerName(){
        return this.EmployerName;
    }
    public String getEmployerMobileNo(){
        return this.EmployerMobileNo;
    }
    public String getEmployerEmail(){
        return this.EmployerEmail;
    }
    public String getEmployerAddress()
    {
        return this.EmployerAddress;
    }

}
