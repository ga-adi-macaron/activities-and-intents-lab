package co.ga.madlibs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by james on 12/7/15.
 */
public class ResultActivity extends AppCompatActivity {

    // Constants to be used as keys for each value in the Bundle attached to the Intent
    public static final String ADJECTIVE1 = "adj1";
    public static final String ADJECTIVE2 = "adj2";
    public static final String NOUN1 = "noun1";
    public static final String NOUN2 = "noun";
    public static final String ANIMALS = "animals";
    public static final String GAME = "game";

    // Member variables to hold references to our views
    private TextView mResultTextView;
    private Button mBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Get references to views
        mResultTextView = (TextView) findViewById(R.id.result_textview);
        mBackButton = (Button) findViewById(R.id.back_button);

        // Call finish() when the back button is clicked (that ends the activity)
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Call the helper method that populates the TextView based on the data passed in the Intent
        setResults();
    }

    private void setResults() {
        String[] words = new String[6];

        // Get the bundle of data from the Intent object
        Bundle extras = getIntent().getExtras();

        // Check to be sure it isn't null before trying to get data out of the bundle
        if (extras != null) {
            words[0] = extras.getString(ADJECTIVE1, "???");
            words[1] = extras.getString(ADJECTIVE2, "???");
            words[2] = extras.getString(NOUN1, "???");
            words[3] = extras.getString(NOUN2, "???");
            words[4] = extras.getString(ANIMALS, "???");
            words[5] = extras.getString(GAME, "???");

            // Get the MadLibs string (entered in res/values/strings.xml)
            String resultString = getString(R.string.madlib_result, words);

            // Use another helper method to fix up the grammar of the string
            resultString = addIndefiniteArticles(resultString, words[2], words[3]);

            // Finally, put the string in the TextView
            mResultTextView.setText( Html.fromHtml(resultString) );
        } else {
            // If the Intent didn't have a bundle for some reason, notify user
            Toast.makeText(ResultActivity.this, "Did you set the words as extra data in the Intent?", Toast.LENGTH_SHORT).show();
        }
    }

    private String addIndefiniteArticles(String sentence, String... nouns) {
        String result = sentence;
        for (String noun : nouns){
            if (!TextUtils.isEmpty(noun)){
                String article = isVowel(noun.charAt(0))? "an" : "a";
                result = result.replaceFirst("a/an <b>" + noun + "</b>", article + " <b>" + noun + "</b>");
            }
        }

        return result;
    }

    private static boolean isVowel(char c) {
        return "AEIOUaeiou".indexOf(c) != -1;
    }
}
