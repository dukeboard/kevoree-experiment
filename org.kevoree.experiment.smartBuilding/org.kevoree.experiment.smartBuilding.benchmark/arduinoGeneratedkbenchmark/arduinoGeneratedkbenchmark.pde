
     #include <QueueList.h> 
     #include <avr/pgmspace.h> 
     #include <avr/wdt.h>
     #define UDI_C 0 
     #define AIN_C 1 
     #define RIN_C 2 
     #define ABI_C 3 
     #define RBI_C 4 
     #define kprint(x) KevSerialPrint_P(PSTR(x)) 
     #define kprintln(x) KevSerialPrintln_P(PSTR(x)) 
     #define KNOINLINE __attribute__((noinline))
     #define BUFFERSIZE 100
     int serialIndex = 0;
     char inBytes[BUFFERSIZE];
     const char startBAdminChar = '{';
     const char endAdminChar = '}';
     const char startAdminChar = '$';
     const char sepAdminChar = '/';


#define EEPROM_MAX_SIZE 16000
#define MAX_INST_ID 15
#define kevoreeID1 6
#define kevoreeID2 3
int eepromIndex;
//Global Kevoree Type Defintion declaration
const prog_char pushbutton[] PROGMEM = "PushButton";
const prog_char digitallight[] PROGMEM = "DigitalLight";
const prog_char lightsensor[] PROGMEM = "LightSensor";
const prog_char timer[] PROGMEM = "Timer";
const prog_char localchannel[] PROGMEM = "LocalChannel";
PROGMEM const char * typedefinition[] = { 
pushbutton
,digitallight
,lightsensor
,timer
,localchannel
};
//Global Kevoree Port Type Defintion declaration
const prog_char port_click[] PROGMEM = "click";
const prog_char port_release[] PROGMEM = "release";
const prog_char port_on[] PROGMEM = "on";
const prog_char port_off[] PROGMEM = "off";
const prog_char port_toggle[] PROGMEM = "toggle";
const prog_char port_trigger[] PROGMEM = "trigger";
const prog_char port_light[] PROGMEM = "light";
const prog_char port_tick[] PROGMEM = "tick";
PROGMEM const char * portdefinition[] = { port_click,port_release,port_on,port_off,port_toggle,port_trigger,port_light,port_tick};
const prog_char prop_pin[] PROGMEM = "pin";
const prog_char prop_period[] PROGMEM = "period";
PROGMEM const char * properties[] = { prop_pin,prop_period};
#include <Fat16.h>
SdCard card;
Fat16 file;

const int nbPortType = 8;
int getIDFromPortName(char * portName){
  for(int i=0;i<nbPortType;i++){
   if(strcmp_P(portName, (char*)pgm_read_word(&(portdefinition[i]))  )==0) { return i; }
  }
  return -1;
}
const int nbTypeDef = 5;
int getIDFromType(char * typeName){
  for(int i=0;i<nbTypeDef;i++){
   if(strcmp_P(typeName, (char*)pgm_read_word(&(typedefinition[i]))  )==0) { return i; }
  }
  return -1;
}
const int nbProps = 5;
int getIDFromProps(char * propName){
  for(int i=0;i<nbProps;i++){
   if(strcmp_P(propName, (char*)pgm_read_word(&(properties[i]))  )==0) { return i; }
  }
  return -1;
}


static KNOINLINE void KevSerialPrint_P(PGM_P str) {
  for (uint8_t c; (c = pgm_read_byte(str)); str++) Serial.print(c);
}

static KNOINLINE void KevSerialPrintln_P(PGM_P str) {
  KevSerialPrint_P(str);
  Serial.println();
}
     struct kmessage {char* value;char* metric;}; 
     class KevoreeType { public : int subTypeCode; char instanceName[MAX_INST_ID]; }; 
    //GENERATE kbinding framework
     struct kbinding { KevoreeType * instance;int portCode; QueueList<kmessage> * port;   }; 
     #define BDYNSTEP 3 
     class kbindings { 
      public: 
      kbinding ** bindings; 
      int nbBindings; 
      void init(){ nbBindings = 0; } 
      int addBinding( KevoreeType * instance , int portCode , QueueList<kmessage> * port){ 
        kbinding * newBinding = (kbinding*) malloc(sizeof(kbinding)); 
        if(newBinding){ 
           memset(newBinding, 0, sizeof(kbinding)); 
        } 
        newBinding->instance=instance; 
        newBinding->portCode=portCode; 
        newBinding->port = port; 
     if(nbBindings % BDYNSTEP == 0){ 
       bindings = (kbinding**) realloc(bindings, (nbBindings+BDYNSTEP) * sizeof(kbinding*) ); 
     } 
     bindings[nbBindings] = newBinding; 
        nbBindings ++; 
        return nbBindings-1; 
      } 
      boolean removeBinding( KevoreeType * instance , int portCode ){ 
        //SEARCH INDEX 
        int indexToRemove = -1; 
        for(int i=0;i<nbBindings;i++){ 
          kbinding * binding = bindings[i]; 
          if( (strcmp(binding->instance->instanceName,instance->instanceName) == 0 ) && binding->portCode == portCode){ indexToRemove = i; } 
        } 
        if(indexToRemove == -1){return -1;} else { free(bindings[indexToRemove]); } 
     if(indexToRemove != nbBindings-1){ 
       bindings[indexToRemove] = bindings[nbBindings-1]; 
     } 
     if(nbBindings % BDYNSTEP == 0){ 
       bindings = (kbinding**) realloc(bindings, (nbBindings) * sizeof(kbinding*) ); 
     } 
       nbBindings--; 
       return true;   
      }  
     void destroy(){ 
      for(int i=0;i<nbBindings;i++){ free(bindings[i]); }  
      free(bindings); 
     } 
     };







    //GENERATE GLOBAL VARIABLE
      KevoreeType ** instances; //GLOBAL INSTANCE DYNAMIC ARRAY 
      int nbInstances = 0; //GLOBAL NB INSTANCE 
      KevoreeType * tempInstance; //TEMP INSTANCE POINTER 
    //GENERATE ADD INSTANCE HELPER
      int addInstance(){ //TECHNICAL HELPER ADD INSTANCE 
        if(tempInstance){ 
          KevoreeType** newInstances =  (KevoreeType**) malloc(  (nbInstances+1) *sizeof(KevoreeType*) ); 
          if (!newInstances) { return -1;} 
          for (int idx=0;idx < nbInstances ; idx++ ){ newInstances[idx] = instances[idx]; } 
          newInstances[nbInstances] = tempInstance; 
          if(instances){ free(instances); } 
          instances = newInstances; 
          tempInstance = NULL; 
          nbInstances ++; 
          return nbInstances-1; 
        } 
        return -1; 
      } 
    //GENERATE REMOVE INSTANCE HELPER
      boolean removeInstance(int index){ 
      destroyInstance(index); 
      KevoreeType** newInstances =  (KevoreeType**) malloc(  (nbInstances-1) *sizeof(KevoreeType*) ); 
      if (!newInstances) { return false;} 
      for (int idx=0;idx < nbInstances ; idx++ ){  
        if(idx < index){ newInstances[idx] = instances[idx]; } 
        if(idx > index){ newInstances[idx-1] = instances[idx]; } 
      } 
      if(instances){ free(instances); } 
      instances = newInstances;  
      nbInstances--;return true; 
      }


           const char delimsEQ[] =  "="  ;
           char * key;
           char * val;
           byte paramCode;
           void updateParams (int index, char * params) {
              if ((params[ 0] == '\0') &&(params[ 1] != '=') )
              {
                return;
              }
              paramCode = params[ 0];
              int i = 2;
              while (params[i] != '\0' && params[i] != ',') {
                i ++;
              }
              if (params[i] == ',') {
                params[i] = '\0';
                updateParam(index, instances[index] -> subTypeCode, paramCode, & params[ 2] );
                updateParams(index, & params[i + 1] ); //recursive call
             } else {
                updateParam(index, instances[index] -> subTypeCode, paramCode, & params[ 2] );
              }
            }
