package com.in28minutes.todo;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.in28minutes.exception.ExceptionController;

@Controller
@SessionAttributes("name")
public class TodoController {

    private final Log LOGGER = LogFactory.getLog(ExceptionController.class);

    @Autowired
    TodoService service;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @GetMapping("/list-todos")
    public String listTodos(ModelMap model) {
        model.addAttribute("todos", service.retrieveTodos(retrieveLoggedInUserName()));
        return "list-todos";
    }


    @GetMapping("/add-todo")
    public String showTodoPage(ModelMap model) {
        model.addAttribute("todo", new Todo(0, retrieveLoggedInUserName(), "", LocalDate.now(), false));
        return "todo";
    }

    @PostMapping("/add-todo")
    public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "todo";
        }
        service.addTodo(retrieveLoggedInUserName(), todo.getDesc(), LocalDate.now(), false);
        model.clear();
        return "redirect:list-todos";
    }

    @GetMapping("/update-todo")
    public String updateTodo(ModelMap model, @RequestParam int id) {
        Todo todo = service.retrieveTodo(id);
        model.addAttribute("todo", todo);
        return "todo";
    }

    @PostMapping("/update-todo")
    public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        // Update to do
        if (result.hasErrors()) {
            return "todo";
        }

        todo.setUser(retrieveLoggedInUserName());
        service.updateTodo(todo);

        return "redirect:list-todos";
    }

    @GetMapping("/delete-todo")
    public String deleteTodo(ModelMap model, @RequestParam int id) {
        service.deleteTodo(id);
        model.clear();
        return "redirect:list-todos";
    }

    @ExceptionHandler(value = Exception.class)
    public String handleException(HttpServletRequest request, Exception ex) {
        LOGGER.error("Request " + request.getRequestURL() + " Threw an Exception", ex);
        return "error-specific";
    }

    private String retrieveLoggedInUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails)
            return ((UserDetails) principal).getUsername();

        return principal.toString();
    }

}