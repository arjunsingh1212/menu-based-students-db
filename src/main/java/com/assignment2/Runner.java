package com.assignment2;

import com.assignment2.enums.Option;
import com.assignment2.enums.Order;
import com.assignment2.enums.Param;
import com.assignment2.processing.PersistorUsingFileIO;
import com.assignment2.processing.Processor;
import com.assignment2.processing.interfaces.Persistent;
import com.assignment2.readerwriter.ReaderWriter;
import com.assignment2.student.Student;
import com.assignment2.student.StudentDTO;
import com.assignment2.student.comparators.AddressComparator;
import com.assignment2.student.comparators.AgeComparator;
import com.assignment2.student.comparators.RollNumberComparator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public final class Runner {

  private static final Processor processor = new Processor();
  private static final AgeComparator ageComparator = new AgeComparator();
  private static final AddressComparator addressComparator = new AddressComparator();
  private static final RollNumberComparator rollNumberComparator = new RollNumberComparator();
  private static final ReaderWriter readerWriter = new ReaderWriter();
  private static final Persistent persistor = new PersistorUsingFileIO();

  private Runner() {
  }

  public static void main(final String[] args) throws IOException {

    ArrayList<Student> students;
    if (persistor.testConnectivity(
            "src\\main\\java\\com\\assignment2\\StudentsDB.txt")) {
      students = persistor.load();
    } else {
      students = new ArrayList<>();
    }

    StudentDTO studentDTO;

    boolean shouldExit = false;
    while (!shouldExit) {
      try {
        final Option option = readerWriter.displayMainMenu(); //Input option
        switch (option) {
          case ADD:
            studentDTO = readerWriter.addStudent();
            try {
              students = processor.addAfterValidation(students, studentDTO);
              readerWriter.showMessage("Successfully Added the record!");
            } catch (Exception except) {
              readerWriter.showMessage("Invalid format or "
                      + "record/roll-number already existing");
            }
            break;
          case DISPLAY:
            try {
              //Input parameter according to which you want sorting
              final Param param = readerWriter.displaySubMenuParam();
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
              readerWriter.showMessage("Entered wrong input."
                      + " Please enter the inputs as shows in Brackets of the option."
                      + "Try using the menu again.");
              break;
            }
            try {
              //Input order - ascending or descending
              final Order order = readerWriter.displaySubMenuOrder();
              if (order == Order.DESC) {
                Collections.reverse(students);
              }
            } catch (IllegalArgumentException exception) {
              readerWriter.showMessage("Entered wrong input."
                      + " Please enter the inputs as shows in Brackets of the option."
                      + "Try using the menu again.");
              break;
            }
            readerWriter.displayStudentDetails(students);
            break;
          case DELETE:
            //Input roll number
            final int rollNoToDelete = readerWriter.deleteStudent();
            try {
              students = processor.deleteIfRecordExists(students, rollNoToDelete);
              readerWriter.showMessage("Successfully Deleted");
            } catch (Exception exception) {
              readerWriter.showMessage("Roll number not in records!");
            }
            break;
          case SAVE:
            try {
              persistor.store(students);
              readerWriter.showMessage("Successfully Saved");
            } catch (Exception exception) {
              readerWriter.showMessage("Failed to save!");
            }
            break;
          case EXIT:
            readerWriter.showMessage("Thank You for using the application..");
            shouldExit = true;
            break;
          default:
            readerWriter.showMessage("Invalid Input! Please Enter a valid Input!");
            break;
        }
      } catch (IllegalArgumentException exception) {
        readerWriter.showMessage("Sorry, you entered Invalid input. "
                + "Please enter the inputs as shows in Brackets of the option.");
      }
    }
  }
}
