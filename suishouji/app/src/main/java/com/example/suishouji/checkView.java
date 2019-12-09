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

public class checkView extends AppCompatActivity {

    EditText taskTittle;
    EditText taskDetail;
    EditText startTime;
    EditText remark;
    EditText person;
    Button delete;
    Button modification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_view);


        taskTittle = (EditText)findViewById(R.id.taskTittle);
        taskDetail = (EditText)findViewById(R.id.taskDetail);
        startTime = (EditText)findViewById(R.id.startTime);
        remark = (EditText)findViewById(R.id.remark);
        person = (EditText)findViewById(R.id.person);

        Intent intent = getIntent();
        final String tittle = intent.getStringExtra("taskTittle");

        final List<newTaskList>newTaskLists = LitePal.select("taskTittle","taskDetail","startTime","remark","person")
                .where("taskTittle=?",tittle)
                .find(newTaskList.class);

        for (newTaskList n :newTaskLists) {
            taskTittle.setText(n.getTaskTittle());
            taskDetail.setText(n.getTaskDetail());
            startTime.setText(n.getStartTime());
            remark.setText(n.getRemark());
            person.setText(n.getPerson());
        }

        delete = (Button)findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletedTaskList deletedTaskList = new deletedTaskList();
                for (newTaskList nn :newTaskLists) {
                    deletedTaskList.setTaskTittle(nn.getTaskTittle());
                    deletedTaskList.setTaskDetail(nn.getTaskDetail());
                    deletedTaskList.setStartTime(nn.getStartTime());
                    deletedTaskList.setRemark(nn.getRemark());
                    deletedTaskList.setPerson(nn.getPerson());
                }
                deletedTaskList.setDeleteTime("2019");
                deletedTaskList.save();
                LitePal.deleteAll(newTaskList.class,"taskTittle=?",tittle);
                Toast.makeText(checkView.this,"删除成功，可在回收站查看!",Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(checkView.this,recycleBin.class);
                startActivity(intent1);
            }
        });

        modification = (Button)findViewById(R.id.modification);
        modification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(checkView.this,modification.class);
                intent.putExtra("taskTittle",tittle);
                startActivity(intent);
            }
        });

    }
}
