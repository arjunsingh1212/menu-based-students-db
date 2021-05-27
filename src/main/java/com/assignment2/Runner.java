package com.assignment2;

import com.assignment2.enums.Option;
import com.assignment2.exceptions.GenericApplicationException;
import com.assignment2.processing.PersistorUsingFileIO;
import com.assignment2.processing.Processor;
import com.assignment2.processing.interfaces.Persistent;
import com.assignment2.readerwriter.ReaderCLI;
import com.assignment2.readerwriter.Utility;
import com.assignment2.readerwriter.WriterCLI;
import com.assignment2.readerwriter.interfaces.ReaderStudentEntity;
import com.assignment2.readerwriter.interfaces.WriterStudentEntity;
import com.assignment2.student.Student;
import com.assignment2.student.StudentDTO;
import java.io.IOException;
import java.util.ArrayList;

public final class Runner {

  private static final Processor processor = new Processor();
  private static final Utility UTILITY = new Utility();
  private static final Persistent persistor = new PersistorUsingFileIO();
  private static final ReaderStudentEntity reader = new ReaderCLI();
  private static final WriterStudentEntity writer = new WriterCLI();

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
        final Option option = UTILITY.displayMainMenu(); //Input option
        switch (option) {
          case ADD:
            studentDTO = reader.readDetails();
            try {
              students = processor.addAfterValidation(students, studentDTO);
              UTILITY.showMessage("Successfully Added the record!");
            } catch (Exception except) {
              UTILITY.showMessage("Invalid format or "
                      + "record/roll-number already existing");
            }
            break;
          case DISPLAY:
            try {
              writer.writeDetails(students);
            } catch (GenericApplicationException exception) {
              UTILITY.showMessage(exception.getMessage());
            }
            break;
          case DELETE:
            //Input roll number
            final int rollNoToDelete = UTILITY.deleteStudent();
            try {
              students = processor.deleteIfRecordExists(students, rollNoToDelete);
              UTILITY.showMessage("Successfully Deleted");
            } catch (Exception exception) {
              UTILITY.showMessage("Roll number not in records!");
            }
            break;
          case SAVE:
            try {
              persistor.store(students);
              UTILITY.showMessage("Successfully Saved");
            } catch (Exception exception) {
              UTILITY.showMessage("Failed to save!");
            }
            break;
          case EXIT:
            UTILITY.showMessage("Thank You for using the application..");
            shouldExit = true;
            break;
          default:
            UTILITY.showMessage("Invalid Input! Please Enter a valid Input!");
            break;
        }
      } catch (IllegalArgumentException exception) {
        UTILITY.showMessage("Sorry, you entered Invalid input. "
                + "Please enter the inputs as shows in Brackets of the option.");
      }
    }
  }
}
