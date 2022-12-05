grammar Json;

@header {
package me.jiayu.json;
}

file: json EOF ;

json:
    arr
    | obj
    | string
    | jsonNumber
    | jsonNull
    ;

jsonNull : 'null';

jsonNumber : NUMBER;

arr : '[' json (',' json)* ']' ;

obj : '{' kvPair (',' kvPair)* '}' ;

kvPair : key ':' value ;

key: string ;

value: json ;

string : '"' STRING '"';

// number must proceed string value
NUMBER: [+-]? [0-9]+ ;

STRING : [a-zA-Z0-9]+ ;

WS : [ \t\r\n]+ -> skip ;
