package com.server.demo;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
public class ChatController {
    
    @Autowired
    private ChatService chatService;

    @GetMapping("/{id}")
    public Chat getById(@PathVariable Long id) {
        return chatService.findById(id);
    }

    @PostMapping("/")
    public Chat save(@RequestBody Chat chat) {
        return chatService.save(chat);
    }

    @PutMapping("/{id}")
    public Chat update(@PathVariable Long id, @RequestBody Chat chat) {
        chat.setId(id);
        return chatService.save(chat);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        chatService.delete(id);
    }

    @GetMapping("/")
    public List<Chat> getAll() {
        return chatService.findAll();
    }
}
