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

import java.util.ArrayList;
import java.util.List;

public class ViewGradesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_grades);

        final Context
                context = this;

        Button
                btnEnterGrades = (Button)findViewById(R.id.btnBackToEnterGrades),
                btnEnterStudents = (Button)findViewById(R.id.btnBackToEnterStudents);

        StudentGradesDBHelper
                db = new StudentGradesDBHelper(context);
        final List<Student> studentList = db.getAllStudents();

        TableLayout
                tblStudentRecords = (TableLayout)findViewById(R.id.tblStudentGrades);

        TableRow
                studentColumns = new TableRow(context);
        studentColumns.setBackgroundColor(Color.parseColor("#eeeeee"));
        studentColumns.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        String[]
                columnNames = {"Student", "Grade"};
        String
                studentIDCheck = "",
                letterGrade = "";
        ArrayList<String>
                studentExists = new ArrayList<>(),
                letterGradeList = new ArrayList<>(),
                studentNamesList = new ArrayList<>();
        int
            gradeValue = 0,
            studentIndex = -1,
            numberOfClasses = 0,
            classesCountIdentifier = 0;
        ArrayList<Integer>
                gradeTotal = new ArrayList<>(),
                countOfClassesList = new ArrayList<>();

        for (Student filterStudent : studentList) {
            if (!studentIDCheck.equals(filterStudent.getStudentID())) {
                numberOfClasses = 0;
                studentNamesList.add(filterStudent.getFirstName() + " "
                        + filterStudent.getLastName());
                studentIDCheck = filterStudent.getStudentID();
                gradeValue = filterStudent.getStudentGrade();
                studentExists.add(filterStudent.getStudentID());
                studentIndex += 1;
                numberOfClasses += 1;
            }
            else {
                if (gradeTotal.size() != studentIndex) {
                    gradeTotal.remove(studentIndex);
                }
                if (numberOfClasses == 2) {
                    countOfClassesList.remove(studentIndex);
                }
                gradeValue += filterStudent.getStudentGrade();
                numberOfClasses += 1;
                gradeTotal.add(gradeValue);
                countOfClassesList.add(numberOfClasses);
            }
        }
        for (int grade : gradeTotal) {
            if (countOfClassesList.get(classesCountIdentifier) == 2) {
                grade = grade / 2;
                classesCountIdentifier += 1;
            }
            else if (countOfClassesList.get(classesCountIdentifier) == 3) {
                grade = grade / 3;
                classesCountIdentifier += 1;
            }
            if (grade >= 90) {
                letterGrade = "A";
                letterGradeList.add(letterGrade);
            }
            else if (grade >= 80 && grade <= 89) {
                letterGrade = "B";
                letterGradeList.add(letterGrade);
            }
            else if (grade >= 70 && grade <= 79) {
                letterGrade = "C";
                letterGradeList.add(letterGrade);
            }
            else if (grade >= 60 && grade <= 69) {
                letterGrade = "D";
                letterGradeList.add(letterGrade);
            }
            else if (grade < 60) {
                letterGrade = "F";
                letterGradeList.add(letterGrade);
            }
        }

        for (String column : columnNames) {
            TextView
                    txtColumn = new TextView(this);
            txtColumn.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            txtColumn.setGravity(Gravity.CENTER);
            txtColumn.setTextSize(18);
            txtColumn.setPadding(235, 19, 155, 19);
            txtColumn.setText(column);
            studentColumns.addView(txtColumn);
        }
        tblStudentRecords.addView(studentColumns);

        for (int i = 0; i < studentNamesList.size(); i += 1) {
            TableRow
                    studentRow = new TableRow(context);
            studentRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

            String[]
                    currentStudent = {studentNamesList.get(i), letterGradeList.get(i)};
            for (String studentValue : currentStudent) {
                    TextView
                            studentEachColumn = new TextView(this);
                    studentEachColumn.setLayoutParams(new TableRow.LayoutParams(
                            TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT));
                    studentEachColumn.setGravity(Gravity.CENTER);
                    studentEachColumn.setTextSize(16);
                    studentEachColumn.setPadding(235, 19, 155, 19);
                    studentEachColumn.setText(studentValue);
                    studentRow.addView(studentEachColumn);
            }
            tblStudentRecords.addView(studentRow);
        }

        btnEnterGrades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gradesIntent =
                        new Intent(context, EnterStudentGradesActivity.class);
                startActivity(gradesIntent);
            }
        });

        btnEnterStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gradesIntent =
                        new Intent(context, StudentEntryActivity.class);
                startActivity(gradesIntent);
            }
        });
    }
}
