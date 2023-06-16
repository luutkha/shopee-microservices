package daily.learn.auth.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ROLES")
public class Role {

    @Id
    @SequenceGenerator(
            name = "roles_sequence",
            sequenceName = "ROLE",
            allocationSize = 20
    )
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "roles_sequence")
    private Long id;

    @Column(unique = true)
    private String name;

    @Fetch(value = FetchMode.SELECT)
    @JsonIgnore
    @ManyToMany(mappedBy = "roles")

    private Set<User> users = new HashSet<>();

    public Role(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
