package com.example.hangman20;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.Math;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

   String[] hangWords = {"github", "javascript", "flutter", "network","datalink",
                        "legumes","tomates","ignames", "bananne", "betterave",
                        "aerospatiale", "aerodynamique", "transduction", "scalaire",
                        "mercedezbenz", "audi","lexus","lamborgini","aston martins"};
   
   int chance = 5;
   String wordChoosen;
   String subWord;
   private static final Random random = new Random();
   TextView hiddenText;
   TextView chanceText;
   List<Character> charList = new ArrayList<>();
   Button reset;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        chanceText = findViewById(R.id.chansTv);
        chanceText.setText(chance+" Chance");

        reset = findViewById(R.id.reset);

        wordChoosen = randomWord();
        //initializing the char array with the random word
        char[] hiddenWorld = new char[wordChoosen.length()];

        hiddenText = findViewById(R.id.hiddenWord);

        int subWordIndex = (int) ( wordChoosen.length()*0.2 );
        if(subWordIndex == 0)
            subWord = wordChoosen.substring(0,1);
        else
            subWord = wordChoosen.substring(0,subWordIndex);

        for(int i=0; i<subWord.length(); i++)
                charList.add(subWord.charAt(i));

        for(int i=0; i<wordChoosen.length()-subWord.length(); i++){
            charList.add(charList.size(), '_');
        }
        hiddenText.setText(charList.toString());
    }

    //choosing a random word in the array
    public String randomWord(){
        return hangWords[random.nextInt(hangWords.length)];
    }

    public void wordReset(View view){
        this.recreate();
    }

    public String getDisplayText(){
    StringBuilder sb = new StringBuilder();

        for (char c : charList) {
            sb.append(c);
        }
        return sb.toString();
    }

    @SuppressLint("SetTextI18n")
    public void onTapLetter(View btnView){
        Button buttonLetter = (Button) btnView;
        String letterHit = buttonLetter.getText().toString();

        if(wordChoosen.contains(letterHit)){

            int letterIndex = wordChoosen.indexOf(letterHit);
            while(letterIndex>=0){
                charList.set(letterIndex, letterHit.charAt(0));
                letterIndex = wordChoosen.indexOf(letterHit, letterIndex+1);
            }

        }else{
            chance -= 1;
            chanceText.setText(chance+" Chance");
            Toast.makeText(this,"This letter isn't on the letter",Toast.LENGTH_SHORT).show();

            if(chance==0){
                Toast.makeText(this, "Sorry you lost", Toast.LENGTH_LONG).show();
                Intent mainIntent = new Intent(this,MainActivity.class);
                startActivity(mainIntent);
            }
        }

        hiddenText.setText(getDisplayText());
    }

}