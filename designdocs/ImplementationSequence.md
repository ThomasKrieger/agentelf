model processing:
   project init -> caches/generated loaded all model files
   model To Source. Potential folder
   later we have synchronize
   propably different phases similar to maven?
   open if we need to exclude specific models?
   type repo
   reference type repo (insted current SourceModel variable)
   open: variable or field? 


activity diagramm alle benötigten klassen zusammen suchen
Context zusammenbauen + inlining
sources: Model, Source, Jars, JavaDoc, external files
user stories?
später Jira...
start at prompt builder



activity diagramm update mechanism


activity diagramm init
     cache update


variations at functions/activities:
at types we have either algebaric or a implements b
We need to specify what is the sane and what changes
so two types for activites:
eith extensionpoints (similar to abstract methods)
with variations (or + and for stuff whicxh is the same for all)
similar to algebaric data types
the problem is we can not remove details 
   only perhaps the order between the elements?
   and variable resolution
       if/foreach than not possible
       or simply an unorderd collections of activisties
       so in this case we are interested in what is the same what is different
       do we have all diffences and all steps but not the order of steps
       so
       variations:
       processes: (similar) display like algebraic
          and + or
       perhaps more algebraic operations (union/differnce) set based?
       perhaps order can also be defined <

activity:
    call
    call
    variation{
        case 
        case 
    }
    call
    call





strategien sammeln/lösungen/pattern


add comment (in doku but not for llm)
für variations... open issues
später draus aus higher level model extra potential iomplementations
implementation alternatives


inititialize project

support for final fields
what about constructors?
and all args annotation from lombok?

struktur:
    module 
        types
        activity
    system activities?
    cross cutting?
      or
    modul 
       api
          service activities?
    layer structure?

how would it look like if we have a feature seperation

features as modeling construct?

next: 
     tasks -> simple list of actions
     actions as activities/and or types
 
find and add existing and classes from model


refactoring typen aufzählen

modeling of meta prozesses
   for example all models go from a to x and have the following structure

models for architecture/layers/components
    look at c4?

MethodHanlde: when equals, what infos needed
    when to infer the types -> TypeHandle conversion?
    use TypeHanldle? 
    how to inject type mapping?
TypeRepo
MethodRepo
   -> thransforms a java parsed method to an method handle

model of spring beans -> modules, d.i. replaceable


model for how used, where used (which context)


support for completly generated classes without llm calls

activity 
   binding to existing class or genrate new one if it does not exist
   parser for activity, antlr?
      var x... can also be done by javaparser
      how to define binding?
      do i need a.method call also?
      visitor + builder?
      ast for this language?
      how to define higher order functions
           simply function calls but lookup in special map
           if found we can use it if the other parts are labels
           perhaps use upper case?
           #if
           as macros
      antlr for other cases (only call.. without binding...)
   variable resolution
   type resolution
   add parameter to method
         for generation?
   create method call
       also later class builder
       method builder
       probably based on javapoet
       sourcebuilder 
   algorithm for activity -> source model
    imports in models
type
    inheritance
    add annotation
    add documentation
    PromptBuilder
model -> diagram (mermaid or plantuml)
test/test types

Use the smallest abstraction that provides the required cross-cutting behavior while keeping dependencies and control flow understandable.
perhaps meaning refactoring -> change...
something like add
remove
replace...


perhaps cahce using lucene:
updateDocuments(Term delTerm, Iterable<? extends Iterable<? extends IndexableField>> docs)
Atomically deletes documents matching the provided delTerm and adds a block of documents with sequentially assigned document IDs, such that an external reader will see all or none of the documents.

support for incremental updates?
define queries, which methods needed?

extension through scripts/kotlin:
new actions like git commit
replace maven through gradle
new sterotypes/pattern
new data formats
mutliple different types
new data sources (jira..)
create jira ticket

Modern software engineering generally moved toward:
Make dependencies explicit.
AOP often moves in the opposite direction:
Make dependencies implicit so the primary code stays clean.
That trade-off became less attractive as systems became larger.

Report generation

two dimensional relations
for example component/used in scenario x

show for scenario x what is used which classes are used?

new parameter -> requires change of test and all calling
methods (not so easy)

perhaps default values
moving fields easier (e.g. context)
binding easier


check if possible useful:
   move change model x -> leads to refactoring of classes


The biggest conceptual problem: AOP optimizes for locality of code, not locality of reasoning


variable resolution
type resolution

when to use algebaric data types when absrtract data types


method handle 
field handle
    used for refactoring
    not used for method resolution


für tests klassen typen und arten der tests aufschreiben + wie tests beschreinben
bezug zu bestehnden modellen -> activity diagram type diagram
sprezifische testarten je nach modell!

vielleicht auch noch modell/test modell für abstract data types?
invariants
pre post conditions 
vielleicht als beschreibung


visitor as basis/typical operation
similar to builder for collecting
and factory for building?


