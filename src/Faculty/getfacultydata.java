/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Faculty;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Soumya Sudipta
 */
public class getfacultydata {
    private final StringProperty fid,name,education,dob,gender,bloodgroup,department,address,city,state,zipcode,phone,email,doj;
    
    public getfacultydata(String fid,String name, String education, String dob,String gender, String bloodgroup, String department,String address, String city, String state,String zipcode, String phone,String email,String doj)
    {
        this.fid = new SimpleStringProperty(fid);
        this.name = new SimpleStringProperty(name);
        this.education = new SimpleStringProperty(education);
        this.dob = new SimpleStringProperty(dob);
        this.gender = new SimpleStringProperty(gender);
        this.bloodgroup = new SimpleStringProperty(bloodgroup);
        this.department = new SimpleStringProperty(department);
        this.address = new SimpleStringProperty(address);
        this.city = new SimpleStringProperty(city);
        this.state = new SimpleStringProperty(state);
        this.zipcode = new SimpleStringProperty(zipcode);
        this.phone = new SimpleStringProperty(phone);
        this.email = new SimpleStringProperty(email);
        this.doj = new SimpleStringProperty(doj);
    }
    
    //Getters
    public String gefid()
    {
        return fid.get();
    }
    public String getname()
    {
        return name.get();
    }
    public String geteducation()        
    {
        return education.get();
    }
    public String getdob()
    {
        return dob.get();
    }
    public String getgender()
    {
        return gender.get();
    }
    public String getbloodgroup()        
    {
        return bloodgroup.get();
    }
    public String getdepartment()
    {
        return department.get();
    }
    public String getaddress()
    {
        return address.get();
    }
    public String getcity()        
    {
        return city.get();
    }
    public String getstate()
    {
        return state.get();
    }
    public String getzipcode()
    {
        return zipcode.get();
    }
    public String getphone()        
    {
        return phone.get();
    }
    public String getemail()
    {
        return email.get();
    }        
    public String getdoj()
    {
        return doj.get();
    }        
    
    // Setters
    public void setfid(String value)
    {
        fid.set(value);
    }
    public void setname(String value)
    {
        name.set(value);
    }
    public void seteducation(String value)
    {
        education.set(value);
    }
    public void setdob(String value)
    {
        dob.set(value);
    }
    public void setgender(String value)
    {
        gender.set(value);
    }
    public void setbloodgroup(String value)
    {
        bloodgroup.set(value);
    }
    public void setdepartment(String value)
    {
        department.set(value);
    }
    public void setaddress(String value)
    {
        address.set(value);
    }
    public void setcity(String value)
    {
        city.set(value);
    }
    public void setstate(String value)
    {
        state.set(value);
    }
    public void setzipcode(String value)
    {
        zipcode.set(value);
    }
    public void setphone(String value)
    {
        phone.set(value);
    }
    public void setemail(String value)
    {
        email.set(value);
    }
    public void setdoj(String value)
    {
        doj.set(value);
    }
    
    
    // Return to calling function 
    public StringProperty sidProperty(){
        return fid;
    }
    public StringProperty nameProperty(){
        return name;
    }  
    public StringProperty educationProperty(){
        return education;
    }
    
    public StringProperty dobProperty(){
        return dob;
    }
    public StringProperty genderProperty(){
        return gender;
    }  
    public StringProperty bloodgroupProperty(){
        return bloodgroup;
    }
    public StringProperty departmentProperty(){
        return department;
    }
    public StringProperty addressProperty(){
        return address;
    }  
    public StringProperty cityProperty(){
        return city;
    }
    public StringProperty stateProperty(){
        return state;
    }
    public StringProperty zipcodeProperty(){
        return zipcode;
    }  
    public StringProperty phoneProperty(){
        return phone;
    }
    public StringProperty emailProperty(){
        return email;
    }
    public StringProperty dojProperty(){
        return doj;
    }
}
