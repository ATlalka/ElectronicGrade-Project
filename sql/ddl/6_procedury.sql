DROP procedure IF EXISTS `wprowadzOcene`;
DELIMITER $$
USE `dziennikszkolny`$$
CREATE PROCEDURE `wprowadzOcene` (ocena integer, waga double, idLekcja integer, idUczen integer, idSprawdzian integer, komentarz varchar(255))
BEGIN
	INSERT INTO Oceny (Wartosc, Waga, UczniowieidUczen, LekcjeidLekcja, SprawdzianyidSprawdzian, Komentarz) values (ocena, waga, idLekcja, idUczen, idSprawdzian, komentarz);
END$$
DELIMITER ;


DROP procedure IF EXISTS `usprawiedliwienie`;
DELIMITER $$
USE `dziennikszkolny`$$
CREATE PROCEDURE `usprawiedliwienie` (idUczen integer, idLekcja integer, idNauczyciel integer, idRodzic integer)
BEGIN
    UPDATE Obecnosci
    SET Rodzaj = 'U',
	RodziceidRodzic = idRodzic,
    NauczycieleidNauczyciel = idNauczyciel
    WHERE UczniowieidUczen = idUczen
    AND LekcjeidLekcja = idLekcja;
    
END$$
DELIMITER ;


DROP procedure IF EXISTS `zapowiadanieSprawdzianu`;
DELIMITER $$
USE `dziennikszkolny`$$
CREATE PROCEDURE `zapowiadanieSprawdzianu` (idLekcja integer, komentarz varchar(255))
BEGIN
		INSERT INTO Sprawdziany (LekcjeidLekcja, Komentarz) values (idLekcja, komentarz);
END$$
DELIMITER ;


DROP procedure IF EXISTS `archiwizowanieKlasy`;
DELIMITER $$
USE `dziennikszkolny`$$
CREATE PROCEDURE `archiwizowanieKlasy` (idKlasa integer)
BEGIN
	UPDATE Klasy
    SET Zarchiwizowana = 1
    WHERE Klasy.idKlasa = idKlasa;
    
    UPDATE Uzytkownicy
    SET Zarchiwizowany = 1
    WHERE idUzytkownik IN
    (SELECT idUzytkownik FROM Uczniowie
    WHERE KlasyidKlasa = idKlasa);
    
    UPDATE Zajecia
    SET Zarchiwizowane = 1
    WHERE KlasyidKlasa = idKlasa;
    
END$$
DELIMITER ;


DROP procedure IF EXISTS `wpisywanieObecnosci`;
DELIMITER $$
USE `dziennikszkolny`$$
CREATE PROCEDURE `wpisywanieObecnosci` (idLekcja integer, idUczen integer, obecnosc char(1))
BEGIN
	INSERT INTO Obecnosci (LekcjeidLekcja, UczniowieidUczen, Rodzaj) VALUES (idLekcja, idUczen, obecnosc);
END$$
DELIMITER ;


DROP procedure IF EXISTS `sprawdzenieZajecNauczyciela`;
DELIMITER $$
USE `dziennikszkolny`$$
CREATE PROCEDURE `sprawdzenieZajecNauczyciela` (idNauczyciela integer)
BEGIN
	SELECT 
    p.Nazwa "Przedmiot", z.dzientygodnia "Dzien tygodnia", z.godzina "Godzina", CONCAT(k.rok,k.symbol) "Klasa"
FROM
    Zajecia z
JOIN
    Przedmioty p ON p.idPrzedmiot = z.PrzedmiotyidPrzedmiot
JOIN
    Nauczyciele n ON n.idUzytkownik = z.NauczycieleidNauczyciel
JOIN
    Uzytkownicy u ON u.idUzytkownik = n.idUzytkownik
JOIN
    Osoby o ON o.idOsoba = u.OsobyidOsoba
JOIN
    Klasy k ON k.idKlasa = z.KlasyidKlasa
WHERE 
	n.idUzytkownik = idNauczyciela;
END$$
DELIMITER ;


DROP procedure IF EXISTS `sprawdzenieZajecUcznia`;
DELIMITER $$
USE `dziennikszkolny`$$
CREATE PROCEDURE `sprawdzenieZajecUcznia` (idUcznia integer)
BEGIN
	SELECT 
    p.Nazwa "Przedmiot", z.dzientygodnia "Dzien tygodnia", z.godzina "Godzina", CONCAT(CONCAT(b.imie, ' '), b.nazwisko) "Nauczyciel" 
FROM
    Zajecia z
JOIN
    Przedmioty p ON p.idPrzedmiot = z.PrzedmiotyidPrzedmiot
JOIN
    Klasy k ON k.idKlasa = z.KlasyidKlasa
JOIN
    Uczniowie c ON c.KlasyidKlasa = k.idKlasa
JOIN
    Uzytkownicy u ON u.idUzytkownik = c.idUzytkownik
