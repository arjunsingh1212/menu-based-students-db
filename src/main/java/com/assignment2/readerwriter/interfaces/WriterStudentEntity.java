package com.assignment2.readerwriter.interfaces;

import com.assignment2.exceptions.GenericApplicationException;
import com.assignment2.student.Student;
import java.io.IOException;
import java.util.ArrayList;

public interface WriterStudentEntity {
  void writeDetails(ArrayList<Student> students)
          throws GenericApplicationException, IOException;
}
