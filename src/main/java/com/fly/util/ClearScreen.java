package com.fly.util;

public class ClearScreen {
    public static void clear() {
        System.out.println(new String(new char[50]).replace("\0", "\r\n"));
    }
}