JOIN
    Osoby o ON o.idOsoba = u.OsobyidOsoba
JOIN
	Nauczyciele n ON n.idUzytkownik = z.NauczycieleidNauczyciel
JOIN
	Uzytkownicy t ON t.idUzytkownik = n.idUzytkownik
JOIN
	Osoby b ON b.idOsoba = t.OsobyidOsoba
WHERE 
	c.idUzytkownik = idUcznia;
END$$
DELIMITER ;


DROP procedure IF EXISTS `sprawdzOcenyUcznia`;
DELIMITER $$
USE `dziennikszkolny`$$
CREATE PROCEDURE `sprawdzOcenyUcznia` (idUcznia integer)
BEGIN
SELECT 
    e.Wartosc "Ocena", e.Waga "Waga", e.Komentarz "Komentarz", p.Nazwa "Przedmiot", l.data "Data", CONCAT(CONCAT(s.imie, ' '), s.nazwisko) "Nauczyciel" 
FROM
    Oceny e
JOIN
    Lekcje l ON l.idLekcja = e.LekcjeidLekcja
JOIN
    Zajecia z ON z.idZajecia = l.ZajeciaidZajecia
JOIN
    Przedmioty p ON p.idPrzedmiot = z.PrzedmiotyidPrzedmiot
JOIN
    Klasy k ON k.idKlasa = z.KlasyidKlasa
JOIN
    Uczniowie c ON c.KlasyidKlasa = k.idKlasa
JOIN
    Uzytkownicy u ON u.idUzytkownik = c.idUzytkownik
JOIN
    Osoby o ON o.idOsoba = u.OsobyidOsoba
JOIN
	Nauczyciele n ON n.idUzytkownik = z.NauczycieleidNauczyciel
JOIN
    Uzytkownicy y ON y.idUzytkownik = n.idUzytkownik
JOIN
    Osoby s ON s.idOsoba = y.OsobyidOsoba
WHERE 
	c.idUzytkownik = idUcznia
ORDER BY
	p.Nazwa;
END$$
DELIMITER ;

DROP procedure IF EXISTS `sprawdzOcenyNauczyciela`;
DELIMITER $$
USE `dziennikszkolny`$$
CREATE PROCEDURE `sprawdzOcenyNauczyciela` (idNauczyciela integer)
BEGIN
SELECT 
     p.Nazwa "Przedmiot", CONCAT(CONCAT(o.imie, ' '), o.nazwisko) "Uczen", e.Wartosc "Ocena", e.Waga "Waga", l.data "Data", e.Komentarz "Komentarz"
FROM
    Oceny e
JOIN
    Lekcje l ON l.idLekcja = e.LekcjeidLekcja
JOIN
    Zajecia z ON z.idZajecia = l.ZajeciaidZajecia
JOIN
    Przedmioty p ON p.idPrzedmiot = z.PrzedmiotyidPrzedmiot
JOIN
    Klasy k ON k.idKlasa = z.KlasyidKlasa
JOIN
    Uczniowie c ON c.KlasyidKlasa = k.idKlasa
JOIN
    Uzytkownicy u ON u.idUzytkownik = c.idUzytkownik
JOIN
    Osoby o ON o.idOsoba = u.OsobyidOsoba
JOIN
	Nauczyciele n ON n.idUzytkownik = z.NauczycieleidNauczyciel
JOIN
    Uzytkownicy y ON y.idUzytkownik = n.idUzytkownik
JOIN
    Osoby s ON s.idOsoba = y.OsobyidOsoba   
WHERE 
	n.idUzytkownik = idNauczyciela
ORDER BY
	p.Nazwa;
END$$
DELIMITER ;

DROP procedure IF EXISTS `wyswietlSprawdzianyUcznia`;
DELIMITER $$
USE `dziennikszkolny`$$
CREATE PROCEDURE `wyswietlSprawdzianyUcznia` (idUcznia integer)
BEGIN
SELECT  
    p.Nazwa "Przedmiot", l.data "Data", CONCAT(CONCAT(o.imie, ' '), o.nazwisko) "Nauczyciel", s.komentarz "Komentarz"
FROM
    Sprawdziany s
JOIN
	Lekcje l ON l.idLekcja = s.LekcjeidLekcja
JOIN
	Zajecia z ON z.idZajecia = l.ZajeciaidZajecia
JOIN
    Przedmioty p ON p.idPrzedmiot = z.PrzedmiotyidPrzedmiot
JOIN
    Klasy k ON k.idKlasa = z.KlasyidKlasa
JOIN
    Uczniowie c ON c.KlasyidKlasa = k.idKlasa
JOIN
    Uzytkownicy u ON u.idUzytkownik = c.idUzytkownik
JOIN
    Osoby o ON o.idOsoba = u.OsobyidOsoba
JOIN
	Nauczyciele n ON n.idUzytkownik = z.NauczycieleidNauczyciel
JOIN
    Uzytkownicy a ON a.idUzytkownik = n.idUzytkownik
