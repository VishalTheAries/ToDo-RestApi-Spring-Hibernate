package com.todo.api;

import org.springframework.web.bind.annotation.*;
import com.todo.models.Task;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.todo.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;

import com.todo.exception.ResourceNotFoundException;

@RestController
@RequestMapping(value = "/todo/task")
public class TaskController {

	
	@Autowired
    private TaskService taskService;
    
	
    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = {"application/json"},
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public void createTask(@RequestBody Task task,
            HttpServletRequest request, HttpServletResponse response) {
    		System.out.print(task);
    		Task createdTask = this.taskService.createtask(task);
    		response.setHeader("Location", request.getRequestURL().append("/").append(createdTask.getId()).toString());
    }
    
    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    Iterable<Task> getAllTask(@RequestParam(value = "date", required = false) String dateparam,
    			HttpServletRequest request, HttpServletResponse response) {
    	System.out.println(dateparam);
    	if(dateparam != null){
    		try{
	    		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
	    		LocalDate localdateparam = LocalDate.parse(dateparam, formatter);
	    		System.out.println(localdateparam);
	    		return this.taskService.getTasksforDate(localdateparam);
    		}catch(Exception e){
    			throw new RuntimeException("Invalid Date format");
    		}
    	}
        return this.taskService.getAllTasks();			
    }
    
    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    Task getTask(@PathVariable("id") Long id,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
        Task task= this.taskService.getTask(id);
        checkResourceFound(task);
        return task;
    }
    
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json"},
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTask(@PathVariable("id") Long id, @RequestBody Task task,
                                 HttpServletRequest request, HttpServletResponse response) {
        checkResourceFound(this.taskService.getTask(id));
        if (id != task.getId()) throw new RuntimeException("ID doesn't match!");
        this.taskService.updateTask(task);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable("id") Long id, HttpServletRequest request,
                                 HttpServletResponse response) {
        checkResourceFound(this.taskService.getTask(id));
        this.taskService.deleteTask(id);
    }

    public static <T> T checkResourceFound(final T resource) {
        if (resource == null) {
            throw new ResourceNotFoundException();
        }
        return resource;
}
	
}
