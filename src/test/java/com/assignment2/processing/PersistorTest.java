package com.assignment2.processing;

import com.assignment2.student.Student;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersistorTest {

  private final TreeSet<Character> myCourses = new TreeSet<>();
  private final Student student1 = new Student("Arjun",22,"317 N Block",2017021030,myCourses);
  private final Student student2 = new Student("Arpit Patel",21,"Lucknow",2017021031,myCourses);

  @Test
  void storeTest() {
    try {
      Persistent persistor = new Persistor();
      ArrayList<Student> students = new ArrayList<>();
      students.add(student1); students.add(student2);
      persistor.store(students,"StudentsDB.txt");

      FileInputStream fileInputStream = new FileInputStream("StudentsDB.txt");
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
      Student stud1 = (Student) objectInputStream.readObject();
      Student stud2 = (Student) objectInputStream.readObject();
      objectInputStream.close();
      fileInputStream.close();

      assertEquals(stud1.toString()+stud2.toString(),student1.toString()+ student2);

    } catch (ClassNotFoundException | IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  void loadTest() {
    try {
      FileOutputStream fileOutputStream = new FileOutputStream("StudentsDB.txt");
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
      objectOutputStream.writeObject(student1);
      objectOutputStream.writeObject(student2);
      objectOutputStream.close();
      fileOutputStream.close();
      ArrayList<Student> tempStudents = new ArrayList<>();
      tempStudents.add(student1); tempStudents.add(student2);

      Persistent persistor = new Persistor();
      ArrayList<Student> students;
      students = persistor.load("StudentsDB.txt");

      assertEquals(tempStudents.toString(),students.toString());

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}