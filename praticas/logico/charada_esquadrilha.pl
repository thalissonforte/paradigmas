% Regras para determinar se X está ao lado de Y
ao_lado(X, Y, List) :- nextto(X, Y, List). % X à esquerda de Y
ao_lado(X, Y, List) :- nextto(Y, X, List). % Y à esquerda de X

% Regras para determinar se existe 1 elemento entre X e Y
um_entre(X, Y, L) :- nextto(X, A, L), nextto(A, Y, L).
um_entre(X, Y, L) :- nextto(Y, A, L), nextto(A, X, L).

% PADRAO
% Avioes[ piloto,  cor,  anomalia,  bebida,  esporte  ]

solucao(Avioes) :- 
    Avioes = [_, _, _, _, _],
    member(aviao(milton, vermelha, _, _, _), Avioes),
    member(aviao(walter, _, radio, _, _), Avioes),
    member(aviao(_, verde, _, _, pesca), Avioes),
    member(aviao(rui, _, _, _, futebol), Avioes),
    nextto(aviao(_, branca, _, _, _), aviao(_, verde, _, _, _), Avioes),
    member(aviao(_, _, altimetro, leite, _), Avioes),
    member(aviao(_, preta, _, cerveja, _), Avioes),
    member(aviao(_,vermelha,_,_,natacao), Avioes),
    [aviao(farfarelli, _, _, _, _) | _] = Avioes,
    ao_lado(aviao(_, _, _, cafe, _), aviao(_, _, hidraulico, _, _), Avioes),
    ao_lado(aviao(_, _, _, cerveja, _), aviao(_, _, bussola, _, _), Avioes),
    member(aviao(_, _, _, cha, equitacao), Avioes),
    member(aviao(nascimento, _, _, agua, _), Avioes),
    ao_lado(aviao(farfarelli, _, _, _, _), aviao(_, azul, _, _, _), Avioes),
    um_entre(aviao(_, _, hidraulico, _, _), aviao(_, _, altimetro, _, _), Avioes),
    member(aviao(_, _, _, _, tenis), Avioes),
    member(aviao(_, _, temperatura, _, _), Avioes).

    

