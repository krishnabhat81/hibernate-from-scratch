package com.examples.xmlconfig;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by krishna1bhat on 5/16/17.
 */
public class MainApp {
    private static SessionFactory sessionFactory;

    public static void main(String[] args){
        try{
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable e){
            System.out.println("Can not create sessionFactory object: " + e);
            throw new ExceptionInInitializerError(e);
        }
    }
}
