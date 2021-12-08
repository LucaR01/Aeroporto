-- TODO: aggiungere qualche GROUP BY DESC o ASC
-- USARE Persona P JOIN Aereo A e ON P.Cod = A.Cod, ecc..

-- Aggiungere in AEREO tipo un boolean per capire se è un aereo commerciale o no.

-- TODO: magari in pista potrei mettere qualche specifica, tipo lunghezza, visto che 
-- altrimenti ha solo il codice

-- Cargo capacity aka Num_Merci in tonnellate nel secondo, ma in volume nel primo
-- Io sceglierei tonnellate.

-- OP.1 CREATE NEW PASSEGGERO()
-- TODO: questa funziona è solo da decommentare.
-- INSERT INTO aeroporto.persona (CodiceFiscale, CodBagaglio, Nome, Cognome, Età, CodVolo )
-- VALUES("BNCNTN81T12L736O", 2, "Antonio", "Bianchi", 39, 1); 
-- i punti interrogativi vogliono dire che è un input dall'utente.

-- OP.2 CONTROLLARE UN PASSEGGERO
-- Mi servirebbe tipo un attributo boolean controllato. Per controllare un passeggero
-- e quindi fare passi un passeggero e gli controlli l'attributo.

-- Oppure posso fare un controllo del bagaglio. con ACC_CodBagaglio in persona
-- e bagaglio, semplicemente restituisco il bagaglio. ( così facciamo il controllo )

-- SELECT bagaglio.CodBagaglio, Peso, persona.CodiceFiscale, persona.Nome, persona.Cognome, persona.Età, ACC_CodBagaglio
-- FROM aeroporto.persona, aeroporto.bagaglio
-- WHERE persona.Cognome = "Bianchi" -- TODO: da riguardare
-- AND persona.ACC_CodBagaglio = persona.CodBagaglio
-- AND bagaglio.CodBagaglio = persona.ACC_CodBagaglio;

-- TODO: Controllare se un componente dell'aereo è funzionante o no.
-- o magari anche quanti pezzi non sono funzionanti (tipo un controllo se tutti funzionano ok)
-- di uno specifico aereo

-- OP. 2 Numero totale di componenti non funzionanti in un aereo.

SELECT COUNT(Funzionante) AS Num_Componenti_Non_Funzionanti
FROM aeroporto.aereo A JOIN aeroporto.componente_aereo CA ON A.CodAereo = CA.CodAereo
WHERE A.CodAereo = 1
AND CA.Funzionante = 0; -- TODO: ? per input dall'utente.

-- OP. 3 Voli in partenza
-- ho rimosso CodVolo = 1, non serviva

SELECT volo.CodTratta, CodVolo, Nome, Città_partenza, Città_destinazione, Aeroporto_partenza, Aeroporto_destinazione, Ora_partenza, Ora_fine
FROM aeroporto.volo, aeroporto.tratta 
-- WHERE aeroporto.tratta.CodTratta = aeroporto.volo.CodTratta TODO: remove
WHERE aeroporto.volo.CodTratta = aeroporto.tratta.CodTratta
AND aeroporto.tratta.Aeroporto_partenza = "Malpensa" -- TODO: basta l'ora di partenza senza l'aeroporto?
AND Ora_Partenza = "11:00";

-- TODO: OP.3 e OP.4 sono la stessa cosa, da vedere come fare

-- OP. 4 Voli in arrivo
-- al posto di mostrare Ora_fine

-- ho provato a mettere volo.CodTratta as CodT non ha funzionato nel where
SELECT volo.CodTratta, CodVolo, Nome, Città_partenza, Città_destinazione, Aeroporto_partenza, Aeroporto_destinazione, Ora_partenza, Ora_fine
FROM aeroporto.volo, aeroporto.tratta
-- WHERE aeroporto.tratta.CodTratta = aeroporto.volo.CodTratta TODO: remove
WHERE aeroporto.volo.CodTratta = aeroporto.tratta.CodTratta
AND aeroporto.tratta.Aeroporto_destinazione = "Fiumicino"
AND Ora_fine = "12:12";

-- OP. 5 Manutenzione di un aereo
SELECT CodMantenimento, CodMacchinario, aeroporto.mantenimento.CodAereo
FROM aeroporto.mantenimento, aeroporto.aereo
WHERE aeroporto.mantenimento.CodAereo = aeroporto.aereo.CodAereo;

-- OP. 6 Comunicazione tra membri dell'equipaggio e controllori
-- Non mi serve il COUNT(*) perchè c'ho già l'attributo Num. Aerei
SELECT Num_Aerei_in_comunicazione
FROM aeroporto.torre_di_controllo
WHERE CodTorre = 1; -- TODO: qui ci va ? per indicare input da utente

-- OP. 7 Rifornimento di un aereo
-- Potrei farlo col ground support equipment e cerco tipologia "Refuelers".
-- oppure con Persona ruolo = "GSE / Mantenimento " e 

-- Oppure ancora meglio, Logistica  materiali = "Kerosene" o altri fuels. -> cargo -> aereo

SELECT *
FROM aeroporto.logistica, aeroporto.cargo
WHERE logistica.CodLogistica = cargo.CodLogistica
AND logistica.Materiali = "Kerosene";

-- OP. 8 Assunzione di nuovi addetti
-- Faccio semplicemente un nuova persona, ma con un Ruolo che va inserito.alter

