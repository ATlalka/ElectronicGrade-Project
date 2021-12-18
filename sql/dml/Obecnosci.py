import random
for j in range(6):
    result = 'INSERT INTO Obecnosci (`idObecnosc`, `Rodzaj`, `UczniowieidUczen`, `LekcjeidLekcja`, `RodziceidRodzic`, `NauczycieleidNauczyciel`, `tempUczen`, `tempLekcja`) VALUES'
    print(j)
    for i in range(1, 100001):
        if i%10000 ==0:
            print(i)
        result += '(' + str(j*100000+i) + ',"' + str(random.choice(['Z', 'U', 'N'])) + '",' + str(random.randint(1, 12)) + ',' + str(random.randint(1, 41)) + ',' + str(random.randint(1, 12)) + ',' + str(random.randint(1, 6)) + ',' + str(random.randint(1, 4800)) + ',' + str(random.randint(1, 176000)) + '),'

    f = open("Obecnosci" + str(j) +".sql", 'w')
    f.write(result[:-1])
    f.close()