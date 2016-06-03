package com.softacad.snippets.math;

public class ComplexNumber {
    private double realPart;
    private double imaginaryPart;

    public ComplexNumber(double realPart, double imagineryPart) {
        this.realPart = realPart;
        this.imaginaryPart = imagineryPart;
    }

    public double getRealPart() {
        return realPart;
    }

    public double getImaginaryPart() {
        return imaginaryPart;
    }

    public ComplexNumber add(ComplexNumber other) {
        if (other == null) {
            throw new IllegalArgumentException("Argument should not be null");
        }

        double newReal = realPart + other.getRealPart();
        double newImaginery = imaginaryPart + other.getImaginaryPart();
        return new ComplexNumber(newReal, newImaginery);
    }

    public ComplexNumber multiply(ComplexNumber other) {
        if (other == null) {
            throw new IllegalArgumentException("Argument should not be null");
        }

        double newReal = realPart * other.realPart
                - imaginaryPart * other.imaginaryPart;

        double newImaginary = realPart * other.imaginaryPart
                + imaginaryPart * other.realPart;

        return new ComplexNumber(newReal, newImaginary);
    }

    @Override
    public String toString() {
        return "(" + realPart + ", " + imaginaryPart + ")";
    }
}
