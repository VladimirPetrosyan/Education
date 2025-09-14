#Тут будут решение домашних заданий по курсу Java

#Для запуска из корня проекта используйте команду

javac -d out src\main\*.java

java -cp out main.Main

Что реализовано
1) Собственный HashSet — MyHashSet

API:
boolean insert(E key), boolean delete(E key), int size().

Устройство: массив бакетов Node<E>[] table, коллизии — связные списки (separate chaining).
Ресайз при loadFactor = 0.75 (удвоение массива и перехеширование).

Сложности: insert/delete — амортизированно O(1), в худшем O(n) при сильных коллизиях.

2) Собственный динамический массив — MyArrayList

API:
void add(E e), E get(int idx), E remove(int idx), void addAll(Collection<? extends E>).
Реализует Iterable<E> для for-each.

Устройство: внутренний Object[] data, рост old + old/2.
remove(int) сдвигает «хвост» массивом.

3) Доменные классы

Book — неизменяемая модель. equals/hashCode основаны на (title, author, year) — это важно для distinct() в стриме.

Student — содержит name и обязательное поле List<Book> (хранится через List.copyOf(...)).
toString() печатает имя и кол-во книг.

В Main.main создаётся список студентов и сразу запускается единая стрим-цепочка


#Module2 

запуск проекта осущесвляется командами:

javac -d out src/module2/FileOperationException.java src/module2/FileApp.java
java -cp out module2.FileApp

в FileOperationException - Пользовательское исключение. Наследуется от Exception и используется для обёртки ошибок при работе с файлами.

FileApp - Главный класс приложения. Содержит методы для записи и чтения файлов (writeToFile, readFromFile) и демонстрацию работы в main. 