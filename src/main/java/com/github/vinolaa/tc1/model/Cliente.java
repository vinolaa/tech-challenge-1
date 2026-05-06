package com.github.vinolaa.tc1.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CLIENTE")
public class Cliente extends User {

    @Column(length = 11)
    private String cpf;

    protected Cliente() {}

    public Cliente(String name, String email, String login, String password, Endereco endereco, String cpf) {
        super(name, email, login, password, endereco);
        this.cpf = cpf;
    }

    public String getCpf() { return cpf; }
}