package com.assignment2;

import com.assignment2.processing.ReadWriteFileIO;
import com.assignment2.interfaces.Reader;
import com.assignment2.student.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.TreeSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class PersistorTest {

  private Student student1;
  private Student student2;
  private ReadWriteFileIO persistor;

  @BeforeEach
  void init() {
    TreeSet<Character> courses = new TreeSet<>();
    courses.add('A');
    courses.add('B');
    courses.add('C');
    courses.add('D');
    student1 = new Student("Arjun", 22,
            "317 N Block", 2017021030, courses);
    student2 = new Student("Arpit Patel", 21,
            "Lucknow", 2017021031, courses);

    persistor = new ReadWriteFileIO("src\\main\\java\\com\\assignment2\\StudentsDB.txt");
  }

  @Test
  void storeTest() {
    try {
      ArrayList<Student> students = new ArrayList<>();
      students.add(student1);
      students.add(student2);
      persistor.write(students);
      FileInputStream fileInputStream = new FileInputStream(
              "src\\main\\java\\com\\assignment2\\StudentsDB.txt");
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
      Student stud1 = (Student) objectInputStream.readObject();
      Student stud2 = (Student) objectInputStream.readObject();
      objectInputStream.close();
      fileInputStream.close();

      assertEquals(stud1.toString() + stud2.toString(),
              student1.toString() + student2);

    } catch (ClassNotFoundException | IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  void loadTest() {
    try {
      FileOutputStream fileOutputStream = new FileOutputStream(
              "src\\main\\java\\com\\assignment2\\StudentsDB.txt");
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
      objectOutputStream.writeObject(student1);
      objectOutputStream.writeObject(student2);
      objectOutputStream.close();
      fileOutputStream.close();
      ArrayList<Student> tempStudents = new ArrayList<>();
      tempStudents.add(student1);
      tempStudents.add(student2);

      ArrayList<Student> students;
      students = persistor.read();

      assertEquals(tempStudents.toString(), students.toString());

    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  @Test
  void testForFileNotFound() {
    Reader persistor = new ReadWriteFileIO("src\\main\\java\\com\\assignment2\\childrenDB.txt");
    final ArrayList<Student> students = new ArrayList<>();
    assertEquals(students.toString(),persistor.read().toString());
  }

  void fileNotFoundExcept() throws FileNotFoundException {
    new FileInputStream("src\\main\\java\\com\\assignment2\\childrenDB.txt");
  }
  @Test
  void testFileNotFound2() {
    assertThrows(FileNotFoundException.class, () -> fileNotFoundExcept());
  }

}