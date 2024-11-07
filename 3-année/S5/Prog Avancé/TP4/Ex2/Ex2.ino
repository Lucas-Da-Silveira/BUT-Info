const int pinSwitch = 27;
const int pinLED = 5;

void IRAM_ATTR handleSwitch() {
  int etatSwitch = digitalRead(pinSwitch);

  if (etatSwitch == HIGH) {
    digitalWrite(pinLED, HIGH);
  }
  else {
    digitalWrite(pinLED, LOW);
  }
}


void setup() {
  Serial.begin(115200);

  pinMode(pinSwitch, INPUT);
  pinMode(pinLED, OUTPUT);

  attachInterrupt(digitalPinToInterrupt(pinSwitch), handleSwitch, CHANGE);

  Serial.println("OK");

}

void loop() {

}
