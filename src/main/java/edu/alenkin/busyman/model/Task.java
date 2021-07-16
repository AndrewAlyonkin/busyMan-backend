package edu.alenkin.busyman.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Entity
@Table(name = "task")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task extends AbstractBaseEntity {

    @Column(name = "title")
    @Size(max = 100)
    @NotBlank
    private String title;

    @Column(name = "completed")
    private Integer completed;

    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;

    @ManyToOne
    @JoinColumn(name = "priority_id")
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Task(Integer id, User user, String title, int completed,
                LocalDateTime date, Priority priority, Category category) {
        super(id);
        this.user = user;
        this.title = title;
        this.completed = completed;
        this.date = date;
        this.priority = priority;
        this.category = category;
    }

    public Task(Task task) {
        this(task.getId(), task.getUser(), task.title, task.getCompleted(), task.getDate(),
                task.getPriority(), task.getCategory());
    }
}
