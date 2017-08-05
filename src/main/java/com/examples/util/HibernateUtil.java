package com.examples.util;

import com.examples.annotationconfig.Employee;
import com.examples.enumeration.HibernateConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

/**
 * Created by krishna1bhat on 8/5/17.
 */
public class HibernateUtil {
    //default is xml based configuration...
    public static SessionFactory getSessionFactory(HibernateConfiguration configuration){
        if(sessionFactory == null) {
            switch (configuration) {
                case ANNOTATION_BASED:
                    sessionFactory = buildXmlSessionFactory("hibernate.cfg.annotation.xml");
                    break;
                case JAVA_BASED:
                    sessionFactory = buildJavaSessionFactory();
                    break;
                case XML_BASED:
                default:
                    sessionFactory = buildXmlSessionFactory("hibernate.cfg.xmlbased.xml");
                    break;
            }
        }
        return sessionFactory;
    }


    //xml based configuration
    //similar to annotation based except xml configuration file

    //annotation based configuration
    private static SessionFactory sessionFactory;

    private static SessionFactory buildXmlSessionFactory(String configFile){

        try {
            Configuration configuration = new Configuration();
            configuration.configure(configFile);

            sessionFactory = configuration.buildSessionFactory();
            return sessionFactory;
        }catch (Throwable ex){
            System.out.println("Can not create sessionFactory object: " + ex);
            throw new ExceptionInInitializerError(ex);
        }

    }

    //java based configuration
    //if you want to use hibernate.properties file no need to add properties here...
    private static SessionFactory buildJavaSessionFactory(){
        try{
            Configuration configuration = new Configuration();

            Properties properties = new Properties();
            properties.put("hibernate.connection.url", "jdbc:mysql://localhost/hibernate-examples");
            properties.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
            properties.put("hibernate.connection.username", "root");
            properties.put("hibernate.connection.password", "root");
            properties.put("hibernate.current_session_context_class", "thread");

            properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            properties.put("hibernate.show_sql", "true");
            properties.put("hibernate.hbm2ddl.auto", "create");

            configuration.setProperties(properties);

            //or
            /*configuration
                    .setProperty("", "")
                    .setProperty("", "")
                    .addAnnotatedClass(Employee.class);
            */

            //we can set mapping file or class with annotation
            //addClass(Employee1.class) will look for resource
            // /src/main/resources/Employee.hbm.xml (not good)
            configuration.addAnnotatedClass(Employee.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate Java Config serviceRegistry created");

            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            return sessionFactory;
        }catch (Exception ex){
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
}
