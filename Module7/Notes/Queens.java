import java.util.Scanner;

public class Queens {
   public static void main(String[] args) {
        giveIntro();
        Scanner console = new Scanner(System.in);
        System.out.print("What size board do you want to use? ");
        int size = console.nextInt();
        System.out.println();
        Board b = new BoardFrame(size);
        solve(b);
    }

    // post: explains program to user
    public static void giveIntro() {
        System.out.println("This program solves the classic '8 queens'");
        System.out.println("problem, placing queens on a chessboard so");
        System.out.println("that no two queens threaten each other.");
        System.out.println();
    }
   
   public static void solve(Board b) {
      explore(b, 1);
   }
   
   // pre: all previous columns have a queen safely placed
   // post: explored all placements of queens from column to board size
   private static void explore(Board b, int column) {
      if (column > b.size()) {
         b.print();
      }
      else {
         for (int row = 1; row <= b.size(); ++row) {
            if (b.safe(row, column)) { // pruning choices
               b.place(row, column);
               explore(b, column + 1);
               b.remove(row, column);
            }
         }
      }      
   }
}