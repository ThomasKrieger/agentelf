grammar FunctionDeclaration;


functionDeclaration
    : IDENTIFIER LPAREN argumentList? RPAREN COLON IDENTIFIER
      ;


argumentList
    : argument (COMMA argument)*
    ;

argument
   :    IDENTIFIER | (IDENTIFIER IDENTIFIER)
   ;


LPAREN      : '(';
RPAREN      : ')';
COMMA       : ',';
COLON       : ':';

IDENTIFIER
    : [a-zA-Z_] [a-zA-Z_0-9]*
    ;

WS
    : [ \t\r\n]+ -> skip
    ;