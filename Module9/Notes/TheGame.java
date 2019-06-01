import java.util.*;

public class TheGame {

    public static void main(String[] args) {
        List<Pirate> pirates;
        List<Ninja> ninjas;
        List<Player> players = new ArrayList<Player>();
        Player bobPirate;
        Player bobNinja;
        Player bobWookie;
        
        // not good style, but it works...
        players.add(bobPirate = new Pirate("Bob", 20));
        players.add(bobNinja = new Ninja("Bob", 20));
        players.add(bobWookie = new Wookie("Bob"));
        
        System.out.println("pirate: " + bobPirate + " ninja:" + bobNinja);
        bobPirate.attack(bobNinja);
        System.out.println("pirate: " + bobPirate + " ninja:" + bobNinja);
        bobNinja.attack(bobPirate);
        bobNinja.attack(bobPirate);
        System.out.println("pirate: " + bobPirate + " ninja:" + bobNinja);
        
        //bobPirate.flick();
        ((Ninja)bobNinja).flick(bobPirate);
        System.out.println("pirate: " + bobPirate + " ninja:" + bobNinja);
        
        ((Pirate)bobPirate).steal(bobNinja);
        System.out.println("pirate: " + bobPirate + " ninja:" + bobNinja);

        System.out.println(players);        
        Collections.sort(players);
        System.out.println(players);      
        
        ((Pirate)bobPirate).steal(bobWookie);
        System.out.println(players);
        bobPirate.attack(bobWookie);     
        System.out.println(players);
        Collections.sort(players);
        System.out.println(players);          
        bobPirate.attack(bobWookie);     
        System.out.println(players);          
        Collections.sort(players);
        System.out.println(players);
        
        for (Player player: players) {
           if (player instanceof Scavenger) {
              ((Scavenger)player).scavenge();
              System.out.println(player);
           }
        }        
        
    }

}