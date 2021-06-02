package com.assignment2.student;

import com.assignment2.student.comparators.RollNumberComparator;

import java.io.Serializable;
import java.util.TreeSet;

public class Student implements Comparable<Student>, Serializable {
  public static final long serialVersionUID = 432874324;
  private String fullName;
  private int age;
  private String address;
  private int rollNumber;
  private TreeSet<Character> courses;

  public Student() {
  }

  public Student(final String fullName,
                 final int age,
                 final String address,
                 final int rollNumber,
                 final TreeSet<Character> courses) {
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

  public int getAge() {
    return age;
  }

  public void setAge(final int age) {
    this.age = age;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(final String address) {
    this.address = address;
  }

  public int getRollNumber() {
    return rollNumber;
  }

  public void setRollNumber(final int rollNumber) {
    this.rollNumber = rollNumber;
  }

  public TreeSet<Character> getCourses() {
    return courses;
  }

  public void setCourses(final TreeSet<Character> courses) {
    this.courses = courses;
  }

  @Override
  public int compareTo(final Student studentObject) {
    final int comparison = this.fullName.compareTo(studentObject.getFullName());
    int result;
    if (comparison > 0) {
      result = 1;
    } else if (comparison < 0) {
      result = -1;
    } else {
      result = new RollNumberComparator().compare(this, studentObject);
    }
    return result;
  }

  @Override
  public String toString() {
    return fullName + " , "
            + rollNumber + " , "
            + age + " , "
            + address + " , " + courses.toString();
  }

  public boolean equals(Student student) {
    return student.fullName.equals(this.fullName)
            && student.rollNumber == this.rollNumber
            && student.age == this.age
            && student.address.equals(this.address)
            && student.courses.toString().equals(this.courses.toString());
  }
}
