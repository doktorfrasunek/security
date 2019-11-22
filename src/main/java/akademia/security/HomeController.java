package akademia.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {
    @GetMapping("/")
    public String getHomePage() {
        return "index";
    }
    @GetMapping("/user")
    public String getUserPage() {
        return "user";
    }
    @GetMapping("/admin")
    public String getAdminPage() {
        return "admin";
    }
}
