import java.io.*;
import java.util.*;
import java.util.Scanner;

public class Trie {
  private static String [] topWords = new String[10];
  private static int [] counts = new int[10];

  char c;
  Trie[] children;
  boolean isWord;
  int wordCount;
  String word;

  public Trie(){
    this.c = 0;
    this.children = new Trie[26];
    this.isWord = false;
    this.wordCount = 0;
    this.word = "";
  }

  public String normalize(String s){
    String newString = "";
    for (int i = 0; i < s.length(); i++){
      char n = s.charAt(i);
      if (n >= 'A' && n <= 'Z'){
        newString += Character.toLowerCase(n);
      } else if (n >= 'a' && n <= 'z'){
        newString += n;
      }
    }
    return newString;
  }

  public void add(String addWord) {
    String s = normalize(addWord);

    if(s.length() == 0) {
      this.isWord = true;
      this.wordCount++;
      return;
    }

    char letter = s.charAt(0);
    int index = (int)letter - 97;

    if(this.children[index] == null) {
      this.children[index] = new Trie();
      this.children[index].c = letter;
      this.children[index].word = this.word + s.charAt(0);
    }
    this.children[index].add(s.substring(1));
  }
  
  public static void visit(Trie trie) {
    if(trie != null) {
      if(trie.isWord){
        String tempWord = trie.word;
        compare(trie.wordCount, tempWord, counts, topWords);
        trie.isWord = false;
      }
    }
    for (int i = 0; i < 26; i++){
      if(trie.children[i] != null){
        visit(trie.children[i]);
      }
    }
  }
  
  public static void compare(int value, String word, int[] counts, String[] topWords) {
    int[] tempInt = new int[11];
    String[] tempString = new String[11];
    for(int i = 0; i < counts.length; i++){
      if(counts[i] >= value){
        tempInt[i] = value;
        tempString[i] = word;
        word = topWords[i];
        value = counts[i];
      } else {
        tempInt[i] = counts[i];
        tempString[i] = topWords[i];
        
      }
    }
    tempInt[10] = value;
    tempString[10] = word;
    for ( int i = 0; i < counts.length; i++){
      counts[i] = tempInt[i+1];
      topWords[i] = tempString[i+1];
    }
  }

  

  public static void main(String[] args) throws IOException {
    Trie trie = new Trie();
    Scanner s = null;

    try {
      s = new Scanner(System.in);
      while (s.hasNext()) {
        String word = s.next(); // Scanner splits input on whitespace, by default
        trie.add(word);
      }

    } finally {
      if (s != null) {
        s.close();
      }
    }
    visit(trie);
    for (int i = 0; i < 10; i++){
      System.out.println(trie.topWords[i] + " | " + trie.counts[i]);
    }
  }
}
