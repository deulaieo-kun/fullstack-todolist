package jcastaneda.fullstacktodolist.service;

import com.sun.xml.bind.v2.TODO;
import jcastaneda.fullstacktodolist.dto.TodoDTO;
import jcastaneda.fullstacktodolist.entity.TodoListEntity;
import jcastaneda.fullstacktodolist.model.Todo;
import jcastaneda.fullstacktodolist.repository.TodoRepository;
import jcastaneda.fullstacktodolist.util.DateTimeUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class TodoService {

    private final DateTimeUtil dateTimeUtil;
    private final TodoRepository todoRepository;
    private final ModelMapper modelMapper;

    public List<TodoDTO> addTodo(@NonNull String newTodo) {

//        Initialize new todo
        TodoListEntity todo = TodoListEntity.builder()
                .todoId(UUID.randomUUID())
                .todo(newTodo)
                .createdDate(dateTimeUtil.currentDate())
                .modifiedDate(dateTimeUtil.currentDate())
                .build();

//        save to database
        todoRepository.save(todo);

        return getAllTodos();
    }

    public List<TodoDTO> updateTodo(@NonNull Todo updatedTodo) {

//        Get To do Entity
        TodoListEntity oldTodo = todoRepository.findByTodoId(updatedTodo.getTodoId());

//        Check if todoId exist
        if (oldTodo == null) throw new RuntimeException("Todo doesn't exist");

//        Update To do
        TodoListEntity newTodo = TodoListEntity.builder()
                .todoId(oldTodo.getTodoId())
                .todo(updatedTodo.getTodo())
                .createdDate(oldTodo.getCreatedDate())
                .modifiedDate(dateTimeUtil.currentDate())
                .build();

//        save to database
        todoRepository.save(newTodo);

        return getAllTodos();
    }

    public List<TodoDTO> deleteTodo(UUID id) {
//        Get To do Entity
        TodoListEntity todo = todoRepository.findByTodoId(id);

        if(todo == null) throw new RuntimeException("Todo doesn't exist");

//        Delete To do
        todoRepository.deleteByTodoId(id);

        return getAllTodos();
    }

    public List<TodoDTO> getAllTodos() {
        List<TodoListEntity> allTodos = todoRepository.findAll(Sort.by(Sort.Direction.ASC, "createdDate"));
        List<TodoDTO> updatedList = new ArrayList<>();

        allTodos.forEach(data -> {
            updatedList.add(modelMapper.map(data, TodoDTO.class));
        });

        return updatedList;
    }
}
