#include <HCSR04Ultrasonic.h>

const int pinTrigger = 12;  
const int pinEcho = 14;    
const int pinSwitch = 27; 
const int pinLED = 5;   

HCSR04Ultrasonic ultrasonic(pinTrigger, pinEcho);

void setup() {
  Serial.begin(115200);

  pinMode(pinSwitch, INPUT);
  pinMode(pinLED, OUTPUT);

  Serial.println("OK");
}

void loop() {
  int etatSwitch = digitalRead(pinSwitch);

  float distance = ultrasonic.getDistance();

  Serial.print("Distance : ");
  Serial.print(distance);
  Serial.println(" cm");

  if (etatSwitch == HIGH) {
    if (distance <= 20) {
      digitalWrite(pinLED, HIGH);
    } 
    else {
      digitalWrite(pinLED, LOW);
    }
  } 
  else {
    if (distance > 20) {
      digitalWrite(pinLED, HIGH);
    } 
    else {
      digitalWrite(pinLED, LOW);
    }
  }
  delay(500);
}
