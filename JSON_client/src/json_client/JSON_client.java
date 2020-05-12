package json_client;

import com.google.gson.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import static json_client.Network.*;

public class JSON_client {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Сканируем число, которое ввел пользователь.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номер порта 3222 или 2015:");
        int port = scanner.nextInt();

        Socket socket;
        socket = new Socket("localhost", port);

        InputStream sin = socket.getInputStream();
        OutputStream sout = socket.getOutputStream();

        DataInputStream in = new DataInputStream(sin);
        DataOutputStream out = new DataOutputStream(sout);

        String answer_string = "";

        while (!answer_string.equals("Игрок 1 победил.") && !answer_string.equals("Игрок 2 победил.")) {

            answer_string = in.readUTF();    // Считываем из потока строку в кодировке UTF-8 и записываем ее в переменную line.
            System.out.println("Ответ от сервера: ");
            System.out.println(answer_string);

            if((answer_string.equals("Очередь игрока 1.") && port == 3222) || (answer_string.equals("Очередь игрока 2.")&& port == 2015)) {

                System.out.println("Введите через пробел номер ряда и номер ячейки");
                int row = scanner.nextInt();
                int col = scanner.nextInt();

                Move move = new Move(row, col);
                move.port = port;

                // Формируем строку JSON из данных игрока, которые передаются в качестве аргумента.
                String move_string = createJSON(move);

                System.out.println("Отправка строки серверу...");
                System.out.println();
                out.writeUTF(move_string); // Отсылаем введенную строку текста серверу (записываем в поток строку в кодировке UTF-8).
                out.flush();        // Очистка буферов вывода. Байты записываются в место назначения. Поток прекращает передачу данных и данные сбрасываются в поток.
            }
        }
    }
}
