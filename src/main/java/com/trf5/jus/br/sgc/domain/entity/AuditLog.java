package com.trf5.jus.br.sgc.domain.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // RELAÇÃO N:1 — Um User pode gerar muitas auditorias
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

    private String action; // CREATE, UPDATE, DELETE

    private String entity; // nome da entidade afetada

    @Column(name = "entity_id")
    private Long entityId;

    // guarda dados alterados
    @Column(columnDefinition = "jsonb")
    private String data;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}

