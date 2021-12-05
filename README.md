# Esame FindWork

Scopo di questo progetto è la creazione di una REST API che consenta di:

* Ottenere, specificando una lista di città, le relative offerte di lavoro.
* Ottenere dei suggerimenti per le città utilizzabili come luoghi di ricerca.
* Ottenere delle statistiche su ogni città ricercata. Nello specifico su **percentuale di impieghi a contratto/part-time, full time e da remoto**, **numero minimo/massimo di keywords**, **statistiche per le keywords**.

L'acronimo REST sta per "**RE**presentational **S**tate **T**ransfer",  definisce delle linee guida da seguire durante lo sviluppo delle API per la gestione delle risorse remote ed indica come mappare le operazioni CRUD (**C**reate, **R**etrieve, **U**pdate, **D**elete) da eseguire sul dato, tramite i 4 metodi messi a disposizione dal protocollo HTTP: **GET**, **POST**, **PUT**, **DELETE**.

## Percorsi API
L'applicazione mette a disposizione 2 rotte distinte.

>GET /api/search

Richiede un body con i parametri di filtraggio. Deve essere presente almeno il campo "**locations**". Restituisce: 
* Una lista di record contenente la totalità di offerte di lavoro fra le varie **location** (ovviamente applicando eventuali filtri)
* Una lista di statistiche per ogni **location**

Bisogna specificare alcune peculiarità dell'endpoint:
* Implicitamente, vengono scartati tutti i lavori in cui non è specificato il tipo di impiego;
* In concomitanza col punto sopracitato, l'endpoint può ritornare una lista vuota, se nulla dovesse rispettare i filtri e le condizioni implicite del codice.
* fra i filtri, vi è la possibilità di non calcolare le statistiche. In tal caso il campo **ststistics** del JSON di risposta sarà **null**.

>GET /api/suggestions

Restituisce una breve lista di **location** utilizzabili. Da notare che non specifica la totalità di esse.

### Le chiamate
Ricapitolando:
| **Metodo** | **Richiesta** | **Risposta** |
| :--- | :---: | ---: |
| GET   | /api/suggestions	| JSON |
| GET   | /api/search		| JSON |

## Software Utilizzati
* [SpringBoot](https://spring.io/projects/spring-boot) - Framework per applicazioni Java.
* [Eclipse](https://www.eclipse.org/) - IDE per linguaggio Java.

## Reference API
* **FindWork.dev:** [jobs](https://findwork.dev/api/jobs)