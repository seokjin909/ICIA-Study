package com.dto;

import lombok.Data;

@Data
public class ManagementDto extends CheckOutDto {
    private int b_code;
    private String m_id;
    private String book_tf;
    private String  c_serialnum;

}
