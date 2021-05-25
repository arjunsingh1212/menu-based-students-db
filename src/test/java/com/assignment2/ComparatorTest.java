package com.assignment2.processing;

import com.assignment2.student.comparators.AddressComparator;
import com.assignment2.student.comparators.AgeComparator;
import com.assignment2.student.Student;
import com.assignment2.student.comparators.RollNumberComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.TreeSet;

public class ComparatorTest {

  private final TreeSet<Character> courses=new TreeSet<>();

  @BeforeEach
  void init() {
    courses.add('A'); courses.add('B'); courses.add('C'); courses.add('D');
  }

  @Test
  @DisplayName("Name based, branch 1")
  void nameTest1() {
    Student student1 = new Student("Arpit Patel",21,"Lucknow",2017021031, courses);
    Student student2 = new Student("Arjun Singh",22,"317 N Block",2017021030, courses);
    assertEquals(1,student1.compareTo(student2));
  }

  @Test
  @DisplayName("Same Name, then roll no. based")
  void nameTest2() {
    Student student1 = new Student("Arpit Patel",21,"Lucknow",2017021031, courses);
    Student student2 = new Student("Arpit Patel",22,"317 N Block",2017021030, courses);
    assertEquals(1,student1.compareTo(student2));
  }

  @Test
  @DisplayName("Name based, branch 2")
  void nameTest3() {
    Student student1 = new Student("Arjun Singh",22,"317 N Block",2017021030, courses);
    Student student2 = new Student("Arpit Patel",21,"Lucknow",2017021031, courses);
    assertEquals(-1,student1.compareTo(student2));
  }


  @Test
  @DisplayName("Age based, branch 1")
  void nameTest4() {
    Student student1 = new Student("Arjun Singh",22,"317 N Block",2017021030, courses);
    Student student2 = new Student("Arpit Patel",21,"Lucknow",2017021031, courses);
    assertEquals(1,new AgeComparator().compare(student1,student2));
  }

  @Test
  @DisplayName("Same Age, then roll no. based")
  void nameTest5() {
    Student student1 = new Student("Arpit Patel",21,"Lucknow",2017021031, courses);
    Student student2 = new Student("Arjun Singh",21,"317 N Block",2017021030, courses);
    assertEquals(1,new AgeComparator().compare(student1,student2));
  }

  @Test
  @DisplayName("Age based, branch 2")
  void nameTest6() {
    Student student1 = new Student("Arpit Patel",21,"Lucknow",2017021031, courses);
    Student student2 = new Student("Arjun Singh",22,"317 N Block",2017021030, courses);
    assertEquals(-1,new AgeComparator().compare(student1,student2));
  }

  @Test
  @DisplayName("Address based, branch 1")
  void nameTest7() {
    Student student1 = new Student("Arpit Patel",21,"Lucknow",2017021031, courses);
    Student student2 = new Student("Arjun Singh",22,"317 N Block",2017021030, courses);
    assertEquals(1,new AddressComparator().compare(student1,student2));
  }

  @Test
  @DisplayName("Same Address, then roll no. based")
  void nameTest8() {
    Student student1 = new Student("Arpit Patel",21,"Lucknow",2017021031, courses);
    Student student2 = new Student("Arjun Singh",21,"Lucknow",2017021030, courses);
    assertEquals(1,new AddressComparator().compare(student1,student2));
  }

  @Test
  @DisplayName("Address based, branch 2")
  void nameTest9() {
    Student student1 = new Student("Arjun Singh",22,"317 N Block",2017021030, courses);
    Student student2 = new Student("Arpit Patel",21,"Lucknow",2017021031, courses);
    assertEquals(-1,new AddressComparator().compare(student1,student2));
  }

  @Test
  @DisplayName("Roll Number based, branch 1")
  void nameTest10() {
    Student student1 = new Student("Arpit Patel",21,"Lucknow",2017021031, courses);
    Student student2 = new Student("Arjun Singh",22,"317 N Block",2017021030, courses);
    assertEquals(1,new RollNumberComparator().compare(student1,student2));
  }

  @Test
  @DisplayName("Roll Number based, branch 2")
  void nameTest11() {
    Student student1 = new Student("Arjun Singh",22,"317 N Block",2017021030, courses);
    Student student2 = new Student("Arpit Patel",21,"Lucknow",2017021031, courses);
    assertEquals(-1,new RollNumberComparator().compare(student1,student2));
  }

}
