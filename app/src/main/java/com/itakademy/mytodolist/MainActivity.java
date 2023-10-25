package com.itakademy.mytodolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // récupère la toolbar et l'affecte comme ActionBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



    }

    @Override
    protected void onStart() {
        super.onStart();
        //recupere la liste des todos en bdd
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu_main);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        // Intention to start another activity
        if (item.getItemId() == R.id.todo){
            // instancier une intention avec parametre
            Intent intent=new Intent(this,AddTodoActivity.class);

            // class Question a été serialiser car putExtra prend en argument string
            // on ajoute à intent les donnée souhaiter
            //intent.putExtra(KEY_QUESTION, questions.get(index));
            // démarre activité
            this.startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}