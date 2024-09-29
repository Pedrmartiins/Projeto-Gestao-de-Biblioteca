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
                    String numeroRegistro = scanner.nextLine();

                    if (biblioteca.usuarioJaCadastrado(numeroRegistro)) {
                        System.out.println("Usuario já cadastrado");
                    } else {
                        Usuario usuario = new Usuario(nome, numeroRegistro);
                        biblioteca.cadastrarUsuario(usuario);
                        System.out.println("Usuário cadastrado com sucesso!");

                    }
                    break;

                case 3:
                    System.out.print("ISBN do livro para emprestar: ");
                    isbn = scanner.nextLine();
                    System.out.print("Número de registro do usuário: ");
                    String numeroDeRegistro = scanner.nextLine();

//                    if (biblioteca.disponibilidadeDoLivro(isbn)) {
                    biblioteca.disponibilidadeDoLivro(isbn);
                    biblioteca.emprestimoNovo(isbn, numeroDeRegistro);

                    biblioteca.disponibilidadeDoLivro(isbn);

//                        System.out.println("Livro emprestado com sucesso");
//                    } else {
//                        System.out.println("nao foi possivel emprestar ");
//                    }
                    break;

                case 4:
                    System.out.print("ISBN do livro para devolver: ");
                    String isbnDevolucao = scanner.nextLine();
                    System.out.print("Número de registro do usuário: ");
                    String numeroRegistroDevolucao = scanner.nextLine();
                    boolean devolucao = biblioteca.devolverLivro(isbnDevolucao, numeroRegistroDevolucao);
                    if (devolucao) {
                        System.out.println("Livro devolvido com sucesso!");
                    } else {
                        System.out.println(
                                "Não foi possível devolver o livro. Verifique se o livro está emprestado e se o usuário está registrado.");
                    }
                    break;

                case 5:

                    if (biblioteca.exibirLivros().isEmpty()) {
                        System.out.println("Não há livros disponiveis!");

                    } else {
                        System.out.println("Livros disponíveis:");
                        for (Livro l1 : biblioteca.exibirLivros())
                            System.out.println(
                                    "Titulo: " + l1.getTitulo() + " Autor: " + l1.getAutor() + " Isbn: " + l1.getIsbn());

                    }

                    break;

                case 6:
                    isbn = scanner.nextLine();
                    //  biblioteca.acharLivro(isbn);
                    biblioteca.disponibilidadeDoLivro(isbn);

                    numeroDeRegistro = scanner.nextLine();
                    biblioteca.acharUser(numeroDeRegistro);
                    // if (biblioteca.acharLivro(isbn)){
                    //      System.out.println("Achei o livro "+ biblioteca.acharLivro(isbn));

                    // }else {
                    //      System.out.println("Nao achei");
                    //   }
                    // biblioteca.ExibirUsers();
                    break;


                case 7:
                    continuar = false;
                    biblioteca.escreverArquivoUser();

                    System.out.println("Saindo do sistema. Até logo!");

                    break;

                case 8:

                    biblioteca.lerLivros();

                    break;


                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }

        scanner.close();
    }
}
