import Text.Printf

type Point     = (Float,Float)
type Rect      = (Point,Float,Float)
type Circle    = (Point,Float)

-------------------------------------------------------------------------------
--                           Auxiliar                                        --
-------------------------------------------------------------------------------
degTorad :: Float -> Float
degTorad deg = deg * pi / 180

limit :: Float -> Float -> Float -> Float
limit x sup inf = if x > sup then sup else if x < inf then inf else x

limitColor :: Int -> Float
limitColor x = if (x == 0 || x == 6) then 0.5 else if x < 6 then 0 else 1

rgbColorf :: Int -> Int -> Int
rgbColorf x n = round(255 * limitColor(mod x n))     -- 0, 0, 0, 0, 0, 0.5, 1, 1, 1, 1, 1, 0.5

-------------------------------------------------------------------------------
--                           Paletas de cores                                --
-------------------------------------------------------------------------------
rgbPalette :: Int -> [(Int,Int,Int)]
rgbPalette n = take n (cycle [(255,0,0),(0,255,0),(0,0,255)])

greenPalette :: Int -> Int -> [(Int,Int,Int)]
greenPalette l c = [(0, 80+(i+j)*10, 0) | j <- [0..(c-1)], i <- [0..(l-1)] ]

redPalette :: Int -> Int -> [(Int, Int, Int)]
redPalette l c = [(80+(i+j)*10, 0, 0) | j <- [0..(c-1)], i <- [0..(l-1)] ]

bluePalette :: Int -> Int -> [(Int, Int, Int)]
bluePalette l c = [(0, 0, 80+(i+j)*10) | j <- [0..(c-1)], i <- [0..(l-1)]]

colorfulPalette :: Int -> [(Int, Int, Int)]
colorfulPalette n = [ ( rgbColorf (x+8) n, rgbColorf (x+4) n, rgbColorf x n) | x <- [1..fromIntegral n]]
--colorfulPalette n = [ (255 - round (255 * limitColor(atan(degTorad((x+2)*30)))), round (255 * limitColor(atan(degTorad((x+4)*30)))), round (255 * limitColor(atan(degTorad((x)*30))))) | x <- [-5..6]]
--colorfulPalette n = [ (floor (255 * abs(cos(degTorad (x*15)))), floor (255 * abs(sin(degTorad (x*15)))), floor (x/15 * 255)) | x <- [0..11]]
--colorfulPalette n = [ ( round(limit ((255 * sin(degTorad(x*45)))) 255) , 0, 0) | x <- [0..fromIntegral(n-1)]]

-------------------------------------------------------------------------------
--                 Gerando posições dos retângulos                           --
-------------------------------------------------------------------------------
genRectsInLine :: Int -> [Rect]
genRectsInLine n  = [((m*(w+gap),0.0),w,h) | m <- [0..fromIntegral (n-1)]]
  where (w,h) = (50,50)
        gap = 10

genRectsInMatr :: Int -> Int -> Float -> [Rect]
genRectsInMatr n m initGap = [((initGap + l*(w+gap), initGap + c*(h+gap)),w,h) |  c <- [0..fromIntegral (m-1)], l <- [0..fromIntegral (n-1)]] -- C para coluna, L para linha
  where (w,h) = (50,50)
        gap = 10

-------------------------------------------------------------------------------
--                   Gerando posições dos círculos                           --
-------------------------------------------------------------------------------

genCircleInCircle :: Int -> Int -> Float -> Float -> [Circle]
genCircleInCircle n r initGap gap = [(( initGap + gap * cos (x * degTorad 30), initGap + gap * sin (x * degTorad 30)),fromIntegral r) | x <- [0..fromIntegral (n-1)]]

genTripleCircle :: Int -> Int -> Float -> Float -> Float -> [Circle]  
genTripleCircle n r initGap gap l = [((initGap*x + gap * sin(y*degTorad 120), (z*200) + initGap/1.5 - gap*cos(y*degTorad 120)), fromIntegral r) | z<-[0..(l-1)], x <- [1..3], y <- [2,1,0]]

genSinCircle :: Int -> Int -> Float -> Float -> Int -> [Circle]
genSinCircle n r initGap gap lines = [((initGap + (x*gap), initGap + (y*100) + 50*sin(x * degTorad 30)), fromIntegral r) | y<-[0..fromIntegral (lines-1)], x<-[0..fromIntegral (n-1)]]

genCircleCircle :: Int -> Int -> Float -> Float -> Float -> [Circle]
genCircleCircle n r initGap gap l = [((initGap*x + gap * sin(y*degTorad 120), ((z-1)*60) + initGap/1.5 - gap*cos(y*degTorad 120)), fromIntegral r) | z<-[0..(l-1)], x <- [1..6], y <- [2,1,0]]

