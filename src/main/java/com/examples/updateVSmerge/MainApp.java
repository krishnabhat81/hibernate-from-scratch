package com.examples.updateVSmerge;

import com.examples.annotationconfig.Employee;
import com.examples.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Created by krishna1bhat on 8/5/17.
 */
public class MainApp {
    public static void main(String[] args) throws CloneNotSupportedException {

        //creating transient state...
        Employee empTransient = new Employee("Transient", "Object", 20000);



        //------------------ session1 ---------------------------
        Session session1 = HibernateUtil.getSessionFactory().openSession();
        //create new Employee and set values -- transient state
        Employee emp1 = new Employee("Krishna", "Bhat", 90000);
        //save() can be used outside transaction, but if we have mapping entities, then only the primary object gets saved causing data inconsistencies
        //also returns the generated id immediately
        //session1.save(emp1);//persistent state

        Transaction tx1 = session1.beginTransaction();

        //persist() can’t use it outside the boundary of transaction, so all the object mappings are preserved
        //also doesn’t return the generated id immediately
        session1.persist(emp1);// -- now persistent state
        Employee originalEmp1 = (Employee) emp1.clone();

        //Trying to call update on a transient instance will result in an exception
        //session1.update(empTransient);//this will throw StaleStateException...

        tx1.commit();
        //session1.close();//close first session1

        emp1.setFirstName("First Employee updated");//emp1 is in detached state
        emp1.setSalary(130000);


        //------------------ session2 ---------------------------
        Session session2 = HibernateUtil.getSessionFactory().openSession();
        Transaction tx2 = session2.beginTransaction();

        Employee emp2 = session2.get(Employee.class, 1);//inside session2 get employee with same identifier i.e. id=1
        emp2.setFirstName("Second Employee updated");
        emp2.setSalary(120000);

        //session2.update(emp1);//Exception because emp1 has id=1 and also emp2(with same identifier) is inside this session2 -- hence update throws exception NonUniqueObjectException
        session2.merge(emp1);//but merge() will work...

        tx2.commit();
        session2.close();

        //Again look the result here from Emp1 and Emp2
        //Both results are same: calling merge() inside session2 merges Emp1 in Emp2; resulting emp1 and emp2 both same
        System.out.println("\n\n---------- Check some results ----------\n");
        System.out.println("\nEmp1: " + emp1);
        System.out.println("\nEmp2: " + emp2);
        System.out.println("\nOriginal Emp1: " + originalEmp1 + "\n\n");


        HibernateUtil.getSessionFactory().close();
    }
}
