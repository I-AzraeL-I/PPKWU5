package com.mycompany.vcard.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return name.equals(company.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
