# Viikon 1 viikkoraportti
## Mitä on tehty
Aloitin työn keskiviikkona. Sain idean luotoa ja sen avulla vaativuusmäärittelyn luotua. Ohjelman teknologian eli eri pluginien ja javan kanssa pientä säätöä. Alustavasti tehnyt
ristinollan pohjaa. 
## Epäselvyyksiä ja ongelmia
Napin tekstin muuttuminen klikkauksella javafx hiukan hankala. Mietin pitäisikö käyttää eri gui teknologiaa? Mielestäni ohjelma on tarpeeksi laaja, mutta ei liian laaja, mutta 
siitäkin on vielä hiukan epävarmuutta.
## Mitä seuraavaksi
seuraavaksi viimeistelen ristinollan logiikan ja pyrin aloittamaan min-max algoritmin luontia. Yksikkötestausta ritinollan logiikkaa ja min-maxia varten.
## tuntimäärä 
Kaiken kaikkiaan 8 tuntia + 1 tunnin luento. Ensi viikolla pyrin tekemään enemmän työtä, kun aloitan tekemisen jo ennen keskiviikkoa.

# Viikon 2 viikkoraportti
Tehty Min Maxin keskineräine pohja joka pystyy nyt pelaamaan ristinollaa pienillä kentillä. GameStatukselle luotu oma arraylist luokka ja kokonaisluvuille vertailu metodi. Yksikkö testaus on melko kunnossa kaikkialla paitsi MinMaxAI luokassa ja käyttöliittymissä. Alustavasti käyttöliittymä pysyy tekstipohjasena.
## Epäselvyyksiä ja ongelmia
Kaikki tällä hetkellä on selvää, saatu halutut asiat valmiiksi.
## Mitä seuraavaksi
Mahdollisia käyttöliittymän parannuksia, mahdollisuus tekoälyn pelata itseään vastaan, heuristiikan kehitystä ja min max algoritmin parannusta.
## tuntimäärä 
Kaiken kaikkiaan 20 tuntia sekoilujen kanssa.

# Viikon 3 viikkoraportti
Kone pystyy nyt pelaamaan itseään vastaan, heuristiikka alulla, testit edennyt, heuristiikan testaus vielä kesken koska heuristiikka on hyvin kesken. Käyttöliittymä on vähän ymmärrettävämpi.
## Mitä seuraavaksi
Heuristiikan kehitystä pää osin. Ehkä Graafisen liittymän alkua.
## tuntimäärä 
Kaiken kaikkiaan 15 tuntia.

# Viikon 4 viikkoraportti
Heuristiikkaa kehitetty, hashmap luotu ja integroitu heuristiikan laskentaan (tallensi eri kenttien heuristiikka arvot, hidastaa laskentaa, pyrin keksimään tavan joka parantais suorituskykyä), testausta lisätty, aikatestestaus aloitettu. Suuria bugifixejä minmaxissa, metodi ei valinnut parasta kenttää kunnolla nyt pitäisi sujua paremmin. Syvyys valinta alustavasti toiminnassa. Testaus documentin teko.
## Mitä seuraavaksi
Heuristiikan mahdollista kehitystä, syvyysvalintaa, hashmapin hyödyntämisen parantamista, aikatestausta lisää. Mahdollisesti graafista liittymää.
## tuntimäärä 
Kaiken kaikkiaan 24 tuntia.

# Viikon 5 viikkoraportti
Testausta edistetty, Kuinka syvälle lasketaan nousee kun kenttä on tyhjempi. Toteutettu siten, että laskenta ei kasva liikaa ja alku syvyyttä ei tarvi laskea. Parempi pitää alussa ja koko ajan mahdollisimman korkea syvyys kun taas pistää se heti alussa liian pieneksi jolloin voi tulla nopea häviö. GUI alustavasti rakennettu, teksti UI parannuksia, pieniä parannuksia siellä täällä. Dokumentaatio kehitystä.
## Mitä seuraavaksi
Heuristiikan kehitys, hashmapin hyödyntämisen parantamista.
## tuntimäärä 
Kaiken kaikkiaan 16 tuntia.
# Viikon 6 viikkoraportti
Testausta edistetty. Tehty erilaisia testejä kuten testi joka satunnaistaa heuristiikan joitakin arvoja ja testaa mikä on paras, pieni ongelma tässä on se, että aloittavalla pelaajalla on aina etu joten täysin tasainen peli on mahdoton. Heuristiikkaa kehitetty. Paljon aikaa kulunu erilaisten arvojen vertailuun ja testailuun. Manuaalista että kone vastaan kone testailua. Tällä hetkellä olen melko tyytyväinen heuristiikan laadusta.
## Mitä seuraavaksi
Asioiden viilaamista, mahdollisesti yritän hashmappia hyödyntää vaikka vaikuttaa siltä että on turha. Testauksen laajentamista.
