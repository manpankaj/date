package date.jalaunup.Objects;
public class Employment {
    private String projectId;
    private String projectName;
    private String projectAddress;
    private String projectTehsil;
    
    public Employment(){}
    public Employment(String projectId, String projectName, String projectAddress, String projectTehsil)
    {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectAddress = projectAddress;
        this.projectTehsil = projectTehsil;
    }
    public void setProjectId(String projectId){this.projectId = projectId;}
    public void setProjectName(String projectName){this.projectName = projectName;}
    public void setProjectAddress(String projectAddress){this.projectAddress = projectAddress;}
    public void setProjectTehsil(String projectTehsil){this.projectTehsil = projectTehsil;}
    public String getProjectId(){return this.projectId;}
    public String getProjectName(){return this.projectName;}
    public String getProjectAddress(){return this.projectAddress;}
    public String getProjectTehsil(){
        return this.projectTehsil;
    }
}
