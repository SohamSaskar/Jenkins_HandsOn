# CodeVault: Developer Snippet Manager

CodeVault is a lightweight, beginner-friendly console application written in Java 21 using Apache Maven. It helps developers organize and manage their reusable code snippets directly from the command line.

The application follows clean Object-Oriented Programming (OOP) principles and stores all snippets in-memory using an `ArrayList`.

---

## Features

1. **Add Code Snippet**: Save snippets with a title, programming language, and multi-line source code. IDs are automatically generated.
2. **Display All Snippets**: View all saved snippets with their ID, title, language, and code formatted in clear, readable blocks.
3. **Search Snippets**: Search existing snippets by title, by programming language, or across both fields using case-insensitive keyword matching.
4. **Delete Snippet by ID**: Remove a snippet using its unique numerical ID with error handling for non-existent IDs.
5. **Display Total Count**: Quickly view the total number of snippets currently saved in the vault.

---

## Technical Specifications

- **Language**: Java 21 (LTS)
- **Build Tool**: Apache Maven
- **Storage**: In-memory storage using `java.util.ArrayList`
- **User Interface**: Menu-driven standard console interface (`java.util.Scanner`)
- **Testing**: JUnit 5 (`org.junit.jupiter`)
- **Dependencies**: No external runtime dependencies, no database, no GUI frameworks, no Spring Boot.

---

## Project Structure

```
CodeVault/
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   └── java/
    │       └── com/
    │           └── codevault/
    │               ├── Main.java       # Console menu and user interaction logic
    │               ├── Snippet.java    # Snippet model (id, title, language, code)
    │               └── CodeVault.java  # Snippet collection manager (ArrayList)
    └── test/
        └── java/
            └── com/
                └── codevault/
                    ├── SnippetTest.java    # Unit tests for Snippet class
                    └── CodeVaultTest.java  # Unit tests for CodeVault operations
```

---

## Prerequisites

Before running the application, ensure you have the following installed on your machine:

- **Java Development Kit (JDK) 21** or higher:
  ```bash
  java -version
  ```
- **Apache Maven 3.8+**:
  ```bash
  mvn -v
  ```

---

## Building the Application

To clean, compile, execute unit tests, and package the application into an executable JAR file, run:

```bash
mvn clean install
```

When the build finishes, Maven will display `BUILD SUCCESS` and place the packaged JAR in the `target/` directory:
- `target/codevault-1.0-SNAPSHOT.jar`

---

## Running the Application

### Option 1: Run the Executable JAR

```bash
java -jar target/codevault-1.0-SNAPSHOT.jar
```

### Option 2: Run via Maven Exec Plugin

```bash
mvn compile exec:java
```

---

## Running Tests

To run the automated JUnit 5 test suite:

```bash
mvn test
```

---

## Sample Usage Walkthrough

```text
========================================
  CodeVault: Developer Snippet Manager  
========================================
----------------------------------------
MAIN MENU
----------------------------------------
1. Add a new snippet
2. Display all snippets
3. Search snippets
4. Delete a snippet by ID
5. Display total number of snippets
6. Exit
----------------------------------------
Enter your choice (1-6): 1

--- Add Code Snippet ---
Enter snippet title: Reverse a String
Enter programming language: Java
Enter code (type 'END' on a new line to finish):
public static String reverse(String str) {
    return new StringBuilder(str).reverse().toString();
}
END
Success: Snippet added with ID: 1

----------------------------------------
MAIN MENU
----------------------------------------
1. Add a new snippet
2. Display all snippets
3. Search snippets
4. Delete a snippet by ID
5. Display total number of snippets
6. Exit
----------------------------------------
Enter your choice (1-6): 2

--- All Saved Snippets ---
----------------------------------------
ID:       1
Title:    Reverse a String
Language: Java
Code:
public static String reverse(String str) {
    return new StringBuilder(str).reverse().toString();
}
----------------------------------------
```
