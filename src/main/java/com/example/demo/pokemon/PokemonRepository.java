package com.example.demo.pokemon;

import com.example.demo.TrainerPokemon.TrainerPokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository  extends JpaRepository<Pokemon, Integer> {



}
