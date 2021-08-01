package com.felece.todoapp.service;

import com.felece.todoapp.dto.TodoDto;
import com.felece.todoapp.entity.Todo;
import com.felece.todoapp.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Service
public class TodosService {

    @Autowired
    TodoRepository todoRepository;

    public List<Todo> findByUserId(String id){
        return todoRepository.findTodosByUserId(id);
    }

    public Todo addTodo(TodoDto dto){
        Todo todo = new Todo();
        todo.setDescription(dto.getDescription());
        todo.setUserId(dto.getUserId());
        todo.setTodoStatus(dto.getTodoStatus());
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        //LocalDate now = LocalDate.now();
        //ZoneId systemTimeZone = ZoneId.systemDefault();
        //ZonedDateTime zonedDateTime = now.atStartOfDay(systemTimeZone);
        //Date formatted = Date.from(zonedDateTime.toInstant());
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

}
