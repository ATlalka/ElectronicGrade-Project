DELIMITER /
CREATE TRIGGER LimitUczniowInsert
BEFORE INSERT ON Uczniowie
FOR EACH ROW
BEGIN
DECLARE
liczba INTEGER;
SELECT COUNT(*) INTO liczba FROM Uczniowie
WHERE Uczniowie.KlasyidKlasa = NEW.KlasyidKlasa;

IF (liczba>30) THEN
signal sqlstate '45000' set message_text ='Klasa jest pelna';
END IF;
END;/
DELIMITER ;

DELIMITER /
CREATE TRIGGER LimitUczniowUpdate
BEFORE UPDATE ON Uczniowie
FOR EACH ROW
BEGIN
DECLARE
liczba INTEGER;
SELECT COUNT(*) INTO liczba FROM Uczniowie
WHERE Uczniowie.KlasyidKlasa = NEW.KlasyidKlasa;

IF (liczba>30) THEN
signal sqlstate '45000' set message_text ='Klasa jest pelna';
END IF;
END;/
DELIMITER ;



DELIMITER /
CREATE TRIGGER LimitKlas
BEFORE INSERT ON Klasy
FOR EACH ROW
BEGIN
DECLARE
liczba INTEGER;
SET liczba = (SELECT COUNT(*) 
	FROM Klasy
	WHERE Zarchiwizowana = false AND Rocznik = NEW.Rocznik);

IF (liczba = 4) THEN
signal sqlstate '45000' set message_text ='Limit  klas na rocznik zostal osiagniety.';
END IF;
END;/
DELIMITER ;


DELIMITER /
CREATE TRIGGER CzyWychowawcaInsert
BEFORE INSERT ON Klasy
FOR EACH ROW
BEGIN
IF NEW.NauczycieleidNauczyciel IN (SELECT NauczycieleidNauczyciel 
     FROM Klasy) THEN
signal sqlstate '45000' set message_text ='Ten nauczyciel jest juz wychowawca.';
END IF;
END;/
DELIMITER ;


DELIMITER /
CREATE TRIGGER CzyWychowawcaUpdate
BEFORE UPDATE ON Klasy
FOR EACH ROW
BEGIN
IF NEW.NauczycieleidNauczyciel IN (SELECT NauczycieleidNauczyciel 
     FROM Klasy) THEN
signal sqlstate '45000' set message_text ='Ten nauczyciel jest juz wychowawca.';
END IF;
END;/
DELIMITER ;


DELIMITER /
CREATE TRIGGER CzyOcenicInsert
BEFORE INSERT ON Oceny
FOR EACH ROW
BEGIN

IF ((SELECT COUNT(*)
      FROM Obecnosci
      WHERE LekcjeidLekcja = NEW.LekcjeidLekcja AND UczniowieidUczen = NEW.UczniowieidUczen) != 0) THEN
signal sqlstate '45000' set message_text ='Uczen nie moze otrzymac oceny, poniewaz nie byl/jest wtedy obecny.';
END IF;
END;/
DELIMITER ;


DELIMITER /
CREATE TRIGGER CzyOcenicUpdate
BEFORE UPDATE ON Oceny
FOR EACH ROW
BEGIN

IF (NOT((SELECT Rodzaj
      FROM Obecnosci
      WHERE LekcjeidLekcja = NEW.LekcjeidLekcja AND UczniowieidUczen = NEW.UczniowieidUczen) LIKE 'o' )) THEN
signal sqlstate '45000' set message_text ='Uczen nie moze otrzymac oceny, poniewaz nie byl/jest wtedy obecny.';
END IF;
END;/
DELIMITER ;


DELIMITER /
CREATE TRIGGER CzyNowySymbolInsert
BEFORE INSERT ON Klasy
FOR EACH ROW
BEGIN

IF ((SELECT count(*)
      FROM Klasy
      WHERE  Rok = NEW.Rok AND Symbol = NEW.Symbol) > 0 ) THEN
signal sqlstate '45000' set message_text ='Uczen nie moze otrzymac oceny, poniewaz nie byl/jest wtedy obecny.';
END IF;
END;/
DELIMITER ;

