package com.tsystems.javaschool.tasks.calculator;

public abstract class Expression {
    public enum ExpressionType {
        // Simple expression is just a number.
        SIMPLE,

        // Complex expression has left, right operands and operator.
        COMPLEX
    }

    public abstract ExpressionType getType();

    public abstract double evaluate();
}

