package com.examples.associations.one2one;

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
public class OneToOneTest {
    public static void main(String... args){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory(HibernateConfiguration.ANNOTATION_BASED, "associations/hibernate.cfg.annotation.one2one.xml");
        Session session = sessionFactory.openSession();

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
        session.persist(customer);
        tx.commit();

        session.close();
        sessionFactory.close();
    }
}
