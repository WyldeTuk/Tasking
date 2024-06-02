package br.com.agendamento;

public class Main {
    public static void main(String[] args){
        System.out.println("Iniciando sistema de agendamento da Tasking...");
        System.out.println("Sistema iniciado \n Seja bem vindo!");

        Agenda agenda = new Agenda();
        agenda.menu();
        
    }
}
