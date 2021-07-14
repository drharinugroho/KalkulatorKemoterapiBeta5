package com.example.android.kalkulatorkemoterapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.Math;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * Fungsi tombol hitung
     */

    public void hitung(View view) {

        //mengubah input usia ke variable finalUsiaPasien
        EditText usia = (EditText) findViewById(R.id.usia);
        String usiaPasien = usia.getText().toString();
        int finalUsiaPasien = Integer.parseInt(usiaPasien);

        //mengubah input berat badan ke variable finalBeratBadan
        EditText berat = (EditText) findViewById(R.id.beratBadan);
        String beratBadan = berat.getText().toString();
        double finalBeratBadan = Double.parseDouble(beratBadan);

        //mengubah input tinggi badan ke variable finalTinggiBadan
        EditText tinggi = (EditText) findViewById(R.id.tinggiBadan);
        String tinggiBadan = tinggi.getText().toString();
        double finalTinggiBadan = Double.parseDouble(tinggiBadan);

        //mengubah input serum Kreatinin ke variable finalSerumKreatinin
        EditText kadarSK = (EditText) findViewById(R.id.serumKreatinin);
        String serumKreatinin = kadarSK.getText().toString();
        double finalSerumKreatinin = Double.parseDouble(serumKreatinin);

        //menghitung IMT
        //IMT = BB / ((TB/100)x(TB/100))

        double TBjadiMeter = (finalTinggiBadan / 100);
        double TbjadiMeterKuadrat = TBjadiMeter * TBjadiMeter;
        double isiIMT = finalBeratBadan / TbjadiMeterKuadrat;
        double isiIMTbulat = Math.round(isiIMT * 100);
        double isiIMTbulatFinal = isiIMTbulat / 100;
        TextView viewIMT = (TextView) findViewById(R.id.IndeksMassaTubuh);
        viewIMT.setText((double) isiIMTbulatFinal + " kg/m2");


        //Hitung LPT
        //LPT = akar kuadrat dari ((BB x TB)/3600)

        double isiLPT = (finalBeratBadan * finalTinggiBadan) / 3600;
        double luasPermukaanTubuh = Math.sqrt(isiLPT);
        double luasPermukaanTubuhBulat = Math.round(luasPermukaanTubuh * 100);
        double luasPermukaanTubuhBulatFinal = luasPermukaanTubuhBulat / 100;

        //menampilkan Luas Permukaan Tubuh
        TextView viewLPT = (TextView) findViewById(R.id.LuasPermukaanTubuh);
        viewLPT.setText((double) luasPermukaanTubuhBulatFinal + " m2");

        //menghitung dosis Paclitaxel = LPT x 175 mg
        double dosisPaclitaxel = luasPermukaanTubuh * 175;

        //menampilkan kadar Paclitaxel
        TextView kadarPaclitaxel = (TextView) findViewById(R.id.paclitaxel);
        kadarPaclitaxel.setText((int) dosisPaclitaxel + " mg");


        //Hitung GFR
        //GFR = ((140-Umur) x BeratBadan x 0.85) / (72 x SK)
        double GFR = (((140 - finalUsiaPasien) * finalBeratBadan * 0.85) / (72 * finalSerumKreatinin));
        double GFRBulat = Math.round(GFR * 100);
        double GFRBulatFinal = GFRBulat / 100;
        //menampilkan GFR
        TextView viewGFR = (TextView) findViewById(R.id.GFR_Normal);
        viewGFR.setText((double) GFRBulatFinal + " mL/min");

        //Hitung GFR Obese
        //GFR Obese = ((146-Umur) x BeratBadan x 0.287) + (9.74 * TB * TB) / (60 x SK)
        double GFRObese = ((146 - finalUsiaPasien) * ((finalBeratBadan * 0.287) + (9.74 * TbjadiMeterKuadrat))) / (60 * finalSerumKreatinin);
        double GFRObeseBulat = Math.round(GFRObese * 100);
        double GFRObeseBulatFinal = GFRObeseBulat / 100;

        //menampilkan GFR Obese
        TextView viewGFRobese = (TextView) findViewById(R.id.GFR_Obese);
        viewGFRobese.setText((double) GFRObeseBulatFinal + " mL/min");


        //menghitung dosis Carboplatin = (GFR + 25) x 6
        double dosisCarboplatin = (GFR + 25) * 6;

        //menampilkan kadar Carboplatin Normal
        TextView kadarCarboplatin = (TextView) findViewById(R.id.carboplatin);
        kadarCarboplatin.setText((int) dosisCarboplatin + " mg");

        //menghitung dosis Carboplatin Obese = (GFR Obese + 25) x 6
        double dosisCarboplatinObese = (GFRObese + 25) * 6;

        //menampilkan kadar Carboplatin Obese
        TextView kadarCarboplatinObese = (TextView) findViewById(R.id.carboplatinObese);
        kadarCarboplatinObese.setText((int) dosisCarboplatinObese + " mg");

        //menghitung dosis Carboplatin GFR 40-60 = 250 x LPT
        double dosisCarboplatinMildAki = 250 * luasPermukaanTubuh;

        //menampilkan kadar Carboplatin Obese
        TextView kadarCarboplatinMildAki = (TextView) findViewById(R.id.carboplatin4060);
        kadarCarboplatinMildAki.setText((int) dosisCarboplatinMildAki + " mg");

        //menghitung dosis Carboplatin GFR 40 = 200 x LPT
        double dosisCarboplatinSevereAki = 200 * luasPermukaanTubuh;

        //menampilkan kadar Carboplatin Obese
        TextView kadarCarboplatinSevereAki = (TextView) findViewById(R.id.carboplatin40);
        kadarCarboplatinSevereAki.setText((int) dosisCarboplatinSevereAki + " mg");


    }


}
