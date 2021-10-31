package Game;

import com.github.freva.asciitable.AsciiTable;


import java.util.ArrayList;

public class Table {


    public static void ShowTable(ArrayList<Object> arguments) {
        String[] headers = new String[arguments.size() + 1];

        int sqrt = arguments.size() / 2;
        headers[0] = "PC\\USER";
        for (int i = 1; i < arguments.size() + 1; i++) {
            headers[i] = (String) arguments.get(i - 1);
        }
        String[][] data = new String[arguments.size() + 2][arguments.size() + 2];
        for (int i = 0; i < arguments.size(); i++) {
            for (int j = 1; j < arguments.size() + 1; j++) {
                data[i][0] = (String) arguments.get(i);
                if (data[i][0].equals(headers[j])) {
                    data[i][j] = "DRAW";
                    int k=j;
                    int b=1;
                    for (int s = 0; s < sqrt; s++) {

                        if (k < arguments.size()) {
                            data[i][k + 1] = "WIN";
                            k++;
                        }
                        else{ data[i][b] = "WIN";b++;}
                    }
                }
                if (data[i][j]==null){
                    data[i][j]="LOOSE";
                }
            }
        }
        System.out.println(AsciiTable.getTable(headers, data));

    }
}
