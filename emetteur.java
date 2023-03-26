/sahouli/

import java.io.*;
import java.net.*;

public class emetteur {
    private static final int BUFFER_SIZE = 1024;
    private static final int PORT = 4569;

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName("localhost");

            // Création d'un fichier à envoyer
            File file = new File("bino.png");
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[BUFFER_SIZE];

            // Lecture du fichier et envoi par paquets UDP
            int bytesRead = 0;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                DatagramPacket packet = new DatagramPacket(buffer, bytesRead, address, PORT);
                socket.send(packet);
            }

            // Envoi du paquet final pour indiquer la fin de la transmission
            byte[] endData = "END".getBytes();
            DatagramPacket endPacket = new DatagramPacket(endData, endData.length, address, PORT);
            socket.send(endPacket);

            // Fermeture des flux et de la socket
            fileInputStream.close();
            socket.close();

            System.out.println("Fichier envoyé avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

