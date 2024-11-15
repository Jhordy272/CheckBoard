package com.check_board.entity;

import com.check_board.security.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "project")
public class ProjectEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "year")
    private String year;
    
    @Column(name = "start_date")
    private Date startDate;
    
    @Column(name = "end_date")
    private Date endDate;
    
    @Column(name = "fees")
    private int fees;
    
    @Column(name = "extra_expenses")
    private int extraExpenses;
    
    @Column(name = "budgeted_hours")
    private int budgetedHours;
    
    @Column(name = "hourly_rate")
    private int hourlyRate;
    
    @Column(name = "total")
    private int total;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "division", referencedColumnName = "id")
    private DivisionEntity division;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_lines", referencedColumnName = "id")
    private ServiceLineEntity serviceLine;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manager", referencedColumnName = "id")
    private UserEntity manager;
    
    @Column(name = "observations")
    private String observations;
}
