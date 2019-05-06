-- 1
verifChar :: Char -> Char -> Bool
verifChar c v = if c == v then True else False

isVowel :: Char -> Bool
isVowel char = any(==True) (map(verifChar char)['a','e','i','o','u'])

-- 2
commaInd :: String -> String
commaInd s = s ++ ","

addComma :: [String] -> [String]
addComma s = map(commaInd)s

-- 3
htmlInd :: String -> String
htmlInd s = "<LI>" ++ s ++ "</LI>"

htmlListItems :: [String] -> [String]
htmlListItems str = map (htmlInd) str

-- 4
semVogaisAux :: Char -> Bool
semVogaisAux c = if (isVowel c) then False else True
semVogais :: String -> String
semVogais str = filter(semVogaisAux)str

semVogaisAnon :: String -> String
semVogaisAnon name = filter(\x -> not(isVowel x))name

-- 5
codificaAux :: Char -> Char
codificaAux c = if c==' ' then ' ' else '-'
codifica :: String -> String
codifica str = map(codificaAux)str

codificaAnon str = map(\c -> if c==' ' then ' ' else '-')str