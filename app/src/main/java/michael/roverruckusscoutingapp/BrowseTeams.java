package michael.roverruckusscoutingapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
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

        Button help = findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String output = "Jasper|11419|ROboHoboOsGEarsAnd Cheers|true|true|true|true|6|0|true";
                Log.d("AHHHHHHHHHHHHHHH",output);

                FileOutputStream outputStream;
                try {
                    outputStream = openFileOutput("teams", Context.MODE_PRIVATE);
                    outputStream.write(output.getBytes());
                    outputStream.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

                File file = new File(getFilesDir(), "teams");
                int barCount = 0;
                Team newTeam = new Team();
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

                    Log.d("String Array", Arrays.deepToString(stringDataSplit));

                    for (String[] sd : stringDataSplit) {
                        for (String s : sd) {
                            switch (barCount) {
                                case 0:
                                    newTeam.teamName = s;
                                    break;
                                case 1:
                                    newTeam.teamNumber = Integer.parseInt(s);
                                    break;
                                case 2:
                                    newTeam.miscInfo = s;
                                    break;
                                case 3:
                                    newTeam.landing = s.equals("true");
                                    break;
                                case 4:
                                    newTeam.sampling = s.equals("true");
                                    break;
                                case 5:
                                    newTeam.marker = s.equals("true");
                                    break;
                                case 6:
                                    newTeam.parking = s.equals("true");
                                    break;
                                case 7:
                                    newTeam.mineralsLander = Integer.parseInt(s);
                                    break;
                                case 8:
                                    newTeam.mineralsDepot = Integer.parseInt(s);
                                    break;
                                case 9:
                                    newTeam.latching = s.equals("true");
                                    break;
                            }
                            barCount++;
                        }
                        teamList.add(newTeam);
                        newTeam = new Team();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                for (Team t : teamList) {
                    t.logTeam();
                }

            }
        });

        LinearLayout teamHolder = findViewById(R.id.teamHolder);
    }

}
