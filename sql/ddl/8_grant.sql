GRANT EXECUTE
ON PROCEDURE wyswietlNieobecnosci
TO Uczen@localhost;

GRANT EXECUTE
ON PROCEDURE sprawdzenieZajecUcznia
TO Uczen@localhost;

GRANT EXECUTE
ON PROCEDURE wyswietlSprawdzianyUcznia
TO Uczen@localhost;

GRANT EXECUTE
ON PROCEDURE wyswietlLekcjeUcznia
TO Uczen@localhost;

GRANT EXECUTE
ON PROCEDURE sprawdzOcenyUcznia
TO Uczen@localhost;

-- granty Rodzic


GRANT EXECUTE
ON PROCEDURE wyswietlNieobecnosci
TO Rodzic@localhost;

GRANT EXECUTE
ON PROCEDURE sprawdzenieZajecUcznia
TO Rodzic@localhost;

GRANT EXECUTE
ON PROCEDURE wyswietlSprawdzianyUcznia
TO Rodzic@localhost;

GRANT EXECUTE
ON PROCEDURE wyswietlLekcjeUcznia
TO Rodzic@localhost;

GRANT EXECUTE
ON PROCEDURE sprawdzOcenyUcznia
TO Rodzic@localhost;

GRANT EXECUTE
ON PROCEDURE Usprawiedliwienie
TO Rodzic@localhost;

-- granty Nauczyciel

GRANT SELECT
ON dziennikszkolny.listaNauczycieli
TO Nauczyciel@localhost;

GRANT SELECT
ON dziennikszkolny.listaklas
TO Nauczyciel@localhost;

GRANT SELECT
ON dziennikszkolny.listauczniow
TO Nauczyciel@localhost;

GRANT UPDATE
ON dziennikszkolny.obecnosci
TO Nauczyciel@localhost;

GRANT DELETE
ON dziennikszkolny.obecnosci
TO Nauczyciel@localhost;

GRANT INSERT
ON dziennikszkolny.Lekcje
TO Nauczyciel@localhost;

GRANT UPDATE
ON dziennikszkolny.Lekcje
TO Nauczyciel@localhost;

GRANT DELETE
ON dziennikszkolny.Lekcje
TO Nauczyciel@localhost;

GRANT UPDATE
ON dziennikszkolny.Sprawdziany
TO Nauczyciel@localhost;

GRANT DELETE
ON dziennikszkolny.Sprawdziany
TO Nauczyciel@localhost;

GRANT UPDATE
ON dziennikszkolny.Oceny
TO Nauczyciel@localhost;

GRANT DELETE
ON dziennikszkolny.Oceny
TO Nauczyciel@localhost;

GRANT EXECUTE
ON PROCEDURE Usprawiedliwienie
TO Nauczyciel@localhost;

GRANT EXECUTE
ON PROCEDURE zapowiadanieSprawdzianu
TO Nauczyciel@localhost;

GRANT EXECUTE
ON PROCEDURE wprowadzOcene
TO Nauczyciel@localhost;

GRANT EXECUTE
ON PROCEDURE wpisywanieObecnosci
TO Nauczyciel@localhost;

GRANT EXECUTE
ON PROCEDURE sprawdzenieZajecNauczyciela
TO Nauczyciel@localhost;

GRANT EXECUTE
ON PROCEDURE sprawdzOcenyNauczyciela
TO Nauczyciel@localhost;

GRANT EXECUTE
ON PROCEDURE wyswietlSprawdzianyNauczyciela
TO Nauczyciel@localhost;

GRANT EXECUTE
ON PROCEDURE wyswietlLekcjeNauczyciela
TO Nauczyciel@localhost;

GRANT EXECUTE
ON PROCEDURE wyswietlNieobecnosci
TO Uczen@localhost;


-- granty Administrator

GRANT EXECUTE
ON PROCEDURE archiwizowanieKlasy
TO Administrator@localhost;

GRANT INSERT
ON dziennikszkolny.osoby
TO Administrator@localhost;

