package com.example.suishouji;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.suishouji.task.newTaskList;

import org.litepal.LitePal;

import java.util.List;

public class MainActivity extends AppCompatActivity {

//    Button create;
    ImageButton insertView;
    ImageButton checkDeleteView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        create = (Button)findViewById(R.id.create);
//        create.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                LitePal.getDatabase();
//                Toast.makeText(MainActivity.this,"创建成功",Toast.LENGTH_SHORT).show();
//            }
//        });

        insertView = (ImageButton)findViewById(R.id.insertView);
        insertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,insertView.class);
                startActivity(intent);
            }
        });

        checkDeleteView = (ImageButton)findViewById(R.id.checkDeleteView);
        checkDeleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,recycleBin.class);
                startActivity(intent);
            }
        });

        LinearLayout ll = (LinearLayout)findViewById(R.id.ll);
        List<newTaskList> newTaskLists = LitePal.select("taskTittle")
                .find(newTaskList.class);
        ll.removeAllViews();
        final Button[] tv = new Button[newTaskLists.size()];
        int x = 0;
        for (newTaskList task : newTaskLists) {
            tv[x] = new Button(MainActivity.this);
            tv[x].setId(x);
            tv[x].setHeight(100);
            tv[x].setBackgroundColor(Color.parseColor("#ffffff"));
            tv[x].setText(task.getTaskTittle());
            tv[x].setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
            tv[x].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = view.getId();
                    String str = tv[i].getText().toString();
                    Intent intent = new Intent(MainActivity.this,checkView.class);
                    intent.putExtra("taskTittle",str);
                    startActivity(intent);
                }
            });
            View vi = new View(MainActivity.this);
            vi.setMinimumHeight(5);
            vi.setBackgroundColor(Color.parseColor("#22000000"));
            ll.addView(tv[x]);
            ll.addView(vi);
            x++;
        }

    }
}
