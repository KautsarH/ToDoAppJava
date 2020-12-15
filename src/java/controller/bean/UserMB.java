/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.bean;

import dao.ProjectDAO;
import javax.faces.bean.ManagedBean;
import java.util.List;
import dao.UserDAO; 
import dao.TaskDAO; 
import model.entity.User; 
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.primefaces.event.RowEditEvent;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;  
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.SessionScoped;
import model.entity.Project;
import org.primefaces.context.RequestContext;
import util.ConnectionBean;
/**
 *
 * @author HASANAH
 */
@ManagedBean
@SessionScoped
public class UserMB implements Serializable {

    /**
     * Creates a new instance of UserMB
     */
    
    private List < User > usersList;  
    private List < User > userInfo;  
    private List < User > searchList;  
    private List < User > searchByUserNameList;
    private String searchByUserName;
    UserDAO userDao = new UserDAO();  
    TaskDAO taskDao = new TaskDAO();
    ProjectDAO projectDao = new ProjectDAO();
    User user = new User();  
    User newuser = new User();  
    private int userId;
    public String userName;
    private String userPass;
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    String SQL,SQL2;
    private Integer dbuserId;
    private String dbuserName;
    private String dbuserPass;
    
    public Integer getUserId() 
    { return userId; }
    
    public void setUserId(int userId) 
    { this.userId = userId; }

    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
     public String getUserPass() {
        return this.userPass;
    }
    
    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
    
    public User getNewuser() 
    { return newuser; }
    
    public void setNewuser(User newuser){
        this.newuser = newuser;
    }
   
    public List <User> getUsers() 
    {   
        usersList = userDao.AllUsers(); 
        int count = usersList.size();
        return usersList;
    }
     
    public String addUser()  
    {  
        userId = userDao.getId(); 
        newuser.setUserId(userId); 
        newuser.setUserName(userName); 
        newuser.setUserPass(userPass); 
        userDao.add(newuser); 
        newuser = new User(); 
              
        
        System.out.println("User successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "User successfully saved.");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        newuser = new User();  
        return "index";
    }  
    public Integer getTotalUser()  
    {  
        return userDao.getTotalUser();  
    } 
    public Integer getTotalProject()  
    {  
        return projectDao.getTotalProject();  
    }
    public Integer getTotalTask()  
    {  
        return taskDao.getTotalTask();  
    }
    
     public void changeUser(User user)  
    {  
        this.user = user;  
    }  
    public void UpdateUser(User user)  
    {  
        String Name = user.getUserName();  
        FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Name", Name);  
        RequestContext.getCurrentInstance().showMessageInDialog(message1);  
        userDao.update(user);  
        System.out.println("User Info successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "User updated successfully .");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        user = new User();  
    }  
    public void deleteUser(User user)  
    {  
        String Name = user.getUserName();  
        //FacesMessage message3= new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete Item",contactName);  
        // RequestContext.getCurrentInstance().showMessageInDialog(message3);  
        userDao.delete(user);  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Record deleted successfully");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
    }  
    public void searchByUserNameList()  
    {  
        searchByUserNameList = userDao.SearchByUserNameList(user.getUserName());  
        int count = searchByUserNameList.size();  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Number of Record Selected:", Integer.toString(count));  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
    }  
    public User getUser()  
    {  
        return user;  
    }  
    public void setUser(User user)  
    {  
        this.user = user;  
    }  
   
    public List < User > getUsersList()  
    {  
        return usersList;  
    }  
    public void setUsersList(List < User > usersList)  
    {  
        this.usersList = usersList;  
    }  
    public List < User > getSearchList()  
    {  
        return searchList;  
    }  
    public void setSearchList(List < User > searchList)  
    {  
        this.searchList = searchList;  
    }  
    public List < User > getSearchByUserNameList()  
    {  
        return searchByUserNameList;  
    }  
    public void setSearchByUserNameList(List < User > userName)  
    {  
        this.searchByUserNameList = userName;  
    }  
    
    public String getSearchByUserName()  
    {  
        return userName;  
    }  
    
    public void setSearchByUserName(String userName)  
    {  
        this.userName = userName;  
    }  
    
    public void onRowEdit(RowEditEvent event)  
    {  
        FacesMessage msg = new FacesMessage(" Edited UserName ", ((User) event.getObject()).getUserName());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        User editeduser = (User) event.getObject();  
        userDao.update(editeduser);  
    }  
    public void onCancel(RowEditEvent event)  
    {  
        FacesMessage msg = new FacesMessage("Edit Cancelled");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        usersList.remove((User) event.getObject());  
    }  
    
    //login
    
    public void dbData(String userName) {
        try {
            connection = ConnectionBean.getConnection();
            statement = connection.createStatement();
            SQL = "SELECT * FROM user where user_name LIKE ('" + userName + "')";
            resultSet = statement.executeQuery(SQL);
            resultSet.next();
            dbuserId = Integer.getInteger(resultSet.getString(1).toString());  
            dbuserName = resultSet.getString(2).toString();
            dbuserPass = resultSet.getString(3).toString();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exception Occured in the process :" + ex);
        }
    }
    
    public String checkValid() {
        dbData(userName);

        if (userName.equalsIgnoreCase(dbuserName)) {

            if (userPass.equals(dbuserPass)) {

                return "project";
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Incorrect Password",
                                "Please enter correct Password"));
                return "failure";
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Password",
                            "Please enter correct username and Password"));
            return "failure";
        }
    }
  
//    public UserMB() {
//    }
    
    public List<User> getUserInfo()
    {
        usersList=userDao.userInfo(userName,userPass);
        return usersList;
    }
    public User retrieveId()
    {
        User user=userDao.userInfo2(userName,userPass);
        return user;
    }
    
    public List<Project> getProjectByUser()  
    {  
        User p= userDao.getUserById(userName);
        List project=projectDao.getProject(p);
        return project;
    }
 
}
