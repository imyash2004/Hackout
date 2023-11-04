package com.example.hack1.Controller;

import com.example.hack1.Repository.UserRepo;
import com.example.hack1.entities.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController

{
    @Autowired
    private UserRepo userRepo;
//    @GetMapping("/")
//    public String home(){
//        System.out.println("yash" +
//                "bd");
//        return "home";
//    }
    @GetMapping("/signup")
    public String signup(Model m){
        //m.addAttribute("user",new User());
        return "signup";
    }
    @GetMapping ("/home")
    public String home(){

        return "home";
    }
    @PostMapping("do_register")
    public String registerUser(@ModelAttribute("user") User user, @RequestParam(value = "agreement",defaultValue = "false") boolean agreement, Model m, HttpSession session){
        this.userRepo.save(user);
        return "signup";
    }

}
