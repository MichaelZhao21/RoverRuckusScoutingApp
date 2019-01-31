package michael.roverruckusscoutingapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileOutputStream;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button deleteTeams = findViewById(R.id.deleteTeams);
        deleteTeams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Add confirmation dialog box
                FileOutputStream outputStream;
                try {
                    outputStream = openFileOutput("teams", Context.MODE_PRIVATE);
                    outputStream.write("".getBytes());
                    outputStream.close();

                    Context context = getApplicationContext();
                    CharSequence text = "Successfully cleared data!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
