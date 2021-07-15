package edu.alenkin.busyman.model;

import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 * <p>
 * The user of application.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends AbstractBaseEntity {
    @NotBlank
    @Column(name = "name", nullable = false)
    @Size(max = 100)
    private String name;

    @Column(name = "email", nullable = false)
    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(max = 100)
    private String password;

    @Column(name = "registered", nullable = false)
    @NotNull
    private LocalDateTime registered;

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private boolean enabled;

    @Column(name = "completed_total")
    private int completedTotal;

    @Column(name = "uncompleted_total")
    private int uncompletedTotal;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role"}, name = "uk_user_roles")})
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    private Set<Role> roles;

    public User(Integer id, String name, String email, String password, boolean enabled, LocalDateTime registered,
                int completed, int uncompleted, Role... roles) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.registered = registered;
        this.completedTotal = completed;
        this.uncompletedTotal = uncompleted;
        this.roles = Set.of(roles);
    }

    public User(User u) {
        this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.isEnabled(), u.getRegistered(),
                u.getCompletedTotal(), u.getUncompletedTotal(), u.getRoles().toArray(new Role[0]));
    }

    public User(Integer id) {
        super(id);
    }
}
