package com.assignment2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.assignment2.exceptions.GenericApplicationException;
import com.assignment2.processing.Validator;
import com.assignment2.interfaces.Valid;
import com.assignment2.student.Student;
import com.assignment2.student.StudentDTO;
import java.util.TreeSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class ValidatorTest {

  private final TreeSet<Character> courses = new TreeSet<>();
  private final TreeSet<String> myCourses = new TreeSet<>();

  private Valid validator;

  @BeforeEach
  void createObject() {
    validator = new Validator();
  }

  @Test
  @DisplayName("Testing the validator (Should Pass)")
  void validatorTest1() throws GenericApplicationException {
    courses.add('A');
    courses.add('B');
    courses.add('C');
    courses.add('D');
    Student student = new Student(
            "Arjun Singh", 22,
            "317 N Block", 2017021030,
            courses);
    myCourses.add("A");
    myCourses.add("B");
    myCourses.add("C");
    myCourses.add("D");
    assertEquals(student.toString(), validator.validate(new StudentDTO(
            "Arjun Singh", "22",
            "317 N Block",
            "2017021030", myCourses)).toString());
  }

  @Test
  @DisplayName("Testing the validator (Should Pass)")
  void validatorTest2() throws GenericApplicationException {
    courses.add('A');
    courses.add('B');
    courses.add('C');
    courses.add('D');
    Student student = new Student(
            "Arpit Patel", 21,
            "Lucknow", 2017021031, courses);
    myCourses.add("A");
    myCourses.add("B");
    myCourses.add("C");
    myCourses.add("D");
    assertEquals(student.toString(), validator.validate(new StudentDTO(
            "Arpit Patel", "21", "Lucknow",
            "2017021031", myCourses)).toString());
  }

  @Test
  @DisplayName("Name has digits (Should Fail)")
  void validatorTest3() {
    myCourses.add("A");
    myCourses.add("B");
    myCourses.add("C");
    myCourses.add("D");
    assertThrows(GenericApplicationException.class, () -> validator.validate(new StudentDTO(
            "Arjun12121212", "22",
            "317 N Block", "2017021030", myCourses)));
  }

  @Test
  @DisplayName("Name length >25 (Should Fail)")
  void validatorTest3a() {
    myCourses.add("A");
    myCourses.add("B");
    myCourses.add("C");
    myCourses.add("D");
    assertThrows(GenericApplicationException.class, () -> validator.validate(new StudentDTO(
            "Arjun Singh Arjun Singh Arjun Singh Arjun Singh",
            "22", "317 N Block", "2017021030", myCourses)));
  }

  @Test
  @DisplayName("Age incorrect - Float. (Should Fail)")
  void validatorTest4() {
    TreeSet<String> myCourses = new TreeSet<>();
    myCourses.add("A");
    myCourses.add("B");
    myCourses.add("C");
    myCourses.add("D");
    assertThrows(GenericApplicationException.class, () -> validator.validate(new StudentDTO(
            "Arjun Singh", "22.5",
            "317 N Block", "2017021030", myCourses)));
  }

  @Test
  @DisplayName("Age incorrect format (Should Fail)")
  void validatorTest5() {
    TreeSet<String> myCourses = new TreeSet<>();
    myCourses.add("A");
    myCourses.add("B");
    myCourses.add("C");
    myCourses.add("D");
    assertThrows(GenericApplicationException.class, () -> validator.validate(new StudentDTO(
            "Arjun Singh", "Twenty Two",
            "317 N Block", "2017021030", myCourses)));
  }

  @Test
  @DisplayName("Age 0 (Should Fail)")
  void validatorTest5a() {
    TreeSet<String> myCourses = new TreeSet<>();
    myCourses.add("A");
    myCourses.add("B");
    myCourses.add("C");
    myCourses.add("D");
    assertThrows(GenericApplicationException.class, () -> validator.validate(new StudentDTO(
            "Arjun Singh", "0", "317 N Block",
            "2017021030", myCourses)));
  }

  @Test
  @DisplayName("Age >100 (Should Fail)")
  void validatorTest5b() {
    TreeSet<String> myCourses = new TreeSet<>();
    myCourses.add("A");
    myCourses.add("B");
    myCourses.add("C");
    myCourses.add("D");
    assertThrows(GenericApplicationException.class, () -> validator.validate(new StudentDTO(
            "Arjun Singh", "200", "317 N Block",
            "2017021030", myCourses)));
  }

  @Test
  @DisplayName("Address only has digits (Should Fail)")
  void validatorTest6() {
    TreeSet<String> myCourses = new TreeSet<>();
    myCourses.add("A");
    myCourses.add("B");
    myCourses.add("C");
    myCourses.add("D");
    assertThrows(GenericApplicationException.class, () -> validator.validate(new StudentDTO(
            "Arjun Singh", "22", "317232323",
            "2017021030", myCourses)));
  }

  @Test
  @DisplayName("Address too big (Should Fail)")
  void validatorTest6a() {
    TreeSet<String> myCourses = new TreeSet<>();
    myCourses.add("A");
    myCourses.add("B");
    myCourses.add("C");
    myCourses.add("D");
    assertThrows(GenericApplicationException.class, () -> validator.validate(new StudentDTO(
            "Arjun Singh", "22", "B Block 345 sdkcijbi"
            + "sk sckijnksjdnckjsdnc knckisdbcisdbciscbi cisduj"
            + "bcisdb", "2017021030", myCourses)));
  }

  @Test
  @DisplayName("Address blank (Should Fail)")
  void validatorTest6b() {
    TreeSet<String> myCourses = new TreeSet<>();
    myCourses.add("A");
    myCourses.add("B");
    myCourses.add("C");
    myCourses.add("D");
    assertThrows(GenericApplicationException.class, () -> validator.validate(new StudentDTO(
            "Arjun Singh", "22", "   ",
            "2017021030", myCourses)));
  }

  @Test
  @DisplayName("Roll no. too big (Should Fail)")
  void validatorTest7() {
    TreeSet<String> myCourses = new TreeSet<>();
    myCourses.add("A");
    myCourses.add("B");
    myCourses.add("C");
    myCourses.add("D");
    assertThrows(GenericApplicationException.class, () -> validator.validate(new StudentDTO(
            "Arjun Singh", "22", "317 N Block",
            "201702103012331231231231", myCourses)));
  }

  @Test
  @DisplayName("Roll no. blank (Should Fail)")
  void validatorTest7a() {
    TreeSet<String> myCourses = new TreeSet<>();
    myCourses.add("A");
    myCourses.add("B");
    myCourses.add("C");
    myCourses.add("D");
    assertThrows(GenericApplicationException.class, () -> validator.validate(new StudentDTO(
            "Arjun Singh", "22", "317 N Block",
            "  ", myCourses)));
  }

  @Test
  @DisplayName("Roll no. 0 (Should Fail)")
  void validatorTest7b() {
    TreeSet<String> myCourses = new TreeSet<>();
    myCourses.add("A");
    myCourses.add("B");
    myCourses.add("C");
    myCourses.add("D");
    assertThrows(GenericApplicationException.class, () -> validator.validate(new StudentDTO(
            "Arjun Singh", "22",
            "317 N Block", "0", myCourses)));
  }

  @Test
  @DisplayName("Courses less than 4 - due to duplicate entry (Should Fail)")
  void validatorTest8() {
    TreeSet<String> myCourses = new TreeSet<>();
    myCourses.add("A");
    myCourses.add("B");
    myCourses.add("C");
    myCourses.add("C");
    assertThrows(GenericApplicationException.class, () -> validator.validate(new StudentDTO(
            "Arjun Singh", "22", "317 N Block",
            "2017021030", myCourses)));
  }

  @Test
  @DisplayName("Courses format wrong (Should Fail)")
  void validatorTest9() {
    TreeSet<String> myCourses = new TreeSet<>();
    myCourses.add("A");
    myCourses.add("B");
    myCourses.add("Cat");
    myCourses.add("Dog12");
    assertThrows(GenericApplicationException.class, () -> validator.validate(new StudentDTO(
            "Arjun Singh", "22", "317 N Block",
            "2017021030", myCourses)));
  }

  @Test
  @DisplayName("Blank Entry (Should Fail)")
  void validatorTest10() {
    TreeSet<String> myCourses = new TreeSet<>();
    myCourses.add("A");
    myCourses.add(" ");
    myCourses.add("C");
    myCourses.add("D");
    assertThrows(GenericApplicationException.class, () -> validator.validate(new StudentDTO(
            " ", "22", "317 N Block", " ", myCourses)));
  }
}