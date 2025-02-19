// Project made using JSON from https://github.com/Purukitto/pokemon-data.json/tree/master
package org.example;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Pokedex");

        String path = "src/main/resources/pokedex.json";

        ReadPokemon read = new ReadPokemon(path);
        ArrayList <Pokemon> pokedex = read.getPokemonList();

        boolean inProgress=true;
        while(inProgress){
            Scanner scanner = new Scanner(System.in);
            System.out.println("\n1 - Find By ID");
            System.out.println("\n2 - Find By Name");
            System.out.println("\n3 - Build your Team");


            Integer value = scanner.nextInt();
            switch (value){
                case 1:
                    System.out.println("Write the ID");
                    int id = scanner.nextInt();
                    Pokemon pokemonID = findPokemonByID(pokedex, id);
                    System.out.println(pokemonID.pokemonDescription());
                    break;
                case 2:
                    System.out.println("Write the Name");
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    Pokemon pokemonName = findPokemonByName(pokedex, name);
                    System.out.println(pokemonName.pokemonDescription());
                    break;
                case 3:
                    System.out.println("Build your Team");
                    int i=0;
                    int j=1;
                    ArrayList <Pokemon> team = new ArrayList<>();
                    scanner.nextLine();
                    while(i<6){
                        System.out.println("Select Pokemon number " + j +" - Type Name or ID");

                        String input = scanner.nextLine();
                        if(input.matches("\\d+")){
                            int pokemonIdTeam = Integer.parseInt(input);
                            team.add(findPokemonByID(pokedex, pokemonIdTeam));
                        }
                        else{
                            team.add(findPokemonByName(pokedex, input));

                        }
                        i+=1;
                        j+=1;
                    }
                    for(Pokemon pokemon: team){
                        System.out.println(pokemon.pokemonDescription());
                    }
                    System.out.println("Would you like to save the team on a text file? Y/N" );
                    String decision = scanner.nextLine();

                    if(decision.equalsIgnoreCase("Y")){
                        System.out.println("Write file name");
                        String fileName = scanner.nextLine();
                        TeamWrite writeTeam = new TeamWrite(fileName+".txt",team);

                    }
                    else{
                        break;
                    }
            }
        }
    }

    public static Pokemon findPokemonByID(ArrayList <Pokemon> pokedex, int id){
        return pokedex.get(id-1);
        /*
        int left = 0;
        int right = pokedex.size()-1;

        while(left<=right){
            int middle = left+(right-left)/2;
            int middleID = pokedex.get(middle).getId();
            if(middleID==id){
                return pokedex.get(middle);
            }
            else if(id<middle){
                right=middle-1;
            }
            else{
                left = middle+1;
            }
        }
        return null; */
    }

    public static Pokemon findPokemonByName(ArrayList <Pokemon> pokedex, String name){
        for(Pokemon pokemon:pokedex){
            if(pokemon.getName().equalsIgnoreCase(name)){
                return pokemon;
            }
        }
        return null;
    }
}