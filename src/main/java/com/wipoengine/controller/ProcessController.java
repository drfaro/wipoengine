package com.wipoengine.controller;

import com.wipoengine.dtos.ProcessRecordDto;
import com.wipoengine.models.ProcessModel;
import com.wipoengine.repository.ProcessRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ProcessController {

    @Autowired
    ProcessRepository processRepository;

    @PostMapping("/process")
    public ResponseEntity<ProcessModel> saveProcess(@RequestBody @Valid ProcessRecordDto processRecordDto){
        var processModel = new ProcessModel();
        BeanUtils.copyProperties(processRecordDto, processModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(processRepository.save(processModel));
    }

    @GetMapping("/process")
    public  ResponseEntity<Object> getAllProcess(){
        return ResponseEntity.status(HttpStatus.OK).body(processRepository.findFirst50ByOrderByCreatedAtDesc());
    }


    @GetMapping("/process/{id}")
        public ResponseEntity<Object> getOneProcess(@PathVariable(value="id") UUID id){
        Optional<ProcessModel> process = processRepository.findById(id);
        if (process.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Process not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(process.get());
    }

    @GetMapping("/process/search/{term}")
    public ResponseEntity<Object> getSearchProcess(@PathVariable(value="term") String term){

        Optional<List<ProcessModel>> process = processRepository.findByExternalIdProcessContainingOrClaimantContaining(term, term);

        if (process.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Process not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(process);
    }

    @PutMapping("/process/{id}")
    public ResponseEntity<Object> updateOneProcess(@PathVariable(value="id") UUID id, @RequestBody @Valid ProcessRecordDto processRecordDto){
        Optional<ProcessModel> process = processRepository.findById(id);
        if (process.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Process not found");
        }
        var processModel = process.get();
        BeanUtils.copyProperties(processRecordDto, processModel);
        return ResponseEntity.status(HttpStatus.OK).body(processRepository.save(processModel));
    }

    @DeleteMapping("/process/{id}")
    public ResponseEntity<Object> DeleteOneProcess(@PathVariable(value="id") UUID id){
        Optional<ProcessModel> process = processRepository.findById(id);
        if (process.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Process not found");
        }
        processRepository.delete(process.get());
        return ResponseEntity.status(HttpStatus.OK).body("Process Deleted");
    }
}
