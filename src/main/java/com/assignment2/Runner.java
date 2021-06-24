package com.assignment2;

import com.assignment2.enums.Option;
import com.assignment2.processing.ReadWriteFileIO;
import com.assignment2.processing.Processor;
import com.assignment2.commandline.ReaderCLI;
import com.assignment2.printer.Print;
import com.assignment2.commandline.WriterCLI;
import com.assignment2.student.Student;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public final class Runner {

  private static final String filepath = "src\\main\\java\\com\\assignment2\\StudentsDB.txt";
  private static final Processor processor = new Processor();
  private static final Print PRINT = new Print();
  private static final ReadWriteFileIO persistor = new ReadWriteFileIO(filepath);
  private static final ReaderCLI reader = new ReaderCLI();
  private static final WriterCLI writer = new WriterCLI();

  private static final transient BufferedReader bufferedReader = new BufferedReader(
          new InputStreamReader(System.in));

  private Runner() {
  }

  public static void main(final String[] args) throws IOException {

    ArrayList<Student> students = persistor.read();

    ArrayList<Student> studentsCLI;

    boolean shouldExit = false;
    while (!shouldExit) {
      try {
        PRINT.displayMainMenu(); //Input option
        final Option option = Option.valueOf(bufferedReader.readLine());
        switch (option) {
          case ADD:
            studentsCLI = reader.read();
            try {
              students = processor.addIfNoDuplicate(students, studentsCLI);
              PRINT.showMessage("Successfully Added the record!");
            } catch (Exception except) {
              PRINT.showMessage("Invalid format or "
                      + "record/roll-number already existing");
            }
            break;
          case DISPLAY:
            writer.write(students);
            break;
          case DELETE:
            //Input roll number
            PRINT.displayDeleteMenu();
            final int rollNoToDelete = Integer.parseInt(bufferedReader.readLine());
            try {
              students = processor.deleteIfRecordExists(students, rollNoToDelete);
              PRINT.showMessage("Successfully Deleted");
            } catch (Exception exception) {
              PRINT.showMessage("Roll number not in records!");
            }
            break;
          case SAVE:
            try {
              persistor.write(students);
              PRINT.showMessage("Successfully Saved");
            } catch (Exception exception) {
              PRINT.showMessage("Failed to save!");
            }
            break;
          case EXIT:
            PRINT.showMessage("Do you want to save changes? (y for yes): ");
            String confirmation = bufferedReader.readLine();
            if(confirmation.equals("y")) {
              persistor.write(students);
              PRINT.showMessage("Successfully Saved");
            }
            PRINT.showMessage("Thank You for using the application..");
            shouldExit = true;
            break;
          default:
            PRINT.showMessage("Invalid Input! Please Enter a valid Input!");
            break;
        }
      } catch (IllegalArgumentException exception) {
        PRINT.showMessage("Sorry, you entered Invalid input. "
                + "Please enter the inputs as shows in Brackets of the option.");
      }
    }
  }
}
