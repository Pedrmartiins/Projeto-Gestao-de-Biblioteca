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


    public void cadastrarLivro(Livro livro) {
        livros.add(livro);
    }

    public void cadastrarUsuario(Usuario usuario) {
        this.usuario.add(usuario);
    }


    public boolean isbnJaCadastrado(String isbn) {
        for (Livro livro : livros) {
            if (livro.verificaIsbn(isbn)) {
                return true;
            }
        }
        return false;
    }

    public boolean usuarioJaCadastrado(String numeroDeRegistro) {
        for (Usuario usuario : usuario) {
            if (usuario.verificaUsuario(numeroDeRegistro)) {
                return true;
            }
        }
        return false;
    }




    public void lerLivros() {
        String path = "/Users/macbookpro/Documents/Projeto-Gestao-de-Biblioteca/ArquivoCSV/Livros.csv";


        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linha = br.readLine();
            while (linha != null) {

                linha = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }

    }


    public void escreverArquivoLivro() {

        String path = "/Users/macbookpro/Documents/Projeto-Gestao-de-Biblioteca/ArquivoCSV/Livros.csv";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            if (Files.size(Paths.get(path)) == 0) {
                bw.write("Titulo,Autor,Isbn,Disponibilidade");
                bw.newLine();
            }

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

    public boolean acharLivro(String isbn) {
        String pathLivros = "/Users/macbookpro/Documents/Projeto-Gestao-de-Biblioteca/ArquivoCSV/Livros.csv";


        try (BufferedReader br = new BufferedReader(new FileReader(pathLivros))) {
            String linha = br.readLine();
            while (linha != null) {

                String valor[] = linha.split(",");
                if (valor.length > 0 && valor[2].equals(isbn)) {

                    return true;

                }
                linha = br.readLine();
            }


        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());

        }

        return false;

    }

    public boolean disponibilidadeDoLivro(String isbn) {
        String pathLivros = "/Users/macbookpro/Documents/Projeto-Gestao-de-Biblioteca/ArquivoCSV/Livros.csv";


        try (BufferedReader br = new BufferedReader(new FileReader(pathLivros))) {
            String linha = br.readLine();
            while (linha != null) {

                String valor[] = linha.split(",");
                if (valor.length >= 4 && valor[2].equals(isbn)) {
                    if (valor[3].equals("true")) {

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

    public boolean acharUser(String numeroDeRegistro) {
        String path = "/Users/macbookpro/Documents/Projeto-Gestao-de-Biblioteca/ArquivoCSV/Usuarios.csv";


        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linha = br.readLine();
            while (linha != null) {

                String valor[] = linha.split(",");
                if (valor.length > 0 && valor[1].equals(numeroDeRegistro)) {
                    return true;

                }
                linha = br.readLine();

            }


        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());

        }

        return false;
    }

    public void emprestimoNovo(String isbn, String numeroDeRegistro) throws IOException {
        String path1 = "/Users/macbookpro/Documents/Projeto-Gestao-de-Biblioteca/ArquivoCSV/LivrosEmprestados.csv";
        String pathLivros = "/Users/macbookpro/Documents/Projeto-Gestao-de-Biblioteca/ArquivoCSV/Livros.csv";

        if (acharUser(numeroDeRegistro)  && acharLivro(isbn)  && disponibilidadeDoLivro(isbn)
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


    public void devolucaoNova(String isbn, String numeroDeRegistro) throws IOException {
        String path1 = "/Users/macbookpro/Documents/Projeto-Gestao-de-Biblioteca/ArquivoCSV/LivrosEmprestados.csv";
        String pathLivros = "/Users/macbookpro/Documents/Projeto-Gestao-de-Biblioteca/ArquivoCSV/Livros.csv";

        if (acharUser(numeroDeRegistro) == true||false && acharLivro(isbn)  && disponibilidadeDoLivro(isbn) != true
        ) {

            ArrayList<String> salvar = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(path1))) {
                String linha = br.readLine();
                while (linha != null) {
                    String valor[] = linha.split(",");

                    if (valor.length > 1 && !(valor[0].equals(numeroDeRegistro) && valor[1].equals(isbn))) {
                        salvar.add(linha);
                    }
                    linha = br.readLine();
                }
            } catch (IOException e) {
                System.out.println("Erro: " + e.getMessage());
            }

            try(BufferedWriter bw = new BufferedWriter(new FileWriter(path1))){

                for (String s : salvar) {
                    bw.write(s);
                    bw.newLine();
                }
            } catch (IOException e) {
                System.out.println("Erro: " + e.getMessage());

            }
            atualizarDisponibilidade(isbn, true, pathLivros);
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

    public boolean livrosDisponiveis() throws IOException {
        String pathLivros = "/Users/macbookpro/Documents/Projeto-Gestao-de-Biblioteca/ArquivoCSV/Livros.csv";


        try (BufferedReader br = new BufferedReader(new FileReader(pathLivros))) {
            String linha = br.readLine();
            while (linha != null) {

                String valor[] = linha.split(",");
                if (valor.length >= 4 && valor[3].equals("true")) {
                    System.out.println(linha);
return true;
                }

                linha = br.readLine();
                }




        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());

        }
        return false;

    }
    public boolean acharUserEmprestados(String numeroDeRegistro) {
        String path = "/Users/macbookpro/Documents/Projeto-Gestao-de-Biblioteca/ArquivoCSV/LivrosEmprestados.csv";


        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linha = br.readLine();
            while (linha != null) {

                String valor[] = linha.split(",");
                if (valor.length > 0 && valor[0].equals(numeroDeRegistro)) {
                    return true;

                }
                linha = br.readLine();

            }


        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());

        }

        return false;
    }

    public boolean acharLivroEmprestado(String isbn) {
        String pathLivros = "/Users/macbookpro/Documents/Projeto-Gestao-de-Biblioteca/ArquivoCSV/LivrosEmprestados.csv";


        try (BufferedReader br = new BufferedReader(new FileReader(pathLivros))) {
            String linha = br.readLine();
            while (linha != null) {

                String valor[] = linha.split(",");
                if (valor.length > 0 && valor[1].equals(isbn)) {

                    return true;

                }
                linha = br.readLine();
            }


        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());

        }

        return false;

    }

}



