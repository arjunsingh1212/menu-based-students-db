package com.assignment2.processing;

import com.assignment2.student.Student;

import java.util.ArrayList;

public class Persistor implements Persistent {

  @Override
  public void store(ArrayList<Student> students,String filePath) {
  }

  @Override
  public ArrayList<Student> load(String filePath) {
    return new ArrayList<>();
  }
}
