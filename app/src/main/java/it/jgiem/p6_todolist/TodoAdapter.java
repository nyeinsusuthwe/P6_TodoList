package it.jgiem.p6_todolist;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.jgiem.p6_todolist.databinding.AdapterTodoBinding;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    private List<Todo> todoList;

    public TodoAdapter(List<Todo> todoList){
        this.todoList = todoList;
    }

    public void setTodoList(List<Todo> todoList){
        this.todoList = todoList;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TodoViewHolder(AdapterTodoBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        Todo todo = todoList.get(position);
        holder.getBinding().cbTodo.setText(todo.getTitle());
        holder.getBinding().cbTodo.setChecked(todo.isCompleted());
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    class TodoViewHolder extends RecyclerView.ViewHolder{
        private AdapterTodoBinding binding;
        public TodoViewHolder(AdapterTodoBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        public AdapterTodoBinding getBinding(){
            return  binding;
        }
    }
}
