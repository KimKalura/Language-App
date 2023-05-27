package com.spring.languageapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.spring.languageapp.dto.TranslatedWordDTO;
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
import java.util.ArrayList;
import java.util.List;

@Service
public class DictionaryService {


    private static final String WORD_URL = "https://dictionary.yandex.net/api/v1/dicservice.json/lookup?key={APIkey}&lang={en-ru}&text={code}";
    private static final String LANGUAGES_URL = "https://dictionary.yandex.net/api/v1/dicservice.json/getLangs?key=APIkey";

    @Value("${api.dictionary.yandex}")
    private String apiKey;

    private RestTemplate restTemplate;

    @Autowired
    public DictionaryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //VAR1
   /* public TranslatedWordDTO getTranslatedWord(String languageType, String word) throws JsonProcessingException {
        URI url = new UriTemplate(WORD_URL).expand(apiKey, languageType, word);

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String definitionBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(response.getBody());

        String text = root.path("def").get(0).path("text").asText();
        String partOfSpeech = root.path("def").get(0).path("pos").asText();
        String transliteration = root.path("def").get(0).path("ts").asText();
        String textTranslation = root.path("def").get(0).path("tr").get(0).path("text").asText();
        //String partOfSpeechTranslation = root.path("def").get(0).path("tr").get(0).path("pos").asText();
        //String gen = root.path("def").get(0).path("tr").get(0).path("gen").asText();
        String textSynonymous = root.path("def").get(0).path("tr").get(0).path("syn").get(0).path("text").asText();
        String textMeaning = root.path("def").get(0).path("tr").get(0).path("mean").get(0).path("text").asText();
        return new TranslatedWordDTO(text, partOfSpeech, transliteration, textTranslation, textSynonymous, textMeaning);
    }*/


    public List<TranslatedWordDTO> getTranslatedWord(String languageType, String word) throws JsonProcessingException {
        JsonNode root = getResponseBodyJson(WORD_URL, languageType, word);
        ArrayNode wordsListJson = (ArrayNode) root.path("def");
        List<TranslatedWordDTO> wordsList = new ArrayList<>();
        for(JsonNode wordJson : wordsListJson) {
            wordsList.add(convertFromJsonToTranslatedWord(wordJson));
        }
        return wordsList;
    }

    public JsonNode getResponseBodyJson(String requestBaseUrl, String languageType, String word) throws JsonProcessingException {
        URI url = new UriTemplate(requestBaseUrl).expand(apiKey, languageType, word);
        return makeAPICall(url);
    }

    private JsonNode makeAPICall(URI url) throws JsonProcessingException {
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(response.getBody());
    }

    public TranslatedWordDTO convertFromJsonToTranslatedWord(JsonNode node) {
        String text = node.path("text").asText();
        String partOfSpeech = node.path("pos").asText();
        String transliteration = node.path("ts").asText();
        String textTranslation = node.path("tr").get(0).path("text").asText();
        String textSynonymous = node.path("tr").get(0).path("syn").get(0).path("text").asText();
        String textMeaning = node.path("tr").get(0).path("mean").get(0).path("text").asText();
        return new TranslatedWordDTO(text, partOfSpeech, transliteration, textTranslation, textSynonymous, textMeaning);
    }



    //pentru response->body(->JSON)
    public String getTranslation(String languageType, String word) {
        RestTemplate restTemplate = new RestTemplate();
        URI url = new UriTemplate(WORD_URL).expand(apiKey, languageType, word);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return "";
    }

    //*Accepted languages:
    // ["be-be","be-ru",
    // "bg-ru",
    // "cs-cs","cs-en","cs-ru",
    // "da-en","da-ru",
    // "de-de","de-en","de-ru","de-tr",
    // "el-en","el-ru",
    // "en-cs","en-da","en-de","en-el","en-en","en-es","en-et","en-fi","en-fr","en-it","en-lt","en-lv","en-nl","en-no","en-pt","en-pt-BR","en-ru","en-sk","en-sv","en-tr", "en-uk",
    // "es-en","es-es","es-ru",
    // "et-en","et-ru",
    // "fi-en","fi-ru","fi-fi",
    // "fr-fr","fr-en","fr-ru",
    // "hu-hu","hu-ru",
    // "it-en","it-it","it-ru",
    // "lt-en","lt-lt","lt-ru",
    // "lv-en","lv-ru",
    // "mhr-ru","mrj-ru",
    // "nl-en","nl-ru",
    // "no-en","no-ru",
    // "pl-ru",
    // "pt-en","pt-ru",
    // "ru-be","ru-bg","ru-cs","ru-da","ru-de","ru-el","ru-en","ru-es","ru-et","ru-fi","ru-fr","ru-hu","ru-it","ru-lt","ru-lv","ru-mhr","ru-mrj","ru-nl","ru-no","ru-pl","ru-pt","ru-pt-BR","ru-ru","ru-sk","ru-sv","ru-tr","ru-tt","ru-uk","ru-zh",
    // "sk-en","sk-ru",
    // "sv-en","sv-ru",
    // "tr-de","tr-en","tr-ru",
    // "tt-ru",
    // "uk-en","uk-ru","uk-uk",
    // "zh-ru",
    // "emj-ru","emj-en","emj-fr","emj-es","emj-tr","emj-it","emj-pt"]
}
