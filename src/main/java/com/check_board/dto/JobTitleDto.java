package com.check_board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobTitleDto {
    private Integer id;
    private String name;
    private int hourlyRate;
}
