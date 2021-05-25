package com.assignment2;

import com.assignment2.processing.Persistent;
import com.assignment2.processing.Persistor;
import com.assignment2.processing.Processor;
import com.assignment2.student.Student;
import com.assignment2.student.StudentDTO;
import com.assignment2.student.comparators.AddressComparator;
import com.assignment2.student.comparators.AgeComparator;
import com.assignment2.student.comparators.RollNumberComparator;
import com.assignment2.userinterface.UserInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Runner {

  public static void main(String[] args) throws IOException {
    final UserInterface userInterface = new UserInterface();
    final Persistent persistor = new Persistor();
    ArrayList<Student> students = persistor.load("StudentsDB.txt");

    boolean shouldExit = false;
    while (!shouldExit) {
      Option option = userInterface.displayMainMenu();
      Processor processor = new Processor();
      switch (option) {
        case ADD:
          StudentDTO studentDTO = userInterface.addStudent();
          try {
            students = processor.addAfterValidation(students, studentDTO);
            userInterface.showMessage("Successfully Added the record!");
          } catch (Exception except) {
            userInterface.showMessage("Invalid format");
          }
          break;
        case DISPLAY:
          Param param = userInterface.displaySubMenuParam();
          switch (param) {
            case NAME:
              Collections.sort(students);
            case AGE:
              students.sort(new AgeComparator());
              break;
            case ADDRESS:
              students.sort(new AddressComparator());
              break;
            case ROLLNUMBER:
              students.sort(new RollNumberComparator());
              break;
            default:
              throw new IllegalStateException("Unexpected value: " + param);
          }
          Order order = userInterface.displaySubMenuOrder();
          if (order == Order.DESC) {
            Collections.reverse(students);
          }
          userInterface.displayStudentDetails(students);
          break;
        case DELETE:
          int rollNoToDelete = userInterface.deleteStudent();
          try {
            students = processor.deleteIfRecordExists(students, rollNoToDelete);
            userInterface.showMessage("Successfully Deleted");
          } catch (Exception exception) {
            userInterface.showMessage("Roll number not in records!");
          }
          break;
        case SAVE:
          try {
            persistor.store(students,"StudentsDB.txt");
            userInterface.showMessage("Successfully Saved");
          } catch (Exception exception) {
            userInterface.showMessage("Failed to save!");
          }
          break;
        case EXIT:
          userInterface.showMessage("Thank You for using the application..");
          shouldExit = true;
          break;
        default:
          userInterface.showMessage("Invalid Input! Please Enter a valid Input!");
          break;
      }
    }
  }
}
