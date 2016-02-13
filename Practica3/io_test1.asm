; pos 0 es el contador
push 1 ;guardar desde la 1
store 0

in
dup
load 0 ;cargo pos
flip
storeind ;guardo letra en posición
load 0
push 1
add 
store 0 ;pos++
push -1
eq
rbf -11 ;while !fin cadena

push 1
store 0

;escribir
load 0
dup
loadind
dup
push -1
eq
rbf 2
halt
flip
push 1
add
store 0 ;pos++
out
rjump -13
end