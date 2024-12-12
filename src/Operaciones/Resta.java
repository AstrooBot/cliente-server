package Operaciones;

public class Resta implements Operacion{
    int number = 0;
    @Override
    public int operar(int b) {
        return number-= b ;
    }
    @Override
    public float operar(float a, float b) {
        return a - b;
    }
    @Override
    public double operar(double a, double b) {
        return a - b;
    }
}
