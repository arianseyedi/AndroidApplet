package ca.roumani.jumbleapp;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;

/**
 * The controller of this app.
 *
 * @author Franck van Breugel (Instructor of course EECS1022, Summer2017)
 */
public class Controller extends AppCompatActivity {
    private Dictionary dictionary;

    /**
     * Initializes this controller.
     *
     * @param savedInstanceState information needed for re-initialization.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_controller);
        // get the dictionary file
        Resources resources = this.getResources();
        int id = resources.getIdentifier("dictionary", "raw", this.getPackageName());
        InputStream stream = resources.openRawResource(id);
        // create a dictionary
        this.dictionary = new Dictionary(stream);
    }

    /**
     * Unscrambles the entered jumble.
     *
     * @param view not applicable.
     */
    public void jumble(View view) {
        // get the input as text
        View inputView = findViewById(R.id.input);
        EditText inputText = (EditText) inputView;
        String input = inputText.getText().toString();
        // get the unscramblings
        List<String> unscramblings = this.dictionary.getUnscramblings(input);
        // output
        View outputView = findViewById(R.id.output);
        TableLayout output = (TableLayout) outputView;
        for (String word : unscramblings) {
            TableRow row = new TableRow(this);
            TextView cell = new TextView(this);
            cell.setText(word);
            row.addView(cell);
            output.addView(row);
        }
    }
}