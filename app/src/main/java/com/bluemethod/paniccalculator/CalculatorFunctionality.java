package com.bluemethod.paniccalculator;

/**
 * Directly controls the functionality of the calculator
 * All code in here gets displayed by Calculator.java
 *
 * @author Samuel Wyss-Duhammel
 */
public class CalculatorFunctionality{
    private String display;
    private String tempString;
    private Double left;
    private Double right;
    private String sign;
    private Boolean numNeg;


    public CalculatorFunctionality(){
        display = "";
        tempString = "";
        left = null;
        right = null;
        sign = "";
        numNeg = false;
    }


    public String getDisplay() {
        return this.display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getTempString() {
        return this.tempString;
    }

    public void setTempString(String tempString) {
        this.tempString = tempString;
    }

    public Double getLeft() {
        return this.left;
    }

    public void setLeft(Double left) {
        this.left = left;
    }

    public Double getRight() {
        return this.right;
    }

    public void setRight(Double right) {
        this.right = right;
    }

    public String getSign() {
        return this.sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Boolean getNumNeg() {
        return this.numNeg;
    }
    //Use next setter for negative number
    public void setNumNeg(Boolean numNeg) {
        this.numNeg = numNeg;
    }


    //This next method is used to establish the left/right number
    public void addtoString(int k){
        setTempString(getTempString() + k);
        setDisplay(getDisplay() + k);
    }
    //This next method is used for the decimal (not for negative)
    public void addtoString(String m){
        setTempString(getTempString() + m);
        setDisplay(getDisplay() + m);
    }

    public Double convString(String n){
        Double dnum = Double.parseDouble(n);
        return dnum;
    }

    public Double estNeg(Boolean c, Double z){
        Double y = z;
        if (c = true){
            y = y*-1;
        }
        return y;
    }

    public void addNum(Double i){
        //setDisplay(getDisplay() + i);

        if(left==null){
            setLeft(i);
        }
        else{
            setRight(i);
        }
        setDisplay(getDisplay() + " ");
    }

    //Note to Dan: Ensure the operating sign is set as a String
    public void addSign(String j){
        Double term = convString(tempString);
        if(numNeg = true){
            term = estNeg(true, term);
        }
        addNum(term);
        setTempString("");
        String equalsSign = "=";
        if(j == equalsSign){
            setDisplay("");
            evalLine();
        }
        else{
            setDisplay(getDisplay() + j);
            setSign(j);
            if(sign == "%"){
                setDisplay(getDisplay() + "*");
            }
            setDisplay(getDisplay() + " ");
        }
    }

    public void evalLine(){
        Double temp = 0.0;
        switch(sign){
            case "+":
                temp = left + right;
                break;

            case "-":
                temp = left - right;
                break;

            case "*":
                temp = left * right;
                break;

            case "/":
                temp = left / right;
                break;

            case "%":
                temp = (right*(left/100));
                break;
//Note to Dan: Make sure the symbol(√) is sent, rather than the square root method, unicode, or xml
            case "√":
                temp = Math.sqrt(left);
                break;
        }
        setDisplay(temp + "");
        setLeft(temp);
        setRight(null);
        setSign("");
        setNumNeg(false);
    }

    public void clearButton(){
        setDisplay("");
        setTempString("");
        setLeft(null);
        setRight(null);
        setSign("");
        setNumNeg(false);
    }
}
