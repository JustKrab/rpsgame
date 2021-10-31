package Game;

import java.util.ArrayList;

public class WhoWin {


    public static String whoWillWin(ArrayList<Object> arguments, Object playerMove, String pcMove) {
        int i1 = 0;
        int middle = arguments.size() / 2;

        String winner = null;
        for (int i = 0; i < arguments.size(); i++) {
            if (playerMove.equals(arguments.get(i))) {
                i1 = arguments.indexOf(playerMove) + 1;
            }
        }

        ArrayList<Object> looseAgainst = new ArrayList<>(middle);
        ArrayList<Object>winsAgainst = new ArrayList<>(middle);

        for (int i = i1; i < arguments.size(); i++) {
            if (looseAgainst.size() < middle)
                looseAgainst.add(arguments.get(i));
        }
        for (Object argument : arguments) {
            if (looseAgainst.size() < middle) {
                looseAgainst.add(argument);
            }
            if (!winsAgainst.contains(argument) && !looseAgainst.contains(argument))
                winsAgainst.add(argument);

        }

        winsAgainst.remove(playerMove);

        if (winsAgainst.contains(pcMove))
            winner = "You win!";
        else if (pcMove.equals(playerMove))
            winner = "Tie";
        else
            winner = "Computer wins";


        return winner;
    }

    public static boolean Check(Object playerMove, ArrayList<Object> arguments) {

        for (Object obj : arguments) {
            if (obj.equals(playerMove) || playerMove.equals("0") || playerMove.equals("?")|| playerMove.equals("exit")|| playerMove.equals("help")) {
                return true;
            }
        }
        return false;
    }


}
