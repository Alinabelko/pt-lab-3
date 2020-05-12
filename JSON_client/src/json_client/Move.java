package json_client;

public class Move {

    public Integer[] move = new Integer[2];
    public int port;

    Move(int row, int col) {
        move[0] = row;
        move[1] = col;
    }
}
