package com.example.depremler;

import android.annotation.SuppressLint;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

public class DepremModel {
    private Date tarih_saat;
    private double enlem;
    private double boylam;
    private double derinlik;
    private double mD;
    private double mL;
    private double mw;
    private String yer;
    private String cozum_niteligi;

    @SuppressLint("NewApi")
    public static ArrayList<DepremModel> GetData() {
        try {
            Document doc = Jsoup.connect("http://www.koeri.boun.edu.tr/scripts/lst4.asp").get();
            Element data = doc.selectFirst("pre");
            ArrayList<DepremModel> models = new ArrayList<>();
            ArrayList<String> lines = Arrays.stream(data.text().split("\n")).skip(6).collect(Collectors.toCollection(ArrayList::new));
            for (String line : lines) {
                try {
                    models.add(new DepremModel(line));
                } catch (Exception e) {
                    Log.d("DepremItem", e.getMessage() + "-" + line);
                }
            }
            return models;
        } catch (IOException e) {
            return null;
        }
    }

    public DepremModel() {
    }

    @SuppressLint("NewApi")
    public DepremModel(String data) {
        data = data.replace("-.-", "0").replaceAll("( )+", " ").trim();
        String[] cols = data.split(" ");
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").parse(cols[0] + " " + cols[1]);
        } catch (ParseException e) {

        }
        this.tarih_saat = date;
        this.enlem = Double.parseDouble(cols[2]);
        this.boylam = Double.parseDouble(cols[3]);
        this.derinlik = Double.parseDouble(cols[4]);
        this.mD = Double.parseDouble(cols[5]);
        this.mL = Double.parseDouble(cols[6]);
        this.mw = Double.parseDouble(cols[7]);
        if (!data.contains("Revize")) {
            this.yer = String.join(" ", Arrays.stream(cols).skip(8).collect(Collectors.toCollection(ArrayList::new)));
            this.yer = this.yer.replace("İlksel", "").trim();
            this.cozum_niteligi = "İlksel";
        } else {
            cols = (String[]) Arrays.stream(cols).skip(8).toArray();
            this.cozum_niteligi = String.join(" ", Arrays.stream(cols).skip(cols.length - 3).collect(Collectors.toCollection(ArrayList::new)));
            this.yer = String.join(" ", cols).replace(cozum_niteligi, "").trim();
        }
    }

    public Date getTarih_saat() {
        return tarih_saat;
    }

    public void setTarih_saat(Date tarih_saat) {
        this.tarih_saat = tarih_saat;
    }

    public double getEnlem() {
        return enlem;
    }

    public void setEnlem(double enlem) {
        this.enlem = enlem;
    }

    public double getBoylam() {
        return boylam;
    }

    public void setBoylam(double boylam) {
        this.boylam = boylam;
    }

    public double getDerinlik() {
        return derinlik;
    }

    public void setDerinlik(double derinlik) {
        this.derinlik = derinlik;
    }

    public double getmD() {
        return mD;
    }

    public void setmD(double mD) {
        this.mD = mD;
    }

    public double getmL() {
        return mL;
    }

    public void setmL(double mL) {
        this.mL = mL;
    }

    public double getMw() {
        return mw;
    }

    public void setMw(double mw) {
        this.mw = mw;
    }

    public String getYer() {
        return yer;
    }

    public void setYer(String yer) {
        this.yer = yer;
    }

    public String getCozum_niteligi() {
        return cozum_niteligi;
    }

    public void setCozum_niteligi(String cozum_niteligi) {
        this.cozum_niteligi = cozum_niteligi;
    }
}
