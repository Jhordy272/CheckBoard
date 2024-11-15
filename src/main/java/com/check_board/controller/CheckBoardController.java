package com.check_board.controller;

import com.check_board.dto.CheckBoardDto;
import com.check_board.dto.Message;
import com.check_board.entity.CheckBoardEntity;
import com.check_board.entity.ProjectEntity;
import com.check_board.repository.CheckBoardRepository;
import com.check_board.repository.ProjectRepository;
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
@RequestMapping("/checkboard")
@CrossOrigin(origins = "*")
public class CheckBoardController {
    
    @Autowired
    CheckBoardRepository checkBoardRepository;
    
    @Autowired
    ProjectRepository projectRepository;
    
    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> list() {
        List<CheckBoardEntity> listCheckBoard = checkBoardRepository.findAll();
        return new ResponseEntity<>(listCheckBoard, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        if (!checkBoardRepository.existsById(id)) {
            return new ResponseEntity<>(new Message("El CheckBoard solicitado no existe."), HttpStatus.NOT_FOUND);
        }
        CheckBoardEntity checkBoard = checkBoardRepository.findById(id).orElse(null);
        return new ResponseEntity<>(checkBoard, HttpStatus.OK);
    }
    
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> create(@RequestBody CheckBoardDto checkBoardDto) {
        CheckBoardEntity checkBoard = new CheckBoardEntity();
        if(!checkBoardRepository.findByName(checkBoardDto.getName()).isEmpty()){
            return new ResponseEntity<>(new Message("El CheckBoardName ya existe."), HttpStatus.BAD_REQUEST);
        }
        checkBoard.setName(checkBoardDto.getName());
        ProjectEntity project = projectRepository.findById(checkBoardDto.getProject()).orElse(null);
        checkBoard.setProject(project);
        
        checkBoardRepository.save(checkBoard);
        return new ResponseEntity<>(new Message("CheckBoard creado exitosamente."), HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody CheckBoardDto checkBoardDto) {
        if (!projectRepository.existsById(id)) {
            return new ResponseEntity<>(new Message("El CheckBoard solicitado no existe."), HttpStatus.NOT_FOUND);
        }
        if(!projectRepository.findByName(checkBoardDto.getName()).isEmpty()){
            return new ResponseEntity<>(new Message("El CheckBoardName ya existe."), HttpStatus.BAD_REQUEST);
        }
        CheckBoardEntity checkBoard = checkBoardRepository.findById(id).orElse(null);
        checkBoard.setName(checkBoardDto.getName());
        ProjectEntity project = projectRepository.findById(checkBoardDto.getProject()).orElse(null);
        checkBoard.setProject(project);
        
        checkBoardRepository.save(checkBoard);
        return new ResponseEntity<>(new Message("CheckBoard actualizado exitosamente."), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if (!checkBoardRepository.existsById(id)) {
            return new ResponseEntity<>(new Message("El CheckBoard solicitado no existe."), HttpStatus.NOT_FOUND);
        }
        checkBoardRepository.deleteById(id);
        return new ResponseEntity<>(new Message("CheckBoard eliminado exitosamente."), HttpStatus.OK);
    }
}
