package json_client;

import com.google.gson.Gson;

public class Network {

    // Метод формирует строку в формате JSON из полученного в качестве аргумента листа.
    static String createJSON(Move move) {
        Gson g = new Gson();
        String jsonString = g.toJson(move);    // Метод toJson() приводит строку к типу JSON.

        System.out.println("\nСтрока в формате JSon, которая будет отправлена серверу:");
        System.out.println(jsonString);
        System.out.println();
        return jsonString;
    }
}
