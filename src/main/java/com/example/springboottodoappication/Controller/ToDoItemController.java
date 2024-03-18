package com.example.springboottodoappication.Controller;

import com.example.springboottodoappication.Service.ToDoItemService;
import com.example.springboottodoappication.model.ToDoItem;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ToDoItemController {

    @Autowired
    private ToDoItemService toDoItemService;

    @GetMapping("/create-todo")
    public String showCreateForm(ToDoItem toDoItem){
        return "new-todo-item";
    }

    @PostMapping("/todo")
    public String createToDoItem(@Valid ToDoItem toDoItem, BindingResult result, Model model){
        ToDoItem item = new ToDoItem();
        item.setDescription(toDoItem.getDescription());
        item.setIsComplete(toDoItem.getIsComplete());
        toDoItemService.save(toDoItem);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteToDoItem(@PathVariable("id") Long id, Model model){
        ToDoItem toDoItem = toDoItemService.getById(id).orElseThrow(()->
                new IllegalArgumentException("ToDoItem id : " + id + " not found"));
        toDoItemService.delete(toDoItem);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showToDoItemForm(@PathVariable("id") Long id, Model model){
        ToDoItem toDoItem = toDoItemService.getById(id).orElseThrow(()->
                new IllegalArgumentException("ToDoItem id : " + id + " not found"));
        model.addAttribute("todo", toDoItem);
        return "edit-todo-item";
    }

    @PostMapping("/todo/{id}")
    public String updateToDoItem(@PathVariable("id") Long id, @Valid ToDoItem toDoItem, BindingResult result, Model model){
        ToDoItem item = toDoItemService.getById(id).orElseThrow(()->
                new IllegalArgumentException("ToDoItem id : " + id + " not found"));
        item.setIsComplete(toDoItem.getIsComplete());
        item.setDescription(toDoItem.getDescription());
        toDoItemService.save(item);
        return "redirect:/";

    }

}
