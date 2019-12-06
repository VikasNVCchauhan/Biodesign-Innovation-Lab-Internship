package com.example.biodesigninnovationlabsprivatelimitedcolorrgbapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Widgets declaration
    private CardView cardViewCombinedColour;
    private ImageView imageViewGreenColour, imageViewBlueColour, imageViewRedColour, imageViewViewCombinedColour;
    private ImageView imageViewIncrementRedColour, imageViewIncrementGreenColour, imageViewIncrementBlueColour, imageViewDecrementRedColour, imageViewDecrementBlueColour, imageViewDecrementGreenColour;
    private TextView textViewRedColourCount, textViewGreenColourCount, textViewBlueColourCount;
    private TextView textViewAlertDialog;
    private Drawable background;
    //Pre Defined Classes
    private Handler handler;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    //User Defined classes

    //Variable deceleration
    private int BLUE_COLOUR_COUNT = 100;      //initial value of all colours
    private int GREEN_COLOUR_COUNT = 210;
    private int RED_COLOUR_COUNT = 150;

    private boolean mAutoIncrement = false;
    private boolean mAutoDecrement = false;
    //Constant Variable
    private static final int MAX_COLOUR_CODE = 250;
    private static final int MIN_COLOUR_CODE = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setIdForAllWidgets();                  //For getting Id of all widgets declared
        background = imageViewViewCombinedColour.getBackground();
        setChangeToCombineColor(RED_COLOUR_COUNT, BLUE_COLOUR_COUNT, GREEN_COLOUR_COUNT);  //This method will set the new changes to combine colour effect
        handler = new Handler();

        imageViewIncrementRedColour.setOnClickListener(this);
        imageViewIncrementGreenColour.setOnClickListener(this);
        imageViewIncrementBlueColour.setOnClickListener(this);
        imageViewDecrementRedColour.setOnClickListener(this);
        imageViewDecrementBlueColour.setOnClickListener(this);
        imageViewDecrementGreenColour.setOnClickListener(this);
    }

    private void setIdForAllWidgets() {

        imageViewViewCombinedColour = findViewById(R.id.card_view_combined_color);
        //  cardViewCombinedColour = findViewById(R.id.card_view_combined_color);
        imageViewGreenColour = findViewById(R.id.image_view_green_color_code);
        imageViewBlueColour = findViewById(R.id.image_view_blue_color_code);
        imageViewRedColour = findViewById(R.id.image_view_red_color_code);
        textViewRedColourCount = findViewById(R.id.textViewRedColorCodeCount);
        textViewGreenColourCount = findViewById(R.id.textViewGreenColorCodeCount);
        textViewBlueColourCount = findViewById(R.id.textViewBlueColorCodeCount);

        imageViewIncrementRedColour = findViewById(R.id.image_view_red_increment);
        imageViewIncrementGreenColour = findViewById(R.id.image_view_green_increment);
        imageViewIncrementBlueColour = findViewById(R.id.image_view_blue_increment);
        imageViewDecrementRedColour = findViewById(R.id.image_view_red_decrement);
        imageViewDecrementBlueColour = findViewById(R.id.image_view_blue_decrement);
        imageViewDecrementGreenColour = findViewById(R.id.image_view_green_decrement);


    }

    @Override
    public void onClick(View view) {
        if (imageViewIncrementRedColour == view) {
            /*Here we are firstly checking is increment less than 50 or not this thinkg can be done here.
             But for making code reusability I'm creating checkincrimet()
             */
            if (checkIncriment(RED_COLOUR_COUNT)) {
                RED_COLOUR_COUNT += 1;
                /* setUpdatedCountToTextView() method update new count value to selected color
                 i.e if green color is incremented or decremented then the new value is updated in text view
                 */
                setUpdateRedColourCount(RED_COLOUR_COUNT);
                setChangeToCombineColor(RED_COLOUR_COUNT, BLUE_COLOUR_COUNT, GREEN_COLOUR_COUNT);  //This method will set the new changes to combine colour effect
            } else {
                customAlertDialog();       //By using this method I'm showing toast message if 0-250 colour range selection is exceeded
            }
        } else if (imageViewIncrementGreenColour == view) {
            if (checkIncriment(GREEN_COLOUR_COUNT)) {
                GREEN_COLOUR_COUNT += 1;
                setUpdateGreenColourCount(GREEN_COLOUR_COUNT);
                setChangeToCombineColor(RED_COLOUR_COUNT, BLUE_COLOUR_COUNT, GREEN_COLOUR_COUNT);
            } else {
                customAlertDialog();
            }
        } else if (imageViewIncrementBlueColour == view) {
            if (checkIncriment(BLUE_COLOUR_COUNT)) {
                BLUE_COLOUR_COUNT += 1;
                setUpdateBlueColourCount(BLUE_COLOUR_COUNT);
                setChangeToCombineColor(RED_COLOUR_COUNT, BLUE_COLOUR_COUNT, GREEN_COLOUR_COUNT);
            } else {
                customAlertDialog();
            }
        } else if (imageViewDecrementRedColour == view) {
            if (checkDecrement(RED_COLOUR_COUNT)) {
                RED_COLOUR_COUNT -= 1;
                setUpdateRedColourCount(RED_COLOUR_COUNT);
                setChangeToCombineColor(RED_COLOUR_COUNT, BLUE_COLOUR_COUNT, GREEN_COLOUR_COUNT);
            } else {
                customAlertDialog();
            }
        } else if (imageViewDecrementBlueColour == view) {
            if (checkDecrement(BLUE_COLOUR_COUNT)) {
                BLUE_COLOUR_COUNT -= 1;
                setUpdateBlueColourCount(BLUE_COLOUR_COUNT);
                setChangeToCombineColor(RED_COLOUR_COUNT, BLUE_COLOUR_COUNT, GREEN_COLOUR_COUNT);
            } else {
                customAlertDialog();
            }
        } else if (imageViewDecrementGreenColour == view) {
            if (checkDecrement(GREEN_COLOUR_COUNT)) {
                GREEN_COLOUR_COUNT -= 1;
                setUpdateGreenColourCount(GREEN_COLOUR_COUNT);
                setChangeToCombineColor(RED_COLOUR_COUNT, BLUE_COLOUR_COUNT, GREEN_COLOUR_COUNT);
            } else {
                customAlertDialog();
            }
        } else if (view == textViewAlertDialog) {
            alertDialog.dismiss();
        }
    }

    private void setUpdateRedColourCount(int red_colour_count) {
        textViewRedColourCount.setText(String.valueOf(red_colour_count));
    }

    private void setUpdateGreenColourCount(int green_colour_count) {
        textViewGreenColourCount.setText(String.valueOf(green_colour_count));
    }

    private void setUpdateBlueColourCount(int blue_colour_count) {
        textViewBlueColourCount.setText(String.valueOf(blue_colour_count));
    }


    @SuppressLint({"NewApi", "ResourceType"})
    private void setChangeToCombineColor(int red_colour_count, int blue_colour_count, int green_colour_count) {

        if (background instanceof ShapeDrawable) {
            // cast to 'ShapeDrawable'
            ShapeDrawable shapeDrawable = (ShapeDrawable) background;
            shapeDrawable.getPaint().setColor(ContextCompat.getColor(this, Color.RED));
        } else if (background instanceof GradientDrawable) {
            // cast to 'GradientDrawable'
            GradientDrawable gradientDrawable = (GradientDrawable) background;
            /*
            Here i have used Math.min() to lighter the color so that the stroke color means border color will be visible too
             */
            gradientDrawable.setColor(Math.min(Color.rgb(red_colour_count, green_colour_count, blue_colour_count) + (Color.rgb(red_colour_count, green_colour_count, blue_colour_count) * 1), 5));
            gradientDrawable.setStroke(3, Color.rgb(red_colour_count, green_colour_count, blue_colour_count));

        } else if (background instanceof ColorDrawable) {
            // alpha value may need to be set again after this call
            ColorDrawable colorDrawable = (ColorDrawable) background;
            colorDrawable.setColor(ContextCompat.getColor(this, Color.RED));

        }
    }

    private boolean checkDecrement(int blue_colour_count) {
        boolean returnValue;
        if (blue_colour_count <= MIN_COLOUR_CODE) {
            returnValue = false;
        } else {
            returnValue = true;
        }
        return returnValue;
    }


    private boolean checkIncriment(int blue_colour_count) {
        boolean returnValue;
        if (blue_colour_count < MAX_COLOUR_CODE) {
            returnValue = true;
        } else {
            returnValue = false;
        }
        return returnValue;
    }

    private void customAlertDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.custom_alert_dialog, null);
        setIdForAlertDialogWidgets(view);
        builder = new AlertDialog.Builder(this);

        builder.setView(view);
        alertDialog = builder.show();

        ColorDrawable back = new ColorDrawable(Color.TRANSPARENT);
        InsetDrawable inset = new InsetDrawable(back, 40);    //here margin from left and right are set
        alertDialog.getWindow().setBackgroundDrawable(inset);
        alertDialog.setCancelable(false);

        textViewAlertDialog.setOnClickListener(this);
    }

    private void setIdForAlertDialogWidgets(View view) {
        textViewAlertDialog = view.findViewById(R.id.text_view_alert_dialog_ok);
    }
}



