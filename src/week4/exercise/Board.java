package week4.exercise;

import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final char[] tiles;
    private final int N;

    private int hamming = -1;
    private int manhatten = -1;
    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles){
        if(tiles==null)
            throw new IllegalArgumentException("Tiles can't be null");
        N = tiles.length;
        this.tiles = new char[N*N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                this.tiles[i*N+j] = (char)tiles[i][j];
            }
        }
    }
                                           
    // string representation of this board
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(N).append("\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(" ").append((int) tiles[i * N + j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // board dimension n
    public int dimension(){
        return N;
    }

    // number of tiles out of place
    public int hamming(){
        if(hamming!=-1) return hamming;
        hamming = 0;

        for (int i = 0; i < N*N; i++) {
            if (tiles[i] != 0 && tiles[i] != i + 1) {
                hamming++;
            }
        }
        return hamming;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan(){
        if(manhatten!=-1)return manhatten;
        manhatten = 0;
        for (int i = 0; i < N*N; i++) {
            if (tiles[i] != 0) {
                int expectedRow = (tiles[i] - 1) / N;
                int expectedCol = (tiles[i] - 1) % N;
                int currentRow = i / N;
                int currentCol = i % N;
                manhatten += Math.abs(currentRow - expectedRow) + Math.abs(currentCol - expectedCol);
            }
        }
        return manhatten;
    }

    // is this board the goal board?
    public boolean isGoal(){
        return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y){
        if(this == y){
            return true;
        }

        if(y==null || this.getClass() != y.getClass())
            return false;

        Board other = (Board)y;
        if(other.N != this.N)
            return false;

        for(int i=0;i<N*N;i++){
                if(this.tiles[i]!=other.tiles[i]){
                    return false;
                }
        }

        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors(){

        List<Board> neighborBoards = new ArrayList<>();
        int zeroIndex = 0;

        // Find the index of the blank (zero) tile
        for (int i = 0; i < N*N; i++) {
            if (tiles[i] == 0) {
                zeroIndex = i;
                break;
            }
        }

        // Generate neighboring boards by swapping the blank tile with its neighbors
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int zeroRow = zeroIndex / N;
        int zeroCol = zeroIndex % N;
        for (int[] dir : directions) {
            int newRow = zeroRow + dir[0];
            int newCol = zeroCol + dir[1];
            if (newRow >= 0 && newRow < N && newCol >= 0 && newCol < N) {
                int newIndex = newRow * N + newCol;
                char[] newTiles = tiles.clone();
                char temp = newTiles[zeroIndex];
                newTiles[zeroIndex] = newTiles[newIndex];
                newTiles[newIndex] = temp;
                neighborBoards.add(new Board(convertTo2D(newTiles)));
            }
        }

        return neighborBoards;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin(){
        char[] twinTiles = tiles.clone();
        int row1 = 0, col1 = 0;
        int row2 = 1, col2 = 1;


        while (twinTiles[row1 * N + col1] == 0) {
            col1 = (col1 + 1) % N;
            if (col1 == 0) {
                row1++;
            }
        }
        while (twinTiles[row2 * N + col2] == 0 || (row2 == row1 && col2 == col1)) {
            col2 = (col2 + 1) % N;
            if (col2 == 0) {
                row2++;
            }
        }

        // swap (row1,col1) , with (row2,col2)
        char temp = twinTiles[row1 * N + col1];
        twinTiles[row1 * N + col1] = twinTiles[row2 * N + col2];
        twinTiles[row2 * N + col2] = temp;

        return new Board(convertTo2D(twinTiles));

    }

    private int[][] convertTo2D(char[] tiles1D) {
        int[][] tiles2D = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tiles2D[i][j] = tiles1D[i * N + j];
            }
        }
        return tiles2D;
    }

    public static void main(String[] args){
        int[][] testTiles = {{0,1,3},{4,2,5},{7,8,6}};
        Board board = new Board(testTiles);

        StdOut.println("week4.exercise.Board is \n"+board);
        StdOut.println("week4.exercise.Board Dimension is : "+board.dimension());
        StdOut.println("week4.exercise.Board hamming distance = "+board.hamming());
        StdOut.println("week4.exercise.Board manhattan distance = "+board.manhattan());
        StdOut.println("Is week4.exercise.Board Goal ? "+board.isGoal());

        int[][] goalTiles = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };
        StdOut.println("Goal week4.exercise.Board is :");
        Board goalBoard = new Board(goalTiles);
        StdOut.println(goalBoard);
        StdOut.println("check if goalBoard is goal ? "+goalBoard.isGoal());

        StdOut.println("week4.exercise.Board Neighbors : ");
        for(Board neighbor : board.neighbors()){
            StdOut.println(neighbor);
        }

        StdOut.println("week4.exercise.Board twin is : ");
        StdOut.println(board.twin());
    }

}