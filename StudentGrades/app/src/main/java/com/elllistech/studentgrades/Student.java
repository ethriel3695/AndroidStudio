package com.elllistech.studentgrades;

/**
 * Created by Reuben Ellis on 9/13/2016
 * The Student Class is a Student object with variables,
 * a default constructor, a constructor which takes all values
 * of the class, and get and set methods for each value
 */
public class Student {

    private String
            studentID,
            studentFirstName,
            studentLastName,
            classID,
            className,
            letterGrade;

    private int
            grade;

    public Student () {

    }

    public Student (String ID, String firstName, String lastName, String classIdentifier, String
            nameOfClass, String letterGrade, int studentGrade) {
        this.studentID = ID;
        this.studentFirstName = firstName;
        this.studentLastName = lastName;
        this.classID = classIdentifier;
        this.className = nameOfClass;
        this.letterGrade = letterGrade;
        this.grade = studentGrade;
    }
    public String getStudentID() { return studentID; }
    public void setStudentID(String id) { this.studentID = id; }

    public String getFirstName() { return studentFirstName; }
    public void setFirstName(String firstName) { this.studentFirstName = firstName; }

    public String getLastName() { return studentLastName; }
    public void setLastName(String lastName) { this.studentLastName = lastName; }

    public String getClassID() { return classID; }
    public void setClassID(String classIdentifier) { this.classID = classIdentifier; }

    public String getClassName() { return className; }
    public void setClassName(String nameOfClass) { this.className = nameOfClass; }

    public String getLetterGrade() { return letterGrade; }
    public void setLetterGrade(String LetterGrade) { this.letterGrade = LetterGrade; }

    public int getStudentGrade() { return grade; }
    public void setStudentGrade(int studentGrade) { this.grade = studentGrade; }
}
