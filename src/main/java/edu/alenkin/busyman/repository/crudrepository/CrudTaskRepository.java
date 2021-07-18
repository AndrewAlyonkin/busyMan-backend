package edu.alenkin.busyman.repository.crudrepository;

import edu.alenkin.busyman.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public interface CrudTaskRepository extends JpaRepository<Task, IndexOutOfBoundsException> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Task t WHERE t.id=:id AND t.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT t FROM Task t WHERE t.user.id=:userId ORDER BY t.date DESC")
    List<Task> getAll(@Param("userId") int userId);

    @Query("SELECT t FROM Task t WHERE t.user.id=:userId AND t.priority.id=:priorityId ORDER BY t.date DESC")
    List<Task> getWithPriority(@Param("priorityId") int priorityId, @Param("userId") int userId);

    @Query("SELECT t FROM Task t WHERE t.user.id=:userId AND t.category.id=:categoryId ORDER BY t.date DESC")
    List<Task> getWithCategory(@Param("categoryId") int categoryId, @Param("userId") int userId);

    @Query("SELECT t FROM Task t WHERE t.id=:id AND t.user.id=:userId")
    Task get(@Param("id") int id, @Param("userId") int userId);

    @Query("""
            SELECT t FROM Task t 
            WHERE t.user.id=:userId AND
            (:title is null or :title ='' OR lower(t.title) LIKE  lower(concat('%' , :title, '%'))) AND 
            (:completed is null or t.completed=:completed) AND 
            (:priorityId is null or t.priority.id=:priorityId) AND 
            (:categoryId is null or t.category.id=:categoryId)""")
    Page<Task> findByParameter(@Param("title") String title,
                               @Param("completed") Integer completed,
                               @Param("priorityId") Integer priorityId,
                               @Param("categoryId") Integer categoryId,
                               @Param("userId") int id,
                               Pageable page);


}
