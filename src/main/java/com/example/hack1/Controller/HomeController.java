package com.example.hack1.Controller;

import com.example.hack1.Repository.UserRepo;
import com.example.hack1.entities.Message;
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
    public String signup(Model m,HttpSession session){
        //m.addAttribute("user",new User());
        //session.setAttribute("message"," ");
        return "signup";
    }
    @GetMapping ("/home")
    public String home(HttpSession session){

        return "home";
    }
    @PostMapping("/do_register")
    public String registerUser(@ModelAttribute("user") User user, @RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model, HttpSession session) {
        try {
            if (!agreement) {
                //model.addAttribute("message", ("You have not agreed to the terms and conditions"));
                session.setAttribute("message",new Message("You have not agreed terms and Condition","alert-success"));
                return "signup"; // Return to the registration form
            } else {
                // Perform user data validation here (e.g., using annotations or custom validation logic)
                // If validation fails, return an error message and stay on the registration form

                this.userRepo.save(user);
                session.setAttribute("message",new Message("SuccessFully Registered","alert-success"));
                return "login"; // Redirect to a success page
            }
        } catch (Exception e) {
            session.setAttribute("message",new Message("Some thing went wrong  "+e.getMessage(),"alert-danger"));
            e.printStackTrace();
            return "signup"; // Return to the registration form with an error message
        }

    }
    @GetMapping("/login")
    public String login1(){
        return "login";
    }
    @GetMapping("/dologin")
    public String login(@RequestParam("email") String email,@RequestParam("password") String password){
        User u=this.userRepo.getUserByEmail(email);
        if(u.getPassword().equals(password)){
            return"home";
        }
        else {
        return "login";
    }}

}
