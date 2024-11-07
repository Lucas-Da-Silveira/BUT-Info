#include <Ultrasonic.h>
#define TRIGGER_PIN  12
#define ECHO_PIN     14

 
const int pinSwitch = 27; 
const int pinLED = 5;   

Ultrasonic ultrasonic(TRIGGER_PIN, ECHO_PIN);

void setup() {
  Serial.begin(115200);

  pinMode(pinSwitch, INPUT);
  pinMode(pinLED, OUTPUT);

  Serial.println("OK");
}

void loop() {
  int etatSwitch = digitalRead(pinSwitch);
  long microsec = ultrasonic.timing();

  float distance = ultrasonic.convert(microsec, Ultrasonic::CM);

  Serial.print("Distance : ");
  Serial.print(distance);
  Serial.println(" cm");

  if (etatSwitch == HIGH) {
    Serial.print(etatSwitch);
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
