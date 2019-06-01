public final class Ninja extends Player {

    private int shuriken;

    public Ninja(String name, int shuriken) {
       super(name);
       this.shuriken = shuriken;
    }
    
    // pirate steals shuriken and drops them
    protected void steal() {
       int shurikenToTake = Math.min(15, this.shuriken);
       shuriken -= shurikenToTake;
    }
    
    // post: you have 15 pieces from other, or all they have if less
    public void flick(Player other) {
       int shurikenToThrow = Math.min(5, this.shuriken);
       this.shuriken -= shurikenToThrow;
       if (other instanceof Ninja) {
          ((Ninja)other).shuriken += shurikenToThrow;
       }
    }
    
    @Override
    public String toString() {
       return super.toString() + " T" + shuriken;
    }

}