package com.example.demo.trainer;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainer")
@RequiredArgsConstructor
public class TrainerController {

    private final TrainerService trainerService;
    private final TrainerRepository trainerRepository;


    @GetMapping("/{id}")
    public ResponseEntity<TrainerDto> getTrainer(
             @PathVariable int id
    ){
        return ResponseEntity.ok(trainerService.getTrainer(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTrainer(
            @PathVariable int id
    ){
        trainerRepository.deleteById(id);
        return ResponseEntity.ok("ok");
    }

    @DeleteMapping("/{id}/annotation")
    public ResponseEntity<String> deleteTrainerWithAnnotation(
            @PathVariable int id
    ){
        trainerRepository.deleteTrainerByIdWithAnnotation(id);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/{id}/lock")
    public ResponseEntity<TrainerDto> getTrainerWithLock(
            @PathVariable int id
    ){
        return ResponseEntity.ok(trainerService.getTrainerWithLock(id));
    }
}
