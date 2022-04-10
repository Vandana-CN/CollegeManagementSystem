/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Course;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Soumya Sudipta
 */
public class getcoursedata {
    private final StringProperty cid,course,department;
    
    public getcoursedata(String cid,String course, String department)
    {
        this.cid = new SimpleStringProperty(cid);
        this.course = new SimpleStringProperty(course);
        this.department = new SimpleStringProperty(department);
    }
    
    
    public String getcid()
    {
        return cid.get();
    }
    public String getcourse()
    {
        return course.get();
    }
    public String getdept()        
    {
        return department.get();
    }
    
    
    
    public void setcid(String value)
    {
        cid.set(value);
    }
    public void setcourse(String value)
    {
        course.set(value);
    }
    public void setdepartment(String value)
    {
        department.set(value);
    }
    
    
    public StringProperty cidProperty(){
        return cid;
    }
    public StringProperty courseProperty(){
        return course;
    }  
    public StringProperty departmentProperty(){
        return department;
    }
}
