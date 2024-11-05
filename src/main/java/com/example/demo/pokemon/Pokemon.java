package com.example.demo.pokemon;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pokemon")
@NoArgsConstructor
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "kor_name")
    private String korName;

    @Column(name = "eng_name")
    private String engName;

    private String type1;
    private String type2;
    private Integer total;
    private Integer hp;
    private Integer attack;
    private Integer defense;

    @Column(name = "special_attack")
    private Integer specialAttack;

    @Column(name = "special_defense")
    private Integer specialDefense;

    private Integer speed;
    private Integer generation;

    @Column(name = "is_legendary")
    private Boolean isLegendary;

    public Pokemon(int id, String korName, String engName, String type1, String type2, Integer total, Integer hp, Integer attack, Integer defense, Integer specialAttack, Integer specialDefense, Integer speed, Integer generation, Boolean isLegendary) {
        this.id = id;
        this.korName = korName;
        this.engName = engName;
        this.type1 = type1;
        this.type2 = type2;
        this.total = total;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;
        this.generation = generation;
        this.isLegendary = isLegendary;
    }
}