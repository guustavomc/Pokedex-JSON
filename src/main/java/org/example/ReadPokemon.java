package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadPokemon {

    private String filePath;
    private List<Pokemon> listPokemon = new ArrayList<>();

    public ReadPokemon(String path) {
        this.filePath = path;
    }

    public List<Pokemon> getPokemonList() {
        ObjectMapper objectMapper = new ObjectMapper(); // Create an instance

        try {
            // Use the instance to call readValue
            JsonNode rootNode = objectMapper.readTree(new File(filePath));

            for(JsonNode pokemonNode:rootNode){
                int id = pokemonNode.get("id").asInt();
                String name = pokemonNode.get("name").get("english").asText();
                List<String> types = new ArrayList<>();
                for(JsonNode typeNode: pokemonNode.get("type")){
                    types.add(typeNode.asText());
                }
                String description = pokemonNode.get("description").asText();
                listPokemon.add(new Pokemon(id,name,types,description));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listPokemon;
    }
}
