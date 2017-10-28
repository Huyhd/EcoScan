package org.tensorflow.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Kimsoomin on 2017-10-29.
 */

public class Edititems extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newitemslist);

   final ListView listview;
        final ListViewAdapterNew adapter;

        // Adapter 생성
        adapter = new ListViewAdapterNew();

        // 리스트뷰 참조 및 Adapter달기
        final ListView newlv = (ListView) findViewById(R.id.NewFood);
        newlv.setAdapter(adapter);

        FileInputStream fis = null;
        try {
            fis = openFileInput("Saveobjects");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader bufferedReader = new BufferedReader(isr);
        //StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
          //      sb.append(line);
                adapter.addItem(line.split(",")[0], "Expiration date : "+line.split(",")[1]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

       /* newlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                switch (v.getId()) {
                    case R.id.btn_alert:
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                context);

                        // 제목셋팅
                        alertDialogBuilder.setTitle("Delete Item");

                        // AlertDialog 셋팅
                        alertDialogBuilder
                                .setMessage("Delte This Item?")
                                .setCancelable(false)
                                .setPositiveButton("Yes",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                //delete
                                            }
                                        })
                                .setNegativeButton("No",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(
                                                    DialogInterface dialog, int id) {
                                                // 다이얼로그를 취소한다
                                                dialog.cancel();
                                            }
                                        });

                        // 다이얼로그 생성
                        AlertDialog alertDialog = alertDialogBuilder.create();

                        // 다이얼로그 보여주기
                        alertDialog.show();
                        break;

                    default:
                        break;
                }
            }
        });*/
    }

    public void BacktoMain(View view){
        Intent additems = new Intent(Edititems.this, Main3Activity.class);
        startActivity(additems);
        finish();
    }
}