class PushButton : public KevoreeType {
 public : 
unsigned long nextExecution;
int buttonState ;

kbinding * click;
kbinding * release;
char pin[20];
char period[20];
void init(){
click = (kbinding*) malloc(sizeof(kbinding));
if(click){
   memset(click, 0, sizeof(kbinding));
}
release = (kbinding*) malloc(sizeof(kbinding));
if(release){
   memset(release, 0, sizeof(kbinding));
}
nextExecution = millis();
}
void destroy(){
free(click);
free(release);
}
void runInstance(){
pinMode(atoi(pin), INPUT);
int newButtonState = digitalRead(atoi(pin));
  if (newButtonState == HIGH) { 
    if(buttonState == LOW){
      buttonState = HIGH;
kmessage * msg = (kmessage*) malloc(sizeof(kmessage));if (msg){memset(msg, 0, sizeof(kmessage));}msg->value = "click";msg->metric = "event";click_rport(msg);free(msg);    }
  } else {
    if(buttonState == HIGH){
      buttonState = LOW;
      //DO ACTION UNRELEASE ACTION
kmessage * msg = (kmessage*) malloc(sizeof(kmessage));if (msg){memset(msg, 0, sizeof(kmessage));}msg->value = "release";msg->metric = "event";release_rport(msg);free(msg);
    }
  }
nextExecution += atol(period);
}
void click_rport(kmessage * msg){
if(click->port){
click->port->push(*msg);
}
}
void release_rport(kmessage * msg){
if(release->port){
release->port->push(*msg);
}
}
};
class DigitalLight : public KevoreeType {
 public : 
boolean state ;

QueueList<kmessage> * on;
QueueList<kmessage> * off;
QueueList<kmessage> * toggle;
char pin[20];
void init(){
on = (QueueList<kmessage>*) malloc(sizeof(QueueList<kmessage>));
if(on){
   memset(on, 0, sizeof(QueueList<kmessage>));
}
off = (QueueList<kmessage>*) malloc(sizeof(QueueList<kmessage>));
if(off){
   memset(off, 0, sizeof(QueueList<kmessage>));
}
toggle = (QueueList<kmessage>*) malloc(sizeof(QueueList<kmessage>));
if(toggle){
   memset(toggle, 0, sizeof(QueueList<kmessage>));
}
}
void destroy(){
free(on);
free(off);
free(toggle);
}
void runInstance(){
if(!on->isEmpty()){
kmessage * msg = &(on->pop());
DigitalLight::on_pport(msg);
}
if(!off->isEmpty()){
kmessage * msg = &(off->pop());
DigitalLight::off_pport(msg);
}
if(!toggle->isEmpty()){
kmessage * msg = &(toggle->pop());
DigitalLight::toggle_pport(msg);
}
}
void on_pport(kmessage * msg){
pinMode(atoi(pin), OUTPUT);digitalWrite(atoi(pin), HIGH);

}
void off_pport(kmessage * msg){
pinMode(atoi(pin), OUTPUT);digitalWrite(atoi(pin), LOW);

}
void toggle_pport(kmessage * msg){
int newState = 0;
if(state){ newState = LOW; } else { newState=HIGH; }state = ! state; pinMode(atoi(pin), OUTPUT);digitalWrite(atoi(pin), newState);

}
};
class LightSensor : public KevoreeType {
 public : 
int photocellReading;
char buf[10];

QueueList<kmessage> * trigger;
kbinding * light;
char pin[20];
void init(){
trigger = (QueueList<kmessage>*) malloc(sizeof(QueueList<kmessage>));
if(trigger){
   memset(trigger, 0, sizeof(QueueList<kmessage>));
}
light = (kbinding*) malloc(sizeof(kbinding));
if(light){
   memset(light, 0, sizeof(kbinding));
}
}
void destroy(){
free(trigger);
free(light);
}
void runInstance(){
if(!trigger->isEmpty()){
kmessage * msg = &(trigger->pop());
LightSensor::trigger_pport(msg);
}
}
void light_rport(kmessage * msg){
if(light->port){
light->port->push(*msg);
}
}
void trigger_pport(kmessage * msg){
photocellReading = analogRead(atoi(pin));
kmessage * smsg = (kmessage*) malloc(sizeof(kmessage));if (smsg){memset(smsg, 0, sizeof(kmessage));}sprintf(buf,"%d",photocellReading);
smsg->value = buf;
smsg->metric="alux";light_rport(smsg);free(smsg);
}
};
class Timer : public KevoreeType {
 public : 
unsigned long nextExecution;
kbinding * tick;
char period[20];
void init(){
tick = (kbinding*) malloc(sizeof(kbinding));
if(tick){
   memset(tick, 0, sizeof(kbinding));
}
nextExecution = millis();
}
void destroy(){
free(tick);
}
void runInstance(){
kmessage * msg = (kmessage*) malloc(sizeof(kmessage));
if (msg){memset(msg, 0, sizeof(kmessage));}
msg->value = "tick";msg->metric = "t";tick_rport(msg);free(msg);
nextExecution += atol(period);
}
void tick_rport(kmessage * msg){
if(tick->port){
tick->port->push(*msg);
}
}
};
class LocalChannel : public KevoreeType {
 public : 
QueueList<kmessage> * input;
kbindings * bindings;
void init(){
input = (QueueList<kmessage>*) malloc(sizeof(QueueList<kmessage>));
if(input){
   memset(input, 0, sizeof(QueueList<kmessage>));
}
bindings = (kbindings *) malloc(sizeof(kbindings));
if(bindings){
   memset(bindings, 0, sizeof(kbindings));
}
}
void destroy(){
free(input);
bindings->destroy();
free(bindings);
}
void runInstance(){
if(!input->isEmpty()){
kmessage * msg = &(input->pop());
LocalChannel::dispatch(msg);
}
}
void dispatch(kmessage * msg){
for(int i=0;i<bindings->nbBindings;i++){    bindings->bindings[i]->port->push(*msg);}
}
};
boolean destroyInstance(int index){
KevoreeType * instance = instances[index];
 switch(instance->subTypeCode){
case 0:{
((PushButton*) instances[index])->destroy();
break;}
case 1:{
((DigitalLight*) instances[index])->destroy();
break;}
case 2:{
((LightSensor*) instances[index])->destroy();
break;}
case 3:{
((Timer*) instances[index])->destroy();
break;}
case 4:{
((LocalChannel*) instances[index])->destroy();
break;}
}
free(instance);
}
void updateParam(int index,int typeCode,int keyCode,char * val){
 switch(typeCode){
case 0:{
PushButton * instance = (PushButton*) instances[index];
 switch(keyCode){
case 0:{
strcpy (instance->pin,val);
break;}
case 1:{
strcpy (instance->period,val);
break;}
}
break;}
case 1:{
DigitalLight * instance = (DigitalLight*) instances[index];
 switch(keyCode){
case 0:{
strcpy (instance->pin,val);
break;}
}
break;}
case 2:{
LightSensor * instance = (LightSensor*) instances[index];
 switch(keyCode){
case 0:{
strcpy (instance->pin,val);
break;}
}
break;}
case 3:{
Timer * instance = (Timer*) instances[index];
 switch(keyCode){
case 1:{
strcpy (instance->period,val);
break;}
}
break;}
case 4:{
LocalChannel * instance = (LocalChannel*) instances[index];
break;}
}
}
int createInstance(int typeCode,char* instanceName,char* params){
switch(typeCode){
case 0:{
  PushButton * newInstance = (PushButton*) malloc(sizeof(PushButton));
  if (newInstance){
    memset(newInstance, 0, sizeof(PushButton));
  } 
  strcpy(newInstance->instanceName,instanceName);
  newInstance->init();
  tempInstance = newInstance;
  tempInstance->subTypeCode = 0; 
  int newIndex = addInstance();
  updateParams(newIndex,params);
  return newIndex;
break;}
case 1:{
  DigitalLight * newInstance = (DigitalLight*) malloc(sizeof(DigitalLight));
  if (newInstance){
    memset(newInstance, 0, sizeof(DigitalLight));
  } 
  strcpy(newInstance->instanceName,instanceName);
  newInstance->init();
  tempInstance = newInstance;
  tempInstance->subTypeCode = 1; 
  int newIndex = addInstance();
  updateParams(newIndex,params);
  return newIndex;
break;}
case 2:{
  LightSensor * newInstance = (LightSensor*) malloc(sizeof(LightSensor));
  if (newInstance){
    memset(newInstance, 0, sizeof(LightSensor));
  } 
  strcpy(newInstance->instanceName,instanceName);
  newInstance->init();
  tempInstance = newInstance;
  tempInstance->subTypeCode = 2; 
  int newIndex = addInstance();
  updateParams(newIndex,params);
  return newIndex;
break;}
case 3:{
  Timer * newInstance = (Timer*) malloc(sizeof(Timer));
  if (newInstance){
    memset(newInstance, 0, sizeof(Timer));
  } 
  strcpy(newInstance->instanceName,instanceName);
  newInstance->init();
  tempInstance = newInstance;
  tempInstance->subTypeCode = 3; 
  int newIndex = addInstance();
  updateParams(newIndex,params);
  return newIndex;
break;}
case 4:{
  LocalChannel * newInstance = (LocalChannel*) malloc(sizeof(LocalChannel));
  if (newInstance){
    memset(newInstance, 0, sizeof(LocalChannel));
  } 
  strcpy(newInstance->instanceName,instanceName);
  newInstance->init();
  tempInstance = newInstance;
  tempInstance->subTypeCode = 4; 
  int newIndex = addInstance();
  updateParams(newIndex,params);
  return newIndex;
break;}
}
 return -1;
}
void runInstance(int index){
int typeCode = instances[index]->subTypeCode;
 switch(typeCode){
case 0:{
PushButton * instance = (PushButton*) instances[index];
instance->runInstance();
break;}
case 1:{
DigitalLight * instance = (DigitalLight*) instances[index];
instance->runInstance();
break;}
case 2:{
LightSensor * instance = (LightSensor*) instances[index];
instance->runInstance();
break;}
case 3:{
Timer * instance = (Timer*) instances[index];
instance->runInstance();
break;}
case 4:{
LocalChannel * instance = (LocalChannel*) instances[index];
instance->runInstance();
break;}
}
}

