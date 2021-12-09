package com.mycompany.vcard.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.vcard.dto.Company;
import com.mycompany.vcard.dto.Data;
import com.mycompany.vcard.exception.ParserException;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScrapperService {

    private static final String urlTemplate = "https://panoramafirm.pl/%s/%s/firmy,%d.html";
    private final ObjectMapper objectMapper;

    public List<Company> getAllCompanies(String service, String location, Integer page) {
        String url = createFormattedUrl(service, location, page);
        try {
            String json = getDataJson(url);

            var dataListType = objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, Data.class);
            List<Data> dataList = objectMapper.readValue(json, dataListType);

            return dataList.stream()
                    .map(Data::getCompanies)
                    .flatMap(List::stream)
                    .sorted(Comparator.comparing(Company::getName))
                    .distinct()
                    .collect(Collectors.toUnmodifiableList());
        } catch (IOException e) {
            throw new ParserException(url);
        }
    }

    private String getDataJson(String path) throws IOException {
        Document document = Jsoup.connect(path).get();
        return Arrays.stream(document.body().html().split("\n"))
                .filter(line -> line.contains("var markers"))
                .map(String::trim)
                .map(line -> line.substring(line.indexOf("[")))
                .findFirst()
                .orElse("");
    }

    private String createFormattedUrl(String service, String location, Integer page) {
        return String.format(urlTemplate, service, location, page);
    }
}
