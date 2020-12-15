package model.entity;
// Generated Jun 23, 2019 3:17:25 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name="user"
    ,catalog="todoapp"
)
public class User  implements java.io.Serializable {


     private int userId;
     private String userName;
     private String userPass;
     private Set<Project> projects = new HashSet<Project>(0);

    public User() {
    }

	
    public User(int userId) {
        this.userId = userId;
    }
    public User(int userId, String userName, String userPass, Set<Project> projects) {
       this.userId = userId;
       this.userName = userName;
       this.userPass = userPass;
       this.projects = projects;
    }
   
     @Id 

    
    @Column(name="user_id", unique=true, nullable=false)
    public int getUserId() {
        return this.userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }

    
    @Column(name="user_name", length=15)
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    
    @Column(name="user_pass", length=15)
    public String getUserPass() {
        return this.userPass;
    }
    
    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<Project> getProjects() {
        return this.projects;
    }
    
    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }




}


