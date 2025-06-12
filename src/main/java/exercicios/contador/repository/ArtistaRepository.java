package exercicios.contador.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import exercicios.contador.models.Artista;

public interface ArtistaRepository 
extends JpaRepository<Artista, Long>{

    Optional<Artista> findByNomeContainingIgnoreCase(String nomeArtista);}