package com.example.suishouji;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.suishouji.task.newTaskList;

public class insertView extends AppCompatActivity {

    EditText taskTittle;
    EditText taskDetail;
    EditText startTime;
    EditText remark;
    EditText person;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_view);

        taskTittle = (EditText)findViewById(R.id.taskTittle);
        taskDetail = (EditText)findViewById(R.id.taskDetail);
        startTime = (EditText)findViewById(R.id.startTime);
        remark = (EditText)findViewById(R.id.remark);
        person = (EditText)findViewById(R.id.person);
        submit = (Button)findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    newTaskList task = new newTaskList();
                    task.setTaskTittle(taskTittle.getText().toString());
                    task.setTaskDetail(taskDetail.getText().toString());
                    task.setStartTime(startTime.getText().toString());
                    task.setRemark(remark.getText().toString());
                    task.setPerson(person.getText().toString());
                    task.save();
                    Intent intent = new Intent(insertView.this,MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(insertView.this,"随手记添加成功！",Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(insertView.this,"添加失败，请重新尝试！",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
