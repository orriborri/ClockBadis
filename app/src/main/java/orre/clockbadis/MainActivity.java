package orre.clockbadis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class MainActivity extends Activity {
    private TextView mTextView;
    private TextView joinTextView;
    private String roomId;
    private int myScore = 0;
    private boolean match = false;
    ExecutorService es = Executors.newSingleThreadExecutor();

    WebSocketFactory factory = new WebSocketFactory().setConnectionTimeout(5000);
    private WebSocket ws = factory.createSocket("ws://klockbadis.herokuapp.com")
            .addListener(new WebSocketAdapter() {
                public void onTextMessage(WebSocket websocket, String message) {
                    System.out.println(message);
                }
            });

    Future<WebSocket> future = ws.connect(es);

    public MainActivity() throws IOException {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isMatch()) {
            setContentView(R.layout.round_activity_main);
            mTextView = findViewById(R.id.text);

        } else {
            setContentView(R.layout.init);
        }
    }

    public void incScore(View view) {
        myScore += 1;
        mTextView.setText("" + myScore);

    }

    public void decScore(View view) {
        myScore -= 1;
        mTextView.setText("" + myScore);

    }

    public boolean isMatch() {
        return match;
    }


    public void createNewMacth(View view) throws Exception {
        future.get();
      /*  System.out.println(res.getJSONObject("data").getString("id"));
        setContentView(R.layout.showkey);
        joinTextView = findViewById(key);
        joinTextView.setText(res.getJSONObject("data").getString("id"));
        roomId = res.getJSONObject("data").getString("id");
    */}
    public void joinMatch(View view) throws ExecutionException, InterruptedException {
        future.get();
        Intent i = new Intent(this, JoinaActivity.class);
        startActivity(i);
    }



    public void joinRoomButton(View view) throws JSONException {

    }


};



