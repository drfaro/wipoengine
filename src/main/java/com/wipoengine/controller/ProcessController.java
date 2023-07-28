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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
        Optional<List<ProcessModel>> optionalListProcessModel= processRepository.findFirst50ByOrderByCreatedAtDesc();
        List<ProcessModel> listProcessModel = optionalListProcessModel.get();
        if (!listProcessModel.isEmpty()){
            for (ProcessModel processModel : listProcessModel){
                UUID id = processModel.getIdProcess();
                processModel.add(linkTo(methodOn(ProcessController.class).getOneProcess(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalListProcessModel);
    }


    @GetMapping("/process/{id}")
        public ResponseEntity<Object> getOneProcess(@PathVariable(value="id") UUID id){
        Optional<ProcessModel> process = processRepository.findById(id);
        if (process.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Process not found");
        }
        process.get().add(linkTo(methodOn(ProcessController.class).getAllProcess()).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(process.get());
    }

    @GetMapping("/process/search/{term}")
    public ResponseEntity<Object> getSearchProcess(@PathVariable(value="term") String term){

        Optional<List<ProcessModel>> optionalListProcessModel = processRepository.findByExternalIdProcessContainingOrClaimantContaining(term, term);

        if (optionalListProcessModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Process not found");
        }
        List<ProcessModel> listProcessModel = optionalListProcessModel.get();
        if (!listProcessModel.isEmpty()){
            for (ProcessModel processModel : listProcessModel){
                UUID id = processModel.getIdProcess();
                processModel.add(linkTo(methodOn(ProcessController.class).getOneProcess(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalListProcessModel);
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
