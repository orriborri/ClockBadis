package orre.clockbadis;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class MainActivity extends Activity {
    private TextView mTextView;
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
        ws.getSocket();
    }




};



