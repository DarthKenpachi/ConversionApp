package com.m5b10.conversion;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //declare spinner, EditTxt, and TextViews
    private Spinner unitTypeSpinner;
    private EditText amountTextView;
    TextView teaspoonTextView, tablespoonTextView, cupTextView, ounceTextView,
    pintTextView, quartTextView, gallonTextView, poundTextView,
    milliliterTextView, literTextView, milligramTextView, kilogramTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //fill spinner with unit type options
        addItemsToUnitTypeSpinner(); //call to add items to spinner

        addListenerToUnitTypeSpinner();

        amountTextView = (EditText) findViewById(R.id.amount_edit_text);
         //take user input, convert to EditText, store in amountTextView

        initializeTextViews();  //call to initialize all TextViews

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Move If You Wanna Move. ^_^", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void initializeTextViews(){
        teaspoonTextView = (TextView) findViewById(R.id.tsp_text_view);
        tablespoonTextView = (TextView) findViewById(R.id.tbs_text_view);
        cupTextView = (TextView) findViewById(R.id.cup_text_view);
        ounceTextView = (TextView) findViewById(R.id.oz_text_view);
        pintTextView = (TextView) findViewById(R.id.pint_text_view);
        quartTextView = (TextView) findViewById(R.id.quart_text_view);
        gallonTextView = (TextView) findViewById(R.id.gallon_text_view);
        poundTextView = (TextView) findViewById(R.id.pound_text_view);
        milliliterTextView = (TextView) findViewById(R.id.mL_text_view);
        literTextView = (TextView) findViewById(R.id.liter_text_view);
        milligramTextView = (TextView) findViewById(R.id.mg_text_view);
        kilogramTextView = (TextView) findViewById(R.id.kg_text_view);
    }

    public void addItemsToUnitTypeSpinner(){
        //cast Spinner
        unitTypeSpinner = (Spinner) findViewById(R.id.unit_type_spinner);

        //create arrayAdapter
        ArrayAdapter<CharSequence> unitTypeSpinnerAdapter =
                ArrayAdapter.createFromResource(this, R.array.conversion_types,
                        android.R.layout.simple_spinner_item);
                         //pass this for context, xml array file, and default Spinner layout
            //declare layout to use for list of choices
    unitTypeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //apply adapter to spinner
        unitTypeSpinner.setAdapter(unitTypeSpinnerAdapter);

    }

    public void addListenerToUnitTypeSpinner(){
        unitTypeSpinner = (Spinner) findViewById(R.id.unit_type_spinner);
        unitTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelectedInSpinner = parent.getItemAtPosition(position).toString();
                //get item selected

                checkIfConvertingFromTsp(itemSelectedInSpinner);

            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //TODO later

            }
        });

    }

    public void checkIfConvertingFromTsp(String currentUnit) {
        if (currentUnit.equals("teaspoon")) {

            updateUnitTypeUsingTsp(Quantity.Unit.tsp);
        } else {
            if(currentUnit.equals("tablespoon")){
                updateUnitTypeUsingOther(Quantity.Unit.tbs);
            } else if(currentUnit.equals("cup")){
                updateUnitTypeUsingOther(Quantity.Unit.cup);
            } else if(currentUnit.equals("ounce")){
                updateUnitTypeUsingOther(Quantity.Unit.oz);
            } else if(currentUnit.equals("pint")){
                updateUnitTypeUsingOther(Quantity.Unit.pint);
            } else if(currentUnit.equals("quart")){
                updateUnitTypeUsingOther(Quantity.Unit.quart);
            } else if(currentUnit.equals("gallon")){
                updateUnitTypeUsingOther(Quantity.Unit.gallon);
            } else if(currentUnit.equals("pound")){
                updateUnitTypeUsingOther(Quantity.Unit.pound);
            } else if(currentUnit.equals("milliliter")){
                updateUnitTypeUsingOther(Quantity.Unit.ml);
            } else {
                updateUnitTypeUsingOther(Quantity.Unit.kg);
            }
        }

    }


    public void updateUnitTypeUsingTsp(Quantity.Unit currentUnit){
    double doubleToConvert =
            Double.parseDouble(amountTextView.getText().toString());

        String teaspoonValueAndUnit = doubleToConvert + " tsp";

        teaspoonTextView.setText(teaspoonValueAndUnit);

        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.tbs, tablespoonTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.cup, cupTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.oz, ounceTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.pint, pintTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.quart, quartTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.gallon, gallonTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.pound, poundTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.ml, milliliterTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.liter, literTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.mg, milligramTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.kg, kilogramTextView);

    }

    public void updateUnitTextFieldUsingTsp(double doubleToConvert, Quantity.Unit unitConvertingTo,
                                            TextView theTextViewToUpdate){

            Quantity unitQuantity = new Quantity(doubleToConvert, Quantity.Unit.tsp);

        String tempUnit =
                unitQuantity.to(unitConvertingTo).toString();

        theTextViewToUpdate.setText(tempUnit);

    }


    public void updateUnitTypeUsingOther(Quantity.Unit currentUnit){
        double doubleToConvert=
                Double.parseDouble(amountTextView.getText().toString());

        Quantity currentQuantitySelected = new Quantity(doubleToConvert, currentUnit);

        String valueInTeaspoons =
                currentQuantitySelected.to(Quantity.Unit.tsp).toString();

        teaspoonTextView.setText(valueInTeaspoons);

        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit,
                Quantity.Unit.tbs, tablespoonTextView);

        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit,
                Quantity.Unit.cup, cupTextView);

        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit,
                Quantity.Unit.oz, ounceTextView);

        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit,
                Quantity.Unit.pint, pintTextView);

        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit,
                Quantity.Unit.quart, quartTextView);

        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit,
                Quantity.Unit.gallon, gallonTextView);

        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit,
                Quantity.Unit.pound, poundTextView);

        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit,
                Quantity.Unit.ml, milliliterTextView);

        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit,
                Quantity.Unit.liter, literTextView);

        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit,
                Quantity.Unit.mg, milligramTextView);

        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit,
                Quantity.Unit.kg, kilogramTextView);

        if(currentUnit.name().equals(currentQuantitySelected.unit.name())){
            String currentUnitTextViewText = doubleToConvert + " " +
                    currentQuantitySelected.unit.name();

            String currentTextViewName = currentQuantitySelected.unit.name() +
                    "_text_view";

            int currentId =
                    getResources().getIdentifier(currentTextViewName, "id",
                            MainActivity.this.getPackageName());    //return id for current txt_v

             TextView currentTextView = (TextView) findViewById(currentId);   //instance of current txt_v
          currentTextView.setText(currentUnitTextViewText);  //put right data in right txt_v

        }
    }

    //update txt field using teaspoons
    public void updateUnitTextFieldUsingTsp(double doubleToConvert,
                                            Quantity.Unit currentUnit, Quantity.Unit preferredUnit,
                                            TextView targetTextView){
        Quantity currentQuantitySelected = new
                Quantity(doubleToConvert, currentUnit);

        String tempTextViewText =
                currentQuantitySelected.to(Quantity.Unit.tsp).to(preferredUnit).toString();    //convert to teaspoon

        targetTextView.setText(tempTextViewText);

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
