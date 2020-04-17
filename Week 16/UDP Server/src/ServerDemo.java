import javax.swing.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class ServerDemo {
    //static HashMap<InetAddress, Integer> clients = new HashMap<>(); //For production
    static HashMap<Integer, InetAddress> clients = new HashMap<>(); //Used to test locally that messages are echoed to all clients

    public static void main(String[] args) {
        try{
            DatagramSocket datagramSocket = new DatagramSocket(6780);
            System.out.println("Listening...");
            byte[] recieveArray = new byte[1000]; //Max byte size in UDP = 1500

            while(true){
                DatagramPacket inPacket = new DatagramPacket(recieveArray, recieveArray.length);

                datagramSocket.receive(inPacket); //Receives data from client og program waits

                //clients.put(inPacket.getAddress(), inPacket.getPort()); //For production
                clients.put(inPacket.getPort(), inPacket.getAddress()); //For testing

                String messageIn = new String(recieveArray, 0, inPacket.getLength());

                for(Map.Entry<Integer, InetAddress> entry : clients.entrySet()){
                    //sendTo(datagramSocket, entry.getKey(), entry.getValue(), messageIn); //For production
                    sendTo(datagramSocket, entry.getValue(), entry.getKey(), messageIn); //For Testing

                    System.out.println("'" + messageIn + "'" + " has been sent to " + entry.getKey() + "/" + entry.getValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void sendTo(DatagramSocket datagramSocket, InetAddress address, int port, String message){
        try{
            byte[] outMessage = message.getBytes();
            DatagramPacket outPacket = new DatagramPacket(outMessage, outMessage.length, address, port);
            datagramSocket.send(outPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
