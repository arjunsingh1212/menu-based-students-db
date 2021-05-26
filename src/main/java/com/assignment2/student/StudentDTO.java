package com.assignment2.student;

import java.util.TreeSet;

@SuppressWarnings("PMD.CommentRequired")
public class StudentDTO {
  private String fullName;
  private String age;
  private String address;
  private String rollNumber;
  private TreeSet<String> courses;

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

  public void setFullName(final String fullName) {
    this.fullName = fullName;
  }

  public String getAge() {
    return age;
  }

  public void setAge(final String age) {
    this.age = age;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(final String address) {
    this.address = address;
  }

  public String getRollNumber() {
    return rollNumber;
  }

  public void setRollNumber(final String rollNumber) {
    this.rollNumber = rollNumber;
  }

  public TreeSet<String> getCourses() {
    return courses;
  }

  public void setCourses(final TreeSet<String> courses) {
    this.courses = courses;
  }
}
