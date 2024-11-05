package com.example.demo.TrainerPokemon;


import com.example.demo.trainer.TrainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TrainerPokemonService {
    private final TrainerPokemonRepository trainerPokemonRepository;


    //@Transactional
    public void damage(int id, int damage) {

        TrainerPokemon pokemon = trainerPokemonRepository.findById(id).orElse(null);

        pokemon.decreaseCurrentHealth(damage);

        trainerPokemonRepository.saveAndFlush(pokemon);

    }


}
