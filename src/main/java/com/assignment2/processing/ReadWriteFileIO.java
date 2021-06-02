package com.assignment2.processing;

import com.assignment2.interfaces.Reader;
import com.assignment2.interfaces.Writer;
import com.assignment2.printer.Print;
import com.assignment2.student.Student;

import java.io.*;
import java.util.ArrayList;

public class ReadWriteFileIO implements Reader, Writer {

  private final String filepath;

  public ReadWriteFileIO(String filepath) {
    this.filepath = filepath;
  }

  @Override
  public ArrayList<Student> read() {
    final ArrayList<Student> students = new ArrayList<>();
    FileInputStream fileInputStream = null;
    try {
      fileInputStream = new FileInputStream(filepath);
    } catch (FileNotFoundException except) {
      return students;
    }
    ObjectInputStream objectInputStream = null;
    try {
      objectInputStream = new ObjectInputStream(fileInputStream);
    } catch (IOException exception) {
      new Print().showMessage(exception.getMessage());
    }
    Student student;
    try {
      student = (Student) objectInputStream.readObject();
      while (student != null) {
        students.add(student);
        student = (Student) objectInputStream.readObject();
      }
    } catch (ClassNotFoundException | IOException except) {
      new Print().showMessage("Loading Done.");
    }
    try {
      objectInputStream.close();
      fileInputStream.close();
    } catch (IOException exception) {
      exception.printStackTrace();
    }
    return students;
  }

  @Override
  public void write(final ArrayList<Student> students) {
    FileOutputStream fileOutputStream = null;
    ObjectOutputStream objectOutputStream = null;
    try {
      fileOutputStream = new FileOutputStream(filepath);
    } catch (FileNotFoundException exception) {
      new File(filepath);
    }
    try {
      objectOutputStream = new ObjectOutputStream(fileOutputStream);
    } catch (IOException e) {
      new Print().showMessage(e.getMessage());
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

}
