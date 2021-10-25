package com.example.depremler;

import android.content.res.Resources;
import android.graphics.Color;

import androidx.annotation.ColorInt;

public enum SetColor {
    Information(R.color.Information),
    Safety(R.color.Secure),
    Caution(R.color.Caution),
    Warning(R.color.Warning),
    Danger(R.color.Danger);

    public int Id;

    SetColor(int id) {

        Id = id;
    }
}
