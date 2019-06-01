public abstract class Player implements Comparable<Player> {

    private String name;
    private int health = 100;
        
    public Player(String name) {
       this.name = name;
    }
    
    public void attack(Player other) {
       if (other.health > 0) {
         other.health--;
       }
    }
    
    protected abstract void steal();
    
    /* sort them by name, alphabetically; then by health */
    @Override
    public int compareTo(Player other) {
       if (other.name.equals(this.name)) {
          return other.health - this.health;
       } else {
          return this.name.compareTo(other.name);
       }
    }
    
    @Override
    public boolean equals(Object other) {
       if (! (other instanceof Player)) {
          return false;
       }
       return this.compareTo((Player)other) == 0;
    }
    
    @Override
    public String toString() {
       return name + " H" + health; 
    }
}