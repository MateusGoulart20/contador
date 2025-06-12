package exercicios.contador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import exercicios.contador.models.Musica;

public interface MusicaRepository 
extends JpaRepository<Musica, Long>{}