package com.mycompany.vcard.controller;

import com.mycompany.vcard.dto.Company;
import com.mycompany.vcard.service.ScrapperService;
import com.mycompany.vcard.service.VCardService;
import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.VCardVersion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class VCardController {

    private final ScrapperService scrapperService;
    private final VCardService vCardService;

    @GetMapping("/companies")
    public String getCompanies(@RequestParam String service,
                               @RequestParam String location,
                               @RequestParam(defaultValue = "1") Integer page,
                               Model model) {
        model.addAttribute("companies", scrapperService.getAllCompanies(service, location, page));
        return "companies";
    }

    @PostMapping("/generate")
    public void getVCard(@ModelAttribute Company company, HttpServletResponse response) {
        VCard vCard = vCardService.createVCard(company);
        response.setContentType("text/x-vcard; charset=UTF-8");
        response.addHeader("Content-Disposition", "attachment; filename=card.vcf");
        try {
            var writer = response.getWriter();
            writer.write(Ezvcard.write(vCard).version(VCardVersion.V4_0).go());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
