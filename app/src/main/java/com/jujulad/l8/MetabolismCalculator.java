package com.jujulad.l8;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
// Klasa,a zarazem główna aktywność, obsługująca pola tekstowe oraz przycisk i akcje z nimi związane
public class MetabolismCalculator extends AppCompatActivity {
    //Pola klasy
    private Calendar calendar;
    private TextView dateView;
    private Spinner gender;
    private Spinner method;
    private TextView weight;
    private TextView height;
    private TextView output;
    private int myYear, myMonth, myDay;
    private double age;


    @Override
    //Metoda wywoływana przy otworzeniu aplikacji
    //Odpowiednim polom przypisane są wartości odpowiednich pól tekstowych za pomocą ich ID
    //Kalendarz
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metabolism_calculator);
        dateView = findViewById(R.id.age);
        gender=findViewById(R.id.gender);
        method=findViewById(R.id.method);
        weight=findViewById(R.id.weight);
        height=findViewById(R.id.height);
        output=findViewById(R.id.output);
        calendar = Calendar.getInstance();
    }
    //Metoda do wyświetlania daty urodzin w polu tekstowym
    private void updateLabel() {
        String myFormat = "dd/MM/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dateView.setText(sdf.format(calendar.getTime()));
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        //Metoda wykonywująca się po zatwierdzeniu wyboru daty urodzin w DatePickerze
        // Odbieramy wybraną datę i na jej podstawie obliczamy wiek
        //Po sprawdzeniu poprawności wieku odpowiednia wartość jest wyświetlania
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            LocalDate today=LocalDate.now();
            myYear = year;
            myMonth = month;
            myDay = day;
            age=today.getYear()-myYear;
            if(today.getMonthValue()<myMonth || today.getMonthValue()==myMonth && today.getDayOfMonth()<=myDay){
                age--;
            }
            if (age>0) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
                updateLabel();
            }

            else dateView.setText("Incorrect date of birth");
    }

    };
    //Metoda tworząca nowy DatePicker po kilknięciu w odpowiednie pole tekstowe
    public void chooseAge(View view) {
        new DatePickerDialog(MetabolismCalculator.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
    //Metoda wywołująca się po przyciśnięciu przycisku "Calculate"
    //Sprawdzana jest również poprawność wpisania danych
    //A anstępnie wywoływana jest odpowiednia metoda do obliczenia PPM
    public void calculate(View view) {
            try {
                double w = Double.parseDouble(weight.getText().toString());
                double h = Double.parseDouble(height.getText().toString());
                if(w>0 &&h>0) {
                    PPM metabolism = new PPM(String.valueOf(gender.getSelectedItem()), w, age, h);
                    String methodChoice = method.getSelectedItem().toString();
                    if (methodChoice.equals("Mifflin method")) metabolism.calculateMifflin();
                    else metabolism.calculateHarris();
                    output.setText(String.valueOf(metabolism.getMetabolism()) + "  kcal");
                }else{
                    if(w<0) weight.setText("Incorrect weight");
                    if(h<0) height.setText("Incorrect height");
                }
            } catch (NumberFormatException e) {
                output.setText("Cannot calculate");
            }

    }
}



