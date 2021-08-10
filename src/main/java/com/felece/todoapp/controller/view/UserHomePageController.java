package com.felece.todoapp.controller.view;

import com.felece.todoapp.dto.FilterTodoByUserDto;
import com.felece.todoapp.dto.TodoDto;
import com.felece.todoapp.dto.UpdateUserDto;
import com.felece.todoapp.entity.Todo;
import com.felece.todoapp.service.TodosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserHomePageController {

    @Autowired
    TodosService todosService;

    @GetMapping("user/home/{userid}")
    public String userHome(Model model, @PathVariable("userid")Long id){
        List<Todo> todos = todosService.findByUserId(id);
        model.addAttribute("todos", todos);
        return "user";
    }

    @PostMapping(value = "user/home/{userid}")
    @ResponseBody
    public List<Todo> filterTodos(@RequestBody FilterTodoByUserDto dto){
        List<Todo> todos = todosService.filterTodosByUserId(dto);
        return todos;
    }

    @PostMapping("user/home/addtodo")
    @ResponseBody
    public Todo addTodoUser(@RequestBody TodoDto dto){
        Todo todo = todosService.addTodo(dto);
        return todo;
    }

    @PutMapping(value = "user/home/updatetodo", produces = "application/json")
    @ResponseBody
    public Todo updateTodo(@RequestBody UpdateUserDto dto){
        Todo todo = todosService.update(dto.getId(), dto.getStatus());
        return todo;
    }

    @DeleteMapping("/user/deletetodo/{todoid}")
    @ResponseBody
    public void deleteTodoUser(@PathVariable Long todoid){
        todosService.deleteTodo(todoid);
    }

}