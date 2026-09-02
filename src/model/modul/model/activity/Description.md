An activity contains of the name
potentially variable bindings and aa list of sub activities/ statements
or a prompt
and a test
documentation can always be availabele
perhaps type llm list? at leas for parser?

each statement is either a
method call
method call with binding
higher order function for example either, for each


We have an ActivityStatement = VariableBinding | BoundMethodCall | UnboundMethodCall


MethodCall = WithoutParameter | complete Parameter | SomeNamedParameter


and than functions

List(Parameter)

later cast


builder für ActivityStatement which implements antlr visitor




