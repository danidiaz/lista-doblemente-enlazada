# lista-doblemente-enlazada

Una implementación en Java de una lista doblemente enlazada.

La lista implementa interfaces estándar Java para este tipo de contenedores,
como [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html) y
[Deque](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html). 

El añadido y borrado de elementos en el interior de la lista se realiza a través de los métodos del [ListIterator](https://docs.oracle.com/javase/8/docs/api/java/util/ListIterator.html).

## Notas sobre el diseño

Métodos como addFirst, addLast, removeFirst, removeLast están implementados a
través del iterador. El motivo es reducir la duplicación de código,
especialmente de código que manipula "punteros".

Esto es ligeramente ineficiente, porque se crea un iterador cuando no sería
estrictamente necesario. Decidí que este trade-off valía la pena.

## Compilación

El proyecto necesita [Maven 3](https://maven.apache.org/) y Java 8.

Para generar el jar:

> mvn package

Para ejecutar las pruebas:

> mvn test

