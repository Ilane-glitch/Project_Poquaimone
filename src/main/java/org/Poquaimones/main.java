package org.Poquaimones;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        //instanciation
        Scanner sc = new Scanner(System.in);
        world w = new world();

        System.out.println(" nouvelle partie (1) ou charger une partie ? (2)");
        int choice = sc.nextInt();
        switch (choice){
            case 1:
                w.cycle();
                break;
            case 2:
                w = w.LOAD();
                w.cycle();
                break;
        }
    }

}
