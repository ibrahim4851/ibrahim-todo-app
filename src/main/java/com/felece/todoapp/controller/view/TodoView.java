package com.felece.todoapp.controller.view;

import com.felece.todoapp.dto.TodoDto;
import com.felece.todoapp.entity.Todo;
import com.felece.todoapp.service.TodosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class TodoView {

    @Autowired
    private TodosService todosService;

    private static final List<String> statuslist;
    static {
        statuslist = new ArrayList<>();
        statuslist.add("TODO");
        statuslist.add("DELAY");
        statuslist.add("DONE");
    }

    @GetMapping(path = "user/addtodo")
    private String getTodoForm(Model model) {
        model.addAttribute("statuslist", statuslist);
        return "login";
    }

    @PostMapping("user/addtodo")
    public String addTodoAdmin(@ModelAttribute("todoDto") TodoDto dto, Model model){
        Todo todo = todosService.addTodo(dto);
        model.addAttribute("todo", todo);
        return "login";
    }

}