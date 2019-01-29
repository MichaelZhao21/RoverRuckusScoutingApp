package michael.roverruckusscoutingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Locale;

import michael.roverruckusscoutingapp.R;

public class AddScoring extends AppCompatActivity {

    public int score = 0;
    public ToggleButton landing;
    public ToggleButton sampling;
    public ToggleButton marker;
    public ToggleButton parking;
    public Button mineralsLanderIncrease;
    public Button mineralsLanderDecrease;
    public Button mineralsDepotIncrease;
    public Button mineralsDepotDecrease;
    public TextView mineralsLander;
    public TextView mineralsDepot;
    public TextView totalScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_scoring);

        landing = findViewById(R.id.landingButton);
        sampling = findViewById(R.id.samplingButton);
        marker = findViewById(R.id.markerButton);
        parking = findViewById(R.id.parkingButton);
        mineralsLanderIncrease = findViewById(R.id.mineralsLanderIncrease);
        mineralsLanderDecrease = findViewById(R.id.mineralsLanderDecrease);
        mineralsDepotIncrease = findViewById(R.id.mineralsDepotIncrease);
        mineralsDepotDecrease = findViewById(R.id.mineralsDepotDecrease);
        mineralsLander = findViewById(R.id.mineralsLanderCount);
        mineralsDepot = findViewById(R.id.mineralsDepotCount);
        totalScore = findViewById(R.id.totalScore);

        landing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateScore("landing");
            }
        });

        sampling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateScore("sampling");
            }
        });

        marker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateScore("marker");
            }
        });

        parking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateScore("parking");
            }
        });

        mineralsLanderIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateScore("mineralsLanderIncrease");
                mineralsLander.setText(String.format(Locale.US, "%d",Integer.parseInt(mineralsLander.getText().toString()) + 1));
            }
        });

        mineralsLanderDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(mineralsLander.getText().toString()) > 0) {
                    calculateScore("mineralsLanderDecrease");
                    mineralsLander.setText(String.format(Locale.US, "%d",Integer.parseInt(mineralsLander.getText().toString()) - 1));
                }
            }
        });

        mineralsDepotIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateScore("mineralsDepotIncrease");
                mineralsDepot.setText(String.format(Locale.US, "%d", Integer.parseInt(mineralsDepot.getText().toString()) + 1));
            }
        });

        mineralsDepotDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(mineralsDepot.getText().toString()) > 0) {
                    calculateScore("mineralsDepotDecrease");
                    mineralsDepot.setText(String.format(Locale.US, "%d",Integer.parseInt(mineralsDepot.getText().toString()) - 1));
                }
            }
        });

        totalScore.setText(getString(R.string.addScoreTotal,Integer.toString(score)));

    }

    public void calculateScore(String changed) {

        switch (changed) {
            case "landing":
                if (landing.isChecked()) {
                    score += 30;
                }
                else {
                    score -= 30;
                }
                break;
            case "sampling":
                if (sampling.isChecked()) {
                    score += 25;
                }
                else {
                    score -= 25;
                }
                break;
            case "marker":
                if (marker.isChecked()) {
                    score += 15;
                }
                else {
                    score -= 15;
                }
                break;
            case "parking":
                if (parking.isChecked()) {
                    score += 10;
                }
                else {
                    score -= 10;
                }
                break;
            case "mineralsLanderIncrease":
                score += 5;
                break;
            case "mineralsLanderDecrease":
                score -= 5;
                break;
            case "mineralsDepotIncrease":
                score += 2;
                break;
            case "mineralsDepotDecrease":
                score -= 2;
                break;



        }

        totalScore.setText(getString(R.string.addScoreTotal,Integer.toString(score)));

    }

}
