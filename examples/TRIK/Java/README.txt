В каталоге k-lab находится пример программы (проект в Idea) на Java,
управляющий устройствами ТРИК по i2c через командную строку.
Работает он только в Java 7 и практически бесполезен т.к. запускать что-то через exec очень медленно.

Java 8 у нас не взлетела, а было бы не плохо разобраться так как в ней
всеми внешними устройствами можно управлять через Device/IO (dio).

Скачиваем с сайта Oracle и распаковываем:
ejre-7u60-fcs-b19-linux-arm-sflt-headless-07_may_2014.tar
в
/home/root/java/ejre1.7.0_60

Прописываем /etc/profile две строчки:
export JAVA_HOME=/home/root/java/ejre1.7.0_60
export PATH=$PATH:$JAVA_HOME/bin

Выполняем эти команды и проверяем работу JRE:
java -version

Видим:
java version "1.7.0_60"
Java(TM) SE Embedded Runtime Environment (build 1.7.0_60-b19, headless)
Java HotSpot(TM) Embedded Client VM (build 24.60-b09, mixed mode)

Сборка проекта:
mvn install

Копирование на Трик:
scp target/klab.jar root@192.168.1.1:/home/root/trik/

Запуск программы на Трик:
cd trik
java -jar klab.jar

В файле TrikTest1.java аналогичный пример, который можно компилировать через javac без maven.