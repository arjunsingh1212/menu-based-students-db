package com.assignment2.processing;

import com.assignment2.exceptions.GenericApplicationException;
import com.assignment2.student.Student;
import com.assignment2.student.StudentDTO;
import java.util.ArrayList;

public class Processor {

  //Binary search is costly as it would need Sorting
  /**
  private int binarySearch(final ArrayList<Student> students, final int rollNumber) {
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
    return index;
  }
   */

  // Linear Search
  private int linearSearch(final ArrayList<Student> students, final int rollNumber) {
    int index = -1;
    for (int i = 0; i < students.size(); i++) {
      if (students.get(i).getRollNumber() == rollNumber) {
        index = i;
        break;
      }
    }
    return index;
  }

  public ArrayList<Student> addAfterValidation(
          final ArrayList<Student> students, final StudentDTO studentDTO)
          throws GenericApplicationException {
    final Student student = new Validator().validate(studentDTO);
    int index = linearSearch(students, student.getRollNumber()); //return -1 if not present
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
    int index = linearSearch(students, rollNumber);
    if (index == -1) {
      throw new GenericApplicationException("Student record not existing");
    }
    students.remove(index);
    return students;
  }
}
