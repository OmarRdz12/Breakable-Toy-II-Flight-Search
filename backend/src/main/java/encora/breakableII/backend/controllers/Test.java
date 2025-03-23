package encora.breakableII.backend.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @GetMapping("/ping")
    String createTodo() {
        return  "pong";
    }
}
