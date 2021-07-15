package edu.alenkin.busyman.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Entity
@Table(name = "priority")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Priority extends AbstractBaseEntity {

    @Column(name = "title", nullable = false, length = 45)
    @NotNull
    private String title;

    @Column(name = "color", nullable = false, length = 45)
    @NotNull
    private String color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;

    public Priority(Integer id, User user, String title, String color) {
        super(id);
        this.user = user;
        this.title = title;
        this.color = color;
    }

    public Priority(Priority priority) {
        this(priority.getId(), priority.getUser(), priority.title, priority.getColor());
    }
}
