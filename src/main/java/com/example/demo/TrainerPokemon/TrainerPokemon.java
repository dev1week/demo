package com.example.demo.TrainerPokemon;

import com.example.demo.pokemon.Pokemon;
import com.example.demo.trainer.Trainer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "trainer_pokemon")
@Getter
@NoArgsConstructor
public class TrainerPokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id", nullable = false)
    private Trainer trainer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pokemon_id", nullable = false)
    private Pokemon pokemon;

    private Integer level;
    private Integer experiencePoint;
    private Integer currentHealth;

    @Temporal(TemporalType.DATE)
    private Date catchDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date catchDatetime;

    private String location;
    private String status;



    public void decreaseCurrentHealth(Integer damage){
        if(this.currentHealth < 0){
            throw new RuntimeException("체력은 음수가 될 수 없습니다.");
        }

        this.currentHealth -= damage;
    }

    public TrainerPokemon(int health, Trainer trainer, Pokemon pokemon) {


        this.trainer = trainer;
        this.pokemon = pokemon;
        this.level = 20;
        this.experiencePoint = 20;
        this.currentHealth = health;
        this.catchDate = new Date();
        this.catchDatetime = new Date();
        this.location = "seoul";
        this.status = "active";
    }
}