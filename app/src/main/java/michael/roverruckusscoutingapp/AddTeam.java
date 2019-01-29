package michael.roverruckusscoutingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import michael.roverruckusscoutingapp.R;

public class AddTeam extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);

        Button next = findViewById(R.id.addTeamNext);
        final EditText teamName = findViewById(R.id.teamName);
        final EditText teamNumber = findViewById(R.id.teamNumber);
        final EditText miscInfo = findViewById(R.id.miscInfo);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTeam.this, AddScoring.class);
                intent.putExtra("teamName",teamName.getText().toString());
                intent.putExtra("teamNumber",Integer.parseInt(teamNumber.getText().toString()));
                intent.putExtra("miscInfo",miscInfo.getText().toString());
                intent.putExtra("newTeam",true);
                startActivity(intent);
            }
        });

    }
}
