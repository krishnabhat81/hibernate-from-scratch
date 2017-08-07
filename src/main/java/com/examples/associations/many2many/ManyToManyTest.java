package com.examples.associations.many2many;

import com.examples.associations.many2many.domain.Category;
import com.examples.associations.many2many.domain.Stock;
import com.examples.associations.one2one.domain.Address;
import com.examples.associations.one2one.domain.Customer;
import com.examples.enumeration.HibernateConfiguration;
import com.examples.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by krishna1bhat on 8/7/17.
 */

//Reference: https://www.mkyong.com/hibernate/hibernate-many-to-many-relationship-example-annotation/
public class ManyToManyTest {
    public static void main(String... args){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory(HibernateConfiguration.ANNOTATION_BASED, "associations/hibernate.cfg.annotation.many2many.xml");
        Session session = sessionFactory.openSession();

        Stock stock = new Stock();
        stock.setStockCode("1234");
        stock.setStockName("NAMASTE");

        Category category1 = new Category("CONSUMER", "CONSUMER COMPANY");
        Category category2 = new Category("INVESTMENT", "INVESTMENT COMPANY");

        Set<Category> categories = new HashSet<>();
        categories.add(category1);
        categories.add(category2);

        stock.setCategories(categories);

        Transaction tx = session.beginTransaction();
        session.save(stock);
        tx.commit();

        session.close();
        sessionFactory.close();
    }
}
