package com.example.suishouji;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.suishouji.task.deletedTaskList;
import com.example.suishouji.task.newTaskList;

import org.litepal.LitePal;

import java.util.List;

public class checkDeleteView extends AppCompatActivity {

    EditText taskTittle;
    EditText taskDetail;
    EditText startTime;
    EditText deleteTime;
    EditText remark;
    EditText person;
    Button restore;
    Button deleteall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_delete_view);

        taskTittle = (EditText)findViewById(R.id.taskTittle);
        taskDetail = (EditText)findViewById(R.id.taskDetail);
        startTime = (EditText)findViewById(R.id.startTime);
        deleteTime = (EditText)findViewById(R.id.deleteTime);
        remark = (EditText)findViewById(R.id.remark);
        person = (EditText)findViewById(R.id.person);

        Intent intent = getIntent();
        final String tittle = intent.getStringExtra("taskTittle");

        final List<deletedTaskList> deletedTaskLists = LitePal.select("taskTittle","taskDetail","startTime","remark","person")
                .where("taskTittle=?",tittle)
                .find(deletedTaskList.class);

        for (deletedTaskList n :deletedTaskLists) {
            taskTittle.setText(n.getTaskTittle());
            taskDetail.setText(n.getTaskDetail());
            startTime.setText(n.getStartTime());
            deleteTime.setText("2019-11-30");
            remark.setText(n.getRemark());
            person.setText(n.getPerson());
        }

        restore = (Button)findViewById(R.id.restore);
        restore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newTaskList newTaskList = new newTaskList();
                for (deletedTaskList nn :deletedTaskLists) {
                    newTaskList.setTaskTittle(nn.getTaskTittle());
                    newTaskList.setTaskDetail(nn.getTaskDetail());
                    newTaskList.setStartTime(nn.getStartTime());
                    newTaskList.setRemark(nn.getRemark());
                    newTaskList.setPerson(nn.getPerson());
                }
                newTaskList.save();
                LitePal.deleteAll(deletedTaskList.class,"taskTittle=?",tittle);
                Toast.makeText(checkDeleteView.this,"已还原！",Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(checkDeleteView.this,MainActivity.class);
                startActivity(intent1);
            }
        });

        deleteall = (Button)findViewById(R.id.deleteall);
        deleteall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LitePal.deleteAll(deletedTaskList.class,"taskTittle=?",tittle);
                Toast.makeText(checkDeleteView.this,"已彻底删除！",Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(checkDeleteView.this,recycleBin.class);
                startActivity(intent1);
            }
        });

    }
}
