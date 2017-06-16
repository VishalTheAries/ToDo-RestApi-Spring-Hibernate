package com.todo.models;

import java.time.LocalDate;
import com.todo.serializer.*;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
/*
 * a persistant class using jda-hibernate annotations
 */

@Entity
@Table(name = "task")
public class Task {

	@Id
    @GeneratedValue()
    private long id;
	
    @Column()
    private String description;
    
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @Column()
    private LocalDate date;

    public Task() {
    }

    public Task(String description,LocalDate date) {
        this.description = description;
        this.date = date;
        System.out.println(this.date);
    }
    
    public long getId() {
        return this.id;
    }

    public String getDescription() {
        return description;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public String toString() {
        return "Task {" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
