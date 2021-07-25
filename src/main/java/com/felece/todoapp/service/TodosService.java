package com.felece.todoapp.service;

import com.felece.todoapp.dto.TodoDto;
import com.felece.todoapp.entity.Todo;
import com.felece.todoapp.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodosService {

    @Autowired
    TodoRepository todoRepository;

    public Optional<Todo> findByUserId(Long id){
        return todoRepository.findById(id);
    }

    public Todo addTodo(TodoDto dto){
        Todo todo = new Todo();
        todo.setDesc(dto.getDesc());
        todo.setUserId(dto.getUserId());
        todo.setStatus(dto.getStatus());
        return todo;
    }

    public Todo update(Long id, String status) {
        Todo todo = todoRepository.findById(id).get();
        todo.setStatus(status);
        return todoRepository.save(todo);
    }

    public void deleteTodo(Long id){
        todoRepository.deleteById(id);
    }

    public List<Todo> findAll(){
        return todoRepository.findAll();
    }

}
