# **FANTAPARROCCHIE**

## **IL FANTACALCIO DEL TORNEO DELLE PARROCCHIE**

### ESEGUIRE IL JAR

Il modo più facile per eseguire il jar é caricare il progetto in un IDE ed eseguire la  classe FantacalcioApplication.

L'unica cosa di cui necessita è un database mariaDB locale. I dati di configurazione del database sono presenti nel file
 ./resources/application.yml
 
N.B. è consigliato attenersi alla configurazione del DB fornita.

### ENDPOINTS

Tutti gli endpoint, per ora, hanno come prefisso http://localhost:8080/rest

* GET /check
    * endpoint di prova per vedere se il sistema é correttamente configurato


* GET /player/{id}
    * restituisce il giocatore con quell'id
* GET /player/
    * restituisce una lista di tutti i giocatori presenti nel DB
* PUT /player/
    * permette il salvataggio di un giocatore con i dati passati
    * INPUT:
        * String nome
        * String cognome
        * String squadraReale
        * String squadraFanta (se il giocatore è svincolato passare null o stringa vuota o "svincolato")
* DELETE /player/{id}
    * permette di cancellare il giocatore con quell'id dal DB

### PROSSIMI SVILUPPI
1. Inserire il sistema (compreso di db (?)) in un docker in modo da evitare possibili configurazioni arbitrarie del db.
2. Popolare il DB
3. Creare tutta la parte di calcolo dei punti, rose, formazioni, ecc...
4. Fornire tutti gli endpoint necessari
5. Implementare la parte di security
