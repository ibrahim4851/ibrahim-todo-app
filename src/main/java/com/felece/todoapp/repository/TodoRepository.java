package com.felece.todoapp.repository;

import com.felece.todoapp.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findTodosByUserId(Long id);
    List<Todo> findTodosByUserIdOrderByDateAsc(String id);
    List<Todo> findAllByDateBetween(Date date, Date date2);
}