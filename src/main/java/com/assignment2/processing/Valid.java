package com.assignment2.processing;

import com.assignment2.student.Student;
import com.assignment2.student.StudentDTO;

public interface Valid {
  Student validate(StudentDTO studentDTO);
}
