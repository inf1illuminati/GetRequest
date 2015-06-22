package com.stuff.xalandra.getrequest;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;


public class MainActivityGet extends Activity {

    /**@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_get);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity_get, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_get);

        //knop
        final Button GetServerData = (Button) findViewById(R.id.GetServerData);

        // On button click call this listener
        GetServerData.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Toast.makeText(getBaseContext(),
                        "Please wait, connecting to server.",
                        Toast.LENGTH_SHORT).show();


                // Create Inner Thread Class
                Thread background = new Thread(new Runnable() {

                    private final HttpClient Client = new DefaultHttpClient();
                    //stuur x = 100 door naar webpagina
                    private String URL = "http://robotarm.serverict.nl/index.php?x=100";

                    // After call for background.start this run method call
                    public void run() {
                        try {

                            String SetServerString = "";
                            HttpGet httpget = new HttpGet(URL);
                            ResponseHandler<String> responseHandler = new BasicResponseHandler();
                            SetServerString = Client.execute(httpget, responseHandler);
                            threadMsg(SetServerString);

                        } catch (Throwable t) {
                            // just end the background thread
                            Log.i("Animation", "Thread  exception " + t);
                        }
                    }

                    private void threadMsg(String msg) {

                        if (!msg.equals(null) && !msg.equals("")) {
                            Message msgObj = handler.obtainMessage();
                            Bundle b = new Bundle();
                            b.putString("message", msg);
                            msgObj.setData(b);
                            handler.sendMessage(msgObj);
                        }
                    }

                    // Define the Handler that receives messages from the thread and update the progress
                    private final Handler handler = new Handler() {

                        public void handleMessage(Message msg) {

                            String aResponse = msg.getData().getString("message");

                            if ((null != aResponse)) {

                                // ALERT MESSAGE
                                Toast.makeText(
                                        getBaseContext(),
                                        "Server Response: "+aResponse,
                                        Toast.LENGTH_SHORT).show();
                            }
                            else
                            {

                                // ALERT MESSAGE
                                Toast.makeText(
                                        getBaseContext(),
                                        "Not Got Response From Server.",
                                        Toast.LENGTH_SHORT).show();
                            }

                        }
                    };

                });
                // Start Thread
                background.start();  //After call start method thread called run Method
            }
        });

    }
}
