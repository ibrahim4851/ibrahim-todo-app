package com.felece.todoapp.controller;


import com.felece.todoapp.dto.TodoDto;
import com.felece.todoapp.entity.Todo;
import com.felece.todoapp.service.TodosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TodoController {


    @Autowired
    private TodosService todosService;


    /*
    @PostMapping("/todos")
    @ResponseBody
    public List<Tod o> getTodo(Model model){
        List<Tod o> todos = todosService.findByUserId(id)
    }
     */

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

    @PutMapping("/admin/todos/{todo-id}")
    @ResponseBody
    public void updateAdminTodo(@PathVariable("todo-id") Long id, String status){
        todosService.update(id, status);
    }

    @PutMapping("/user/todos/{todo-id}")
    @ResponseBody
    public void updateUserTodo(@PathVariable("todo-id") Long id, String status){
        todosService.update(id, status);
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
