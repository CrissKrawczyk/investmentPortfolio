package pl.portfolio.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
    @RequestMapping(value = {
            "",
            "/",
            "/{path:^(?!api|assets|favicon\\.ico|index\\.html).*}"
    })
    public String forward() {
        return "forward:/index.html";
    }
}