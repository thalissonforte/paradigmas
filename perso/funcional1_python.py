# Definindo funções auxiliares
def head(l):
    return l[0]

def last(l):
    return l[-1]

# Crie uma função sumSquares :: Int -> Int -> Int que calcule a soma dos quadrados de dois números x e y.
def sumSquares(x, y):
    return x*x + y*y
    
# Crie uma função hasEqHeads :: [Int] -> [Int] -> Bool que verifique se 2 listas possuem o mesmo primeiro elemento.
def hasEqHeads(a, b):
    if(head(a) == head(b)):
        return True
    return False
    
# Escreva uma função que receba uma lista de nomes e adicione a string "Super " no início de cada nome. Use o operador ++ para concatenar strings (ou qualquer lista).
def addSuper(a):
    return 'Super ' + a

def superString(n):
    return list(map(addSuper, n))

# Crie uma função que receba uma string e retorne o número de espaços nela contidos. Dica: aplique 2 funções consecutivamente.
def findSpace(a):
    return a == ' '

def countSpace(str):
    return len(list(filter(findSpace, str)))

# Escreva uma função que, dada uma lista de números, calcule 3*n^2 + 2/n + 1 para cada número n da lista. Dica: defina uma função anônima.
def calcExpr(n):
    calc = lambda a : 3*(a*a) + 2/a 
    return list(map(calc, n))

# Escreva uma função que, dada uma lista de números, selecione somente os que forem negativos.
def negative(a):
    return a < 0

def negOnly(n):
    return list(filter(negative, n))

# Escreva uma função que receba uma lista de números e retorne somente os que estiverem entre 1 e 100, inclusive. Dica 1: defina uma função anônima. Dica 2: use o operador && para expressar um 'E' lógico.
def betw100(n):
    return list(filter(lambda a: a <= 100 and a >= 1, n))

# Escreva uma função que, dada uma lista de idades de pessoas no ano atual, retorne uma lista somente com as idades de quem nasceu depois de 1980. Para testar a condição, sua função deverá subtrair a idade do ano atual.
def aft1980(n):
    return list(filter(lambda a: (2019-a)>1980, n))

# Escreva uma função que receba uma lista de números e retorne somente aqueles que forem pares.
def evenNum(n):
    return list(filter(lambda a: a%2==0, n))

# Crie uma função charFound :: Char -> String -> Bool que verifique se o caracter (primeiro argumento) está contido na string (segundo argumento). Exemplos de uso da função:
def verfChar(a, str):
    return a in str

# Crie uma função que receba uma lista de nomes e retorne outra lista com somente aqueles nomes que terminarem com a letra 'a'. Dica: conheça o list monster, do autor Miran 
def aLast(n):
    return list(filter(lambda x: last(x) == 'a', n))

