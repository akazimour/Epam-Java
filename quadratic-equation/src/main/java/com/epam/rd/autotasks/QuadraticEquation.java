package com.epam.rd.autotasks;

import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Scanner;

import static java.lang.Math.sqrt;

public class QuadraticEquation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();
        double d = (b*b) - (4*a*c);

        if (d < 0){
            System.out.println("no roots");
        } else if (d>0) {
            DecimalFormat df=new DecimalFormat("#");
            double t1 = (-b - Math.sqrt(d)) / (2*a);
            double t2 = (-b + Math.sqrt(d)) / (2*a);
            var x1 = t1 % 1 != 0 ? t1 : df.format(t1);
            var x2 = t2 % 1 != 0 ? t2 : df.format(t2);
            System.out.println(x1+" "+x2);
        } else if (d==0) {
            DecimalFormat df=new DecimalFormat("#");
            double d1 = -b / (2*a);
            var x1 = d1 % 1 != 0 ? d1 : df.format(d1);
            System.out.println(x1);
        }



    }

}