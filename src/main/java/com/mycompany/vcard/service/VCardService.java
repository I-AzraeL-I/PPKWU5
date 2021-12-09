package com.mycompany.vcard.service;

import com.mycompany.vcard.dto.Company;
import com.mycompany.vcard.dto.Contact;
import ezvcard.VCard;
import ezvcard.property.Address;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class VCardService {

    public VCard createVCard(Company theCompany) {
        var vCard = new VCard();

        Optional.ofNullable(theCompany)
                .map(company -> {
                    var address = new Address();
                    doNullSafe(address::setLabel, company.getAddress());
                    vCard.addAddress(address);
                    doNullSafe(vCard::setFormattedName, company.getName());
                    return company;
                })
                .map(Company::getContact)
                .map(contact -> {
                    doNullSafe(vCard::addUrl, contact.getWww());
                    doNullSafe(vCard::addEmail, contact.getEmail());
                    return contact;
                })
                .map(Contact::getPhone)
                .map(phone -> {
                    var area = Objects.requireNonNullElse(phone.getArea(), "");
                    vCard.addTelephoneNumber(area + phone.getNumber());
                    return phone;
                });
        return vCard;
    }

    private <T> void doNullSafe(Consumer<T> target, T source) {
        if (source != null) {
            target.accept(source);
        }
    }
}
