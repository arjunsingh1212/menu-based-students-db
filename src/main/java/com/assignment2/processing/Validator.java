package com.assignment2.processing;

import com.assignment2.student.Student;
import com.assignment2.student.StudentDTO;

import java.util.TreeSet;

public class Validator implements Valid {
  @Override
  public Student validate(StudentDTO studentDTO) {
    TreeSet<Character> courses=new TreeSet<>();
    courses.add('A'); courses.add('B'); courses.add('C'); courses.add('E');
    Student student = new Student("Arjun Singh",22,"317 N Block",2017021030, courses);
    return student;
  }
}
