// Project made using JSON from https://github.com/Purukitto/pokemon-data.json/tree/master
package org.example;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Pokedex");

        String path = "src/main/resources/pokedex.json";

        ReadPokemon read = new ReadPokemon(path);
        List<Pokemon> pokedex = read.getPokemonList();

        for(Pokemon pokemon:pokedex){
            System.out.println(pokemon.getName());
        }

    }
}