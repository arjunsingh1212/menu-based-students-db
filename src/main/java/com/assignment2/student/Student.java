package com.assignment2.student;

import com.assignment2.student.comparators.RollNumberComparator;

import java.util.Set;
import java.util.TreeSet;

public class Student implements Comparable<Student> {
  private String fullName;
  private int age;
  private String address;
  private int rollNumber;
  Set<Character> courses;

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

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public int getRollNumber() {
    return rollNumber;
  }

  public void setRollNumber(int rollNumber) {
    this.rollNumber = rollNumber;
  }

  public Set<Character> getCourses() {
    return courses;
  }

  public void setCourses(Set<Character> courses) {
    this.courses = courses;
  }

  @Override
  public int compareTo(Student studentObject) {
    int comparison = this.fullName.compareTo(studentObject.getFullName());
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

  public String toString() {
    return fullName+age+address+courses.toString();
  }
}
