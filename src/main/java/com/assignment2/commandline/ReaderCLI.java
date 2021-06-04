package com.assignment2.commandline;

import com.assignment2.exceptions.GenericApplicationException;
import com.assignment2.interfaces.Reader;
import com.assignment2.interfaces.Valid;
import com.assignment2.processing.Validator;
import com.assignment2.student.Student;
import com.assignment2.student.StudentDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeSet;

public class ReaderCLI implements Reader {

  private final transient BufferedReader bufferedReader = new BufferedReader(
          new InputStreamReader(System.in));
  private final Valid validator = new Validator();

  @Override
  public ArrayList<Student> read() {
    ArrayList<Student> result = new ArrayList<>();
    String name = "";
    String age = "";
    String address = "";
    String rollNumber = "";
    TreeSet<String> courses = new TreeSet<>();
    try {
      System.out.println("Please enter how many Student entries you want to make.");
      final int numOfEntries = Integer.parseInt(bufferedReader.readLine());
      for (int itr = 0; itr < numOfEntries; itr++) {
        System.out.println("Please enter the following details of the Student " + (itr + 1));
        System.out.println("Enter Name: ");
        name = bufferedReader.readLine();
        System.out.println("Enter Age: ");
        age = bufferedReader.readLine();
        System.out.println("Enter Address: ");
        address = bufferedReader.readLine();
        System.out.println("Enter Roll Number: ");
        rollNumber = bufferedReader.readLine();
        System.out.println("Choose 4 Courses out of 6 options (A, B, C, D, E, F).");
        courses = new TreeSet<>();
        for (int i = 1; i <= 4; i++) {
          System.out.println("Enter choice for course " + i + " : ");
          courses.add(bufferedReader.readLine());
        }
        try {
          result.add(validator.validate(
                  new StudentDTO(name, age, address, rollNumber, courses)));
        } catch (GenericApplicationException exception) {
          System.out.println(exception.getMessage());
          itr--;
        }
      }
    } catch (IOException except) {
      System.out.println(except.getMessage());
    }
    return result;
  }
}
