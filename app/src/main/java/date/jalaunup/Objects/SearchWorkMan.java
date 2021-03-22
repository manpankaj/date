package date.jalaunup.Objects;
public class SearchWorkMan {
    private String ProjectId;
    private String ProjectField;
    private String ProjectName;
    private String ProjectAddress;
    public SearchWorkMan(){}
    public SearchWorkMan(String ProjectId, String ProjectField, String ProjectName, String ProjectAddress)
    {
        this.ProjectId = ProjectId;
        this.ProjectField = ProjectField;
        this.ProjectName = ProjectName;
        this.ProjectAddress = ProjectAddress;
    }
    public void setProjectId(String ProjectId)
    {
        this.ProjectId = ProjectId;
    }
    public void setProjectField(String ProjectField)
    {
        this.ProjectField = ProjectField;
    }
    public void setProjectName(String ProjectName)
    {
        this.ProjectName = ProjectName;
    }
    public void setProjectAddress(String ProjectAddress)
    {
        this.ProjectAddress = ProjectAddress;
    }
    public String getProjectId()
    {
        return this.ProjectId;
    }
    public String getProjectField(){
        return this.ProjectField;
    }
    public String getProjectName(){
        return this.ProjectName;
    }
    public String getProjectAddress(){
        return this.ProjectAddress;
    }
}
