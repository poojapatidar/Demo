import java.io.*;

 
class GuessWord
{
	
      public static int ReadWordsFromFile(String[] words)
      {
    	 
        try
            {
                  FileReader fr = new FileReader("words_input.txt");
                  BufferedReader br = new BufferedReader(fr);
                  int count = 0;
                  for (int i = 0; i < 100; i++)
                  {
                        String s = br.readLine();
                        if (s == null)
                              break;
                        words[count++] = s;
                  }
                  fr.close();
                  return count;
            }
            catch (FileNotFoundException e)
            {
                  System.out.println(e.getMessage());
                  return -1;
            }
            catch (IOException err)
            {
                  System.out.println(err.getStackTrace());
                  return -1;
            }
        }
 
      static public String ReadString()
      {
            try
            {
                  String inpString = "";
                  InputStreamReader input = new InputStreamReader(System.in);
                  BufferedReader reader = new BufferedReader(input);
                  return reader.readLine();
            }
            catch (Exception e)
            {
                  e.printStackTrace();
            }
            return "";
      }
 
      public static void main(String[] args) throws IOException
      {
    	  char res=0;
  		do{
    	    System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::");
	        System.out.println("                 GUESS THE WORD                       ");
	        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::");
	        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            String[] words = new String[100];
            
            int count = ReadWordsFromFile(words);
            if (count < 0)
            {
                  System.out.println("No words found in the file");
                  return;
            }
 
            int x = (int)(Math.random() * 100);
            int guessX = (x % count);
 
            String secretWord = words[guessX];
 
            int numChars = secretWord.length();
 
            System.out.print("Your secret word is: ");
            //System.out.println("______________________________________________________");
            for(int i = 0; i < numChars; i++)
                  System.out.print("*");
            System.out.println();
 
            boolean bGuessedCorrectly = true;
            System.out.println("Guess now  (To stop the program, enter #) : ");
            String choicelist = "";
            
            while (true)
            {
               bGuessedCorrectly = true;
               String choice = ReadString();
               
               if (choice.compareTo("#") == 0)
               {
                  bGuessedCorrectly = false;
                  break;
               }
               
               choicelist += choice;
               if(choicelist.length() > 20)
                  choicelist = choicelist.substring(0,9);
               
               boolean bResult = false;
               String dispstr = "";
               for (int i = 0; i < numChars; i++)
               {
                  boolean bCorrect = false;
                  for(int j = 0; j < choicelist.length(); j++)
                  {
                     if(choice.indexOf(secretWord.charAt(i)) >= 0)
                        bResult = true;
                     if (secretWord.charAt(i) == choicelist.charAt(j))
                     {
                        dispstr += secretWord.charAt(i);
                        bCorrect = true;
                        break;
                     }  
                  }
                  if(bCorrect == false)
                  {
                     dispstr += "*";
                     bGuessedCorrectly = false;
                  }
               }
               if(bResult == true)
               {
                  System.out.format("Correct: %s", dispstr);
                  //System.out.println("______________________________________________________");
               }
               else
               {
                  System.out.format("Wrong: %s", dispstr);
                  System.out.println("______________________________________________________");
               }
               
               if(bGuessedCorrectly == true)
                     break;
               System.out.println();
               System.out.print("Guesses : ");
               for(int i = 0; i < choicelist.length(); i++)
                  System.out.format("%c ", choicelist.charAt(i));
               System.out.println();
               System.out.println();
                  
               if(choicelist.length() >= 20)
               {
                  System.out.print("You have reached maximum amount of guesses.");
                  System.out.println("______________________________________________________");
                  bGuessedCorrectly = false;
                  break;
               }
            }
            
            if (bGuessedCorrectly == false)
            {
                  System.out.println("\nUnfortunately you did not guess it correctly. The secret word is: " + secretWord);
                  System.out.println("______________________________________________________");
            }else
            {
                  System.out.println("\nCongrats! You have guessed it correctly");  
                  System.out.println("______________________________________________________");
            }
            System.out.println("Do you want to play Again(y/n)");
        	res = (char) input.read();
        	System.out.println("______________________________________________________");
      }while(res=='y');
  		System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println("                     THANK YOU                        ");
		System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::");
      }
     
}
    