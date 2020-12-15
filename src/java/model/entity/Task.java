package model.entity;
// Generated Jun 23, 2019 3:17:25 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Task generated by hbm2java
 */
@Entity
@Table(name="task"
    ,catalog="todoapp"
)
public class Task  implements java.io.Serializable {


     private int taskId;
     private Project project;
     private String taskDesc;
     private Integer taskCompleted;

    public Task() {
    }

	
    public Task(int taskId) {
        this.taskId = taskId;
    }
    public Task(int taskId, Project project, String taskDesc, Integer taskCompleted) {
       this.taskId = taskId;
       this.project = project;
       this.taskDesc = taskDesc;
       this.taskCompleted = taskCompleted;
    }
   
     @Id 

    
    @Column(name="task_id", unique=true, nullable=false)
    public int getTaskId() {
        return this.taskId;
    }
    
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="proj_id")
    public Project getProject() {
        return this.project;
    }
    
    public void setProject(Project project) {
        this.project = project;
    }

    
    @Column(name="task_desc")
    public String getTaskDesc() {
        return this.taskDesc;
    }
    
    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    
    @Column(name="task_completed")
    public Integer getTaskCompleted() {
        return this.taskCompleted;
    }
    
    public void setTaskCompleted(Integer taskCompleted) {
        this.taskCompleted = taskCompleted;
    }




}


