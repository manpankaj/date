package date.jalaunup.Objects;
public class AdminSearchEmployerWork {
    private String projectId;
    private String projectField;
    private String projectName;
    private String projectAddress;

    public AdminSearchEmployerWork(){}
    public AdminSearchEmployerWork(String projectId, String projectField, String projectName, String projectAddress)
    {
        this.projectId = projectId;
        this.projectField = projectField;
        this.projectName = projectName;
        this.projectAddress = projectAddress;
    }
    public void setProjectId(String projectId){this.projectId = projectId;}
    public void setProjectField(String projectField){this.projectField = projectField;}
    public void setProjectName(String projectName){this.projectName = projectName;}
    public void setProjectAddress(String projectAddress){this.projectAddress = projectAddress;}
    public String getProjectId(){return this.projectId;}
    public String getProjectField(){return this.projectField;}
    public String getProjectName(){return this.projectName;}
    public String getProjectAddress(){return this.projectAddress;}
}
