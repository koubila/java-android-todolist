package com.itakademy.mytodolist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.itakademy.mytodolist.adapter.TodoAdapter;
import com.itakademy.mytodolist.data.TodoDatabase;
import com.itakademy.mytodolist.pojos.Todo;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Context context;
    //private TextView tvText;
    private int count = 0;
    private RecyclerView rvTodos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_main);
        // récupère la toolbar et l'affecte comme ActionBar
        //Toolbar toolbar = findViewById(R.id.toolbar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = getApplicationContext();

        // recupere le RecyclerView
        rvTodos = findViewById(R.id.rvTodo);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        rvTodos.setHasFixedSize(true);
        rvTodos.setLayoutManager(layoutManager);

    }



    @Override
    protected void onStart() {
        super.onStart();

        //utilisation de AsyncTask
        TodoAsyncTask todoAsyncTask = new TodoAsyncTask();
        todoAsyncTask.execute();


        //recupere la liste des todos en bdd
        /*List<Todo> todos = TodoDatabase.getDb(context).todoDAO().list();
        tvText = findViewById(R.id.tvText);
        String content="";

        // boucler et afficher
        for (Todo todo : todos) {
            Log.d("Todo :", todo.getName());
            Log.d("Urgency :", todo.getUrgency());

            content += "-- "+ count +" :"+todo.getName()+ " // "+  todo.getUrgency() + "--\n";
            count++;
        }
        tvText.setText(content);*/
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

    public class TodoAsyncTask extends AsyncTask<Nullable, Nullable, List<Todo>> {

        // 4 étapes: OnPreExecute(), doInBackground(), onProgressUpdate() et onPostExecute()

        @Override
        protected  List<Todo> doInBackground(Nullable... nullables) {

            List<Todo> todos = TodoDatabase.getDb(context).todoDAO().list();
            return todos;
        }

        @Override
        protected void onPostExecute(List<Todo> todos) {

            /*StringBuilder sb = new StringBuilder();

            for (Todo todo : todos){
                sb.append(String.format("%s // %s\n", todo.getName(), todo.getUrgency()));
            }

            tvText.setText(sb.toString());*/

            // avec le receiverview
            // créé l'adapter en lui passant la liste
            TodoAdapter todoAdapter = new TodoAdapter(todos);
            rvTodos.setAdapter(todoAdapter);
        }

    }
}