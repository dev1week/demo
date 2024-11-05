package com.example.demo.trainer;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Builder
public class TrainerService {
    private final TrainerRepository trainerRepository;

    public TrainerDto getTrainer(Integer id) {
        return trainerRepository.findById(id).get().toDto();
    }

    @Transactional
    public TrainerDto getTrainerWithLock(Integer id) {
        return trainerRepository.findByIdWithLock(id).get().toDto();
    }



}
