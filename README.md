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
	"locations": ["PLANO", "london", "neW YorK", "uS"],
	"includeStatistics": true,
	"maxKeywords": 0,
	"employment": "full time"
}
```
La cui risposta sarà:
```json
{
  "records": [
    {
      "id": 101035,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/101035/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 101034,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/101034/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 101031,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "java developer - software engineering",
      "link": "https://findwork.dev/101031/java-developer-software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 101030,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/101030/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 101029,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/101029/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 101004,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/101004/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 101006,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "applications support engineer",
      "link": "https://findwork.dev/101006/applications-support-engineer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 101011,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "applications support - mainframe",
      "link": "https://findwork.dev/101011/applications-support-mainframe-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 100927,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/100927/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 100924,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/100924/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 100923,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/100923/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 100920,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/100920/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 100794,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/100794/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 100793,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/100793/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 100792,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/100792/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 100791,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/100791/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 100790,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/100790/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 100391,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/100391/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 100395,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/100395/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 100315,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/100315/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 100320,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/100320/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 100317,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer (602)",
      "link": "https://findwork.dev/100317/software-engineer-602-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 100235,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "java software engineering",
      "link": "https://findwork.dev/100235/java-software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 100234,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "java software engineering",
      "link": "https://findwork.dev/100234/java-software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 100236,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "java software engineer (602)",
      "link": "https://findwork.dev/100236/java-software-engineer-602-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 100229,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/100229/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 100230,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/100230/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 100150,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "sr. software engineer",
      "link": "https://findwork.dev/100150/sr-software-engineer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 100147,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "java software engineering",
      "link": "https://findwork.dev/100147/java-software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 100146,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/100146/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 100145,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "java software engineering",
      "link": "https://findwork.dev/100145/java-software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 100144,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/100144/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 100098,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/100098/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 100100,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering (602)",
      "link": "https://findwork.dev/100100/software-engineering-602-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 100099,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering lead",
      "link": "https://findwork.dev/100099/software-engineering-lead-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 100097,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/100097/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 100032,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/100032/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 100029,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/100029/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 100027,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "java software engineering",
      "link": "https://findwork.dev/100027/java-software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99956,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "java software engineering",
      "link": "https://findwork.dev/99956/java-software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99955,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/99955/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99957,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "java software engineering",
      "link": "https://findwork.dev/99957/java-software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99958,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/99958/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99942,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer program (sep) manager",
      "link": "https://findwork.dev/99942/software-engineer-program-sep-manager-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99940,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer",
      "link": "https://findwork.dev/99940/software-engineer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99943,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "sr. software engineer",
      "link": "https://findwork.dev/99943/sr-software-engineer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99883,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/99883/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99846,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/99846/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99850,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/99850/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99848,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/99848/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99860,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering analyst - commercial banking technology",
      "link": "https://findwork.dev/99860/software-engineering-analyst-commercial-banking-technology-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99847,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/99847/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99849,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/99849/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99845,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/99845/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99884,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "sr. software engineer",
      "link": "https://findwork.dev/99884/sr-software-engineer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99883,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/99883/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99855,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/99855/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99856,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/99856/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99857,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer - fraud analytics | commercial banking technology",
      "link": "https://findwork.dev/99857/software-engineer-fraud-analytics-commercial-banking-technology-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99669,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "ha proxy-java/python engineer",
      "link": "https://findwork.dev/99669/ha-proxy-javapython-engineer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99592,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "applications support",
      "link": "https://findwork.dev/99592/applications-support-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99587,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/99587/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99585,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/99585/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99584,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer - solution architecture | ccb finance technology",
      "link": "https://findwork.dev/99584/software-engineer-solution-architecture-ccb-finance-technology-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99516,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "l2 applications support - vp",
      "link": "https://findwork.dev/99516/l2-applications-support-vp-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99512,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "vp, software engineering manager",
      "link": "https://findwork.dev/99512/vp-software-engineering-manager-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99498,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "wholesale payments - vice president, marketing data engineer lead - marketing data",
      "link": "https://findwork.dev/99498/wholesale-payments-vice-president-marketing-data-engineer-lead-marketing-data-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99490,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer - cxloyalty",
      "link": "https://findwork.dev/99490/software-engineer-cxloyalty-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99482,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "java software engineering",
      "link": "https://findwork.dev/99482/java-software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99481,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/99481/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99300,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/99300/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99301,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/99301/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99302,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/99302/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99315,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "senior associate - salesforce software engineer | commercial banking technology -...",
      "link": "https://findwork.dev/99315/senior-associate-salesforce-software-engineer-commercial-banking-technology-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99297,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "global technology women conference - software engineer (salesforce)",
      "link": "https://findwork.dev/99297/global-technology-women-conference-software-engineer-salesforce-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99190,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/99190/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99187,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/99187/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99108,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/99108/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99110,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer - quality lead",
      "link": "https://findwork.dev/99110/software-engineer-quality-lead-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99120,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "devops application support",
      "link": "https://findwork.dev/99120/devops-application-support-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99107,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "full stack java software engineer",
      "link": "https://findwork.dev/99107/full-stack-java-software-engineer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99109,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer - valuations control group technology | corporate technology",
      "link": "https://findwork.dev/99109/software-engineer-valuations-control-group-technology-corporate-technology-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99047,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer, vp - java",
      "link": "https://findwork.dev/99047/software-engineer-vp-java-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99017,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/99017/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 98984,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/98984/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 98981,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/98981/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 98982,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/98982/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 98979,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "android - software engineer",
      "link": "https://findwork.dev/98979/android-software-engineer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 98985,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/98985/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 98980,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/98980/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 98927,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "mainframes application developer",
      "link": "https://findwork.dev/98927/mainframes-application-developer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 98926,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "java developer - software engineering",
      "link": "https://findwork.dev/98926/java-developer-software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 98922,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/98922/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 98921,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/98921/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 98925,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer",
      "link": "https://findwork.dev/98925/software-engineer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 98920,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/98920/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 98829,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/98829/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 98825,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "601- software engineering",
      "link": "https://findwork.dev/98825/601-software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 98826,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/98826/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 98835,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "applications support engineer",
      "link": "https://findwork.dev/98835/applications-support-engineer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 98717,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/98717/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 98724,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/98724/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 98721,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/98721/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 98720,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/98720/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 98719,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/98719/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 98718,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/98718/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 98713,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer - corporate technology",
      "link": "https://findwork.dev/98713/software-engineer-corporate-technology-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 98573,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/98573/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 98569,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/98569/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 98568,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/98568/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 98020,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/98020/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 98019,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/98019/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 98018,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "mainframe software engineer",
      "link": "https://findwork.dev/98018/mainframe-software-engineer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 97996,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer (602)",
      "link": "https://findwork.dev/97996/software-engineer-602-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 97983,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/97983/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 97875,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/97875/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 97815,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "java software engineer (602)",
      "link": "https://findwork.dev/97815/java-software-engineer-602-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 97814,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "java software engineering",
      "link": "https://findwork.dev/97814/java-software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 97813,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "java software engineering",
      "link": "https://findwork.dev/97813/java-software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 97819,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "sr. software engineer",
      "link": "https://findwork.dev/97819/sr-software-engineer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 97762,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "applications support-base24",
      "link": "https://findwork.dev/97762/applications-support-base24-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 97755,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/97755/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 97685,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/97685/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 97656,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering (602)",
      "link": "https://findwork.dev/97656/software-engineering-602-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 97655,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering lead",
      "link": "https://findwork.dev/97655/software-engineering-lead-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 97648,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "applications support lead",
      "link": "https://findwork.dev/97648/applications-support-lead-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 97646,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering analyst - valuations control group technology",
      "link": "https://findwork.dev/97646/software-engineering-analyst-valuations-control-group-technology-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 97642,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/97642/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 97597,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "sr. software engineer",
      "link": "https://findwork.dev/97597/sr-software-engineer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 97510,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering analyst - commercial banking technology",
      "link": "https://findwork.dev/97510/software-engineering-analyst-commercial-banking-technology-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 97505,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/97505/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 97504,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/97504/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 97503,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer - valuations control group",
      "link": "https://findwork.dev/97503/software-engineer-valuations-control-group-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 97502,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/97502/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 97595,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/97595/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 97538,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/97538/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 97506,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/97506/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 97445,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/97445/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 97453,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "applications support",
      "link": "https://findwork.dev/97453/applications-support-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 97449,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/97449/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 97251,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/97251/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 97250,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/97250/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 97185,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer - solution architecture | ccb finance technology",
      "link": "https://findwork.dev/97185/software-engineer-solution-architecture-ccb-finance-technology-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 97110,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/97110/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96999,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer",
      "link": "https://findwork.dev/96999/software-engineer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 97005,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "java software engineering",
      "link": "https://findwork.dev/97005/java-software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 97004,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/97004/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 97007,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer - cxloyalty",
      "link": "https://findwork.dev/97007/software-engineer-cxloyalty-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96764,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "python/django developer - corporate sector | ccb finance technology",
      "link": "https://findwork.dev/96764/pythondjango-developer-corporate-sector-ccb-finance-technology-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96787,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "global technology women conference - software engineer (salesforce)",
      "link": "https://findwork.dev/96787/global-technology-women-conference-software-engineer-salesforce-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96790,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer",
      "link": "https://findwork.dev/96790/software-engineer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96791,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer",
      "link": "https://findwork.dev/96791/software-engineer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96792,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer",
      "link": "https://findwork.dev/96792/software-engineer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96809,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "senior associate - salesforce software engineer | commercial banking technology -...",
      "link": "https://findwork.dev/96809/senior-associate-salesforce-software-engineer-commercial-banking-technology-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96795,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/96795/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96796,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/96796/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96810,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer associate",
      "link": "https://findwork.dev/96810/software-engineer-associate-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96736,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/96736/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96734,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/96734/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96708,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering - java fullstack developer",
      "link": "https://findwork.dev/96708/software-engineering-java-fullstack-developer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96707,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering - java fullstack developer",
      "link": "https://findwork.dev/96707/software-engineering-java-fullstack-developer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96710,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering - java fullstack developer",
      "link": "https://findwork.dev/96710/software-engineering-java-fullstack-developer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96709,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering - java fullstack developer",
      "link": "https://findwork.dev/96709/software-engineering-java-fullstack-developer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96631,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer",
      "link": "https://findwork.dev/96631/software-engineer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96639,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "site reliability engineering lead",
      "link": "https://findwork.dev/96639/site-reliability-engineering-lead-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96630,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/96630/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96543,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/96543/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96542,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer - valuations control group technology | corporate technology",
      "link": "https://findwork.dev/96542/software-engineer-valuations-control-group-technology-corporate-technology-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96541,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/96541/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96540,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer",
      "link": "https://findwork.dev/96540/software-engineer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96539,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/96539/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96538,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/96538/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96458,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer, vp - java",
      "link": "https://findwork.dev/96458/software-engineer-vp-java-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96457,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/96457/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96455,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/96455/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96461,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/96461/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96462,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/96462/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96463,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/96463/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96459,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/96459/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96260,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "android - software engineer",
      "link": "https://findwork.dev/96260/android-software-engineer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96259,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/96259/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96220,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer",
      "link": "https://findwork.dev/96220/software-engineer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96170,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/96170/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96178,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "application support",
      "link": "https://findwork.dev/96178/application-support-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96174,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/96174/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96171,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "601- software engineering",
      "link": "https://findwork.dev/96171/601-software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96092,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/96092/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96091,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer - corporate technology",
      "link": "https://findwork.dev/96091/software-engineer-corporate-technology-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96096,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/96096/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 96099,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/96099/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95708,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/95708/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95711,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/95711/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95709,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/95709/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95707,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/95707/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95705,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/95705/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95703,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/95703/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95642,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer (602)",
      "link": "https://findwork.dev/95642/software-engineer-602-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95641,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/95641/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95639,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "mainframe software engineer",
      "link": "https://findwork.dev/95639/mainframe-software-engineer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95637,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/95637/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95580,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/95580/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95579,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/95579/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95578,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/95578/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95577,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/95577/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95575,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/95575/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95512,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer",
      "link": "https://findwork.dev/95512/software-engineer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95494,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/95494/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95480,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/95480/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95483,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "java software engineer (602)",
      "link": "https://findwork.dev/95483/java-software-engineer-602-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95482,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/95482/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95481,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/95481/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95431,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "java software engineering",
      "link": "https://findwork.dev/95431/java-software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95430,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "sep - software engineering",
      "link": "https://findwork.dev/95430/sep-software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95428,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "java software engineering",
      "link": "https://findwork.dev/95428/java-software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95321,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "java software engineering",
      "link": "https://findwork.dev/95321/java-software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95320,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/95320/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95319,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "java software engineering",
      "link": "https://findwork.dev/95319/java-software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95318,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer",
      "link": "https://findwork.dev/95318/software-engineer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95317,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/95317/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95316,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/95316/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95323,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "java software engineer",
      "link": "https://findwork.dev/95323/java-software-engineer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95218,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/95218/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95219,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/95219/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95155,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering (602)",
      "link": "https://findwork.dev/95155/software-engineering-602-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95159,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/95159/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95156,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering lead",
      "link": "https://findwork.dev/95156/software-engineering-lead-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95157,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/95157/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95089,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/95089/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95092,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering analyst - valuations control group technology | corporate technology",
      "link": "https://findwork.dev/95092/software-engineering-analyst-valuations-control-group-technology-corporate-technology-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95091,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "sr. software engineer",
      "link": "https://findwork.dev/95091/sr-software-engineer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95090,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering - back end microservices",
      "link": "https://findwork.dev/95090/software-engineering-back-end-microservices-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95089,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/95089/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95020,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering analyst - commercial banking technology",
      "link": "https://findwork.dev/95020/software-engineering-analyst-commercial-banking-technology-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95022,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "application support",
      "link": "https://findwork.dev/95022/application-support-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 95021,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering analyst - commercial banking technology",
      "link": "https://findwork.dev/95021/software-engineering-analyst-commercial-banking-technology-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 94937,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/94937/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 94867,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer - solution architecture | ccb finance technology",
      "link": "https://findwork.dev/94867/software-engineer-solution-architecture-ccb-finance-technology-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 94727,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/94727/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 94704,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "manager - software engineering",
      "link": "https://findwork.dev/94704/manager-software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 94702,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/94702/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 94670,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer",
      "link": "https://findwork.dev/94670/software-engineer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 94669,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer",
      "link": "https://findwork.dev/94669/software-engineer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 94675,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer - cxloyalty",
      "link": "https://findwork.dev/94675/software-engineer-cxloyalty-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 94589,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "senior associate - salesforce software engineer | commercial banking technology -...",
      "link": "https://findwork.dev/94589/senior-associate-salesforce-software-engineer-commercial-banking-technology-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 94585,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "early career software engineer recruiter",
      "link": "https://findwork.dev/94585/early-career-software-engineer-recruiter-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 94573,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/94573/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 94570,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer - java",
      "link": "https://findwork.dev/94570/software-engineer-java-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 94567,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer",
      "link": "https://findwork.dev/94567/software-engineer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 94566,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer",
      "link": "https://findwork.dev/94566/software-engineer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 94565,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer",
      "link": "https://findwork.dev/94565/software-engineer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 94564,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "senior software engineer - cio technology | corporate technology",
      "link": "https://findwork.dev/94564/senior-software-engineer-cio-technology-corporate-technology-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 94563,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer - corporate technology",
      "link": "https://findwork.dev/94563/software-engineer-corporate-technology-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 94562,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/94562/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 94559,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "global technology women conference - software engineer (salesforce)",
      "link": "https://findwork.dev/94559/global-technology-women-conference-software-engineer-salesforce-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 94568,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/94568/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 94548,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "java software engineering",
      "link": "https://findwork.dev/94548/java-software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 94547,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/94547/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 94545,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer",
      "link": "https://findwork.dev/94545/software-engineer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 94440,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/94440/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 94437,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "python/django developer - corporate sector | ccb finance technology",
      "link": "https://findwork.dev/94437/pythondjango-developer-corporate-sector-ccb-finance-technology-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 94461,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "senior manager, enterprise architecture",
      "link": "https://findwork.dev/94461/senior-manager-enterprise-architecture-at-capital-one-financial-corporation",
      "keywords": []
    },
    {
      "id": 94446,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering - java fullstack developer",
      "link": "https://findwork.dev/94446/software-engineering-java-fullstack-developer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 94449,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "sr. software engineering",
      "link": "https://findwork.dev/94449/sr-software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 94440,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/94440/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 94444,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering - java fullstack developer",
      "link": "https://findwork.dev/94444/software-engineering-java-fullstack-developer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 94445,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering - java fullstack developer",
      "link": "https://findwork.dev/94445/software-engineering-java-fullstack-developer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 94446,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering - java fullstack developer",
      "link": "https://findwork.dev/94446/software-engineering-java-fullstack-developer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 94353,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/94353/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 94349,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/94349/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 94348,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering - java fullstack developer",
      "link": "https://findwork.dev/94348/software-engineering-java-fullstack-developer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 94260,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/94260/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 94259,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer",
      "link": "https://findwork.dev/94259/software-engineer-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 94206,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer, vp - java",
      "link": "https://findwork.dev/94206/software-engineer-vp-java-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 94203,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineer - valuations control group technology | corporate technology",
      "link": "https://findwork.dev/94203/software-engineer-valuations-control-group-technology-corporate-technology-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 94204,
      "location": "PLANO",
      "remote": false,
      "employment": "full time",
      "role": "software engineering",
      "link": "https://findwork.dev/94204/software-engineering-at-jpmorgan-chase-co",
      "keywords": []
    },
    {
      "id": 99762,
      "location": "LONDON",
      "remote": false,
      "employment": "full time",
      "role": "engineering manager",
      "link": "https://findwork.dev/99762/engineering-manager-at-tessian",
      "keywords": []
    },
    {
      "id": 97683,
      "location": "NEW YORK",
      "remote": false,
      "employment": "full time",
      "role": "tech lead manager",
      "link": "https://findwork.dev/97683/tech-lead-manager-at-duolingo-inc",
      "keywords": []
    }
  ],
  "statistics": [
    {
      "location": "PLANO",
      "fullTimePercentage": 1.0,
      "partTimePercentage": 0.0,
      "remotePercentage": 0.0,
      "topRoles": [
        "software engineering",
        "software engineer",
        "java software engineering",
        "software engineering - java fullstack developer",
        "sr. software engineer"
      ],
      "minKeywords": 0,
      "maxKeywords": 0,
      "keywordsStatistics": []
    },
    {
      "location": "london",
      "fullTimePercentage": 1.0,
      "partTimePercentage": 0.0,
      "remotePercentage": 0.0,
      "topRoles": [
        "engineering manager"
      ],
      "minKeywords": 0,
      "maxKeywords": 0,
      "keywordsStatistics": []
    },
    {
      "location": "neW YorK",
      "fullTimePercentage": 1.0,
      "partTimePercentage": 0.0,
      "remotePercentage": 0.0,
      "topRoles": [
        "tech lead manager"
      ],
      "minKeywords": 0,
      "maxKeywords": 0,
      "keywordsStatistics": []
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

## Software Utilizzati
* [SpringBoot](https://spring.io/projects/spring-boot) - Framework per applicazioni Java.
* [Eclipse](https://www.eclipse.org/) - IDE per linguaggio Java.

## Reference API
* **FindWork.dev:** [jobs](https://findwork.dev/api/jobs)

## Autori
* **Adelio Antonini**
* **Leonardo Notarandrea**
* **Elia Zito**