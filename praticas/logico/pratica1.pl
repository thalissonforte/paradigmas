% Exemplo de programa em Prolog que define
% fatos e regras sobre pessoas e localizacoes geograficas.

localizado_em(santa_maria, rs).
localizado_em(salvador, bahia).
localizado_em(rs, brasil).
localizado_em(bahia, brasil).
localizado_em(paris, franca).
localizado_em(franca, europa).

nasceu_em(andre, santa_maria).
nasceu_em(joao, salvador).
nasceu_em(X, Y) :- localizado_em(Z, Y), nasceu_em(X, Z).

mora_em(andre, paris).
mora_em(joao, salvador).
mora_em(X, Y) :- localizado_em(Z, Y), mora_em(X, Z).

idade(andre, 25).
idade(joao, 32).

gaucho(X) :- nasceu_em(X, rs).
brasileiro(X) :- nasceu_em(X, brasil).
europeu(X) :- nasceu_em(X, europa).
 
%2
nasceu_em(joana, salvador).
idade(joana, 22).
nasceu_em(michel, paris).
idade(michel, 40).

% 3
% brasileiro(jose).
% europeu(X).
% idade(X,Y), Y>30.
% brasileiro(X), idade(X, Y), Y<30.

% 4
maisVelho(X, Y) :- idade(X, A), idade(Y, B), A>B.

% 5
soma(A,B,C) :- C is A + B. 
pred(A,B,C) :- X is (A+B)^2, C is X*2+1.
% soma(8,5,S).
% pred(3,2,X).

% 6
anoNasc(X,A) :- idade(X, I), A is 2019-I.

% 7
% member(a, [a,b,c]).
% member(x, [a,b,c]).
% member(A, [a,b,c]).
% member(a, [a,b,c,a]).
% length([a,b,c], L).
% length([], X).
% length(a, X).
% length([a,b,c], 2).
% nextto(1, 2, [1,2,3]).
% nextto(2, Y, [1,2,3]).
% nextto(4, X, [1,2,3]).
% nextto(1, 2, [1,2,3,1,2]).

% 8
isVowel(X) :- member(X, [a,e,i,o,u]).
