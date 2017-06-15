package com.todo.dao.jpa;
import com.todo.models.Task;
import java.util.Date;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long>{
    Iterable<Task> findTaskByDate(Date date);
}
