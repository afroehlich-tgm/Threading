
#Threading- Zusammenfassung

**Threading Einfürhung:**
Das Betriebssystem simuliert Multitasking.
Der Scheduler wechselt so schnell zwischen den verschiedenen Prozessen (Threads), dass sie sozusagen Parallel laufen.
Threads haben einen eigenen Adressraum im Hauptspeicher und arbeiten unabhänging voneinander. (Ausname: Shared Memory)
Threads teilen sich Klassenvariablen
Sie besitzen eigene Kopien von globalen Variablen, 
die mit ThreadLocal erstellt werden.
Threads teilen sich den Heap

**Der kritische Bereich:**
Im kritisches Bereich kann sich nur 1 Thread gleichzeitig befinden.
Hier werden jene Operationen durchgeführt, die nicht unterbrochen werden dürfen.

**Starvation:**
Wenn ein Thread keinen oder kaum Fortschritt machen kann,
weil die Ressourcen ständig von einem anderen Thread blockiert
werden.

Dieser Effekt verstärkt sich je länger sich ein Thread 
im kritischen Berreich aufhält.

Threads die zu lange im kritischen Berreich sind,
werden gerne "greedy" genannt.

**Wo wird threading verwendet**
Zum Beispiel:
Ein Server der mehrere Clients bedienen möchte.
Ein Sortieralgorythmus der parallelisiert werden muss.
Webrequests und GUI - Anwendungen.

Wenn Prozesse miteinander kommunizieren müssen, 
benötigen wird eine IPC (Interprozess- Kommunikation)

Wir nennen diese Prozesse auch "schwergewichtig"

**mehrere Threads verwenden**

Ein Prozesss besteht immer aus mindestens einem Thread (Faden), ein Thread gehört immer zu einem Prozess.

Threads im selben Prozess teilen sich den selben Adressraum und können daher leichter miteinander synchronisiert & zerstört werden.

Der Adressraum von Threads muss im gegensatz zu Prozessen nicht getauscht werden.

Threads sind daher "leichtgewichtige"- Prozesse.

**Threading in Python**
Es gibt zwei grundlegende Module fur Multi-Threading in Python:
Das Modul thread (veraltet) und das Modul threading

```python
import threading
class EndlosschleifenThread ( threading .Thread):
    def __init__(self):
        threading.Thread.__init__(self)
    def run(self):
        while True:
        pass
```

Mit der Methode t.join() kann auf die Terminierung von Thread t gewartet werden.

```python
t1 = EndlosschleifenThread ()
t2 = EndlosschleifenThread ()
t1.start ()
t2.start ()
t1.join ()
t2.join ()
```


Threads erben von threading.Thread
Im Konstruktor muss der Basisklassen-Konstruktor aufgerufen werden.
Parameter fur den Thread werden ebenfalls im Konstruktor übergeben 
(und als Instanzvariable über self.<variablenname>
gespeichert).


Die run- Methode wird ausgeführt sobal der Thread mit thread.start() gestartet wurde.

**Threading in Java:**

```java
class SimpleThread extends Thread {
    public SimpleThread(String str) {
        super(str);
    }
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i + " " + getName());
            try {
                sleep((int)(Math.random() * 1000));
            } catch (InterruptedException e) {}
        }
        System.out.println("DONE! " + getName());
    }
}
```
