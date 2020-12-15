package dao;  
import java.util.List;  
import java.util.ArrayList;  
import org.hibernate.Query;  
import org.hibernate.Session;  
import util.HibernateUtil;  
import model.entity.Project;  
import model.entity.Task;
import javax.faces.application.FacesMessage;  
import org.primefaces.context.RequestContext;  
/** 
 * 
 * @author Raichand 
 */  
public class TaskDAO  
{  
    private Task task;  
    private Task newtask;  
    private List < Task > DaoAllTasks;  
    private List < Task > DaoSearchTaskList;  
    //Session session;  
    public List < Task > AllTasks()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            DaoAllTasks = session.createCriteria(Task.class).list();  
            int count = DaoAllTasks.size();  
            // FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "List Size", Integer.toString(count));//Debugging Purpose  
            //RequestContext.getCurrentInstance().showMessageInDialog(message1);  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
        return DaoAllTasks;  
    }  
    public Integer getId()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        String hql = "select max(U.id) from Task U";  
        Query query = session.createQuery(hql);  
        List < Integer > results = query.list();  
        Integer taskId = 1;  
        if (results.get(0) != null)  
        {  
            taskId = results.get(0) + 1;  
        }  
        session.flush();  
        session.close();  
        return taskId;  
    }  
    public Integer getTotalTask()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        String hql = "select U.id from Task U";  
        Query query = session.createQuery(hql); 
        List < Project > totalList = new ArrayList < > ();
        totalList = query.list();  
           int count = totalList.size();
//        List < Integer > results = query.list();  
//        Integer taskId;  
//          
//            taskId = results.get(0);  
        
        session.flush();  
        session.close();  
        return count;  
    }
//    public List < Task > SearchByTaskTitle(String ProjTitle)  
//    {  
//        Session session = HibernateUtil.getSessionFactory().openSession();  
//        List < Project > daoSearchList = new ArrayList < > ();  
//        try  
//        {  
//            session.beginTransaction();  
//            Query qu = session.createQuery("From Project U where U.projTitle =:projTitle"); //Project is the entity not the table  
//            qu.setParameter("projTitle", ProjTitle);  
//            daoSearchList = qu.list();  
//            int count = daoSearchList.size();  
//            session.getTransaction().commit();  
//        }  
//        catch (Exception e)  
//        {  
//            e.printStackTrace();  
//            session.getTransaction().rollback();  
//        }  
//        finally  
//        {  
//            session.close();  
//        }  
//        return daoSearchList;  
//    }  
    public void add(Task newtask)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String Id = Integer.toString(newtask.getTaskId());  
            //begin a transaction  
            session.beginTransaction();  
            session.merge(newtask);  
            session.flush();  
            System.out.println("NewTask saved, id: " +  newtask.getTaskId());  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void removed(Task task)  
    {  
        Session a = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            //String name = project.getProjTitle();  
            a.beginTransaction();  
            a.delete(task);  
            a.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            a.getTransaction().rollback();  
        }  
        a.close();  
    }  
public List getTask(Project d)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();  
        List < Project > daoSearchList = new ArrayList < > ();  
        Project project = new Project();
        try  
        {  session.beginTransaction();  
            Query qu = session.createQuery("From Task where project =:title"); //User is the entity not the table  
            qu.setParameter("title", d);  
             
            daoSearchList = qu.list(); 
            project = (Project)qu.uniqueResult(); 
            int count = daoSearchList.size();  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        finally  
        {  
            session.close();  
        }  
        return daoSearchList;  
    }

    public Task taskInfo2(String d)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();  
        List < Task > daoSearchList = new ArrayList < > ();  
        Task task = new Task();
        try  
        {  session.beginTransaction();  
            Query qu = session.createQuery("From Task where taskDesc =:desc"); //User is the entity not the table  
            qu.setParameter("desc", d);  
             
            daoSearchList = qu.list(); 
            task = (Task)qu.uniqueResult(); 
            int count = daoSearchList.size();  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        finally  
        {  
            session.close();  
        }  
        return task;  
    }

} 