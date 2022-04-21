package comp1721.cwk1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Game {
  private int Num;// Game number
  private String target;//string for word
  private List<String> str = new ArrayList<>();//The array of string to record the result of every guesses
  // TODO: Implement constructor with String parameter
  // To start today's game
  // set the start data as 2022/4/1
  // and the number of the word is the difference value of current date and start date
  // This function will get the word and number
  public Game(String file) throws IOException{
    LocalDate Today = LocalDate.now();
    LocalDate Start_date = LocalDate.of(2022, 4, 1);
    Period Between_days = Period.between(Start_date, Today);
    int Number = Between_days.getDays();
    WordList Word_today = new WordList(file);
    target = Word_today.getWord(Number);
    this.Num = Number;
  }

  // TODO: Implement constructor with int and String parameters
  // set the number equals to the users' input
  // select the word from wordList read from the datafile
  public Game(int num, String file) throws IOException{
    Num = num;
    WordList word_specific = new WordList(file);
    target = word_specific.getWord(Num);
  }

  // TODO: Implement play() method
  void play(){
    System.out.println(target); // for test
    boolean symbol = false;  // check the match
    System.out.printf("WORDLE\t%d\n",Num);
    int n;
    for(n = 1; n <= 6; n++){
      Guess game = new Guess(n);
      System.out.printf("Enter guess (%d/6): \n", n);
      game.readFromPlayer();
      str.add(game.compareWith(target));
      System.out.println(game.compareWith(target));
      if(game.matches(target)){
        symbol = true;
        break;
      }
    }
    //some outputs
    if(symbol && n == 1){
        System.out.println("Superb - Got it in one!");
    }
    if(symbol && n > 1 && n < 6){
      System.out.println("Well done!");
  }
    if(symbol && n == 6){
      System.out.println("That was a close call!");
  }
    if(!symbol){
      System.out.println("Nope - Better luck next time!");
    }

  }

  // TODO: Implement save() method, with a String parameter
  // save the last game solution
  void save(String file) throws FileNotFoundException{
    PrintWriter savefile = new PrintWriter(file);
    int i;
    for( i = 0; i < str.size(); i++){
      savefile .println(str.get(i));
      
  }
  savefile.close();
}
}
