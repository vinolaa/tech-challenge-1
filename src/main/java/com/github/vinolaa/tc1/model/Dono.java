package com.github.vinolaa.tc1.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("DONO")
public class Dono extends User {

    @Column(length = 14)
    private String cnpj;

    protected Dono() {}

    public Dono(String name, String email, String login, String password, Endereco endereco, String cnpj) {
        super(name, email, login, password, endereco);
        this.cnpj = cnpj;
    }

    public String getCnpj() { return cnpj; }
}