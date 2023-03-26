/sahouli /
import java.io.*;
import java.net.*;

public class recepteur {
    private static final int BUFFER_SIZE = 1024;
    private static final int PORT = 4569;

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(PORT);

            // Création d'un fichier pour enregistrer les données reçues
            File file = new File("received.txt");
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            // Réception des données par paquets UDP
            byte[] buffer = new byte[BUFFER_SIZE];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            while (true) {
                socket.receive(packet);
                if (new String(packet.getData(), 0, packet.getLength()).equals("END")) {
                    break;
                }
                fileOutputStream.write(packet.getData(), 0, packet.getLength());
            }

            // Fermeture des flux et de la socket
            fileOutputStream.close();
            socket.close();

            System.out.println("Fichier reçu avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
