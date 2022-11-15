package com.example.baiquatrinh2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class NoteAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<NoteDTO> items = new ArrayList<>();



    public NoteAdapter(Activity activity,ArrayList<NoteDTO> items){
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = activity.getLayoutInflater();

        view = inflater.inflate(R.layout.note_item, null);

        TextView title = (TextView) view.findViewById(R.id.textTitle);
        title.setText(items.get(i).getTitle());

        TextView time = (TextView) view.findViewById(R.id.textTime);
        time.setText(items.get(i).getTime());

        TextView desc = (TextView) view.findViewById(R.id.textDesc);
        desc.setText(items.get(i).getDesc());

        TextView id = (TextView) view.findViewById(R.id.textId);
        id.setText(items.get(i).getId() + "");

        Button editBtn = view.findViewById(R.id.editBtn);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("id", id.getText().toString());
                bundle.putString("title", title.getText().toString());
                bundle.putString("desc", desc.getText().toString());
                bundle.putString("time", time.getText().toString());

                Intent i = new Intent(activity, AddNote.class);
                i.putExtras(bundle);
                activity.startActivity(i);
            }
        });

        return view;
    }
}
