package com.elllistech.studentgrades;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Reuben Ellis on 9/13/2016
 * The EnterStudentGradesActivity class contains methods
 * for creation and interaction with the Student Grades Entry Page
 * Also, the TableLayout and rows are generated dynamically so only
 * rows for existing records are created and visible on the page
 */
public class EnterStudentGradesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_student_grades);

        final Context
                context = this;
        final Student
                students = new Student();
        Button
                btnStudentCreation = (Button)findViewById(R.id.btnStudentCreation),
                btnViewGrades = (Button)findViewById(R.id.btnViewGrades);

        String
                studentResults = "";

        StudentGradesDBHelper
                db = new StudentGradesDBHelper(context);
        final List<Student>
                studentList = db.getAllStudents();
        int
                idCount = 100;
        final ArrayList<Integer>
                editTextIDList = new ArrayList<>();

        TableLayout
                tblStudentRecords = (TableLayout)findViewById(R.id.tblStudentRecords);

        TableRow
                studentColumns = new TableRow(context);
        studentColumns.setBackgroundColor(Color.parseColor("#eeeeee"));
        studentColumns.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        String[]
            columnNames = {"ID", "Student", "Class ID", "Class Name", "Grade"};

        for (String column : columnNames) {
            TextView
                    txtColumn = new TextView(this);
            txtColumn.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            txtColumn.setGravity(Gravity.CENTER);
            txtColumn.setTextSize(18);
            txtColumn.setPadding(19, 19, 19, 19);
            txtColumn.setText(column);
            studentColumns.addView(txtColumn);
        }
        tblStudentRecords.addView(studentColumns);

        for (Student student : studentList) {
            TableRow
                    studentRow = new TableRow(context);
            studentRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

            String[]
                currentStudent = {student.getStudentID(), student.getFirstName() + " " +
                    student.getLastName(), student.getClassID(), student.getClassName(),
                    "Grade"};
            for (String studentValue : currentStudent) {
                    if (studentValue == "Grade") {
                    EditText
                        studentGrade = new EditText(this);
                        studentGrade.setLayoutParams(new TableRow.LayoutParams(
                                TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        studentGrade.setGravity(Gravity.CENTER);
                        studentGrade.setTextSize(16);
                        studentGrade.setPadding(19, 19, 19, 19);
                        studentGrade.setFilters(new InputFilter[]
                                {new InputFilter.LengthFilter(3)});
                        studentGrade.setInputType(InputType.TYPE_CLASS_NUMBER);
                        studentGrade.setText("");

                        studentGrade.setId(idCount);
                        editTextIDList.add(idCount);

                        studentRow.addView(studentGrade);

                    }
                    else {
                        TextView
                                studentEachColumn = new TextView(this);
                        studentEachColumn.setLayoutParams(new TableRow.LayoutParams(
                                TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        studentEachColumn.setGravity(Gravity.CENTER);
                        studentEachColumn.setTextSize(16);
                        studentEachColumn.setPadding(19, 19, 19, 19);
                        studentEachColumn.setText(studentValue);
                        studentRow.addView(studentEachColumn);
                    }
                }

            tblStudentRecords.addView(studentRow);
            idCount += 1;
        }

        btnStudentCreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gradesIntent =
                        new Intent(context, StudentEntryActivity.class);
                startActivity(gradesIntent);
            }
        });

        //The View Grades button onClick method checks to see if
        //the grade box is empty or contains a value over 100 and if
        //so, the user receives a message indicating a change is necessary
        //and the user will stay on the current page until changes are made
        btnViewGrades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String
                        currentGrade = "",
                        studentID = "",
                        classID = "",
                        currentStudentID = "",
                        currentClassID = "";
                int
                    indexCount = 0,
                    currentGradeValue = 0;
                ArrayList<String>
                        studentIds = new ArrayList<String>(),
                        classIds = new ArrayList<String>();
                ArrayList<String>
                        checkEmptyGrades = new ArrayList<>();
                StudentGradesDBHelper
                        dbUpdate = new StudentGradesDBHelper(context);
                for (Student currentStudent : studentList) {
                    studentID = currentStudent.getStudentID();
                    classID = currentStudent.getClassID();
                    studentIds.add(studentID);
                    classIds.add(classID);
                }
                for(int textID : editTextIDList) {
                    EditText
                            gradesText = (EditText)findViewById(textID);
                    currentGrade = gradesText.getText().toString();
                    if (!currentGrade.equals("") && Integer.parseInt(currentGrade) <= 100) {
                        students.setStudentGrade(Integer.parseInt(currentGrade));
                        currentStudentID = studentIds.get(indexCount);
                        currentClassID = classIds.get(indexCount);
                        students.setStudentID(currentStudentID);
                        students.setClassID(currentClassID);
                        currentGradeValue = Integer.parseInt(currentGrade);
                        if (currentGradeValue >= 90) {
                            students.setLetterGrade("A");
                        }
                        else if (currentGradeValue >= 80 && currentGradeValue <= 89) {
                            students.setLetterGrade("B");
                        }
                        else if (currentGradeValue >= 70 && currentGradeValue <= 79) {
                            students.setLetterGrade("C");
                        }
                        else if (currentGradeValue >= 60 && currentGradeValue <= 69) {
                            students.setLetterGrade("D");
                        }
                        else if (currentGradeValue < 60) {
                            students.setLetterGrade("F");
                        }
                        dbUpdate.updateStudentGrade(students);
                    }
                    else {
                        checkEmptyGrades.add(currentGrade);
                    }
                    indexCount += 1;
                }
                if (checkEmptyGrades.size() == 0) {
                    Intent gradesIntent =
                            new Intent(context, ViewGradesActivity.class);
                    startActivity(gradesIntent);
                }
                else {
                    Toast.makeText(context, "Please enter a grade between 0 - 100 for each class!",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
