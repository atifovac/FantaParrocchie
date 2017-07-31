CREATE TABLE fantasquadra
(
  nome         VARCHAR(50)          NOT NULL
    PRIMARY KEY,
  fanta_soldi  INT DEFAULT 'NULL'   NULL,
  punti_totali FLOAT DEFAULT 'NULL' NULL,
  modificabile BIT DEFAULT NULL     NULL,
  president_id BIGINT               NOT NULL
);

CREATE INDEX FKpolgjbpmx78krecil15wu7hij
  ON fantasquadra (president_id);

CREATE TABLE fantasquadra_roster
(
  fanta_team_nome VARCHAR(255) NOT NULL,
  roster_id       BIGINT       NOT NULL,
  CONSTRAINT UK_osbdhxyuicu4igv38twu36a6n
  UNIQUE (roster_id),
  CONSTRAINT FKfu0kcyrrq6lckmxe4ic0itb8s
  FOREIGN KEY (fanta_team_nome) REFERENCES FantaParrocchieDB.fantasquadra (nome)
);

CREATE INDEX FKfu0kcyrrq6lckmxe4ic0itb8s
  ON fantasquadra_roster (fanta_team_nome);

CREATE TABLE formazione_giornaliera
(
  id                BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  modulo_formazione VARCHAR(255) DEFAULT 'NULL' NULL,
  punti             FLOAT DEFAULT 'NULL'        NULL,
  fanta_squadra     VARCHAR(255)                NOT NULL,
  giornata          INT                         NOT NULL,
  CONSTRAINT UKo9ihrrbn743wmykb3awke8v3g
  UNIQUE (giornata, fanta_squadra),
  CONSTRAINT FKf5kt696xt10b52cb5kdm7m18m
  FOREIGN KEY (fanta_squadra) REFERENCES FantaParrocchieDB.fantasquadra (nome)
);

CREATE INDEX FKf5kt696xt10b52cb5kdm7m18m
  ON formazione_giornaliera (fanta_squadra);

CREATE TABLE giocatore
(
  id               BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  ruolo_formazione VARCHAR(255) DEFAULT 'NULL' NULL,
  nome             VARCHAR(255) DEFAULT 'NULL' NULL,
  squadra_reale    VARCHAR(255) DEFAULT 'NULL' NULL,
  ruolo_rosa       VARCHAR(255) DEFAULT 'NULL' NULL,
  cognome          VARCHAR(255) DEFAULT 'NULL' NULL,
  quotazione       INT DEFAULT 'NULL'          NULL,
  CONSTRAINT UKsic8yo14efj0o6btoyxydx9oq
  UNIQUE (nome, cognome)
);

ALTER TABLE fantasquadra_roster
  ADD CONSTRAINT FKgvrlme6xkn8h1pc5n7ovpnl7
FOREIGN KEY (roster_id) REFERENCES FantaParrocchieDB.giocatore (id);

CREATE TABLE giornata
(
  numero_giornata INT AUTO_INCREMENT
    PRIMARY KEY,
  calcolabile     BIT DEFAULT NULL NULL,
  inseribile      BIT DEFAULT NULL NULL
);

ALTER TABLE formazione_giornaliera
  ADD CONSTRAINT FK1hqcd9ocm9dkrcvsfu4bdko4e
FOREIGN KEY (giornata) REFERENCES FantaParrocchieDB.giornata (numero_giornata);

CREATE TABLE log
(
  id          BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  information VARCHAR(255) DEFAULT 'NULL' NULL,
  level       VARCHAR(255) DEFAULT 'NULL' NULL,
  message     VARCHAR(255) DEFAULT 'NULL' NULL,
  operation   VARCHAR(255) DEFAULT 'NULL' NULL,
  timestamp   DATETIME                    NOT NULL
);

CREATE TABLE schema_version
(
  installed_rank INT                                     NOT NULL
    PRIMARY KEY,
  version        VARCHAR(50) DEFAULT 'NULL'              NULL,
  description    VARCHAR(200)                            NOT NULL,
  type           VARCHAR(20)                             NOT NULL,
  script         VARCHAR(1000)                           NOT NULL,
  checksum       INT DEFAULT 'NULL'                      NULL,
  installed_by   VARCHAR(100)                            NOT NULL,
  installed_on   TIMESTAMP DEFAULT 'current_timestamp()' NOT NULL,
  execution_time INT                                     NOT NULL,
  success        TINYINT(1)                              NOT NULL
);

CREATE INDEX schema_version_s_idx
  ON schema_version (success);

CREATE TABLE utente
(
  id       BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  email    VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  username VARCHAR(255) NOT NULL,
  CONSTRAINT UK_gxvq4mjswnupehxnp35vawmo2
  UNIQUE (email),
  CONSTRAINT UK_2vq82crxh3p7upassu0k1kmte
  UNIQUE (username)
);

ALTER TABLE fantasquadra
  ADD CONSTRAINT FKpolgjbpmx78krecil15wu7hij
FOREIGN KEY (president_id) REFERENCES FantaParrocchieDB.utente (id);

CREATE TABLE votazione
(
  id         BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  voto       FLOAT DEFAULT 'NULL'  NULL,
  giocatore  BIGINT                NOT NULL,
  giornata   INT                   NOT NULL,
  formazione BIGINT DEFAULT 'NULL' NULL,
  CONSTRAINT UKrmjw4puvci9hvjthsv4coq58m
  UNIQUE (giornata, giocatore),
  CONSTRAINT FKlf58606dxy76b6vkwa12yl720
  FOREIGN KEY (giocatore) REFERENCES FantaParrocchieDB.giocatore (id),
  CONSTRAINT FKnfjicutpfxgc9x79plwr9vhll
  FOREIGN KEY (giornata) REFERENCES FantaParrocchieDB.giornata (numero_giornata),
  CONSTRAINT FKobyfdqnved4w72ed61bnu17qb
  FOREIGN KEY (formazione) REFERENCES FantaParrocchieDB.formazione_giornaliera (id)
);

CREATE INDEX FKlf58606dxy76b6vkwa12yl720
  ON votazione (giocatore);

CREATE INDEX FKobyfdqnved4w72ed61bnu17qb
  ON votazione (formazione);

