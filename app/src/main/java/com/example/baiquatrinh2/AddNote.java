package com.example.baiquatrinh2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class AddNote extends AppCompatActivity {

    String title = "", desc = "", time = "";
    int id = 0;
    TextView textTitle, textDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        textTitle = findViewById(R.id.editTitle);
        textDesc = findViewById(R.id.editDesc);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            title = bundle.getString("title");
            desc = bundle.getString("desc");
            time = bundle.getString("time");
            title = bundle.getString("title");
            id = Integer.parseInt(bundle.getString("id"));

            textTitle.setText(title);
            textDesc.setText(desc);
        }

        Button deleteBtn = findViewById(R.id.delete);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id == 0){
                    Toast.makeText(AddNote.this,"Vui lòng chọn note để xóa",Toast.LENGTH_LONG).show();
                }else{

                    AlertDialog.Builder alert = new AlertDialog.Builder(AddNote.this);
                    alert.setTitle("Delete");
                    alert.setMessage("Are you sure you want to delete?");
                    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Bundle bundleDelete = new Bundle();
                            bundleDelete.putString("action","delete");
                            bundleDelete.putString("id",id + "");
                            Intent i = new Intent(AddNote.this, MainActivity.class);
                            i.putExtras(bundleDelete);
                            startActivity(i);

                            dialog.dismiss();
                        }
                    });

                    alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    alert.show();

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater im = getMenuInflater();
        im.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save:
                Date currentTime = Calendar.getInstance().getTime();
                time = currentTime.toString();


                title = textTitle.getText().toString();
                desc = textDesc.getText().toString();

                Bundle bundle = new Bundle();

                if(id == 0){
                    bundle.putString("title",title);
                    bundle.putString("time",time);
                    bundle.putString("desc",desc);
                    bundle.putString("action","add");
                }else{
                    bundle.putString("id",id + "");
                    bundle.putString("title",title);
                    bundle.putString("time",time);
                    bundle.putString("desc",desc);
                    bundle.putString("action","update");
                }

                Intent i = new Intent(this, MainActivity.class);
                i.putExtras(bundle);
                startActivity(i);
                break;
        }
        return true;
    }
}