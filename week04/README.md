# 第四周作业

题目：思考有多少种方式，在 main 函数启动一个新线程，运行一个方法，拿到这
个方法的返回值后，退出主线程? 写出你的方法，越多越好，提交到 GitHub。

## Join

- 第一时间想到的是join，让主线程在这里等待子线程执行完，再继续执行



## CountDownLatch

- 之后能想到的就是用工具类，使用CountDownLatch让主线程await，等到子线程们都进行countDown并且计数到0的时候，主线程再执行接下去的代码，实现等待子线程执行完才退出主线程的目的



## CyclicBarrier

- CyclicBarrier也能达到同样的目的。子线程们先await等待，当等待的子线程数目达到预设值的时候，所有线程再执行await之后的代码。然后主线程reset barrier，才结束主线程