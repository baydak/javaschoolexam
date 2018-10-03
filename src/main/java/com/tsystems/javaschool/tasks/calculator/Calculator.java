package com.tsystems.javaschool.tasks.calculator;


public class Calculator {

    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */
    public String evaluate(String statement) {
        double value;

        try {
            value = parse(statement).evaluate();
        }
        catch (RuntimeException e) {
            return null;
        }

        if (value == Math.floor(value) && !Double.isInfinite(value)) {
            int intVal = (int)value;

            return Integer.toString(intVal);
        }

        // 4 digits rounding.
        int rounded = (int)(value * 10000);

        return Double.toString((double)rounded / 10000);
    }

    private Expression parse(String statement) {
        System.out.println("Parsing: " + statement);

        StringBuilder buff = new StringBuilder();

        Expression left = null;
        Expression right = null;
        Character operator = null;
        Expression prev = null;
        Character prevOperator = null;

        for (int i = 0; i < statement.length(); i++) {
            char ch = statement.charAt(i);

            if (Character.isDigit(ch) || ch == Tokens.DOT) {
                // Simple expression (number).
                buff.append(ch);
            }
            else {
                if (buff.length() > 0) {
                    Expression expr = new SimpleExpression(Double.parseDouble(buff.toString()));

                    buff.setLength(0);

                    if (left == null) {
                        left = expr;
                    }
                    else {
                        if (operator == null) {
                            throwInvalidStatement(statement);
                        }

                        right = expr;
                    }
                }

                switch (ch) {
                    case Tokens.OPEN_PARENTHESIS : {
                        int idx = statement.lastIndexOf(Tokens.CLOSE_PARENTHESIS);

                        if (idx == -1) {
                            throwInvalidStatement(statement);
                        }

                        Expression expr = parse(statement.substring(i + 1, idx));

                        // Skip already parsed.
                        i = idx;

                        if (left == null) {
                            left = expr;
                        }
                        else {
                            if (operator == null) {
                                return throwInvalidStatement(statement);
                            }

                            right = expr;

                            if (prev != null) {
                                left = mkComplexExpression(prev, mkComplexExpression(left, right, operator), prevOperator);
                            }

                            left = mkComplexExpression(left, right, operator);

                            operator = null;
                            right = null;
                        }
                    }

                    case Tokens.CLOSE_PARENTHESIS : {
                        // No op.
                        break;
                    }

                    case Tokens.PLUS:
                    case Tokens.MINUS:
                    case Tokens.MULTIPLY:
                    case Tokens.DIVIDE: {
                        if (operator == null) {
                            operator = ch;
                        }
                        else {
                            // Multi-expression (like 2+3*4). We need to take into account operators priority.
                            if (left == null || right == null) {
                                throwInvalidStatement(statement);
                            }

                            // Need to reverse execution sequence.
                            if (morePriority(operator, ch)) {
                                prev = left;
                                left = right;
                                prevOperator = operator;
                            }
                            else {
                                left = mkComplexExpression(left, right, operator);
                            }

                            operator = ch;

                            right = null;
                        }

                        break;
                    }

                    case Tokens.WHITESPACE : {
                        // No op.
                        break;
                    }

                    default: {
                        throw new RuntimeException("Unsupported character: " + ch);
                    }
                }
            }
        }

        if (buff.length() > 0) {
            if (left == null && operator == null) {
                return new SimpleExpression(Double.parseDouble(buff.toString()));
            }

            right = new SimpleExpression(Double.parseDouble(buff.toString()));
        }

        if (left == null || right == null || operator == null) {
            throwInvalidStatement(statement);
        }

        if (prev != null) {
            return mkComplexExpression(prev, new ComplexExpression(left, right, operator), prevOperator);
        }

        return mkComplexExpression(left, right, operator);
    }

    private Expression throwInvalidStatement(String statement) {
        throw new RuntimeException("Invalid statement: " + statement);
    }

    private static boolean morePriority(Character operator1, Character operator2) {
        switch (operator1) {
            case Tokens.MULTIPLY:
            case Tokens.DIVIDE: {
                // If first operator is multiply or divide, execute in "normal order";
                return false;
            }
            case Tokens.PLUS:
            case Tokens.MINUS: {
                return operator2 == Tokens.MULTIPLY || operator2 == Tokens.DIVIDE;
            }

            default: {
                throw new RuntimeException("Invalid operator: " + operator1);
            }
        }
    }

    private ComplexExpression mkComplexExpression(Expression left, Expression right, Character operator) {
        if (operator == Tokens.DIVIDE && Double.compare(0, right.evaluate()) == 0) {
            throw new RuntimeException("Divided by 0.");
        }

        return new ComplexExpression(left, right, operator);
    }
}