GRANT UPDATE
ON dziennikszkolny.osoby
TO Administrator@localhost;

GRANT DELETE
ON dziennikszkolny.osoby
TO Administrator@localhost;


GRANT INSERT
ON dziennikszkolny.uzytkownicy
TO Administrator@localhost;

GRANT UPDATE
ON dziennikszkolny.uzytkownicy
TO Administrator@localhost;

GRANT DELETE
ON dziennikszkolny.uzytkownicy
TO Administrator@localhost;


GRANT INSERT
ON dziennikszkolny.uczniowie
TO Administrator@localhost;

GRANT UPDATE
ON dziennikszkolny.uczniowie
TO Administrator@localhost;

GRANT DELETE
ON dziennikszkolny.uczniowie
TO Administrator@localhost;


GRANT INSERT
ON dziennikszkolny.nauczyciele
TO Administrator@localhost;

GRANT UPDATE
ON dziennikszkolny.nauczyciele
TO Administrator@localhost;

GRANT DELETE
ON dziennikszkolny.nauczyciele
TO Administrator@localhost;


GRANT INSERT
ON dziennikszkolny.rodzice
TO Administrator@localhost;

GRANT UPDATE
ON dziennikszkolny.rodzice
TO Administrator@localhost;

GRANT DELETE
ON dziennikszkolny.rodzice
TO Administrator@localhost;


GRANT INSERT
ON dziennikszkolny.administratorzy
TO Administrator@localhost;

GRANT UPDATE
ON dziennikszkolny.administratorzy
TO Administrator@localhost;

GRANT DELETE
ON dziennikszkolny.administratorzy
TO Administrator@localhost;

GRANT INSERT
ON dziennikszkolny.danelogowania
TO Administrator@localhost;

GRANT UPDATE
ON dziennikszkolny.danelogowania
TO Administrator@localhost;

GRANT DELETE
ON dziennikszkolny.danelogowania
TO Administrator@localhost;


GRANT INSERT
ON dziennikszkolny.adresy
TO Administrator@localhost;

GRANT UPDATE
ON dziennikszkolny.adresy
TO Administrator@localhost;

GRANT DELETE
ON dziennikszkolny.adresy
TO Administrator@localhost;


GRANT INSERT
ON dziennikszkolny.zajecia
TO Administrator@localhost;

GRANT UPDATE
ON dziennikszkolny.zajecia
TO Administrator@localhost;

GRANT DELETE
ON dziennikszkolny.zajecia
TO Administrator@localhost;


GRANT INSERT
ON dziennikszkolny.przedmioty
TO Administrator@localhost;

GRANT UPDATE
ON dziennikszkolny.przedmioty
TO Administrator@localhost;

GRANT DELETE
ON dziennikszkolny.przedmioty
TO Administrator@localhost;


GRANT INSERT
ON dziennikszkolny.specjalnosci
TO Administrator@localhost;

GRANT UPDATE
ON dziennikszkolny.specjalnosci
TO Administrator@localhost;

GRANT DELETE
ON dziennikszkolny.specjalnosci
TO Administrator@localhost;


GRANT INSERT
ON dziennikszkolny.rozszerzenia
TO Administrator@localhost;

GRANT UPDATE
ON dziennikszkolny.rozszerzenia
TO Administrator@localhost;

GRANT DELETE
ON dziennikszkolny.rozszerzenia
TO Administrator@localhost;


GRANT INSERT
ON dziennikszkolny.klasy
TO Administrator@localhost;

GRANT UPDATE
ON dziennikszkolny.klasy
TO Administrator@localhost;

GRANT DELETE
ON dziennikszkolny.klasy
TO Administrator@localhost;

GRANT SELECT
ON dziennikszkolny.*
TO Administrator@localhost;

GRANT SELECT
ON dziennikszkolny.listanauczycieli
TO Administrator@localhost;

GRANT SELECT
ON dziennikszkolny.listaklas
TO Administrator@localhost;

GRANT SELECT
ON dziennikszkolny.listauczniow
TO Administrator@localhost;
