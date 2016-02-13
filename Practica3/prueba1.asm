; Leemos el caracter de la entrada
in
; Miramos si hemos llegado al final
dup ; Duplicamos el valor devuelto por IN
push -1 ; Si es == -1, entonces
eq ; hemos llegado al final de la entrada
bt 11 ; por lo que saltamos al final del programa
; No hemos llegado al final. Miramos si es una ’a’
dup
push 97 ; ascii(’a’) = 97
eq
bt 0
; No es una ’a’. Escribimos y volvemos a empezar
out
jump 0
;
; Fin del programa.
; Número de instrucciones = 11
;
