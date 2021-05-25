package com.assignment2.processing;

import com.assignment2.student.Student;

import java.util.ArrayList;

public interface Persistent {

  void store(ArrayList<Student> students,String filePath);

  ArrayList<Student> load(String filePath);
}
