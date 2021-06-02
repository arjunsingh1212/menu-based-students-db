package com.assignment2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.assignment2.exceptions.GenericApplicationException;
import com.assignment2.processing.Processor;
import com.assignment2.student.Student;
import java.util.ArrayList;
import java.util.TreeSet;
import org.junit.jupiter.api.Test;


class ProcessorTest {

  @Test
  void addAfterValidationTest() {
    TreeSet<Character> myCourses = new TreeSet<>();
    myCourses.add('A');
    myCourses.add('B');
    myCourses.add('C');
    myCourses.add('D');
    Student student1 = new Student(
            "Arjun Singh", 22, "317 N Block",
            2017021030, myCourses);
    ArrayList<Student> studentsTemp = new ArrayList<>();
    studentsTemp.add(student1);

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
            new Processor().addIfNoDuplicate(studentsTest, studentsTemp).toString());
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