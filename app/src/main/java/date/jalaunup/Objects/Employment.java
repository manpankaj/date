package date.jalaunup.Objects;
public class Employment {
    private String employerId;
    private String employerName;
    private String employerMobileNo;
    private String employerAddress;
    public Employment(){}
    public Employment(String employerId, String employerName, String employerMobile, String employerAddress)
    {
        this.employerId = employerId;
        this.employerName = employerName;
        this.employerMobileNo = employerMobile;
        this.employerAddress = employerAddress;
    }
    public void setEmployerId(String employerId)
    {
        this.employerId = employerId;
    }
    public void setEmployerName(String employerName)
    {
        this.employerName = employerName;
    }
    public void setEmployerMobileNo(String employerMobileNo){this.employerMobileNo = employerMobileNo;}
    public void setEmployerAddress(String employerAddress){this.employerAddress = employerAddress;}
    public String getEmployerId()
    {
        return this.employerId;
    }
    public String getEmployerName(){
        return this.employerName;
    }
    public String getEmployerMobileNo(){
        return this.employerMobileNo;
    }
    public String getEmployerAddress(){
        return this.employerAddress;
    }
}
