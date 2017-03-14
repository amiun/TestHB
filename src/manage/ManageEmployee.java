/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import project.Employee;
import util.HibernateUtil;


/**
 *
 * @author Alex
 */
/*Create Manage Class*/
public class ManageEmployee {
    
    /*Method to add an Employee in the database*/
    public Integer addEmployee(String fname,String lname,int salary){
        Session session =HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Integer employeeID= null;
        try{
            tx = session.beginTransaction();
            Employee employee = new Employee(fname,lname,salary);
            employeeID = (Integer) session.save(employee);
            tx.commit();
        } catch (HibernateException e){
            if (tx!=null) 
                tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        } return employeeID;
    }
    
    /*Method to read all employees*/
    public void listEmployees(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx=session.beginTransaction();
            List employees = session.createQuery("FROM Employee").list();
            for (Iterator iterator = employees.iterator(); iterator.hasNext();){
                Employee employee = (Employee) iterator.next();
                System.out.print("First name: "+employee.getFirstName()+", ");
                System.out.print("Last name: "+employee.getLastName()+", ");
                System.out.println("Salary: "+employee.getSalary());
            }
            tx.commit();
        } catch (HibernateException e){
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }      
    }
    
    /*Method to UPDATE salary for an employee */
    public void updateEmployee(Integer EmployeeID, int salary){
        Session session =HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Employee employee = (Employee)session.get(Employee.class,EmployeeID);
            employee.setSalary(salary);
            session.update(employee);
            tx.commit();
        }catch (HibernateException e){
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
    
    /*Method to DELETE an employee from the record*/
    public void deleteEmployee(Integer EmployeeID){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx=null;
        try{
            tx=session.beginTransaction();
            Employee employee = (Employee)session.get(Employee.class, EmployeeID);
            session.delete(employee);
            tx.commit();
        }catch (HibernateException e){
            if (tx!=null)tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public static void main(String[] args) {
        ManageEmployee ME = new ManageEmployee();
        
        Integer emp1 = ME.addEmployee("Alex", "Ali", 1000);
        Integer emp2 = ME.addEmployee("Geo", "Dudu", 5000);
        Integer emp3 = ME.addEmployee("John", "Park", 10000);
        
        ME.listEmployees();
        
        ME.updateEmployee(emp3, 21000);
        
        ME.deleteEmployee(emp2);
        
        ME.listEmployees();
        
        HibernateUtil.getSessionFactory().close();
    }
}
