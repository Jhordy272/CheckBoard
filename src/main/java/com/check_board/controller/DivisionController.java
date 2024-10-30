package com.check_board.controller;

import com.check_board.dto.DivisionDto;
import com.check_board.dto.Message;
import com.check_board.entity.DivisionEntity;
import com.check_board.repository.DivisionRepository;
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
@RequestMapping("/divisions")
@CrossOrigin(origins = "*")
public class DivisionController {
    @Autowired
    DivisionRepository divisionRepository;
    
    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> list() {
        List<DivisionEntity> listDivision = divisionRepository.findAll();
        return new ResponseEntity<>(listDivision, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        if (!divisionRepository.existsById(id)) {
            return new ResponseEntity<>(new Message("La división solicitada no existe."), HttpStatus.NOT_FOUND);
        }
        DivisionEntity division = divisionRepository.findById(id).orElse(null);
        return new ResponseEntity<>(division, HttpStatus.OK);
    }
    
    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> create(@RequestBody DivisionDto divisionDto, HttpServletRequest request) {
        DivisionEntity division = new DivisionEntity();
        if(!divisionRepository.findByName(divisionDto.getName()).isEmpty()){
            return new ResponseEntity<>(new Message("El Divisionname ya existe."), HttpStatus.BAD_REQUEST);
        }
        division.setName(divisionDto.getName());
        divisionRepository.save(division);
        return new ResponseEntity<>(new Message("Division creada exitosamente."), HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DivisionDto divisionDto, HttpServletRequest request) {
        if (!divisionRepository.existsById(id)) {
            return new ResponseEntity<>(new Message("La Divison solicitada no existe."), HttpStatus.NOT_FOUND);
        }
        if(!divisionRepository.findByName(divisionDto.getName()).isEmpty()){
            return new ResponseEntity<>(new Message("El Divisionname ya existe."), HttpStatus.BAD_REQUEST);
        }
        DivisionEntity division = divisionRepository.findById(id).orElse(null);
        division.setName(divisionDto.getName());
        divisionRepository.save(division);
        return new ResponseEntity<>(new Message("Division actualizado exitosamente."), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if (!divisionRepository.existsById(id)) {
            return new ResponseEntity<>(new Message("La division solicitada no existe."), HttpStatus.NOT_FOUND);
        }
        divisionRepository.deleteById(id);
        return new ResponseEntity<>(new Message("Division eliminada exitosamente."), HttpStatus.OK);
    }
}
