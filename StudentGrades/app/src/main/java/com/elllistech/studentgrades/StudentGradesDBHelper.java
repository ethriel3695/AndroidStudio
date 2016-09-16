package com.elllistech.studentgrades;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by ethri on 9/13/2016.
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

    public StudentGradesDBHelper(Context context) {
        super(context, StudentGradesDB, null, DatabaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String
                SQLTableCreation = "CREATE TABLE " + Student_Table + " (" + StudentID +
                " VARCHAR(8), " + StudentFirstName + " VARCHAR(20), " +
                StudentLastName + " VARCHAR(20), " + ClassID + " VARCHAR(20), " + ClassName +
                " VARCHAR(20)," + Grade + " INTEGER(3)," + LetterGrade + " VARCHAR(2)" + ")";
        sqLiteDatabase.execSQL(SQLTableCreation);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int currentVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Student_Table);
        onCreate(db);
    }

    public void addStudent(Student student) {
        studentValues.put(StudentID, student.getStudentID());
        studentValues.put(StudentFirstName, student.getFirstName());
        studentValues.put(StudentLastName, student.getLastName());
        studentValues.put(ClassID, student.getClassID());
        studentValues.put(ClassName, student.getClassName());
        studentValues.put(Grade, student.getStudentGrade());
        studentValues.put(LetterGrade, student.getLetterGrade());
//        long
//                customerId = db.insert(Student_Table, null, studentValues);
        db.insert(Student_Table, null, studentValues);
        //student.setCustomerID(customerId);
    }

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
    public int updateStudentGrade(Student student) {
        studentValues.put(Grade, student.getStudentGrade());

        return db.update(Student_Table, studentValues, StudentID + "=? AND " + ClassID + "=?",
                new String[]{student.getStudentID(), student.getClassID()});
    }
}
