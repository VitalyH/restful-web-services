package com.vitalyh.restfulwebservices.Todo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


@Service
class TodoService {
    private static List<Todo> todos = new ArrayList<Todo>();
    private static int todoCount = 3;

    static {
        todos.add(new Todo(1, "user", "Learn Spring MVC", new Date(),
                false));
        todos.add(new Todo(2, "user", "Learn Struts", new Date(), false));
        todos.add(new Todo(3, "user", "Learn Hibernate", new Date(),
                false));
    }

     List<Todo> retrieveTodos(String user) {
        List<Todo> filteredTodos = new ArrayList<Todo>();
        for (Todo todo : todos) {
            if (todo.getUser().equals(user)) {
                filteredTodos.add(todo);
            }
        }
        return filteredTodos;
    }

     void addTodo(String name, String desc, Date targetDate,
                        boolean isDone) {
        todos.add(new Todo(++todoCount, name, desc, targetDate, isDone));
    }

    void deleteTodo(int id) {
        Iterator<Todo> iterator = todos.iterator();
        while (iterator.hasNext()) {
            Todo todo = iterator.next();
            if (todo.getId() == id) {
                iterator.remove();
            }
        }
    }

     Todo retrieveTodo(int id) {
        List<Todo> filteredTodos = new ArrayList<Todo>();
        for (Todo todo : todos) {
            if (todo.getId() == id)
               return todo;
        }
        return null;
    }

    void updateTodo(Todo todo){
        todos.remove(todo);
        todos.add(todo);
    }
}