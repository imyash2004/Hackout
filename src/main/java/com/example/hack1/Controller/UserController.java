package com.example.hack1.Controller;

import com.example.hack1.Repository.UserRepo;
import com.example.hack1.Repository.VideoRepo;
import com.example.hack1.entities.Message;
import com.example.hack1.entities.User;
import com.example.hack1.entities.Video;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private VideoRepo videoRepo;

    @PostMapping("/uploading")
    public String uploaded(@ModelAttribute Video video, Principal p,@RequestParam("email") String email, @RequestParam("vrl") MultipartFile vr, HttpSession session){
        try {

            User u=this.userRepo.getUserByEmail(email);

            video.setUserId(u.getUserId());



            if(vr.isEmpty()){

                System.out.println("file is empty");
                return "upload";
            }
            else{
                video.setVideoUrl(vr.getOriginalFilename() );
                File file=new ClassPathResource("static/images").getFile();
                Path path = Paths.get(file.getAbsolutePath()+File.separator+vr.getOriginalFilename());
                Files.copy(vr.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);

                System.out.println("video is uploaded");
            }
            video.setTags(null);

            video.setDislikes(0);
            video.setLikes(0);

this.videoRepo.save(video);


            session.setAttribute("message",new Message("your contact is added successfully","success"));


        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("error"+e.getMessage());

            session.setAttribute("message",new Message("something went wrong try again","alert"));

        }
        return "home";
    }


}
