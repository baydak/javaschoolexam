package com.tsystems.javaschool.tasks.calculator;

public class ComplexExpression extends Expression {
    private Expression left;

    private Expression right;

    private char operator;

    public ComplexExpression(Expression left, Expression right, char operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;

        System.out.println("COMPLEX: " + this);
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.COMPLEX;
    }

    @Override
    public double evaluate() {
        switch (operator) {
            case Tokens.PLUS: {
                return left.evaluate() + right.evaluate();
            }

            case Tokens.MINUS: {
                return left.evaluate() - right.evaluate();
            }

            case Tokens.MULTIPLY: {
                return left.evaluate() * right.evaluate();
            }

            case Tokens.DIVIDE: {
                return left.evaluate() / right.evaluate();
            }

            default: {
                throw new RuntimeException("Invalid operator: " + operator);
            }
        }
    }

    @Override
    public String toString() {
        return "ComplexExpression{" +
                "left=" + left +
                ", right=" + right +
                ", operator=" + operator +
                '}';
    }
}
