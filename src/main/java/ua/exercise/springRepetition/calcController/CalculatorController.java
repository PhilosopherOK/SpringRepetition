package ua.exercise.springRepetition.calcController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/calculator")
public class CalculatorController {
    @GetMapping("/calculator")
    String calculate(@RequestParam(value = "a", required = false) int a,
                     @RequestParam(value = "b", required = false) int b,
                     @RequestParam(value = "action", required = false) String action,
                     Model model){
        int result;
        switch (action){
            case "multiplication":
                result = a * b;
                break;
            case "addition":
                result = a + b;
                break;
            case "subtraction":
                result = a - b;
                break;
            case "division":
                result = a / b;
                break;
            default:
                result = 0;
                break;
        }
        model.addAttribute("message", result);
        return "calculator/calculator";
    }
}
