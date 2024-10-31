package com.check_board.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    private Integer id;
    private String name;
    private String year;
    private Date startDate;
    private Date endDate;
    private int fees;
    private int extraExpenses;
    private int budgetedHours;
    private int hourlyRate;
    private int total;
    private int division;
    private int serviceLine;
    private int manager;
    private String observations;
}
