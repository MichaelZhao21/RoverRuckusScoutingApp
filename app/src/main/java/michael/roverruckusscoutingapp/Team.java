package michael.roverruckusscoutingapp;

import android.util.Log;

import java.util.Locale;

public class Team {

    public String teamName;
    public int teamNumber;
    public String miscInfo;
    public boolean landing;
    public boolean sampling;
    public boolean marker;
    public boolean parking;
    public int mineralsLander;
    public int mineralsDepot;
    public boolean latching;

    public Team() {
        this.teamName = "";
        this.teamNumber = 0;
        this.miscInfo = "";
        this.landing = false;
        this.sampling = false;
        this.marker = false;
        this.parking = false;
        this.mineralsLander = 0;
        this.mineralsDepot = 0;
        this.latching = false;
    }

    public Team(String teamName, int teamNumber, String miscInfo, boolean landing, boolean sampling, boolean marker, boolean parking, int mineralsLander, int mineralsDepot, boolean latching) {
        this.teamName = teamName;
        this.teamNumber = teamNumber;
        this.miscInfo = miscInfo;
        this.landing = landing;
        this.sampling = sampling;
        this.marker = marker;
        this.parking = parking;
        this.mineralsLander = mineralsLander;
        this.mineralsDepot = mineralsDepot;
        this.latching = latching;
    }

    public void logTeam() {
        Log.d("Team","---------------------------");
        Log.d("Team","Team Object Output");
        Log.d("Team",String.format(Locale.US, "Team Name: %s", teamName));
        Log.d("Team",String.format(Locale.US, "Team Number: %d", teamNumber));
        Log.d("Team",String.format(Locale.US, "Misc Info: %s", miscInfo));
        Log.d("Team",String.format(Locale.US, "Landing: %b", landing));
        Log.d("Team",String.format(Locale.US, "Sampling: %b", sampling));
        Log.d("Team",String.format(Locale.US, "Marker: %b", marker));
        Log.d("Team",String.format(Locale.US, "Parking: %b", parking));
        Log.d("Team",String.format(Locale.US, "Minerals in Lander: %d", mineralsLander));
        Log.d("Team",String.format(Locale.US, "Minerals in Depot: %d", mineralsDepot));
        Log.d("Team",String.format(Locale.US, "Latching: %b", latching));
        Log.d("Team","---------------------------");
    }
}
