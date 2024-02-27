package ScreenMusic.principal;

import ScreenMusic.models.*;
import ScreenMusic.repository.ScreenMusicRepository;
import ScreenMusic.service.ConsultaChatGPT;

import java.util.*;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsultaChatGPT servico = new ConsultaChatGPT();
    private ScreenMusicRepository repositorio;

    private List<Artista> artistas = new ArrayList<>();
    private Optional<Artista> artistaBusca;

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
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");

            }



        }
    }
private void cadastrarArtista() {
        String cadastrarNovo = "S";
        while (cadastrarNovo.equalsIgnoreCase("s")) {
            System.out.println("Diga o artista que deseja pesquisar");
            String nomeArtista = leitura.nextLine();
            System.out.println("Que tipo de artista ele é? (solo, dupla ou banda)");
            String tipo = leitura.nextLine();
            TipoArtista tipoArtista = TipoArtista.valueOf(tipo.toUpperCase());
            Artista artista = new Artista(nomeArtista, tipoArtista);
            repositorio.save(artista);
            System.out.println("Cadastrar outro artista? S/N");
            cadastrarNovo = leitura.nextLine();
        }
    }

private void cadastrarMusicaArtista() {
    buscarMusicaPorArtista();
    if (artistaBusca.isPresent()) {
        System.out.println("Qual o nome da música?");
        String nomeMusica = leitura.nextLine();
        Musica musica = new Musica(nomeMusica);
        musica.setArtista(artistaBusca.get());
        artistaBusca.get().getListaMusicas().add(musica);
        repositorio.save(artistaBusca.get());
    }
}

private void listarArtistas() {
    artistas = repositorio.findAll();
    artistas.forEach(System.out::println);
}

private void listarMusicas() {
}

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

private void buscarMusicaPorGenero() {
//    System.out.println("""
//            Genêros disponíveis:
//            Pop;
//            Hip hop;
//            Rock;
//            Bossa nova;
//            Eletronica;
//            Rap
//            """);
//    String nomeGenero = leitura.nextLine();
//    Genero genero = Genero.fromString(nomeGenero);
//    Optional<Musica> musicas = repositorio.findByTipo(genero);
//    musicas.stream()
//            .forEach(System.out::println);

}

private void buscarMusicaPorAno() {

}

private void buscarMusicaPorGeneroEAno() {

}

}