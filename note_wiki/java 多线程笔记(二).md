# 8 线程池

线程的创建及销毁需要较大的代价，然而对于业务来说，它关心的只是线程所执行的任务，它希望把更多的cpu用在线程的任务上，而不是辅助性的线程的创建及销毁。 所以线程池应运而生，它主要的任务就是线程的复用，避免每执行一个线程都要开启和销毁一次线程。

线程池创建的核心是要把所有的线程保留下来用以复用，而非一执行完之后就销毁线程。

线程池设计的思想就是要把所有的活动线程保留下来(会有一个list 来保存这些线程)

为什么要使用线程池？

1： 降低资源损耗，线程的创建和销毁都有性能消耗

2： 提高响应处理任务的速度。 一个做和几个做，当然是几个人做快，而且还减少了不断换人和喊人的时间，任务可以不需要的等到线程创建就能立即执行。

3：提高线程的可管理性。线程是稀缺资源，如果无限制的创建，不仅会消耗系统资源，还会降低系统的稳定性，使用线程池可以进行统一的分配，调优和监控

线程池（Thread Pool）对于限制应用程序中同一时刻运行的线程数很有用。因为每启动一个新线程都会有相应的性能开销，每个线程都需要给栈分配一些内存等等。



## 基本线程池

![img](https://upload-images.jianshu.io/upload_images/1958298-1442fe24be61cbf2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/752/format/webp)



### 线程池的创建

通过ThreadPoolExecutor来创建一个线程池

~~~java
new ThreadPoolExecutor(corePoolSize, maximumPoolSize,keepAliveTime, milliseconds,runnableTaskQueue, threadFactory,handler);

~~~

![Javaçº¿ç¨æ± ä¸"è¦å·¥ä½æµç¨](http://ifeve.com/wp-content/uploads/2012/12/Java%E7%BA%BF%E7%A8%8B%E6%B1%A0%E4%B8%BB%E8%A6%81%E5%B7%A5%E4%BD%9C%E6%B5%81%E7%A8%8B.jpg)

**源码分析**

~~~java
 public void execute(Runnable command) {
        if (command == null)
            throw new NullPointerException();
        /*
         * Proceed in 3 steps:
         *
         * 1. If fewer than corePoolSize threads are running, try to
         * start a new thread with the given command as its first
         * task.  The call to addWorker atomically checks runState and
         * workerCount, and so prevents false alarms that would add
         * threads when it shouldn't, by returning false.
         *
         * 2. If a task can be successfully queued, then we still need
         * to double-check whether we should have added a thread
         * (because existing ones died since last checking) or that
         * the pool shut down since entry into this method. So we
         * recheck state and if necessary roll back the enqueuing if
         * stopped, or start a new thread if there are none.
         *
         * 3. If we cannot queue task, then we try to add a new
         * thread.  If it fails, we know we are shut down or saturated
         * and so reject the task.
         */
        int c = ctl.get();
        if (workerCountOf(c) < corePoolSize) {
            //如果正在运行的线程数小于核心线程数，尝试用command作为第一个任务开启一个新线程。
            if (addWorker(command, true))
             // addWorker check  runState and workerCount 来检测是否添加成功
                return;
            c = ctl.get();
        }
        if (isRunning(c) && workQueue.offer(command)) {
            int recheck = ctl.get();
            if (! isRunning(recheck) && remove(command))
                reject(command);
            else if (workerCountOf(recheck) == 0)
                addWorker(null, false);
        }
        else if (!addWorker(command, false))
            reject(command);
    }

~~~

 java提供了四种线程池的实现：

​     （1）newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。

    Executors 是一个线程池工厂，他提供了四个方法去构造线程池
    （1）newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
    （2）newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
    （3）newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
    （4）newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
## 扩展和增强线程池

~~~Java
public interface Executor{
    void executor(Runnable command);
}
// 线程池的本质就是一个执行者，他能执行一个Runable的任务
~~~

### 线程池的扩展之回调API

ThreadPoolExecutor 是一个可以扩展的线程池，它提供了 beforeExecute()、afterExecute()和terminated()3个接口

~~~java
 ExcutorServicer es =new ThreadPoolExecutor（.....）{

	protected void beforeExecute(Thread t, Runnable r) {
		System.out.println("准备执行前");
	}
 
	protected void afterExecute(Runnable r, Throwable t) {
        System.out.println("执行完以后");
	}
     protected void terminated() {
        System.out.println("线程池退出");
	}
     
}
~~~

### 拒绝策略

### 自定义ThreadFactory



## 线程池及其源码的分析

## ForkJoin

### 什么是fork/join

Fork/Join 框架：就是在必要的情况下，将一个大任务，进行拆分(fork)成 若干个小任务（拆到不可再拆时），再将一个个的小任务运算的结果进行 join 汇总。

~~~java
public class CaculatorForkAndJoin extends RecursiveTask<Long> {

	/**
	 * 创建serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private long start;
	private long end;
	private static final long THURSHOLD = 10000L; // 临界值

	CaculatorForkAndJoin(long start, long end) {
		this.start = start;
		this.end = end;
	}

	// 重写方法
	@Override
	protected Long compute() {
		long length = end - start;

		if (length <= THURSHOLD) {
			long sum = new Long(0);

			for (long i = start; i <= end; i++) {
				sum += i;
			}

			return sum;
		} else {
			// 中间值
			long mid = (start + end) / 2;
			CaculatorForkAndJoin left = new CaculatorForkAndJoin(start, mid);
			left.fork();// 进行拆分，同时压入现线程队列

			CaculatorForkAndJoin right = new CaculatorForkAndJoin(mid + 1, end);
			right.fork();// 进行拆分，同时压入现线程队列

			return left.join() + right.join();
		}
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		// 创建 线程池
		ForkJoinPool pool = new ForkJoinPool();
		// 创建任务
		CaculatorForkAndJoin task = new CaculatorForkAndJoin(0L, 100000000L);

		// 添加任务到线程池，获得返回值
		long sum = pool.invoke(task);

 		long end = System.currentTimeMillis();
		System.out.println(sum + "spend:" + (end - start));
	}
}
~~~



### Fork/Join框架要完成两件事情：

1.**任务分割**：首先Fork/Join框架需要把大的任务分割成足够小的子任务，如果子任务比较大的话还要对子任务进行继续分割

2.**执行任务并合并结果**：分割的子任务分别放到**双端队列**里，然后**几个启动线程**分别**从双端队列里获取任务执行**。**子任务执行完的结果都放在另外一个队列里**，启动一个线程从队列里取数据，然后合并这些数据。

Java的Fork/Join框架中，使用两个类完成:

1.ForkJoinTask:我们要使用Fork/Join框架，首先需要创建一个ForkJoin任务。该类提供了在任务中执行fork和join的机制。通常情况下我们不需要直接集成ForkJoinTask类，只需要继承它的子类，Fork/Join框架提供了两个子类：

　　　　`a.RecursiveAction：用于没有返回结果的任务`

　　　　`b.RecursiveTask:用于有返回结果的任务`

2.ForkJoinPool:ForkJoinTask需要通过ForkJoinPool来执行

任务分割出的子任务会添加到当前工作线程所维护的双端队列中，进入队列的头部。当一个工作线程的队列里暂时没有任务时，它会随机从其他工作线程的队列的尾部获取一个任务(工作窃取算法)。

 ForkJoinTask的Fork方法的实现原理：
　　当我们调用ForkJoinTask的fork方法时，程序会把任务放在ForkJoinWorkerThread的pushTask的workQueue中，异步地执行这个任务，然后立即返回结果，代码如下：

```java
public final ForkJoinTask<V> fork() {
        Thread t;     
        if ((t = Thread.currentThread()) instanceof ForkJoinWorkerThread)
            ((ForkJoinWorkerThread)t).workQueue.push(this);
        else
            ForkJoinPool.common.externalPush(this);
        return this;
    }
```

　　pushTask方法把当前任务存放在ForkJoinTask数组队列里。然后再调用ForkJoinPool的signalWork()方法唤醒或创建一个工作线程来执行任务。代码如下：

```java
final void push(ForkJoinTask<?> task) {
            ForkJoinTask<?>[] a; ForkJoinPool p;
            int b = base, s = top, n;
            if ((a = array) != null) {    // ignore if queue removed
                int m = a.length - 1;     // fenced write for task visibility
                U.putOrderedObject(a, ((m & s) << ASHIFT) + ABASE, task);
                U.putOrderedInt(this, QTOP, s + 1);
                if ((n = s - b) <= 1) {
                    if ((p = pool) != null)
                        p.signalWork(p.workQueues, this);
                }
                else if (n >= m)
                    growArray();
            }
        }
```

　　**ForkJoinTask的join方法实现原理**

　　Join方法的主要作用是阻塞当前线程并等待获取结果。让我们一起看看ForkJoinTask的join方法的实现，代码如下：

```java
public final V join() {
        int s;
        if ((s = doJoin() & DONE_MASK) != NORMAL)
            reportException(s);
        return getRawResult();
    }
```

　　它首先调用doJoin方法，通过doJoin()方法得到当前任务的状态来判断返回什么结果，任务状态有4种：已完成（NORMAL）、被取消（CANCELLED）、信号（SIGNAL）和出现异常（EXCEPTIONAL）。

　　如果任务状态是已完成，则直接返回任务结果。

　　如果任务状态是被取消，则直接抛出CancellationException

　　如果任务状态是抛出异常，则直接抛出对应的异常

在doJoin()方法里，首先通过查看任务的状态，看任务是否已经执行完成，如果执行完成，则直接返回任务状态；如果没有执行完，则从任务数组里取出任务并执行。如果任务顺利执行完成，则设置任务状态为NORMAL，如果出现异常，则记录异常，并将任务状态设置为EXCEPTIONAL。

进一步了解ForkJoinTask，ForkJoinTask与一般任务的主要区别在于它需要实现compute方法，在这个方法里，首**先需要判断任务是否足够小，如果足够小就直接执行任务**。如果不足够小，就必须分割成两个子任务，每个子任务在调用fork方法时，又会进入compute方法，看看当前子任务是否需要继续分割成子任务，如果不需要继续分割，则执行当前子任务并返回结果。使用join方法会等待子任务执行完并得到其结果。

# 9 并行设计模式

## Future 模式

Future模式的核心在于：去除了主函数的等待时间，并使得原本需要等待的时间段可以用于处理其他业务逻辑。

Future模式有点类似于商品订单。在网上购物时，提交订单后，在收货的这段时间里无需一直在家里等候，可以先干别的事情。类推到程序设计中时，当提交请求时，期望得到答复时，如果这个答复可能很慢。传统的是一直持续等待直到这个答复收到之后再去做别的事情，但如果利用Future模式，其调用方式改为异步，而原先等待返回的时间段，在主调用函数中，则可以用于处理其他事务。（**Future模式就是将同步变异步**）

Future模式是一种并行设计模式，原理是当你申请资源时，立即返回一个虚拟的资源（通常这个时候在后台异步去申请真正资源），当真正需要使用资源的时候，再将对虚拟资源的调用传递给成真正的资源（如果这个时候真正资源依然没有申请到，则阻塞）。



单例模式

不变模式

生产者消费者模式



