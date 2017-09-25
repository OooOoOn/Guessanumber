package com.example.oooooon.guessanumber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btn_easy;
    Button btn_normal;
    TextView txt_title;
    EditText txt_guess;
    Button btn_plus;
    Button btn_minus;
    Button sw_guess;
    ImageView img_bender;
    TextView txt_dialog;
    TextView txt_score;

    Random r = new Random();
    int randomNumber = 0;
    String difficulty;
    int numberOfGuesses = 0;
    int enteredValue = 0;
    int maxGuess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_easy = (Button) findViewById(R.id.button_easy);
        btn_normal = (Button) findViewById(R.id.button_normal);
        txt_title = (TextView) findViewById(R.id.text_title);
        txt_guess = (EditText) findViewById(R.id.text_guess);
        btn_plus = (Button) findViewById(R.id.button_plus);
        btn_minus = (Button) findViewById(R.id.button_minus);
        sw_guess = (Button) findViewById(R.id.switch_guess);
        img_bender = (ImageView) findViewById(R.id.image_bender);
        txt_dialog = (TextView) findViewById(R.id.text_dialog);
        txt_score = (TextView) findViewById(R.id.text_score);

        img_bender.setVisibility(View.INVISIBLE);
        txt_dialog.setVisibility(View.INVISIBLE);
        sw_guess.setVisibility(View.INVISIBLE);
        txt_score.setVisibility(View.INVISIBLE);

        startGame();
        guessing();
        changingNumber();

    }

    public void startGame() {
        btn_easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomNumber = r.nextInt(10) + 1;
                btn_easy.setVisibility(View.INVISIBLE);
                btn_normal.setVisibility(View.INVISIBLE);
                img_bender.setVisibility(View.VISIBLE);
                txt_dialog.setVisibility(View.VISIBLE);
                sw_guess.setVisibility(View.VISIBLE);
                txt_score.setVisibility(View.VISIBLE);

                txt_dialog.setText(R.string.easy_dialogue);
                difficulty = "easy";
                maxGuess = 5;

            }
        });

        btn_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomNumber = r.nextInt(100) + 1;
                btn_easy.setVisibility(View.INVISIBLE);
                btn_normal.setVisibility(View.INVISIBLE);
                img_bender.setVisibility(View.VISIBLE);
                txt_dialog.setVisibility(View.VISIBLE);
                sw_guess.setVisibility(View.VISIBLE);
                txt_score.setVisibility(View.VISIBLE);

                txt_dialog.setText(R.string.normal_dialogue);
                difficulty = "normal";
                maxGuess = 7;
            }
        });
    }

    public void changingNumber() {

        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredValue = Integer.parseInt(txt_guess.getText().toString());
                enteredValue++;
                txt_guess.setText(Integer.toString(enteredValue));
            }
        });

        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredValue = Integer.parseInt(txt_guess.getText().toString());
                enteredValue--;
                txt_guess.setText(Integer.toString(enteredValue));
            }
        });

    }

    public void guessing() {

        sw_guess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int playerGuess = Integer.parseInt(txt_guess.getText().toString());


                    if (randomNumber == playerGuess) {
                        Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
                        sw_guess.setVisibility(View.INVISIBLE);
                    } else if (randomNumber - playerGuess <= 3 && randomNumber - playerGuess > 0) {
                        Toast.makeText(MainActivity.this, "you are close!", Toast.LENGTH_SHORT).show();
                    } else if (playerGuess < randomNumber) {

                        Toast.makeText(MainActivity.this, "Guess higher!", Toast.LENGTH_SHORT).show();


                    } else if (playerGuess > randomNumber) {

                        Toast.makeText(MainActivity.this, "Guess lower!", Toast.LENGTH_SHORT).show();
                    }


                numberOfGuesses++;
                txt_score.setText("Number of guesses: " + Integer.toString(numberOfGuesses));

                if (numberOfGuesses == maxGuess) {
                    Toast.makeText(MainActivity.this, "GAME OVER!", Toast.LENGTH_SHORT).show();
                    sw_guess.setVisibility(View.INVISIBLE);

                }

            }


        });

    }


}


