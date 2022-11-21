# Flood-fill

*Flood fill* ou *Algoritmo de Inundação* é um algoritmo usado para determinar ou preencher uma área limitada conectada a um determinado célula/nó em uma matriz/grafo.

## Aplicações
<!-- Comentário: aqui cada um pode explicar cada aplicação --> 

### Rede de computadores

É usado para distribuir informação para todos os nós de uma rede conectada

<p align="center">
<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/f/f4/Usenet_servers_and_clients.svg/800px-Usenet_servers_and_clients.svg.png" width="400" />
</p>

### Matemática 

É usado para resolver vários problemas, inclusive problemas de labirinto e os envolvidos com a teoria de grafos. 

<p align="center">
<img src="https://i.ytimg.com/vi/Zwh-QNlsurI/maxresdefault.jpg" width="400" />
</p>

### Computação Gŕafica

É usado para preencher toda uma área delimitada por uma cor com uma outra cor, a partir de um ponto inicial. 

<p align="center">
<img src="https://algomonster.s3.us-east-2.amazonaws.com/flood_fill.png" width="400" />
</p>

## Algoritmo

```
1. Pegue a posição do ponto de partida.
2. Decida se quer ir em 4 direções (N, S, W, E) ou 8 direções (N, S, W, E, NW, NE, SW, SE).
3. Escolha uma cor/número de substituição e de alvo.
4. Viaje nessas direções.
5. Se a cor/número em que você cair for um alvo, troque pela cor/número de substituição.
6. Repita 4 e 5 até que você esteja em todos os lugares dentro dos limites.
```

## Implementações
<!-- Comentário: já que tenho parte do código, vou tentar implementar cada um e gerar GIFs próprios. Talvez a gente tenha que deixar o Span Filling de fora --> 

### Recursive 4-way

```
Flood-fill (node):
 1. If node is not Inside return.
 2. Set the node
 3. Perform Flood-fill one step to the south of node.
 4. Perform Flood-fill one step to the north of node
 5. Perform Flood-fill one step to the west of node
 6. Perform Flood-fill one step to the east of node
 7. Return.
```

<p align="center">
<img src="https://upload.wikimedia.org/wikipedia/commons/7/7e/Recursive_Flood_Fill_4_%28aka%29.gif" width="300" />
</p>

### Iterative 4-way - Queue or Stack

```
Flood-fill (node):
  1. Set Q to the empty queue or stack.
  2. Add node to the end of Q.
  3. While Q is not empty:
  4.   Set n equal to the first element of Q.
  5.   Remove first element from Q.
  6.   If n is Inside:
         Set the n
         Add the node to the west of n to the end of Q.
         Add the node to the east of n to the end of Q.
         Add the node to the north of n to the end of Q.
         Add the node to the south of n to the end of Q.
  7. Continue looping until Q is exhausted.
  8. Return.
```
<p align="center">
<img src="https://upload.wikimedia.org/wikipedia/commons/b/b6/Wfm_floodfill_animation_queue.gif" width="250" />
<img src="https://upload.wikimedia.org/wikipedia/commons/5/5e/Wfm_floodfill_animation_stack.gif" width="250" />
</p>

### Span Filling

```
fn fill(x, y):
  if not Inside(x, y) then return
  let s = new empty stack or queue
  add (x, y) to s
  while s is not empty:
    Remove an (x, y) from s
    let lx = x
    while Inside(lx - 1, y):
      Set(lx - 1, y)
      lx = lx - 1
    while Inside(x, y):
      Set(x, y)
      x = x + 1
    scan(lx, x - 1, y + 1, s)
    scan(lx, x - 1, y - 1, s)

fn scan(lx, rx, y, s):
  let added = false
  for x in lx .. rx:
    if not Inside(x, y):
      added = false
    else if not added:
      Add (x, y) to s
      added = true
```

<p align="center">
<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/b/b7/Smiley_fill.gif/212px-Smiley_fill.gif" width="300" />
</p>

## Referências

- [https://pt.wikipedia.org/wiki/Algoritmo_de_inunda%C3%A7%C3%A3o](https://pt.wikipedia.org/wiki/Algoritmo_de_inunda%C3%A7%C3%A3o)
- [https://www.freecodecamp.org/news/flood-fill-algorithm-explained/](https://www.freecodecamp.org/news/flood-fill-algorithm-explained/)
- [https://www.thecshandbook.com/Flood_Fill](https://www.thecshandbook.com/Flood_Fill)
- [https://en.wikipedia.org/wiki/Flood_fill](https://en.wikipedia.org/wiki/Flood_fill)
