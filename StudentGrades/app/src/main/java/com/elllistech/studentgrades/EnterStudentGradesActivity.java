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
        final List<Student> studentList = db.getAllStudents();

        TableLayout
                tblStudentRecords = (TableLayout)findViewById(R.id.tblStudentRecords);

        TableRow
                studentColumns = new TableRow(context);
        studentColumns.setBackgroundColor(Color.parseColor("#eeeeee"));
        studentColumns.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        String[]
            columnNames = {"ID", "Student", "Class ID", "Class Name", "Grade"};

        int
                idCount = 100;
        final ArrayList<Integer>
            editTextIDList = new ArrayList<>();
        final ArrayList<String>
                checkEmptyGrades = new ArrayList<>();

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
                        Log.d("Text View ID", "" + studentGrade.getId());

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
                    indexCount = 0;
                ArrayList<String>
                        studentIds = new ArrayList<String>(),
                        classIds = new ArrayList<String>();
                StudentGradesDBHelper
                        dbUpdate = new StudentGradesDBHelper(context);
                for (Student currentStudent : studentList) {
                    studentID = currentStudent.getStudentID();
                    classID = currentStudent.getClassID();
                    studentIds.add(studentID);
                    classIds.add(classID);
                }
                //editTextIDList.get(1);
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
                        dbUpdate.updateStudentGrade(students);
                    }
                    else {
                        checkEmptyGrades.add(currentGrade);
                        Toast.makeText(context, "Please enter a grade between 0 - 100!",
                                Toast.LENGTH_SHORT).show();
                    }
                    indexCount += 1;
                }
                if (checkEmptyGrades.size() == 0) {
                    Intent gradesIntent =
                            new Intent(context, ViewGradesActivity.class);
                    startActivity(gradesIntent);
                }
                else {
                    Toast.makeText(context, "Please enter a grade for each class!",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
