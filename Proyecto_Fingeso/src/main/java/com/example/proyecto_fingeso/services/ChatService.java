package com.example.proyecto_fingeso.services;

import java.util.ArrayList;
import java.time.LocalDateTime;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecto_fingeso.entities.Chat;
import com.example.proyecto_fingeso.entities.Usuario;
import com.example.proyecto_fingeso.entities.Vivienda;
import com.example.proyecto_fingeso.repository.InterChat;
import com.example.proyecto_fingeso.repository.InterUsuario;
import com.example.proyecto_fingeso.repository.InterVivienda;

@Service
public class ChatService {
    @Autowired
    InterChat interChat;
    @Autowired
    InterUsuario interUsuario;
    @Autowired
    InterVivienda interVivienda;

    //obtiene todos los autos
    public ArrayList<Chat> getChatByReceptor(Usuario usuario){
        return (ArrayList<Chat>) interChat.findByReceptor(usuario);
    }

    public ArrayList<Chat> getAll() {
        return (ArrayList<Chat>) interChat.findAll();
    }

    public Chat saveChat(Chat chat) {
        Usuario emisor = interUsuario.findByUsuarioId(chat.getEmisor().getIdUsuario());
        return (Chat) interChat.save(chat);
    }

    public ArrayList<Chat> buscarChatExistente(Long emisorId, Long receptorId, Long viviendaId) {
        return interChat.findExistingChat(emisorId, receptorId, viviendaId);
    }

    public Chat crearNuevoChat(Long emisorId, Long receptorId, Long viviendaId, String mensajeInicial) {
        Usuario emisor = interUsuario.findByUsuarioId(emisorId);
        // .orElseThrow(() -> new RuntimeException("Emisor no encontrado"));
        Usuario receptor = interUsuario.findByUsuarioId(receptorId);
        // .orElseThrow(() -> new RuntimeException("Receptor no encontrado"));
        Vivienda vivienda = interVivienda.findByViviendaId(viviendaId);
        // .orElseThrow(() -> new RuntimeException("Vivienda no encontrada"));

        Chat nuevoChat = new Chat();
        nuevoChat.setEmisor(emisor);
        nuevoChat.setReceptor(receptor);
        nuevoChat.setVivienda(vivienda);
        nuevoChat.setMensaje(mensajeInicial);
        nuevoChat.setFecha(LocalDateTime.now());
        nuevoChat.setLeido(false);

        return interChat.save(nuevoChat);
    }

    public Long contarMensajesNoLeidos(Long userId) {
        return interChat.countUnreadMessages(userId);
    }

    public ArrayList<Chat> obtenerMensajesNoLeidos(Long receptorId) {
        return interChat.findByReceptorIdUsuarioAndLeidoFalse(receptorId);
    }

    public Chat marcarComoLeido(Long chatId) {
        Chat chat = interChat.findById(chatId).orElseThrow(() -> new RuntimeException("Chat no encontrado"));
        chat.setLeido(true);
        return interChat.save(chat);
    }
}
