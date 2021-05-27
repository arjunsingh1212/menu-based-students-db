package com.assignment2.readerwriter;

import com.assignment2.enums.Order;
import com.assignment2.enums.Param;
import com.assignment2.exceptions.GenericApplicationException;
import com.assignment2.readerwriter.interfaces.WriterStudentEntity;
import com.assignment2.student.Student;
import com.assignment2.student.comparators.AddressComparator;
import com.assignment2.student.comparators.AgeComparator;
import com.assignment2.student.comparators.RollNumberComparator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class WriterCLI implements WriterStudentEntity {

  private static final AgeComparator ageComparator = new AgeComparator();
  private static final AddressComparator addressComparator = new AddressComparator();
  private static final RollNumberComparator rollNumberComparator = new RollNumberComparator();
  private static final Utility UTILITY = new Utility();

  @Override
  public void writeDetails(ArrayList<Student> students)
          throws GenericApplicationException, IOException {

    Param param;
    Order order;
    try {
      //Input parameter according to which you want sorting
      param = UTILITY.displaySubMenuParam();
      //Input order - ascending or descending
      order = UTILITY.displaySubMenuOrder();
    } catch (IllegalArgumentException exception) {
      UTILITY.showMessage("Entered wrong input."
              + " Please enter the inputs as shows in Brackets of the option."
              + "Try using the menu again.");
      throw new GenericApplicationException("Invalid input for Param or Order");
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
      System.out.println(student.getFullName()
              + ", " + student.getRollNumber()
              + ", " + student.getAge()
              + ", " + student.getAddress()
              + ", " + student.getCourses().toString());
    }
  }
}
