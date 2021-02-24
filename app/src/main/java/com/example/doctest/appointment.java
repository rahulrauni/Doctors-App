package com.example.doctest;

public class appointment {
    private String docName;
    private String docProf;
    private String descriptionDoc;
    private String dockey;
    private String mobilenumber;
    private String address;
    private String pincode;
    private String age;
    private String fullname;
    private String gender;
    private String state;
    private String city;
    private String description;
    private String userid;
    private String userregid;
    private String appointkey;
    public appointment(){

    }

    public appointment(String docName, String docProf, String descriptionDoc, String dockey, String mobilenumber, String address, String pincode, String age, String fullname, String gender, String state, String city, String description, String userid, String userregid, String appointkey) {
        this.docName = docName;
        this.docProf = docProf;
        this.descriptionDoc = descriptionDoc;
        this.dockey = dockey;
        this.mobilenumber = mobilenumber;
        this.address = address;
        this.pincode = pincode;
        this.age = age;
        this.fullname = fullname;
        this.gender = gender;
        this.state = state;
        this.city = city;
        this.description = description;
        this.userid = userid;
        this.userregid = userregid;
        this.appointkey = appointkey;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocProf() {
        return docProf;
    }

    public void setDocProf(String docProf) {
        this.docProf = docProf;
    }

    public String getDescriptionDoc() {
        return descriptionDoc;
    }

    public void setDescriptionDoc(String descriptionDoc) {
        this.descriptionDoc = descriptionDoc;
    }

    public String getDockey() {
        return dockey;
    }

    public void setDockey(String dockey) {
        this.dockey = dockey;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserregid() {
        return userregid;
    }

    public void setUserregid(String userregid) {
        this.userregid = userregid;
    }

    public String getAppointkey() {
        return appointkey;
    }

    public void setAppointkey(String appointkey) {
        this.appointkey = appointkey;
    }
}
