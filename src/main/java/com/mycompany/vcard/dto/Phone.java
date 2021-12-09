package com.mycompany.vcard.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Phone {

    private String area;
    private String number;
    private String formatted;
}
