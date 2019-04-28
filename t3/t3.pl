%1
odd(N) :- A is mod(N,2), A = 1.

%2
hasN([],0).
hasN(L,N) :-
  L = [_|X],
  hasN(X,M),
  N is 1+M.

%3
inc99([],[]).
inc99(L1,L2) :-
  L1 = [A|X],
  L2 = [B|Y],
  B is A+99,
  inc99(X,Y).

%4
incN([],[],_).
incN(L1,L2,N) :-
  L1 = [A|X],
  L2 = [B|Y],
  B is A+N,
  incN(X,Y,N).

%5
comment([],[]).
comment(L1,L2) :-
  L1 = [A|X],
  L2 = [B|Y],
  string_concat("%%", A, B),
  comment(X,Y).

%6
onlyEven([],[]).
onlyEven(L1,L2) :-
  L1 = [A|X],
  0 is mod(A,2),
  L2 = [B|Y],
  B is A,
  onlyEven(X,Y), !.

onlyEven(L1,L2) :-
  L1 = [H|T],
  onlyEven(T,L2).

%7
countdown(0,[]).
countdown(N,L) :-
  L = [A|Y],
  A is N,
  B is N-1,
  countdown(B,Y), !.

%8
nRandoms(0,[]).
nRandoms(N,L) :-
  L = [H|T],
  random(1,100,H),
  B is N-1,
  nRandoms(B,T), !.

%9
potN0(-1,[]).
potN0(N,L) :-
  L = [A|Y],
  A is 2^N,
  B is N-1,
  potN0(B,Y), !.

%10
zipmult([],[],[]).
zipmult(L1,L2,L3) :-
  L1 = [H1|T1],
  L2 = [H2|T2],
  L3 = [H3|T3],
  H3 is H1*H2,
  zipmult(T1,T2,T3), !.

%11
potencias2(N,N,[]).
potencias2(A, N, L) :-  
  H is 2^A,
  L = [H|T],
  A2 is A+1,
  A<N,
  potencias2(A2, N, T), !.

potencias(N,L) :-
  A is 0,
  potencias2(A, N, L).

%12
cedulas(_, [], []).
cedulas(V,L1,L2) :-
  L1 = [H1|T1],
  L2 = [H2|T2],
  H2 is V//H1,
  A is mod(V, H1),
  cedulas(A, T1, T2), !.