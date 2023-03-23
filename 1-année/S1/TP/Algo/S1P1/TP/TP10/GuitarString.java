import java.util.Random;
import java.util.Scanner;
/**
 * Created by zulupero on 01/12/15.
 */
public class GuitarString {
    static final Scanner input = new Scanner(System.in);
    final Random RAND = new Random();
    final double ATTN = 0.994 ;
    int toc ;
    RingBuffer buf ;

    /**
     * Constructeur. Alloue un RingBuffer de capacité Fe/freq
     * @param freq
     */
    public GuitarString(double freq){
        int N = (int) Math.round(StdAudio.SAMPLE_RATE / freq);
        buf = new RingBuffer(N);
        toc = 0;
    }
    

    /**
     * Initialise le buffer avec du bruit blanc
     */
    public void pluck(){
        while(!buf.isFull()){
            buf.enqueue(RAND.nextDouble() - 0.5);

        }
    }

    /**
     * execute un pas de simulation
     */
    public void tic(){
        double note = buf.dequeue();
        double apres = buf.pick();
        buf.enqueue(0.5 * (note + apres) * ATTN);
        toc++;
    }

    public void sample(){
        StdAudio.play(buf.pick());
    }

    public int time(){
        return toc;
    }


    public static void main(String[] args) {
        double f0 = 0;
        String[] notes = {"Do", "Do#", "Re", "Re#", "Mi", "Fa", "Fa#", "Sol", "Sol#", "La", "La#", "Si"};
        double[] freq = {523.25, 554.37, 587.33, 622.25, 659.25, 698.46, 739.99, 783.99, 830.61, 880.00, 932.33, 987.77};
        for (int i = 0; i < notes.length; i++) {
            System.out.print("[" + notes[i] + "] ");
        }

        /*System.out.println("\nEntrez le nom de la note");

        String note = input.nextLine();
        for (int i = 0; i < notes.length; i++) {
            if (note.equals(notes[i])){
                f0 = freq[i];
            }
        }
        
        GuitarString gs = new GuitarString(f0);
        gs.pluck();*/

        String clavier = "azertyuiopqs";
        GuitarString[] maGuitare = new GuitarString[clavier.length()];
        for (int i = 0; i < clavier.length(); i++) {
            maGuitare[i] = new GuitarString(freq[i]);
            maGuitare[i].pluck();
        }
        while(true){
            double sample = 0;
            for (int i = 0; i < clavier.length(); i++) {
                if (StdDraw.hasNextKeyTyped()){

                    char key = StdDraw.nextKeyTyped();
                    if (key == clavier.charAt(i)){
                        sample += maGuitare[i].buf.pick();
                    }
                }
            }
            StdAudio.play(sample);

            }
        }
    }
