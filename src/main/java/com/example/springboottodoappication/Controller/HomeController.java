package com.example.springboottodoappication.Controller;

import com.example.springboottodoappication.Service.ToDoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    private ToDoItemService toDoItemService;

    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("toDoItems",toDoItemService.getAll());
        return modelAndView;
    }
}
