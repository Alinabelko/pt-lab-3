package json_server;

import com.google.gson.Gson;

public class Network {

    // Метод формирует строку в формате JSON из полученного в качестве аргумента листа.
    static String createJSON(Move move) {
        Gson g = new Gson();
        String jsonString = g.toJson(move);    // Метод toJson() приводит строку к типу JSON.

        System.out.println("\nТут будет вывод из класса JSon:");
        System.out.println(jsonString);
        return jsonString;
    }

    // Метод, наоборот, из формата JSON формирует лист класса Move и перед возвратом листа выводит его в консоль.
    static Move JSONinFighter(String jsonString) {
        Gson g = new Gson();
        Move move_j = g.fromJson(jsonString, Move.class);  // Метод fromJson() преобразует строку jsonString из формата JSON в класс Gamer.
        return move_j;
    }
}
