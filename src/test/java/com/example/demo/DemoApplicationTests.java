package com.example.demo;

import com.example.demo.TrainerPokemon.TrainerPokemon;
import com.example.demo.TrainerPokemon.TrainerPokemonRepository;
import com.example.demo.TrainerPokemon.TrainerPokemonService;
import com.example.demo.pokemon.Pokemon;
import com.example.demo.pokemon.PokemonRepository;
import com.example.demo.trainer.Trainer;
import com.example.demo.trainer.TrainerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootTest
@ActiveProfiles("test")

class DemoApplicationTests {
    private static final Logger log = LoggerFactory.getLogger(DemoApplicationTests.class);

    @Autowired
    private TrainerPokemonService trainerPokemonService;


    @Autowired
    private TrainerPokemonRepository trainerPokemonRepository;

    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    private long startTime;

    @BeforeEach
    public void setUp() {
        startTime = System.currentTimeMillis();
    }


    @BeforeEach
    public void before(){
        Trainer trainer = new Trainer(1, "Ash Ketchum",      // name
                10,                 // age
                "Pallet Town",      // hometown
                "Electric",         // preferredPokemonType
                8,                  // badgeCount
                "Champion" );        // achievementLevel)

        Pokemon pokemon = new Pokemon(1,
                "피카츄",        // korName
                "Pikachu",       // engName
                "Electric",      // type1
                null,            // type2 (없을 경우 null)
                320,             // total
                35,              // hp
                55,              // attack
                40,              // defense
                50,              // specialAttack
                50,              // specialDefense
                90,              // speed
                1,               // generation
                false            // isLegendary
        );

        pokemon = pokemonRepository.saveAndFlush(pokemon);
        trainer = trainerRepository.saveAndFlush(trainer);

        for(int i=0; i<1000; i++){
            trainerPokemonRepository.saveAndFlush(new TrainerPokemon(100,trainer, pokemon));
        }


    }

    @AfterEach
    public void after(){

        trainerPokemonRepository.deleteAll();

    }



    @Test
    public void 체력감소(){
        trainerPokemonService.damage(1, 1);


        TrainerPokemon trainerPokemon = trainerPokemonRepository.findById(1).get();

        assertEquals(99, trainerPokemon.getCurrentHealth());

    }


    @Test
    public void 동시에_100명이_공격() throws Exception{

        int threadCount = 100;
        ExecutorService es = Executors.newFixedThreadPool(32);
        CountDownLatch latch= new CountDownLatch(threadCount);

        for(int i=0; i<threadCount; i++){
            es.submit(()->{
                try{
                    trainerPokemonService.damage(1, 1);
                }finally{
                    latch.countDown();
                }
            });

        }

        latch.await();

        TrainerPokemon trainerPokemon = trainerPokemonRepository.findById(1).get();

        assertEquals(0, trainerPokemon.getCurrentHealth());
    }

    public void logging(){
        log.info("삭제 종료");
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        log.info("Execution Time: {} ms", executionTime);
    }

    @Test
    public void delete_쿼리_메서드_사용시_문제(){
        log.info("삭제 시작");
        trainerRepository.deleteById(1);
        logging();

    }

    @Test
    public void delete_어노테이션_사용(){
        log.info("삭제 시작");
        trainerRepository.deleteTrainerByIdWithAnnotation(1);
        logging();

    }

}
