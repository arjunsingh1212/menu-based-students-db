package com.assignment2.commandline;

import com.assignment2.enums.Order;
import com.assignment2.enums.Param;
import com.assignment2.interfaces.Writer;
import com.assignment2.printer.Print;
import com.assignment2.student.Student;
import com.assignment2.student.comparators.AddressComparator;
import com.assignment2.student.comparators.AgeComparator;
import com.assignment2.student.comparators.RollNumberComparator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class WriterCLI implements Writer {

  private static final AgeComparator ageComparator = new AgeComparator();
  private static final AddressComparator addressComparator = new AddressComparator();
  private static final RollNumberComparator rollNumberComparator = new RollNumberComparator();
  private static final Print PRINT = new Print();
  private static final transient BufferedReader bufferedReader = new BufferedReader(
          new InputStreamReader(System.in));

  @Override
  public void write(ArrayList<Student> students) {

    Param param = null;
    Order order = null;
    try {
      //Input parameter according to which you want sorting
      PRINT.displaySubMenuParam();
      param = Param.valueOf(bufferedReader.readLine());
      //Input order - ascending or descending
      PRINT.displaySubMenuOrder();
      order = Order.valueOf(bufferedReader.readLine());
    } catch (IllegalArgumentException exception) {
      PRINT.showMessage("Entered wrong input."
              + " Please enter the inputs as shows in Brackets of the option."
              + "Try using the menu again.");
    } catch (IOException except) {
      PRINT.showMessage(except.getMessage());
    }

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
        break;
    }
    if (order == Order.DESC) {
      Collections.reverse(students);
    }

    System.out.println("Name, Roll Number, Age, Address, Courses");
    for (final Student student : students) {
      System.out.println(student.toString());
    }
  }
}
