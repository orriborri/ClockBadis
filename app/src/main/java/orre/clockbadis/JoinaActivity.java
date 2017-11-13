package orre.clockbadis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

public class JoinaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);
    }
    public void joinRoomButton(View view) throws JSONException {
        EditText mEdit = (EditText) findViewById(R.id.key);
        JSONObject event = new JSONObject();
        JSONObject data = new JSONObject();
        event.put("event","join");
        data.put("id",mEdit.getText());
        event.put("data",data);
      //  ws.sendText(event.toString());
        System.out.println(mEdit.getText()+"kakka");
        finish();
    }
}
