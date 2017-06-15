package com.todo;


import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = "com.todo")
@EnableJpaRepositories("com.todo.dao.jpa") 
@EnableAutoConfiguration
public class ToDoApplication extends SpringBootServletInitializer{
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(ToDoApplication.class, args);  
	}

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ToDoApplication.class);
    }

}
