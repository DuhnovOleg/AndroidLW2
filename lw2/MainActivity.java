package com.example.lw2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.LauncherActivity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button  buttonAdd    = findViewById(R.id.buttonAdd);
        Button  buttonDelete = findViewById(R.id.buttonDelete);
        ListView listView    = findViewById(R.id.textList);

        ArrayList<String> selectedUsers = new ArrayList<String>();
        ArrayList<String> stringArray = new ArrayList<String>();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, stringArray);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                String user = (String) adapter.getItem(position);
                if(listView.isItemChecked(position))
                {
                    selectedUsers.add(user);
                    v.setBackgroundColor(Color.RED);
                }
                else
                {
                    selectedUsers.remove(user);
                    v.setBackgroundColor(Color.WHITE);
                }
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener()
        {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v)
            {
                EditText editText = findViewById(R.id.editText);
                String userData   = editText.getText().toString();

                adapter.add(userData);
                adapter.notifyDataSetChanged();
                editText.setText("");
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener()
        {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v)
            {
                for(int i=0; i< selectedUsers.size();i++)
                {
                    adapter.remove(selectedUsers.get(i));
                }
                listView.setBackgroundColor(Color.WHITE);
                listView.clearChoices();
                selectedUsers.clear();

                adapter.notifyDataSetChanged();
            }
        });
    }
}