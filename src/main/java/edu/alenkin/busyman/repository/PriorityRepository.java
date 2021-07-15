package edu.alenkin.busyman.repository;

import edu.alenkin.busyman.model.Priority;
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
public interface PriorityRepository extends JpaRepository<Priority, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Priority p WHERE p.id=:id AND p.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT p FROM Priority p WHERE p.user.id=:userId")
    List<Priority> getAll(@Param("userId") int userId);
}
