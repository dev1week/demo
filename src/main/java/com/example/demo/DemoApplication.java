package com.example.demo;

import com.example.demo.TrainerPokemon.TrainerPokemon;
import com.example.demo.pokemon.Pokemon;
import com.example.demo.trainer.Trainer;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
