package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public  class BuscadorDeFilmes {
    private final Gson gson;

    public BuscadorDeFilmes(Gson gson) {
        this.gson = gson;
    }

    public Titulo buscarTitulo (String busca) throws IOException, InterruptedException {
        String endereco = "http://www.omdbapi.com/?t=" + busca.replace(" ", "+") + "&apikey=cf954156";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        System.out.println(json);

        TituloOmdb meuTituloOmdb = gson.fromJson(json, TituloOmdb.class);
        System.out.println(meuTituloOmdb);
        return new Titulo(meuTituloOmdb);
    }
}