#define SD_SUBBUF_SIZE 50
byte sdLocalBuffer[SD_SUBBUF_SIZE];
int subBufIndex = -60;
byte readPMemory(int preciseIndex){
      if( ( preciseIndex > (subBufIndex+SD_SUBBUF_SIZE-1) ) || (preciseIndex <subBufIndex )   ){
          if(file.curPosition() != preciseIndex){file.seekSet(preciseIndex);}
         // return file.read();
          file.read(&sdLocalBuffer, SD_SUBBUF_SIZE);
          subBufIndex = preciseIndex;
      }
      return sdLocalBuffer[(preciseIndex-subBufIndex)];
}

void save2MemoryNoInc(int preciseIndex,byte b){
subBufIndex = -60;
    if(file.curPosition() != preciseIndex){file.seekSet(preciseIndex);}
    file.write(b);
    if(b == endAdminChar | b == sepAdminChar) file.sync();
}

void save2Memory(byte b){
subBufIndex = -60;
    if(file.curPosition() != eepromIndex){file.seekSet(eepromIndex);}
    file.write(b);
    if(b == endAdminChar | b == sepAdminChar) file.sync();
    eepromIndex++;
}

void bind(int indexComponent,int indexChannel,int portCode){
QueueList<kmessage> * providedPort = 0;
kbinding * requiredPort = 0;
char * componentInstanceName;
int componentTypeCode = instances[indexComponent]->subTypeCode;
int channelTypeCode = instances[indexChannel]->subTypeCode;
 switch(componentTypeCode){
case 0:{
PushButton * instance = (PushButton*) instances[indexComponent];
switch(portCode){
case 0:{
   requiredPort=instance->click;
break;}
case 1:{
   requiredPort=instance->release;
break;}
}
break;}
case 1:{
DigitalLight * instance = (DigitalLight*) instances[indexComponent];
switch(portCode){
case 2:{
   providedPort=instance->on;
componentInstanceName=instance->instanceName;
break;}
case 3:{
   providedPort=instance->off;
componentInstanceName=instance->instanceName;
break;}
case 4:{
   providedPort=instance->toggle;
componentInstanceName=instance->instanceName;
break;}
}
break;}
case 2:{
LightSensor * instance = (LightSensor*) instances[indexComponent];
switch(portCode){
case 5:{
   providedPort=instance->trigger;
componentInstanceName=instance->instanceName;
break;}
case 6:{
   requiredPort=instance->light;
break;}
}
break;}
case 3:{
Timer * instance = (Timer*) instances[indexComponent];
switch(portCode){
case 7:{
   requiredPort=instance->tick;
break;}
}
break;}
}
 switch(channelTypeCode){
case 4:{
LocalChannel * instance = (LocalChannel*) instances[indexChannel];
if(providedPort){
instance->bindings->addBinding(instances[indexComponent],portCode,providedPort);
}
if(requiredPort){
requiredPort->portCode = portCode;
requiredPort->instance = instance;
requiredPort->port = instance->input;
}
break;}
}
}
void unbind(int indexComponent,int indexChannel,int portCode){
kbinding * requiredPort = 0;
 switch(instances[indexComponent]->subTypeCode){
case 0:{
switch(portCode){
case 0:{
   requiredPort=(((PushButton*)instances[indexComponent])->click);
break;}
case 1:{
   requiredPort=(((PushButton*)instances[indexComponent])->release);
break;}
}
break;}
case 2:{
switch(portCode){
case 6:{
   requiredPort=(((LightSensor*)instances[indexComponent])->light);
break;}
}
break;}
case 3:{
switch(portCode){
case 7:{
   requiredPort=(((Timer*)instances[indexComponent])->tick);
break;}
}
break;}
}
if(requiredPort){
     requiredPort->instance=NULL;
     requiredPort->port=NULL;
     requiredPort->portCode=0;
} else {
 switch(instances[indexChannel]->subTypeCode){
case 4:{
((LocalChannel*)instances[indexChannel])->bindings->removeBinding(instances[indexComponent],portCode);
break;}
}
}
}
boolean periodicExecution(int index){
 switch(instances[index]->subTypeCode){
case 0:{
return millis() > (((PushButton *) instances[index] )->nextExecution);
}
case 3:{
return millis() > (((Timer *) instances[index] )->nextExecution);
}
}
return false;
}
int getPortQueuesSize(int index){
 switch(instances[index]->subTypeCode){
case 1:{
return (((DigitalLight *)instances[index])->on->count())+(((DigitalLight *)instances[index])->off->count())+(((DigitalLight *)instances[index])->toggle->count());
}
case 2:{
return (((LightSensor *)instances[index])->trigger->count());
}
case 4:{
return (((LocalChannel *)instances[index])->input->count());
}
}
return 0;
}
int getIndexFromName(char * id){
 for(int i=0;i<nbInstances;i++){
  if(strcmp(instances[i]->instanceName,id)==0){ return i; }
 } 
 return -1;
}
     //ARDUINO SERIAL INPUT READ                                        

     char ackToken;                                                     
     boolean parsingAdmin = false;                                      
     int eepromPreviousIndex;                                               
     boolean firstAdd ;                                                     
     unsigned long timeBeforeScript; 
         void checkForAdminMsg () {                                                       
           if (Serial.peek() == startAdminChar) {                                         
             timeBeforeScript = millis(); 
             Serial.read(); //DROP ADMIN START CHAR                                       
             while (!Serial.available() > 0) {                                            
               delay(10);                                                                 
             }                                                                            
             ackToken = Serial.read();                                                    
             while (!Serial.available() > 0) {                                            
               delay(10);                                                                 
             }                                                                             
             if (Serial.read() == startBAdminChar) {                                       
               parsingAdmin = true;                                                        
               eepromPreviousIndex = eepromIndex;                                          
               eepromIndex ++;                                                             
               firstAdd = true;                                                            
               while (parsingAdmin) {                                                      
                 if (Serial.available() > 0 && serialIndex < BUFFERSIZE) {                 
                   inBytes[serialIndex] = Serial.read();                                   
                   if (inBytes[serialIndex] == sepAdminChar) {                             
                     inBytes[serialIndex] = '\0';
                     //saveScriptCommand();                                                
                     if (!firstAdd) {                                                      
                       save2Memory(sepAdminChar);            
                     }                                                                     
                     parseAndSaveAdminMsg();                                               
                     flushAdminBuffer();                                                   
                     firstAdd = false;                                                     
                   } else {                                                                
                     if (inBytes[serialIndex] == endAdminChar) {                           
                       parsingAdmin = false;                                               
                       inBytes[serialIndex] = '\0';
                       //saveScriptCommand();                                              
                                                                                           
                       if (!firstAdd) {                                                    
                         save2Memory(sepAdminChar);          
                       }                                                                   
                       parseAndSaveAdminMsg();                                             
                       flushAdminBuffer();                                                 
               save2MemoryNoInc(eepromIndex, endAdminChar);                                    
               eepromIndex = eepromPreviousIndex + 1;                                      
               executeScriptFromEEPROM();                                                  
                                                                                           
               save2MemoryNoInc(eepromPreviousIndex, sepAdminChar); //CLOSE TRANSACTION        
    //COMPRESS EEPROM IF NECESSARY , DON'T GO TO LIMIT
               if(eepromIndex > (EEPROM_MAX_SIZE-100)){         
                 compressEEPROM();             
               }                               
                       kprint("ms");
                       Serial.println( millis() - timeBeforeScript );
                       kprint("mem");
                       Serial.println(freeRam());
                       kprint("emem");
                       Serial.println(eepromIndex);
                       kprint("ack");
                       Serial.println(ackToken);
                       firstAdd = false;                                                   
                     } else {                                                              
                       serialIndex ++;                                                     
                     }                                                                     
                   }                                                                        
                 }                                                                         
                 if (serialIndex >= BUFFERSIZE) {                                          
                   kprintln("BFO");
                   flushAdminBuffer();                                                     
                   Serial.flush();                                                         
                   parsingAdmin = false; //KILL PARSING ADMIN                              
                 }                                                                         
               }                                                                           
                                                                                           
             } else {                                                                      
               kprintln("BAM");
               flushAdminBuffer();                                                          
               Serial.flush();                                                              
             }                                                                              
           }                                                                                
         }                                                                                  
     void flushAdminBuffer(){                                           
       for(int i=0;i<serialIndex;i++){                                  
          inBytes[serialIndex];                                         
       }                                                                
       serialIndex = 0;                                                 
     }                                                                  
     //END SECTION ADMIN DETECTION 1 SPLIT                              

     char * insID;
     char * typeID;
     char * params;
     char * chID;
     char * portID;
     const char delims[] = ":";
     boolean parseAndSaveAdminMsg(){
         if( inBytes[0]=='p' && inBytes[1]=='i' && inBytes[2]=='n' && inBytes[3]=='g' ){
           return true;
         }
         if( inBytes[0]=='u' && inBytes[1]=='d' && inBytes[2]=='i' && inBytes[3]==':' ){
           insID = strtok(&inBytes[4], delims);
           params = strtok(NULL, delims);
           saveUDI_CMD(insID,params);
           return true;
         }
         if( inBytes[0]=='a' && inBytes[1]=='i' && inBytes[2]=='n' && inBytes[3]==':' ){
           insID = strtok(&inBytes[4], delims);
           typeID = strtok(NULL, delims);
           params = strtok(NULL, delims);
           saveAIN_CMD(insID,getIDFromType(typeID),params);
           return true;
         }
         if( inBytes[0]=='r' && inBytes[1]=='i' && inBytes[2]=='n' && inBytes[3]==':' ){
           insID = strtok(&inBytes[4], delims);
            saveRIN_CMD(insID);
           return true;
         }
         if( inBytes[0]=='a' && inBytes[1]=='b' && inBytes[2]=='i' && inBytes[3]==':' ){
           insID = strtok(&inBytes[4], delims);
           chID = strtok(NULL, delims);
           portID = strtok(NULL, delims);
           saveBI_CMD(true,insID,chID,getIDFromPortName(portID));
           return true;
         }
         if( inBytes[0]=='r' && inBytes[1]=='b' && inBytes[2]=='i' && inBytes[3]==':' ){
           insID = strtok(&inBytes[4], delims);
           chID = strtok(NULL, delims);
           portID = strtok(NULL, delims);
           saveBI_CMD(false,insID,chID,getIDFromPortName(portID));
           return true;
         }
       return false;
     }

         void saveRIN_CMD (char * instName) {                
           save2Memory(RIN_C);                 
           save2Memory(':');
           for (int j = 0;                                   
           j < strlen(instName);                             
           j ++)                                             
           {                                                 
             if (instName[j] != '\0 ')
             {                                               
               save2Memory(instName[j]);       
             }                                               
           }                                                 
         }                                                   
    
             void saveBI_CMD (boolean abiCmd, char * compName, char * chaName, int portCode) {       
               if (abiCmd) {                                                                         
                 save2Memory(ABI_C);                                                   
               } else {                                                                              
                 save2Memory(RBI_C);                                                   
               }                                                                                     
               save2Memory(delims[0]);                                               
               for (int j = 0;                                                                       
               j < strlen(compName);                                                                 
               j ++)                                                                                 
               {                                                                                     
                 if (compName[j] != '\0') {
                   save2Memory(compName[j]);                                           
                 }                                                                                   
               }                                                                                      
               save2Memory(delims[0] );                                                
               for (int j = 0;                                                                       
               j < strlen(chaName);                                                                  
               j ++)                                                                                 
               {                                                                                     
                 if (chaName[j] != '\0') {
                   save2Memory(chaName[j]);                                            
                 }                                                                                   
               }                                                                                     
               save2Memory(delims[0] );                                               
               save2Memory(portCode);                                                  
             }                                                                                       


         char * str; 
         boolean first = true;                                                               
         void saveUDI_CMD(char * instName,char * params){                                    
          save2Memory(UDI_C);                                     
          save2Memory(':');
          for(int j=0;j<strlen(instName);j++){                                         
            if(instName[j]!='\0'){
              save2Memory(instName[j]);                          
            }                                                                                
          }                                                                                  
          save2Memory(':');
          first = true;                                                                      
          while ((str = strtok_r(params, "," , &params)) != NULL){
             if(!first){                                                                     
               save2Memory(',');
             }                                                                               
             first = false;                                                                  
             key = strtok(str, delimsEQ);                                                    
             val = strtok(NULL, delimsEQ);                                                   
             save2Memory(getIDFromProps(key));                    
             save2Memory('=');
             for(int j=0;j<strlen(val);j++){                                           
               if(val[j]!='\0'){
                 save2Memory(val[j]);                             
               }                                                                             
             }                                                                               
           }                                                                                 
         }                                                                                   

             void saveAIN_CMD (char * instanceName, int typeID,char * params) {                  
           save2Memory(AIN_C);                                                 
           save2Memory(delims[0]);                                           
           for (int i = 0;                                                                   
           i < strlen(instanceName);                                                         
           i ++)                                                                             
           {                                                                                 
             save2Memory(instanceName[i]);                                     
           }                                                                                 
           save2Memory(delims[0]);                                           
           save2Memory(typeID);                                                
           save2Memory(delims[0]);                                           
           first = true;                                                                     
           while ((str = strtok_r(params, "," , & params)) != NULL) {
             if (!first) {                                                                   
               save2Memory(',');
             }                                                                               
             first = false;                                                                  
             key = strtok(str, delimsEQ);                                                    
             val = strtok(NULL, delimsEQ);                                                   
             save2Memory(getIDFromProps(key));                                 
             save2Memory('=');
             for (int j = 0;                                                                 
             j < strlen( val);                                                               
             j ++)                                                                           
             {                                                                               
               if ( val[j] != '\0')
               {                                                                             
                 save2Memory(val[j] );                                         
               }                                                                             
             }                                                                               
           }                                                                                 
         }                                                                                   
    void initPMEM() {               
if (!card.init()) kprintln("error.card.init"); 
if (!Fat16 :: init(& card)) kprintln("Fat16::init");    
if (file.open("PKEVS", O_CREAT |O_RDWR)) kprintln("Fat16::open");  
  file.writeError = false;                        
  file.rewind();                                   
    if(file.fileSize() !=  EEPROM_MAX_SIZE){                   
    for (int i = 0; i < EEPROM_MAX_SIZE; i++) {                 
      file.write((byte)'=');                                     
    }                                                             
    file.rewind();                                                 
  }                                                                 
}                                                        
void savePropertiesToEEPROM(int instanceIndex){
 switch(instances[instanceIndex]->subTypeCode){
case 0:{
save2Memory(0);
save2Memory(delimsEQ[0]);
for(int i=0;i<strlen(((PushButton *) instances[instanceIndex])->pin);i++){
save2Memory(((PushButton *) instances[instanceIndex])->pin[i]);
}
save2Memory(',');
save2Memory(1);
save2Memory(delimsEQ[0]);
for(int i=0;i<strlen(((PushButton *) instances[instanceIndex])->period);i++){
save2Memory(((PushButton *) instances[instanceIndex])->period[i]);
}
break;
}
case 1:{
save2Memory(0);
save2Memory(delimsEQ[0]);
for(int i=0;i<strlen(((DigitalLight *) instances[instanceIndex])->pin);i++){
save2Memory(((DigitalLight *) instances[instanceIndex])->pin[i]);
}
break;
}
case 2:{
save2Memory(0);
save2Memory(delimsEQ[0]);
for(int i=0;i<strlen(((LightSensor *) instances[instanceIndex])->pin);i++){
save2Memory(((LightSensor *) instances[instanceIndex])->pin[i]);
}
break;
}
case 3:{
save2Memory(1);
save2Memory(delimsEQ[0]);
for(int i=0;i<strlen(((Timer *) instances[instanceIndex])->period);i++){
save2Memory(((Timer *) instances[instanceIndex])->period[i]);
}
break;
}
}
}
void compressEEPROM(){                                                              
eepromIndex=2;                                                                      
save2Memory(startBAdminChar);                            
for(int i=0;i<nbInstances;i++){                                                     
 if(i != 0){save2Memory(sepAdminChar);}                  
 save2Memory(AIN_C);                                       
 save2Memory(':');                                       
 for(int j=0;j<(sizeof(instances[i]->instanceName) - 1);j++){                       
   if(instances[i]->instanceName[j]!='\0'){                                         
     save2Memory(instances[i]->instanceName[j]);;         
   }                                                                                
 }                                                                                  
 save2Memory(':');                                       
 save2Memory(instances[i]->subTypeCode);                 
 save2Memory(':');                                       
 savePropertiesToEEPROM(i);                                                                
}                                                                                   
for (int i = 0;i < nbInstances;i ++){
  save2Memory(sepAdminChar);         
  saveInstancesBindings(i);          
}                                    
save2Memory(endAdminChar);                               
eepromIndex--;
 }                                                                                  
  void saveInstancesBindings(int instanceIndex){      
   switch(instances[instanceIndex]->subTypeCode){     
case 0:{
                  if ((((PushButton *) instances[instanceIndex]) -> click -> port))                          
                  {                                                                                    
                    saveBI_CMD(                                                                        
                      true,                                                                            
                      instances[instanceIndex] -> instanceName,                                        
                      ((PushButton *) instances[instanceIndex]) -> click -> instance -> instanceName,        
                    ((PushButton *) instances[instanceIndex]) -> click -> portCode                           
                    );                                                                                 
                  }                                                                                    
                  if ((((PushButton *) instances[instanceIndex]) -> release -> port))                          
                  {                                                                                    
                    saveBI_CMD(                                                                        
                      true,                                                                            
                      instances[instanceIndex] -> instanceName,                                        
                      ((PushButton *) instances[instanceIndex]) -> release -> instance -> instanceName,        
                    ((PushButton *) instances[instanceIndex]) -> release -> portCode                           
                    );                                                                                 
                  }                                                                                    
break;
}
case 2:{
                  if ((((LightSensor *) instances[instanceIndex]) -> light -> port))                          
                  {                                                                                    
                    saveBI_CMD(                                                                        
                      true,                                                                            
                      instances[instanceIndex] -> instanceName,                                        
                      ((LightSensor *) instances[instanceIndex]) -> light -> instance -> instanceName,        
                    ((LightSensor *) instances[instanceIndex]) -> light -> portCode                           
                    );                                                                                 
                  }                                                                                    
break;
}
case 3:{
                  if ((((Timer *) instances[instanceIndex]) -> tick -> port))                          
                  {                                                                                    
                    saveBI_CMD(                                                                        
                      true,                                                                            
                      instances[instanceIndex] -> instanceName,                                        
                      ((Timer *) instances[instanceIndex]) -> tick -> instance -> instanceName,        
                    ((Timer *) instances[instanceIndex]) -> tick -> portCode                           
                    );                                                                                 
                  }                                                                                    
break;
}
case 4:{
  for(int i=0;i<(((LocalChannel *)instances[instanceIndex])->bindings->nbBindings);i++){                   
       saveBI_CMD(                                                                                         
         true,                                                                                             
         ((LocalChannel *)instances[instanceIndex])->bindings->bindings[i]->instance->instanceName,        
         instances[instanceIndex]->instanceName,                                                           
         ((LocalChannel *)instances[instanceIndex])->bindings->bindings[i]->portCode                       
       );                                                                                                  
  }                                                                                                        
break;
}
}
}

    byte typeIDB;                                          
    byte portIDB;                                          
    boolean parseForCAdminMsg(){                           
      switch((byte)inBytes[0]){                            
        case AIN_C: {                                      
          insID = strtok(&inBytes[1], delims);             
          typeIDB = (byte)inBytes[strlen(insID)+3];        
          params = &inBytes[strlen(insID)+5];              
          createInstance(typeIDB,insID,params);            
          return true;                                     
          break;                                           
        }                                                  
        case RIN_C: {                                              
          insID = strtok (& inBytes[1], delims);                   
          removeInstance (getIndexFromName (insID) );              
          return true;                                             
          break;                                                   
        }                                                          
        case ABI_C: {                                                           
            insID = strtok(&inBytes[1], delims);                                
            chID = strtok(NULL, delims);                                        
            portIDB = (byte)chID[strlen(chID)+1];                               
            bind(getIndexFromName(insID),getIndexFromName(chID),portIDB);       
            return true;break;                                                  
          }                                                                     
        case RBI_C: {                                                           
            insID = strtok(&inBytes[1], delims);                                
            chID = strtok(NULL, delims);                                        
            portIDB = (byte)chID[strlen(chID)+1];                               
            unbind(getIndexFromName(insID),getIndexFromName(chID),portIDB);       
            return true;break;                                                  
          }                                                                     
        case UDI_C: {                                                                 
          insID = strtok (& inBytes[1], delims);                                      
          params = & inBytes[strlen (insID) + 3];                                     
          updateParams (getIndexFromName (insID), params);                            
        }                                                                             
      }                                                    
    }
                                                          
                                                          
            void executeScriptFromEEPROM () {                                            
              inBytes[serialIndex] = readPMemory(eepromIndex);                           
              while (inBytes[serialIndex] != endAdminChar && eepromIndex < EEPROM_MAX_SIZE) {        
                if (inBytes[serialIndex] == sepAdminChar) {                              
                  inBytes[serialIndex] = '\0';                                           
                  parseForCAdminMsg();                                                   
                  flushAdminBuffer();                                                    
                } else {                                                                 
                  serialIndex ++;                                                        
                }                                                                        
                eepromIndex ++;                                                          
                inBytes[serialIndex] = readPMemory(eepromIndex);                         
              }                                                                          
              //PROCESS LAST CMD                                                         
              if (inBytes[serialIndex] == endAdminChar) {                                
                inBytes[serialIndex] = '\0';                                             
                parseForCAdminMsg();                                                     
                flushAdminBuffer();                                                      
              }                                                                          
   
            }                                                                            
                                                          
                                                          
