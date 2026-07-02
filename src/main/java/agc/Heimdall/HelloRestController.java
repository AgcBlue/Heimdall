package agc.Heimdall;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class HelloRestController 
{
    @GetMapping("/nameing")
    public String sayHello(String name) 
    {
        return String.format("Hello, %s!", name);
    }
}