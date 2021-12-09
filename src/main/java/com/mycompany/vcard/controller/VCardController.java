package com.mycompany.vcard.controller;

import com.mycompany.vcard.service.ScrapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class VCardController {

    private final ScrapperService scrapperService;

    @GetMapping("/companies")
    public String getCompanies(@RequestParam String service,
                               @RequestParam String location,
                               @RequestParam(defaultValue = "1") Integer page,
                               Model model) {
        model.addAttribute("companies", scrapperService.getAllCompanies(service, location, page));
        return "companies";
    }
}
