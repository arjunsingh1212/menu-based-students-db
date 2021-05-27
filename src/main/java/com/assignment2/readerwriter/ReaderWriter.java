package com.assignment2.readerwriter;

import com.assignment2.enums.Option;
import com.assignment2.enums.Order;
import com.assignment2.enums.Param;
import com.assignment2.student.Student;
import com.assignment2.student.StudentDTO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeSet;

@SuppressWarnings({"PMD.SystemPrintln"})
public class ReaderWriter {

  private final transient BufferedReader bufferedReader = new BufferedReader(
          new InputStreamReader(System.in));

  public Option displayMainMenu() throws IOException {
    System.out.println("\nWelcome to the Students Database. Selection your option.\n"
            + "1. Add User details. (ADD) \n"
            + "2. Display User details. (DISPLAY)\n"
            + "3. Delete User details (DELETE) \n"
            + "4. Save User details. (SAVE)\n"
            + "5. Exit (EXIT)\n"
            + "Enter your choice: ");
    return Option.valueOf(bufferedReader.readLine());
  }

  public StudentDTO addStudent() throws IOException {
    System.out.println("Please enter the following details of the Student.");
    System.out.println("Enter Name: ");
    final String name = bufferedReader.readLine();
    System.out.println("Enter Age: ");
    final String age = bufferedReader.readLine();
    System.out.println("Enter Address: ");
    final String address = bufferedReader.readLine();
    System.out.println("Enter Roll Number: ");
    final String rollNumber = bufferedReader.readLine();
    System.out.println("Choose 4 Courses out of 6 options (A, B, C, D, E, F).");
    final TreeSet<String> courses = new TreeSet<>();
    for (int i = 1; i <= 4; i++) {
      System.out.println("Enter choice for course " + i + " : ");
      courses.add(bufferedReader.readLine());
    }
    return new StudentDTO(name, age, address, rollNumber, courses);
  }

  public Param displaySubMenuParam() throws IOException {
    System.out.println("Please tell, according to which field you want to display sorted details\n"
            + "1 - Name (NAME) \n"
            + "2- Age (AGE)\n"
            + "3- Address (ADDRESS) \n"
            + "4- Roll Number (ROLLNUMBER)\n"
            + "Enter your choice: ");
    return Param.valueOf(bufferedReader.readLine());
  }

  public Order displaySubMenuOrder() throws IOException {
    System.out.println("Please tell, according to order want to display sorted details\n"
            + "1 - Ascending (ASC) \n"
            + "2- Descending (DESC)\n"
            + "Enter your choice: ");
    return Order.valueOf(bufferedReader.readLine());
  }

  public void displayStudentDetails(final ArrayList<Student> students) {
    System.out.println("Name, Roll Number, Age, Address, Courses");
    for (final Student student : students) {
      System.out.println(student.getFullName()
              + ", " + student.getRollNumber()
              + ", " + student.getAge()
              + ", " + student.getAddress()
              + ", " + student.getCourses().toString());
    }
  }

  public int deleteStudent() throws IOException {
    System.out.println("Please enter the Roll Number of Student whose record is to be deleted: ");
    return Integer.parseInt(bufferedReader.readLine());
  }

  public void showMessage(final String message) {
    System.out.println(message);
  }
}
