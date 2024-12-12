import java.net.*;
import java.util.ArrayList;
import java.util.List;

import Operaciones.*;
import java.io.*;

public class Servidor {
    ServerSocket socketServidor = null;
    Socket socketCliente = null;
    DataInputStream in = null;
    DataOutputStream out = null;
    int number = 0;
    Operacion suma = new Suma();
    Operacion resta = new Resta();
    Operacion multiplicacion = new Multiplicar();
    Operacion dividir = new Dividir();    
    Operacion[] operaciones = {suma, resta, multiplicacion, dividir};

    List<Integer> numeros = new ArrayList<>();

    public Servidor(int puerto) {

        try {
            socketServidor = new ServerSocket(puerto);
            System.out.println("Servidor escuchando...");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void ejecucion() {
        try {
            for (int numCli = 1; numCli <= 2; numCli++) {
                socketCliente = socketServidor.accept();
                in = new DataInputStream(new BufferedInputStream(socketCliente.getInputStream()));
                out = new DataOutputStream(socketCliente.getOutputStream());
                number = 0;
                System.out.println("Servidor conectado al cliente " + numCli);

                this.notificar("Servidor: Escriba los numeros a sumar. Para finalizar escriba 0 .");
                this.imprimir(numCli);
            }
            System.out.println("maximo de clientes alcanzado\nSe cierra el servidor");
            this.cerrar();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void notificar(String notificacion) {
        try {
            out.writeUTF(notificacion);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int operar(Operacion operador , List<Integer> lista) {
        for (int elemento : lista) {
            number = operador.operar(elemento);
        }
        return number;
    }

    public void calculos(List<Integer> lista) {
        for (Operacion operacion : operaciones) {
            
            this.notificar("El resultado de la operacion es: " + this.operar(operacion, lista));
        }
    }

    public void imprimir(int numCliente) {
        try {
            String line = "";
            while (!line.equals("0")) {
                line = in.readUTF();
                System.out.println("Cliente " + numCliente + " : " + line);
                numeros.add(Integer.parseInt(line));

            }
            this.calculos(numeros);
            System.out.println("El cliente ha cerrado la conexion...");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void cerrar() {
        try {
            in.close();
            out.close();
            socketServidor.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        Servidor servidor = new Servidor(1234);
        servidor.ejecucion();
    }
}
