package com.assignment2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.assignment2.exceptions.GenericApplicationException;
import com.assignment2.processing.Processor;
import com.assignment2.student.Student;
import com.assignment2.student.StudentDTO;
import java.util.ArrayList;
import java.util.TreeSet;
import org.junit.jupiter.api.Test;


class ProcessorTest {

  @Test
  void addAfterValidationTest() throws GenericApplicationException {
    TreeSet<String> myCourses = new TreeSet<>();
    myCourses.add("A");
    myCourses.add("B");
    myCourses.add("C");
    myCourses.add("D");
    StudentDTO studentDTO = new StudentDTO(
            "Arjun Singh", "22", "317 N Block",
            "2017021030", myCourses);
    ArrayList<Student> studentsTest = new ArrayList<>();

    TreeSet<Character> courses = new TreeSet<>();
    courses.add('A');
    courses.add('B');
    courses.add('C');
    courses.add('D');
    Student student = new Student(
            "Arjun Singh", 22, "317 N Block",
            2017021030, courses);
    ArrayList<Student> students = new ArrayList<>();
    students.add(student);

    assertEquals(students.toString(),
            new Processor().addAfterValidation(studentsTest, studentDTO).toString());
  }

  @Test
  void addAfterValidationTestValidationFailing() {
    TreeSet<String> myCourses = new TreeSet<>();
    myCourses.add("A");
    myCourses.add("B");
    myCourses.add("C");
    myCourses.add("D");
    StudentDTO studentDTO = new StudentDTO(
            "Arjun", "220",
            "317 N Block", "30", myCourses);
    ArrayList<Student> studentsTest = new ArrayList<>();

    assertThrows(GenericApplicationException.class, () -> new Processor().addAfterValidation(
            studentsTest, studentDTO));
  }

  @Test
  void deleteIfRecordExistsTest() throws GenericApplicationException {
    ArrayList<Student> students = new ArrayList<>();

    TreeSet<Character> courses = new TreeSet<>();
    courses.add('A');
    courses.add('B');
    courses.add('C');
    courses.add('D');
    Student student = new Student(
            "Arjun Singh", 22, "317 N Block",
            2017021030, courses);
    ArrayList<Student> studentsTest = new ArrayList<>();
    studentsTest.add(student);

    int rollNumber = 2017021030;

    assertEquals(students.toString(),
            new Processor().deleteIfRecordExists(studentsTest, rollNumber).toString());
  }

  @Test
  void deleteIfRecordExistsTestNonExistence() {
    ArrayList<Student> studentsTest = new ArrayList<>();

    int rollNumber = 2017021030;

    assertThrows(GenericApplicationException.class, () -> new Processor().deleteIfRecordExists(
            studentsTest, rollNumber));
  }
}