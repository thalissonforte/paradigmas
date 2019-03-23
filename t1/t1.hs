--Import
import Data.Char

--1
verifChar :: Char -> Char -> Bool
verifChar c v = if (c==v) then True else False

isVowel :: Char -> Bool
isVowel c = any(True ==) (map(verifChar c)['a','e','i','o','u'])

--2
addComma :: [String] -> [String]
addComma list = map(\i -> i++",")list

--3
htmlListItemsAnon :: [String] -> [String]
htmlListItemsAnon list = map(\i -> "<LI>"++i++"</LI>")list

htmlListAux :: String -> String
htmlListAux i = "<LI>"++i++"</LI>"
htmlListItems :: [String] -> [String]
htmlListItems list = map(htmlListAux)list

--4
verifVogal :: Char -> Bool
verifVogal c = if (isVowel c) then False else True
semVogais :: String -> String
semVogais name = filter (verifVogal) name

semVogaisAnon :: String -> String
semVogaisAnon name = filter (\n-> not (isVowel n))name

--5
verifSpace :: Char -> Char
verifSpace c = if (c /= ' ') then '-' else ' '
codifica :: String -> String
codifica str = map(verifSpace)str

codificaAnon :: String -> String
codificaAnon str = map(\s -> verifSpace s)str

--6
firstName :: String -> String
firstName name = takeWhile(/=' ') name

--7
verifInt :: Char -> Bool
verifInt c = any(True==) (map(verifChar c)['1','2','3','4','5','6','7','8','9','0'])
isInt :: String -> Bool
isInt str = all(==True) (map(verifInt)str)

--8
lastName :: String -> String
lastName name = reverse(takeWhile(/=' ') (reverse(name)))

--9
toLow :: Char -> Char
toLow c = toLower c
userName :: String -> String
userName name = map(toLow)([head name] ++ lastName(name))

--10
encode 'a' = '4'
encode 'e' = '3'
encode 'i' = '2'
encode 'o' = '1'
encode 'u' = '0'
encode c = c

encodeName :: String -> String
encodeName str = map(encode)str

--11
encode2 'a' = "4"
encode2 'e' = "3"
encode2 'i' = "1"
encode2 'o' = "0"
encode2 'u' = "00"
encode2 c = [c]

betterEncodeName :: String -> String
betterEncodeName c = concatMap(encode2)c

--12
charLen :: String -> String
charLen s = if (10 - length(s) > 0)
then charLen(s++".")
else s

func :: [String] -> [String]
func str = map (\x -> if ((length (take(10)x)) < 10) then charLen x else take(10)x) str
