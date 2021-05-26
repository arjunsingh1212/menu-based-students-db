package com.assignment2;

import com.assignment2.enums.Option;
import com.assignment2.enums.Order;
import com.assignment2.enums.Param;
import com.assignment2.processing.PersistorUsingFileIO;
import com.assignment2.processing.Processor;
import com.assignment2.processing.interfaces.Persistent;
import com.assignment2.student.Student;
import com.assignment2.student.StudentDTO;
import com.assignment2.student.comparators.AddressComparator;
import com.assignment2.student.comparators.AgeComparator;
import com.assignment2.student.comparators.RollNumberComparator;
import com.assignment2.userinterface.UserInterface;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

@SuppressWarnings({"PMD.DataflowAnomalyAnalysis","PMD.CommentRequired"})
public final class Runner {

  private Runner() {
  }

  public static void main(final String[] args) throws IOException {
    final UserInterface userInterface = new UserInterface();
    final Persistent persistor = new PersistorUsingFileIO();
    ArrayList<Student> students;
    if (persistor.testConnectivity(
            "src\\main\\java\\com\\assignment2\\StudentsDB.txt")) {
      students = persistor.load();
    } else {
      students = new ArrayList<>();
    }

    final Processor processor = new Processor();
    final AgeComparator ageComparator = new AgeComparator();
    final AddressComparator addressComparator = new AddressComparator();
    final RollNumberComparator rollNumberComparator = new RollNumberComparator();
    StudentDTO studentDTO;

    boolean shouldExit = false;
    while (!shouldExit) {
      try {
        final Option option = userInterface.displayMainMenu(); //Input option
        switch (option) {
          case ADD:
            studentDTO = userInterface.addStudent();
            try {
              students = processor.addAfterValidation(students, studentDTO);
              userInterface.showMessage("Successfully Added the record!");
            } catch (Exception except) {
              userInterface.showMessage("Invalid format or "
                      + "record/roll-number already existing");
            }
            break;
          case DISPLAY:
            try {
              //Input parameter according to which you want sorting
              final Param param = userInterface.displaySubMenuParam();
              switch (param) {
                case NAME:
                  Collections.sort(students);
                  break;
                case AGE:
                  students.sort(ageComparator);
                  break;
                case ADDRESS:
                  students.sort(addressComparator);
                  break;
                case ROLLNUMBER:
                  students.sort(rollNumberComparator);
                  break;
                default:
                  throw new IllegalStateException("Unexpected value: " + param);
              }
            } catch (IllegalArgumentException exception) {
              userInterface.showMessage("Entered wrong input."
                      + " Please enter the inputs as shows in Brackets of the option."
                      + "Try using the menu again.");
              break;
            }
            try {
              //Input order - ascending or descending
              final Order order = userInterface.displaySubMenuOrder();
              if (order == Order.DESC) {
                Collections.reverse(students);
              }
            } catch (IllegalArgumentException exception) {
              userInterface.showMessage("Entered wrong input."
                      + " Please enter the inputs as shows in Brackets of the option."
                      + "Try using the menu again.");
              break;
            }
            userInterface.displayStudentDetails(students);
            break;
          case DELETE:
            //Input roll number
            final int rollNoToDelete = userInterface.deleteStudent();
            try {
              students = processor.deleteIfRecordExists(students, rollNoToDelete);
              userInterface.showMessage("Successfully Deleted");
            } catch (Exception exception) {
              userInterface.showMessage("Roll number not in records!");
            }
            break;
          case SAVE:
            try {
              persistor.store(students);
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
      } catch (IllegalArgumentException exception) {
        userInterface.showMessage("Sorry, you entered Invalid input. "
                + "Please enter the inputs as shows in Brackets of the option.");
      }
    }
  }
}
