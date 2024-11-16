package com.check_board.controller;

import com.check_board.dto.CheckBoardAssignmentDto;
import com.check_board.dto.Message;
import com.check_board.entity.CheckBoardAssignmentEntity;
import com.check_board.entity.CheckBoardEntity;
import com.check_board.repository.CheckBoardAssignmentRepository;
import com.check_board.repository.CheckBoardRepository;
import com.check_board.security.entity.UserEntity;
import com.check_board.security.repository.UserRepository;
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
@RequestMapping("/checkboard-assignment")
@CrossOrigin(origins = "*")
public class CheckBoardAssignmentController {
    
    @Autowired
    CheckBoardAssignmentRepository checkBoardAssignmentRepository;
    
    @Autowired
    CheckBoardRepository checkBoardRepository;
    
    @Autowired
    UserRepository userRepository;
    
    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> list() {
        List<CheckBoardAssignmentEntity> listCheckBoardAssigment = checkBoardAssignmentRepository.findAll();
        return new ResponseEntity<>(listCheckBoardAssigment, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        if (!checkBoardAssignmentRepository.existsById(id)) {
            return new ResponseEntity<>(new Message("El CheckBoardAssigment solicitado no existe."), HttpStatus.NOT_FOUND);
        }
        CheckBoardAssignmentEntity checkBoardAssignment = checkBoardAssignmentRepository.findById(id).orElse(null);
        return new ResponseEntity<>(checkBoardAssignment, HttpStatus.OK);
    }
    
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> create(@RequestBody CheckBoardAssignmentDto checkBoardAssignmentDto) {
        CheckBoardAssignmentEntity checkBoardAssignment = new CheckBoardAssignmentEntity();
        
        CheckBoardEntity checkBoard = checkBoardRepository.findById(checkBoardAssignmentDto.getCheckBoard()).orElse(null);
        checkBoardAssignment.setCheckBoard(checkBoard);
        
        UserEntity user = userRepository.findById(checkBoardAssignmentDto.getUser()).orElse(null);
        checkBoardAssignment.setUser(user);
        
        checkBoardAssignment.setHours(checkBoardAssignmentDto.getHours());
        checkBoardAssignment.setWeek(checkBoardAssignmentDto.getWeek());
        
        checkBoardAssignmentRepository.save(checkBoardAssignment);
        return new ResponseEntity<>(new Message("CheckBoardAssignment creado exitosamente."), HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody CheckBoardAssignmentDto checkBoardAssignmentDto) {
        if (!checkBoardAssignmentRepository.existsById(id)) {
            return new ResponseEntity<>(new Message("El CheckBoardAssignment solicitado no existe."), HttpStatus.NOT_FOUND);
        }

        CheckBoardAssignmentEntity checkBoardAssignment = checkBoardAssignmentRepository.findById(id).orElse(null);
        CheckBoardEntity checkBoard = checkBoardRepository.findById(checkBoardAssignmentDto.getCheckBoard()).orElse(null);
        checkBoardAssignment.setCheckBoard(checkBoard);
        
        UserEntity user = userRepository.findById(checkBoardAssignmentDto.getUser()).orElse(null);
        checkBoardAssignment.setUser(user);
        
        checkBoardAssignment.setHours(checkBoardAssignmentDto.getHours());
        checkBoardAssignment.setWeek(checkBoardAssignmentDto.getWeek());
        
        checkBoardAssignmentRepository.save(checkBoardAssignment);
        return new ResponseEntity<>(new Message("CheckBoardAssignment actualizado exitosamente."), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if (!checkBoardAssignmentRepository.existsById(id)) {
            return new ResponseEntity<>(new Message("El CheckBoardAssigment solicitado no existe."), HttpStatus.NOT_FOUND);
        }
        checkBoardAssignmentRepository.deleteById(id);
        return new ResponseEntity<>(new Message("CheckBoardAssignment eliminado exitosamente."), HttpStatus.OK);
    }
}
