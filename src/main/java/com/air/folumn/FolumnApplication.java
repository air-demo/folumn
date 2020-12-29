package com.air.folumn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class FolumnApplication extends SpringBootServletInitializer {

   @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
        return super.configure(builder);
    }
    public static void main(String[] args) {
        SpringApplication.run(FolumnApplication.class, args);
    }
    /* public static void main(String[] args) {
        //Spring应用启动起来
        SpringApplication.run(FolumnApplication.class,args);
    }*/

}
