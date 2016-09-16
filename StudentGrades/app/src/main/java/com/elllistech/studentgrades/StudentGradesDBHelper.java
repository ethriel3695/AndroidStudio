package com.elllistech.studentgrades;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Reuben Ellis on 9/13/2016
 * The StudentGradesDBHelper class declares components and methods
 * for creating and working with a SQLite database
 */
public class StudentGradesDBHelper extends SQLiteOpenHelper {

    private static final String
            StudentGradesDB = "student_grades.db";

    public static final String
            Student_Table = "student",
            StudentID = "StudentID",
            StudentFirstName = "FirstName",
            StudentLastName = "LastName",
            ClassID = "ClassID",
            ClassName = "className",
            Grade = "Grade",
            LetterGrade = "LetterGrade";

    private static final int
            DatabaseVersion = 1;

    SQLiteDatabase
            db = this.getWritableDatabase();

    ContentValues
            studentValues = new ContentValues();

    //The Constructor for the class which accepts the current context of the database object
    public StudentGradesDBHelper(Context context) {
        super(context, StudentGradesDB, null, DatabaseVersion);
    }

    //The onCreate method contains a SQL string complete with column
    //names and data types and then the sql is executed to create the table
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String
                SQLTableCreation = "CREATE TABLE " + Student_Table + " (" + StudentID +
                " VARCHAR(8), " + StudentFirstName + " VARCHAR(20), " +
                StudentLastName + " VARCHAR(20), " + ClassID + " VARCHAR(20), " + ClassName +
                " VARCHAR(20)," + Grade + " INTEGER(3)," + LetterGrade + " VARCHAR(2)" + ")";
        sqLiteDatabase.execSQL(SQLTableCreation);
    }

    //The onUpgrade method updates the database whenever changes are made to the database
    //and a new version of the database is created
    @Override
    public void onUpgrade(SQLiteDatabase db, int currentVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Student_Table);
        onCreate(db);
    }

    //The addStudent method accepts a Student object and puts
    //values within the declared ContentValues and inserts the values
    //into the database via the insert method
    public void addStudent(Student student) {
        studentValues.put(StudentID, student.getStudentID());
        studentValues.put(StudentFirstName, student.getFirstName());
        studentValues.put(StudentLastName, student.getLastName());
        studentValues.put(ClassID, student.getClassID());
        studentValues.put(ClassName, student.getClassName());
        studentValues.put(Grade, student.getStudentGrade());
        studentValues.put(LetterGrade, student.getLetterGrade());
        db.insert(Student_Table, null, studentValues);
    }

    //The getAllStudents method is an ArrayList of type Student
    //and returns a Student list which contains all the rows
    //and columns from the database.  The selectCustomersQuery
    //gets all the records and then the method iterates through the
    //records by using a while loop which checks for records until
    //no more records are found
    public ArrayList<Student> getAllStudents() {
        ArrayList<Student>
                studentList = new ArrayList<>();
        String
                selectCustomersQuery = "SELECT * FROM " + Student_Table;
        Cursor
                cursor = null;
        try {
            cursor = db.rawQuery(selectCustomersQuery, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    Student
                            student = new Student();
                    student.setStudentID(cursor.getString
                            (cursor.getColumnIndex(StudentID)));
                    student.setFirstName(cursor.getString(
                            cursor.getColumnIndex(StudentFirstName)));
                    student.setLastName(cursor.getString(
                            cursor.getColumnIndex(StudentLastName)));
                    student.setClassID(cursor.getString(
                            cursor.getColumnIndex(ClassID)));
                    student.setClassName(cursor.getString(
                            cursor.getColumnIndex(ClassName)));
                    student.setLetterGrade(cursor.getString(
                            cursor.getColumnIndex(LetterGrade)));
                    student.setStudentGrade(cursor.getInt(
                            cursor.getColumnIndex(Grade)));
                    studentList.add(student);
                }
            }
        }catch (Exception e) {
            Log.d("Error", "Exception with a value of " + e);
        }finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return studentList;
    }
    //The updateStudentGrade method puts the student grade into
    //the ContentValues variable and then updates the database based
    //on the Student ID and Class ID to update a unique record
    public int updateStudentGrade(Student student) {
        studentValues.put(Grade, student.getStudentGrade());
        studentValues.put(LetterGrade, student.getLetterGrade());

        return db.update(Student_Table, studentValues, StudentID + "=? AND " + ClassID + "=?",
                new String[]{student.getStudentID(), student.getClassID()});
    }
}
