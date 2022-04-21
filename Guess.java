package comp1721.cwk1;
import java.util.Scanner;


public class Guess {
  private int guessNumber;
  private String chosenWord;
  // Use this to get player input in readFromPlayer()
  private static final Scanner INPUT = new Scanner(System.in);

  // TODO: Implement constructor with int parameter
  // get guessNumber
  public Guess(int num){
    if (num < 1 || num >6)
      throw new GameException("The number is out of range!");
    else
      guessNumber = num;
  }

  // TODO: Implement constructor with int and String parameters
  // get guessNumber and word
  public Guess(int num, String word){
    this(num);
    boolean symbol=true;
    for(int i=0;i<word.length();i++){
      char tst=word.charAt(i);
      if(Character.isDigit(tst)){
        symbol=false;
      }
    }
    if(word.length() != 5 || !symbol)
      throw new GameException("The word is not in valid format!");
    else
      chosenWord = word.toUpperCase();
  }

  // TODO: Implement getGuessNumber(), returning an int
  public int getGuessNumber() {
    return guessNumber;
  }

  // TODO: Implement getChosenWord(), returning a String
  public String getChosenWord() {
      return chosenWord;
  }

  // TODO: Implement readFromPlayer()
  public void readFromPlayer(){
    chosenWord = INPUT.nextLine();
    chosenWord = chosenWord.toUpperCase();
  }

  // TODO: Implement compareWith(), giving it a String parameter and String return type
  // in ColorList, 0 represents white, 1 represents yellow and 2 represents green
  public String compareWith(String target){
    int[] wordColor = new int[]{0,0,0,0,0};
    int[] recordColor = new int[]{0,0,0,0,0};
    String return_String = ""; //a string to store the final result and for return
    for(int i = 0; i < target.length(); i++){
      for(int j = 0; j < chosenWord.length(); j++){

        if(target.charAt( i) == chosenWord.charAt(j) && i != j){
          if(recordColor[i]!=1 && recordColor[i]!=2 && wordColor[j] !=2 ) { //already been matched characters
            wordColor[j] = 1;
            recordColor[i] = 1;
          }
        }
        if(i == j && target.charAt(i) == chosenWord.charAt(j)){
          recordColor[i] = 2;
          wordColor[j] = 2;
        }
      }
    }
    for(int n = 0; n < 5; n++){
      String tmp = "";//to store the character temporarily
      if(wordColor[n] == 2){
        tmp = String.format("\033[30;102m %c \033[0m", chosenWord.charAt(n));
        return_String += tmp;
      }
      if(wordColor[n] == 1){
        tmp = String.format("\033[30;103m %c \033[0m", chosenWord.charAt(n));
        return_String += tmp;
      }
      if(wordColor[n] == 0){
        tmp = String.format("\033[30;107m %c \033[0m", chosenWord.charAt(n));
        return_String += tmp;
      }
    }
    return return_String;
  }

  // TODO: Implement matches(), giving it a String parameter and boolean return type
  public boolean matches(String word){
    return chosenWord.equals(word);
}
}
