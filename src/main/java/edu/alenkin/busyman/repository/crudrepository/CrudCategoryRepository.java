package edu.alenkin.busyman.repository.crudrepository;

import edu.alenkin.busyman.model.Category;
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
public interface CrudCategoryRepository extends JpaRepository<Category, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Category c WHERE c.id=:id AND c.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT c FROM Category c WHERE c.user.id=:userId")
    List<Category> getAll(@Param("userId") int userId);

    @Query("SELECT c FROM Category c WHERE c.id =:id AND c.user.id=:userId")
    Category get(@Param("id") int id, @Param("userId") int userId);

    @Query("""
            SELECT c FROM Category c 
            WHERE c.user.id=:userId AND (:search is null or :search =''
            OR lower(c.title) LIKE  lower(concat('%' , :search, '%')))""")
    Page<Category> findByParameter(@Param("search") String search, @Param("userId") int id, Pageable page);
}
