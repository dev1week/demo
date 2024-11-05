package com.example.demo.trainer;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trainer")
@RequiredArgsConstructor
public class TrainerController {

    private final TrainerService trainerService;


    @GetMapping("/{id}")
    public ResponseEntity<TrainerDto> getTrainer(
             @PathVariable int id
    ){
        return ResponseEntity.ok(trainerService.getTrainer(id));
    }

    @GetMapping("/{id}/lock")
    public ResponseEntity<TrainerDto> getTrainerWithLock(
            @PathVariable int id
    ){
        return ResponseEntity.ok(trainerService.getTrainerWithLock(id));
    }
}
