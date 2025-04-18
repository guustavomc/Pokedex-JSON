package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReadPokemonService {

    private String filePath = "src/main/resources/pokedex.json";;
    private ArrayList<Pokemon> listPokemon = new ArrayList<>();

    @PostConstruct
    public ArrayList<Pokemon> getPokemonList() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode rootNode = objectMapper.readTree(new File(filePath));

            for(JsonNode pokemonNode:rootNode){
                int id = pokemonNode.get("id").asInt();
                String name = pokemonNode.get("name").get("english").asText();
                ArrayList<String> types = new ArrayList<>();
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

    public List<Pokemon> findAllPokemon(){
        return listPokemon;
    }

    public Pokemon findPokemonByID(List <Pokemon> pokedex, int id){

        return (Pokemon) pokedex.stream().filter(pokemon -> pokemon.getId()==id).findFirst().orElse(null);
    }

    public Pokemon findPokemonByName(List <Pokemon> pokedex, String name){
        return (Pokemon) pokedex.stream().filter(pokemon -> pokemon.getName().equalsIgnoreCase(name)).findFirst().orElse(null);

    }
}
