package br.com.agendamento;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Agenda {
    private List<Usuario> usuarios;
    private List<Tarefa> tarefas;

    public Agenda() {
        this.usuarios = new ArrayList<>();
        this.tarefas = new ArrayList<>();
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
    
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Registrar Usuário");
            System.out.println("2. Criar Tarefa");
            System.out.println("3. Atribuir Tarefa");
            System.out.println("4. Listar Tarefas");
            System.out.println("5. Listar Tarefas por Usuário");
            System.out.println("6. Deletar Tarefa");
            System.out.println("7. Listar Usuários");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");


            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir nova linha

                switch (opcao) {
                    case 1:
                        registrarUsuario(scanner);
                        break;
                    case 2:
                        criarTarefa(scanner);
                        break;
                    case 3:
                        atribuirTarefa(scanner);
                        break;
                    case 4:
                        listarTarefas();
                        break;
                    case 5:
                        listarTarefasUsuario(scanner);
                        break;
                    case 6:
                        deletarTarefa(scanner);
                        break;
                    case 7:
                        listarUsuariosETarefas();
                        break;
                    case 8:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.nextLine(); // Consumir a entrada inválida
                opcao = -1; // Set opcao to a value that doesn't exit the loop
            }
        } while (opcao != 8);

        scanner.close();
    }
    

    private void registrarUsuario(Scanner scanner) {
        System.out.print("  Nome do Usuário: ");
        String nome = scanner.nextLine();
        System.out.print("  Email: ");
        String email = scanner.nextLine();
        Usuario usuario = new Usuario(nome, email);
        usuarios.add(usuario);
        System.out.println("\nUsuário registrado com sucesso!\n\n");
    }

    private void criarTarefa(Scanner scanner) {
        System.out.print("  Título da Tarefa: ");
        String titulo = scanner.nextLine();
        System.out.print("  Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("  Prioridade: ");
        int prioridade = scanner.nextInt();
        Tarefa tarefa = new Tarefa(titulo, descricao, prioridade);
        tarefas.add(tarefa);
        System.out.println("\nTarefa criada com sucesso!\n\n");
    }

    private void atribuirTarefa(Scanner scanner) {
        System.out.print("  Título da Tarefa: ");
        String titulo = scanner.nextLine();

        System.out.print("  Nome do Usuário: ");
        String nomeUsuario = scanner.nextLine();

        Tarefa tarefa = tarefas.stream().filter(t -> t.getTitulo().equals(titulo)).findFirst().orElse(null);
        Usuario usuario = usuarios.stream().filter(u -> u.getNome().equals(nomeUsuario)).findFirst().orElse(null);

        if (tarefa != null && usuario != null) {
            usuario.adicionarTarefa(tarefa);
            System.out.println("\nTarefa atribuída com sucesso!\n\n");

        } else {
            System.out.println("\nTarefa ou usuário não encontrado.\n\n");
        }
    }

    private void listarTarefas() {
        if (tarefas != null && !tarefas.isEmpty()) {
            System.out.println("    \n\nTodas as Tarefas: ");
            for (Tarefa tarefa : tarefas) {
                System.out.println("\n" + tarefa.getPrioridade() + " - " + tarefa.getTitulo() + " - " + tarefa.getDescricao());
            }
        } else {
            System.out.println("\nSem tarefas registradas.\n\n");
        }
    }
    

    private void listarTarefasUsuario(Scanner scanner) {
        System.out.print("  Nome do Usuário: ");
        String nomeUsuario = scanner.nextLine();
        Usuario usuario = usuarios.stream().filter(u -> u.getNome().equals(nomeUsuario)).findFirst().orElse(null);
        if (usuario != null) {
            System.out.println("\nTarefas de " + usuario.getNome() + ":");
            for (Tarefa tarefa : usuario.getTarefas()) {
                System.out.println("\n" + tarefa.getPrioridade() + " - " + tarefa.getTitulo() + " - " + tarefa.getDescricao());
            }
        } else {
            System.out.println("\nUsuário não encontrado.\n\n");
        }
    }

    private void deletarTarefa(Scanner scanner) {
        System.out.print("  Selecione o Usuário: ");
        String nomeUsuario = scanner.nextLine();
        System.out.print("  \nTítulo da Tarefa a ser deletada: ");
        String titulo = scanner.nextLine();

        Usuario usuario = usuarios.stream().filter(u -> u.getNome().equals(nomeUsuario)).findFirst().orElse(null);
        Tarefa tarefa = tarefas.stream().filter(t -> t.getTitulo().equals(titulo)).findFirst().orElse(null);

        if (tarefa != null) {
            tarefas.remove(tarefa);
            usuario.removerTarefa(tarefa);

            System.out.println("\nTarefa deletada com sucesso!\n");

        } else {
            System.out.println("\nTarefa não encontrada.\n");
        }
    }

    private void listarUsuariosETarefas() {
        if (usuarios != null && !usuarios.isEmpty()) {
            System.out.println("\nUsuários e suas Tarefas:\n");
            for (Usuario usuario : usuarios) {
                System.out.println("    Usuário: " + usuario.getNome());
                List<Tarefa> tarefasUsuario = usuario.getTarefas();
                if (tarefasUsuario != null && !tarefasUsuario.isEmpty()) {
                    System.out.println("Tarefas:");
                    for (Tarefa tarefa : tarefasUsuario) {
                        System.out.println("    " + tarefa.getPrioridade() + " - " + tarefa.getTitulo() + " - " + tarefa.getDescricao());
                    }
                } else {
                    System.out.println("Nenhuma tarefa atribuída.");
                }
                System.out.println(); // linha em branco entre usuários
            }
        } else {
            System.out.println("\nSem usuários registrados.\n");
        }
    }

    
}
