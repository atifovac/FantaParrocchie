#**FANTAPARROCCHIE**

##**IL FANTACALCIO DEL TORNEO DELLE PARROCCHIE**

###ESEGUIRE IL JAR

Il modo più facile per eseguire il jar é caricare il progetto in un IDE ed eseguire la  classe FantacalcioApplication.

L'unica cosa di cui necessita è un database mariaDB locale. I dati di configurazione del database sono presenti nel file
 ./resources/application.yml
 
N.B. è consigliato attenersi alla configurazione fornita.

###ENDPOINTS

* GET /rest/ciao
    * endpoint di prova per vedere se il sistema é correttamente configurato

###PROSSIMI SVILUPPI
1. Inserire il sistema (compreso di db (?)) in un docker in modo da evitare possibili configurazioni arbitrarie del db.
2. Popolare il DB
3. Creare tutta la parte di calcolo dei punti, rose, formazioni, ecc...
4. Fornire tutti gli endpoint necessari
