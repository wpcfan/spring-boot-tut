package dev.local.taskmgr.mongo.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.local.taskmgr.mongo.domain.Quote;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class QuoteServiceImpl implements QuoteService {

    private final RestTemplate rest;

    @Autowired
    public QuoteServiceImpl(RestTemplate rest) {
        this.rest = rest;
    }

    @Override
    public Quote getDailyQuote() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "mozilla");
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        URI targetUrl= UriComponentsBuilder.fromUriString("http://open.iciba.com/dsapi")
                .queryParam("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
                .build()
                .toUri();
        ResponseEntity<String> responseEntity = this.rest.exchange(targetUrl, HttpMethod.GET, entity, String.class);
        String result = responseEntity.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(result);
        JsonNode note = rootNode.path("note");
        JsonNode content = rootNode.path("content");
        JsonNode picture = rootNode.path("picture2");
        return new Quote(note.asText(), content.asText(), picture.asText());
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    class CibaQuote {
        private String content;
        private String note;
        private String picture;
    }
}
