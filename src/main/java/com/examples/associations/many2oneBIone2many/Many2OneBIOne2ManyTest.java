package com.examples.associations.many2oneBIone2many;

import com.examples.associations.many2oneBIone2many.domain.Car;
import com.examples.associations.many2oneBIone2many.domain.Person;
import com.examples.enumeration.HibernateConfiguration;
import com.examples.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by krishna1bhat on 8/6/17.
 */
public class Many2OneBIOne2ManyTest {
    public static void main(String... args){
        SessionFactory sf = HibernateUtil.getSessionFactory(HibernateConfiguration.ANNOTATION_BASED, "associations/hibernate.cfg.annotation.many2oneBIone2many.xml");
        Session session = sf.openSession();

        Car car1 = new Car();
        car1.setYear((short)2017);
        car1.setModel("MDL0001");
        car1.setMaker("Marker1");

        Person person = new Person();
        person.setFirstName("Krishna");
        person.setLastname("Bhat");

        Car car2 = new Car();
        car2.setYear((short)2016);
        car2.setModel("MDL0002");
        car2.setMaker("Marker11");

        List<Car> cars = new ArrayList<>();
        cars.add(car1);
        cars.add(car2);

        person.setCars(cars);
        car1.setOwner(person);
        car2.setOwner(person);

        Transaction tx = session.beginTransaction();

        session.persist(person);
        tx.commit();

        session.close();
        sf.close();
    }
}
