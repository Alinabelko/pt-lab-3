package json_server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class JSON_server {

    public static void main(String[] args) throws IOException {

        Move move1;
        Move move2;

        Field field = new Field();

        // Создаем 2 сокета для коммуникации с от игроками. В скобках указывается номер ожидаемого порта.
        MySocket MS1 = new MySocket(3222);
        MySocket MS2 = new MySocket(2015);

        int playerWon = 0;
        int playerTurn = 1;
        String line;

        for (int i = 0; i < 9; i++) {
            if(playerTurn == 1){
                line = "Очередь игрока 1.";
                MS1.setLine(line);
                MS2.setLine(line);
                MS1.sendLine();
                MS2.sendLine();

                MS1.recieveMessage();
                move1 = Network.JSONinFighter(MS1.getLine());
                field = field.makeMove(field, move1);
                line = field.fieldString(field);
                MS1.setLine(line);
                MS2.setLine(line);
                MS1.sendLine();
                MS2.sendLine();
                playerWon = field.playerWon(field);
                if(playerWon != 0){
                    line = "Игрок " + playerWon + " победил.";
                    MS1.setLine(line);
                    MS2.setLine(line);
                    MS1.sendLine();
                    MS2.sendLine();
                    break;
                }
                playerTurn = 2;
            }
            if(playerTurn == 2){
                line = "Очередь игрока 2.";
                MS1.setLine(line);
                MS2.setLine(line);
                MS1.sendLine();
                MS2.sendLine();

                MS2.recieveMessage();
                move2 = Network.JSONinFighter(MS2.getLine());
                field = field.makeMove(field, move2);
                line = field.fieldString(field);
                MS1.setLine(line);
                MS2.setLine(line);
                MS1.sendLine();
                MS2.sendLine();
                playerWon = field.playerWon(field);
                if(playerWon != 0){
                    line = "Игрок " + playerWon + " победил.";
                    MS1.setLine(line);
                    MS2.setLine(line);
                    MS1.sendLine();
                    MS2.sendLine();
                    break;
                }
                playerTurn = 1;
            }
        }

        // Вызываем методы для закрытия потоков.
        MS1.finishWork();
        MS2.finishWork();
    }
}