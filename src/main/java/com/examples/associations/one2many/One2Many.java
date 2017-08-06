package com.examples.associations.one2many;

import com.examples.associations.one2many.domain.Car;
import com.examples.associations.one2many.domain.Person;
import com.examples.enumeration.HibernateConfiguration;
import com.examples.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by krishna1bhat on 8/5/17.
 */
public class One2Many {
    public static void main(String... args){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory(HibernateConfiguration.ANNOTATION_BASED, "associations/hibernate.cfg.annotation.one2many.xml");
        Session session = sessionFactory.openSession();

        Person customer = new Person();
        customer.setFirstName("Krishna");
        customer.setLastname("Bhat");

        Car car1 = new Car();
        car1.setYear((short)2017);
        car1.setModel("MDL0001");
        car1.setMaker("Marker1");

        Car car2 = new Car();
        car2.setYear((short)2016);
        car2.setModel("MDL0002");
        car2.setMaker("Marker11");

        List<Car> cars = new ArrayList<>();
        cars.add(car1);
        cars.add(car2);

        customer.setCars(cars);

        Transaction tx = session.beginTransaction();
        session.persist(customer);
        tx.commit();

        session.close();
        sessionFactory.close();
    }
}
