# **FANTAPARROCCHIE**

## **IL FANTACALCIO DEL TORNEO DELLE PARROCCHIE**

### ESEGUIRE IL JAR

Il modo più facile per eseguire il jar é caricare il progetto in un IDE ed eseguire la  classe FantacalcioApplication.

L'unica cosa di cui necessita è un database [mariaDB](https://mariadb.com/downloads) locale. I dati di configurazione 
del database sono presenti nel file 
[application.yml](https://github.com/atifovac/FantaParrocchie/blob/master/src/main/resources/application.yml)
 
N.B. è consigliato attenersi alla configurazione del DB fornita.

### ENDPOINTS

Tutti gli endpoint, per ora, hanno come prefisso "http://localhost:8080/rest"

* GET /check
    * endpoint di prova per vedere se il sistema é correttamente configurato
    * OUTPUT:
        * "Hello, World!!"


* GET /player/{id}
    * restituisce il giocatore con quell'id
    * OUTPUT:
        * Player
* GET /player/
    * restituisce una lista di tutti i giocatori presenti nel DB
    * OUTPUT:
        * List\<Player>
* PUT /player/
    * permette il salvataggio di un giocatore con i dati passati
    * INPUT:
        * String nome
        * String cognome
        * String squadraReale
        * String nomeFantaSquadra (se il giocatore è svincolato passare null o stringa vuota o "svincolato")
    * OUTPUT:
        * Player
* POST /player/{id}
    * permette l'aggiornamento dei dati relativi a un giocatore
    * INPUT \[tutti facoltativi\]:
        * String nome
        * String cognome
        * String squadraReale
        * String nomeFantaSquadra (se il giocatore è svincolato passare null o stringa vuota o "svincolato")
    * OUTPUT:
        * Player
* DELETE /player/{id}
    * permette di cancellare il giocatore con quell'id dal DB
    
    
* GET /fantasquadra/{nome}
    * restituisce la fantasquadra con quel nome
    * OUTPUT:
        * FantaTeam
* GET /fantasquadra/
    * restituisce una lista di tutte le fantasquadre presenti nel DB
    * OUTPUT:
        * List\<FantaTeam>
* PUT /fantasquadra/
    * permette il salvataggio di una fantasquadra con i dati passati
    * INPUT:
        * String nome
        * String presidente
        * Long fantaSoldi \[da indicare in unità]
    * OUTPUT:
        * FantaTeam
* POST /fantasquadra/{vecchioNome}
    * permette l'aggiornamento dei dati relativi a  una fantasquadra
    * INPUT \[tutti facoltativi\]:
        * String nome
        * String presidente
        * Long fantaSoldi \[da indicare in unità]
    * OUTPUT:
        * FantaTeam
* DELETE /fantasquadra/{nome}
    * permette di cancellare la fantasquadra con quel nome dal DB

### PROSSIMI SVILUPPI
1. Inserire il sistema (backend + DB + futuro frontend) in un docker ciascuno (da gestire tramite docker-compose) 
       in modo da evitare possibili configurazioni arbitrarie del db.
1. Popolare il DB
1. Creare tutta la parte di calcolo dei punti, rose, formazioni, ecc...
1. Fornire tutti gli endpoint necessari
1. Implementare la parte di security
1. Verificare che i dati inseriti siano accettabili (no duplicazioni)
1. Implementare la possibilità di aggiungere delle immagini al DB
