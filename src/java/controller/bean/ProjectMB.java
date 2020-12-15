/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;
import dao.ProjectDAO; 
import dao.TaskDAO;
import dao.UserDAO; 
import model.entity.User;
import model.entity.Project; 
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.primefaces.event.RowEditEvent;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;  
import model.entity.Task;
import org.primefaces.context.RequestContext;
import util.ConnectionBean;
/**
 *
 * @author HASANAH
 */
@ManagedBean
@SessionScoped
public class ProjectMB implements Serializable {

    /**
     * Creates a new instance of UserMB
     */
    
    private List < Project > projectsList;  
    private List < Project > searchList;  
    private List < Project > searchByProjTitleList; 
    private List < Project > taskByProject;
    ProjectDAO projectDao = new ProjectDAO();  
    Project project = new Project();  
    Project newproj = new Project();  
    Project retrieveId = new Project();  
    User user= new User();
    private int projId;
    public String projTitle;
    private String projDesc;
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    String SQL,SQL2;
    private Integer dbprojId;
    private String dbprojTitle;
    private String dbprojDesc;
    UserDAO userDao = new UserDAO();
    TaskDAO taskDao = new TaskDAO();  
     
    public Integer getProjId() 
    { return projId; }
    
    public void setProjId(int projId) 
    { this.projId = projId; }

    public String getProjTitle() {
        return this.projTitle;
    }
    
    public void setProjTitle(String projTitle) {
        this.projTitle = projTitle;
    }
    
     public String getProjDesc() {
        return this.projDesc;
    }
    
    public void setProjDesc(String projDesc) {
        this.projDesc = projDesc;
    }
    
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public Project getnewproj() 
    { return newproj; }
    
    public void setnewproj(Project newproj){
        this.newproj = newproj;
    }
   
    public List <Project> getProjects() 
    {   
        projectsList = projectDao.AllProjects(); 
        int count = projectsList.size();
        return projectsList;
    }
     
    public void addProject(User id)  
    {  
        projId = projectDao.getId(); 
        newproj.setProjId(projId); 
        newproj.setProjTitle(projTitle);
        newproj.setProjDesc(projDesc);
        //User user = userDao.getUserById(id);
        newproj.setUser(id);
        projectDao.add(newproj); 
        newproj = new Project(); 
              
        
        System.out.println("Project successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "Project successfully saved.");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        newproj = new Project();  
        
    }  
    public void changeProject(Project project)  
    {  
        this.project = project;  
    }  
    public void UpdateProject(Project project)  
    {  
        String Title = project.getProjTitle();  
        FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Title", Title);  
        RequestContext.getCurrentInstance().showMessageInDialog(message1);  
        projectDao.update(project);  
        System.out.println("project Info successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "Project updated successfully .");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        project = new Project();  
    }  
    public String remove(Project project)  
    {  
        String Title = project.getProjTitle();  
        FacesMessage message3= new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete Item",Title);  
        RequestContext.getCurrentInstance().showMessageInDialog(message3);  
        projectDao.removed(project);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Record deleted successfully");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        project = new Project();
        return "project?faces-redirect=true";
        
    }  
    public void searchbyProjTitle()  
    {  
        searchByProjTitleList = projectDao.SearchByProjTitle(project.getProjTitle());  
        int count = searchByProjTitleList.size();  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Number of Record Selected:", Integer.toString(count));  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
    }  
    public Project getProject()  
    {  
        return project;  
    }  
    public void setProject(Project project)  
    {  
        this.project = project;  
    }  
   
    public List < Project > getProjectsList()  
    {  
        return projectsList;  
    }  
    public void setProjectsList(List < Project > projectsList)  
    {  
        this.projectsList = projectsList;  
    }  
    public List < Project > getSearchList()  
    {  
        return searchList;  
    }  
    public void setSearchList(List < Project > searchList)  
    {  
        this.searchList = searchList;  
    }  
    public List < Project > getSearchByProjTitleList()  
    {  
        return searchByProjTitleList;  
    }  
    public void setSearchByProjTitleList(List < Project > searchByProjTitleList)  
    {  
        this.searchByProjTitleList = searchByProjTitleList;  
    }  
    public void onRowEdit(RowEditEvent event)  
    {  
        FacesMessage msg = new FacesMessage(" Edited Project Title ", ((Project) event.getObject()).getProjTitle());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        Project editedproject = (Project) event.getObject();  
        projectDao.update(editedproject);  
    }  
    public void onCancel(RowEditEvent event)  
    {  
        FacesMessage msg = new FacesMessage("Edit Cancelled");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        projectsList.remove((Project) event.getObject());  
    }  
    
    //login
    
    public void dbData(String projTitle) {
        try {
            connection = ConnectionBean.getConnection();
            statement = connection.createStatement();
            SQL = "SELECT * FROM project where proj_title LIKE ('" + projTitle + "')";
            resultSet = statement.executeQuery(SQL);
            resultSet.next();
            dbprojId = Integer.getInteger(resultSet.getString(1).toString());  
            dbprojTitle = resultSet.getString(2).toString();
            dbprojDesc = resultSet.getString(3).toString();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exception Occured in the process :" + ex);
        }
    }
    
    public String task()
    {
        return "task?faces-redirect=true";
    }
  
//    public UserMB() {
//    }
    public List <Project> getProjectInfo()
    {
        projectsList=projectDao.projectInfo(projTitle);
        return projectsList;
    }

    public  Project getRetrieveId()
    {
        Project project=projectDao.projectInfo2(projTitle);
        return project;
    } 
     public List<Task> getTaskByProject()  
    {  
        Project p= projectDao.getProjectById(projId);
        List task=taskDao.getTask(p);
        return task;
    }  
//    public String getTitle();
//    {
//        projTitle =projectDao.SearchByProjTitle(projTitle)
//        return projTitle;
//    } 
    
    public String viewProject(Project project)
    {
        projId=project.getProjId();
        projTitle=project.getProjTitle();
        projDesc=project.getProjDesc();
        return "task?faces-redirect=true";
    }
    
    public Project retrieveId()
    {
        Project project=projectDao.projectInfo2(projTitle);
        return project;
    }
    
    
}
