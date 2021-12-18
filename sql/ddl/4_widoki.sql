DROP VIEW if exists ListaNauczycieli;
CREATE VIEW ListaNauczycieli
AS 
SELECT 
    DISTINCT CONCAT(CONCAT(o.imie, ' '), o.nazwisko) "Nauczyciel", p.Nazwa "Przedmiot", 
    (SELECT COUNT(idZajecia) FROM Zajecia z WHERE z.NauczycieleidNauczyciel = n.idNauczyciel AND z.PrzedmiotyidPrzedmiot = p.idPrzedmiot) "Liczba godzin w tygodniu", o.Plec "Plec"
FROM
    Zajecia z
JOIN
    Przedmioty p ON p.idPrzedmiot = z.PrzedmiotyidPrzedmiot
JOIN
    Nauczyciele n ON n.idNauczyciel = z.NauczycieleidNauczyciel
JOIN
    Uzytkownicy u ON u.idUzytkownik = n.UzytkownicyidUzytkownik
JOIN
    Osoby o ON o.idOsoba = u.OsobyidOsoba;
    
DROP VIEW if exists ListaKlas;
CREATE VIEW ListaKlas
AS 
SELECT 
    CONCAT(k.rok, k.symbol) "Klasa", k.rocznik "Rocznik"
FROM
	Klasy k;
    
DROP VIEW if exists ListaUczniow;
CREATE VIEW ListaUczniow
AS 
SELECT 
    CONCAT(CONCAT(o.imie, ' '), o.nazwisko) "Uczen", CONCAT(k.rok, k.symbol) "Klasa", k.rocznik "Rocznik"
FROM
	Klasy k
JOIN
    Uczniowie d ON k.idKlasa = d.KlasyidKlasa
JOIN
    Uzytkownicy u ON u.idUzytkownik = d.UzytkownicyidUzytkownik
JOIN
    Osoby o ON o.idOsoba = u.OsobyidOsoba;