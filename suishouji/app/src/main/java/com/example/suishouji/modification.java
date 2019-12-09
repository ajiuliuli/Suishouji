package com.example.suishouji;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.suishouji.task.newTaskList;

import org.litepal.LitePal;

import java.util.List;

public class modification extends AppCompatActivity {

    EditText taskTittle;
    EditText taskDetail;
    EditText startTime;
    EditText remark;
    EditText person;
    Button saveall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modification);

        taskTittle = (EditText)findViewById(R.id.taskTittle);
        taskDetail = (EditText)findViewById(R.id.taskDetail);
        startTime = (EditText)findViewById(R.id.startTime);
        remark = (EditText)findViewById(R.id.remark);
        person = (EditText)findViewById(R.id.person);

        Intent intent = getIntent();
        final String tittle = intent.getStringExtra("taskTittle");

        final List<newTaskList> newTaskLists = LitePal.select("taskTittle","taskDetail","startTime","remark","person")
                .where("taskTittle=?",tittle)
                .find(newTaskList.class);

        for (newTaskList n :newTaskLists) {
            taskTittle.setText(n.getTaskTittle());
            taskDetail.setText(n.getTaskDetail());
            startTime.setText(n.getStartTime());
            remark.setText(n.getRemark());
            person.setText(n.getPerson());
        }

        saveall = (Button)findViewById(R.id.saveall);
        saveall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newTaskList newTaskList = new newTaskList();
                newTaskList.setTaskTittle(taskTittle.getText().toString());
                newTaskList.setTaskDetail(taskDetail.getText().toString());
                newTaskList.setStartTime(startTime.getText().toString());
                newTaskList.setRemark(remark.getText().toString());
                newTaskList.setPerson(person.getText().toString());
                newTaskList.updateAll("taskTittle=?",tittle);
                Intent intent1 = new Intent(modification.this,MainActivity.class);
                startActivity(intent1);
                Toast.makeText(modification.this,"修改已完成，返回主界面！",Toast.LENGTH_SHORT).show();
            }
        });




    }
}
