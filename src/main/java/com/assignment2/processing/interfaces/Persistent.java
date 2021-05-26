package com.assignment2.processing.interfaces;

import com.assignment2.student.Student;
import java.util.ArrayList;

@SuppressWarnings("PMD.CommentRequired")
public interface Persistent {

  void store(ArrayList<Student> students);

  ArrayList<Student> load();

  boolean testConnectivity(String filPath);
}