process folder of model files
if we want to process activity files we need also process types
we can start with one service class bind everything to one class


builder/dsl for types/activity to allow llm and rule based genration
of models from use cases

templates/meta data
design pattern as constraints/templates?

open/to be defined:
    extra test model?
    scenario model auf ebene use case model
    problem frame model?
    test
    update
    refactoring (where, how to translate from model to source?)
    especially test refactoring
    test independent on x change operation
    variation = change?
    scenarios, abstract scenarios (use cases?)
    resolution/checks by datalog/tree based?
    incoporation of datalog/flix?


Refactoring/Dokumentation
some models are independent os specific questions for example
where
so we can translate the documentation here
propably refactorinmg must
allow for seperating/moving/adding and removing parameters
poropably transformation in higher levevel model and change here
probably seperatijg (for erxample large methods in activity diagram)
and then refactoring based on activity diagram
jeweils indepence des models klären und dann
darauf basierend refactoring
probably seperation of methods
and combining methods
inlining methods


documentation of higher level classes needs to be translated
to lower level models


for useage/source model
going to scenarios/use cases (+ domain model description)
allows us to specify new use cases new types of usages based on scenarios
use cases...
propbable not for all for example sql stuff like this need special model support!




über modelToSource:
Action updateGeneratedSource
    annotation anwenden
    doku anwenden
GeneratedType -> setDocumentaion
                 setAnnotation



first type resolution for method calls
second type checking

import

model -> diagram

support for scripting/kotlin
   for example transform all files cucumber


variable resolution
type resolution



prompt extra package
graph or relation package
      key 
      link type (inheritance...)
      how to build
file reader?
    encapsulate file sytem access
detect type?
     basically a detecting file, yaml and also per types potential actions
     xml, json, yaml...


