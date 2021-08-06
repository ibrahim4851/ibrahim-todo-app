package com.felece.todoapp.controller.rest;


import com.felece.todoapp.dto.FilterTodoDto;
import com.felece.todoapp.dto.TodoDto;
import com.felece.todoapp.entity.Todo;
import com.felece.todoapp.service.TodosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TodoController {

    @Autowired
    private TodosService todosService;


    @GetMapping("/user/todos/{userid}")
    public List<Todo> getUserTodo(@PathVariable("userid")Long id){
        List<Todo> todos = todosService.findByUserId(id);
        return todos;
    }

    @GetMapping("/user/todos/bydate/{userid}")
    public List<Todo> getUserTodoByDate(@PathVariable("userid")String id){
        List<Todo> todos = todosService.findByDateAndUserId(id);
        return todos;
    }

    @GetMapping("admin/todos/filter")
    public List<Todo> filterTodos(@RequestBody FilterTodoDto dto){
        List<Todo> todos = todosService.filterTodos(dto);
        return todos;
    }


    @PostMapping("/admin/todos/addtodo")
    public Todo addTodoAdmin(@RequestBody TodoDto dto){
        Todo todo = todosService.addTodo(dto);
        return todo;
    }

    @PostMapping("/user/todos/addtodo")
    public Todo addTodoUser(@RequestBody TodoDto dto){
        Todo todo = todosService.addTodo(dto);
        return todo;
    }

    @PutMapping("/admin/todos/{todoid}&{status}")
    @ResponseBody
    public Todo updateAdminTodo(@PathVariable("todoid") Long id, @PathVariable("status") String status){
        return todosService.update(id, status);
    }

    @PutMapping("/user/todos/{todoid}&{status}")
    @ResponseBody
    public Todo updateUserTodo(@PathVariable("todoid") Long id, @PathVariable("status") String status){
        return todosService.update(id, status);
    }

    @DeleteMapping("/user/deletetodo/{todoid}")
    public void deleteTodoUser(@PathVariable Long todoid){
        todosService.deleteTodo(todoid);
    }

    @DeleteMapping("/admin/deletetodo/{todoid}")
    public void deleteTodoAdmin(@PathVariable Long todoid){
        todosService.deleteTodo(todoid);
    }

}
