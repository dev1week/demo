package com.example.demo.TrainerPokemon;


import com.example.demo.pokemon.Pokemon;
import com.example.demo.pokemon.PokemonRepository;
import com.example.demo.trainer.Trainer;
import com.example.demo.trainer.TrainerRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TrainerPokemonService {
    private final TrainerPokemonRepository trainerPokemonRepository;
    private final TrainerRepository trainerRepository;
    private final PokemonRepository pokemonRepository;


    //@Transactional
    public void damage(int id, int damage) {

        TrainerPokemon pokemon = trainerPokemonRepository.findById(id).orElse(null);

        pokemon.decreaseCurrentHealth(damage);

        trainerPokemonRepository.saveAndFlush(pokemon);

    }

    @PostConstruct
    public void initData() {
        // Trainer와 Pokemon 데이터 저장
        Trainer trainer = trainerRepository.save(new Trainer(1, "Ash Ketchum", 10, "Pallet Town", "Electric", 8, "Champion"));
        Pokemon pokemon = pokemonRepository.save(new Pokemon(1, "피카츄", "Pikachu", "Electric", null, 320, 35, 55, 40, 50, 50, 90, 1, false));

        // TrainerPokemon 데이터를 1000개 생성하여 저장
        for (int i = 0; i < 1000; i++) {
            TrainerPokemon trainerPokemon = new TrainerPokemon(100, trainer, pokemon);
            trainerPokemonRepository.save(trainerPokemon);
        }
    }
}
