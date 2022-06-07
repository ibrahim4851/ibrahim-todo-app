package com.felece.todoapp.service;

import com.felece.todoapp.dto.FilterTodoByUserDto;
import com.felece.todoapp.dto.FilterTodoDto;
import com.felece.todoapp.dto.TodoDto;
import com.felece.todoapp.entity.MyUserDetails;
import com.felece.todoapp.entity.Todo;
import com.felece.todoapp.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TodosService {

    @Autowired
    TodoRepository todoRepository;

    public List<Todo> findByUserId(Long id){
        return todoRepository.findTodosByUserId(id);
    }

    public Todo addTodo(TodoDto dto){

        //getting the user's ID
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails customUser = (MyUserDetails) authentication.getPrincipal();
        Long userId = customUser.getId();

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

    public List<Todo> filterTodos(FilterTodoDto dto){
        Date date1 = dto.getDateStart();
        Date date2 = dto.getDateEnd();
        return todoRepository.findAllByDateBetween(date1, date2);}

    public List<Todo> filterTodosByUserId(FilterTodoByUserDto dto){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails customUser = (MyUserDetails) authentication.getPrincipal();
        Long userId = customUser.getId();

        Date date1 = dto.getDateStart();
        Date date2 = dto.getDateEnd();
        Long id = userId;
        return todoRepository.findTodosByDateBetweenAndUserId(date1, date2, id);
    }

}
