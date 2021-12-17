-- OP.1 CREATE NEW PASSEGGERO()
INSERT INTO aeroporto.persona (CodiceFiscale, Nome, Cognome, Età, Ruolo,  CodVolo )
VALUES("BNCNTN81T12L736O", "Antonio", "Bianchi", 39, "Passeggero", 1); 

-- OP. 2 Numero totale di componenti non funzionanti in un aereo.
SELECT COUNT(Funzionante) AS Num_Componenti_Non_Funzionanti
FROM aeroporto.aereo A JOIN aeroporto.componente_aereo CA ON A.CodAereo = CA.CodAereo
WHERE A.CodAereo = 1
AND CA.Funzionante = 0;

-- OP. 3 Voli in partenza
SELECT volo.CodTratta, CodVolo, Nome, Città_partenza, Città_destinazione, Aeroporto_partenza, Aeroporto_destinazione, Ora_partenza, Ora_fine
FROM aeroporto.volo, aeroporto.tratta 
WHERE aeroporto.volo.CodTratta = aeroporto.tratta.CodTratta
AND aeroporto.tratta.Aeroporto_partenza = "Malpensa"
AND Ora_Partenza = "11:00";

-- OP. 4 Voli in arrivo
SELECT volo.CodTratta, CodVolo, Nome, Città_partenza, Città_destinazione, Aeroporto_partenza, Aeroporto_destinazione, Ora_partenza, Ora_fine
FROM aeroporto.volo, aeroporto.tratta
WHERE aeroporto.volo.CodTratta = aeroporto.tratta.CodTratta
AND aeroporto.tratta.Aeroporto_destinazione = "Fiumicino"
AND Ora_fine = "12:12";

-- OP. 5 Manutenzione di un aereo
SELECT CodMantenimento, CodMacchinario, aeroporto.mantenimento.CodAereo
FROM aeroporto.mantenimento, aeroporto.aereo
WHERE aeroporto.mantenimento.CodAereo = aeroporto.aereo.CodAereo;

-- OP. 6 Comunicazione tra membri dell'equipaggio e controllori
SELECT Num_Aerei_in_comunicazione
FROM aeroporto.torre_di_controllo
WHERE CodTorre = 1;

-- OP. 7 Rifornimento di un aereo
SELECT *
FROM aeroporto.logistica, aeroporto.cargo
WHERE logistica.CodLogistica = cargo.CodLogistica
AND logistica.Materiali = "Kerosene";

-- OP. 8 Assunzione di nuovi addetti
INSERT INTO aeroporto.persona (CodiceFiscale, Nome, Cognome, Età, Ruolo, Ora_inizio, Ora_fine)
VALUES ("LGURSO95P09G888P", "Luigi", "Rosa", 37, "Addetto di scalo", "15:00", "21:00");

-- OP. 9 Controllare numero di radar presenti all'aeroporto
SELECT COUNT(CodRadar) AS Num_Radars
FROM aeroporto.radar;

-- OP. 10 Aerei in nella Via di Rullaggio
SELECT Num_Aerei
FROM aeroporto.via_di_rullaggio
WHERE CodVia = 1;

-- OP. 11 Persone che comprano prodotti ai negozi
SELECT COUNT(P.CodiceFiscale) AS Num_Clienti
FROM aeroporto.negozio N JOIN aeroporto.persona P on P.CodNegozio = N.CodNegozio
WHERE P.CodNegozio = 1; 

-- OP. 12 Persone che si recano al terminal
SELECT COUNT(P.CodiceFiscale) AS Persone_Al_Terminal
FROM aeroporto.terminal T JOIN aeroporto.persona P ON T.CodTerminal = P.CodTerminal
WHERE P.CodTerminal = 1;

-- OP. 13 Nuovi membri dell'equipaggio assunti da una compagnia aerea
INSERT INTO aeroporto.persona ( CodiceFiscale, Nome, Cognome, Età, Ruolo, Ora_Inizio, Ora_fine, CodAereo )
VALUES ( "MRTLLL96H58I138R", "Martina", "Lilla", 25, "Hostess", "09:00", "12:00", 1 );

-- OP. 14 Inserimento degli aerei stazionati negli hangar
INSERT INTO aeroporto.aereo ( CodAereo, CodPista, Nome, Num_Equipaggio, Peso, Tipologia, CodHangar)
VALUES( 3, 3, "Boeing 747", 12, 377842, "Wide-body jet airliner", 2);

-- OP. 15 Calcolare l'età media dei passeggeri
SELECT AVG(Età) AS Media_Età
FROM aeroporto.persona
WHERE Ruolo = "Passeggero";

-- OP. 16 Ottenere il num. aerei di una compagnia aerea
SELECT Num_Aerei
FROM aeroporto.compagnia_aerea
WHERE CodCompagnia = 1; 

-- OP. 17 Numero di controllori in una Torre di Controllo
SELECT Num_dipendenti
FROM aeroporto.torre_di_controllo
WHERE CodTorre = 1; 

-- OP. 18 Numero di macchinari presenti nell'aeroporto
SELECT SUM(Quantità) AS Num_Totale_Macchinari
FROM aeroporto.ground_support_equipment;

-- OP. 19 Mostrare i controllori che erano in servizio dalle 08:00 alle 13:00
SELECT CodiceFiscale, Nome, Cognome, Età, CodTorre, CodCentro, Ora_inizio, Ora_fine
FROM aeroporto.persona
WHERE Ruolo = "Controllore"
AND Ora_inizio >= CAST("8:00" AS TIME)
AND Ora_fine <= CAST("13:00" AS TIME)
GROUP BY Cognome;

-- OP. 20 Numeri aerei commerciali di una compagnia aerea
SELECT COUNT(Commerciale) AS Num_Aerei_Commerciali
FROM aeroporto.aereo
WHERE Commerciale = 1;

-- OP. 21 Quantità di merci trasportate in media da un aereo commerciale
SELECT AVG(Num_Merci) AS Media_Merci_Trasportate
FROM aeroporto.aereo;