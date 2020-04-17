import javax.xml.crypto.Data;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientDemo {
    public static void main(String[] args) {
        new ClientDemo();
    }
    private DatagramSocket datagramSocket;
    private InetAddress address;
    private int port = 6780;
    private Scanner input = new Scanner(System.in);

    public ClientDemo(){
        System.out.println("Please type in your name: ");
        String name = input.nextLine();

        try{
            UserInput userInput = new UserInput(this, name);
            Thread userInputThread = new Thread(userInput);
            userInputThread.start();

            datagramSocket = new DatagramSocket();
            address = InetAddress.getByName("localhost");
            System.out.println("Connected to the chat server... ");

            while(true) {
                System.out.println("(DataEcho) " + receive(datagramSocket));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String receive(DatagramSocket datagramSocket) {
        try{
            byte[] inArray = new byte[1000];
            DatagramPacket inPacket = new DatagramPacket(inArray, inArray.length);
            datagramSocket.receive(inPacket);
            return new String(inArray, 0, inPacket.getLength());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public void send(String message) {
        sendTo(datagramSocket, address, port, message);
    }

    private void sendTo(DatagramSocket datagramSocket, InetAddress address, int port, String message){
        try{
            byte[] outMessage = message.getBytes();
            DatagramPacket outPacket = new DatagramPacket(outMessage, outMessage.length, address, port);
            datagramSocket.send(outPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

