package com.github.vinolaa.tc1.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
@EntityListeners(AuditingEntityListener.class)
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(nullable = false, updatable = false)
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private String updatedBy;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "logradouro", column = @Column(name = "end_logradouro")),
        @AttributeOverride(name = "numero", column = @Column(name = "end_numero")),
        @AttributeOverride(name = "complemento", column = @Column(name = "end_complemento")),
        @AttributeOverride(name = "bairro", column = @Column(name = "end_bairro")),
        @AttributeOverride(name = "cidade", column = @Column(name = "end_cidade")),
        @AttributeOverride(name = "uf", column = @Column(name = "end_uf")),
        @AttributeOverride(name = "cep", column = @Column(name = "end_cep"))
    })
    private Endereco endereco;

    protected User() {}

    protected User(String name, String email, String login, String password, Endereco endereco) {
        this.name = name;
        this.email = email;
        this.login = login;
        this.password = password;
        this.endereco = endereco;
    }

    public UUID getId()             { return id; }
    public String getName()         { return name; }
    public String getEmail()        { return email; }
    public String getLogin()        { return login; }
    public String getPassword()     { return password; }
    public Endereco getEndereco()   { return endereco; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public void updateInfo(String name, String email, String login, Endereco endereco) {
        this.name = name;
        this.email = email;
        this.login = login;
        if (endereco != null) {
            this.endereco = endereco;
        }
    }

    public void updatePassword(String encodedPassword) {
        this.password = encodedPassword;
    }
}