package com.example.personalQualityTracker;

import com.example.personalQualityTracker.security.application.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class PostConstruct implements InitializingBean {

    private static final Logger LOG = Logger.getLogger(String.valueOf(PostConstruct.class));

    @Autowired
    UserService userService;

    @Autowired
    private Environment environment;

    @Override
    public void afterPropertiesSet() {
        try{
            System.out.println("Geregistreerd");
//            userService.register("user@user.com", "user123", "EMPLOYEE");
            userService.register("admin@admin.com", "admin123", "STREAM_LEAD");
        }catch (Exception ignored){}
    }
}
