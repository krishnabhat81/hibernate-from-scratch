package com.examples.deleteEntityFromDatasource;

import com.examples.associations.one2one.domain.Address;
import com.examples.associations.one2one.domain.Customer;
import com.examples.enumeration.HibernateConfiguration;
import com.examples.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Created by krishna1bhat on 8/7/17.
 */
public class DeleteEntityTest {
    public static void main(String... args) throws InterruptedException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory(HibernateConfiguration.ANNOTATION_BASED, "associations/hibernate.cfg.annotation.one2one.xml");
        Session session = sessionFactory.openSession();

//*************** Creating Customer Object for test ****************************

        Customer customer = new Customer();
        customer.setFirstName("Krishna");
        customer.setLastName("Bhat");

        Address address = new Address();
        address.setCity("Fairfield");
        address.setState("IOWA");
        address.setStreet("1000 North..");
        address.setZip(52557);

        customer.setAddress(address);

        Transaction tx = session.beginTransaction();
        long myId = (long)session.save(customer);
        //or
        //Serializable myId = session.save(customer);
        tx.commit();

        session.close();

        Thread.sleep(10000);//at this time I am looking to my database to make sure that database save Customer/Address to corresponding tables... ;)


//Three ways to delete entity from datasource
//Use each approach based on the job requirement


//*************** 1. Delete Customer using HQL - Careful ****************************

//        Session session2 = sessionFactory.openSession();
//        Transaction tx2 = session2.beginTransaction();
//
//        //this approach doesn’t remove associated instances...
//        Query query = session2.createQuery("delete from Customer where id = :cId");
//        query.setParameter("cId", myId);
//
//        query.executeUpdate();
//
//        tx2.commit();
//        session2.close();


//*************** 2. Delete Customer using persistent instance - GOOD ****************************
        //this approach removes associated instances
        Session session3 = sessionFactory.openSession();
        Transaction tx3 = session3.beginTransaction();

        //load() is lazy, don't use get(), one extra query will be executed
        //one for selecting Customer from database if you use get()
        //another for deleting me object --- look log
        Customer me = session3.load(Customer.class, myId);
        if(me != null)
            session3.delete(me);

        tx3.commit();
        session3.close();

//*************** 3. Delete Customer using transient instance - Careful ****************************
        //this approach also doesn’t remove associated instances...

        //try yourself below:

        /*
        Customer newCustomer = new Customer();
        newCustomer.setId(123);
        newCustomer.setFirstName("Krishna");
        newCustomer.setLastName("Bhat");

        //write associated Addresses objects too..
        session4.delete(newCustomer);
        */

        sessionFactory.close();
    }
}
