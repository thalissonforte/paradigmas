-- Crie uma função não-recursiva em Haskell que receba uma tupla (x,y) e calcule o seguinte somatório 
-- (sem simplificações matemáticas): (x+1) + (x+2) + ... + (x+n), onde n = y-x.

sumTupla :: (Int, Int) -> Int
sumTupla tupla = sum[x+n | x <- [fst(tupla)], n<-[1..snd(tupla)-x]]

