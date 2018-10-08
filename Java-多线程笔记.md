多线程相关笔记

# 1. 为什么需要并行（多线程）
1. 业务需求
eg: 一个http服务器同一时刻需要去处理多个客户端的请求。 通常的做法是为每个客户端去开启一个线程。
2. 性能需求
由于硬件的原因，芯片发展的计算速度限制（4Hz）,我们为了性能而折中使用的是一个cpu中插入多个芯片（多核）

# 2. 并行重要的几个概念
## 同步和异步
![同步与异步的理解图](https://github.com/zhaodahan/zhao_Note/blob/master/img-storage/%E5%90%8C%E6%AD%A5%E5%BC%82%E6%AD%A5.JPG)
1. 同步和异步是对方法的调用而言的。如果一个方法是同步的，在时间轴上，同步调用会等待方法的返回。
1. 异步调用他会立即返回(但是这并不表示方法调用已经结束了，而是会在后台起一个线程慢慢的进行方法的执行)。
1. 异步调用在调用后会立即得到方法的返回，并不会做等待，这就表示不会影响调用者后面做什么事。

## 并发和并行
![并发和并行](https://github.com/zhaodahan/zhao_Note/blob/master/img-storage/%E5%B9%B6%E5%8F%91%E4%B8%8E%E5%B9%B6%E8%A1%8C.JPG)
1. 并行指的是两个线程同时执行。
1. 并发指的是两个线程交替执行。
1. 并行存在于多个CPU中，单个CPU只能并发。(虽然这两者的外在表现形式是相同的，我们看到的都是两个任务在同时执行)
***

## 临界区
临界区用来表示一种公共的资源和数据，可以被多个线程使用(所有的线程都能访问临界区)。	临界区需要被保护，同一时间只允许一个线程来访问他。

## 阻塞和非阻塞
1. 阻塞和非阻塞是用来形容多线程间的相互影响。比如： 两个线程都需要使用临界区的资源，一个线程在访问的时候，另一个线程无法访问
另个一个线程被迫等待，这就是阻塞。阻塞的含义是这个线程在操作系统层面别挂起，这就导致阻塞的性能一般不会太好。
	
1. 非阻塞就是允许多个线程同时进入阻塞区(只要保证不把临界区资源弄坏)
	
## 锁 ，饥饿 和活锁
![死锁](https://github.com/zhaodahan/zhao_Note/blob/master/img-storage/%E6%AD%BB%E9%94%81.JPG)
1. 对于阻塞的程序来讲，进入临界区可能发生死锁的情况。如图中四辆车辆发生了死锁，每辆小车锁占有的路线视为资源。每辆车各占一条路，A车需要B车锁占得路，都堵住了无法行走，除非挪走一辆车。------这就是死锁。
	
1. 与死锁对应的就是活锁。就类似电梯遇人的情况。电梯门开了，里面的人要出来，外面的人要进去，两人迎面碰撞，这是，为了下次再相撞，里面的人往左让，外面的人往右让，这是两人又相撞了，这时你发现不对，就往右让，他往左让，因为你们是相对的，如此总是会把门堵住。。。如此反复。-----这就是活锁。(如果是人的话，会停下来，让一个人先过，但是程序不会，他会一直执行)。如果是发生在线程上面的，一个线程如果在抢占到资源后发现另外的资源他无法拿到，(死锁的必要条件是抢占了资源而不释放它，如果抢占了资源释放了它，就不会产生死锁) 因为这时候没有办法拿到所有的资源，这时候为避免死锁，就释放掉已经拿到的资源，然而这时候另外的线程也做了同样的事情。他们需要相同的资源（比如说都需要资源A 和资源B ,这时候一个线程抢占了A,另一个线程抢占了资源B,但是这个时候两个线程都释放了资源，这个时候发现A,B 都有了，又进行新一波的抢占和释放）。这就是活锁，就是资源在线程之间跳来跳去且任务无法继续下去，活锁这样的问题是动态的，死锁是静态问题。
	​	
	
1. 饥饿时在一种情况下： 在等待队列中，一个线程的优先级很低，在线程抢占调度过程总是无法调用到这个线程，就会导致这个线程无法抢占资源而饿死。
	​	
	
## 并发的级别

1. 阻塞
	
1. 非阻塞
	
1. 无障碍 ：最弱的非阻塞调度(和阻塞调度相比，阻塞调度室一种悲观的策略，它会认为大家一起修改数据会导致把数据改坏，所以每次只允许一个人去修改，而非阻塞调度相比而言比较乐观，可以大家一起修改，放开了锁都可以进，但它是一种宽进严出的策略，如果它发现一个线程操作临界区数据与其他线程操作发生了冲突，它会回滚重试)，
	
1. 无锁
无锁是无障碍的升级版，无锁首要条件是无障碍的，它在无障碍的基础上加了一个条件就是保证在临界区的竞争中保证会有一个胜出。(无锁的使用比较广泛)
	
1. 无等待
无锁
	​			
要求所有线程必须在有限步数里完成
	​				
无饥饿
	​				
(无等待的典型案例：所有的线程只是读线程，不是写线程必然是无等待的。）


# 3.并行的两个重要的定律

## 1. Amdahl定律（阿姆达尔定律）
![阿姆达尔定律——加速比](https://github.com/zhaodahan/zhao_Note/blob/master/img-storage/%E9%98%BF%E5%A7%86%E8%BE%BE%E5%B0%94%E5%AE%9A%E5%BE%8B(%E5%8A%A0%E9%80%9F%E6%AF%94).JPG)

阿姆达尔定律定义了串行系统并行化后的加速比( 加速比=优化前系统耗时/优化后系统耗时)--理论上限


## 2. Gustafson定律（古斯塔夫森定律）

只要有足够的并行化，加速比和CPU个数成正比

# 4 多线程基础

（1）Java中线程的概念和操作系统的线程是类似的。事实上Java中会把Java的线程映射到操作系统的线程中去。就是说如果在Java中开启了一个线程就等同于在windows中调用了开启线程的方法。

（2）线程的start() 方法和run() 方法的区别： run() 实现于runnable接口(==每个线程都是runnable接口的一个实现==)，start()只是在一个新的操作系统的线程上去调用run()方法，实际上调用run()而调用start(),两者实质上做的事情都一样start()就是去调用run方法，但是调用run()方法并不会开启一个新的线程，而是在调用的当前线程去执行操作；只有使用了start()才是真的开了一个新的线程。 


(3) 线程的操作
###### 创建

Thread.start() 和run() 方法

###### 终止

Thread.stop()方法(++不推荐使用，这个API太暴力，会导致线程没有执行完的时候就释放了资源，会导致多线程的数据的不一致性++)。

###### 线程的中断

线程中断的三个方法

```
(Thread)
public void interrupt() //中断线程
public Boolean  isInterrupted //判断是否被中断
public static boolean interrupted //判断是否被中断，并清除当前中断状态

```
什么是线程中断？

> 就是在线程运行时，对它打了个招呼，它就会把自己的一个所谓的中断标志位置上。这样他在运行的时候就知道有人需要它在运行的时候做一些响应。这样他就会做一些额外的操作。

> 因为Thread.stop()太过于暴力会影响数据的一致性。可以使用线程的中断来较为温和的停止线程。

==中断并不会让线程执行，他只是打了一个招呼，如果要让线程做额外的操作，需要再额外的填添加一些操作逻辑==

线程中断另一种常用的方法：


```
public static  native void sleep(long milis) throw InterruptedException // 线程休眠
```
如果我们希望一个线程不要走太快我们一般会调用sleep()方法让其休眠几秒钟。

> sleep()等让线程等待方法或抛出InterruptedException。(是因为线程希望在等待的过程中被打招呼中断他也能被结束，因为在等的过程中对业务来说是没有任何意义的，在等的过程中是应该要对别人打招呼作出响应的)。 sleep方法一般是要被try catch的。只有这样，当别人给我打招呼中断的时候才能立即的通过interruptedException来进行响应来做出一些动作。 (==这里需要注意一点抛出interruptedException后会中断异常标记==。--如果是在一个循环中，想要后续的仍能检测到中断标志需要重新调用一次interrupt()方法。如下图
![image](https://github.com/zhaodahan/zhao_Note/blob/master/img-storage/sleep%E4%B8%AD%E6%96%AD.JPG)
)

###### 线程让步

==static== Thread.yield( ) 方法：使当前线程从运行就绪状态。cpu会从众多的可执行态里选择，也就是说，当前也就是刚刚的那个线程还是有可能会被再次执行到的，并不是说一定会执行其他线程而该线程在下一次中不会执行到了。

###### 等待线程结束

join() 等待县城结束：
在我们使用多线程的时候我，我们并不知道线程在什么时候执行完了。往往我们希望等到线程结束后，拿到一些信息才能进行下去。（命名为join的原因：两个线程在不同的路上的走，要join加到一条路上就要等另一个走完。a.join()就是要等a线程走完）

join(long milins)等待多少时间

###### 线程的挂起(suspend)和线程的继续执行(resune)

> 当一个线程在执行过程中吗，如果你希望它能暂停一下，可以使用suspend()方法将线程挂起，当你希望线程继续执行的时候可以调用resume()方法让线程继续执行。 ==但是这两个方法不推荐使用==。 不推荐使用的原因是：如果线程加了锁后执行suspend()方法挂起，在调用resume()之前它不会释放临界区的资源，这样会导致其他线程无法获取到临界区资源而无法执行。
如下图：
![image](https://github.com/zhaodahan/zhao_Note/blob/master/img-storage/%e7%ba%bf%e7%a8%8b%e6%b0%b8%e4%b9%85%e8%a2%ab%e6%8c%82%e8%b5%b7.JPG)
在多线程情况下：如果另一线程执行resume()在线程执行suspend()之前，将会导致需要锁的线程被永久挂起。

---
==1.如果安装了jdk，在cmd中可以使用jps命令查看系统中的Java进程
2. 使用jstack pid(进程id) 可以导出该进程的所有线程。==
---

（4） 守护线程
在后台默默的完成一些系统维护（如： 垃圾回收）的一些线程。 一个Java程序中珠现场执行完，不管还有没有守护线程，系统就认为已无用，就会关闭。

Thread t=new Thread();
t.setDeamon(true); //设置线程为守护线程。必须设置在start()之前；

（5）线程的优先级
线程是可以抢占到资源的，优先级高的线程是更有可能抢占到资源的，更快的执行。

```
设置线程优先级的方法是在线程上调用setPriority()设置优先级
```

(6) 线程的同步，通信

多线程执行过程中十分重要的一点就是—多线程之间如何通信。一个线程挂起了，别人怎么来唤醒我。还有几个线程彼此竞争，如何来协调竞争了。

Java对这些情况提供了synchronized 关键字来协调线程竞争

synchronize需要注意的一点就是多个线程一定是要操作同一个锁；（特别是当synchronize加到实力方法上，它锁的对象就是当前执行线程传进来的实例）

![synchronized.png](https://github.com/zhaodahan/zhao_Note/blob/master/img-storage/synchronized.png?raw=true)

~~~java
Java提供Ojbect.wait()  //线程等待在当前对象(Object --synchronized(object)监视器)上
Object.notify() 用于线程通信 //通知等待在object这个对象上的线程从wait()上返回
~~~

也就是说一个线程调用了wait()方法就会进入wait状态，而不会再接着往下执行。

wait()这个方法的执行，必须要获得object这个监视器对象。这个方法的大致做的事情是在内存中建立一个键值对表，object监视器作为key,等待在当前监视器上的线程队列作为value。 这个线程队列中可以存在许多线程。当调用object。notify()的时候，会通过object这个监视器对象作为key，在键值对表中查询出它对应的等待线程队列。 然后随机的竞争一个线程来唤醒。

![wait，notify.JPG](https://github.com/zhaodahan/zhao_Note/blob/master/img-storage/wait%EF%BC%8Cnotify.JPG?raw=true)

在使用这两个方法的时候需要注意

![wait,notify 注意事项.JPG](https://github.com/zhaodahan/zhao_Note/blob/master/img-storage/wait,notify%20%E6%B3%A8%E6%84%8F%E4%BA%8B%E9%A1%B9.JPG?raw=true)

无论是wait()还是notify(),==在调用之前都必须先获得这个监视器的这个锁==，即需要在synchronized 修饰代码块中才能使用。(十分正常的逻辑，你需要先拿到他，才能调用他的方法)。且调用了object.wait()方法后， 线程的会释放object这个监视器的所有权，然后等待其他线程将他唤醒。

# 5 ，无锁

1.什么是无锁？

无锁就是“无障碍”的运行且每次竞争的时候必然会决出一个优胜者。“无障碍” 就是可以任意的访问临界区的资源，不用等待获取锁，多个线程可以同时的进入临界区。

2.无锁的原理：

说到了无锁，那就要说我们为什么要有锁。并发编程的加锁是为了保证共享对象的安全访问。 加锁的本质就是变“并发”为“串行”， 一次只允许一个访问就安全了。 并发变成了串行必然的访问速度的就降低了。 要提高速度就只能不加锁。 无锁由此诞生。

无锁的实现是使用了CAS算法。

3. 什么是CAS算法

CAS（Compare-and-Swap），即比较并替换，是一种实现并发算法时常用到的技术，Java并发包中的很多类都使用了CAS技术。

简而言之： CAS算法就是一种比synchronized关键字更加优秀的能保证并发访问时共享对象安全的策略。

CAS算法

要讲cas算法就要讲无锁

1. 什么是无锁

   相对于线程加锁来保证线程访问临界区的安全访问这种悲观策略。 无锁是一种相对乐观的策略，他认为对临界区的访问不存在冲突，无需阻塞。

2. 无锁的时候多线程共同访问临界区的资源怎么办？

   无锁就是通过CAS比较交换技术来鉴别线程冲突。一旦检测到有异常就会重试当前操作，直到没有异常为止。

CAS的原理

1. 具体内容

   一个CAS方法包含三个参数CAS(V,E,N)。V表示要更新的变量，E表示预期的值，N表示新值。只有当V的值等于E时，才会将V的值修改为N。如果V的值不等于E，说明已经被其他线程修改了，当前线程可以放弃此操作，也可以再次尝试次操作直至修改成功。基于这样的算法，CAS操作即使没有锁，也可以发现其他线程对当前线程的干扰（临界区值的修改），并进行恰当的处理

   说人话就是：CAS算法会在一个线程视图操作临界区变量的值的时候判断一下要修改的值是否是我们需要的预期的值，即它是否被其他的线程给修改了。 否则就重试，直至成功。 CAS算法是cpu指令级的原子操作(避免了异常)。

   **CAS 的含义是“我认为原有的值应该是什么，如果是，则将原有的值更新为新值，否则不做修改，并告诉我原来的值是多少”**。


**cas**

 当多个线程同时使用CAS操作一个变量时，只有一个会胜出，并成功更新。 其余都失败，失败的不会被挂起，仅被告知失败，并允许再次尝试，和放弃操作。cas只是由一条CPU指令完成，是指令级的操作。

# 6. 多线程工具类

都是synchronized关键字的升级版

## java 线程的中断机制

什么是中断？ 就是讲线程==从阻塞等待状态中唤醒==，并做出相应的响应处理。

有时想让主线程启动的一个子线程结束运行，我们就需要让这个子线程中断，不再继续执行。线程是有中断机制的，我们可以对每个线程进行中断标记，注意只是标记，中断与否还是虚拟机自己的事情，虚拟机自己家的事情，我们也就说说，不能实际操作控制他家

### 中断时一种协作机制

中断时一种协作机制，当一个线程需要中断另一个线程的时候，只是一种通知和商量，告诉被中断线程，请你中断操作。 他不是要求线程立即做中断操作，他只是设置了中断标志，线程检测中断标志后可以，可以在自己方便的时候中断线程，中断操作是被中断线程自己做的逻辑，所以他有很大的主动性，他可以释放掉一些资源后才中断，也可以什么都不做

### 中断状态

每个Java线程都维护着一个Boolean interrupted属性的中断状态值。（true 表示需要进行中断）

| public static boolean**interrupted**() | 这个方法是用来清除中断状态的值得，清除成功返回true，反之false |
| :------------------------------------: | ------------------------------------------------------------ |
|   **public boolean isInterrupted()**   | 判断线程是否已经中断，不对中断值做任何操作                   |
|      public void **interrupt**()       | 中断线程， 将interrupted 设置为true                          |

 线程调用了 Interrupt()并不会立即的就停止线程，它只是设置了线程的阻塞状态是true，当线程在调用阻塞方法的时候，阻塞方法轮询interrupted，检测是否中断，如果为true，就抛出InterruptedException。

### 中断的处理

作为一种协作机制，中断机制不强求中断线程一定要在某个点进行出路。

在JDK中，很多阻塞方法的声明中有抛出InterruptedException异常，这暗示该方法是可中断的，这些方法会检测当前线程是否被中断，如果是，则立刻结束阻塞方法，并抛出InterruptedException异常

程序捕获到这些可中断的阻塞方法抛出的InterruptedException或检测到中断后，该如何处理：

1. 如果是可中断的阻塞方法抛出InterruptedException，可以继续的往上层抛异常，由上层调用异常。
2. 如果是检测到中断，则可以清除中断并抛出中断异常使当前方法也成为一个可中断的方法
3. 如果 在当前方法中不方便抛出异常这时就可以捕获可中断方法的InterruptedException并通过Thread.currentThread.interrupt()来重新设置中断状态

由上面可以在抛出InterruptedException之前都是清除了中断标志了的。这是因为抛出InterruptedException就是已经对检测到中断标志做了一次处理了。已经处理过了一次，标志也就无效了。 如果需要再做一次处理就需要重新设置一次标志位。

==这里抛出异常使为了让线程从阻塞的状态清醒过来，且抛出中断异常后中断标志就清除了==。

设置标志位只是为引起中断线程的注意，被中断线程可以决定如何应对中断



·

## AQS(AbstractQueuedSynchronizer)

AQS）作为java.util.concurrent包的基础，它提供了一套完整的同步编程框架，开发人员只需要实现其中几个简单的方法就能自由的使用诸如独占，共享，条件队列等多种同步模式。我们常用的比如ReentrantLock，CountDownLatch等等基础类库都是基于AQS实现的

AQS通过内部实现的FIFO等待队列来完成资源获取线程的等待工作，如果当前线程获取资源失败，AQS则会将当前线程以及等待状态等信息构造成一个Node结构的节点，并将其加入等待队列中，同时会阻塞当前线程；当其它获取到资源的线程释放持有的资源时，则会把等待队列节点中的线程唤醒，使其再次尝试获取对应资源。

内部定义的Node**类的代码**

~~~java
static final class Node {
        //声明共享模式下的等待节点
        static final Node SHARED = new Node();
        //声明独占模式下的等待节点
        static final Node EXCLUSIVE = null;

        //waitStatus的一常量值，表示线程已取消
        static final int CANCELLED =  1;
        //waitStatus的一常量值，表示后继线程需要取消挂起
        static final int SIGNAL    = -1;
        //waitStatus的一常量值，表示线程正在等待条件
        static final int CONDITION = -2;
        //waitStatus的一常量值，表示下一个acquireShared应无条件传播
        static final int PROPAGATE = -3;

        //waitStatus,其值只能为CANCELLED、SIGNAL、CONDITION、PROPAGATE或0
        //初始值为0
        volatile int waitStatus;

        //前驱节点
        volatile Node prev;

        //后继节点
        volatile Node next;

        //当前节点的线程，在节点初始化时赋值，使用后为null
        volatile Thread thread;

        //下一个等待节点
        Node nextWaiter;

        Node() { 
        }

        Node(Thread thread, Node mode) {     // Used by addWaiter
            this.nextWaiter = mode;
            this.thread = thread;
        }

        Node(Thread thread, int waitStatus) { // Used by Condition
            this.waitStatus = waitStatus;
            this.thread = thread;
        }
    }
~~~

![img](https://images2018.cnblogs.com/blog/1400011/201805/1400011-20180513211820725-389872507.png)

AbstractQueuedSynchronizer的三个重要属性

~~~java
  //等待队列的头结点
    private transient volatile Node head;
    //等待队列的尾节点
    private transient volatile Node tail;
    //同步状态，这个很重要
    private volatile int state;
~~~

可以得到同步队列的基本结构：

![img](https://images2018.cnblogs.com/blog/1400011/201805/1400011-20180513211830066-167429002.png)

AbstractQueuedSynchronizer类中其它方法主要是用于插入节点、释放节点

插入节点过程如下图所示：

![img](https://images2018.cnblogs.com/blog/1400011/201805/1400011-20180513211836170-1249096077.png)

释放头结点过程如下图所示：

![img](https://images2018.cnblogs.com/blog/1400011/201805/1400011-20180513211842294-1124199004.png)

AQS的实现依赖的是由Node节点构建的内部先进先出的双向链表队列。

**线程获取和释放锁的本质是去修改AQS内部代表同步变量的值 state  1 代表线程获取了锁 0带表线程未获取锁**

AQS 中维护着两个队列，一个同步队列（获取锁失败暂时阻塞），一个等待队列(因为某些条件主动调用await()进行阻塞，必须要被主动唤醒)。

### 

### 深入浅出AQS之独占锁模式

AQS独占锁的执行逻辑:

**获取锁的过程：**

1. 当线程调用acquire()申请获取锁资源，如果成功，则进入临界区。
2. 当获取锁失败时，则进入一个FIFO等待队列，然后被挂起等待唤醒。
3. 当队列中的等待线程被唤醒以后就重新尝试获取锁资源，如果成功则进入临界区，否则继续挂起等待。

释放锁过程：

1. 当线程调用release()进行锁资源释放时，如果没有其他线程在等待锁资源，则释放完成。
2. 如果队列中有其他等待锁资源的线程需要唤醒，则唤醒队列中的第一个等待节点（先入先出）

~~~java
   public final void acquire(int arg) {
        if (!tryAcquire(arg) &&
            acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
            selfInterrupt();
    }
首先是调用开发人员自己实现的tryAcquire() 方法尝试获取锁资源，如果成功则整个acquire()方法执行完毕，即当前线程获得锁资源，可以进入临界区。
如果获取锁失败，则开始进入后面的逻辑，加入等待队列

~~~

首先是addWaiter(Node.EXCLUSIVE)方法 : 这里是独占锁模式，所以节点模式为Node.EXCLUSIVE

线程要包装为Node对象的主要原因，除了用Node构造供虚拟队列外，还用Node包装了各种线程状态

SIGNAL(-1) ：线程的后继线程正/已被阻塞，当该线程release或cancel时要重新这个后继线程(unpark)

CANCELLED(1)：因为超时或中断，该线程已经被取消

CONDITION(-2)：表明该线程被处于条件队列，就是因为调用了Condition.await而被阻塞

PROPAGATE(-3)：传播共享锁

0：0代表无状态

(https://blog.csdn.net/chen77716/article/details/6641477)

~~~java
//注意：该入队方法的返回值就是新创建的节点
    private Node addWaiter(Node mode) {
        //基于当前线程，节点类型（Node.EXCLUSIVE）创建新的节点
        //由于这里是独占模式，因此节点类型就是Node.EXCLUSIVE
        Node node = new Node(Thread.currentThread(), mode);
        Node pred = tail;
        //这里为了提搞性能，首先执行一次快速入队操作，即直接尝试将新节点加入队尾
        if (pred != null) {
            node.prev = pred;
            //这里根据CAS的逻辑，即使并发操作也只能有一个线程成功并返回，其余的都要执行后面的入队操作。即enq()方法
            if (compareAndSetTail(pred, node)) {
                pred.next = node;
                return node;
            }
        }
        enq(node);
        return node;
    }

    //完整的入队操作
    private Node enq(final Node node) {
        for (;;) {
            Node t = tail; // tail 尾节点， t就是尾节点
            //如果队列还没有初始化，则进行初始化，即创建一个空的头节点
            if (t == null) {  
                //同样是CAS，只有一个线程可以初始化“头结点”成功，其余的都要重复执行循环体
                if (compareAndSetHead(new Node())) 
                    tail = head; //初始化的时候头结点和尾节点指向同一个节点
            } else {
                //新创建的节点指向队列尾节点，毫无疑问并发情况下这里会有多个新创建的节点指向队列尾节点
                node.prev = t; //简单来说就是将新节点放在原来链表的尾节点后
                //基于这一步的CAS，不管前一步有多少新节点都指向了尾节点，这一步只有一个能真正入队成功，其他的都必须重新执行循环体
                if (compareAndSetTail(t, node)) { 
                  //这里只是将尾指针指向新节点，将新节点变成尾节点并没有修改t指向的值。t只是作为一个比较值
                    t.next = node;  
                    //该循环体唯一退出的操作，就是入队成功（否则就要无限重试）
                    return t;
                }
            }
        }
    }
========================================================================
一： 初始化队列的触发条件就是当前已经有线程占有了锁资源，因此上面创建的空的头节点可以认为就是当前占有锁资源的节点（虽然它并没有设置任何属性）。
二： 注意整个代码是处在一个死循环中，知道入队成功。如果失败了就会不断进行重试。 
三： 代码中使用无线循环的原因, 因为这是无锁的，在高并发的情况下，可能存在多个线程同时执行这个方法，但是无锁CAS只会允许一个线程执行成功，所以使用了无线循环让其他线程的任务在下次循环过程中能被执行成功，如果执行成功就会退出循环。
~~~



经过上面的操作，我们申请获取锁的线程已经成功加入了等待队列，那么节点接下来就要被挂起，等待被唤醒

（这里挂起就是线程被中断     Thread.currentThread().interrupt(); ）

~~~java
final boolean acquireQueued(final Node node, int arg) { //node就是刚入队的包含当前线程信息的节点
        //锁资源获取失败标记位
        boolean failed = true;
        try {
            //等待线程被中断标记位
            boolean interrupted = false;
            //这个循环体执行的时机包括新节点入队和队列中等待节点被唤醒两个地方
            for (;;) {
                //获取当前节点的前置节点
                final Node p = node.predecessor();
                //如果前置节点就是头结点，则尝试获取锁资源
                if (p == head && tryAcquire(arg)) {
                    //当前节点获得锁资源以后设置为头节点，这里继续理解我上面说的那句话
                    //头结点就表示当前正占有锁资源的节点
                    setHead(node);
                    p.next = null; //帮助GC
                    //表示锁资源成功获取，因此把failed置为false
                    failed = false;
                    //返回中断标记，表示当前节点是被正常唤醒还是被中断唤醒
                    return interrupted;
                }
                如果没有获取锁成功，则进入挂起逻辑
                if (shouldParkAfterFailedAcquire(p, node) && //先判断线程获取锁失败后是否应该被挂起
                    parkAndCheckInterrupt()) //如果应该被挂起就执行挂起逻辑并检查中断
                    interrupted = true;
            }
        } finally {
            //最后会分析获取锁失败处理逻辑
            if (failed)
                cancelAcquire(node);
        }
    }
~~~

~~~java
//这个方法就是判断该线程是否应该被挂起
private static boolean shouldParkAfterFailedAcquire(Node pred, Node node) {//node是当前线程的节点，pred是它的前置节点
        //获取前置节点的waitStatus
        int ws = pred.waitStatus;
        if (ws == Node.SIGNAL)
       //如果前置节点的waitStatus是Node.SIGNAL则返回true，然后会执行parkAndCheckInterrupt()方法进行挂起
       //这里有点难以理解，但是线程在定义节点--- SIGNAL(-1) ：线程的后继线程正/已被阻塞(下一个线程需要被挂起)
            return true;
        if (ws > 0) {
            //由waitStatus的几个取值可以判断这里表示前置节点被取消
            do {
                node.prev = pred = pred.prev;
            } while (pred.waitStatus > 0);
            //这里我们由当前节点的前置节点开始，一直向前找最近的一个没有被取消的节点
            //注，由于头结点head是通过new Node()创建，它的waitStatus为0,因此这里不会出现空指针问题，也就是说最多就是找到头节点上面的循环就退出了
            pred.next = node;
        } else {
            //根据waitStatus的取值限定，这里pre的waitStatus的值只能是0或者PROPAGATE(共享锁模式)，那么我们把前置节点的waitStatus设为Node.SIGNAL然后重新进入该方法进行判断
            compareAndSetWaitStatus(pred, ws, Node.SIGNAL);
        }
        return false;
    }

---------------------------------------------------
    private final boolean parkAndCheckInterrupt() {
        LockSupport.park(this); //挂起
        return Thread.interrupted();
    }

~~~

释放锁的过程：

~~~javascript
public final boolean release(int arg) {
        if (tryRelease(arg)) {
            Node h = head;
            if (h != null && h.waitStatus != 0)
                unparkSuccessor(h);
            return true;
        }
        return false;
    }
tryRelease()方法是用户自定义的释放锁逻辑
如果成功，就判断等待队列中有没有需要被唤醒的节点（waitStatus为0表示没有需要被唤醒的节点,0是无效节点）
---------------------
private void unparkSuccessor(Node node) { //此处的节点是头结点， 头结点是获取了锁的节点，他的下一个节点是等待中的节点，也就是需要被唤醒的节点
        int ws = node.waitStatus;
        if (ws < 0)
            //把标记为设置为0，表示唤醒操作已经开始进行，提高并发环境下性能
            compareAndSetWaitStatus(node, ws, 0);

        Node s = node.next;
        //如果当前节点的后继节点为null，或者已经被取消
        if (s == null || s.waitStatus > 0) {
            s = null;  //这里s可能不为null，只是一个废弃节点
            //注意这个循环没有break，也就是说它是从后往前找，一直找到离当前节点最近的一个等待唤醒的节点
            for (Node t = tail; t != null && t != node; t = t.prev)
                if (t.waitStatus <= 0)
                    s = t;
        }
        //执行唤醒操作
        if (s != null)
            LockSupport.unpark(s.thread);
    }


~~~

### 深入浅出AQS之共享锁模式

**执行过程**

获取锁的过程：

1. 当线程调用acquireShared()申请获取锁资源时，如果成功，则进入临界区。
2. 当获取锁失败时，则创建一个共享类型的节点并进入一个FIFO等待队列，然后被挂起等待唤醒。
3. 当队列中的等待线程被唤醒以后就重新尝试获取锁资源，如果成功则**唤醒后面还在等待的共享节点并把该唤醒事件传递下去，即会依次唤醒在该节点后面的所有共享节点**，然后进入临界区，否则继续挂起等待。

释放锁过程：

1. 当线程调用releaseShared()进行锁资源释放时，如果释放成功，则唤醒队列中等待的节点，如果有的话。

~~~java
获取共享锁的方法acquireShared()
public final void acquireShared(int arg) {
        //尝试获取共享锁，返回值小于0表示获取失败
        if (tryAcquireShared(arg) < 0)
            //执行获取锁失败以后的方法
            doAcquireShared(arg);
 }

----------------------------------------------------
    private void doAcquireShared(int arg) { //获取锁失败后挂起
        //添加等待节点的方法跟独占锁一样，唯一区别就是节点类型变为了共享型，不再赘述
        final Node node = addWaiter(Node.SHARED);
        boolean failed = true;
        try {
            boolean interrupted = false;
            for (;;) {
                final Node p = node.predecessor();
                //表示前面的节点已经获取到锁，自己会尝试获取锁
                if (p == head) {
                    int r = tryAcquireShared(arg);
                    //注意上面说的， 等于0表示不用唤醒后继节点，大于0，获取锁成功，唤醒后继节点
                    if (r >= 0) {
                        //这里是重点，获取到锁以后的唤醒操作，后面详细说
                        setHeadAndPropagate(node, r);
                        p.next = null;
                        //如果是因为中断醒来则设置中断标记位
                        if (interrupted)
                            selfInterrupt();
                        failed = false;
                        return;
                    }
                }
                //挂起逻辑跟独占锁一样，不再赘述
                if (shouldParkAfterFailedAcquire(p, node) &&
                    parkAndCheckInterrupt())
                    interrupted = true;
            }
        } finally {
            //获取失败的取消逻辑跟独占锁一样，不再赘述
            if (failed)
                cancelAcquire(node);
        }
    }
~~~



### 深入浅出AQS之条件队列

## 1.ReentrantLock

ReentrantLock（重入锁）是synchronized的升级版，但是两者在性能上并无太大的差异，只是ReentantLock具有更完善的功能：可中断响应、锁申请等待限时、公平锁等。 如果并发量较少就不是一定需要使用ReentrantLock。

~~~java
public static ReentrantLock lock = new ReentrantLock();
public void run() {
        for (int j = 0; j < 10000; j++) {
            lock.lock();  // 看这里就可以
            //lock.lock(); ①
            try {
                i++;
            } finally { //try --finnally这是一种标准写法
                lock.unlock(); // 看这里就可以
                //lock.unlock();②
            }
        }
    }
使用重入锁加锁是一种显式的操作。其对逻辑控制的灵活性远大于synchronized关键字，但是他并不像synchronized关键字那样会自己unLock。 ReentrantLock 需要自己显式的来解锁。并且加锁与解锁的次数要一样，这里就引出了“重”入的概念。重入锁对一个线程可以加几次锁(但是需要注意的是： 加锁次数需要和解锁次数一样，加了多少道锁就需要解多少锁)。

~~~



### 中断

相对于synchronized关键字来说，要么获取到锁执行到底，要么持续等待，中间不能中断执行。但是如果加锁之后这个线程陷入死循环，这时导致其他的线程无法获取资源而陷入死锁，这时就需要中断线程，synchronized关键字是无法解决这种情况的。而重入锁的中断响应就能解决这种情况。譬如一个正在等待锁的线程被“告知”无需再继续等待下去，停止工作。

~~~java
public class KillDeadlock implements Runnable{
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;

    public KillDeadlock(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            if (lock == 1) {
                //t1线程的代码
                lock1.lockInterruptibly();  // 以可以响应中断的方式加锁
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {}
                lock2.lockInterruptibly();
            } else {
                 //t2线程的代码
                lock2.lockInterruptibly();  // 以可以响应中断的方式加锁
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {}
                lock1.lockInterruptibly();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock1.isHeldByCurrentThread()) lock1.unlock();  // 注意判断方式
            if (lock2.isHeldByCurrentThread()) lock2.unlock();
            System.err.println(Thread.currentThread().getId() + "退出！");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        KillDeadlock deadLock1 = new KillDeadlock(1);
        KillDeadlock deadLock2 = new KillDeadlock(2);
        Thread t1 = new Thread(deadLock1);
        Thread t2 = new Thread(deadLock2);
        t1.start();t2.start();
        Thread.sleep(1000);
        t2.interrupt(); // ③
    }
}

---------------------
 注意： 
1：lock1.lockInterruptibly(); 只有加这种可中断锁才能监听到线程中断信号，跑出InterruptedException异常，进而结束线程。
2： 这里需要注意的事，重入锁可以加多次锁是有条件的。
重入锁ReentrantLock，顾名思义，就是支持重进入的锁，它表示该锁能够支持一个线程对资源的重复加锁。
一个线程中能对同一把锁加多次，但是如果要加其他的锁，则其他的锁则应该处于解锁(未加锁状态)
------------------------------------------------------------------------
    
    源码解释：
       final void lock() {
            if (compareAndSetState(0, 1)) //初始加锁，锁的状态是0，加了锁之后修改为1
                setExclusiveOwnerThread(Thread.currentThread()); //取锁成功，当前线程加锁， setExclusiveOwnerThread 排他独有的线程，多个线程竞争，只有一个线程能竞争到锁。
            else
                acquire(1); //锁已经被占用，取锁失败，请求标志加一
        }
----
    public final void acquire(int arg) {
        if (!tryAcquire(arg) &&
            acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
            selfInterrupt();
    }
// tryAcquire(arg) 再去试着请求一次锁，万一锁被释放了，就加锁，不执行后面的代码
   protected final boolean tryAcquire(int acquires) {
            return nonfairTryAcquire(acquires);
        }
      final boolean nonfairTryAcquire(int acquires) { // acquires=1
            final Thread current = Thread.currentThread();
            int c = getState(); //获取加锁的状态
            if (c == 0) { //c=0 ，锁的资源已经被释放
                if (compareAndSetState(0, acquires)) { //重新尝试加锁，如果成功就返回
                    setExclusiveOwnerThread(current); 
                    return true;
                }
            }
            else if (current == getExclusiveOwnerThread()) { 
            //锁未被释放就去判断一下被锁的线程是否是当前线程，如果是同一个线程将申请标志加1，这个标志随着重入次数而递增，在解锁的时候回不短的递减，直至解锁为0后才能完全释放完锁。这里也解释了上面的问题重入锁限制的问题。
                int nextc = c + acquires;
                if (nextc < 0) // overflow
                    throw new Error("Maximum lock count exceeded");
                setState(nextc);
                return true;
            }
            return false; //如果不是当前线程且未获取到锁则返回false
        }
//addWaiter(Node.EXCLUSIVE)： 此处Node.EXCLUSIVE是null,      
    private Node addWaiter(Node mode) {
        Node node = new Node(Thread.currentThread(), mode);
        // Try the fast path of enq; backup to full enq on failure
        Node pred = tail;
        if (pred != null) {
            node.prev = pred;
            if (compareAndSetTail(pred, node)) {
                pred.next = node;
                return node;
            }
        }
        enq(node);
        return node; 
    }//将当前线程构建成一个双向链表的节点并返回
  //acquireQueued(addWaiter(Node.EXCLUSIVE), arg) ==acquireQueued(Node, 1) 
  //acquireQueued 请求排队方法
      final boolean acquireQueued(final Node node, int arg) {
        boolean failed = true;
        try {
            boolean interrupted = false;
            for (;;) {
                final Node p = node.predecessor();
                if (p == head && tryAcquire(arg)) { 
  //当前节点是head节点的next节点且尝试请求加锁成功，则释放当前节点
                    setHead(node);
                    p.next = null; // help GC
                    failed = false;
                    return interrupted;
                }
                if (shouldParkAfterFailedAcquire(p, node) &&
                    parkAndCheckInterrupt())
                    interrupted = true;
            }
        } finally {
            if (failed)
                cancelAcquire(node);
        }
    }
//此处是做Node节点线程的自旋过程，自旋过程主要检查当前节点是不是head节点的next节点，如果是，则尝试获取锁，如果获取成功，那么释放当前节点，同时返回。  cancelAcquire(node) 和 selfInterrupt();

~~~

### 锁申请等待限时

可以使用 tryLock()或者tryLock(long timeout, TimeUtil unit) 方法进行一次限时的锁等待。

在指定时长内获取到锁则继续执行，如果等待指定时长后还没有获取到锁则返回false

~~~java
public void run() {
        try {
            if (lock.tryLock(1, TimeUnit.SECONDS)) { // 等待1秒
                Thread.sleep(2000);  //休眠2秒
            } else {
                System.err.println(Thread.currentThread().getName() + "获取锁失败！");
            }
        } catch (Exception e) {
            if (lock.isHeldByCurrentThread()) lock.unlock();
        }
    }

~~~

### 公平锁

所谓公平锁，就是按照时间先后顺序，使先等待的线程先得到锁，而且，公平锁不会产生饥饿锁，也就是只要排队等待，最终能等待到获取锁的机会。使用重入锁（默认是非公平锁）创建公平锁：（我们默认使用的就不公平锁）

~~~java
public ReentrantLock(boolean fair) {
    sync = fair ? new FairSync() : new NonfairSync();
}
~~~



## 2.condition 

通常在开发并发程序的时候，会碰到需要停止正在执行业务A，来执行另一个业务B，当业务B执行完成后业务A继续执行。ReentrantLock通过Condtion等待/唤醒这样的机制.

condition 等价于synchronized关键字中使用的Object的wait()和notify方法,它和ReentrantLock结合起来用于线程的等待和唤醒，但是它更加的灵活，与Object下的wait() 只能有一个等待队列不同，Condition可以实现由多个条件下的等待队列，condition顾名思义可知，在不同的条件下可以创建不同的等待队列，调用一次lock.newCondition()就为lock下生成一个等待队列。

基本内容：

1.Condition提供了await()方法将当前线程阻塞，并提供signal()方法支持另外一个线程将已经阻塞的线程唤醒。

2.Condition需要结合Lock使用

3 线程调用await()方法前必须获取锁，调用await()方法时，将线程构造成节点加入等待队列，同时释放锁，并挂起当前线程

4 其他线程调用signal()方法前也必须获取锁，当执行signal()方法时将等待队列的节点移入到同步队列，当线程退出临界区释放锁的时候，唤醒同步队列的首个节点

![img](https://upload-images.jianshu.io/upload_images/5507455-37635d0723174712.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/923/format/webp)



Condition是一个接口，它主要是由awiat和singal方法组成，awiat方法是放弃自身锁，进入阻塞状态，等待信号进行唤醒，singal是唤醒线程，让线程去重新竞争锁。它和Object的wait和notify方法是一样的。

~~~java
        //   头节点 
        private transient Node firstWaiter;
        //   尾节点
        private transient Node lastWaiter;
Condition内部维护了一个由线程封装的Node节点组成的单向链表(等待队列)，这个链表的作用是存放等待signal信号的线程。
~~~

Condition 的await()方法： 他做两件事，将当前线程加入到等待队列，和完全地解开加在线程上的锁。线程放弃共享资源的所有权(且线程暂时不挣抢资源)，进入等待	

~~~java
       public final void await() throws InterruptedException {
            if (Thread.interrupted())
                throw new InterruptedException();
            Node node = addConditionWaiter(); //将当前线程封装为线程等待单链表的尾节点
            int savedState = fullyRelease(node); //完全的释放当前线程占有的锁
            int interruptMode = 0;
            while (!isOnSyncQueue(node)) { //判断当前线程是否是在可以挣抢资源的同步队列中
                LockSupport.park(this); //挂起当前线程
                if ((interruptMode = checkInterruptWhileWaiting(node)) != 0)
                    break;
            }
            if (acquireQueued(node, savedState) && interruptMode != THROW_IE)
                interruptMode = REINTERRUPT;
            if (node.nextWaiter != null) // clean up if cancelled
                unlinkCancelledWaiters();
            if (interruptMode != 0)
                reportInterruptAfterWait(interruptMode);
        }
~~~

相比较synchronize的wait()和notify()/notifAll()的机制而言，Condition具有更高的灵活性，这个很关键。Conditon可以实现多路通知和选择性通知。当使用notify()/notifAll()时，JVM时随机通知线程的，具有很大的不可控性，所以建议使用Condition。Condition使用起来也非常方便，只需要注册到ReentrantLock下面即可。

~~~java
public class MyService {

    // 实例化一个ReentrantLock对象
    private ReentrantLock lock = new ReentrantLock();
    // 为线程A注册一个Condition
    public Condition conditionA = lock.newCondition();
    // 为线程B注册一个Condition
    public Condition conditionB = lock.newCondition();

    public void awaitA() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "进入了awaitA方法");
            long timeBefore = System.currentTimeMillis();
            // 执行conditionA等待
            conditionA.await();
            long timeAfter = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName()+"被唤醒");
            System.out.println(Thread.currentThread().getName() + "等待了: " + (timeAfter - timeBefore)/1000+"s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void awaitB() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "进入了awaitB方法");
            long timeBefore = System.currentTimeMillis();
            // 执行conditionB等待
            conditionB.await();
            long timeAfter = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName()+"被唤醒");
            System.out.println(Thread.currentThread().getName() + "等待了: " + (timeAfter - timeBefore)/1000+"s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void signallA() {
        try {
            lock.lock();
            System.out.println("启动唤醒程序");
            // 唤醒所有注册conditionA的线程
            conditionA.signalAll();
        } finally {
            lock.unlock();
        }
    }
    
    public void signallB() {
        try {
            lock.lock();
            System.out.println("启动唤醒程序");
            // 唤醒所有注册conditionA的线程
            conditionB.signalAll();
        } finally {
            lock.unlock();
        }
    }
}

================================================
 注意：
 分别实例化了两个Condition对象，都是使用同一个lock注册。注意conditionA对象的等待和唤醒只对使用了conditionA的线程有用，同理conditionB对象的等待和唤醒只对使用了conditionB的线程有用。
~~~



## 3.semaphore (信号量)

Semaphore也叫信号量，在JDK1.5被引入，可以**用来控制同时访问特定资源的线程数量**，通过协调各个线程，以保证合理的使用资源。

Semaphore内部维护了一组虚拟的许可，许可的数量可以通过构造函数的参数指定

- 访问特定资源前，必须使用acquire方法获得许可，如果许可数量为0，该线程则一直阻塞，直到有可用许可。
- 访问资源后，使用release释放许可。

Semaphore管理一系列许可证。

Semaphore和ReentrantLock类似，获取许可有公平策略和非公平许可策略，默认情况下使用非公平策略。

每个acquire方法阻塞，直到有一个许可证可以获得然后拿走一个许可证；每个release方法增加一个许可证，这可能会释放一个阻塞的acquire方法。然而，其实并没有实际的许可证这个对象，Semaphore只是维持了一个可获得许可证的数量。 

### 应用场景 1

Semaphore经常用于限制获取某种资源的线程数量。下面举个例子，比如说操场上有5个跑道，一个跑道一次只能有一个学生在上面跑步，一旦所有跑道在使用，那么后面的学生就需要等待，直到有一个学生不跑了。

~~~java
使用实例：
class SDTask extends Thread
{
	private Semaphore s;
	public SDTask(Semaphore s,String name)
	{
		super(name);
		this.s = s;
	}
	public void run()
	{
		try
		{
			System.out.println(Thread.currentThread().getName()+" 尝试获取3个信号!!!");
			s.acquire(3);
			System.out.println(Thread.currentThread().getName()+" 获取了3个信号!!!");
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}finally
		{
			System.out.println(Thread.currentThread().getName()+" 释放了3个信号!!!");
			s.release(3);
		}
	}
}
public class SemaphoreDemo2
{
	public static void main(String[] args)
	{
		Semaphore s = new Semaphore(7);
		for(int i=0; i<3; i++)
		{
			new SDTask(s,"thread"+i).start();;
		}
	}
}

~~~

Semaphore是一个计数信号量，采用的是共享锁的方式来控制

release(int)用来释放信号量，将信号量数量返回给Semaphore

### 应用场景 2

1 Semaphore可以用来做流量分流，特别是对公共资源有限的场景，比如数据库连接。

假设有这个的需求，读取几万个文件的数据到数据库中，由于文件读取是IO密集型任务，可以启动几十个线程并发读取，但是数据库连接数只有10个，这时就必须控制最多只有10个线程能够拿到数据库连接进行操作。这个时候，就可以使用Semaphore做流量控制。

~~~java
public class SemaphoreTest {
    private static final int COUNT = 40;
    private static Executor executor = Executors.newFixedThreadPool(COUNT);
    private static Semaphore semaphore = new Semaphore(10);
    public static void main(String[] args) {
        for (int i=0; i< COUNT; i++) {
            executor.execute(new ThreadTest.Task());
        }
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            try {
                //读取文件操作
                semaphore.acquire();
                // 存数据过程
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
            }
        }
    }
}
~~~



## 4.LockSupport 

LockSupport 实际做的是情和Object的wait和notify/notifyAll是类似的。但是wait和notify有个重要的限制是**它们必须放在同步块中执行**，而LockSupport可以单独执行。其次是LockSupport具有较高的灵活性，wait和notify的使用具有顺序性，需要先wait再notify，如果先notify在调用wait就会一直阻塞。 换做LockSupportt就支持线程先调用unpark后，再调用park而不被阻塞。

**总结一下，LockSupport比Object的wait/notify有两大优势**：

①LockSupport不需要在同步代码块里 。所以线程间也不需要维护一个共享的同步对象了，实现了线程间的解耦。

②unpark函数可以先于park调用，所以不需要担心线程间的执行的先后顺序。



LockSupport 和 CAS 是Java并发包中很多并发工具控制机制的基础，它们底层其实都是依赖Unsafe实现是用来创建锁和其他同步类的基本**线程阻塞**  "原语"。LockSupport的pack挂起线程，unpack唤醒被挂起的线程

 LockSupport 很类似于二元信号量(只有1个许可证可供使用， 信号量(0,1),默认是0.)，如果这个许可还没有被占用，当前线程获取许可并继 续 执行；如果许可已经被占用，当前线 程阻塞，等待获取许可。默认许可是被占用的，所以线程占用了许可能正常运行。

~~~java
LockSupport.park();
运行该代码，可以发现线程一直处于阻塞状态。因为 许可默认是被占用的，信号量默认是0 ，调用park()时获取不到许可，所以进入阻塞状态。
------------------
Thread thread = Thread.currentThread();
     LockSupport.unpark(thread);//释放许可，这里信号量+1 变成1.
     LockSupport.park();// 获取许可，这时信号量是1，所以线程能正常运行，不会阻塞。他会消耗掉一个信号量，这个方法执行后信号量会变成0，所以Locksupport是不支持重入的
     System.out.println("b");
这里主线程是能够正常的运行的

--------------------
public static void t2() throws Exception
{
	Thread t = new Thread(new Runnable()
	{
		public void run()
		{
			.................
		//等待或许许可
			LockSupport.park();
		    System.out.println("thread over." + ......);
		}
	});
	t.start();
	Thread.sleep(2000);
	// 中断线程
	t.interrupt();
	System.out.println("main over");
}

---------------------
最终线程会打印出thread over.true。这说明 线程如果因为调用park而阻塞的话，能够响应中断请求(中断状态被设置成true)，但是不会抛出InterruptedException 。
~~~

**源码分析**

park主要功能：

如果许可存在，那么将这个许可使”用掉“，并且立即返回。如果许可不存在，那么挂起当前线程，直到以下任意一件事情发生：

~~~java
public static void park(Object blocker) {
        //获取当前线程
        Thread t = Thread.currentThread();
        //设置线程的blocker对象
        setBlocker(t, blocker);
        //通过UNSAFE调用，挂起线程
        UNSAFE.park(false, 0L);  // park（） 无参park也是调用的这句代码
        //挂起的线程被唤醒以后，需要将阻塞的Blocker清理掉。
        setBlocker(t, null);
    }
~~~



## 5.ReadWriteLock(读写锁)

ReadWriteLock管理一组锁，一个是只读的锁，一个是写锁。读锁(共享锁)可以在没有写锁的时候被多个线程同时持有，写锁(独占锁)是独占的。 

一个获得了读锁的线程必须能看到前一个释放的写锁所更新的内容。 

读写锁比互斥锁允许对于共享数据更大程度的并发。每次只能有一个写线程，但是同时可以有多个线程并发地读数据。ReadWriteLock适用于读多写少的并发情况。 

~~~java
public class ReadAndWriteLock {
	ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	public void get(Thread thread) {
		lock.readLock().lock();
		try{
			System.out.println("start time:"+System.currentTimeMillis());
			for(int i=0; i<5; i++){
			  。。。。。。。。。。。
				System.out.println(thread.getName() + ":正在进行读操作……");
			}
			System.out.println(thread.getName() + ":读操作完毕！");
			System.out.println("end time:"+System.currentTimeMillis());
		}finally{
			lock.readLock().unlock();
		}
	}
	
	public static void main(String[] args) {
        //开启线程
	}
}
=============================
    验证下读写锁的互斥关系
    public class ReadAndWriteLock {
   ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	public static void main(String[] args) {
		final ReadAndWriteLock lock = new ReadAndWriteLock();
         // 建N个线程，同时读
		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(new Runnable() {
			@Override
			public void run() {
				lock.readFile(Thread.currentThread());
			}
		});
		// 建N个线程，同时写
		ExecutorService service1 = Executors.newCachedThreadPool();
		service1.execute(new Runnable() {
			@Override
			public void run() {
				lock.writeFile(Thread.currentThread());
			}
		});
	}
	// 读操作
	public void readFile(Thread thread){
		lock.readLock().lock();
		boolean readLock = lock.isWriteLocked();
		if(!readLock){
			System.out.println("当前为读锁！");
		}
		try{
			for(int i=0; i<5; i++){
	。。。。。。。。。。。。。。。。。。。。。
				System.out.println(thread.getName() + ":正在进行读操作……");
			}
			System.out.println(thread.getName() + ":读操作完毕！");
		}finally{
         System.out.println("释放读锁！");
			lock.readLock().unlock();
		}
	}
	// 写操作
	public void writeFile(Thread thread){
		lock.writeLock().lock();
		boolean writeLock = lock.isWriteLocked();
		if(writeLock){
			System.out.println("当前为写锁！");
		}
		try{
			for(int i=0; i<5; i++){
		。。。。。。。。。。。。。。。。
				System.out.println(thread.getName() + ":正在进行写操作……");
			}
			System.out.println(thread.getName() + ":写操作完毕！");
		}finally{
         System.out.println("释放写锁！");
			lock.writeLock().unlock();
		}
	}
}

~~~



## 6.CountDownLatch(倒数计时器)

**CountDownLatch是什么？**

CountDownLatch也叫闭锁，在JDK1.5被引入，允许一个或多个线程等待其他线程完成操作后再执行。CountDownLatch是一个同步工具类，协调多个线程之间的同步，或者说起到线程之间的通信。

CountDownLatch能够使一个线程在等待另外一些线程完成各自工作之后，再继续执行。（ReentrantLock +Condition 也可以做到）

CountDownLatch内部会维护一个初始值为线程数量的计数器，主线程执行await方法，如果计数器大于0，则阻塞等待。当一个线程完成任务后，计数器值减1。当计数器为0时，表示所有的线程已经完成任务，等待的主线程被唤醒继续执行。

![img](https://upload-images.jianshu.io/upload_images/2184951-8a570622b8297310.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/353/format/webp)

使用一个计数器进行实现。计数器初始值为线程的数量。当每一个线程完成自己任务后，计数器的值就会减一。当计数器的值为0时，表示所有的线程都已经完成了任务，然后在CountDownLatch上等待的线程就可以恢复执行任务。

**CountDownLatch的用法**

~~~java
class Application {
    private CountDownLatch latch;
    public void startUp() throws Exception {
        latch = new CountDownLatch(2); 
        List<Service> services = new ArrayList<>();
        services.add(new DatabaseCheckerService(latch)); //这里对应上图在服务线程中会进行latch.countDown(); 这样最后才能让latch为0唤醒主线程
        services.add(new HealthCheckService(latch));
        Executor executor = Executors.newFixedThreadPool(services.size());
        for (Service service : services) {
            executor.execute(service);
        }
        latch.await(); //这里对应着上图就是主线程阻塞。 
        System.out.println("all service is start up");
    }
}
~~~



## 7.CyclicBarrier（循环栅栏）

### CyclicBarrier是什么

CyclicBarrier也叫同步屏障，在JDK1.5被引入，可以让一组线程达到一个屏障时被阻塞，直到最后一个线程达到屏障时，所以被阻塞的线程才能继续执行。(相当于为一组线程设置一排栅栏，直到所有线程都到了来开放栅栏通道)、

**构造方法**

1. 默认的构造方法是CyclicBarrier(int parties)，其参数表示屏障拦截的线程数量，**每个线程调用await方法告诉CyclicBarrier已经到达屏障位置**，线程被阻塞。
2. 另外一个构造方法CyclicBarrier(int parties, Runnable barrierAction)，其中barrierAction任务会在所有线程到达屏障后执行。



![img](https:////upload-images.jianshu.io/upload_images/2184951-b972911b7debef14.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/566/format/webp)

### 应用场景

~~~java
public class CyclicBarrierDemo {
    public static final int INIT_SIZE = 4;
    private static CyclicBarrier barrier;
    public static void main(String[] args) {
        System.out.println("开启CyclicBarrier屏障（裁判员就位）");
        //初始化CyclicBarrier
        barrier = new CyclicBarrier(INIT_SIZE, new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName()+"线程通知:所有线程都已经准备好了,CyclicBarrier屏障去除（所有运动员都准备完毕，发信号枪）");
            }
        });
        //开启4个线程，充当运动员
        for (int i=0;i<INIT_SIZE;i++){
            new ThreadDemo().start();
        }

    }

    static class ThreadDemo extends Thread {
        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName()+"线程准备好了,等待CyclicBarrier屏障去除（一名运动员准备好了）");
                barrier.await(); // 通过调用barrier栅栏的等待方法来停止线程，只有栅栏开放了才接着执行下面的方法
                System.out.println(Thread.currentThread().getName()+"线程继续运行（开始跑）");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}

---------------------
    执行结果：
    开启CyclicBarrier屏障（裁判员就位）
Thread-0线程准备好了,等待CyclicBarrier屏障去除（一名运动员准备好了）
Thread-1线程准备好了,等待CyclicBarrier屏障去除（一名运动员准备好了）
Thread-3线程准备好了,等待CyclicBarrier屏障去除（一名运动员准备好了）
Thread-2线程准备好了,等待CyclicBarrier屏障去除（一名运动员准备好了）
Thread-2线程通知:所有线程都已经准备好了,CyclicBarrier屏障去除（所有运动员都准备完毕，发信号枪）
Thread-2线程继续运行（开始跑）
Thread-1线程继续运行（开始跑）
Thread-0线程继续运行（开始跑）
Thread-3线程继续运行（开始跑）

---------------------


~~~

### CyclicBarrier和CountDownLatch区别

| CountDownLatch                                               | CyclicBarrier                                                |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| 减计数方式                                                   | 加计数方式                                                   |
| 计算为0时释放所有等待的线程                                  | 计数达到指定值时释放所有等待线程                             |
| 计数为0时，无法重置                                          | 计数达到指定值时，计数置为0重新开始                          |
| 调用countDown()方法计数减一，调用await()方法只进行阻塞，对计数没任何影响 | 调用await()方法计数加1，若加1后的值不等于构造方法的值，则线程阻塞 |
| 不可重复利用                                                 | 可重复利用                                                   |

**线程在countDown()之后，会继续执行自己的任务，而CyclicBarrier会在所有线程任务结束之后，才会进行后续任务**

# 7 并发容器

java 5.0增加了两种新的容器类型，Queue和BlockingQueue，队列的特点是先进先出，插入和移除操作分别仅能在队列的两端操作。BlockingQueue比Queue多了put、take、offer和poll，他们分别是阻塞插入、阻塞移除、定时阻塞插入和定时阻塞移除。 

事实上，Queue是通过LinkedList实现的（队列的修改操作仅限首尾端，用链表比数组更好），因为它能去掉List的随机访问功能，从而实现了更高效的开发，封装性更好。 

## 1.ConcurrentHashMap

ConcurrentHashMap是HashMap的线程安全版本，ConcurrentSkipListMap是TreeMap的线程安全版本。(hashtable过时)

![ConcurrentMap API](http://www.blogjava.net/images/blogjava_net/xylz/WindowsLiveWriter/JavaConcurrency16part1ConcurrentMap1_10A52/image_thumb_1.png)  除了实现Map接口里面对象的方法外，ConcurrentHashMap还实现了ConcurrentMap里面的四个方法

**V putIfAbsent(K key,V value)**

如果不存在key对应的值，则将key--value加入Map，否则返回key对应的旧值

~~~java
//putIfAbsent()等价于下面的代码
if (!map.containsKey(key)) 
   return map.put(key, value);
else
   return map.get(key);
~~~

**boolean remove(Object key,Object value)**

只有在map中key对应的值是value才能删除对应的key-value	

~~~java
if (map.containsKey(key) && map.get(key).equals(value)) {
   map.remove(key);
   return true;
}
return false;
~~~

**boolean replace(K key,V oldValue,V newValue)**

只有在map中key对应的值是oldValue,才能替换成newValue

~~~java
if (map.containsKey(key) && map.get(key).equals(oldValue)) {
   map.put(key, newValue);
   return true;
}
return false;
~~~

**V replace(K key,V value)**

只有当前键存在的时候更新此键对于的值

~~~java
if (map.containsKey(key)) {
   return map.put(key, value);
}
return null;
~~~

**HashMap原理**

从头设想。要将对象存放在一起，如何设计这个容器。目前只有两条路可以走，一种是采用分格技术（数组的概念），每一个对象存放于一个格子中，这样通过对格子的编号就能取到或者遍历对象；另一种技术就是采用串联的方式（链表的概念），将各个对象串联起来，这需要各个对象至少带有下一个对象的索引（或者指针）。所有的容器的实现其实都是基于这两种方式的，不管是数组还是链表，或者二者俱有。HashMap采用的就是数组的方式。

有了存取对象的容器后还需要以下两个条件才能完成Map所需要的条件。

- 能够快速定位元素：Map的需求就是能够根据一个查询条件尽可能快速得到需要的结果（哈希算法）。
- 能够自动扩充容量：显然对于容器而然，不需要人工的去控制容器的容量是最好的，这样对于外部使用者来说越少知道底部细节越好，不仅使用方便，也越安全。

**HashMap为什么不安全**

HashMap底层维护一个数组，数组中的每一项都是一个Entry

~~~java
transient Entry<K,V>[] table;
~~~

向 HashMap 中所放置的对象实际上是存储在该数组当中； 而Map中的key，value则以Entry的形式存放在数组中

~~~java
static class Entry<K,V> implements Map.Entry<K,V> {
        final K key;
        V value;
        Entry<K,V> next;
        int hash;
~~~

而这个Entry应该放在数组的哪一个位置上（这个位置通常称为位桶或者hash桶，即hash值相同的Entry会放在同一位置，用链表相连），是通过key的hashCode来计算的

通过hash计算出来的值将会使用indexFor方法找到它应该所在的table下标

当两个key通过hashCode计算相同时，则发生了hash冲突(碰撞)，HashMap解决hash冲突的方式是用链表。

当发生hash冲突时，则将存放在数组中的Entry设置为新值的next（这里要注意的是，比如A和B都hash后都映射到下标i中，之前已经有A了，当map.put(B)时，将B放到下标i中，A则为B的next，所以新值存放在数组中，旧值在新值的链表上）

**map.put后的过程：**

当向 HashMap 中 put 一对键值时，它会根据 key的 hashCode 值计算出一个位置， 该位置就是此对象准备往数组中存放的位置。

如果该位置没有对象存在，就将此对象直接放进数组当中；如果该位置已经有对象存在了，则顺着此存在的对象的链开始寻找(为了判断是否值相同，map不允许<key,value>键值对重复)， 如果此链上有对象的话，再去使用 equals方法进行比较，如果对此链上的每个对象的 equals 方法比较都为 false，则将该对象放到数组当中，然后将数组中该位置以前存在的那个对象链接到此对象的后面。

值得注意的是，当key为null时，都放到table[0]中



**ConcurrentHashMap**

ConcurrentHashMap是一个经常被使用的数据结构，相比于Hashtable以及Collections.synchronizedMap()，ConcurrentHashMap在线程安全的基础上提供了更好的写并发能力。

### **ConcurrentHashMap原理分析**

HashTable是一个线程安全的类，它使用synchronized来锁住整张Hash表来实现线程安全，即每次锁住整张表让线程独占。ConcurrentHashMap允许多个修改操作并发进行，其关键在于使用了锁分离技术。它使用了多个锁来控制对hash表的不同部分进行的修改。ConcurrentHashMap内部使用段(Segment)来表示这些不同的部分，每个段其实就是一个小的Hashtable，它们有自己的锁。只要多个修改操作发生在不同的段上，它们就可以并发进行。

有些方法需要跨段，比如size()和containsValue()，它们可能需要锁定整个表而而不仅仅是某个段，这需要按顺序锁定所有段，操作完毕后，又按顺序释放所有段的锁。这里“按顺序”是很重要的，否则极有可能出现死锁。

ConcurrentHashMap使用分段锁技术，将数据分成一段一段的存储，然后给每一段数据配一把锁，当一个线程占用锁访问其中一个段数据的时候，其他段的数据也能被其他线程访问，能够实现真正的并发访问。

ConcurrentHashMap内部分为很多个Segment，每一个Segment拥有一把锁，然后每个Segment（继承ReentrantLock）

~~~java
static final class Segment<K,V> extends ReentrantLock implements Serializable
~~~

Segment继承了ReentrantLock，表明每个segment都可以当做一个锁。

Segment下面包含很多个HashEntry列表数组。对于一个key，需要经过三次（为什么要hash三次下文会详细讲解）hash操作，才能最终定位这个元素的位置，这三次hash分别为：

1. 对于一个key，先进行一次hash操作，得到hash值h1，也即h1 = hash1(key)；
2. 将得到的h1的高几位进行第二次hash，得到hash值h2，也即h2 = hash2(h1高几位)，通过h2能够确定该元素的放在哪个Segment；
3. 将得到的h1进行第三次hash，得到hash值h3，也即h3 = hash3(h1)，通过h3能够确定该元素放置在哪个HashEntry。

ConcurrentHashMap中主要实体类就是三个：ConcurrentHashMap（整个Hash表）,Segment（段），HashEntry（节点）



## 2 BlockingQueue

## 3 ConcurrentLinkQueue



# 8 线程池

线程的创建及销毁需要较大的代价，然而对于业务来说，它关心的只是线程所执行的任务，它希望把更多的cpu用在线程的任务上，而不是辅助性的线程的创建及销毁。 所以线程池应运而生，它主要的任务就是线程的复用，避免每执行一个线程都要开启和销毁一次线程。

线程池创建的核心是要把所有的线程保留下来用以复用，而非一执行完之后就销毁线程

## 基本线程池

## 扩展和增强线程池

## 线程池及其源码的分析

## ForkJoin



# 9 并行设计模式

单例模式

不变模式

Future 模式

生产者消费者模式



















