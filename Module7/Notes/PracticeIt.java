public class PracticeIt {
   public static void waysToClimb(int stairs) {
      if (stairs > 0) {
         waysToClimb(stairs, new ArrayList<Integers>(stairs));
      }
   }
}

private static void waysToClimb(int stairs, List<Integers> path) {
   if (stairs == 0) {
      System.out.println(path);
   }
   else {
      path.add(1);
      waysToClimb(stairs - 1, path);
      path.remove(path.size() - 1);
      if (stairs > 1) {
         path.add(2);
         waysToClimb(stairs - 2, path);
         path.remove(path.size() - 1);
      }
   }   
}