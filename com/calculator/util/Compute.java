package com.calculator.util;

public class Compute {
    public static double addition(double a, double b){
        return a+b;
    }
    public static double subtraction(double a,double b){
        return a-b;
    }
    public static double multiplication(double a,double b){
        return a*b;
    }
    public static double division(double a,double b){
        return a/b;
    }
    public static double modulus(double a, double b){
        return a%b;
    }
    public static double sine(double angle){
        double angleInRadians = Math.toRadians(angle);
        double sineValue = Math.sin(angleInRadians);
        return  sineValue;
    }
    public static double cosine(double angle){
        double angleRadians = Math.toRadians(angle);
        double cosValue = Math.cos(angleRadians);
        return cosValue;
    }
    public static double tangent(double angle){
        double angleRadians=Math.toRadians(angle);
        double tangentValue = Math.tan(angleRadians);
        return tangentValue;
    }
    public static double arcSine(double angle){
        double angleInRadians = Math.toRadians(angle);
        double sineValue = Math.asin(angleInRadians);
        return  sineValue;
    }
    public static double arcCosine(double angle){
        double angleRadians = Math.toRadians(angle);
        double cosValue = Math.acos(angleRadians);
        return cosValue;
    }
    public static double arcTangent(double angle){
        double angleRadians=Math.toRadians(angle);
        double tangentValue = Math.atan(angleRadians);
        return tangentValue;
    }
    public static double pow(double base,double exponent){
        double result=1.0;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;
    }
    public static double log(double num){
        return Math.log(num) ;
    }
    public static double sqrt(double num){
        return Math.sqrt(num);
    }
}
