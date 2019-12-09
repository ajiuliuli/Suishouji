package com.example.suishouji;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.suishouji.task.deletedTaskList;
import com.example.suishouji.task.newTaskList;

import org.litepal.LitePal;

import java.util.List;

public class recycleBin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_bin);

        LinearLayout ll = (LinearLayout)findViewById(R.id.ll);
        List<deletedTaskList> newTaskLists = LitePal.select("taskTittle")
                .find(deletedTaskList.class);
        ll.removeAllViews();
        final Button[] tv = new Button[newTaskLists.size()];
        int x = 0;
        for (deletedTaskList task : newTaskLists) {
            tv[x] = new Button(recycleBin.this);
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
                    Intent intent = new Intent(recycleBin.this,checkDeleteView.class);
                    intent.putExtra("taskTittle",str);
                    startActivity(intent);
                }
            });
            View vi = new View(recycleBin.this);
            vi.setMinimumHeight(5);
            vi.setBackgroundColor(Color.parseColor("#22000000"));
            ll.addView(tv[x]);
            ll.addView(vi);
            x++;
        }

    }
}
