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

#### */api/search*
Un esempio di body:
```json
{
	"locations": ["berlin"],
	"includeStatistics": true,
	"role": "blockchain"
}
```
La cui risposta sarà:
```json
{
  "records": [
    {
      "id": 98120,
      "location": "PARIS BERLIN TOKYO DUBAI",
      "remote": true,
      "employment": "contract",
      "role": "blockchain engineer",
      "link": "https://findwork.dev/98120/blockchain-engineer-at-dsla-protocol",
      "keywords": [
        "blockchain"
      ]
    },
    {
      "id": 95229,
      "location": "BERLIN, GERMANY",
      "remote": false,
      "employment": "full time",
      "role": "cto/co-founder for smart contract/blockchain startup (m/f/d) // termz",
      "link": "https://findwork.dev/95229/ctoco-founder-for-smart-contractblockchain-startup-mfd-termz-at-termz",
      "keywords": [
        "node",
        "blockchain",
        "javascript",
        "aws",
        "postgresql",
        "docker",
        "react"
      ]
    }
  ],
  "statistics": [
    {
      "location": "berlin",
      "fullTimePercentage": 0.5,
      "partTimePercentage": 0.5,
      "remotePercentage": 0.5,
      "topRoles": [
        "cto/co-founder for smart contract/blockchain startup (m/f/d) // termz",
        "blockchain engineer"
      ],
      "minKeywords": 1,
      "maxKeywords": 7,
      "keywordsStatistics": [
        {
          "keyword": "blockchain",
          "percentageRemote": 0.5,
          "percentageFullTime": 0.5,
          "percentualeKeyword": 1.0
        },
        {
          "keyword": "node",
          "percentageRemote": 0.0,
          "percentageFullTime": 1.0,
          "percentualeKeyword": 0.5
        },
        {
          "keyword": "javascript",
          "percentageRemote": 0.0,
          "percentageFullTime": 1.0,
          "percentualeKeyword": 0.5
        },
        {
          "keyword": "aws",
          "percentageRemote": 0.0,
          "percentageFullTime": 1.0,
          "percentualeKeyword": 0.5
        },
        {
          "keyword": "postgresql",
          "percentageRemote": 0.0,
          "percentageFullTime": 1.0,
          "percentualeKeyword": 0.5
        },
        {
          "keyword": "docker",
          "percentageRemote": 0.0,
          "percentageFullTime": 1.0,
          "percentualeKeyword": 0.5
        },
        {
          "keyword": "react",
          "percentageRemote": 0.0,
          "percentageFullTime": 1.0,
          "percentualeKeyword": 0.5
        }
      ]
    }
  ]
}
```

#### */api/suggestions*
Questo percorso ritorna semplicemente una lista di luoghi, scelti arbitrariamenti fra quelli che restituiscono record senza filtri:
```json
[
	"LONDON",
	"BERLIN",
	"PLANO",
	"NEW YORK",
	"PARIS",
	"TOKYO",
	"CHICAGO",
	"BOSTON",
	"SAN FRANCISCO",
	"DENVER",
	"US"
]
```

### Class Diagram
I **diagrammi delle classi** rappresentano, a vari livelli di astrazione, il **contesto** in cui un sistema software deve operare, è utile anche nella progettazione delle parti che lo compongono e delle loro relazioni.
![Diagramma delle classi](https://github.com/adelioA/Programmazione-Oggetti/blob/master/uml/class-diagram.png)

### Use Case Diagram
Il **diagramma dei casi d'uso**, tramite l'utilizzo di **attori**, descrive le funzioni o i servizi messi a disposizione dal sistema.
![Diagramma dei casi d'uso](https://github.com/adelioA/Programmazione-Oggetti/blob/master/uml/use-cases.png)

### Sequence Diagram
Il **diagramma di sequenza** individua relazioni, in particolare i **messaggi** che intercorrono tra le entità rappresentate.
![Diagramma di sequenza](https://github.com/adelioA/Programmazione-Oggetti/blob/master/uml/sequence-diagram.png)

## Software Utilizzati
* [SpringBoot](https://spring.io/projects/spring-boot) - Framework per applicazioni Java.
* [Eclipse](https://www.eclipse.org/) - IDE per linguaggio Java.

## Reference API
* **FindWork.dev:** [jobs](https://findwork.dev/api/jobs)

## Autori
* **Adelio Antonini**
* **Leonardo Notarandrea**
* **Elia Zito**