package com.tsystems.javaschool.tasks.calculator;

public class SimpleExpression extends Expression {
    private double value;

    public SimpleExpression(double value) {
        this.value = value;

        System.out.println("SIMPLE: " + this);
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.SIMPLE;
    }

    @Override
    public double evaluate() {
        return value;
    }

    @Override
    public String toString() {
        return "SimpleExpression{" +
                "value=" + value +
                '}';
    }
}

