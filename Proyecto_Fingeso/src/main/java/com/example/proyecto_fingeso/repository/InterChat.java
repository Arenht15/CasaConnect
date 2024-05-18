package com.example.proyecto_fingeso.repository;

import com.example.proyecto_fingeso.entities.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InterChat extends JpaRepository<Chat, Long> {

    @Query(value = "SELECT * FROM chat WHERE chat.id = :id", nativeQuery = true)
    Chat findByChatId(@Param("id") Long id);
}
