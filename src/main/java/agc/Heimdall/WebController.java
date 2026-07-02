package agc.Heimdall;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;

@Controller 
public class WebController 
{
    @GetMapping("/Welcome")
    public String welcome(Model model) 
    {
        model.addAttribute("message", "Hello from Spring Boot!");
        return "Welcome";
    }
}