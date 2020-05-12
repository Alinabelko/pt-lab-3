package json_server;

public class Field {

    private Integer[][] field = new Integer[3][3];

    Field(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = 0;
            }
        }
    }

    public Field makeMove(Field field, Move move){
        if (move.port == 3222 ){
            field.field[move.move[0]][move.move[1]] = 1;
        }
        if (move.port == 2015 ){
            field.field[move.move[0]][move.move[1]] = 2;
        }
        return field;
    }

    String fieldString(Field field){
        String string = "";
        for (int i = 0; i < 3; i++) {
            string += "_________"+'\n';
            for (int j = 0; j < 3; j++) {
                switch (field.field[i][j]){
                    case 0:
                        string += "| ";
                        break;
                    case 1:
                        string += "|X";
                        break;
                    case 2:
                        string += "|O";
                        break;
                }
            }
            string += "|"+'\n';
        }
        string += "_________"+'\n';
        return string;
    }

    int playerWon(Field field){
        for (int i = 0; i < 3; i++) {
            if((field.field[i][0] != 0)&&(field.field[i][0] == field.field[i][1]) &&  (field.field[i][0] == field.field[0][2])){
                return field.field[i][0];
            }
            if((field.field[0][i] != 0)&&(field.field[0][i] == field.field[1][i]) &&  (field.field[0][i] == field.field[2][i])){
                return field.field[0][i];
            }
        }
        if((field.field[0][0] != 0)&&(field.field[0][0] == field.field[1][1])&&(field.field[0][0] == field.field[2][2])){
            return field.field[0][0];
        }
        if((field.field[0][2] != 0)&&(field.field[0][2] == field.field[1][1])&&(field.field[0][2] == field.field[2][0])){
            return field.field[0][2];
        }
        return 0;
    }
}
