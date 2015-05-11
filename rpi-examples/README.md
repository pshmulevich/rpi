Sample Pi4J GPIO Project
------------------------
Compile project as

`mvn clean package`

Run `LedGpioExample` class as 

`sudo java -classpath ./target/classes:/opt/pi4j/lib/'*' rpi.examples.gpio.LedGpioExample`

Run `TriggerGpioExample` class as 

`sudo java -classpath ./target/classes:/opt/pi4j/lib/'*' rpi.examples.gpio.TriggerGpioExample`

When you click on the button, the LED turns on.
When the button is released, the LED turns off.
