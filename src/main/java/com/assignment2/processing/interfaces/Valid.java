package com.assignment2.processing.interfaces;

import com.assignment2.exceptions.GenericApplicationException;
import com.assignment2.student.Student;
import com.assignment2.student.StudentDTO;

public interface Valid {
  Student validate(StudentDTO studentDTO) throws GenericApplicationException;
}
