package edu.alenkin.busyman.repository;

import edu.alenkin.busyman.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Repository
@Transactional(readOnly = true)
public interface TaskRepository extends JpaRepository<Task, IndexOutOfBoundsException> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Task t WHERE t.id=:id AND t.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT t FROM Task t WHERE t.user.id=:userId ORDER BY t.date DESC")
    List<Task> getAll(@Param("userId") int userId);

    @Query("SELECT t FROM Task t WHERE t.id=:id AND t.user.id=:userId")
    Task get(@Param("id") int id, @Param("userId") int userId);
}
