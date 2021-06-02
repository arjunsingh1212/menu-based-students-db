package com.assignment2.processing.interfaces;

import com.assignment2.student.Student;
import java.util.ArrayList;

public interface ReaderWriter {

  void store(ArrayList<Student> students);

  ArrayList<Student> load();
}
