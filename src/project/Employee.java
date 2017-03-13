/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

/**
 *
 * @author Alex
 */
public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private int salary;
    
    public Employee(){}
    public Employee(String first_name,String last_name,int salary)
    {
        this.firstName=first_name;
        this.lastName=last_name;
        this.salary=salary;
    }
    
    public int getId(){
        return this.id;
    }
    
    public void setId(int id){
        this.id=id;
    }
    
    public String getFirstName(){
        return this.firstName;
    }
    
    public void setFirstName(String first_name){
        this.firstName=first_name;
    }    
    
    public String getLastName(){
        return this.lastName;
    }
    
    public void setLastName(String last_name){
        this.lastName=last_name;
    }   
    
    public int getSalary(){
        return this.salary;
    }
    
    public void setSalary(int salary){
        this.salary=salary;
    }    
}
