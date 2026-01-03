package com.trf5.jus.br.sgc.domain.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

/***/
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Instant cridoEm;

    private Instant alteradoEm;


    @PrePersist
    void onCreate() {
        this.cridoEm = Instant.now();
        this.alteradoEm = Instant.now();
    }


    @PreUpdate
    void onUpdate(){
        this.alteradoEm = Instant.now();
    }

}

