package com.elllistech.studentgrades;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StudentEntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_entry);

        Button
                btnAddStudent = (Button)findViewById(R.id.btnAddStudent),
                btnEnterGradesPage = (Button)findViewById(R.id.openGradesPage);

        final Context context = this;

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText
                        txtstudentID = (EditText)findViewById(R.id.txtStudentID),
                        txtfirstName = (EditText)findViewById(R.id.txtFirstName),
                        txtlastName = (EditText)findViewById(R.id.txtLastName),
                        txtClassID = (EditText)findViewById(R.id.txtClassID),
                        txtClassName = (EditText)findViewById(R.id.txtClassName);
                String
                        studentID = txtstudentID.getText().toString(),
                        firstName = txtfirstName.getText().toString(),
                        lastName = txtlastName.getText().toString(),
                        classID = txtClassID.getText().toString(),
                        className = txtClassName.getText().toString();

                if (!studentID.equals("") && !firstName.equals("") && !lastName.equals("") &&
                        !classID.equals("") && !className.equals("")) {
                    StudentGradesDBHelper
                            db = new StudentGradesDBHelper(context);
                    db.addStudent(new Student(studentID, firstName, lastName, classID,
                            className, null, 100));
                    Toast.makeText(context,
                            firstName + " " + lastName + " is now added to the student registry!",
                            Toast.LENGTH_SHORT).show();
                    txtstudentID.setText("");
                    txtfirstName.setText("");
                    txtlastName.setText("");
                    txtClassID.setText("");
                    txtClassName.setText("");

                } else {
                    Toast.makeText(StudentEntryActivity.this,
                            "Please fill out all fields to add a Student!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnEnterGradesPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gradesIntent =
                        new Intent(context, EnterStudentGradesActivity.class);
                startActivity(gradesIntent);
            }
        });
    }
}
