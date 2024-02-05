package com.example.test;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    public int b1ClickCount = 0;
    public int b2ClickCount = 0;

    private static String formatDuration() {
        LocalDateTime futureDateTime = LocalDateTime.ofEpochSecond(1707375000, 0, ZoneOffset.UTC);

        Instant currentInstant = Instant.now();
        LocalDateTime currentDateTime = LocalDateTime.ofInstant(currentInstant, ZoneOffset.UTC);

        Duration duration = Duration.between(currentDateTime, futureDateTime);

        long days = duration.toDays();
        long hours = duration.toHours() % 24;
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;
        return String.format("Time left: %d days, %d hours, %d minutes, %d seconds%n", days, hours, minutes, seconds);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = findViewById(R.id.button2);
        b.setOnClickListener(v -> {
                    if (b2ClickCount < 20) {
                        b.setScaleX(b.getScaleX() + 0.09f);
                        b2ClickCount += 1;
                        b.setText(String.valueOf(b2ClickCount));
                    } else {
                        Toast.makeText(this, "Enough", Toast.LENGTH_LONG).show();
                    }
                    TextView textView = findViewById(R.id.textView);

                    textView.setText(formatDuration());
                }
        );
    }

    public void func(View view) {

        if (b1ClickCount < 20) {
            view.setScaleX(view.getScaleX() + 0.2f);
            b1ClickCount += 1;
            ((Button) view).setText(String.valueOf(b1ClickCount));
        } else {
            Toast.makeText(this, "Enough", Toast.LENGTH_LONG).show();
        }


    }
}