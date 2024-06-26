package org.example;

import org.example.command.Light;
import org.example.command.LightOffCommand;
import org.example.command.LightOnCommand;
import org.example.command.SimpleRemoteControl;

public class RemoteControlTest {
    public static void main(String[] args) {
        SimpleRemoteControl remote = new SimpleRemoteControl();
        Light light = new Light();
        LightOnCommand lightOn = new LightOnCommand(light);
        remote.setCommand(lightOn);
        remote.buttonWasPressed();

        SimpleRemoteControl remote2 = new SimpleRemoteControl();
        LightOffCommand lightOff = new LightOffCommand(light);
        remote2.setCommand(lightOff);
        remote2.buttonWasPressed();
    }
}
