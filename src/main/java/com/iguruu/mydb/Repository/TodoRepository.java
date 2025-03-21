package com.iguruu.mydb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.iguruu.mydb.Entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
