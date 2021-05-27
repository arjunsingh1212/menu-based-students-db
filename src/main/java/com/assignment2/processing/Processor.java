package com.assignment2.processing;

import com.assignment2.exceptions.GenericApplicationException;
import com.assignment2.student.Student;
import com.assignment2.student.StudentDTO;
import com.assignment2.student.comparators.RollNumberComparator;
import java.util.ArrayList;
import java.util.Collections;

public class Processor {

  public ArrayList<Student> addAfterValidation(
          final ArrayList<Student> students, final StudentDTO studentDTO)
          throws GenericApplicationException {
    final Student student = new Validator().validate(studentDTO);
    Collections.sort(students);
    //Binary search for the record
    int index = -1;
    int low = 0;
    int high = students.size() - 1;
    int mid;
    Student stud;
    while (low <= high) {
      mid = (low + high) / 2;
      stud = students.get(mid);
      if (student.compareTo(stud) == 0
              || student.getRollNumber() == stud.getRollNumber()) {
        index = mid;
        break;
      } else if (student.compareTo(stud) > 0) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    if (index >= 0) {
      throw new GenericApplicationException(
              "Student Record Already Present at index " + index);
    }
    students.add(student);
    return students;
  }

  public ArrayList<Student> deleteIfRecordExists(
          final ArrayList<Student> students, final int rollNumber)
          throws GenericApplicationException {
    students.sort(new RollNumberComparator());
    //Binary search according to roll number
    int index = -1;
    int low = 0;
    int high = students.size() - 1;
    int mid;
    Student stud;
    while (low <= high) {
      mid = (low + high) / 2;
      stud = students.get(mid);
      if (stud.getRollNumber() == rollNumber) {
        index = mid;
        break;
      } else if (stud.getRollNumber() < rollNumber) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    if (index == -1) {
      throw new GenericApplicationException("Student record not existing");
    }
    students.remove(index);
    return students;
  }
}
