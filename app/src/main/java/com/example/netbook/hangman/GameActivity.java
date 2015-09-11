package com.example.netbook.hangman;

import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends ActionBarActivity {

    private String[] words;
    private ImageView hangmanImage;
    private int lifes;
    char[] charText;
    private String randomWord;
    private char[] charTextCheck;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //initialize the image
        hangmanImage = (ImageView) findViewById(R.id.HangmanImage);


        // Initialize a random word from the stings.xml folder
        words = getResources().getStringArray(R.array.words);
        TextView randomText = (TextView) findViewById(R.id.display_word);
        randomWord = words[randomizer()];

        // Convert the word to underscores so it cant be seen
        charText = randomWord.toCharArray();
        charTextCheck = randomWord.toCharArray();

        for (int c = 0; c < randomWord.length(); c++) {
            charText[c] = '_';
        }
        randomText.setText(charText, 0, charText.length);

        // Setting the lives
        lifes = 1;



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    // Method for picking a random word from the string.xml
    public int randomizer() {
        Random r = new Random();
        return r.nextInt(words.length - 1);
    }

    // Finishes the game
    public void gameLost() {
        Toast.makeText(this, "better luck next time!", Toast.LENGTH_SHORT).show();
        finish();
    }



    // The guess-button method contains the checking of the character with the unknown word
    public void guess_button(View view) {


        EditText checkText = (EditText) findViewById(R.id.input_source);
        String checkString = checkText.getText().toString().toLowerCase();
        char checkChar = checkString.charAt(0);

        boolean guessedRight = false;

        // Comparing the user input with the hidden word, taking action if its correct or not
        if (Character.isLetter(checkChar)){
            for (int c = 0; c < randomWord.length(); c++) {
                if (checkChar == charTextCheck[c]){
                    guessedRight = true;
                    charText[c] = checkChar;
                    Toast.makeText(this, "correct!", Toast.LENGTH_SHORT).show();
                }
            }
            if (!guessedRight) {
                Toast.makeText(this, "not correct!", Toast.LENGTH_SHORT).show();
                lifes++;
            }

        }
        else if (Character.isDigit(checkChar))
            Toast.makeText(this, "please enter a letter!", Toast.LENGTH_SHORT).show();



        switch (lifes) {
            case 1:
                hangmanImage.setImageResource(R.drawable.hangman6);
                break;
            case 2:
                hangmanImage.setImageResource(R.drawable.hangman5);
                break;
            case 3:
                hangmanImage.setImageResource(R.drawable.hangman4);
                break;
            case 4:
                hangmanImage.setImageResource(R.drawable.hangman3);
                break;
            case 5:
                hangmanImage.setImageResource(R.drawable.hangman2);
                break;
            case 6:
                hangmanImage.setImageResource(R.drawable.hangman1);
                break;
            case 7:
                hangmanImage.setImageResource(R.drawable.hangman0);
                Toast.makeText(this, "game over!", Toast.LENGTH_SHORT).show();
                gameLost();
            }




        }


    }

