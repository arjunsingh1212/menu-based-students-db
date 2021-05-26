package com.assignment2.student.comparators;

import com.assignment2.student.Student;
import java.util.Comparator;

@SuppressWarnings("PMD.CommentRequired")
public class RollNumberComparator implements Comparator<Student> {
  @Override
  public int compare(final Student student1, final Student student2) {
    return Integer.compare(student1.getRollNumber(), student2.getRollNumber());
  }
}

