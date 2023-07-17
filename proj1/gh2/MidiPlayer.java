package gh2;

import java.util.Scanner;

/**
 * This code plays a MIDI music file specified by the user.
 * Requires completion of CS 61B Homework 1.
 *
 * @author Eli Lipsitz
 */
public class MidiPlayer {
    public static void main(String[] args) {
        System.out.println("Welcome to MIDI Player!");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter the path of the MIDI music file (or 'exit' to quit): ");
            String filePath = scanner.nextLine();

            if (filePath.equalsIgnoreCase("exit")) {
                break;
            }

            playMidi(filePath);
            System.out.println("Playback finished.\n");
        }

        System.out.println("Thank you for using MIDI Player. Goodbye!");
    }

    private static void playMidi(String filePath) {
        filePath = filePath.replace('\\', '/');
        filePath = filePath.trim().substring(1, filePath.length() - 1);  // trim the whitespaces and double quotes
        GuitarPlayer player = new GuitarPlayer(new java.io.File(filePath));
        player.play();

    }
}
