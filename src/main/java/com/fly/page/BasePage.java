package com.fly.page;

import com.fly.entity.User;

import java.util.Stack;

public abstract class BasePage {
    public Stack<BasePage> pages;

    public abstract void printPage(User currentUser);
}
