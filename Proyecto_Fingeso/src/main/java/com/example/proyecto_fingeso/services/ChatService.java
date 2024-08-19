package com.example.proyecto_fingeso.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecto_fingeso.entities.Chat;
import com.example.proyecto_fingeso.entities.Usuario;
import com.example.proyecto_fingeso.repository.InterChat;

@Service
public class ChatService {
    @Autowired
    InterChat interChat;

    //obtiene todos los autos
    public ArrayList<Chat> getChatByReceptor(Usuario usuario){
        return (ArrayList<Chat>) interChat.findByReceptor(usuario);
    }

    public ArrayList<Chat> getAll() {
        return (ArrayList<Chat>) interChat.findAll();
    }

    public Chat saveChat(Chat chat) {
        return (Chat) interChat.save(chat);
    }
}
