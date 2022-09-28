package jcastaneda.fullstacktodolist.controller;

import jcastaneda.fullstacktodolist.dto.TodoDTO;
import jcastaneda.fullstacktodolist.model.Todo;
import jcastaneda.fullstacktodolist.service.TodoService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/todo")
@AllArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @PutMapping("/addTodo/{newTodo}")
    public List<TodoDTO> addTodo(@PathVariable @NonNull String newTodo) {
        return todoService.addTodo(newTodo);
    }

    @PostMapping("/updateTodo")
    public List<TodoDTO> updateTodo(@RequestBody @NonNull Todo updatedTodo) {
        return todoService.updateTodo(updatedTodo);
    }

    @DeleteMapping("/deleteTodo/{id}")
    public List<TodoDTO> deleteTodo(@PathVariable UUID id) {
        return todoService.deleteTodo(id);
    }

    @GetMapping("/getAll")
    public List<TodoDTO> getAllTodo() {
        return todoService.getAllTodos();
    }
}
