add10toall :: [Int] -> [Int]
add10toall list = map(+10)list

multN :: Int -> [Int] -> [Int]
multN m list = [x * m | x <-list]

applyExpr :: [Int] -> [Int]
applyExpr list = [3*x+2 | x <- list]

addSufix :: String -> [String] -> [String]
addSufix sufix list = [x++sufix | x <- list]

selectgt5 :: [Int] -> [Int]
selectgt5 list = [x | x <-list, x>5]

sumOdds :: [Int] -> Int
sumOdds list = sum[x | x<-list, odd x]

selectExpr :: [Int] -> [Int]
selectExpr list = [x | x<-list, even x, x>20, x<50]

countShorts :: [String] -> Int
countShorts list = length ([x | x<-list, length(x)<5])

calcExpr :: [Float] -> [Float]
calcExpr list = [(x*x)/2 | x<-list, x>10]

trSpaces :: String -> String
trSpaces str = [if x==' ' then '-' else x | x<-str]

selectSnd :: [(Int, Int)] -> [Int]
selectSnd tupla = [snd x | x<-tupla]

dotProd :: [Int] -> [Int] -> Int
dotProd list1 list2 = sum[(fst x)*(snd x) | x <- zip list1 list2]

dotProd2 :: [Int] -> [Int] -> Int
dotProd2 list1 list2 = sum[x | x <- zipWith(*) list1 list2]