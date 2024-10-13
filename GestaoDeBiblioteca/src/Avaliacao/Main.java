package Avaliacao;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {


        Livro livro = new Livro();
        Biblioteca biblioteca = new Biblioteca();


        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n--- Sistema de Gestão de Biblioteca ---");
            System.out.println("1. Cadastrar livro");
            System.out.println("2. Cadastrar usuário");
            System.out.println("3. Emprestar livro");
            System.out.println("4. Devolver livro");
            System.out.println("5. Exibir livros disponíveis");
            System.out.println("6. Exibir usuarios");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();

            scanner.nextLine(); // Consumir a nova linha após o número

            switch (opcao) {
                case 1:
                    System.out.print("Título do livro: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Autor do livro: ");
                    String autor = scanner.nextLine();
                    System.out.print("ISBN do livro: ");
                    String isbn = scanner.nextLine();

                    if (biblioteca.isbnJaCadastrado(isbn)) {
                        System.out.println("Livro já cadastrado");
                    } else {
                        livro = new Livro(titulo, autor, isbn);
                        biblioteca.cadastrarLivro(livro);
                        biblioteca.escreverArquivoLivro();
                        System.out.println("Livro cadastrado com sucesso!");

                    }


                    break;

                case 2:
                    System.out.print("Nome do usuário: ");
                    String nome = scanner.nextLine();
                    System.out.print("Número de registro do usuário: ");
                    String numeroDeRegistro = scanner.nextLine();

                    if (biblioteca.usuarioJaCadastrado(numeroDeRegistro) || biblioteca.acharUser(numeroDeRegistro)) {
                        System.out.println("Usuario já cadastrado");
                    } else {
                        Usuario usuario = new Usuario(nome, numeroDeRegistro);
                        biblioteca.cadastrarUsuario(usuario);
                        biblioteca.escreverArquivoUser();

                        System.out.println("Usuário cadastrado com sucesso!");

                    }
                    break;

                case 3:
                    System.out.print("ISBN do livro para emprestar: ");
                    isbn = scanner.nextLine();
                    System.out.print("Número de registro do usuário: ");
                    numeroDeRegistro = scanner.nextLine();

                    if (biblioteca.acharUser(numeroDeRegistro) != true) {
                        System.out.println("Usuario inexistente");
                    } else if (biblioteca.disponibilidadeDoLivro(isbn) != true || biblioteca.acharLivro(isbn) != true) {
                        System.out.println("Livro ja foi emprestado ou não está cadastrado.");
                    } else {
                        biblioteca.emprestimoNovo(isbn, numeroDeRegistro);
                    }

                    break;

                case 4:
                    System.out.print("ISBN do livro para devolver: ");
                    isbn = scanner.nextLine();
                    System.out.print("Número de registro do usuário: ");
                    numeroDeRegistro = scanner.nextLine();


                    if (biblioteca.acharLivroEmprestado(isbn) != true || biblioteca.acharUserEmprestados(numeroDeRegistro) != true) {
                        System.out.println("Não foi possível devolver o livro. Verifique se o livro está emprestado.");
                    }
                    else if (biblioteca.disponibilidadeDoLivro(isbn) != true) {
                        biblioteca.devolucaoNova(isbn, numeroDeRegistro);
                        System.out.println("Livro devolvido com sucesso!");

                    }
                    break;

                case 5:
                    if (biblioteca.livrosDisponiveis() == false) {
                        System.out.println("Nenhum livro disponivel");
                    }
                    break;

                case 6:
                    biblioteca.ExibirUsers();
                    break;


                case 7:
                    continuar = false;


                    System.out.println("Saindo do sistema. Até logo!");

                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }

        scanner.close();
    }
}
