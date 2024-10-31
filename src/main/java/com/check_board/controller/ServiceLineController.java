package com.check_board.controller;

import com.check_board.dto.Message;
import com.check_board.dto.ServiceLineDto;
import com.check_board.entity.ServiceLineEntity;
import com.check_board.repository.ServiceLineRepository;
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
@RequestMapping("/services-lines")
@CrossOrigin(origins = "*")
public class ServiceLineController {
    @Autowired
    ServiceLineRepository serviceLineRepository;
    
    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> list() {
        List<ServiceLineEntity> listServiceLines = serviceLineRepository.findAll();
        return new ResponseEntity<>(listServiceLines, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        if (!serviceLineRepository.existsById(id)) {
            return new ResponseEntity<>(new Message("La linea de servicio solicitada no existe."), HttpStatus.NOT_FOUND);
        }
        ServiceLineEntity serviceLine = serviceLineRepository.findById(id).orElse(null);
        return new ResponseEntity<>(serviceLine, HttpStatus.OK);
    }
    
    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> create(@RequestBody ServiceLineDto serviceLineDto, HttpServletRequest request) {
        ServiceLineEntity serviceLine = new ServiceLineEntity();
        if(!serviceLineRepository.findByName(serviceLineDto.getName()).isEmpty()){
            return new ResponseEntity<>(new Message("El serviceLinename ya existe."), HttpStatus.BAD_REQUEST);
        }
        serviceLine.setName(serviceLineDto.getName());
        serviceLineRepository.save(serviceLine);
        return new ResponseEntity<>(new Message("Linea de servicio creada exitosamente."), HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody ServiceLineDto serviceLineDto, HttpServletRequest request) {
        if (!serviceLineRepository.existsById(id)) {
            return new ResponseEntity<>(new Message("La linea de servicio solicitada no existe."), HttpStatus.NOT_FOUND);
        }
        if(!serviceLineRepository.findByName(serviceLineDto.getName()).isEmpty()){
            return new ResponseEntity<>(new Message("El ServiceLinename ya existe."), HttpStatus.BAD_REQUEST);
        }
        ServiceLineEntity serviceLine = serviceLineRepository.findById(id).orElse(null);
        serviceLine.setName(serviceLineDto.getName());
        serviceLineRepository.save(serviceLine);
        return new ResponseEntity<>(new Message("Linea de servicio actualizado exitosamente."), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if (!serviceLineRepository.existsById(id)) {
            return new ResponseEntity<>(new Message("La Linea de servicio solicitada no existe."), HttpStatus.NOT_FOUND);
        }
        serviceLineRepository.deleteById(id);
        return new ResponseEntity<>(new Message("Linea de servicio eliminada exitosamente."), HttpStatus.OK);
    }
}
