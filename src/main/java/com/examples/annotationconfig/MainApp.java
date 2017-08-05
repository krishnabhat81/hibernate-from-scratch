package com.examples.annotationconfig;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Created by krishna1bhat on 5/16/17.
 */
public class MainApp {
    private static SessionFactory sessionFactory;

    public static void main(String[] args){
        // this step will read hibernate.cfg.annotation.many2one.xml and prepare for use
        // import org.hibernate.cfg.AnnotationConfiguration;
        try{
            sessionFactory = new Configuration().configure("hibernate.cfg.annotation.xml").buildSessionFactory();
        }catch (Throwable e){
            System.out.println("Can not create sessionFactory object: " + e);
            throw new ExceptionInInitializerError(e);
        }

        //hibernate placeholders
        Session session = null;
        Transaction tx = null;

        //open session
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            //create new Employee and set values
            Employee emp1 = new Employee();
            emp1.setFirstName("Krishna");
            emp1.setLastName("Bhat");
            emp1.setSalary(99999);

            //save emp
            //persist() - no return type
            session.persist(emp1);

            //save() -- returns generated id
//            Employee emp2 = new Employee("John", "Oran", 88888);
//            Serializable save = session.save(emp2);
//            System.out.println("Generated identifier: " + save);

            tx.commit();

        }catch (HibernateException e){
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        }finally {
            if(session != null)
                session.close();
        }

        sessionFactory.close();
    }
}