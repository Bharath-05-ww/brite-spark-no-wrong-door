package com.britespark.nowrongdoor.dto;

public class Resident {

    private String id;
    private String first_name;
    private String last_name;
    private String date_of_birth;
    private String address_line;
    private String city;
    private String phone;
    private String program_status;
    private String last_contact;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getAddress_line() {
        return address_line;
    }

    public void setAddress_line(String address_line) {
        this.address_line = address_line;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProgram_status() {
        return program_status;
    }

    public void setProgram_status(String program_status) {
        this.program_status = program_status;
    }

    public String getLast_contact() {
        return last_contact;
    }

    public void setLast_contact(String last_contact) {
        this.last_contact = last_contact;
    }
}