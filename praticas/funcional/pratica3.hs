--1
add10toall :: [Int] -> [Int]
add10toall x = [x+10 | x<-x]

--2
multN :: Int -> [Int] -> [Int]
multN n x = [x*n | x<-x]

--3
applyExpr :: [Int] -> [Int]
applyExpr x = [3*x+2 | x <- x]

--4
addSuffix :: String -> [String] -> [String]
addSuffix str x = [x++str | x<-x]

--5
selectgt5 :: [Int] -> [Int]
selectgt5 i = [i | i<-i, i>5]

--6
sumOdds :: [Int] -> Int
sumOdds x = sum [x | x<-x, odd x]
-- FALTA SOMAR

--7
selectExpr :: [Int] -> [Int]
selectExpr x = [x | x<-x, even x, x>20, x<50]

--8
countShorts :: [String] -> Int
countShorts x = length ([x | x<-x, length(x)<5 ])

--9
calcExpr :: [Float] -> [Float]
calcExpr xs = filter (>10) [ x*x / 2 | x <- xs]

--10
trSpaces :: String -> String
trSpaces str = [if x == ' ' then '-' else x | x<-str]

--11
-- A) Cria conjuntos sendo (par, ímpar) onde o par fica
-- entre 1 e 5, e o ímpar (deve ser maior que o par)
-- deve ser maior que o par, sendo o máximo 6 
-- B) Concatena strings criando conjuntos com elas
-- C) Concatena uma consoante da palavra com '-'

--12
selectSnd :: [(Int,Int)] -> [Int]
selectSnd xs = [snd x | x<-xs]

--13
dotProd :: [Int] -> [Int] -> Int
dotProd a b = sum[ (fst x)*(snd x) | x <- zip a b]

--14
genRects :: Float -> (Float,Float) -> [(Float,Float,Float,Float)]
genRects n cord = [ ( x, snd(cord), 5.5, 5.5) | x <- [fst(cord), fst(cord)+5.5..(n-1) * 5.5 + fst(cord)]]

--15
dotProdZip :: [Int] -> [Int] -> Int
dotProdZip a b = sum[x | x <- zipWith (*) a b]