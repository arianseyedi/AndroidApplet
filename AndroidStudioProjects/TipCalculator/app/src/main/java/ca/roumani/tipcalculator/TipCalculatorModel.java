package ca.roumani.tipcalculator;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by @author Arian Seyedi on 7/6/17.
 */
public class TipCalculatorModel {
    private int numOfPpl;
    private double amount;
    private int tipPercentage;
    private double perPerson;
    private double totAmount;

    // constructor, initialize
    public TipCalculatorModel(String amount, String tipPercentage, String numOfPpl) {
        this.numOfPpl = Integer.parseInt(numOfPpl);
        this.amount = Double.parseDouble(amount);
        this.tipPercentage = Integer.parseInt(tipPercentage);

    }

    public String getTotal() {
        totAmount = amount + (amount * tipPercentage) / 100;
        String totAmountString = Double.toString(totAmount);
        return totAmountString;
    }

    public String getPerPerson() {
        perPerson = (amount + (amount * tipPercentage) / 100) / numOfPpl;
        String perPersonString = String.format("%.2f", perPerson);
        return perPersonString;
    }
}