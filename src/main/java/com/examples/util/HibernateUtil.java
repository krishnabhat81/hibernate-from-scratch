package com.examples.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by krishna1bhat on 8/5/17.
 */
public class HibernateUtil {
    //annotation based configuration
    private static SessionFactory sessionFactory;

    private static SessionFactory buildSessionFactory(){

        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.annotation.xml");

            sessionFactory = configuration.buildSessionFactory();
            return sessionFactory;
        }catch (Throwable ex){
            System.out.println("Can not create sessionFactory object: " + ex);
            throw new ExceptionInInitializerError(ex);
        }

    }

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null) sessionFactory  = buildSessionFactory();
        return sessionFactory;
    }
}
