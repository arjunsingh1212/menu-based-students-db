package com.assignment2.processing;

import com.assignment2.processing.interfaces.ReaderWriter;
import com.assignment2.readerwriter.Utility;
import com.assignment2.student.Student;

import java.io.*;
import java.util.ArrayList;

public class PersistorUsingFileIO implements ReaderWriter {

  private final String filepath;

  public PersistorUsingFileIO(String filepath) {
    this.filepath = filepath;
  }

  @Override
  public void store(final ArrayList<Student> students) {
    FileOutputStream fileOutputStream = null;
    ObjectOutputStream objectOutputStream = null;
    try {
      fileOutputStream = new FileOutputStream(filepath);
//      final FileOutputStream fileOutputStream = new FileOutputStream(
//              "src\\main\\java\\com\\assignment2\\StudentsDB.txt");

    } catch (FileNotFoundException exception) {
      new File(filepath);
    }
    try {
      objectOutputStream = new ObjectOutputStream(fileOutputStream);
    } catch (IOException e) {
      new Utility().showMessage(e.getMessage());
    }
    try {
      for (final Student student : students) {
        objectOutputStream.writeObject(student);
      }
      objectOutputStream.close();
      fileOutputStream.close();
    } catch (IOException | NullPointerException exception) {
      exception.printStackTrace();
    }
  }

  @Override
  public ArrayList<Student> load() {
    final ArrayList<Student> students = new ArrayList<>();
    FileInputStream fileInputStream = null;
    try {
      fileInputStream = new FileInputStream(filepath);
//      final FileInputStream fileInputStream = new FileInputStream(
//              "src\\main\\java\\com\\assignment2\\StudentsDB.txt");
    } catch (FileNotFoundException except) {
      return students;
    }
    ObjectInputStream objectInputStream = null;
    try {
      objectInputStream = new ObjectInputStream(fileInputStream);
    } catch (IOException exception) {
      new Utility().showMessage(exception.getMessage());
    }
    Student student;
    try {
      student = (Student) objectInputStream.readObject();
      while (student != null) {
        students.add(student);
        student = (Student) objectInputStream.readObject();
      }
    } catch (ClassNotFoundException | IOException except) {
      new Utility().showMessage("Loading Done.");
    }
    try {
      objectInputStream.close();
      fileInputStream.close();
    } catch (IOException exception) {
      exception.printStackTrace();
    }
    return students;
  }
}
