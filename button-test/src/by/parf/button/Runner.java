package by.parf.button;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListener;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

/**
 * Created by parf on 28.8.15.
 */
public class Runner {
    public static void main(String args[]) throws InterruptedException {

        final GpioController gpio = GpioFactory.getInstance();
        final GpioPinDigitalInput button = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02, PinPullResistance.PULL_DOWN);

        button.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                System.out.print("--> GPIO PIN STATE CHANGE: " + event.getPin() + event.getState());
            }
        });

        System.out.println(" ... complete the GPIO #02 circuit and see the listener feedback here in the console.");

        for(;;) {
            Thread.sleep(500);
        }

        // gpio.shutdown();
    }
}
