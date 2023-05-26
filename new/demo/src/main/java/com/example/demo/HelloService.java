
@RestController
public class HelloService {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, world!";
    }
}
