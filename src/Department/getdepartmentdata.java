/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Department;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Soumya Sudipta
 */
public class getdepartmentdata {
    private final StringProperty did,department,hod;
    
    public getdepartmentdata(String did,String department, String hod)
    {
        this.did = new SimpleStringProperty(did);
        this.department = new SimpleStringProperty(department);
        this.hod = new SimpleStringProperty(hod);
    }
    
    
    public String getdid()
    {
        return did.get();
    }
    public String getdept()
    {
        return department.get();
    }
    public String gethod()        
    {
        return hod.get();
    }
    
    
    
    public void setdid(String value)
    {
        did.set(value);
    }
    public void sethod(String value)
    {
        hod.set(value);
    }
    public void setdepartment(String value)
    {
        department.set(value);
    }
    
    
    public StringProperty didProperty(){
        return did;
    }
    public StringProperty hodProperty(){
        return hod;
    }  
    public StringProperty departmentProperty(){
        return department;
    }
}
