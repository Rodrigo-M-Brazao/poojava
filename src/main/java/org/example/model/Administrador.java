package org.example.model;

public class Administrador  extends Funcionario{
    public Administrador(String nome, String funcao, String username, String password, int tipoConta, String data, int idade,double salario) {
        super(nome, funcao, username, password, tipoConta, data, idade, salario);
    }

    public Administrador() {
    }
}
