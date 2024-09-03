package com.example.proyecto_fingeso.repository;

import com.example.proyecto_fingeso.entities.Chat;
import com.example.proyecto_fingeso.entities.Usuario;

import java.util.ArrayList;

import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InterChat extends JpaRepository<Chat, Long> {

    @Query(value = "SELECT * FROM chat WHERE chat.id = :id", nativeQuery = true)
    Chat findByChatId(@Param("id") Long id);

    //@Query(value = "SELECT * FROM chat WHERE chat.id = :id", nativeQuery = true)
    ArrayList<Chat> findByReceptor(Usuario usuario);

    @Query("SELECT c FROM Chat c WHERE " +
           "(c.emisor.idUsuario = :emisorId AND c.receptor.idUsuario = :receptorId AND c.vivienda.idVivienda = :viviendaId) OR " +
           "(c.emisor.idUsuario = :receptorId AND c.receptor.idUsuario = :emisorId AND c.vivienda.idVivienda = :viviendaId)")
    ArrayList<Chat> findExistingChat(@Param("emisorId") Long emisorId,
                                @Param("receptorId") Long receptorId,
                                @Param("viviendaId") Long viviendaId);

    @Query("SELECT COUNT(c) FROM Chat c WHERE c.receptor.idUsuario = :userId AND c.leido = false")
    Long countUnreadMessages(@Param("userId") Long userId);

    ArrayList<Chat> findByReceptorIdUsuarioAndLeidoFalse(Long receptorId);
}
