package com.example.proyecto_fingeso.controllers;

import com.example.proyecto_fingeso.entities.Chat;
import com.example.proyecto_fingeso.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {
    @Autowired
    ChatService chatService;

    @GetMapping("/")
    public ResponseEntity<List<Chat>> listChat() {
        List<Chat> chats = chatService.getAll();
        return ResponseEntity.ok(chats);
    }

    // @PostMapping("/")
    // public ResponseEntity<Chat> saveChat(@RequestBody Chat chat) {
    //     Chat newChat = chatService.saveChat(chat);
    //     return ResponseEntity.ok(newChat);
    // }

    @GetMapping("/buscar")
    public ResponseEntity<List<Chat>> buscarChatExistente(
            @RequestParam Long emisorId,
            @RequestParam Long receptorId,
            @RequestParam Long viviendaId) {
        List<Chat> chats = chatService.buscarChatExistente(emisorId, receptorId, viviendaId);
        return ResponseEntity.ok(chats);
    }

    @PostMapping("/")
    public ResponseEntity<Chat> crearNuevoChat(
            @RequestParam Long emisorId,
            @RequestParam Long receptorId,
            @RequestParam Long viviendaId,
            @RequestParam String mensajeInicial) {
        Chat nuevoChat = chatService.crearNuevoChat(emisorId, receptorId, viviendaId, mensajeInicial);
        return ResponseEntity.ok(nuevoChat);
    }

    @GetMapping("/unread/{userId}")
    public ResponseEntity<Long> contarMensajesNoLeidos(@PathVariable Long userId) {
        Long count = chatService.contarMensajesNoLeidos(userId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/unread-messages/{receptorId}")
    public ResponseEntity<List<Chat>> obtenerMensajesNoLeidos(@PathVariable Long receptorId) {
        List<Chat> mensajesNoLeidos = chatService.obtenerMensajesNoLeidos(receptorId);
        return ResponseEntity.ok(mensajesNoLeidos);
    }

    @PostMapping("/{chatId}/leer")
    public ResponseEntity<Chat> marcarComoLeido(@PathVariable Long chatId) {
        Chat chatLeido = chatService.marcarComoLeido(chatId);
        return ResponseEntity.ok(chatLeido);
    }
}
