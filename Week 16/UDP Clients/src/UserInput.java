import java.util.Scanner;

public class UserInput implements Runnable{

    private ClientDemo clientDemo;
    private String name;

    public UserInput(ClientDemo clientDemo, String name){
        this.clientDemo = clientDemo;
        this.name = name;
    }

    @Override
    public void run() {
        Scanner input = new Scanner(System.in);
        while(true){
            String message = input.nextLine();
            clientDemo.send(name + ": " + message);

            if(message.equalsIgnoreCase("quit")){
                break;
            }
        }
    }
}
