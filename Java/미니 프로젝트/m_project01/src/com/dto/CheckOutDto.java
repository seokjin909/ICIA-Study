package com.dto;

import lombok.Data;

@Data
public class CheckOutDto extends BookDto {
    private String c_serialnum;
    private String m_id;
    private int b_code;
    private String c_stdate;
    private String c_endate;
}
