package com.todo.models;

import java.util.Date;
import javax.persistence.*;
import java.text.SimpleDateFormat;  
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
    
//    @Column()
//    private Date date;

    public Task() {
    }

    public Task(String description) {
        this.description = description;
//        DateFormat df = new SimpleDateFormat("mm/dd/yyyy");
//        Date startDate = df.parse(startDate);
//        this.date = date;
    }
    
    public long getId() {
        return this.id;
    }

    public String getDescription() {
        return description;
    }
    
//    public Date getDate() {
//        return date;
//    }
    
//    public void setDate(Date date) {
//        this.date = date;
//    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public String toString() {
        return "Task {" +
                "id=" + id +
                ", description='" + description + '\'' +
//                ", date=" + date +
                '}';
    }
}
