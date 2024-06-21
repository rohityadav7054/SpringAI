package com.AI;

import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ai")
public class AIController {

    private final OllamaChatModel ollamaChatModel;
    private static final String PROMPT = "What is python language";

    @Autowired
    public AIController(OllamaChatModel ollamaChatModel) {
        this.ollamaChatModel = ollamaChatModel;
    }

    @GetMapping("/prompt")
    public Flux<String> promptResponse(@RequestParam("prompt") String prompt) {
        try {
            // Use the provided prompt parameter
            return ollamaChatModel.stream(prompt);
        } catch (Exception e) {
            // Handle exceptions appropriately (e.g., return an error message)
            return Flux.just("Error occurred: " + e.getMessage());
        }
    }
}
