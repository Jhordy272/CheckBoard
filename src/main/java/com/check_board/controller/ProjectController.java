package com.check_board.controller;

import com.check_board.dto.Message;
import com.check_board.dto.ProjectDto;
import com.check_board.entity.DivisionEntity;
import com.check_board.entity.ProjectEntity;
import com.check_board.entity.ServiceLineEntity;
import com.check_board.repository.DivisionRepository;
import com.check_board.repository.ProjectRepository;
import com.check_board.repository.ServiceLineRepository;
import com.check_board.security.entity.UserEntity;
import com.check_board.security.repository.UserRepository;
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
@RequestMapping("/projects")
@CrossOrigin(origins = "*")
public class ProjectController {
    
    @Autowired
    ProjectRepository projectRepository;
    
    @Autowired
    DivisionRepository divisionRepository;
    
    @Autowired
    ServiceLineRepository serviceLineRepository;
    
    @Autowired
    UserRepository userRepository;
    
    
    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> list() {
        List<ProjectEntity> listProjects = projectRepository.findAll();
        return new ResponseEntity<>(listProjects, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        if (!projectRepository.existsById(id)) {
            return new ResponseEntity<>(new Message("El proyecto solicitado no existe."), HttpStatus.NOT_FOUND);
        }
        ProjectEntity project = projectRepository.findById(id).orElse(null);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }
    
    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> create(@RequestBody ProjectDto projectDto, HttpServletRequest request) {
        ProjectEntity project = new ProjectEntity();
        if(!projectRepository.findByName(projectDto.getName()).isEmpty()){
            return new ResponseEntity<>(new Message("El Projectname ya existe."), HttpStatus.BAD_REQUEST);
        }
        project.setName(projectDto.getName());
        project.setYear(projectDto.getYear());
        project.setStartDate(projectDto.getStartDate());
        project.setEndDate(projectDto.getEndDate());
        project.setFees(projectDto.getFees());
        project.setExtraExpenses(projectDto.getExtraExpenses());
        project.setBudgetedHours(projectDto.getBudgetedHours());
        project.setHourlyRate(projectDto.getHourlyRate());
        project.setTotal(projectDto.getTotal());
        
        DivisionEntity division = divisionRepository.findById(projectDto.getDivision()).orElse(null);
        project.setDivision(division);
        
        ServiceLineEntity serviceLine = serviceLineRepository.findById(projectDto.getServiceLine()).orElse(null);
        project.setServiceLine(serviceLine);
        
        UserEntity manager = userRepository.findById(projectDto.getManager()).orElse(null);
        project.setManager(manager);
        
        project.setObservations(projectDto.getObservations());
        projectRepository.save(project);
        return new ResponseEntity<>(new Message("Proyecto creada exitosamente."), HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody ProjectDto projectDto, HttpServletRequest request) {
        if (!projectRepository.existsById(id)) {
            return new ResponseEntity<>(new Message("El proyecto solicitado no existe."), HttpStatus.NOT_FOUND);
        }
        if(!projectRepository.findByName(projectDto.getName()).isEmpty()){
            return new ResponseEntity<>(new Message("El Projectname ya existe."), HttpStatus.BAD_REQUEST);
        }
        ProjectEntity project = projectRepository.findById(id).orElse(null);
        project.setName(projectDto.getName());
        project.setYear(projectDto.getYear());
        project.setStartDate(projectDto.getStartDate());
        project.setEndDate(projectDto.getEndDate());
        project.setFees(projectDto.getFees());
        project.setExtraExpenses(projectDto.getExtraExpenses());
        project.setBudgetedHours(projectDto.getBudgetedHours());
        project.setHourlyRate(projectDto.getHourlyRate());
        project.setTotal(projectDto.getTotal());
        
        DivisionEntity division = divisionRepository.findById(projectDto.getDivision()).orElse(null);
        project.setDivision(division);
        
        ServiceLineEntity serviceLine = serviceLineRepository.findById(projectDto.getServiceLine()).orElse(null);
        project.setServiceLine(serviceLine);
        
        UserEntity manager = userRepository.findById(projectDto.getManager()).orElse(null);
        project.setManager(manager);
        
        project.setObservations(projectDto.getObservations());
        projectRepository.save(project);
        return new ResponseEntity<>(new Message("Proyecto creado exitosamente."), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if (!projectRepository.existsById(id)) {
            return new ResponseEntity<>(new Message("El proyecto solicitado no existe."), HttpStatus.NOT_FOUND);
        }
        projectRepository.deleteById(id);
        return new ResponseEntity<>(new Message("Proyecto eliminado exitosamente."), HttpStatus.OK);
    }
}
