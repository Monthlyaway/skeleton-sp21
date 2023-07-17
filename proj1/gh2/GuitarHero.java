package gh2;
import edu.princeton.cs.introcs.StdAudio;
import edu.princeton.cs.introcs.StdDraw;

public class GuitarHero {
    public static void main(String[] args) {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

        // Create an array of 37 GuitarString objects
        GuitarString[] strings = new GuitarString[37];
        for (int ix = 0; ix < 37; ix++) {
            double frequency = 440.0 * Math.pow(2, (ix - 24) / 12.0);
            strings[ix] = new GuitarString(frequency);
        }

        while (true) {
            // Check if a key is pressed
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);

                // Play the corresponding note if a valid key is pressed
                if (index != -1) {
                    strings[index].pluck();
                } else {
                    continue;
                }
            }

            // Compute the superposition of all the samples
            double sample = 0;
            for (GuitarString string : strings) {
                sample += string.sample();
            }

            // Play the sample sound
            StdAudio.play(sample);

            // Advance the simulation of each guitar string
            for (GuitarString string : strings) {
                string.tic();
            }
        }
    }
}
