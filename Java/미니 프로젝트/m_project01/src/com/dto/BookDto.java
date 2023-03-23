package com.dto;

import lombok.Data;

@Data
public class BookDto extends MemberDto {
    private int b_code;
    private String b_name;
    private String b_writer;
    private String b_genre;
    private String b_date;


}