-- TODO: uncomment, ma non testata
-- INSERT INTO aeroporto.persona (CodiceFiscale, Nome, Cognome, Età, Ruolo, Ora_inizio, Ora_fine)
-- VALUES (?, ?, ?, ?, ?);

-- OP. 9 Operazioni di check-in
-- E' come l'operazione 2, o tolgo questa o tolgo l'operazione 2. Forse è meglio togliere
-- l'operazione 2, visto che questa è check-in quindi è un controllo al bagaglio più 
-- dell'altro credo.

-- OP. 10 Aerei in pista ( Via di Rullaggio )
-- Apparte che in una pista ci dovrebbe essere un singolo aereo alla volta.
-- Piuttosto posso usare la Via di Rullaggio
-- SELECT COUNT(aereo.CodAereo) AS Num_Aerei_In_Pista
-- FROM aeroporto.aereo
-- WHERE aereo.CodPista = 1; -- TODO: qui inserire ? per input

-- Non hanno molto senso, piuttosto avrebbe più senso metterle in aereo ? Forse?

-- Insomma anche questa è così così, è da rivedere!
SELECT Num_Aerei
FROM aeroporto.via_di_rullaggio
WHERE CodVia = 1; -- TODO: Inserire ? per l'input.

-- OP. 11 Persone che comprano prodotti ai negozi
SELECT COUNT(P.CodiceFiscale) AS Num_Clienti
FROM aeroporto.negozio N JOIN aeroporto.persona P on P.CodNegozio = N.CodNegozio
WHERE P.CodNegozio = 1; -- TODO: qui sarà ?

-- OP. 12 Persone che si recano al terminal
-- TODO: fixare questo non va bene, posso inserire una sola persona in un terminal!!!
SELECT COUNT(P.CodiceFiscale) AS Persone_Al_Terminal
FROM aeroporto.terminal T JOIN aeroporto.persona P ON T.CodTerminal = P.CodTerminal
WHERE P.CodTerminal = 1; -- TODO: qui inserire ? 

-- OP. 13 Nuovi membri dell'equipaggio assunti da una compagnia aerea
--  Non ha molto senso avere un riferimento a codice fiscale in compagnia aerea perchè
-- può averne uno solo, ma in realtà ce ne sono tanti.
-- TODO: UNCOMMENT, PER0' NON L'HO TESTATA!
-- INSERT INTO aeroporto.persona ( CodiceFiscale, Nome, Cognome, Età, Ruolo, Ora_Inizio, Ora_fine, CodAereo )
-- VALUES ( ?, ?, ?, ?, ?, ? )

-- OP. 14 Inserimento degli aerei stazionati negli hangar
-- TODO: UNCOMMENT, MA NON L'HO TESTATA
-- INSERT INTO aeroporto.aereo ( CodAereo, CodPista, Nome, Num_Equipaggio, Peso, Tipologia, CodHangar)
-- VALUES( ?, ?, ?, ?, ?, ?);

-- OP. 15 Calcolare l'età media dei passeggeri
-- TODO: teoricamente io dovrei fare soltanto la media dei passeggeri e non del resto
-- Mi sembra sbagliato la media
SELECT AVG(Età) AS Media_Età
FROM aeroporto.persona
WHERE Ruolo = "Passeggero";
-- WHERE Ruolo = NULL;

-- OP. 16 Ottenere il num. aerei di una compagnia aerea
SELECT Num_Aerei
FROM aeroporto.compagnia_aerea
WHERE CodCompagnia = 1; -- TODO: ? per input

-- OP. 17 Numero di controllori in una Torre di Controllo
SELECT Num_dipendenti
FROM aeroporto.torre_di_controllo
WHERE CodTorre = 1; -- TODO: ? per input

-- OP. 18 Numero di macchinari presenti nell'aeroporto
-- Qui è già più complicato perchè devo prendere tutti i macchinari di tutte
-- le tipologie, sommarli e ottenere così il numero totale.
SELECT SUM(Quantità) AS Num_Totale_Macchinari
FROM aeroporto.ground_support_equipment;

-- OP. 19 Approvigionamento dell'aereo
-- Questa è praticamente uguale alla op. 7
-- TODO: Nuova operazione , mostrare tutti i controllori che lavorano presso il
-- centro di controllo d'aerea tra le 17:00 22:00 o meglio entro, poi possono lavorare anche
-- di più

-- OP. 19 Mostrare i controllori che erano in servizio dalle 08:00 alle 13:00

SELECT CodiceFiscale, Nome, Cognome, Età, CodTorre, CodCentro, Ora_inizio, Ora_fine
FROM aeroporto.persona
WHERE Ruolo = "Controllore"
AND Ora_inizio >= CAST("8:00" AS TIME)
AND Ora_fine <= CAST("13:00" AS TIME)
GROUP BY Cognome;

-- OP. 20 Numeri aerei commerciali di una compagnia aerea
-- Non si può fare, ho tolto le relazioni tra aereo e compagnia aerea. (ADESSO SI INVECE)
SELECT COUNT(Commerciale) AS Num_Aerei_Commerciali
FROM aeroporto.aereo
WHERE Commerciale = 1;

-- OP. 21 Quantità di merci trasportate in media da un aereo commerciale
SELECT AVG(Num_Merci) AS Media_Merci_Trasportate
FROM aeroporto.aereo;

-- TODO: Possibili operazioni da aggiungere:
-- Controllare un componente di un aereo se è funzionante o no.