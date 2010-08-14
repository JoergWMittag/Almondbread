import Data.Complex

width = 80
height = 20

escapeTime c = length (take 255 (escapeSeries c))
  where
    escapeSeries c = takeWhile notEscaping mandelSeries
      where
        notEscaping  = \z -> magnitude (abs z) < 4.0
        mandelSeries = iterate (\z -> z*z + c) c

eachPoint width height f =
  mapM_ (\(x, y) -> f x y (escapeTime ((-2.0 + 3.0 / width * x) :+ (-1.0 + 2.0 / height * y)))) [(x, y) | y <- [0..height], x <- [0..width]]

main = eachPoint width height (\x y v -> if x == 0 then putStrLn "" else putStr(if v < 255 then "-" else "#"))
