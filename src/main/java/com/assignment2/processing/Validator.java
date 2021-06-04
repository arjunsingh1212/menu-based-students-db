package com.assignment2.processing;

import com.assignment2.exceptions.GenericApplicationException;
import com.assignment2.interfaces.Valid;
import com.assignment2.student.Student;
import com.assignment2.student.StudentDTO;

import java.util.TreeSet;

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
  public Student validate(final StudentDTO studentDTO) throws GenericApplicationException {
    final Student student = new Student();
    if (validateName(studentDTO.getFullName())) {
      student.setFullName(studentDTO.getFullName());
    } else {
      throw new GenericApplicationException("Invalid Name Exception : "
              + studentDTO.getFullName());
    }
    if (validateAge(studentDTO.getAge())) {
      student.setAge(Integer.parseInt(studentDTO.getAge()));
    } else {
      throw new GenericApplicationException("Invalid Age Exception : "
              + studentDTO.getAge());
    }
    if (validateAddress(studentDTO.getAddress())) {
      student.setAddress(studentDTO.getAddress());
    } else {
      throw new GenericApplicationException("Invalid Address Exception : "
              + studentDTO.getAddress());
    }
    if (validateRollNo(studentDTO.getRollNumber())) {
      student.setRollNumber(Integer.parseInt(studentDTO.getRollNumber()));
    } else {
      throw new GenericApplicationException("Invalid Roll Number Exception : "
              + studentDTO.getRollNumber());
    }
    if (validateCourses(studentDTO.getCourses())) {
      final TreeSet<String> myCourses = studentDTO.getCourses();
      final TreeSet<Character> newCourses = new TreeSet<>();
      for (final String course : myCourses) {
        newCourses.add(course.charAt(0));
      }
      student.setCourses(newCourses);
    } else {
      throw new GenericApplicationException("Invalid Courses Exception : "
              + studentDTO.getCourses().toString());
    }
    return student;
  }
}
