package com.mindtech.newsletter.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "inscricao")
public class InscricaoModel implements Serializable {

    private static final long serialVersionUID = 3827726128685061365L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", length = 80, nullable = false, unique = true)
    private String email;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "data_hora_criacao", nullable = false, columnDefinition = "TIMESTAMP(0) WITHOUT TIME ZONE")
    private LocalDateTime dataHoraCriacao;

    public InscricaoModel() {
    }

    public InscricaoModel(String email, LocalDateTime criadoEm) {
        this.email = email;
        this.dataHoraCriacao = criadoEm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }
}
