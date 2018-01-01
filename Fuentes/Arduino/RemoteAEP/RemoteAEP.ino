#include <EtherCard.h>
 
// ethernet interface mac address, must be unique on the LAN
static byte mymac[] = { 0x74,0x69,0x69,0x2D,0x30,0x31 };
static byte myip[] = { 192,168,1,203 };
byte Ethernet::buffer[700];
 
const int pinLed1 = 2;
const int pinLed2 = 3;
const int pinLed3 = 4;
const int pinLed4 = 5;
const int pinLed5 = 6;
const int pinLed6 = 7;
 
void setup() {
 
   Serial.begin(9600);
 
   if (!ether.begin(sizeof Ethernet::buffer, mymac, 10))
      Serial.println("No se ha podido acceder a la controlador Ethernet");
   else
      Serial.println("Controlador Ethernet inicializado");
 
   if (!ether.staticSetup(myip))
      Serial.println("No se pudo establecer la dirección IP");
   Serial.println();
 
   pinMode(pinLed1, OUTPUT);
   pinMode(pinLed2, OUTPUT);
   pinMode(pinLed3, OUTPUT);
   pinMode(pinLed4, OUTPUT);
   pinMode(pinLed5, OUTPUT);
   pinMode(pinLed6, OUTPUT);
   digitalWrite(pinLed1, HIGH);
   digitalWrite(pinLed2, HIGH);
   digitalWrite(pinLed3, HIGH);
   digitalWrite(pinLed4, HIGH);
   digitalWrite(pinLed5, HIGH);
   digitalWrite(pinLed6, HIGH);
}
 
static word mainPage()
{
   BufferFiller bfill = ether.tcpOffset();
   bfill.emit_p(PSTR("HTTP/1.0 200 OK\r\n"
      "Content-Type: text/html\nPragma: no-cachernRefresh: 5\n\n"
      "<html><head><title>AnepaNet</title></head></html>"));
 
   return bfill.position();
}
 
void loop() 
{
   word len = ether.packetReceive();
   word pos = ether.packetLoop(len);

   if (pos) 
   {
      if (strstr((char *)Ethernet::buffer + pos, "GET /?data1=1") != 0) {
         digitalWrite(pinLed1, LOW);
      }
      if (strstr((char *)Ethernet::buffer + pos, "GET /?data2=1") != 0) {
         digitalWrite(pinLed2, LOW);
      }
      if (strstr((char *)Ethernet::buffer + pos, "GET /?data3=1") != 0) {
         digitalWrite(pinLed3, LOW);
      }
      if (strstr((char *)Ethernet::buffer + pos, "GET /?data4=1") != 0) {
         digitalWrite(pinLed4, LOW);
      }
      if (strstr((char *)Ethernet::buffer + pos, "GET /?data5=1") != 0) {
         digitalWrite(pinLed5, LOW);
      }
      if (strstr((char *)Ethernet::buffer + pos, "GET /?data6=1") != 0) {
         digitalWrite(pinLed6, LOW);
      }
      delay(1000);

      digitalWrite(pinLed1, HIGH);
      digitalWrite(pinLed2, HIGH);
      digitalWrite(pinLed3, HIGH);
      digitalWrite(pinLed4, HIGH);
      digitalWrite(pinLed5, HIGH);
      digitalWrite(pinLed6, HIGH);
      
      ether.httpServerReply(mainPage());
   }
}
