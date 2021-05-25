package com.assignment2.userinterface;

import com.assignment2.Option;
import com.assignment2.Order;
import com.assignment2.Param;
import com.assignment2.student.Student;
import com.assignment2.student.StudentDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeSet;

public class UserInterface {
  public Option displayMainMenu() throws IOException {
    System.out.println("Welcome to the Students Database. Selection your option.\n" +
            "1. Add User details. (ADD) \n" +
            "2. Display User details. (DISPLAY)\n" +
            "3. Delete User details (DELETE) \n" +
            "4. Save User details. (SAVE)\n" +
            "5. Exit (EXIT)\n" +
            "Enter your choice: ");
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    return Option.valueOf(bufferedReader.readLine());
  }

  public StudentDTO addStudent() throws IOException {
    System.out.println("Please enter the following details of the Student.");
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Enter Name: ");
    String name = bufferedReader.readLine();
    System.out.println("Enter Age: ");
    String age = bufferedReader.readLine();
    System.out.println("Enter Address: ");
    String address = bufferedReader.readLine();
    System.out.println("Enter Roll Number: ");
    String rollNumber = bufferedReader.readLine();
    System.out.println("Choose 4 Courses out of 6 options (A, B, C, D, E, F).");
    TreeSet<String> courses = new TreeSet<>();
    for (int i=1;i<=4;i++) {
      System.out.println("Enter choice for course "+i+" : ");
      courses.add(bufferedReader.readLine());
    }
    return new StudentDTO(name,age,address,rollNumber,courses);
  }

  public Param displaySubMenuParam() throws IOException {
    System.out.println("Please tell, according to which field you want to display sorted details\n" +
            "1 - Name (NAME) \n" +
            "2- Age (AGE)\n" +
            "3- Address (ADDRESS) \n" +
            "4- Roll Number (ROLLNUMBER)\n" +
            "Enter your choice: ");
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    return Param.valueOf(bufferedReader.readLine());
  }

  public Order displaySubMenuOrder() throws IOException {
    System.out.println("Please tell, according to order want to display sorted details\n" +
            "1 - Ascending (ASC) \n" +
            "2- Descending (DESC)\n" +
            "Enter your choice: ");
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    return Order.valueOf(bufferedReader.readLine());
  }

  public void displayStudentDetails(ArrayList<Student> students) {
    System.out.println("Name, Roll Number, Age, Address, Courses\n");
    for (Student student : students) {
      System.out.println(student.getFullName()
              + ", " + student.getRollNumber()
              + ", " + student.getAge()
              + ", " + student.getAddress()
              + ", " + student.getCourses().toString());
    }
  }

  public int deleteStudent() throws IOException {
    System.out.println("Please enter the Roll Number of Student whose record is to be deleted: ");
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    return Integer.parseInt(bufferedReader.readLine());
  }

  public void showMessage(String message) {
    System.out.println(message);
  }
}
