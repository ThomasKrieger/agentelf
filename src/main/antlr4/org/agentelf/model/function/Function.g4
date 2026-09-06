grammar Function;

program
    : variableDeclaration EOF
    ;

variableDeclaration
    : VAR IDENTIFIER ASSIGN functionCall
    ;

functionCall
    : IDENTIFIER LPAREN argumentList? RPAREN
    ;

argumentList
    : IDENTIFIER (COMMA IDENTIFIER)*
    ;

VAR         : 'var';
ASSIGN      : '=';
LPAREN      : '(';
RPAREN      : ')';
COMMA       : ',';

IDENTIFIER
    : [a-zA-Z_] [a-zA-Z_0-9]*
    ;

WS
    : [ \t\r\n]+ -> skip
    ;