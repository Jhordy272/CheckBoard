package com.check_board.controller;

import com.check_board.dto.JobTitleDto;
import com.check_board.dto.Message;
import com.check_board.security.entity.JobTitleEntity;
import com.check_board.security.repository.JobTitleRepository;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job-title")
@CrossOrigin(origins = "*")
public class JobTitleController {
    @Autowired
    JobTitleRepository jobTitleRepository;
    
    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> list() {
        List<JobTitleEntity> listJobTitles = jobTitleRepository.findAll();
        return new ResponseEntity<>(listJobTitles, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        if (!jobTitleRepository.existsById(id)) {
            return new ResponseEntity<>(new Message("El cargo solicitado no existe."), HttpStatus.NOT_FOUND);
        }
        JobTitleEntity jobTitle = jobTitleRepository.findById(id).orElse(null);
        return new ResponseEntity<>(jobTitle, HttpStatus.OK);
    }
    
    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> create(@RequestBody JobTitleDto jobTitleDto, HttpServletRequest request) {
        JobTitleEntity jobTitle = new JobTitleEntity();
        if(!jobTitleRepository.findByName(jobTitleDto.getName()).isEmpty()){
            return new ResponseEntity<>(new Message("El JobTitlename ya existe."), HttpStatus.BAD_REQUEST);
        }
        jobTitle.setName(jobTitleDto.getName());
        jobTitle.setHourlyRate(jobTitleDto.getHourlyRate());
        jobTitleRepository.save(jobTitle);
        return new ResponseEntity<>(new Message("Cargo creado exitosamente."), HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody JobTitleDto jobTitleDto, HttpServletRequest request) {
        if (!jobTitleRepository.existsById(id)) {
            return new ResponseEntity<>(new Message("El cargo solicitado no existe."), HttpStatus.NOT_FOUND);
        }
        if(!jobTitleRepository.findByName(jobTitleDto.getName()).isEmpty()){
            return new ResponseEntity<>(new Message("El JobTitlename ya existe."), HttpStatus.BAD_REQUEST);
        }
        JobTitleEntity jobTitle = jobTitleRepository.findById(id).orElse(null);
        jobTitle.setName(jobTitleDto.getName());
        jobTitle.setHourlyRate(jobTitleDto.getHourlyRate());
        jobTitleRepository.save(jobTitle);
        return new ResponseEntity<>(new Message("Cargo actualizado exitosamente."), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if (!jobTitleRepository.existsById(id)) {
            return new ResponseEntity<>(new Message("El cargo solicitado no existe."), HttpStatus.NOT_FOUND);
        }
        jobTitleRepository.deleteById(id);
        return new ResponseEntity<>(new Message("Cargo eliminado exitosamente."), HttpStatus.OK);
    }
}
