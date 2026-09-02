Test Types:
    Unit Tests
    Modul/Component Tests
    Integration Tests
    E2E Tests

Unit Tests:
Always at the class/Unit it is used
    NoTest
    LLMMtest: Prompt
    RuleBased: Open

the test model should not specify specific tests
but how to generate multiple tests
for example how to create variations of input data

based on and and or:
based on sets (combinatoric)
names: ...
methods:
combinatoric...

first pattern
given/when/then tests based on testbuilder
assertions and probably mocks


probably easy to formulate checks for rule based:
1) No Exception, pure, complete
2) if value x than result y based on good data structures on the input and output
3) 


Open: How to check the Texts
Types of checks
How to implement the other types:


Unit Tests: One Method/One Class propably multiple Objects

All other additional:
one verb/Method multiple Nouns
one Noun multiple verbs (order of verbs)
Combination of multiple Nouns and verbs


So we have:
UnitTest:
single method
multiple Methods

UnitTest Multiple Classes (where to describe?)
single Methods multiple Classes (classes implementing interfaces)
multiple classes, one scenario



Basically Tests are based on other models
+ Variation
+ Data Creation

so mock x

Drei Fragen:
Was wird getestet
Wie wird getestet
Wo wird getestet

Nach möglichkeit was von den beiden anderen fragen getrennt beantworten
Was: Beispiele
Meta Daten (Propety besd Tests)

offen beispiele im modell oder später im source code?
nehme n beispiele im modell, eigentliche veispiele im Source code (nur meta data)

Refactoringsmöglichkeit der Tests wenn sich die Implementierung ändert!
e.g. klassen verschieben, neue paramete hinukommen...

Test stufen: Unit/Integration...
Scope: Modul/System/einzelne Methode einer Klasse..
e.g. Scope des Tests (wo und wie definieren?)

Vielleicht macht es Sinn die ganz einfachen Unit Test pro Methode jeweils an der Klasse zu spezifizieen
Was -> Testabdeckung sollte einfach erkennbar sein

wenn man meta modell, meta daten hat sollten. beide wege funktionieren
meta -> instanzen
instanz -> meta

offen: brauchen wir mehre modelle für tests
oder nur eine art?

benötigen wir stereotypes?


invariants, übergänge inpit -> output als relations
oder functions

rules um testdaten zu generieren -> variationen
vielleicht aus produktlinien


kombinatiorik: unit test testet x dann muss im modult test dies getestet werden
tests als doku -> für die nutzung von klassen, modulen

vielleicht input und output als relational data modellieren
und normalisieren

wie teststufen, testabhnängigkeiten modellieren?


model of the seperation of the different tests test a test unit from a to b
test b from .. to ...
test aufteilung (scope überlappung)

alle tests haben eine sehr reguläre meta struktur
input + sit erzeugen
methoden aufrufen
ausgabe, state prüfen



