grammar FunctionDeclaration;


functionDeclaration
    : type IDENTIFIER LPAREN argumentList? RPAREN
    ;


argumentList
    : argument (COMMA argument)*
    ;

argument
   :    type | ( type IDENTIFIER)
   ;

type
  : package? IDENTIFIER generic? array?
  ;

package
  : (IDENTIFIER POINT)+
  ;

generic
   : LANGLE type+ RANGLE
   ;

array
    : LBRACK RBRACK
    ;


LANGLE      : '<';
RANGLE      : '>';
LPAREN      : '(';
RPAREN      : ')';
LBRACK      : '[';
RBRACK      : ']';
COMMA       : ',';
COLON       : ':';
POINT       : '.';

IDENTIFIER
    : [a-zA-Z_] [a-zA-Z_0-9]*
    ;

WS
    : [ \t\r\n]+ -> skip
    ;