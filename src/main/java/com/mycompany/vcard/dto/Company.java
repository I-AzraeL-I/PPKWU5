package com.mycompany.vcard.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Company {

    private String name;
    private String slug;
    private Long payable;
    private Long templateId;
    private Long companyId;
    private Long tradeId;
    private String address;
    private Contact contact;
    private String companyHash;
    private String tradeHash;
}
