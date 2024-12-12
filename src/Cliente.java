
import java.io.*;
import java.net.*;

public class Cliente {

    Socket socket = null;
    DataOutputStream out = null;
    DataInputStream in = null;
    Entrada entrada = null;

    public Cliente(String direccion, int puerto) {

        try {
            socket = new Socket(direccion, puerto);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            entrada = new Entrada();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void imprimir() {
        try {
            String line = in.readUTF();
            System.out.println(line);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void ejecucion() {
        this.imprimir();
        while (!entrada.message.equals("0")) {
            this.escribir();
        }
        this.imprimir();
        this.imprimir();
        this.imprimir();
        this.imprimir();
        this.imprimir();
        this.cerrar();

    }

    public void escribir() {

        entrada.getMessages();
        try {
            out.writeUTF(entrada.message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void cerrar() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        Cliente cliente = new Cliente("127.0.0.1", 1234);
        cliente.ejecucion();
    }
}
