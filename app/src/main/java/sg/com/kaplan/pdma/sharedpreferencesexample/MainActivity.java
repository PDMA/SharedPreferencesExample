package sg.com.kaplan.pdma.sharedpreferencesexample;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.textView);
        final EditText editText = (EditText) findViewById(R.id.editText);
        Button button = (Button) findViewById(R.id.button);

        //Read for saved values
        final SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE); //get the sharedpreferences file
        final int highScore = sharedPref.getInt("MyHighScore", 0); //0 is the initial value
        textView.setText(""+highScore); //reflect it on the UI

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputText = editText.getText().toString();
                if(!inputText.isEmpty()) { //could do more checks here
                    int newHighScore = Integer.parseInt(inputText); //not catch exception here
                    textView.setText(""+newHighScore); //reflect change on UI
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("MyHighScore", newHighScore);
                    editor.commit(); //save it to the sharedpreferences file
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    }
}
