# Definindo funções auxiliares
def head(l):
    return l[0]

def last(l):
    return l[-1]

# Crie uma função isVowel :: Char -> Bool que verifique se um caracter é uma vogal ou não.
def isVowel(c):
    return c in ['a','e','i','o','u']

# Escreva uma função addComma, que adicione uma vírgula no final de cada string contida numa lista.
def comma(str):
    return str + ','

def addComma(n):
    return list(map(comma, n))

# Crie uma função htmlListItems :: [String] -> [String], que receba uma lista de strings e retorne outra lista contendo as strings formatadas como itens de lista em HTML. Resolva este exercício COM e SEM funções anônimas (lambda). Exemplo de uso da função:
def addHtml(str):
    return "<LI>" + str + "</LI>"

def htmlListItems(n):
    return list(map(addHtml, n))

def htmlListItemsAnon(n):
    return list(map(lambda a: "<LI>" + a + "</LI>", n))

# Defina uma função que receba uma string e produza outra retirando as vogais, conforme os exemplos abaixo. Resolva este exercício COM e SEM funções anônimas (lambda).
def isNotVowel(c):
    return not(isVowel(c))

def semVogais(n):
    return ''.join(list(filter(isNotVowel, n)))

def semVogaisAnon(n):
    return ''.join(list(filter(lambda a: not isVowel(a), n)))

# Defina uma função que receba uma string, possivelmente contendo espaços, e que retorne outra string substituindo os demais caracteres por '-', mas mantendo os espaços. Resolva este exercício COM e SEM funções anônimas (lambda). 
def verfSpace(n):
    if n != ' ':
        return '-'
    return n

def subSpace(str):
    return ''.join(list(map(verfSpace, str)))

def subSpaceAnon(str):
    return ''.join(list(map(lambda a: verfSpace(a), str)))

# Escreva uma função firstName :: String -> String que, dado o nome completo de uma pessoa, obtenha seu primeiro nome. Suponha que cada parte do nome seja separada por um espaço e que não existam espaços no início ou fim do nome.
def firstName(n):
    a = head(n.split(' '))
    return a

# Escreva uma função isInt :: String -> Bool que verifique se uma dada string só contém dígitos de 0 a 9.
def verfNumbers(n):
    numbers = ['0','1','2','3','4','5','6','7','8','9']
    return n in numbers

def isInt(str):
    return all(list(map(verfNumbers, str)))

# Escreva uma função lastName :: String -> String que, dado o nome completo de uma pessoa, obtenha seu último sobrenome.
def lastName(str):
    return last(str.split(' '))

# Escreva uma função userName :: String -> String que, dado o nome completo de uma pessoa, crie um nome de usuário (login) da pessoa, formado por: primeira letra do nome seguida do sobrenome, tudo em minúsculas.
def userName(str):
    return (head(str) + lastName(str)).lower()

# Escreva uma função encodeName :: String -> String que substitua vogais em uma string, conforme o esquema a seguir: a = 4, e = 3, i = 2, o = 1, u = 0.
def encodeLett(n):
    if n=='a':
        return '4'
    if n=='e':
        return '3'
    if n=='i':
        return '2'
    if n=='o':
        return '1'
    if n=='u':
        return '0'
    return n
        

def encodeName(str):
    return ''.join(list(map(encodeLett, str)))

# Escreva uma função betterEncodeName :: String -> String que substitua vogais em uma string, conforme este esquema: a = 4, e = 3, i = 1, o = 0, u = 00.
def betterEncodeLett(n):
    if n=='a':
        return '4'
    if n=='e':
        return '3'
    if n=='i':
        return '1'
    if n=='o':
        return '0'
    if n=='u':
        return '00'
    return n
    
def betterEncodeName(str):
    return ''.join(list(map(betterEncodeLett, str)))

# Dada uma lista de strings, produzir outra lista com strings de 10 caracteres, usando o seguinte esquema: strings de entrada com mais de 10 caracteres são truncadas, strings com até 10 caracteres são completadas com '.' até ficarem com 10 caracteres.
def verfLen(tam, str):
    if(tam > 10):
        return str[:10]
    elif (tam < 10):
        return (str+'..........')[:10]
    return str

def tenLen(str):
    return verfLen(len(str), str)