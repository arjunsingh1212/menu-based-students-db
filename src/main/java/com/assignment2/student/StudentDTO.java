package com.assignment2.student;

import java.util.Set;
import java.util.TreeSet;

public class StudentDTO {
  private String fullName;
  private String age;
  private String address;
  private String rollNumber;
  Set<String> courses;

  public StudentDTO(final String fullName,
                    final String age,
                    final String address,
                    final String rollNumber,
                    final TreeSet<String> courses) {
    this.fullName = fullName;
    this.age = age;
    this.address = address;
    this.rollNumber = rollNumber;
    this.courses = courses;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getRollNumber() {
    return rollNumber;
  }

  public void setRollNumber(String rollNumber) {
    this.rollNumber = rollNumber;
  }

  public Set<String> getCourses() {
    return courses;
  }

  public void setCourses(Set<String> courses) {
    this.courses = courses;
  }
}
