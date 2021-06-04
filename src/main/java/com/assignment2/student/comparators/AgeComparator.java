package com.assignment2.student.comparators;

import com.assignment2.student.Student;
import java.util.Comparator;

public class AgeComparator implements Comparator<Student> {
  @Override
  public int compare(final Student student1, final Student student2) {
    int result;
    if (student1.getAge() > student2.getAge()) {
      result = 1;
    } else if (student1.getAge() < student2.getAge()) {
      result = -1;
    } else {
      result = new RollNumberComparator().compare(student1, student2);
    }
    return result;
  }
}
