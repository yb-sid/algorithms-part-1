package week4.exercise;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href="https://coursera.cs.princeton.edu/algs4/assignments/8puzzle/specification.php">...</a>
 */
public class Solver {
    private boolean solvable;
    private int moves;
    private final Deque<Board> solutionPath;
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial){
        if(initial==null)
            throw new IllegalArgumentException("initial board can't be null");

        solvable = false;
        moves = -1;
        solutionPath = new ArrayDeque<>();

        // 1 create a twin board
        Board twinBoard = initial.twin();
        MinPQ<SearchNode> pq = new MinPQ<>();
        MinPQ<SearchNode> twinPq = new MinPQ<>();

        // add initial board to both min-heaps , use previous as null
        pq.insert(new SearchNode(initial, null, 0));
        twinPq.insert(new SearchNode(twinBoard, null, 0));

        aStarSeach(pq,twinPq);
    }

    private void aStarSeach(MinPQ<SearchNode> pq , MinPQ<SearchNode> twinPq){
        while(true){
            SearchNode current = pq.delMin();
            SearchNode twinCurrent = twinPq.delMin();

            // if goal is found
            if(current.board.isGoal()){
                solvable = true;
                moves = current.movesToReach;
                buildSolutionPath(current);
                break;
            }

            if(twinCurrent.board.isGoal()){
                break; // solvable remains false
            }

            for(Board neighbor : current.board.neighbors()){
                if(current.previous==null || !neighbor.equals(current.previous.board)){
                    pq.insert(new SearchNode(neighbor, current, current.movesToReach + 1));
                }
            }

            for(Board twinNeighbor : twinCurrent.board.neighbors()){
                if(twinCurrent.previous==null || !twinNeighbor.equals(twinCurrent.previous.board)){
                    twinPq.insert(new SearchNode(twinNeighbor, twinCurrent, twinCurrent.movesToReach + 1));
                }
            }
        }
    }

    private void buildSolutionPath(SearchNode node) {
        while (node != null) {
            solutionPath.addFirst(node.board);
            node = node.previous;
        }
    }


    // is the initial board solvable? (see below)
    public boolean isSolvable(){
        return solvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves(){
        return moves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution(){
        return isSolvable() ? solutionPath : null;
    }

    private static class SearchNode implements Comparable<SearchNode>{
        private final Board board;
        private final SearchNode previous;
        private final int movesToReach;
        private final int priority;
        SearchNode(Board board , SearchNode previous , int moves ){
            this.board = board;
            this.previous = previous;
            this.movesToReach = moves;
            this.priority = board.manhattan() + moves;
        }
        @Override
        public int compareTo(SearchNode o) {
            return Integer.compare(this.priority,o.priority);
        }
    }

    // test client (see below) 
    public static void main(String[] args){
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}