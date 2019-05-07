% 1
isVowel(Char) :- member(Char, ['a','e','i','o','u','A','E','I','O','U']), !.

% 2
addComma([],[]).
addComma([H|T], L) :-
    atom_concat(H,",", H2),
    L = [H2|Resto],
    addComma(T, Resto).

% 3
addHtml([],[]).
addHtml([H|T], L) :-
    atom_concat('<LI>',H, H2),
    atom_concat(H2,'</LI>', H3),
    L = [H3|Resto],
    addHtml(T, Resto).

% 4
semVogais([],[]).
semVogais([H|T], L) :-
    not(isVowel(H)),
    L = [H|Resto],
    semVogais(T, Resto).
semVogais([H|T], L) :-
    isVowel(H),
    semVogais(T, L).

% 5
traco([],[]).
traco([H|T], L) :-
    H \= ' ',
    L = ['-'|Resto],
    traco(T, Resto).
traco([H|T], L) :-
    H = ' ',
    L = [' '|Resto],
    traco(T, Resto).

% 6
firstName([],[]).
firstName(N, X) :- 
    split_string(N, " ", "", D),
    D = [H|_],
    X = H.

% 7
isIntAux([]).
isIntAux([H|T]) :-
    member(H, ['0','1','2','3','4','5','6','7','8','9']),
    isIntAux(T).

isInt(Str):-
    string_chars(Str, NewStr),
    isIntAux(NewStr).

% 8
lastName2([Last],Last).
lastName2([_|T],Last) :-
    lastName2(T, Last).
lastName(N, Last) :- 
    split_string(N, " ", "", D),
    lastName2(D, Last).
    
% 9
userName(Nome, User):-
    string_chars(Nome, NomeDividido),
    NomeDividido = [PrimeiraLetra|_],
    lastName(Nome, Last),
    atom_concat(PrimeiraLetra,Last,User).

% 10
troca('a','4').
troca('e','3').
troca('i','2').
troca('o','1').
troca('u','0').
troca(C,C).

encode([],[]).
encode([H|T], N):-
    troca(H, X),
    N = [X|Resto],
    encode(T, Resto).

encodeName(Nome, N) :-
    string_chars(Nome, NomeDividido),
    encode(NomeDividido, N).