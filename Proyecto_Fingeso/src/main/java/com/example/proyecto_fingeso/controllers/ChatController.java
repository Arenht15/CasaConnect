package com.example.proyecto_fingeso.controllers;

import com.example.proyecto_fingeso.entities.Chat;
import com.example.proyecto_fingeso.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @PostMapping("/")
    public ResponseEntity<Chat> saveChat(@RequestBody Chat chat) {
        Chat newChat = chatService.saveChat(chat);
        return ResponseEntity.ok(newChat);
    }
}
