
package lab2;

/**
 *
 * @author Bogdan Kovalov
 * @since 9/7/2020
 * @version 1.0
 */
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

public class SongList {


   public static void main(String[] args) throws Exception {

       //Declaring a scanner that reads in the song file.
       Scanner fileReader = new Scanner (new File ("regional-global-daily-latest.csv"));
       
       //Declaring a printsream object so that my output goes towards a file.
       PrintStream ps = new PrintStream ("Artists-WeekOf09-07-2020.txt");
     
       String line="";
       
       //Declaring Array for the names of the artists with the max amount of 500.
       String[] nameOfArtists = new String[500];
       //Declaring Array for the number of artists with the max amount of 500.
       int[] numberOfArtists = new int[500];

       //Counter that shows how many different artists were found in the streaming list.
       int unrepeatedArtistCount=0;
       
       
       for(int i=0;i<numberOfArtists.length;i++){
           numberOfArtists[i] = 0;
       }
       int songCount=0;

       
          //Scanner reads in line by line from the file.
           while(fileReader.hasNextLine()){
               songCount++;
               //read line
               line = fileReader.nextLine();
               String columns[] = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

               //From the file Artist Column is found in  index position 2
               String artistPosition = columns[2];
            

               //replacing quotes from the line.
               
                artistPosition = artistPosition.replaceAll("\"", "");
               for(String artist : artistPosition.split(",")){
                   boolean found = false;
                   for(int i=0;i<unrepeatedArtistCount;i++){
                       
                       // Seeing if an artist appears more than once.
                       // If so the counter for numberofArtists goes up.
                       if(artist.equalsIgnoreCase(nameOfArtists[i])){
                           numberOfArtists[i]++;
                           found = true;
                           break;
                       }
                   }
                   
                   //This if loop sees if there is any repeated artists.
                   if(!found){
                       nameOfArtists[unrepeatedArtistCount] = artist;
                       numberOfArtists[unrepeatedArtistCount]=1;
                       unrepeatedArtistCount++;
                   }
               }
           }
     
           
       //Print statements which prints to the screen and one prints to a file.
      
       System.out.println("Total Amount of Songs Found: : "+songCount);
       ps.println("Total Amount of Songs Found: "+songCount);
       System.out.println("Number Of Unrepeated Artists : "+unrepeatedArtistCount);
       ps.println("Number of Unrepeated Artists "+ unrepeatedArtistCount);
       System.out.printf("%-25s%s\n","Artist","Times Appeared");
       ps.printf("%-25s%s\n","Artist","Times Appeared");
       
       for(int i=0;i<unrepeatedArtistCount;i++){
           System.out.printf("%-25s%s\n",nameOfArtists[i],numberOfArtists[i]);
           ps.printf("%-25s%s\n",nameOfArtists[i],numberOfArtists[i]);
       }
       fileReader.close();
   } //end main method


} //end SongList class