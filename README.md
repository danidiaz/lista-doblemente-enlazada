# lista-doblemente-enlazada

Una implementación en Java de una lista doblemente enlazada.

La lista implementa interfaces estándar Java para este tipo de contenedores,
como [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html) y
[Deque](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html). 

La adición y borrado de elementos en el interior de la lista se realiza a través de los métodos del [ListIterator](https://docs.oracle.com/javase/8/docs/api/java/util/ListIterator.html).

## Compilación

El proyecto necesita [Maven 3](https://maven.apache.org/) y Java 8.

Para generar el jar:

> mvn package

Para ejecutar las pruebas:

> mvn test

