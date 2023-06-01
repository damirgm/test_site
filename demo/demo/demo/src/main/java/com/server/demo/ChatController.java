package com.server.demo;

import spark.Request;
import spark.Response;
import spark.template.mustache.MustacheTemplateEngine;


import java.util.HashMap;
import java.util.Map;

public class ChatController {
    private ChatRepository chatRepository;

    public ChatController(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public String index(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        model.put("chats", chatRepository.findAll());
        return new MustacheTemplateEngine().render(new ModelAndView(model, "index.mustache"));
    }

    public String show(Request request, Response response) {
        Long id = Long.parseLong(request.params("id"));
        Map<String, Object> model = new HashMap<>();
        model.put("chat", chatRepository.findById(id));
        return new MustacheTemplateEngine().render(new ModelAndView(model, "show.mustache"));
    }

    public String create(Request request, Response response) {
        String name = request.queryParams("name");
        Chat chat = new Chat(null, name);
        chatRepository.save(chat);
        response.redirect("/chats/" + chat.getId());
        return "";
    }

    public String delete(Request request, Response response) {
        Long id = Long.parseLong(request.params("id"));
        chatRepository.delete(id);
        response.redirect("/chats");
        return "";
    }
}
