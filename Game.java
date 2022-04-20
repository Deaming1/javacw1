package comp1721.cwk1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Game {
  private int Num;
  private String word;
  private List<String> str = new ArrayList<>();
  // TODO: Implement constructor with String parameter
  public Game(String file) throws IOException{
    LocalDate today = LocalDate.now();
    LocalDate start_date = LocalDate.of(2022, 4, 1);
    Period between_days = Period.between(start_date, today);
    int Number = between_days.getDays();
    WordList word_today = new WordList(file);
    word = word_today.getWord(Number);
    this.Num = Number;
  }

  // TODO: Implement constructor with int and String parameters
  public Game(int num, String file) throws IOException{
    Num = num;
    WordList word_specific = new WordList(file);
    word = word_specific.getWord(Num);
  }

  // TODO: Implement play() method
  void play(){
    System.out.println(word);
    int i;
    boolean symbol = false;
    System.out.println("WORDLE"+" "+String.format("%d",Num));
    System.out.println();
    for( i = 1; i <= 6; i++){
      Guess guess = new Guess(i);
      System.out.print(String.format("Enter guess (%d/6): ", i));
      guess.readFromPlayer();
      str.add(guess.compareWith(word));
      System.out.println(guess.compareWith(word));
      if(guess.matches(word)){
        symbol = true;
        break;
      }
    }
    if(symbol == true && i == 1){
        System.out.println("Superb - Got it in one!");
    }
    if(symbol == true && i > 1 && i < 6){
      System.out.println("Well done!");
  }
    if(symbol == true && i == 6){
      System.out.println("That was a close call!");
  }
    if(symbol == false){
      System.out.println("Nope - Better luck next time!");
    }

  }

  // TODO: Implement save() method, with a String parameter
  void save(String file) throws FileNotFoundException{
    PrintWriter savefile = new PrintWriter(file);
    int i;
    for( i = 0; i < str.size(); i++){
      savefile .println(str.get(i));
      
  }
  savefile.close();
}
}
