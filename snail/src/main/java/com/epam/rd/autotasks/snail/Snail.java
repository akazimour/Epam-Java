package com.epam.rd.autotasks.snail;

import java.util.Scanner;

public class Snail
{
    public static void main(String[] args)
    {
        int[] arr = new int[3];
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            arr[i] = scanner.nextInt();
        }
        int a = Math.abs(arr[0]);
        int b = Math.abs(arr[1]);
        int h = Math.abs(arr[2]);
        int c = a-b;
        int val = c;
        int counter = 0;
        if (val < h) {
            while (val < h) {
                if (a <= b && h <= a) {
                    System.out.println("1");
                    break;
                } else if (a <= b && h > a) {
                    System.out.println("Impossible");
                    break;
                } else if (val == a - b) {
                    counter++;
                }
                val = c + a;
                c = val - b;
                counter++;
            }
            if (counter != 0) {
                System.out.println(counter);
            }
        }else
            System.out.println("1");
    }

    }

