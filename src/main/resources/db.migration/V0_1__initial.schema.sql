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

CREATE TABLE fantasquadra
(
  nome         VARCHAR(50)        NOT NULL
    PRIMARY KEY,
  fanta_soldi  INT DEFAULT 'NULL' NULL,
  president_id BIGINT             NOT NULL,
  CONSTRAINT FKpolgjbpmx78krecil15wu7hij
  FOREIGN KEY (president_id) REFERENCES FantaParrocchieDB.utente (id)
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
  FOREIGN KEY (fanta_team_nome) REFERENCES FantaParrocchieDB.fantasquadra (nome),
  CONSTRAINT FKgvrlme6xkn8h1pc5n7ovpnl7
  FOREIGN KEY (roster_id) REFERENCES FantaParrocchieDB.giocatore (id)
);

CREATE INDEX FKfu0kcyrrq6lckmxe4ic0itb8s
  ON fantasquadra_roster (fanta_team_nome);


CREATE TABLE giocatore
(
  id               BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  ruolo_formazione VARCHAR(255) DEFAULT 'NULL' NULL,
  nome             VARCHAR(255) DEFAULT 'NULL' NULL,
  squadra_reale    INT DEFAULT 'NULL'          NULL,
  ruolo_rosa       INT DEFAULT 'NULL'          NULL,
  cognome          VARCHAR(255) DEFAULT 'NULL' NULL,
  quotazione       BIGINT DEFAULT 'NULL'       NULL,
  CONSTRAINT UKsic8yo14efj0o6btoyxydx9oq
  UNIQUE (nome, cognome)
);
