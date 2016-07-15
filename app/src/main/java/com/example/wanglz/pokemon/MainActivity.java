package com.example.wanglz.pokemon;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.BufferedWriter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
public class MainActivity extends ActionBarActivity {
    private EditText edit1, edit2;
    private Button button1,button2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit1 =(EditText)findViewById(R.id.editText);
        edit2 = (EditText)findViewById(R.id.editText2);
        button1 =(Button)findViewById(R.id.button);
        button2 = (Button)findViewById(R.id.button2);


        button1.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                String str1 = edit1.getText().toString();
                try {
                    File file = new File("/sdcard/pokemon/lat.txt");
                    //第二个参数意义是说是否以append方式添加内容
                    BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));

                    bw.write(str1);
                    bw.flush();
                    System.out.println("写入经度成功");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
            button2.setOnClickListener(new View.OnClickListener(){
                public  void onClick(View v){
                    String str1 = edit1.getText().toString();
                    try {
                        File file = new File("/sdcard/pokemon/lon.txt");

                        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));

                        bw.write(str1);
                        bw.flush();
                        System.out.println("写入纬度成功");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


            });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
