package ScreenMusic.main;

import ScreenMusic.models.*;
import ScreenMusic.repository.ScreenMusicRepository;
import ScreenMusic.service.ConsultaChatGPT;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Principal {

    private final Scanner leitura = new Scanner(System.in);
    private final ScreenMusicRepository repositorio;
    private Optional<Artista> artistaBusca;
    DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final String mostrarGeneros = """
                    Gêneros disponíveis:
                    Pop
                    Hip Hop
                    Rock
                    Eletronica
                    Bossa nova
                    Rap
                    Instrumental
                    """;
    public Principal(ScreenMusicRepository repositorio){
    this.repositorio = repositorio;
}

    public void exibeMenu() {
        int opcao = -1;
        while (opcao != 0) {

            String menu = """
                    1 - cadastrar artista
                    2 - cadastrar músicas de um artista
                    3 - Listar artistas
                    4 - listar músicas
                    5 - Buscar músicas de um artista
                    6 - Buscar músicas por gênero
                    7 - Buscar músicas por ano
                    8 - Buscar músicas por gênero e ano
                    9 - Pesquisar dados sobre um artista
                    0 - Encerrar a sessão
                    """;
            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();


            switch (opcao) {
                case 1:
                    cadastrarArtista();
                    break;
                case 2:
                    cadastrarMusicaArtista();
                    break;
                case 3:
                    listarArtistas();
                    break;
                case 4:
                    listarMusicas();
                    break;
                case 5:
                    buscarMusicaPorArtista();
                    break;
                case 6:
                    buscarMusicaPorGenero();
                    break;
                case 7:
                    buscarMusicaPorAno();
                    break;
                case 8:
                    buscarMusicaPorGeneroEAno();
                    break;
                case 9:
                    pesquisarSobreOArtista();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");

            }



        }
    }

    /**
     * Método para cadastrar um artista novo no banco de dados
     */
    private void cadastrarArtista() {
        String cadastrarNovo = "S";
        while (cadastrarNovo.equalsIgnoreCase("s")) {
            System.out.println("Diga o artista que deseja cadastrar");
            String nomeArtista = leitura.nextLine();
            System.out.println("Que tipo de artista ele é? (solo, dupla ou banda)");
            String tipo = leitura.nextLine();
            TipoArtistaEnum tipoArtista = TipoArtistaEnum.valueOf(tipo.toUpperCase());
            Artista artista = new Artista(nomeArtista, tipoArtista);
            repositorio.save(artista);
            System.out.println("Cadastrar outro artista? S/N");
            cadastrarNovo = leitura.nextLine();
        }
    }

    /**
     * Método para adicionar uma música nova a partir de um
     * artista já existente no banco de dados
     */

private void cadastrarMusicaArtista() {
    buscarMusicaPorArtista();
    if (artistaBusca.isPresent()) {
        System.out.println("Qual o nome da música?");
        String nomeMusica = leitura.nextLine();
        try {
            System.out.println("Data de lançamento? (DD/MM/AAAA)");
            String dataLancamento = leitura.nextLine();
            LocalDate data = LocalDate.parse(dataLancamento, formatador);
            System.out.println(mostrarGeneros);
            String generoInput = leitura.nextLine();
            GeneroEnum genero = GeneroEnum.fromString(generoInput);
            Musica musica = new Musica(nomeMusica);
            musica.setArtista(artistaBusca.get());
            musica.setDataLancamento(data);
            musica.setGenero(genero);
            artistaBusca.get().getListaMusicas().add(musica);
            repositorio.save(artistaBusca.get());
        } catch (DateTimeParseException e) {
            System.out.println("Erro ao converter a data, verifique se adicionou / entre os números.");
        }
    }
}

    /**
     * Método para listar todos os artistas existentes atualmente no banco de dados
     */

private void listarArtistas() {
    List<Artista> artistas = repositorio.findAll();
    artistas.forEach(System.out::println);
}

    /**
     * Método para listar todas as músicas existentes atualmente no banco de dados sem
     * listar o artista do qual pertencem.
     */
private void listarMusicas() {
    List <Musica> musicas = repositorio.listarMusicas();
    musicas.forEach(System.out::println);
}

    /**
     * Método para mostrar músicas que pertençam a um artista já existente no banco de dados.
     */

private void buscarMusicaPorArtista() {
    System.out.println("Digite o nome do artista que deseja pesquisar");
    String buscaArtista = leitura.nextLine();
    artistaBusca = repositorio.findByNomeContainingIgnoreCase(buscaArtista);
    if (artistaBusca.isPresent()) {
        System.out.println(artistaBusca);
    } else {
        System.out.println("Artista não encontrado.");
    }
}

    /**
     * Método para mostrar músicas filtradas por gênero
     */

private void buscarMusicaPorGenero() {
    System.out.println(mostrarGeneros);
    String nomeGenero = leitura.nextLine();
    GeneroEnum genero = GeneroEnum.fromString(nomeGenero);
    List<Musica> musicasPorGenero = repositorio.encontrarPorGenero(genero);
    System.out.println("Buscando músicas...");
    musicasPorGenero.forEach(System.out::println);
}

    /**
     * Método para mostrar músicas por um filtro de data usando a formatação dia/mês/AnoCompleto
     */

private void buscarMusicaPorAno() {
    System.out.println("A partir de qual data deseja buscar? (01/01/0000)");
    String anoBusca = leitura.nextLine();
    LocalDate data = LocalDate.parse(anoBusca, formatador);
    List<Musica> musicasPorAno = repositorio.encontrarPorAno(data);
    System.out.println("Buscando músicas...");
    musicasPorAno.forEach(System.out::println);
}

    /**
     * Método para mostar músicas filtradas por um gênero e então a partir de uma data
     */

private void buscarMusicaPorGeneroEAno() {
    System.out.println(mostrarGeneros);
    String nomeGenero = leitura.nextLine();
    GeneroEnum genero = GeneroEnum.fromString(nomeGenero);
    System.out.println("A partir de qual data deseja buscar? (01/01/0000)");
    String anoBusca = leitura.nextLine();
    LocalDate data = LocalDate.parse(anoBusca, formatador);
    List<Musica> musicasPorGeneroEAno = repositorio.encontrarPorGeneroEAno(genero, data);
    musicasPorGeneroEAno.forEach(System.out::println);

    }

    /**
     * Método para pesquisar sobre um artista usando o chat GPT
     */

    private void pesquisarSobreOArtista() {
        System.out.println("Sobre qual artista deseja pesquisar?");
        String nomeArtista = leitura.nextLine();
        String resposta = ConsultaChatGPT.buscarArtista(nomeArtista);
        System.out.println(resposta.trim());
    }

}
