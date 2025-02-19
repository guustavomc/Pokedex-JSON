package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TeamWrite {

    public TeamWrite(String filename, ArrayList<Pokemon> team){
        String path = "src/main/java/org/example/teams/"+ filename;
        try(FileWriter file = new FileWriter(path)) {
            for (Pokemon pokemon: team){
                file.write(pokemon.pokemonDescription()+"\n");
            }
        }
        catch (IOException e){
            System.out.println("Error creating the file"+ e.getMessage());
        }
    }
}
