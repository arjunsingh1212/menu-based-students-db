package com.assignment2.printer;


@SuppressWarnings({"PMD.SystemPrintln"})
public class Print {

  public void displayMainMenu() {
    System.out.println("\nWelcome to the Students Database. Selection your option.\n"
            + "1. Add User details. (ADD) \n"
            + "2. Display User details. (DISPLAY)\n"
            + "3. Delete User details (DELETE) \n"
            + "4. Save User details. (SAVE)\n"
            + "5. Exit (EXIT)\n"
            + "Enter your choice: ");
  }

  public void displaySubMenuParam() {
    System.out.println("Please tell, according to which field you want to display sorted details\n"
            + "1 - Name (NAME) \n"
            + "2- Age (AGE)\n"
            + "3- Address (ADDRESS) \n"
            + "4- Roll Number (ROLLNUMBER)\n"
            + "Enter your choice: ");
  }

  public void displaySubMenuOrder() {
    System.out.println("Please tell, according to order want to display sorted details\n"
            + "1 - Ascending (ASC) \n"
            + "2- Descending (DESC)\n"
            + "Enter your choice: ");
  }

  public void displayDeleteMenu() {
    System.out.println("Please enter the Roll Number of Student whose record is to be deleted: ");
  }

  public void showMessage(final String message) {
    System.out.println(message);
  }
}
