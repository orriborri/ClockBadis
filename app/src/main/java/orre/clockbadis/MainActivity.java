package orre.clockbadis;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView mTextView;
    private int myScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.round_activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
                mTextView.append(Integer.toString(myScore));
            }
        });
        final Button buttonMinus = findViewById(R.id.buttonminus);
        buttonMinus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myScore -=1;
                mTextView.append(Integer.toString(myScore));
            }
        });
        final Button buttonPlus = findViewById(R.id.buttonplus);
        buttonPlus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myScore +=1;
                mTextView.append(Integer.toString(myScore));
            }
        });
    }

}
