package it.jgiem.p6_todolist;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.jgiem.p6_todolist.databinding.ActivityMainBinding;
import it.jgiem.p6_todolist.databinding.DialogTodoBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private TodoAdapter todoAdapter;
    private List<Todo> todoList;
    private AlertDialog addTodoDialog;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);
        initRecyclerView();
        initListeners();
        initTodoDialog();
    }

    private void initTodoDialog() {
        var dialogBinding = DialogTodoBinding.inflate(getLayoutInflater());

        addTodoDialog = new AlertDialog.Builder(this)
                .setTitle("Add Todo Dialog")
                .setView(dialogBinding.getRoot())
                .setPositiveButton("ADD", (dialog, which) ->{
                    String title = dialogBinding.etTitle.getText().toString();
                    if(!title.isBlank()){
                        todoList.add(new Todo(new Random().nextInt(), title, dialogBinding.cbCompleted.isChecked()));
                        todoAdapter.setTodoList(todoList);
                        dialog.cancel();
                    }
                })
                .setNegativeButton("CANCLE", (dialog, which) ->{
                    dialog.cancel();
                })
                .create();
    }

    private void initListeners() {
        binding.fabAdd.setOnClickListener(v->{
            Toast.makeText(this, "Hi", Toast.LENGTH_SHORT).show();
            showAddTodoDialog();
        });
    }

    private void showAddTodoDialog() {
        addTodoDialog.show();
    }

    private void initRecyclerView() {
        todoList = new ArrayList<>(List.of(new Todo(new Random().nextInt(), "Eat Breakfast", false)));
        todoAdapter= new TodoAdapter(todoList);
        //todoAdapter = new TodoAdapter(List.of(new Todo(new Random().nextInt(), "Eat Breakfast", false)));
        binding.rvTodo.setAdapter(todoAdapter);
        binding.rvTodo.setLayoutManager(new LinearLayoutManager(this));
    }
}