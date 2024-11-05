package com.example.demo.trainer;


import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer> {

    Optional<Trainer> findById(Integer id);

    @Lock(LockModeType.PESSIMISTIC_READ) // 비관적 읽기 락
    @Query("SELECT e FROM Trainer e WHERE e.id = :id")
    Optional<Trainer> findByIdWithLock(@Param("id") Integer id);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM trainer_pokemon WHERE trainer_id = :trainerId; DELETE FROM trainer WHERE id = :trainerId", nativeQuery = true)
    void deleteTrainerByIdWithAnnotation(Integer trainerId);

}
