CREATE DATABASE PRZERO;
USE PRZERO;

CREATE TABLE `tab_operatori` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOME` varchar(100) DEFAULT NULL,
  `COGNOME` varchar(100) DEFAULT NULL,
  `USERNAME` varchar(100) NOT NULL,
  `PASSWORD` varchar(100) NOT NULL,
  `EMAIL` varchar(100) NOT NULL,
  `DATA_REGISTRAZIONE` date NULL,
  `DATA_SCADENZA` date NULL,
  `ID_CENTRO` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tab_ruoli` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RUOLO` varchar(100) DEFAULT NULL,
  `DESCRIZIONE` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tab_centri` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOME` varchar(100) DEFAULT NULL,
  `DESCRIZIONE` varchar(100) DEFAULT NULL,
  `INDIRIZZO` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tab_abilitazioni` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_OPERATORE` int(11) NOT NULL,
  `ID_RUOLO` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tab_log_accessi` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_OPERATORE` int(11) NOT NULL,
  `INIZIO_SESSIONE` datetime NOT NULL,
  `FINE_SESSIONE` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO tab_centri (NOME,DESCRIZIONE) VALUES ('Centro P','Centro Prova');

INSERT INTO tab_operatori (NOME,COGNOME,USERNAME,PASSWORD,EMAIL,ID_CENTRO) VALUES ('Nicola','Dileo','nicola.dileo','5f4dcc3b5aa765d61d8327deb882cf99','nd@mail.it',1);
INSERT INTO tab_operatori (NOME,COGNOME,USERNAME,PASSWORD,EMAIL,ID_CENTRO) VALUES ('Paolo','Rossi','paolo.rossi','5f4dcc3b5aa765d61d8327deb882cf99','nd@mail.it',1);
INSERT INTO tab_operatori (NOME,COGNOME,USERNAME,PASSWORD,EMAIL,ID_CENTRO) VALUES ('Paolo','Verdi','paolo.verdi','5f4dcc3b5aa765d61d8327deb882cf99','nd@mail.it',1);
UPDATE tab_operatori set DATA_REGISTRAZIONE = '2018-10-20';
UPDATE tab_operatori set DATA_SCADENZA = '2019-10-20';

INSERT INTO tab_ruoli (RUOLO,DESCRIZIONE) VALUES ('ROLE_PRZERO','Ruolo accesso applicazione');
INSERT INTO tab_ruoli (RUOLO,DESCRIZIONE) VALUES ('ROLE_001','Ruolo funzione n.1');
INSERT INTO tab_ruoli (RUOLO,DESCRIZIONE) VALUES ('ROLE_002','Ruolo funzione n.2');
INSERT INTO tab_ruoli (RUOLO,DESCRIZIONE) VALUES ('ROLE_003','Ruolo funzione n.3');

INSERT INTO tab_abilitazioni (ID_OPERATORE,ID_RUOLO) VALUES (1,1);
INSERT INTO tab_abilitazioni (ID_OPERATORE,ID_RUOLO) VALUES (1,2);
INSERT INTO tab_abilitazioni (ID_OPERATORE,ID_RUOLO) VALUES (1,3);
INSERT INTO tab_abilitazioni (ID_OPERATORE,ID_RUOLO) VALUES (1,4);
INSERT INTO tab_abilitazioni (ID_OPERATORE,ID_RUOLO) VALUES (2,1);
