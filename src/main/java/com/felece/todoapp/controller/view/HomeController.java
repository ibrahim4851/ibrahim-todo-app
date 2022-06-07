package com.felece.todoapp.controller.view;

import com.felece.todoapp.entity.MyUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {

    @GetMapping("/success")
    public RedirectView loginPage() {

        //getting the user's role and ID
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails customUser = (MyUserDetails) authentication.getPrincipal();
        String userRole = customUser.getAuthorities().toString();
        Long userId = customUser.getId();
        //String roleAdmin = "[ROLE_ADMIN]";
        System.out.println("user role : "+ userRole);

        if(userRole.equals("[ROLE_ADMIN]")) {
            return new RedirectView("/admin/home");
        } else if(userRole.equals("[ROLE_USER]")) {
            System.out.println("user id "+ userId);
            return new RedirectView("/user/home/"+userId);
        } else {
            return new RedirectView("/login");
        }
    }

}
