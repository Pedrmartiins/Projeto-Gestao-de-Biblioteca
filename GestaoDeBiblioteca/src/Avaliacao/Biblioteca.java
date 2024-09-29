package Avaliacao;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Biblioteca {
    private ArrayList<Livro> livros;
    private ArrayList<Usuario> usuario;



    String path2 = "C:\\Users\\Aluno\\Desktop\\Cvs\\Livros.csv";



    BufferedReader livroReader = null;

    FileReader livroFile = null;



    public void lerLivros() {



        try (BufferedReader livroReader = new BufferedReader(new FileReader(path2))) {

            String linha = livroReader.readLine();



            while (linha != null) {

                System.out.println(linha);

                linha = livroReader.readLine();



            }



        } catch (IOException e) {

            System.out.println("Erro: " + e.getMessage());

        }



        // Retorna a lista de livros

    }



    public void escreverArquivoLivro(Livro livro)  {





        try (FileWriter fileWriter = new FileWriter(path2, true); // 'true' para adicionar ao final

             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {



            // Formate os dados do livro como uma linha CSV

            String linha = String.format("%s,%s,%s%n", livro.getTitulo(), livro.getAutor(), livro.getIsbn());

            bufferedWriter.write(linha);

            System.out.println("Livro adicionado ao arquivo com sucesso!");

        } catch (IOException e) {

            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());

        }



    }








    public void escreverArquivo() {



        String path = "/Users/macbookpro/Documents/ArquivoCSV/Usuarios.csv";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            if (Files.size(Paths.get(path)) == 0) {
                bw.write("nome,numero de registro");
                bw.newLine();}
            for (Usuario u : this.usuario) {
                System.out.println(u);
                bw.write( u.getNome() + "," + u.getNumeroRegistro() );
                bw.newLine();
            }


        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    public void ExibirUsers() {

        String path = "/Users/macbookpro/Desktop/ArquivoCSV/Usuarios.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linha = br.readLine();
            while(linha != null) {
                System.out.println(linha);
                linha = br.readLine();
            }



        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }




    public Biblioteca() {
        livros = new ArrayList<>();
        usuario = new ArrayList<>();
    }

    // Métodos de gerenciamento
    public void cadastrarLivro(Livro livro) {
        livros.add(livro);
    }

    public void cadastrarUsuario(Usuario usuario) {
        this.usuario.add(usuario);
    }

    public boolean emprestarLivro(String isbn, String numeroRegistro) {
        Livro livro = buscarLivroPorIsbn(isbn);
        Usuario usuario = buscarUsuarioPorNumeroRegistro(numeroRegistro);

        if (livro != null && usuario != null && livro.isDisponibilidade()) {
            livro.setDisponibilidade(false);
            usuario.adicionarLivroEmprestado(livro);
            return true;
        }
        return false;
    }

    public boolean devolverLivro(String isbn, String numeroRegistro) {
        Livro livro = buscarLivroPorIsbn(isbn);
        Usuario usuario = buscarUsuarioPorNumeroRegistro(numeroRegistro);

        if (livro != null && usuario != null && !livro.isDisponibilidade()) {
            livro.setDisponibilidade(true);
            usuario.removerLivroEmprestado(livro);
            return true;
        }
        return false;
    }

    public ArrayList<Livro> exibirLivros() {
        ArrayList<Livro> exibirLivro = new ArrayList<>();
        for (Livro l : this.livros) {
            if (l.isDisponibilidade() == true) {
                exibirLivro.add(l);
            }

        }
        return exibirLivro;

    }

    // Métodos auxiliares

    private Livro buscarLivroPorIsbn(String isbn) {
        for (Livro livro : livros) {
            if (livro.getIsbn().equals(isbn)) {
                return livro;
            }
        }
        return null;
    }

    private Usuario buscarUsuarioPorNumeroRegistro(String numeroRegistro) {
        for (Usuario usuario : usuario) {
            if (usuario.getNumeroRegistro().equals(numeroRegistro)) {
                return usuario;
            }
        }
        return null;
    }

    public boolean isbnJaCadastrado(String isbn) {
        for (Livro livro : livros) {
            if (livro.verificaIsbn(isbn)) {
                return true;
            }
        }
        return false;
    }

    public boolean usuarioJaCadastrado(String numeroRegistro) {
        for (Usuario usuario : usuario) {
            if (usuario.verificaUsuario(numeroRegistro)) {
                return true;
            }
        }
        return false;
    }

    public void retornaUsers() {
        for (Usuario u : usuario) {
            System.out.println(u);
        }



    }
}
