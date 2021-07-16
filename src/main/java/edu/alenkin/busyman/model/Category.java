package edu.alenkin.busyman.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category extends AbstractBaseEntity {

    @Column(name = "title", nullable = false, length = 45)
    @NotNull
    private String title;

    @Column(name = "completed_count")
    private int completedCount;

    @Column(name = "uncompleted_count")
    private int uncompletedCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;

    public Category(Integer id, User user, String title, int completedCount, int uncompletedCount) {
        super(id);
        this.user = user;
        this.title = title;
        this.completedCount = completedCount;
        this.uncompletedCount = uncompletedCount;
    }

    public Category(Integer id) {
        super(id);
    }

    public Category(Category category) {
        this(category.getId(), category.getUser(), category.getTitle(), category.getCompletedCount(), category.getUncompletedCount());
    }
}
