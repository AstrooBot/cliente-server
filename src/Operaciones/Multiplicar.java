package Operaciones;

public class Multiplicar implements Operacion{
    int number = 1;
    @Override
    public int operar(int b) {
        return number*= b;
    }
    @Override
    public float operar(float a, float b) {
        return a * b;
    }
    @Override
    public double operar(double a, double b) {
        return a * b;
    }
}
