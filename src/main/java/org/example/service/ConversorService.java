package org.example.service;

import com.google.gson.Gson;
import org.example.record.Conversion;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConversorService {
    private final String apiKey = System.getenv("API_KEY");
    private Gson gson = new Gson();

    public Conversion getConvertedValue(String moneda1, String moneda2, int valorAConvertir) {
        String url = String.format("https://v6.exchangerate-api.com/v6/%s/pair/%s/%s/%d", apiKey, moneda1, moneda2, valorAConvertir);

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            return gson.fromJson(response.body(), Conversion.class);

        } catch (IOException | InterruptedException  e) {
            throw new RuntimeException(e);
        }
    }
}

