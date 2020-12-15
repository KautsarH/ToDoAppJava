/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;
import dao.ProjectDAO; 
import dao.UserDAO; 
import dao.TaskDAO; 
import model.entity.User;
import model.entity.Project; 
import model.entity.Task;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.primefaces.event.RowEditEvent;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;  
import org.primefaces.context.RequestContext;
import util.ConnectionBean;
/**
 *
 * @author HASANAH
 */
@ManagedBean
@RequestScoped
public class TaskMB implements Serializable {

    /**
     * Creates a new instance of UserMB
     */
    
    private List < Task > tasksList;  
    private List < Task > searchList;  
    private List < Task > searchByTaskTitleList; 
    TaskDAO taskDao = new TaskDAO();  
    Task task = new Task();  
    Task newtask = new Task();  
    Task retrieveId = new Task();
    Project project= new Project();
    ProjectMB pro = new ProjectMB();
    private int taskId;
    private String taskDesc;
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    String SQL,SQL2;
    private Integer dbtaskId;
    private String dbtaskDesc;
    ProjectDAO projectDao = new ProjectDAO();
     
    public Integer getTaskId() 
    { return taskId; }
    
    public void setTaskId(int taskId) 
    { this.taskId = taskId; }
    
     public String getTaskDesc() {
        return this.taskDesc;
    }
    
    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }
    
    public Project getProject() {
        return this.project;
    }
    
    public void setProject(Project project) {
        this.project = project;
    }
    
    public Task getnewtask() 
    { return newtask; }
    
    public void setnewtask(Task newtask){
        this.newtask = newtask;
    }
   
    public List <Task> getTasks() 
    {   
        tasksList = taskDao.AllTasks(); 
        int count = tasksList.size();
        return tasksList;
    }
     
    public void addTask(Project id)  
    {  
        taskId = taskDao.getId(); 
        newtask.setTaskId(taskId); 
        newtask.setTaskDesc(taskDesc);
       // Project project = projectDao.getProjectById(id);
        newtask.setProject(id);
        //newtask.setProject(project);
        taskDao.add(newtask); 
        newtask = new Task(); 
              
        
        System.out.println("Task successfully added.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "Task successfully added.");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        newtask = new Task();  
        //return id;
    }  
    
    public String remove(Task task)  
    {  
        //String Title = project.getProjTitle();  
        //FacesMessage message3= new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete Item",contactName);  
        //RequestContext.getCurrentInstance().showMessageInDialog(message3);  
        taskDao.removed(task);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Record deleted successfully");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        return "task?faces-redirect=true";
    }  
   
    public Task getTask()  
    {  
        return task;  
    }  
    public void setTask(Task task)  
    {  
        this.task = task;  
    }  
   
    public List < Task > getTasksList()  
    {  
        return tasksList;  
    }  
    public void setTasksList(List < Task > tasksList)  
    {  
        this.tasksList = tasksList;  
    }  
//    public List < Task > getSearchList()  
//    {  
//        return searchList;  
//    }  
//    public void setSearchList(List < Task > searchList)  
//    {  
//        this.searchList = searchList;  
//    }  
//    public List < Project > getSearchByProjTitleList()  
//    {  
//        return searchByProjTitleList;  
//    }  
//    public void setSearchByProjTitleList(List < Project > searchByProjTitleList)  
//    {  
//        this.searchByProjTitleList = searchByProjTitleList;  
//    }  
//    public void onRowEdit(RowEditEvent event)  
//    {  
//        FacesMessage msg = new FacesMessage(" Edited Project Title ", ((Project) event.getObject()).getProjTitle());  
//        FacesContext.getCurrentInstance().addMessage(null, msg);  
//        Project editedproject = (Project) event.getObject();  
//        projectDao.update(editedproject);  
//    }  
//    public void onCancel(RowEditEvent event)  
//    {  
//        FacesMessage msg = new FacesMessage("Edit Cancelled");  
//        FacesContext.getCurrentInstance().addMessage(null, msg);  
//        projectsList.remove((Project) event.getObject());  
//    }  
    
    //login
    
//    public void dbData(String projTitle) {
//        try {
//            connection = ConnectionBean.getConnection();
//            statement = connection.createStatement();
//            SQL = "SELECT * FROM project where proj_title LIKE ('" + projTitle + "')";
//            resultSet = statement.executeQuery(SQL);
//            resultSet.next();
//            dbprojId = Integer.getInteger(resultSet.getString(1).toString());  
//            dbprojTitle = resultSet.getString(2).toString();
//            dbprojDesc = resultSet.getString(3).toString();
//            
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            System.out.println("Exception Occured in the process :" + ex);
//        }
//    }
//    
    public String task()
    {
        return "task?faces-redirect=true";
    }
  
//    public UserMB() {
//    }
    
    public Task getRetrieveId()
    {
        Task task=taskDao.taskInfo2(taskDesc);
        return task;
    }
     public Task retrieveId()
    {
        Task task=taskDao.taskInfo2(taskDesc);
        return task;
    }
  
}
