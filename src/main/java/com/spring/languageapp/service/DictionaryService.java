package com.spring.languageapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

@Service
public class DictionaryService {


    private static final String WORD_URL = "https://dictionary.yandex.net/api/v1/dicservice.json/lookup?key={APIkey}&lang={en-ru}&text={word}"; //lang=en-ru
                                          //https://dictionary.yandex.net/api/v1/dicservice.json/lookup?key=APIkey&lang=en-ru&text=time
    @Value("${api.dictionary.yandex}")
    private String apiKey;

    private RestTemplate restTemplate;

    @Autowired
    public DictionaryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public String  getWord(String word) throws JsonProcessingException {
        URI url = new UriTemplate(WORD_URL).expand(word, apiKey);

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String definitionBody= response.getBody();
        ObjectMapper objectMapper= new ObjectMapper();
        JsonNode root = objectMapper.readTree(response.getBody());

        //fac un definitionWordDTO
        //setez caracteristicile DTO-ului

        return "";
    }


    //pentru a citi k-v din JSON TODO: nu functioneaza; nu intra pe metoda
    /*public String getWord(String word) {
        RestTemplate restTemplate = new RestTemplate();
        URI url = new UriTemplate(WORD_URL).expand(word, apiKey);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return "";
    }*/

}
