package com.assignment2.processing;

import com.assignment2.exceptions.RuntimeExceptionCustom;
import com.assignment2.processing.interfaces.Valid;
import com.assignment2.student.Student;
import com.assignment2.student.StudentDTO;
import java.util.TreeSet;

@SuppressWarnings({"PMD.DataflowAnomalyAnalysis","PMD.CommentRequired","PMD.LooseCoupling"})
public class Validator implements Valid {

  private boolean validateName(final String name) {
    return name.matches("^[ ]*[A-Z][a-z]+([ ][A-Z][a-z]+)*$")
            && name.length() <= 25;
  }

  private boolean validateAge(final String age) {
    return age.matches("^[0-9]+$") && Integer.parseInt(age) > 0
            && Integer.parseInt(age) < 100;
  }

  //Address should not contain only numbers
  private boolean validateAddress(final String address) {
    return address.matches("^[ ]*[.a-zA-Z0-9]+[ .a-zA-Z0-9]*$")
            && !address.matches("^[0-9]+") && address.length() <= 40;
  }

  private boolean validateRollNo(final String rollNo) {
    return rollNo.matches("^[0-9]+$") && rollNo.length() <= 10
            && Integer.parseInt(rollNo) > 0;
  }

  //Course must be from [A,B,C,D,E,F]
  private boolean validateCourses(final TreeSet<String> courses) {
    boolean result = false;
    final int mandatoryCourses = 4;
    if (courses.size() == mandatoryCourses) {
      int count = 0;
      for (final String course : courses) {
        if (course.matches("^[A-F]$")) {
          count++;
        }
      }
      if (count == mandatoryCourses) {
        result = true;
      }
    }
    return result;
  }

  @Override
  public Student validate(final StudentDTO studentDTO) throws RuntimeExceptionCustom {
    final Student student = new Student();
    if (validateName(studentDTO.getFullName())) {
      student.setFullName(studentDTO.getFullName());
    } else {
      throw new RuntimeExceptionCustom("Invalid Name Exception");
    }
    if (validateAge(studentDTO.getAge())) {
      student.setAge(Integer.parseInt(studentDTO.getAge()));
    } else {
      throw new RuntimeExceptionCustom("Invalid Age Exception");
    }
    if (validateAddress(studentDTO.getAddress())) {
      student.setAddress(studentDTO.getAddress());
    } else {
      throw new RuntimeExceptionCustom("Invalid Address Exception");
    }
    if (validateRollNo(studentDTO.getRollNumber())) {
      student.setRollNumber(Integer.parseInt(studentDTO.getRollNumber()));
    } else {
      throw new RuntimeExceptionCustom("Invalid Roll Number Exception");
    }
    if (validateCourses(studentDTO.getCourses())) {
      final TreeSet<String> myCourses = studentDTO.getCourses();
      final TreeSet<Character> newCourses = new TreeSet<>();
      for (final String course : myCourses) {
        newCourses.add(course.charAt(0));
      }
      student.setCourses(newCourses);
    } else {
      throw new RuntimeExceptionCustom("Invalid Courses Exception");
    }
    return student;
  }
}
