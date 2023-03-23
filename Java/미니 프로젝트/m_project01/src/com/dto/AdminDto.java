package com.dto;

import lombok.Data;

@Data
public class AdminDto extends MemberDto{
    private int a_no;
    private String a_name;
    private String a_id;
    private String a_pwd;
}
