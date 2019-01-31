package michael.roverruckusscoutingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class BrowseTeams extends AppCompatActivity {

    ArrayList<Team> teamList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_teams);

        File file = new File(getFilesDir(), "teams");
        int barCount = 0;
        try {
            InputStream inputStream = new FileInputStream(file);
            Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name());
            String stringData = scanner.useDelimiter("\\A").next();
            inputStream.close();

            String[] stringDataTeams = stringData.split("~");
            String[][] stringDataSplit = new String[stringDataTeams.length][10];
            for (int x = 0; x < stringDataTeams.length; x++) {
                stringDataSplit[x] = stringDataTeams[x].split("\\|");
            }

            for (int x = 0; x < stringDataSplit.length; x++) {
                String[] sd = stringDataSplit[x];
                Log.d("stringData",Arrays.toString(sd));
                teamList.add(new Team());
                for (String s : sd) {
                    switch (barCount) {
                        case 0:
                            teamList.get(x).teamName = s;
                            break;
                        case 1:
                            teamList.get(x).teamNumber = Integer.parseInt(s);
                            break;
                        case 2:
                            teamList.get(x).miscInfo = s;
                            break;
                        case 3:
                            teamList.get(x).landing = s.equals("true");
                            break;
                        case 4:
                            teamList.get(x).sampling = s.equals("true");
                            break;
                        case 5:
                            teamList.get(x).marker = s.equals("true");
                            break;
                        case 6:
                            teamList.get(x).parking = s.equals("true");
                            break;
                        case 7:
                            teamList.get(x).mineralsLander = Integer.parseInt(s);
                            break;
                        case 8:
                            teamList.get(x).mineralsDepot = Integer.parseInt(s);
                            break;
                        case 9:
                            teamList.get(x).endGame = s;
                            break;
                    }
                    barCount++;
                }
                barCount = 0;
            }

            Log.d("teamArray",Arrays.deepToString(stringDataSplit));

        } catch (Exception e) {
            e.printStackTrace();
        }


        LinearLayout teamHolder = findViewById(R.id.teamHolder);
        LinearLayout addTeam;
        TextView teamNumber;
        TextView teamName;
        TextView points;
        for (Team t : teamList) {
            t.calcScore();
            t.logTeam();

            addTeam = new LinearLayout(this);
            addTeam.setOrientation(LinearLayout.HORIZONTAL);

            teamNumber = new TextView(this);
            teamNumber.setText(String.format(Locale.US,"%d",t.teamNumber));
            teamNumber.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,2f));
            teamNumber.setTextSize(16f);
            teamNumber.setGravity(Gravity.CENTER);
            addTeam.addView(teamNumber);

            teamName = new TextView(this);
            teamName.setText(t.teamName);
            teamName.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,5f));
            teamName.setTextSize(16f);
            teamName.setGravity(Gravity.CENTER);
            addTeam.addView(teamName);

            points = new TextView(this);
            points.setText(String.format(Locale.US,"%d",t.score));
            points.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1f));
            points.setTextSize(16f);
            points.setGravity(Gravity.CENTER);
            addTeam.addView(points);

            teamHolder.addView(addTeam);

        }

    }

}
