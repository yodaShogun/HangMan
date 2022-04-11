package com.example.hangman20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mainBtn = findViewById(R.id.launch);

        View.OnClickListener click = view -> {
            Intent gameIntent = new Intent(MainActivity.this, GameActivity.class);
            startActivity(gameIntent);
        };
        mainBtn.setOnClickListener(click);
        }
        @Override
        public boolean onCreateOptionsMenu(Menu menu){
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.game_menu, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item){

            switch(item.getItemId()){
                case R.id.it1:
                    System.out.println("Omae wa doko wa da de mieteiru");
                case R.id.it2:
                    System.out.println("I'm living ");
                case R.id.it3:
                    System.out.println("Rasengan");
                default:
                    super.onOptionsItemSelected(item);
            }
            return true;
        }



    }