-------------------------------------------------------------------------------
-- Strings SVG
-------------------------------------------------------------------------------
svgRect :: Rect -> String -> String 
svgRect ((x,y),w,h) style = 
  printf "<rect x='%.3f' y='%.3f' width='%.2f' height='%.2f' style='%s' />\n" x y w h style

svgCircl :: Circle -> String -> String
svgCircl ((x,y),r) style =
  printf  "<circle cx='%.3f' cy='%.3f' r='%.3f' style='%s' />\n" x y r style

svgBegin :: Float -> Float -> String
svgBegin w h = printf "<svg width='%.2f' height='%.2f' xmlns='http://www.w3.org/2000/svg'>\n" w h 

svgEnd :: String
svgEnd = "</svg>"

svgStyle :: (Int,Int,Int) -> String
svgStyle (r,g,b) = printf "fill:rgb(%d,%d,%d); mix-blend-mode: screen;" r g b

svgElements :: (a -> String -> String) -> [a] -> [String] -> String
svgElements func elements styles = concat $ zipWith func elements styles

-------------------------------------------------------------------------------
--                    Função de cada caso específico                         --
-------------------------------------------------------------------------------
genCase1 :: IO ()
genCase1 = do
  writeFile "case1.svg" (txtBuild)
  where txtBuild = svgBegin width height ++ svgfigs ++ svgEnd
        svgfigs = svgElements svgRect rects (map svgStyle palette)
        rects = genRectsInMatr nrects nlines initGap
        palette = greenPalette nrects nlines
        -- ALGUMAS INFORMACOES SOBRE O SVG DA MATRIZ DE RETANGULOS
        nrects = 10   -- rects por linha
        nlines = 8    -- linhas
        width = 1500  -- largura svg
        height = 500  -- altura svg
        initGap = 20  -- gap inicial

genCase2 :: IO ()
genCase2 = do
  writeFile "case2.svg" (txtBuild)
  where txtBuild = svgBegin width height ++ svgfigs ++ svgEnd
        svgfigs = svgElements svgCircl circles (map svgStyle palette)
        circles = genCircleInCircle ncircle rad initGap gap
        palette = colorfulPalette ncircle
        -- ALGUMAS INFORMACOES SOBRE O SVG DOS CIRCULOS NO CIRCULO
        ncircle = 12    -- total de circulos
        rad = 10        -- raio circulo
        width = 1500    -- largura svg
        height = 500    -- altura svg
        initGap = 100   -- gap inicial
        gap = 50        -- gap padrao

genCase3 :: IO ()
genCase3 = do
  writeFile "case3.svg" (txtBuild)
  where txtBuild = svgBegin width height ++ svgfigs ++ svgEnd
        svgfigs = svgElements svgCircl circles (map svgStyle palette)
        circles = genTripleCircle ntriple rad initGap gap nlines
        palette = rgbPalette (ntriple*3)
        -- ALGUMAS INFORMACOES SOBRE O SVG DOS CIRCULOS NO CIRCULO
        ntriple = 6     -- total de triplas
        nlines = 2      -- total de linhas
        rad = 50        -- raio circulo
        width = 1500    -- largura svg
        height = 500    -- altura svg
        initGap = 200   -- gap inicial
        gap = 40        -- gap padrao
        
genCase4 :: IO ()
genCase4 = do
  writeFile "case4.svg" (txtBuild)
  where txtBuild = svgBegin width height ++ svgfigs ++ svgEnd
        svgfigs = svgElements svgCircl circles (map svgStyle palette)
        circles = genSinCircle ncircles rad initGap gap nlines
        palette = redPalette 1 14 ++ greenPalette 1 14 ++ bluePalette 1 14
        -- ALGUMAS INFORMACOES SOBRE O SVG DOS CIRCULOS NO CIRCULO
        ncircles = 14     -- total de triplas
        nlines = 3      -- total de linhas
        rad = 20        -- raio circulo
        width = 1500    -- largura svg
        height = 500    -- altura svg
        initGap = 120   -- gap inicial
        gap = 30        -- gap padrao


genCase5 :: IO ()
genCase5 = do
  writeFile "case5.svg" (txtBuild)
  where txtBuild = svgBegin width height ++ svgfigs ++ svgEnd
        svgfigs = svgElements svgCircl circles (map svgStyle palette)
        circles = genCircleCircle ntriple rad initGap gap nlines
        palette = rgbPalette (ntriple*6)
        -- ALGUMAS INFORMACOES SOBRE O SVG DOS CIRCULOS NO CIRCULO
        ntriple = 12     -- total de triplas
        nlines = 5      -- total de linhas
        rad = 30        -- raio circulo
        width = 1500    -- largura svg
        height = 500    -- altura svg
        initGap = 100   -- gap inicial
        gap = 20       -- gap padrao
