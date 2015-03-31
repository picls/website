package test;

import org.hibernate.Transaction;
import org.hibernate.Session;

import java.util.Date;
import java.util.List;

public class EventManager {

    public static void main(String[] args) {
        EventManager mgr = new EventManager();
        
        
    	mgr.addPersonToEvent(1,1);
    	String str= "this is %1$s %2$s %3$s";
    	System.out.println(String.format(str, "ÖÐ¹úÈË", "xx", "22"));
    	System.exit(0);
        
        String act = "store";

        if (act.equals("store")) {
            mgr.createAndStoreEvent("My Eventb", new Date());
        }
        else if (act.equals("list")) {
            List events = mgr.listEvents();
            for (int i = 0; i < events.size(); i++) {
                Event theEvent = (Event) events.get(i);
                System.out.println("Event: " + theEvent.getTitle() +
                                   " Time: " + theEvent.getDate());
            }
        }

        HibernateUtil.sessionFactory.close();
    }
    
    private void createAndStoreEvent(String title, Date theDate) {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();

        Event theEvent = new Event();
        theEvent.setTitle(title);
        theEvent.setDate(theDate);

        session.save(theEvent);

        tx.commit();
        HibernateUtil.closeSession();
    }
    
    private List listEvents() {
        Session session = HibernateUtil.currentSession();
        //Transaction tx = session.beginTransaction();

        List result = session.createQuery("from Event").list();
        

        //tx.commit();
        session.close();

        return result;
    }
    
    private void addPersonToEvent(int personId, int eventId) {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();

        Person aPerson = (Person) session.load(Person.class, personId);
        Event anEvent = (Event) session.load(Event.class, eventId);

        aPerson.getEvents().add(anEvent);

        tx.commit();
        HibernateUtil.closeSession();
    }

}