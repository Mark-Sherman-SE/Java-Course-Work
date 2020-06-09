package main.controller;

import main.entity.User;
import main.service.ArticleService;
import main.service.BalanceService;
import main.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {
    private final ArticleService articleService;
    private final BalanceService balanceService;
    private final OperationService operationService;

    @Value("${string.profiles.active}")
    private String profile;

    @Autowired
    public MainController(ArticleService articleService, BalanceService balanceService, OperationService operationService) {
        this.articleService = articleService;
        this.balanceService = balanceService;
        this.operationService = operationService;
    }

    @GetMapping
    public String main() {
        return "homePage"; //index
    }

    @GetMapping("articles")
    public String index(Model model, @AuthenticationPrincipal User user) {
        HashMap<Object, Object> data = new HashMap<>();

        data.put("profile", user);
        data.put("messages", articleService.listArticles());

        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", "dev".equals(profile));
        return "index";
    }

    @GetMapping("balances")
    public String balancePage(Model model, @AuthenticationPrincipal User user) {
        HashMap<Object, Object> data = new HashMap<>();

        data.put("profile", user);
        data.put("messages", balanceService.listBalance());

        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", "dev".equals(profile));
        return "balancePage";
    }

    @GetMapping("operations")
    public String operationPage(Model model, @AuthenticationPrincipal User user) {
        HashMap<Object, Object> data = new HashMap<>();

        data.put("profile", user);
        data.put("messages", operationService.listOperation());

        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", "dev".equals(profile));
        return "operationPage";
    }

}