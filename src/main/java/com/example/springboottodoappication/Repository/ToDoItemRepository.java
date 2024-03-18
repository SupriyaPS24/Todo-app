package com.example.springboottodoappication.Repository;

import com.example.springboottodoappication.model.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoItemRepository extends JpaRepository<ToDoItem, Long> {

}
