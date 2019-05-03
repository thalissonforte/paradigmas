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

# 5 aqui