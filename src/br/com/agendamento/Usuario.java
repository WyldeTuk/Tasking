package br.com.agendamento;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String nome;
    private String email;
    private List<Tarefa> tarefas;

    public Usuario(String nome, String email){
        this.nome = nome;
        this.email = email;
        this.tarefas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }

    public void adicionarTarefa(Tarefa tarefa) {
        this.tarefas.add(tarefa);
    }

    public void removerTarefa(Tarefa tarefa) {
        this.tarefas.remove(tarefa);
    }

}
