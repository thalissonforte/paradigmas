% 1
sumSquares(X, Y, Z) :- Z is X*X + Y*Y.

% 2
hasEqHeads([Xh|_],[Yh|_]) :- Xh = Yh. 

% 3
addsuper([],[]).
addsuper([H|T], L) :-
    atom_concat("super ", H, H2),
    L = [H2|Resto],
    addsuper(T, Resto).

% 4
countString2([],_,_).
countString2([H|T], X, Cont) :-
    H = ' ',
    X is Cont + 1,
    CNew is Cont + 1,
    countString2(T, X, CNew).
countString2([H|T], X, Cont) :-
    H \= ' ',
    countString2(T, X, Cont).

countString(Lista, X) :-
    countString2(Lista, X, 0).


% 5
calcEq([],[]).
calcEq([H|T], X) :-
    H2 is 3*H*H + (2/H) + 1,
    X = [H2 | T2],
    calcEq(T, T2).

%6
negNums([], []).
negNums([H|T], [H2|T2]) :-
    H < 0,
    H2 = H,
    negNums(T, T2).

negNums([H|T], A) :-
    H >= 0,
    negNums(T, A).

% 7
interNums([], []).
interNums([H|T], [H2|T2]) :-
    H >= 1,
    H =< 100,
    H2 = H,
    interNums(T, T2).

interNums([H|T], A) :-
    (H < 1 ; H > 100),
    interNums(T, A).

% 8
idade([], []).
idade([H|T], [H2|T2]) :-
    I is 2019-H,
    I > 1980,
    H2 = H,
    idade(T, T2).

idade([H|T], A) :-
    I is 2019-H,
    I =< 1980,
    idade(T, A).

% 9
pares([], []).
pares([H|T], [H2|T2]) :-
    I is H mod 2,
    I = 0,
    H2 = H,
    pares(T, T2).

pares([H|T], A) :-
    I is H mod 2,
    I = 1,
    pares(T, A).

% 10
charFound(_,[]).
charFound(C, [H|_]) :- H = C, !.
charFound(C, [H|T]) :- H\=C, charFound(C, T).

% 11
nomes([],[]).
nomes([H|T], [H2|T2]) :-
    H = a,
    H2 = H,
    nomes(T, T2).
nomes([H|T], L) :-
    H \= a,
    nomes(T, L).