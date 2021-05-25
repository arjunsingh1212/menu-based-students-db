package com.assignment2.student.comparators;

import com.assignment2.student.Student;

import java.util.Comparator;

public class RollNumberComparator implements Comparator<Student> {
  @Override
  public int compare(Student student1, Student student2) {
    int result = 0;
    if (student1.getRollNumber() > student2.getRollNumber()) {
      result = 1;
    } else if (student1.getRollNumber() < student2.getRollNumber()) {
      result = -1;
    }
    return result;
  }
}

