package com.itakademy.mytodolist;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.itakademy.mytodolist.pojos.Todo;

import java.util.ArrayList;
import java.util.List;

public class AddTodoActivity extends AppCompatActivity {

    private Spinner spinner;
    private String urgency;
    private Button addButton;
    private Button cancelButton;
    private EditText edtTodo;
    private Context context;
    private Todo todo;
    public static final String KEY_EDTTODO = "edtTodo";
    public static final String KEY_SPINNER = "spinner";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        // récupère la toolbar et l'affecte comme ActionBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // récupere la action bar et set a true
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

        // récupérer les views
        spinner = findViewById(R.id.spinner);
        addButton = findViewById(R.id.addButton);
        cancelButton = findViewById(R.id.cancelButton);
        edtTodo = findViewById(R.id.edtTodo);
        // recupere context application
        context = getApplicationContext();

        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
        this,
                R.array.array_options,
                // use spinner native or can personalize in a new layout
                android.R.layout.simple_spinner_item
                );
        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        spinner.setAdapter(adapter);

        // get the selected spinner
        urgency = spinner.getSelectedItem().toString();
        // quand il y a une destruction (rotation screen)
        if (savedInstanceState != null){
            // récupere les données misent dans le bundle
            //edtTodo = savedInstanceState.getString(KEY_EDTTODO);
            //spinner = savedInstanceState.getInt(KEY_SPINNER);
        }
        // au clic de ADD alors créer un objet todolist et transmet le texte a main
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // condition sur edittext
                if (edtTodo.getText().length() <= 3){
                    // affiche un toast
                    CharSequence text = "Attention caractères minimum insuffisant !";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                }else{
                    // créer l'objet
                    todo = new Todo(edtTodo.getText().toString(), urgency);
                    // affiche un toast
                    CharSequence text = "Votre toDo est bien ajoutée !";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    finish();
                }
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });





    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // met dans le bundle
        //outState.putInt(KEY_EDTTODO, edtTodo);
        //outState.putInt(KEY_SPINNER, spinner);

    }

    @Override
    public boolean onSupportNavigateUp() {
        // ca termine l'activité
        finish();
        return true;
    }


}