diagram -> model?
wie am besten umsetzen?
   direkt aus model`
   extra diagram file?
   extra diagram  elemen in modell
   eher diagramm aus modell generieren
   ggf. mit update mechanismen für ui infos?
   manuellem änderungen im diagramm
 

diagram
   plantuml
   mermaid
   graohviz...
   layout algo?


open
   source model for all?
   or only for model -> source
   e.g. also for source -> model and bytecode?

link


external data (jira tickets, confluence) -> user stories
  lässt sich wahrscheinlich über import und links lösen

performance/caching?
wahrscheinlich muss mand dependencies cachen 
die eigentlichen modelle/modell transformationen sind ja eher klein
jeweils kleine actionen



GeneratedType
ExistingType
interfaces
method calculation from interfaces + override
where prompt class

test prompt in config

logging
what to log?
> is this crosscutting?
> action, variable?
> outside of action?

e/r modell?
für deka bank?
relation/functions also useful for e/r model
relations in functional programming?

init command (erstmal replace)

perhaps an intermediate diagram model?

Module.


tdd/test modelle

schwierig alles was kein Java ist?
bzw was ist schwierig zu formultiern
warum?


variationen: als teil vomn types finctions?
extra mode.l (wie mit anderen modellen zusammenpspielen)
cross cutting/ darstellungsproblem?
jede variation benötigt standard funktioanlität:
varaition: a | b | c
beihnatlet x extension points
extension points andere modelle (bezug name zu anderen modellen)
variationen dann sammlung aus n extension points wo es zu unterschiedlichem verhalten führt
offen inwieweit regelbasiert -> hnzufügen extension point?
weitere variation?
tests basierend auf variationen
so variation similar to module -> grouping of other classes, functions...


action preprocess 
search additional files
action postprocesslass
generated class (String)

clarify
TypeDescriptionIntermediate

add annotation (for action)
add documentation

design pattern als rules

java doc template
+ used by
+ used in

do i need ReferenceTypeDescriptionIntermediate

propbly remove unnecessary tests

next:
javaparser
    get -> methoden liste
           daten für hash code
    set
    check
    add annotation
    add/set java doc

Generated Class
    Method Handle
    Field Handle?
    clear annotations
    add documentation
    reset compltly generated methods


method call creation
     and method creation
     example visitor
     builder...
     example call sequence
     example data flow 

search
implements
prompt
    @ -> map aus commands when function call abc(param)
    @ direct (super.. param) 
    or call()
    or xyz()
    perhaps @{program code}
    parser (regexp)
update
    link
        replace with existing
    tests komliziert -> neu hinzukommende tests
    offen ob erstmal pro class nicht pro methode?
    keep test? -> Refactoring?
    what methods is a test calling? especially unit test?


create model?

end to end test?


run

functional

test für prompt...



Refactoring -> Does doku still fits?
nicht versuchen zu smart zu sein!
eher über refactoring


N small model types:
types -> entprechend uml class diagramm -> record,...
sequence diagram -> propably mostly rule based?
functional 
state diagram
open: variations?
productline views?
test (end to end?)
modules
event based? use case maps?
ddd design techniken?
abstraction
dependency matrix
entity relation ship?
methods also as relation? later
specialiced dsls (actions...)
LLm Req -> Model


first existing models:
uml class
sequence
state
functional 
use case? -> functional decomposition + ddd techniques








open: one large model
or n different
n different nearer to uml
sequence/state..
easier to develop
especially state
open functional sepreation?
functional part

how to try model?

+ seqquence -> high level
    mostly rule based
    template for visitor
parameter widening


+ functional (adt, very type based, functions)
gadt?

foreach? functional? (maps...)
ähnlich types nicht direkt uml
@für model typ?

+ functional/


prompt generation and context building
    at
    call...
    super
       -> context building
    logging..
    includes
    uses
    extra prompt?


method resolution
    double dispatch
    visitor pattern 

prompt parer probably best regexp based


difference between service and normal mutable class


UML -> Intermediate oder erst data and functions?
parser for data would be cool
danach alles über X
    use parse method
sequence diagram?
   function list
   wie calls darstellen?

Ids, Streetname...
Types based on only one field based on stereotypes
stereotype definition?

You could call these:
Single-value object
Single-value Value Object
Atomic Value Object
Scalar Value Object
I would probably use Single-Value Value Object if you want the distinction to be explicit.
2. Composite Value Object

One important distinction: Money is often considered a value object even though it might only have two fields (amount, currency). The distinction is therefore not really about the number of fields, but about whether the object's state is atomic or composite from the domain's perspective.
If you're designing a Java type hierarchy or DSL, I'd use Scalar/Atomic Domain Type vs. Composite Domain Type if the distinction is important to your model.


Data:
    Service, Strategy?
    Service and Strategy as extensdion
    without state?

task data to source:
    model file
    functionalModel ->  sourceModel
    call intermediateToSource

three actions:
    load model
    functional to sourceModel
    call intermediateToSource

später 
   modeltosource






Javadoc klären
perhaps easier to use extra tags and than replace through java doc
+ parsing of java doc 

suche klären
struktur in ordner übernehmen

logging bei hand

Test for Parameters in ParseMethod

clear and test llm config

    
Data, funcition -> Intermediate
define Java Parser as data, function
Action -> Intermediate

logik intermediate:
intermediate model calculate all methods
Inheritance tree
prompt inheritance prompt resolution
-> by intermediate model
type resolution
-> by intermediate model
    

stereotype for models?:
Model:
   classes
   yaml parser
   model -> intermediate


StopAt X
Restart X
for example first create class
than test 
requires to save the created classes 
and than reload

similar intermediate files
define where it makes sense to stop 
requires a save and reload
for example which classes to include
or the created intermediate model
and support for multiple variations 
    workspace a contains variation one
    workspace b contains variation two
stuff like inline static methods
delombok yes/no

makes sense to allow the creation, tools used for intermediate as control 
in the higher model/extra model
so it is not neccesary to save the intermediate model?
of course forvexpreimenting?
but propably easier to create the class perhand if really impossibkle?
or extravtool loike implementClass for special, very complicated stuff?

so:
model (serialized..)
can be generated as multiple alternatives using llm
contains all control directives to create intermediate
allows oveerrides through directives?
for example order?
includes?
perhaps create prompt as intermediate step?

intermediate -> soure
than save and create test

saving intermediate is propably mire useful for debugging
nit so much real usage?



implement assertion in TypeToTextTest

test only for model (reload class)
check

templates from .agentelf template folder

tests on method basis?


module interfaces
module definition


explain, create doku (uml and html or yml)
find usage
 where is x called for example logger.warn
which questions to answer?


extend class
logging

type resolution
   primitive types
   java.lang... (known types from github)
      open when to include, blacklist based
   newly generated

end to end documentation


modellierung anhand function modell
usage from sequence diagram?
test modell ausarbeiten
variationen?

based on intermediate
action -> intermediate
javaparser
search, include
path, correct dir
uml diagram -> uml description
uml -> Intermediate
extends, implements, uses
based on graph sorting

-> resolve prompts
-> super in prompts?
resolve @ in prompt
pattern support?


data -> Intermediate
function call resolution
based on graph

unit tests for actions


next: Usage Model, Change Model, Update mechanism, modules, model for e2e test, e2e tests for task
update mechanism


build, retry?
other llm calls?


example based prompts?


Support for utility functions: use for:...
always in context?
extended search?


refactoring suggestions
+ tidy

prompt as part of history/search


variations:
    how to create multiple variations/models?
    from textual description?
    how to try?


stereotypes
@ resolution

LLM -> setMethod
setAnnotation
setDocumentation

checkFields
checkMethods

später: 
    allgemein prüfungen später
    import berechnung
    prüfung felder
    add and checks import

modell für scenarios?
Integratoon Tests...
Examples...

perhaps possibility for complex/multple combined refactorings


use case 
problem frames models
why should a technology model suddenly work because of llm?
the most specific model possible 
not ontology but rather relational model
not always use a graph
not necesseraly an datalog database if sql is enough






