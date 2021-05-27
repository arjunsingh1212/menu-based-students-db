package com.assignment2.processing;

import com.assignment2.processing.interfaces.Persistent;
import com.assignment2.readerwriter.Utility;
import com.assignment2.student.Student;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class PersistorUsingFileIO implements Persistent {

  @Override
  public void store(final ArrayList<Student> students) {
    try {
      final FileOutputStream fileOutputStream = new FileOutputStream(
              "src\\main\\java\\com\\assignment2\\StudentsDB.txt");
      final ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
      for (final Student student : students) {
        objectOutputStream.writeObject(student);
      }
      objectOutputStream.close();
      fileOutputStream.close();
    } catch (IOException e) {
      new Utility().showMessage(e.getMessage());
    }
  }

  @Override
  public ArrayList<Student> load() {
    final ArrayList<Student> students = new ArrayList<>();
    try {
      final FileInputStream fileInputStream = new FileInputStream(
              "src\\main\\java\\com\\assignment2\\StudentsDB.txt");
      final ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
      Student student;
      try {
        student = (Student) objectInputStream.readObject();
        while (student != null) {
          students.add(student);
          student = (Student) objectInputStream.readObject();
        }
      } catch (EOFException except) {
        new Utility().showMessage("Loading Done.");
      }
      objectInputStream.close();
      fileInputStream.close();
    } catch (ClassNotFoundException | IOException e) {
      new Utility().showMessage(e.getMessage());
    }
    return students;
  }

  @Override
  public boolean testConnectivity(final String filePath) {
    return new File(filePath).exists();
  }
}
