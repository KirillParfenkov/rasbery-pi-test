package parf.by.light;

import com.pi4j.io.gpio.*;

import java.util.Scanner;

/**
 * Created by parf on 28.8.15.
 */
public class Runner {
    public static void main(String [] args) throws InterruptedException{

        System.out.println("<--PI4J--> GPIO Control Example ... started.");
        final GpioController gpio = GpioFactory.getInstance();
        final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyLED", PinState.HIGH);
        pin.setShutdownOptions(true, PinState.LOW);


        final GpioPinAnalogOutput analogOutput = gpio.provisionAnalogOutputPin(RaspiPin.GPIO_15);


        Scanner scanner = new Scanner(System.in);
        String command;
        while (true) {
            command = scanner.nextLine();

            if (command.equals("low")) {
                pin.low();
            }

            if (command.equals("high")) {
                pin.high();
            }

            if (command.contains("set")) {
                int val = Integer.valueOf(command.substring(3));
                System.out.println("set - " + val);
                analogOutput.setValue(val/1000);
            }

            if (command.equals("exit")) {
                gpio.shutdown();
                break;
            }
        }


        /*System.out.println("--> GPIO state should be: ON");

        Thread.sleep(5000);

        pin.low();
        System.out.println("--> GPIO state should be: OFF");

        Thread.sleep(5000);

        pin.toggle();
        System.out.println("--> GPIO state should be: ON");

        Thread.sleep(5000);

        pin.toggle();
        System.out.println("--> GPIO state should be: OFF");

        Thread.sleep(5000);

        System.out.println("--> GPIO state should be: ON for only 1 second");
        pin.pulse(1000, true); // set second argument to 'true' use a blocking call

        gpio.shutdown(); */
    }

}
