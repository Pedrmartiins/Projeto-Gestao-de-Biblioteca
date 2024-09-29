package Avaliacao;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Biblioteca {
    private ArrayList<Livro> livros;
    private ArrayList<Usuario> usuario;


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

    public boolean emprestarLivro(String isbn, String numeroDeRegistro) {
        Livro livro = buscarLivroPorIsbn(isbn);
        Usuario usuario = buscarUsuarioPorNumeroRegistro(numeroDeRegistro);

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

    public Livro buscarLivroPorIsbn(String isbn) {
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


    public void lerLivros() {
        String path = "/Users/macbookpro/Documents/Projeto-Gestao-de-Biblioteca/ArquivoCSV/Livros.csv";


        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linha = br.readLine();
            while (linha != null) {
                System.out.println(linha);
                linha = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        // Retorna a lista de livros
    }


    public void escreverArquivoLivro() {

        String path = "/Users/macbookpro/Documents/Projeto-Gestao-de-Biblioteca/ArquivoCSV/Livros.csv";

        try (FileWriter fw = new FileWriter(path, true); // 'true' para adicionar ao final

             BufferedWriter bw = new BufferedWriter(fw)) {


            for (Livro l : livros) {
                System.out.println(l);
                bw.write(l.getTitulo() + "," + l.getAutor() + "," + l.getIsbn() + "," + l.isDisponibilidade());
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }


    public void escreverArquivoUser() {


        String path = "/Users/macbookpro/Documents/Projeto-Gestao-de-Biblioteca/ArquivoCSV/Usuarios.csv";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            if (Files.size(Paths.get(path)) == 0) {
                bw.write("nome,numero de registro");
                bw.newLine();
            }
            for (Usuario u : this.usuario) {
                System.out.println(u);
                bw.write(u.getNome() + "," + u.getNumeroRegistro());
                bw.newLine();
            }


        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void ExibirUsers() {

        String path = "/Users/macbookpro/Documents/Projeto-Gestao-de-Biblioteca/ArquivoCSV/Usuarios.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linha = br.readLine();
            while (linha != null) {
                System.out.println(linha);
                linha = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public Livro acharLivro(String isbn) {
        String pathLivros = "/Users/macbookpro/Documents/Projeto-Gestao-de-Biblioteca/ArquivoCSV/Livros.csv";


        try (BufferedReader br = new BufferedReader(new FileReader(pathLivros))) {
            String linha = br.readLine();
            while (linha != null) {

                String valor[] = linha.split(",");
                if (valor.length > 0 && valor[2].equals(isbn)) {
                    System.out.println("Achei o livro " + linha);
                    return new Livro(valor[0], valor[1], valor[2]);

                }
                linha = br.readLine();
            }


        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());

        }
        // Retorna a lista de livros
        return null;

    }

    public boolean disponibilidadeDoLivro(String isbn) {
        String pathLivros = "/Users/macbookpro/Documents/Projeto-Gestao-de-Biblioteca/ArquivoCSV/Livros.csv";


        try (BufferedReader br = new BufferedReader(new FileReader(pathLivros))) {
            String linha = br.readLine();
            while (linha != null) {

                String valor[] = linha.split(",");
                if (valor.length >= 4 && valor[2].equals(isbn)) {
                    if (valor[3].equals("true")) {
                        System.out.println("Disponivel");
                        return true;
                    } else {
                        return false;
                    }
                }
                linha = br.readLine();
            }
            return false;

        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        }

    }

    public Usuario acharUser(String numeroDeRegistro) {
        String path = "/Users/macbookpro/Documents/Projeto-Gestao-de-Biblioteca/ArquivoCSV/Usuarios.csv";


        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linha = br.readLine();
            while (linha != null) {

                String valor[] = linha.split(",");
                if (valor.length > 0 && valor[1].equals(numeroDeRegistro)) {
                    System.out.println("Achei o usuario " + linha);
                    return new Usuario(valor[0], valor[1]);

                }
                linha = br.readLine();
            }


        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());

        }
        // Retorna a lista de livros


        return null;

    }

    public void emprestimoNovo(String isbn, String numeroDeRegistro) throws IOException {
        String path1 = "/Users/macbookpro/Documents/Projeto-Gestao-de-Biblioteca/ArquivoCSV/LivrosEmprestados.csv";
        String pathLivros = "/Users/macbookpro/Documents/Projeto-Gestao-de-Biblioteca/ArquivoCSV/Livros.csv";

        if (acharUser(numeroDeRegistro) != null && acharLivro(isbn) != null && disponibilidadeDoLivro(isbn)
        ) {

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(path1, true))) {

                if (Files.size(Paths.get(path1)) == 0) {
                    bw.write("Numero de Registro,ISBN ");
                    bw.newLine();
                }

                bw.write(numeroDeRegistro + "," + isbn);
                bw.newLine();
                System.out.println("Livro emprestado com sucesso");
                atualizarDisponibilidade(isbn, false, pathLivros);


            } catch (IOException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private void atualizarDisponibilidade(String isbn, boolean disponibilidade, String pathLivros) throws FileNotFoundException {

        ArrayList<String> salvar = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(pathLivros))) {
            String linha = br.readLine();
            while (linha != null) {
                String valor[] = linha.split(",");

                if (valor.length > 0 && valor[2].equals(isbn)) {
                    valor[3] = String.valueOf(disponibilidade);
                    linha = String.join(",", valor);
                }
                salvar.add(linha);
                linha = br.readLine();
                System.out.println(salvar);
            }
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathLivros))) {
            for (String s : salvar) {
                bw.write(s);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());

        }
    }
}



