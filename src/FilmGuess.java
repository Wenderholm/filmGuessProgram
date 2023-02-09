import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FilmGuess {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("text.txt");
        Scanner sc = new Scanner(file);
        Scanner keyboard = new Scanner(System.in);

        ArrayList<String> film = new ArrayList<>();
        while (sc.hasNextLine()){
            film.add(sc.nextLine());
        }

        int randLine = (int) (Math.random() * film.size());
        String secretWord = film.get(randLine);
        List<Character> playerGuess = new ArrayList<>();

        int wrongCount = 0;


        printSecretWord(secretWord, playerGuess,wrongCount);
        while(true){

            if(wrongCount >=5){
                System.out.println("You lost");
                break;
            }

            if(!playerGuess(keyboard, secretWord, playerGuess)){
                wrongCount++;
                System.out.println("You are guessed ("+wrongCount+") wrong letters: ");
            }
            if(printSecretWord(secretWord, playerGuess,wrongCount)){
                System.out.println("You win!");
                break;
            }

//            System.out.println(playerGuess);

            for (int i = 0; i < playerGuess.size(); i++) {
                if(!secretWord.contains(String.valueOf(playerGuess.get(i)))){
                    System.out.print(playerGuess.get(i) + " ");
                }
            }

        }
    }

    private static boolean playerGuess(Scanner keyboard, String secretWord, List<Character> playerGuess) {

        System.out.println();
        System.out.print("Enter a letter:");
        String letterGuess = keyboard.nextLine();
        playerGuess.add(letterGuess.charAt(0));

        return secretWord.contains(letterGuess);
    }

    private static boolean printSecretWord(String secretWord, List<Character> playerGuess, int wrong) {
        int correct = 0;

        System.out.print("You are guessing:");
        for (int i = 0; i < secretWord.length() ; i++) {
            if(playerGuess.contains(secretWord.charAt(i))){
                System.out.print(secretWord.charAt(i));
                correct++;

            }else {
                System.out.print("_");
            }
        }
        System.out.println();
        System.out.print("You are guessed ("+wrong+") wrong letters: ");
        return (secretWord.length() == correct);
    }
}