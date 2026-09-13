Tasks:

typeModelToSource
functionalModel..
activityModel...


uses sourceModelToSource 
uses createClassFromToSourceModel
uses createUnitTestFromToSourceModel

we have the following cases:
1) implementClass: Prompt on complete class, perhaps use update model, open if this is useful later on? add propmt-> method in yaml wich should be implemented?
2) adt, abstract data type: activity: the model knows wich methods should be implemented and wich are rule bases



implementClass later uses createClassFromToSourceModel
similar to algebraic data types
and also creates tests

createUnitTest later uses createClassFromToSourceModel


We propably have two types of tasks:

one large task synchronize

n small tasks like query or refactor create model...
how does x work


the synchronize task could be modeled similar to maven
e.g. multiple phases
default steps can be overriden and additional tasks can be added to the different
life cycle phases


or do we have basically two or three basic tasks:
info/query
refactor
sychnronize

plus the potential to execute specific tasks standalone similar to maven



extensions von tasks
neue tasks aus bestehenden tasks actionen
bestehende tasks erweitern



