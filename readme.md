JustMeet IDS A.A. 19/20

# :ticket: JustMeet
Progetto realizzato per l'esame di Ingegneria del Software dell'Università di Camerino.

## Componenti gruppo:

- **Donoval Candolfi**  
donoval.candolfi@studenti.unicam.it
- **Andrea Perlini**  
andrea.perlini@studenti.unicam.it
- **Arlind Canga**  
arlind.canga@studenti.unicam.it


## Sviluppato in

- **Angular/Ionic** per la parte frontend,
- **Spring Boot** per la parte backend

## Database
- **MongoDB** per lo storage dei dati

## Getting Started

Le istruzioni seguenti ti permetteranno di eseguire il sistema sulla tua macchina.

### Prerequisiti

Di seguito troverai tutto quello che ti occorre per preparare il tuo ambiente per eseguire JustMeet :
- JDK 1.8 (versione minima)
- Maven
- Il tuo IDE preferito ( si consiglia Intellij )

## Come avviare il sistema

**Per il Frontend :**

```
Modalità development:

git clone https://github.com/Donz2020/JustMeet
cd .\frontend\JustmeetFrontend
npm install
ng serve


Modalità produzione:


cd .\frontend\JustmeetFrontend
npm install
npm install -g http-server
ng build --prod
http-server -p 4200 -c-1 dist\JustMeetFrontend

```

**Per il Backend :**

```
Modalità development:

git clone https://github.com/Donz2020/JustMeet
cd .\it.unicam.cs.ids.justmeet.backend
mvn spring-boot:run



Modalità produzione :

cd .\it.unicam.cs.ids.justmeet.backend
mvn install 

```

**Or**

```
cd .\it.unicam.cs.ids.justmeet.backend
mvn package 
cd .\target
java -jar backend-0.0.1-SNAPSHOT.jar

```

