package comp1721.cwk1;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;


public class WordList {
  private List<String> words;//the array to get a list of words from file


  // TODO: Implement constructor with a String parameter
  public WordList(String file) throws IOException{
    this.words = Files.readAllLines(Paths.get(file));

  }

  // TODO: Implement size() method, returning an int
  public int size(){
    return words.size();
  }

  // TODO: Implement getWord() with an int parameter, returning a String
  public String getWord(int n){
    if(n<0 || n>words.size()-1){
      throw new GameException("invalid game number");
    }
    else {
      return words.get(n);
    }
  }
}
