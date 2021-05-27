package com.assignment2.readerwriter;

import com.assignment2.readerwriter.interfaces.ReaderStudentEntity;
import com.assignment2.student.StudentDTO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class ReaderCLI implements ReaderStudentEntity {

  private final transient BufferedReader bufferedReader = new BufferedReader(
          new InputStreamReader(System.in));

  @Override
  public StudentDTO readDetails() {
    String name = "";
    String age = "";
    String address = "";
    String rollNumber = "";
    final TreeSet<String> courses = new TreeSet<>();
    try {
      System.out.println("Please enter the following details of the Student.");
      System.out.println("Enter Name: ");
      name = bufferedReader.readLine();
      System.out.println("Enter Age: ");
      age = bufferedReader.readLine();
      System.out.println("Enter Address: ");
      address = bufferedReader.readLine();
      System.out.println("Enter Roll Number: ");
      rollNumber = bufferedReader.readLine();
      System.out.println("Choose 4 Courses out of 6 options (A, B, C, D, E, F).");
      for (int i = 1; i <= 4; i++) {
        System.out.println("Enter choice for course " + i + " : ");
        courses.add(bufferedReader.readLine());
      }
    } catch (IOException except) {
      System.out.println(except.getMessage());
    }
    return new StudentDTO(name, age, address, rollNumber, courses);
  }
}
