package org.example;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/api/pokemon")
public class PokedexController {

    private ReadPokemonService service;

    public PokedexController(ReadPokemonService service) {

        this.service = service;
    }

    @GetMapping
    public List<Pokemon> getAllPokemon(){
        return service.findAllPokemon();
    }

    @GetMapping("/id/{id}")
    public Pokemon getPokemonByID(@PathVariable int id){
        return service.findPokemonByID(service.findAllPokemon(),id);
    }

    @GetMapping("/search/{search}")
    public Pokemon getPokemonByName(@PathVariable String name){
        return service.findPokemonByName(service.findAllPokemon(),name);
    }
}
