# Vaatimusmäärittely
## Algoritmin vaatimusmäärittely
### Käytety algoritmit ja syötteet.
Tekoäly perustuu min-max algoritmiin, jossa on alpha-beta pruning metodit ja jonkin sortin heuristiikka arvioimaan pelin til. 
Min-max algoritmi ei todennäköisesti mene loppuun saakka, sillä ideana olisi, se että pelikenttän koon ja vaaditun määrän O ja X peräkkäin voittoon voi valita,
joten rekursion pohjalle meno todennäköisesti kestää liikaa isommilla kentillä.
Algoritmi voi pelata itseään vastaan, ja vielä mahdollisesti eri heuristiikoilla toimivia versioita vastaan. Käyttäjä voi myös itse pelata konetta vastaan ja syöttää omat liikkeensä.

- Algoritmi kentän koon syötteen perusteella päättää kuinka syvälle min-max algoritmi menee.
- Algoritmin heuristiikka muuttuu kentän kokoon, vaaditun voittavan rivin pituuteen ja pelin sen hetkiseen tilanteen perusteella.
### Aika ja tilavaativuudet
#### Aika vaativuus
- Heuristiikan arvio kestää todennäköisesti aika arvioltaan O(a^k) missä a on kentän pinta-ala. Heuristiikka käy kentän läpi ja suorittaa vakioaikaisia ja mahdollisesti kentän kokoon perustuvia arvioita joista pisin olkoon k. k kestoinen metodi voisi esimerkiksi katsoa mitä merkkejä on sillä hetkellä käydyn ruudun 5 vasemmassa ruudussa, jolloin k olisi 5. Ideaalisesti heuristiikkaan saadaan mahdollisimman pieni k.n arvo.
- Min-Max algoritmin aikavaatiivuus ilman heuristiikkaa on O(n^p) missä n on sallittujen siirtojen määrä pelin alussa ja p on se kuinka syvälle algoritmi menee min-max algoritmissa. n laskee aina yhdellä per siirto. Algoritmi saa paremman tarkkuuden p.n kasvaessa, mutta aikavaativuus kasvaa nopeasti.

Koska min-max algoritmi joutuu arvioimaan aina heuristiikan avulla pelin tilannetta on aikavaativuus O((n(a^k)^p)=O(s^p) missä s on Min-Max algoritmin aikavaativuus heuristiikalla.

Algoritmissä pitää tasapainottaa heuristiikan ja min-max algoritmin syvyyttä, että tekoäly on tarpeeksi järkevä, mutta silti tarpeeksi nopea. Algoritmi ei skaalaannu hyvin suurille pelikentille, kuten esimerkiksi 50x50 kentälle. Ideaalisesti algoritmin min-max syvyys laskee dynaamisesti pelikentän koon mukaan, jotta algoritmi toimisi aina nopeasti vaikka tehoälyn tehokkuus tarkkuus laskisi.

#### Tila vaativuus
- Peli kenttä vaatii muistia pinta-alan a verran jolloin tilavaativuus on O(a) 
- Min-max algoritmi on rekursiivinen, mutta funktion sisällä oleva tilavaativuus on vakio, jolloin O(p), missä p on min-max algoritmin syvyys, on tilavaativuus.
- Heuristiikan tilavaativuus on vakio, sillä ainoa arvo jota pidetään yllä on pelin tilanteen arvio lukua.

Joten tilavaativuus on O(a) tai O(p) riippuen p.n ja a.n arvosta.

## Miksi valitsin aiheen?
Tekoäly ja tässä tapauksessa tarkemmin tekoäly pelissä on mielenkiintoinen aihe. Koen, että aihe olisi minulle hyödyllinen sillä tämä aihe on kiinnostava ja melko matemaattinen. Ongelma on tarpeeksi rajattu, mutta erilaisten kenttien ja vaadittujen voittorivien pituuden muuttamismahdollisuus luo mielenkiintoisia kysymyksiä, siitä miten arvioida pelin tilannetta pitäen algoritmin silti tehokkaana. Kyseessä on tilanne jossa ohjelmalla on täydellinen tieto tilanteesta, mutta rajattu mahdollisuus tutkia ongelmaa, sillä aikavaativuus kasvaa todella nopeasti mitä syvemmellä min-max algoritmi tutkii. Heuristiikan keksiminen eri kentille on myös mielestäni mielenkiintoista.


## Opinto-ohjelma ja käytetty kieli
Matemaattisten tieteiden kandiohjelma. Koulutussuuntana tietojenkäsittelyteoria.

Projektin kieli on englannin kieli, Voin arvioida suomenkielistä koodia. Koodi on tehty javalla. (JDK 11)

## Lähteet
Alustavasti käytän lähteenä:
- https://en.wikipedia.org/wiki/Minimax
- https://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning
