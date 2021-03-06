
# Threading- Zusammenfassung

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

**Livelocks:**
Ein Livelock ist einem Deadlock ähnlich, außer dass sich die Zustände der Prozesse, die in dem Livelock involviert sind, ständig in Bezug aufeinander ändern, keiner voranschreitet. Livelock ist ein spezieller Fall von Ressourcenmangel; Die allgemeine Definition besagt nur, dass ein bestimmter Prozess nicht voranschreitet


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

**Deadlocks**
Die 4 Deadlock-Bedingungen:

Mutual Exclusion: Der Zugriff auf die Betriebsmittel ist exklusiv
Hold and Wait: Die Prozesse fordern neue Betriebsmittel an und halten dabei aber die Rechte an Betriebsmitteln, die sie schon haben.
No Preemption: Die Betriebsmittel werden ausschließlich durch die Prozesse freigegeben.
Circular Wait: Nicht weniger als zwei Prozesse warten in einem geschlossenen System.

Zwei Threads die sich in in geschützten Bereich befinden und gegenseitig aufeinander angewiesen sind. Thread1 kann den geschützten Bereich nicht verlassen, weil er auf Thread2 warten muss, Thread2 kann aber nicht fortfahren, solange Thread1 im geschüzten Bereich ist.

Möglichkeiten die Chancen eines Deadlocks zu verringern gibt es.

Zum Biespiel:
Locks (bzw. Mutexe)
Events und Bedingungsvariablen
Queues
Atomare Variablen (in Standard-Python nicht vorhanden)

In Java werden meistens Synchronisations-Methoden verwendet:

```java
public void addName(String name) {
    synchronized(this) {
    lastName = name;
    nameCount++;
 }
   nameList.add(name);
}

public class MsLunch {
    private long c1 = 0;
    private long c2 = 0;
    private Object lock1 = new Object();
    private Object lock2 = new Object();
    
    public void inc1() {
        synchronized(lock1) {
            c1++;
        }
    }
        
    public void inc2() {
        synchronized(lock2) {
            c2++;
        }
     }
}

```

Eine weitere Art der Java "Thread-Synchronisation" sind **Atomic Variables**:
```java
import java.util.concurrent.atomic.AtomicInteger;

class AtomicCounter {
    private AtomicInteger c = new AtomicInteger(0);
    
    public void increment() {
        c.incrementAndGet();
    }
    
    public void decrement() {
        c.decrementAndGet();
    }
    
    public int value() {
        return c.get();
    }
}
```java

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
