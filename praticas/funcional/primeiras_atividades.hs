-- 1
sumSquares :: Int -> Int -> Int
sumSquares x y = x^2 + y^2

-- 2
hasEqHeads :: [Int] -> [Int] -> Bool
hasEqHeads list1 list2 = if (head list1) == (head list2) then True else False

-- 3
superString :: [String] -> [String]
superString strs = map("Super "++)strs

-- 4
noSpace :: Char -> Bool
noSpace c = if (c == ' ') then True else False
countSpaces :: String -> Int
countSpaces str = length(filter (noSpace)str)

-- 5
calcEq :: [Int] -> [Int]
calcEq n = map(\x -> 3 * x^2 + (div 2 x) + 1) n

-- 6
positiveNumbers :: [Int] -> [Int]
positiveNumbers nums = filter(<0) nums

-- 7
betw1a100 :: [Int] -> [Int]
betw1a100 nums = filter (<=100) ( filter(>=1) nums )

-- 8
birthYear :: Int -> Bool
birthYear i = if ((2019-i) > 1980) then True else False
aft1980 :: [Int] -> [Int]
aft1980 age = filter (birthYear)age

-- 9
evenNumbers :: [Int] -> [Int]
evenNumbers nums = filter (\x -> (mod x 2) == 0) nums

-- 10
charFound :: Char -> String -> Bool
charFound c str = if(length(filter (==c) str)) > 0 then True else False

-- 11
lastChar :: String -> Bool
lastChar name = if ((last)name) == 'a' then True else False
verifNames :: [String] -> [String]
verifNames name = filter(lastChar)name

-- 12
firstName :: [String] -> [String]
firstName str = map( takeWhile(/=' ') ) str
