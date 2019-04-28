% Remover warning de discontiguous
:-style_check(-discontiguous).

% DEFINIÇÕES
relacionamento(X, Y) :- relacao(X, Y), !.
relacionamento(X, Y) :- relacao(Y, X), !.

% Em uma manhã de sábado, o inspetor Hercule Poirot foi requisitado para solucionar um mistério da morte de Anita, 
%  que foi assassinada no apartamento que dividia com algumas pessoas.
% O inspetor viu indícios de que o crime aconteceu na sexta ou na quinta-feira e foi cometido por apenas uma pessoa.
no_local(X) :- quinta(X, apartamento).
no_local(X) :- sexta(X, apartamento).

% Ele também viu três possíveis motivos para o crime: dinheiro, ciúme ou insanidade. 
motivacao(X, Y) :- insanidade(X), Y = insanidade.
motivacao(X, Y) :- dinheiro(X), Y = dinheiro.
motivacao(X, Y) :- ciumes(X), Y = ciumes.

% Além disso, o agressor devia ser alguém que dividia o apartamento com Anita.
dividia(pedro).
dividia(caren).
dividia(bia).
dividia(adriano).
dividia(alice).
dividia(bernardo).
dividia(maria).
dividia(henrique).

% O bastão de baseball que foi roubado do amigo pobre de Anita, Bernardo, na quinta-feira em Porto Alegre 
%   ou na quarta-feira em Santa Maria; OU
% O martelo que foi roubado da caixa de ferramentas do apartamento na quarta ou na quinta-feira.
%bastao(bernardo).
bastao(X) :- quarta(X, santa_maria) ; quinta(X, porto_alegre).
martelo(X) :- quarta(X, apartamento) ; quinta(X, apartamento).
pobre(bernardo).

% O assassino entrou no quarto de Anita utilizando a chave que roubou dela. 
% Esta chave foi roubada na quarta-feira em Santa Maria ou na terça-feira em Porto Alegre.
chave(X) :- quarta(X, santa_maria) ; terca(X, porto_alegre).

% Dinheiro foi roubado do quarto de Anita e sua amiga Bia, que é pobre, tem uma cópia da chave.
pobre(bia).
chave(bia).

% Anita tem um relacionamento com Bernardo, que por sua vez teve um relacionamento com a garota rica Caren.
relacao(anita, bernardo).
relacao(bernardo, caren).
rico(caren).

% Além disso, Anita teve um relacionamento com Pedro, que é pobre e namorou com a garota rica Alice.
relacao(anita, pedro).
pobre(pedro).
relacao(pedro, alice).

% Alice namorou com o igualmente rico Henrique.
relacao(alice, henrique).

% Henrique tinha sido noivo de Maria, que é pobre.
relacao(henrique, maria).
pobre(maria).

% Maria costumava sair com Adriano, que é rico, e já namorou com a menina rica Caren.
relacao(maria, adriano).
rico(adriano).
relacao(adriano, caren).

% Pedro estava em Santa Maria na segunda e na terça-feira, em Porto Alegre na quarta, 
%    em Santa Maria novamente na quinta e depois voltou ao apartamento.
segunda(pedro, santa_maria).
terca(pedro, santa_maria).
quarta(pedro, porto_alegre).
quinta(pedro, santa_maria).
sexta(pedro, apartamento).

% Caren ficou em Porto Alegre de segunda a quarta-feira, esteve em Santa Maria na quinta 
%    e na sexta ficou no apartamento.
segunda(caren, porto_alegre).
terca(caren, porto_alegre).
quarta(caren, porto_alegre).
quinta(caren, santa_maria).
sexta(caren, apartamento).

% Henrique esteve no apartamento na segunda, em Porto Alegre na terça e depois voltou para o apartamento.
segunda(henrique, apartamento).
terca(henrique, porto_alegre).
quarta(henrique, apartamento).
quinta(henrique, apartamento).
sexta(henrique, apartamento).

% Bia passou a segunda-feira no apartamento, esteve em Porto Alegre na terça e quarta e 
%    foi para Santa Maria na quinta, então retornou para o apartamento na sexta-feira.
segunda(bia, apartamento).
terca(bia, porto_alegre).
quarta(bia, porto_alegre).
quinta(bia, santa_maria).
sexta(bia, apartamento).

% Adriano estava em Santa Maria quarta-feira e ficou no apartamento o resto da semana. 
segunda(adriano, apartamento).
terca(adriano, apartamento).
quarta(adriano, santa_maria).
quinta(adriano, apartamento).
sexta(adriano, apartamento).

% Alice estava em Porto Alegre terça e quarta-feira e no apartamento segunda, quinta e sexta-feira.
segunda(alice, apartamento).
terca(alice, porto_alegre).
quarta(alice, porto_alegre).
quinta(alice, apartamento).
sexta(alice, apartamento).

% Bernardo estava em Santa Maria segunda e terça-feira, em Porto Alegre na quarta-feira, 
%   em Santa Maria novamente na quinta-feira e no apartamento na sexta.
segunda(bernardo, santa_maria).
terca(bernardo, santa_maria).
quarta(bernardo, porto_alegre).
quinta(bernardo, santa_maria).
sexta(bernardo, apartamento).

% Maria esteve em Santa Maria de terça a quinta-feira e no apartamento na segunda e na sexta-feira.
segunda(maria, apartamento).
terca(maria, santa_maria).
quarta(maria, santa_maria).
quinta(maria, santa_maria).
sexta(maria, apartamento).

% Adriano e Maria já visitaram um psiquiatra para tratar transtornos psicóticos.
insano(adriano).
insano(maria).

% Ciumes
ciumes(X) :- relacionamento(anita, Y), relacionamento(Y, X).

% Dinheiro
dinheiro(X) :- pobre(X).

% Insanidade
insanidade(X) :- insano(X).

% Vitima
vitima(anita).

% Acesso à ferramenta, local, chave e dia do assassinato
acesso(X) :-
    (bastao(X) ; martelo(X)),
    chave(X),
    no_local(X), 
    dividia(X), !.

% Descobrir assassino X pelo motivo Y
assassino(X, Y) :- acesso(X), motivacao(X, Y), !.