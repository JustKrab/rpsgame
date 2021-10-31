package Game;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Mac;


import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static Game.Table.ShowTable;
import static Game.WhoWin.Check;
import static Game.WhoWin.whoWillWin;
import static Game.GenerateKey.*;


public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, KeyStoreException {

        ArrayList<Object> arguments = new ArrayList<Object>(Arrays.asList(args));
        ConditionsCheck(arguments);


        byte[] key = generateSecureRandomKey();

        String pcmove = getRandomElement(arguments).toString();


        Mac hmackey = hmac(key);
        Mac hmacpcmove = hmac(pcmove.getBytes());

        byte[] hexkey = hmackey.doFinal(key);
        byte[] hexpcmove = hmacpcmove.doFinal(pcmove.getBytes(StandardCharsets.UTF_8));
        System.out.println("HMAC:\n" + bytesToHex(hexpcmove));

        ShowMenu(arguments, pcmove);

        System.out.println("HMAC key:\n" + bytesToHex(hexkey));

    }

    public static Object getRandomElement(ArrayList<Object> arguments) {

        SecureRandom rand = new SecureRandom();

        return arguments.get(rand.nextInt(arguments.size()));
    }

    public static void ConditionsCheck(ArrayList<Object> arguments){
        if (arguments.size() < 3) {
            System.out.println("Need more arguments");
            System.exit(1);
        }
        if (arguments.size()%2 == 0) {
            System.out.println("Need an odd number");
            System.exit(1);
        }
        for (int i = 0; i < arguments.size(); i++) {
            Object e1 = arguments.get(i);
            if (e1 == null) continue;
            for (int j = 0; j < arguments.size(); j++) {
                if (i == j) continue;
                Object e2 = arguments.get(j);
                if (e1.equals(e2)) {
                    System.out.println("Duplicates arguments");
                    System.exit(1);
                }
            }
        }
    }

    public static void ShowMenu(ArrayList<Object> arguments, String pcMove) {
        System.out.println("Available moves: ");

        for (int i = 0; i < arguments.size(); i++) {
            System.out.println(String.format("%d: %s", i + 1, arguments.get(i)));

            if (i == arguments.size() - 1) {
                System.out.println("0 - exit");
                System.out.println("? - help");
            }
        }

        Scanner console = new Scanner(System.in);

        System.out.print("Your move? ");

        Object playerMove = console.nextLine();

        boolean isChecked = Check(playerMove, arguments);

        while (true) {
            if (isChecked) {
                if (playerMove.equals("0") || playerMove.equals("exit")) {
                    System.out.println("By");
                    System.exit(1);
                }
                if (playerMove.equals("?") || playerMove.equals("help")) {
                    System.out.println("Help");
                    ShowTable(arguments);
                    System.out.print("Your move? ");
                    playerMove = console.nextLine();
                    isChecked = Check(playerMove, arguments);
                }
                System.out.printf("Your moves: %s \n", playerMove);
                System.out.printf("Computer moves: %s \n", pcMove);
                break;
            } else {
                System.out.println("Try again");
                playerMove = console.nextLine();
                isChecked = Check(playerMove, arguments);
            }
        }
        System.out.println(whoWillWin(arguments, playerMove, pcMove));


    }


}


