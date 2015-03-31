package test;

import org.hibernate.Transaction;
import org.hibernate.Session;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class PersonManager {


    public static void main(String[] args) {
        PersonManager mgr = new PersonManager();
        
        String act = "list";

        if (act.equals("store")) {
            mgr.createAndStoreEvent(22,"a" ,"bc");
        }
        else if (act.equals("list")) {
            List persons = mgr.listEvents();
            for (int i = 0; i < persons.size(); i++) {
                Person person = (Person)persons.get(i);
                Set s = person.getEvents();
                Iterator it = s.iterator();
                String es = "";
/*                while(it.hasNext())
                	es += it.next().toString();
                System.out.println("Person: " + person.getAge() +
                                   " Firstname: " + person.getFirstname() +
                                   " Lastname: " + person.getLastname() + 
                                   " Event: " + es);*/
            }
        }
        else if (act.equals("event")) {
        	
        }

        HibernateUtil.sessionFactory.close();
    }
    
    private void createAndStoreEvent(int age, String firstname, String lastname) {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();

        Person person = new Person();
        person.setAge(age);
        person.setFirstname(firstname);
        person.setLastname(lastname);

        session.save(person);

        tx.commit();
        HibernateUtil.closeSession();
    }
    
    private List listEvents() {
        Session session = HibernateUtil.currentSession();
        //Transaction tx = session.beginTransaction();

        List result = session.createQuery("from Person").list();
        

        //tx.commit();
        session.close();

        return result;
    }
    
    
}