package com.server.demo;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    
    @Autowired
    private MessageService messageService;

    @GetMapping("/{id}")
    public Message getById(@PathVariable Long id) {
        return messageService.findById(id);
    }

    @PostMapping("/")
    public Message save(@RequestBody Message message) {
        return messageService.save(message);
    }

    @PutMapping("/{id}")
    public Message update(@PathVariable Long id, @RequestBody Message message) {
        message.setId(id);
        return messageService.save(message);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        messageService.delete(id);
    }
}
