% 1
is_member(A, L) :- member(A, L).

% 2
ao_lado(X, Y, L) :- nextto(X, Y, L) ; nextto(Y,X,L).

% 3
um_entre(X, Y, L) :- nextto(X, A, L), nextto(A, Y, L).