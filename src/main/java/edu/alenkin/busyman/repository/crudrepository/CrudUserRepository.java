package edu.alenkin.busyman.repository.crudrepository;

import edu.alenkin.busyman.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Repository
@Transactional(readOnly = true)
public interface CrudUserRepository extends JpaRepository<User, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id=:id")
    int delete(@Param("id") int id);

    User getByEmail(String email);

    @Query("SELECT u.completedTotal FROM User u WHERE u.id=:id")
    Integer getCompleted(@Param("id") int id);

    @Query("SELECT u.uncompletedTotal FROM User u WHERE u.id=:id")
    Integer getUncompleted(@Param("id") int id);
}
