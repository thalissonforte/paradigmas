% 1
is_member(A, L) :- member(A, L).

% 2
ao_lado(X, Y, L) :- nextto(X, Y, L) ; nextto(Y,X,L).

% 3
um_entre(X, Y, L) :- nextto(X, A, L), nextto(A, Y, L).
um_entre(X, Y, L) :- nextto(Y, A, L), nextto(A, X, L).

% 6
positivos1([],[]).
positivos1([H|T],L) :- H > 0, positivos1(T,Resto), L = [H|Resto].
positivos1([H|T],L) :- H =< 0, positivos1(T,L).

positivos2([],[]).
positivos2([H|T],L) :- H > 0, L = [H|Resto], positivos2(T,Resto).
positivos2([H|T],L) :- H =< 0, positivos2(T,L).