const prog_char init_c[] PROGMEM = "c";
const prog_char init_b[] PROGMEM = "b";
const prog_char init_a[] PROGMEM = "a";
const prog_char init_d[] PROGMEM = "d";
const prog_char init_e[] PROGMEM = "e";
const prog_char init_f[] PROGMEM = "f";
const prog_char init_g[] PROGMEM = "g";
const prog_char init_h[] PROGMEM = "h";
const prog_char init_i[] PROGMEM = "i";
PROGMEM const char * init_tables[] = {init_c,init_b,init_a,init_d,init_e,init_f,init_g,init_h,init_i};
char instNameBuf[MAX_INST_ID];
char instNameBuf2[MAX_INST_ID];
   unsigned long previousBootTime;
void setup(){
Serial.begin(9600);
initPMEM();
//STATE RECOVERY                                                         
if(readPMemory(0) != kevoreeID1 || readPMemory(1) != kevoreeID2){            
  save2MemoryNoInc(0,kevoreeID1);                                                
  save2MemoryNoInc(1,kevoreeID2);                                                
eepromIndex = 2;
save2Memory(startBAdminChar);
        strcpy_P(instNameBuf, (char *) pgm_read_word (&(init_tables[0])));        
        saveAIN_CMD(instNameBuf, 4, "");                                             
save2Memory(sepAdminChar);
        strcpy_P(instNameBuf, (char *) pgm_read_word (&(init_tables[1])));        
        saveAIN_CMD(instNameBuf, 4, "");                                             
save2Memory(sepAdminChar);
        strcpy_P(instNameBuf, (char *) pgm_read_word (&(init_tables[2])));        
        saveAIN_CMD(instNameBuf, 4, "");                                             
save2Memory(sepAdminChar);
        strcpy_P(instNameBuf, (char *) pgm_read_word (&(init_tables[3])));        
        saveAIN_CMD(instNameBuf, 0, "pin=12,period=100");                                             
save2Memory(sepAdminChar);
        strcpy_P(instNameBuf, (char *) pgm_read_word (&(init_tables[4])));        
        saveAIN_CMD(instNameBuf, 1, "pin=9");                                             
save2Memory(sepAdminChar);
        strcpy_P(instNameBuf, (char *) pgm_read_word (&(init_tables[5])));        
        saveAIN_CMD(instNameBuf, 2, "pin=0");                                             
save2Memory(sepAdminChar);
        strcpy_P(instNameBuf, (char *) pgm_read_word (&(init_tables[6])));        
        saveAIN_CMD(instNameBuf, 1, "pin=10");                                             
save2Memory(sepAdminChar);
        strcpy_P(instNameBuf, (char *) pgm_read_word (&(init_tables[7])));        
        saveAIN_CMD(instNameBuf, 3, "period=1000");                                             
save2Memory(sepAdminChar);
        strcpy_P(instNameBuf, (char *) pgm_read_word (&(init_tables[8])));        
        saveAIN_CMD(instNameBuf, 3, "period=200");                                             
save2Memory(sepAdminChar);
strcpy_P(instNameBuf, (char *) pgm_read_word (&(init_tables[7])));        
strcpy_P(instNameBuf2, (char *) pgm_read_word (&(init_tables[0])));        
saveBI_CMD(true,instNameBuf, instNameBuf2, 7);
save2Memory(sepAdminChar);
strcpy_P(instNameBuf, (char *) pgm_read_word (&(init_tables[5])));        
strcpy_P(instNameBuf2, (char *) pgm_read_word (&(init_tables[0])));        
saveBI_CMD(true,instNameBuf, instNameBuf2, 5);
save2Memory(sepAdminChar);
strcpy_P(instNameBuf, (char *) pgm_read_word (&(init_tables[4])));        
strcpy_P(instNameBuf2, (char *) pgm_read_word (&(init_tables[1])));        
saveBI_CMD(true,instNameBuf, instNameBuf2, 4);
save2Memory(sepAdminChar);
strcpy_P(instNameBuf, (char *) pgm_read_word (&(init_tables[3])));        
strcpy_P(instNameBuf2, (char *) pgm_read_word (&(init_tables[2])));        
saveBI_CMD(true,instNameBuf, instNameBuf2, 0);
save2Memory(sepAdminChar);
strcpy_P(instNameBuf, (char *) pgm_read_word (&(init_tables[4])));        
strcpy_P(instNameBuf2, (char *) pgm_read_word (&(init_tables[2])));        
saveBI_CMD(true,instNameBuf, instNameBuf2, 2);
save2Memory(sepAdminChar);
strcpy_P(instNameBuf, (char *) pgm_read_word (&(init_tables[6])));        
strcpy_P(instNameBuf2, (char *) pgm_read_word (&(init_tables[2])));        
saveBI_CMD(true,instNameBuf, instNameBuf2, 2);
save2Memory(sepAdminChar);
strcpy_P(instNameBuf, (char *) pgm_read_word (&(init_tables[8])));        
strcpy_P(instNameBuf2, (char *) pgm_read_word (&(init_tables[1])));        
saveBI_CMD(true,instNameBuf, instNameBuf2, 7);
save2Memory(endAdminChar);
}                                                                            
eepromIndex = 2;                                                             
inBytes[serialIndex] = readPMemory(eepromIndex);                             
if (inBytes[serialIndex] == startBAdminChar) {                               
  eepromIndex ++;                                                            
previousBootTime = millis();
  executeScriptFromEEPROM();                                                 
                  kprint("bootms");                                                 
                  Serial.println( millis() - previousBootTime );                      
}                                                                            
kprint("mem");
Serial.println(freeRam());
kprint("emem");
Serial.println(eepromIndex);
}
long nextExecutionGap(int index){
 switch(instances[index]->subTypeCode){
case 0:{
return ((((PushButton *) instances[index] )->nextExecution)- currentMillis());
}
case 3:{
return ((((Timer *) instances[index] )->nextExecution)- currentMillis());
}
}
return -1;
}
unsigned long currentMillis(){                          
return millis();
}
void sleepNowFor(long duration){ 
}                                
#define minSleepDuration 80                        
long minNextOp;                                    
boolean allEmptyQueue;                             
long duration;                                     
void loop(){                                       
  checkForAdminMsg();                              
  for(int i=0;i<nbInstances;i++){                  
    if(periodicExecution(i)){                      
      runInstance(i);                              
    }                                              
  }                                                
  allEmptyQueue = true;                            
  for(int i=0;i<nbInstances;i++){                  
    int queueSize = getPortQueuesSize(i);          
    if(getPortQueuesSize(i)>0){                    
      allEmptyQueue = false;                       
      runInstance(i);                              
    }                                              
  }                                                
//POWER OPTIMISATION                               
  if(allEmptyQueue){                               
    //COMPUTE NEXT DELAY                           
    minNextOp = -1;                                
    for(int i=0;i<nbInstances;i++){                
      long nextOp = nextExecutionGap(i);           
      if(nextOp != -1){                            
        if(minNextOp == -1){                       
          minNextOp = nextOp;                      
        } else {                                   
          if(nextOp < minNextOp){                  
            minNextOp = nextOp;                    
          }                                        
        }                                          
      }                                            
    }//END FOR ALL INSTANCE                        
    duration = minNextOp-minSleepDuration;         
    if( duration > 0){                             
        sleepNowFor(duration);                     
    }                                              
  }//END IF Qeue size null                         
}                                                  
static int freeRam () {
extern int  __bss_end;                                     
extern int* __brkval;                                      
int free_memory;                                           
if (reinterpret_cast<int>(__brkval) == 0) {                
  // if no heap use from end of bss section                
  free_memory = reinterpret_cast<int>(&free_memory)        
                - reinterpret_cast<int>(&__bss_end);       
} else {                                                   
  // use from top of stack to heap                         
  free_memory = reinterpret_cast<int>(&free_memory)        
                - reinterpret_cast<int>(__brkval);         
}                                                          
return free_memory;                                        
}



