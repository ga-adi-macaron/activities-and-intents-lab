package co.ga.madlibs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Member variables to hold references to our views
    private EditText mAdjective1EditText;
    private EditText mAdjective2EditText;
    private EditText mNoun1EditText;
    private EditText mNoun2EditText;
    private EditText mAnimalsEditText;
    private EditText mGameEditText;
    private Button mSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get references to the EditTexts and Button
        mAdjective1EditText = (EditText) findViewById(R.id.adjective1_edittext);
        mAdjective2EditText = (EditText) findViewById(R.id.adjective2_edittext);
        mNoun1EditText = (EditText) findViewById(R.id.noun1_edittext);
        mNoun2EditText = (EditText) findViewById(R.id.noun2_edittext);
        mAnimalsEditText = (EditText) findViewById(R.id.animals_edittext);
        mGameEditText = (EditText) findViewById(R.id.game_edittext);
        mSubmitButton = (Button) findViewById(R.id.submit_button);

        // Set an OnClickListener on the Button
        View.OnClickListener onSubmitClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (allWordsAreFilledOut()){
                    startResultsActivity();
                } else {
                    Toast.makeText(MainActivity.this, "Please fix the errors", Toast.LENGTH_SHORT).show();
                }
            }
        };

        mSubmitButton.setOnClickListener(onSubmitClick);
    }

    /**
     * @return true, if all of the app's edit texts have a value. false otherwise.
     */
    private boolean allWordsAreFilledOut() {
        boolean wordsFilledOut;

        // Logic: If all of the edit texts have values, then this method will return true
        // The following code is saying "If edit text 1 is valid && edit text 2 is valid && edit
        // text 3 is valid && etc". This can also be done with 6 if-else conditional statements.
        wordsFilledOut = isEditTextValid(mAdjective1EditText);
        wordsFilledOut &= isEditTextValid(mAdjective2EditText);
        wordsFilledOut &= isEditTextValid(mNoun1EditText);
        wordsFilledOut &= isEditTextValid(mNoun2EditText);
        wordsFilledOut &= isEditTextValid(mAnimalsEditText);
        wordsFilledOut &= isEditTextValid(mGameEditText);

        return wordsFilledOut;
    }

    /**
     * This method checks if the given edit text has a value associated with it. If it does not, the
     * edit text will show an error.
     *
     * @param editText The edit text to check
     * @return true, if the edit text has a value. false otherwise.
     */
    private boolean isEditTextValid(EditText editText) {
        boolean isValid = true;
        String text = editText.getText().toString();

        if (text.isEmpty()){
            editText.setError("Please fill out!");
            isValid = false;
        }

        return isValid;
    }

    private void startResultsActivity(){
        // start new intent
        Intent intent = new Intent(this, ResultActivity.class);

        // Add the values from all the EditTexts to a Bundle that will be added to the Intent.
        // Each value needs a key; use the constants defined in ResultsActivity.
        Bundle bundle = new Bundle();
        bundle.putString(ResultActivity.ADJECTIVE1, mAdjective1EditText.getText().toString());
        bundle.putString(ResultActivity.ADJECTIVE2, mAdjective2EditText.getText().toString());
        bundle.putString(ResultActivity.NOUN1, mNoun1EditText.getText().toString());
        bundle.putString(ResultActivity.NOUN2, mNoun2EditText.getText().toString());
        bundle.putString(ResultActivity.ANIMALS, mAnimalsEditText.getText().toString());
        bundle.putString(ResultActivity.GAME, mGameEditText.getText().toString());

        intent.putExtras(bundle);
        startActivity(intent);
    }
}
