package com.example.srmus;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class cgpa extends AppCompatActivity {

    private EditText etNumSemesters;
    private LinearLayout llSemesters;
    private TextView tvResult;
    private Button btnAdd, btnCalculate, btnDetails;

    private ArrayList<EditText> semesterEditTexts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cgpa);

        etNumSemesters = findViewById(R.id.et_num_semesters);
        llSemesters = findViewById(R.id.ll_semesters);
        tvResult = findViewById(R.id.tv_result);
        btnAdd = findViewById(R.id.btn_add);
        btnCalculate = findViewById(R.id.btn_calculate);
        btnDetails = findViewById(R.id.btn_details);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSemesters();
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateCGPA();
            }
        });

        btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDetailsDialog();
            }
        });
    }

    private void addSemesters() {
        llSemesters.removeAllViews();
        semesterEditTexts.clear();

        int numSemesters = Integer.parseInt(etNumSemesters.getText().toString());

        for (int i = 0; i < numSemesters; i++) {
            EditText etSemester = new EditText(this);
            etSemester.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            etSemester.setHint("Enter SGPA for Semester " + (i + 1));
            etSemester.setInputType(android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL);

            llSemesters.addView(etSemester);
            semesterEditTexts.add(etSemester);
        }
    }

    private void calculateCGPA() {
        try {
            int numSemesters = semesterEditTexts.size();
            double totalSGPA = 0;
            StringBuilder semesters = new StringBuilder();

            for (int i = 0; i < numSemesters; i++) {
                double sgpa = Double.parseDouble(semesterEditTexts.get(i).getText().toString());
                totalSGPA += sgpa;
                semesters.append(i + 1).append(" ");
            }

            double cgpa = totalSGPA / numSemesters;
            tvResult.setText("CGPA: " + String.format("%.2f", cgpa));

            Toast.makeText(this, "Your CGPA of " + semesters.toString() + "semester/s is " + String.format("%.2f", cgpa), Toast.LENGTH_LONG).show();
        } catch (NumberFormatException e) {
            tvResult.setText("Please enter valid SGPA values.");
        }
    }

    private void showDetailsDialog() {
        final Dialog detailsDialog = new Dialog(this);
        detailsDialog.setContentView(R.layout.dialog_details);

        Button btnClose = detailsDialog.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailsDialog.dismiss();
            }
        });

        detailsDialog.show();
    }
}
