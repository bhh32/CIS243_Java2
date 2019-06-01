public class Pirate extends Player implements Scavenger {

    private int gold;
        
    public Pirate(String name, int gold) {
       super(name);
       this.gold = gold;
    }
    
    protected void steal() {
       throw new IllegalStateException("should use steal(Player) on a Pirate");
    }
    
    // post: you have 15 pieces from other, or all they have if less
    public void steal(Player other) {
       if (other instanceof Pirate) {
          Pirate otherPirate = (Pirate)other;
          int goldToTake = Math.min(15, otherPirate.gold);
          this.gold += goldToTake;
          otherPirate.gold -= goldToTake;
       } else {
          other.steal();
       }
    }
    
    @Override
    public String toString() {
       return super.toString() + " $" + gold;
    }
    
    @Override
    public void scavenge() {
       this.gold++;
    }

}