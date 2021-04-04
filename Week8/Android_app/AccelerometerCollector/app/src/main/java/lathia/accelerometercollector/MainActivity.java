package lathia.accelerometercollector;

import android.Manifest;
import android.Manifest.permission;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
//import io.netpie.microgear.Microgear;
//import io.netpie.microgear.MicrogearEventListener;
import java.io.IOException;

public class MainActivity extends AppCompatActivity
{
    private Accelerometer accelerometer;
    private Button sensingButton;
    private EditText topicEditTxt;
    public static String URL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        verifyStoragePermissions(this);

        accelerometer = Accelerometer.getInstance(this);
        sensingButton = (Button) findViewById(R.id.sensingButton);
        topicEditTxt = (EditText) findViewById(R.id.topicEdit);

        setRecyclerView();
        LabelPreferences.clear(this);

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setButton();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        stopSensing();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void startSensing()
    {
        if (!accelerometer.isSensing())
        {
            if (LabelPreferences.getLabel(this) == null)
            {
                Toast.makeText(this, R.string.no_label, Toast.LENGTH_LONG).show();
                return;
            }

            try
            {
                accelerometer.start(this);
            }
            catch (IOException e)
            {
                Toast.makeText(this, "IOException: didn't start.", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
    }

    private void stopSensing()
    {
        LabelPreferences.clear(this);
        if (accelerometer.isSensing())
        {
            try
            {
                accelerometer.stop();
            }
            catch (IOException e)
            {
                Toast.makeText(this, "IOException: didn't start.", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
    }

    private void setRecyclerView()
    {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        if (recyclerView != null)
        {
            recyclerView.setHasFixedSize(true);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            LabelAdapter adapter = new LabelAdapter();
            recyclerView.setAdapter(adapter);
        }
    }

    private void setButton()
    {
        if (!accelerometer.isSensing())
        {
            sensingButton.setText(R.string.sensing_start);
        }
        else
        {
            sensingButton.setText(R.string.sensing_stop);
        }
    }

    public void onSensingButtonClicked(final View view)
    {
        if (!accelerometer.isSensing())
        {
            String topicStr = topicEditTxt.getText().toString();
            if(topicStr.isEmpty()){
                topicStr = "test";
            }

            URL =  "https://api.netpie.io/topic/ekaratnida/"+topicStr+"?auth=jtD9ag08syPtqiK:vDEEIuw9Ssj4OvbrBHmM4hZfa";

            startSensing();
        }
        else
        {
            stopSensing();
        }
        setButton();
    }

    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            permission.INTERNET,
            permission.ACCESS_WIFI_STATE,
            permission.ACCESS_NETWORK_STATE
    };

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

}
