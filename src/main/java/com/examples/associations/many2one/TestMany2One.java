package com.examples.associations.many2one;

import com.examples.associations.many2one.domain.Car;
import com.examples.associations.many2one.domain.Customer;
import com.examples.enumeration.HibernateConfiguration;
import com.examples.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Created by krishna1bhat on 8/5/17.
 */
public class TestMany2One {
    public static void main(String... args){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory(HibernateConfiguration.ANNOTATION_BASED, "associations/hibernate.cfg.annotation.many2one.xml");
        Session session = sessionFactory.openSession();

        //just run and check database table relationships on MySQLWorkbench.....
        Customer customer = new Customer();
        customer.setFirstName("Krishna");
        customer.setLastname("Bhat");

        Car car1 = new Car();
        car1.setYear((short)2017);
        car1.setModel("MDL0001");
        car1.setMaker("Marker");
        car1.setCustomer(customer);

        Car car2 = new Car();
        car2.setYear((short)2016);
        car2.setModel("MDL0002");
        car2.setMaker("Marker22");

        Transaction tx = session.beginTransaction();
        //session.persist(customer); //not required, becoz we used @Cascade....
        session.persist(car1);
        session.persist(car2);
        tx.commit();

        session.close();
        sessionFactory.close();
    }
}
