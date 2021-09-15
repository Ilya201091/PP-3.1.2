package ru.ilya.springboot.springboot.entities;

import lombok.*;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@RequiredArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name="roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "roleName")
    private String roleName;

    @Override
    public String getAuthority() {
        return roleName;
    }


}
