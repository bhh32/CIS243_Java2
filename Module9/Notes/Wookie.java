public class Wookie extends Player implements Scavenger {

    public Wookie(String name) {
       super(name);
    }
    
    protected void steal() {
       // does nothing
    }
    
    public void roar(Player other) {
       System.out.println("IAMWOOKIE");
    }
    
    @Override
    public final void scavenge() {
       System.out.println("NOMNOMNOM");
    }
    
}