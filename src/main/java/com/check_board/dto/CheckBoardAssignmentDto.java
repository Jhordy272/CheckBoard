package com.check_board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckBoardAssignmentDto {
    private Integer id;
    private int checkBoard;
    private int user;
    private int week;
    private int hours;
}
