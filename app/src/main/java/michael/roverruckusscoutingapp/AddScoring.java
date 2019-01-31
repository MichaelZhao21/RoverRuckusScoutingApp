package michael.roverruckusscoutingapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Locale;

public class AddScoring extends AppCompatActivity {

    public int score = 0;
    public ToggleButton landing;
    public ToggleButton sampling;
    public ToggleButton marker;
    public ToggleButton parking;
    public Spinner endGameSpinner;
    public Button mineralsLanderIncrease;
    public Button mineralsLanderDecrease;
    public Button mineralsDepotIncrease;
    public Button mineralsDepotDecrease;
    public TextView mineralsLander;
    public TextView mineralsDepot;
    public TextView totalScore;
    public String teamName;
    public String previousEndGameValue = "Nothing";
    public int teamNumber;
    public String miscInfo;
    public boolean newTeam;
    public Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_scoring);

        landing = findViewById(R.id.landingButton);
        sampling = findViewById(R.id.samplingButton);
        marker = findViewById(R.id.markerButton);
        parking = findViewById(R.id.parkingButton);
        endGameSpinner = findViewById(R.id.endGameSpinner);
        mineralsLanderIncrease = findViewById(R.id.mineralsLanderIncrease);
        mineralsLanderDecrease = findViewById(R.id.mineralsLanderDecrease);
        mineralsDepotIncrease = findViewById(R.id.mineralsDepotIncrease);
        mineralsDepotDecrease = findViewById(R.id.mineralsDepotDecrease);
        mineralsLander = findViewById(R.id.mineralsLanderCount);
        mineralsDepot = findViewById(R.id.mineralsDepotCount);
        totalScore = findViewById(R.id.totalScore);
        done = findViewById(R.id.done);

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

        endGameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                calculateScore("endGameSpinner");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                calculateScore("endGameSpinner");
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

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAndWrite();
            }
        });

        totalScore.setText(getString(R.string.addScoreTotal,Integer.toString(score)));

        endGameSpinner.setPrompt(getString(R.string.endGamePrompt));
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.endGameStates, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        endGameSpinner.setAdapter(adapter);

        Intent intent = getIntent();
        teamName = intent.getStringExtra("teamName");
        teamNumber = intent.getIntExtra("teamNumber",0);
        miscInfo = intent.getStringExtra("miscInfo");
        newTeam = intent.getBooleanExtra("newTeam",true);

        if (newTeam) {
            //TODO: Add team values
        }

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
            case "endGameSpinner":
                switch (previousEndGameValue) {
                    case "Latching":
                        score -= 50;
                        break;
                    case "Partially in Crater":
                        score -= 15;
                        break;
                    case "Fully in Crater":
                        score -= 25;
                        break;
                    default:
                        break;
                }
                switch (endGameSpinner.getSelectedItem().toString()) {
                    case "Latching":
                        score += 50;
                        break;
                    case "Partially in Crater":
                        score += 15;
                        break;
                    case "Fully in Crater":
                        score += 25;
                        break;
                    default:
                        break;
                }
                previousEndGameValue = endGameSpinner.getSelectedItem().toString();
                break;
        }

        totalScore.setText(getString(R.string.addScoreTotal,Integer.toString(score)));

    }

    public void finishAndWrite() {

        //TODO: Make sure miscInfo doesn't contain '|'
        String output = String.format(Locale.US, "%1$s|%2$d|%3$s|%4$b|%5$b|%6$b|%7$b|%8$s|%9$s|%10$s|~", teamName, teamNumber, miscInfo, landing.isChecked(), sampling.isChecked(), marker.isChecked(), parking.isChecked(), mineralsLander.getText().toString(), mineralsDepot.getText().toString(), endGameSpinner.getSelectedItem().toString());

        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput("teams", Context.MODE_APPEND);
            outputStream.write(output.getBytes());
            outputStream.close();

            Context context = getApplicationContext();
            CharSequence text = "Successfully added " + teamName;
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        startActivity(new Intent(AddScoring.this, MainActivity.class));

    }

}
