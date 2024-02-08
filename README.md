Labas Tadai,
Sukuriau tau programėlę, kuri pades tau pasiruosti kelionei.
Tau reikes Java 17!
Kaip naudotis programele:
Yra dvi opcijos:
1. Atsisiųskite programelę iš github repozitorijos ir paleiskite ja.
2. Atsisiųskite programelę iš github repozitorijos ir jeigu turi docker gali sukurti docker konteineri ir paleisti ji.
3. Atsisiųskite programelę iš github repozitorijos ir jeigu tavo sistema yra mac ar linux, o galbut naudoti bash-terminal, gali paleisti start.sh. P.S nepamirsk `chmod +x start.sh`

Dokumentacija rasi http://localhost:8080/swagger-ui/index.html kai paleisi programele.
Pagrindinis endpointas yra http://localhost:8080/api/v1/backpack/{km} kur turesi ivesti kilometru skaiciu.
Jei kilometrai yra nepilnas skaicius, pvz 5.25, tai prasau ivesti 5, o ne 5.25.
Tau kuprine sugeneruos kiek ir kokius daiktus pasiimti i kelione pagal metu laika ir kilometru skaiciu.
Si programele dar labai pradineje stadijoje.
Bet tu jau gali paziureti ka turi H2 duombaze, is daiktu ir maisto!
Tavo sugeneruota kuprine bus issaugota joje taipogi.

As pazadu dirbsiu toliau prie programeles ir patobulinsiu ja, apacioje prisegu darbus kuriuos noreciau atlikti ateity.
Todo:
1. Prideti pasirinkima metu laiko, jeigu kelione planuoji ateity.
2. Isplesti daugiau endpointu kurie leistu prideti daikta/maista, ar susirinkti kuprine manualiai.
3. Istrinti nereikiamus daigtus is H2, ar pakeisti ju specifikacijas.
4. Prideti user kontrole, kuri leistu atsiminti, kam priklauso kuprines.
5. Sujungti programele su kita programele kuri suteiktu daugybe maisto ir daiktu pasirinkimu, kurie leistu sugeneruoti daug profesionalesnes kuprines.
6. Pagerinti skaiciavimus, kad jie atitiktu realius matmenis.


Tikiuosi viskas bus aisku, o jei kils klausimu gali kreiptis, butinai padesiu!