JOIN
    Osoby b ON b.idOsoba = a.OsobyidOsoba
WHERE 
	c.idUzytkownik = idUcznia
ORDER BY
	l.data desc;
END$$
DELIMITER ;

DROP procedure IF EXISTS `wyswietlSprawdzianyNauczyciela`;
DELIMITER $$
USE `dziennikszkolny`$$
CREATE PROCEDURE `wyswietlSprawdzianyNauczyciela` (idNauczyciela integer)
BEGIN
SELECT 
   p.Nazwa "Przedmiot", l.data "Data", CONCAT(k.rok, k.symbol) "Klasa", s.komentarz "Komentarz"
FROM
    Sprawdziany s
JOIN
	Lekcje l ON l.idLekcja = s.LekcjeidLekcja
JOIN
	Zajecia z ON z.idZajecia = l.ZajeciaidZajecia
JOIN
    Przedmioty p ON p.idPrzedmiot = z.PrzedmiotyidPrzedmiot
JOIN
    Klasy k ON k.idKlasa = z.KlasyidKlasa
JOIN
	Nauczyciele n ON n.idUzytkownik = z.NauczycieleidNauczyciel
JOIN
    Uzytkownicy u ON u.idUzytkownik = n.idUzytkownik
JOIN
    Osoby o ON o.idOsoba = u.OsobyidOsoba
WHERE 
	n.idUzytkownik = idNauczyciela
ORDER BY
	l.data desc;
END$$
DELIMITER ;


DROP procedure IF EXISTS `wyswietlNieobecnosci`;
DELIMITER $$
USE `dziennikszkolny`$$
CREATE PROCEDURE `wyswietlNieobecnosci` (idUcznia integer)
BEGIN
SELECT 
    l.data "Data", p.nazwa "Przedmiot", c.rodzaj "Usprawiedliwienie"
FROM
    Obecnosci c
JOIN
    Lekcje l ON l.idLekcja = c.LekcjeidLekcja
JOIN
    Uczniowie d ON d.idUzytkownik = c.UczniowieidUczen
JOIN
    Uzytkownicy u ON u.idUzytkownik = d.idUzytkownik
JOIN
    Osoby o ON o.idOsoba = u.OsobyidOsoba
JOIN
	Zajecia z ON z.idZajecia = l.ZajeciaidZajecia
JOIN
	Przedmioty p ON p.idPrzedmiot = z.PrzedmiotyidPrzedmiot
WHERE 
	d.idUzytkownik = idUcznia
ORDER BY
	l.data desc;
END$$
DELIMITER ;

DROP procedure IF EXISTS `wyswietlLekcjeUcznia`;
DELIMITER $$
USE `dziennikszkolny`$$
CREATE PROCEDURE `wyswietlLekcjeUcznia` (idUcznia integer)
BEGIN
SELECT 
    l.temat "Temat", l.data "Data", p.nazwa "Przedmiot", CONCAT(CONCAT(b.imie, ' '), b.nazwisko) "Nauczyciel"
FROM
    Lekcje l
JOIN
    Zajecia z ON l.ZajeciaidZajecia = z.idZajecia
JOIN
    Przedmioty p ON p.idPrzedmiot = z.PrzedmiotyidPrzedmiot
JOIN
	Klasy k ON z.KlasyidKlasa = k.idKlasa
JOIN
    Uczniowie d ON k.idKlasa = d.KlasyidKlasa
JOIN
    Uzytkownicy u ON u.idUzytkownik = d.idUzytkownik
JOIN
    Osoby o ON o.idOsoba = u.OsobyidOsoba
JOIN
	Nauczyciele n ON n.idUzytkownik = z.NauczycieleidNauczyciel
JOIN
    Uzytkownicy c ON c.idUzytkownik = n.idUzytkownik
JOIN
    Osoby b ON b.idOsoba = c.OsobyidOsoba
WHERE 
	d.idUzytkownik = idUcznia
ORDER BY
	l.data desc;
END$$
DELIMITER ;


DROP procedure IF EXISTS `wyswietlLekcjeNauczyciela`;
DELIMITER $$
USE `dziennikszkolny`$$
CREATE PROCEDURE `wyswietlLekcjeNauczyciela` (idNauczyciela integer)
BEGIN
SELECT 
    CONCAT(k.rok, k.symbol) "Klasa", l.temat "Temat", l.data "Data", p.nazwa "Przedmiot"
FROM
    Lekcje l
JOIN
    Zajecia z ON l.ZajeciaidZajecia = z.idZajecia
JOIN
    Przedmioty p ON p.idPrzedmiot = z.PrzedmiotyidPrzedmiot
Join
	Klasy k ON z.KlasyidKlasa = k.idKlasa
JOIN 
	Nauczyciele n ON n.idUzytkownik = z.NauczycieleidNauczyciel
WHERE 
	n.idUzytkownik = idNauczyciela
ORDER BY
	l.data desc;
END$$