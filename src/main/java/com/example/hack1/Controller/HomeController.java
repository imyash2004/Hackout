package com.example.hack1.Controller;

import com.example.hack1.Repository.UserRepo;
import com.example.hack1.entities.Message;
import com.example.hack1.entities.User;
import com.example.hack1.entities.Video;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;

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
    @GetMapping("/")
    public String index(){
        return "home";
    }
    @GetMapping("/signup")
    public String signup(Model m,HttpSession session){
        //m.addAttribute("user",new User());
        //session.setAttribute("message"," ");
        return "signup";
    }
    @GetMapping ("/user/home")
    public String home(HttpSession session){

        return "home";
    }
    @PostMapping("/do_register")
    public String registerUser(@ModelAttribute("user") User user, @RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model, HttpSession session) {
        try {
            if (!agreement) {
                //model.addAttribute("message", ("You have not agreed to the terms and conditions"));
                session.setAttribute("message",new Message("You have not agreed terms and Condition","alert-danger"));
                return "signup"; // Return to the registration form
            } else {
                // Perform user data validation here (e.g., using annotations or custom validation logic)
                // If validation fails, return an error message and stay on the registration form
                user.setRole("ROLE_USER");
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
    public String login(){
        return "login";
    }
    @PostMapping("/dologin")
    public String login1(@RequestParam("email") String email,@RequestParam("password") String password,HttpSession session){
        User u=this.userRepo.getUserByEmail(email);
        if(u==null){
            session.setAttribute("message",new Message("Invalid UserName and Password","alert-danger"));
            return "login";
        }
        if(u.getPassword().equals(password)){
            session.setAttribute("email",u.getEmail());

            return"scroll";
        }
        else {
            session.setAttribute("message",new Message("Invalid UserName and Password","alert-danger"));

            return "login";
    }
    }
    @GetMapping("/user/upload")
    public String upload(){

        return "upload";
    }
//    @PostMapping("/uploading")
//    public String uploaded(@ModelAttribute Video video, Principal p, @RequestParam("vrl") MultipartFile vr,HttpSession session){
//        try {
//            String name=p.getName();
//            System.out.println(name);
//            User user=this.userRepo.getUserByEmail(name);
//            video.setUserId(user.getUserId());
//
//
//
//            if(vr.isEmpty()){
//
//                System.out.println("file is empty");
//                return "upload";
//            }
//            else{
//                video.setVideoUrl(vr.getOriginalFilename() );
//                File file=new ClassPathResource("static/video").getFile();
//                Path path = Paths.get(file.getAbsolutePath()+File.separator+vr.getOriginalFilename());
//
//                Files.copy(vr.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
//                System.out.println("video is uploaded");
//            }
//            video.setTags(null);
//            video.setComments(null);
//            video.setDislikes(0);
//            video.setLikes(0);
//
//
//
//
//            session.setAttribute("message",new Message("your contact is added successfully","success"));
//
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//            System.out.println("error"+e.getMessage());
//
//            session.setAttribute("message",new Message("something went wrong try again","alert"));
//
//        }
//        return "home";
//        }
//

}
