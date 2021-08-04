package com.felece.todoapp.service;

import com.felece.todoapp.dto.TodoDto;
import com.felece.todoapp.entity.MyUserDetails;
import com.felece.todoapp.entity.Todo;
import com.felece.todoapp.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodosService {

    @Autowired
    TodoRepository todoRepository;

    public List<Todo> findByUserId(int id){
        return todoRepository.findTodosByUserId(id);
    }

    public Todo addTodo(TodoDto dto){

        //getting the user's ID
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails customUser = (MyUserDetails) authentication.getPrincipal();
        int userId = customUser.getId();

        Todo todo = new Todo();
        todo.setDescription(dto.getDescription());
        todo.setUserId(userId);
        todo.setTodoStatus(dto.getTodoStatus());
        todo.setDate(dto.getDate());
        todo = todoRepository.save(todo);
        return todo;
    }

    public Todo update(Long id, String status) {
        Todo todo = todoRepository.findById(id).get();
        todo.setTodoStatus(status);
        return todoRepository.save(todo);
    }

    public void deleteTodo(Long id){
        todoRepository.deleteById(id);
    }

    public List<Todo> findAll(){
        return todoRepository.findAll();
    }

    public List<Todo> findByDateAndUserId(String id){return todoRepository.findTodosByUserIdOrderByDateAsc(id);}

}
