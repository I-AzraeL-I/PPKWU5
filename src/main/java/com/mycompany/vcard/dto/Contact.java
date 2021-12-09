package com.mycompany.vcard.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Contact {

    private String www;
    private String email;
    private Phone phone;
}
