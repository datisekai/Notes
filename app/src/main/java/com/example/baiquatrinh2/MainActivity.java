package com.example.baiquatrinh2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    public static ArrayList<NoteDTO> listNotes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listNotes);



        NoteAdapter adapter = new NoteAdapter(this, listNotes);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){

            String title = bundle.getString("title");
            String desc = bundle.getString("desc");
            String time = bundle.getString("time");
            String action = bundle.getString("action");
            if(action.equalsIgnoreCase("add")){
                int id = 0;
                for(NoteDTO note:listNotes){
                    if(note.getId() > id){
                        id = note.getId();
                    }
                }
                NoteDTO note = new NoteDTO(id + 1, title,desc,time);
                listNotes.add(note);
            }else if(action.equalsIgnoreCase("update")){
                int id = Integer.parseInt(bundle.getString("id"));
                for(NoteDTO note:listNotes){
                    if(note.getId() == id){
                        note.setDesc(desc);
                        note.setTitle(title);
                        note.setTime(time);
                    }
                }
            }else{
                int id = Integer.parseInt(bundle.getString("id"));
                for(NoteDTO note:listNotes){
                    if(note.getId() == id){
                       listNotes.remove(note);
                       break;
                    }
                }
            }

            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater im = getMenuInflater();
        im.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.add:
                Intent i = new Intent(this, AddNote.class);
                startActivity(i);
            break;
        }
        return true;
    }
}