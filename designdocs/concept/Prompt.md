Design Space
Größe
    Einzelne Tasks: z.B. für vmlens 
        new, method...
        einzelne statementns
    Methode
    Class
    
Context
    Explicit mitgeben
    Berechnen anhand Sourcen
    LLM Fragen
    Vorherige fragen

Filtern
    Explicit mitgeben
    LLM Fragen
    Pattern Based
    abhängig von code struktur

Completly free task unknown which classes need to be changed, created
vs clear what should be created
vs information tasks, query tasks

techniken:
prüfen z.B. Architecture Checks
vs. vorgeben, sicherstellen

support for alternatives
try alternative a
alterative b

problem cases:
falsche version
flasche apis
z.B. bei playwright java script calls


task classification
welche techniken müssen hierfür verwendet werden?

orthogonalität von prompt und modell klären




What makes a prompt good
what mathematical models exists?
probmlem propabyl correct definition of the solutions space?
not to small but also not to large
perhaps backtracking, trying multiple prompts 
perhaps correcting?

wie messen?
wie fetstellen ob prompt techniken gut funktionieren
-> wahrscheinlich auch abhängig von modell/zeitpunkt des modells
wie am brsten modellieren
welche einfluss faktoren?

Support for
@super
@methodCallLabel
@method(a,b,c)
@a...b
@varaibleName
@fieldName

we could include labels outside of the prompt for method call like:
label = m(a,b,c)
to be used inside the prompt


Prompt Calculation:
Cases:
New:
Modell
Java Source Mit comment
Java Source Mit comment mit template
Test Case für Class
Test Case für Modell
Test Case mit template

Change:

Bis welche stufe
filtern/infos weglassen
nicht benötigte klassen

abgeleitete klassen
benutzte klassen
used klassen
wann stoppen?
filtern
templates
context abhängigkeit
vielleicht n alternativen
was ist einfach,was schwierig?
was ist klar zu erkennen, was ist uneindeutig, benötigt nacharbeit
welche techniken stehen zur verfügung
wie context erkennen?
wie context beschreiben

2 Teile 
filtern/zur Verfügung stellen der daten die spezifiziert wurden
aufbereiten
herausfinden was zur verfügung gestellt werden sol
benötigt vielleicht n alternativen/aufteilen
storage der entscheidungen?

context definieren
einmal was macht den context aus
und welcher context wird gewählt
wie hoch ist die variablitiät?
wie viel contexte gibt es 
wie groß ist jeweils der context?
context wovon abhängig
zeitlich? 
caching


