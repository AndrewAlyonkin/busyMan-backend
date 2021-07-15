package edu.alenkin.busyman.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

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
    private Timestamp date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;

}
