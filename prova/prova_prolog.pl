% Defina um predicado recursivo perfs(L1,L2) que receba uma lista L1 de números e 
% produza uma lista L2 com somente os números que forem quadrados perfeitos. 
%Um número é um quadrado perfeito se sua raiz quadrada for um número exato 
% (defina um predicado auxiliar para verificar isso).

verificaRaizExata(H1) :-
    sqrt(H1, A),
    B is floor(A),
    C is B*B,
    H1 = C.

perfs([],[]).
perfs([H1|T1], [H2|T2]) :-
    verificaRaizExata(H1),
    H2 = H1,
    perfs(T1, T2).

perfs([H1|T1], L) :-
    not(verificaRaizExata(H1)),
    perfs(T1, L).
