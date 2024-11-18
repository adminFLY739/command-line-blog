package com.fly.page;

import java.util.Stack;

public abstract class BasePage {
    public Stack<BasePage> pages;
    public abstract void printPage() throws InterruptedException;
}
