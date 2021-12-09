package com.mycompany.vcard.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Data {

    private Coordinates coordinates;
    private List<Company> companies;
}
