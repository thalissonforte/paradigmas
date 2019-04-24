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

%7
largest1([X],X).
largest1([X|Xs],X) :- largest1(Xs,Y), X>=Y.
largest1([X|Xs],N) :- largest1(Xs,N), N>X.

largest2([X|Xs], N) :- aux(Xs, X, N).
aux([], N, N).
aux([X|Xs], M, N) :-
   M1 is max(X, M),
   aux(Xs, M1, N).