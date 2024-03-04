package ScreenMusic.repository;

import ScreenMusic.models.Artista;
import ScreenMusic.models.GeneroEnum;
import ScreenMusic.models.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ScreenMusicRepository extends JpaRepository<Artista, Long> {
    Optional<Artista> findByNomeContainingIgnoreCase(String nomeArtista);

    @Query("SELECT s FROM Artista a JOIN a.listaMusicas s")
    List<Musica> listarMusicas();

    @Query("SELECT s FROM Artista a JOIN a.listaMusicas s WHERE s.genero = :genero")
    List<Musica> encontrarPorGenero(GeneroEnum genero);
    @Query("SELECT s FROM Artista a JOIN a.listaMusicas s WHERE  s.dataLancamento >= :data")

    List<Musica> encontrarPorAno(LocalDate data);
    @Query("SELECT s FROM Artista a JOIN a.listaMusicas s WHERE s.genero = :genero AND s.dataLancamento >= :data")
    List<Musica> encontrarPorGeneroEAno(GeneroEnum genero, LocalDate data);

}
