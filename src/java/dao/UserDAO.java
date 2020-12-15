package dao;  
import java.util.List;  
import java.util.ArrayList;  
import org.hibernate.Query;  
import org.hibernate.Session;  
import util.HibernateUtil;  
import model.entity.User;  
import javax.faces.application.FacesMessage;  
import org.primefaces.context.RequestContext;  
/** 
 * 
 * @author Raichand 
 */  
public class UserDAO  
{  
    private User user;  
    private User newuser;  
    private List < User > DaoAllUsers;  
    private List < User > DaoUser;
    private List < User > DaoSearchUserList;  
    //Session session;  
    public List < User > AllUsers()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            DaoAllUsers = session.createCriteria(User.class).list();  
            int count = DaoAllUsers.size();  
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
        return DaoAllUsers;  
    }  
    
    public Integer getId()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        String hql = "select max(U.id) from User U";  
        Query query = session.createQuery(hql);  
        List < Integer > results = query.list();  
        Integer userId = 1;  
        if (results.get(0) != null)  
        {  
            userId = results.get(0) + 1;  
        }  
        session.flush();  
        session.close();  
        return userId;  
    }
    
    public Integer getTotalUser()  
    {  
       Session session = HibernateUtil.getSessionFactory().openSession();  
        String hql = "select U.id from User U";  
        Query query = session.createQuery(hql); 
        List < User > totalList = new ArrayList < > ();
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
    
    public String SearchByUserName(String userName)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        String hql = "select userName from User U where U.userName =:userName";  
        Query query = session.createQuery(hql);  
        
        session.flush();  
        session.close();  
        return userName;  
    }  
    public List < User > SearchByUserNameList(String UserName)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        List < User > daoSearchList = new ArrayList < > ();  
        try  
        {  session.beginTransaction();  
            Query qu = session.createQuery("From User U where U.userName =:userName"); //User is the entity not the table  
            qu.setParameter("userName", UserName);  
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
            
    
   
    
    public void add(User newuser)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String Id = Integer.toString(newuser.getUserId());  
            // begin a transaction  
            session.beginTransaction();  
            session.merge(newuser);  
            session.flush();  
            System.out.println("NewUser saved, id: " +  
                newuser.getUserId());  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void delete(User user)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String name = user.getUserName();  
            session.beginTransaction();  
            session.delete(user);  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void update(User user)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            session.update(user);  
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
    public List userInfo(String u,String p)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();  
        List < User > daoSearchList = new ArrayList < > ();  
        try  
        {  session.beginTransaction();  
            Query qu = session.createQuery("From User  where userName =:name AND userPass= :pass"); //User is the entity not the table  
            qu.setParameter("name", u);  
            qu.setParameter("pass", p);
             
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
    public User userInfo2(String u,String p)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();  
        List < User > daoSearchList = new ArrayList < > ();  
        User user= new User();
        try  
        {  session.beginTransaction();  
            Query qu = session.createQuery("From User  where userName =:name AND userPass= :pass"); //User is the entity not the table  
            qu.setParameter("name", u);  
            qu.setParameter("pass", p);
             
            daoSearchList = qu.list();  
            
            user = (User)qu.uniqueResult();  
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
        return user;
    }
    
       public User getUserById(String uname)
       {   User user = new User();
            Session session = HibernateUtil.getSessionFactory().openSession();  
        String hql = "from User where userName= :pid";  
        Query query = session.createQuery(hql);
        query.setParameter("pid",uname);
        user = (User)query.uniqueResult();
        
        session.close();  
        return user; 
       }
} 