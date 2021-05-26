package com.assignment2.processing.interfaces;

import com.assignment2.exceptions.RuntimeExceptionCustom;
import com.assignment2.student.Student;
import com.assignment2.student.StudentDTO;

@SuppressWarnings("PMD.CommentRequired")
public interface Valid {
  Student validate(StudentDTO studentDTO) throws RuntimeExceptionCustom;
}
