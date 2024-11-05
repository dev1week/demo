package com.example.demo.trainer;


import com.example.demo.TrainerPokemon.TrainerPokemon;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="trainer")
@Getter
@Setter
@NoArgsConstructor
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Integer age;
    private String hometown;
    private String preferredPokemonType;
    private Integer badgeCount;
    private String achievementLevel;

    // Trainer와 TrainerPokemon의 관계 설정
    @OneToMany(mappedBy = "trainer", cascade = CascadeType.REMOVE,  fetch = FetchType.LAZY)
    private List<TrainerPokemon> trainerPokemons = new ArrayList<>();


    private int test;

    public TrainerDto toDto() {
        return new TrainerDto(
                this.id,
                this.name,
                this.hometown,
                this.badgeCount
        );
    }

    public Trainer(int id, String name, Integer age, String hometown, String preferredPokemonType, Integer badgeCount, String achievementLevel) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.hometown = hometown;
        this.preferredPokemonType = preferredPokemonType;
        this.badgeCount = badgeCount;
        this.achievementLevel = achievementLevel;
    }
}
