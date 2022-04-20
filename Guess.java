package comp1721.cwk1;
import java.util.Scanner;


public class Guess {
  private int guessNumber;
  private String chosenWord;
  // Use this to get player input in readFromPlayer()
  private static final Scanner INPUT = new Scanner(System.in);

  // TODO: Implement constructor with int parameter
  public Guess(int num){
    if (num < 1 || num >6)
      throw new GameException("The number is out of range!");
    else
      guessNumber = num;

  }

  // TODO: Implement constructor with int and String parameters
  public Guess(int num, String word){
    this(num);
    boolean symbol=true;
    for(int i=0;i<word.length();i++){
      char tst=word.charAt(i);
      if(Character.isDigit(tst)){
        symbol=false;
      }
    }
    if(word.length() != 5 || symbol==false)
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
  public String compareWith(String target){
    char[] wordColor = new char[]{'0','0','0','0','0'};
    char[] recordColor = new char[]{'0','0','0','0','0'};
    String return_String = "";
    for(int i = 0; i < target.length(); i++){
      for(int j = 0; j < chosenWord.length(); j++){

        if(target.charAt( i) == chosenWord.charAt(j) && i != j){
          if(recordColor[i]!='1' && recordColor[i]!='2' && wordColor[j] !='2' ) {
            wordColor[j] = '1';
            recordColor[i] = '1';
          }
        }
        if(i == j && target.charAt(i) == chosenWord.charAt(j)){
          recordColor[i] = '2';
          wordColor[j] = '2';
        }
      }
    }
    
    for(int i = 0; i < 5; i++){
      String tmp = "";
      if(wordColor[i] == '2'){
        tmp = String.format("\033[30;102m %c \033[0m", chosenWord.charAt(i));
        return_String += tmp;
      }
      if(wordColor[i] == '1'){
        tmp = String.format("\033[30;103m %c \033[0m", chosenWord.charAt(i));
        return_String += tmp;
      }
      if(wordColor[i] == '0'){
        tmp = String.format("\033[30;107m %c \033[0m", chosenWord.charAt(i));
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
