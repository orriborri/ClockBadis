package orre.clockbadis;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import tech.gusavila92.websocketclient.WebSocketClient;



public class MainActivity extends Activity {
    private WebSocketClient webSocketClient;
    private TextView mTextView;
    private int myScore = 0;
    private boolean match = false;

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

    public void createNewMacth(View view){
        createWebSocketClient();
        setContentView(R.layout.round_activity_main);
        mTextView = findViewById(R.id.text);

    }
    private void createWebSocketClient() {
        URI uri;
        try {
            uri = new URI("wss://klockbadis.herokuapp.com");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        webSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen() {
                System.out.println("onOpen");
                webSocketClient.send("Hello, World!");
            }

            @Override
            public void onTextReceived(String message) {
                System.out.println("onTextReceived");
            }

            @Override
            public void onBinaryReceived(byte[] data) {
                System.out.println("onBinaryReceived");
            }

            @Override
            public void onPingReceived(byte[] data) {
                System.out.println("onPingReceived");
            }

            @Override
            public void onPongReceived(byte[] data) {
                System.out.println("onPongReceived");
            }

            @Override
            public void onException(Exception e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onCloseReceived() {
                System.out.println("onCloseReceived");
            }
        };
        webSocketClient.setConnectTimeout(10000);
        webSocketClient.setReadTimeout(60000);
        webSocketClient.enableAutomaticReconnection(5000);
        webSocketClient.connect();

    }
};


