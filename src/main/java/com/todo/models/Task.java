package com.todo.models;

import java.util.Date;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;;
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
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column()
    private Date date;

    public Task() {
    }

    public Task(String description,Date date) {
        this.description = description;
        this.date = date;
    }
    
    public long getId() {
        return this.id;
    }

    public String getDescription() {
        return description;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
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
