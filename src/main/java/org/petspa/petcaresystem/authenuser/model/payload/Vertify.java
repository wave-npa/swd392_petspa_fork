package org.petspa.petcaresystem.authenuser.model.payload;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Vertify")
@Transactional
public class Vertify {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long vertifyId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "vertify_code", nullable = false)
    private String vertifyCode;
}
