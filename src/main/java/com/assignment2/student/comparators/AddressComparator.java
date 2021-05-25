package com.assignment2.student.comparators;

import com.assignment2.student.Student;

import java.util.Comparator;

public class AddressComparator implements Comparator<Student> {
  @Override
  public int compare(Student student1, Student student2) {
    int result;
    if (student1.getAddress().compareTo(student2.getAddress()) > 0) {
      result = 1;
    } else if (student1.getAddress().compareTo(student2.getAddress()) < 0) {
      result = -1;
    } else {
      result = new RollNumberComparator().compare(student1, student2);
    }
    return result;
  }
}
