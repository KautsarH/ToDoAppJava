package dao;  
import java.util.List;  
import java.util.ArrayList;  
import org.hibernate.Query;  
import org.hibernate.Session;  
import util.HibernateUtil;  
import model.entity.Project;  
import javax.faces.application.FacesMessage;  
import model.entity.User;
import org.primefaces.context.RequestContext;  
/** 
 * 
 * @author Raichand 
 */  
public class ProjectDAO  
{  
    private Project project;  
    private Project newproj;  
    private List < Project > DaoAllProjects;  
    private List < Project > DaoSearchProjectList;  
    //Session session;  
    public List < Project > AllProjects()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            DaoAllProjects = session.createCriteria(Project.class).list();  
            int count = DaoAllProjects.size();  
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
        return DaoAllProjects;  
    }  
    public Integer getId()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        String hql = "select max(U.id) from Project U";  
        Query query = session.createQuery(hql);  
        List < Integer > results = query.list();  
        Integer projId = 1;  
        if (results.get(0) != null)  
        {  
            projId = results.get(0) + 1;  
        }  
        session.flush();  
        session.close();  
        return projId;  
    }  
    
    public Integer getTotalProject()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        String hql = "select U.id from Project U";  
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
    
    public List < Project > SearchByProjTitle(String ProjTitle)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        List < Project > daoSearchList = new ArrayList < > ();  
        try  
        {  
            session.beginTransaction();  
            Query qu = session.createQuery("From Project U where U.projTitle =:projTitle"); //Project is the entity not the table  
            qu.setParameter("projTitle", ProjTitle);  
            daoSearchList = qu.list();  
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
    public void add(Project newproj)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String Id = Integer.toString(newproj.getProjId());  
            //begin a transaction  
            session.beginTransaction();  
            session.merge(newproj);  
            session.flush();  
            System.out.println("NewProject saved, id: " +  newproj.getProjId());  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void removed(Project project)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String name = project.getProjTitle();  
            session.beginTransaction();  
            session.delete(project);  
            session.flush();
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void update(Project project)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            session.update(project);  
            session.flush();  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    
    public Project getProjectById(int id)
       {   Project project = new Project();
            Session session = HibernateUtil.getSessionFactory().openSession();  
        String hql = "from Project where projId= :pid";  
        Query query = session.createQuery(hql);
        query.setParameter("pid",id);
        project = (Project)query.uniqueResult();
        
        session.close();  
        return project; 
       }
    
    public List projectInfo(String d)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();  
        List < Project > daoSearchList = new ArrayList < > ();  
        try  
        {  session.beginTransaction();  
            Query qu = session.createQuery("From Project where projTitle =:title"); //User is the entity not the table  
            qu.setParameter("title", d);  
             
            daoSearchList = qu.list();  
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
    public Project projectInfo2(String d)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();  
        List < Project > daoSearchList = new ArrayList < > ();  
        Project project = new Project();
        try  
        {  session.beginTransaction();  
            Query qu = session.createQuery("From Project where projTitle =:title"); //User is the entity not the table  
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
        return project;  
    }
    
    public List getProject(User d)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();  
        List < User > daoSearchList = new ArrayList < > ();  
        User user = new User();
        try  
        {  session.beginTransaction();  
            Query qu = session.createQuery("From Project where user =:userName"); //User is the entity not the table  
            qu.setParameter("userName", d);  
             
            daoSearchList = qu.list(); 
            user = (User)qu.uniqueResult(); 
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
} 