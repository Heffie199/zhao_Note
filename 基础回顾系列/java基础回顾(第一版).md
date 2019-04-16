# java 基础回顾 (第一版)



# 一： 概述

1: 软件是什么？

 软件， 代码 是操作硬件(或者说是操作系统)去做一些动作的**指令**。

2 硬件

![JAVA_REVIEW1.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW1.png?raw=true)

(总线搭建在主板上，主板是一个连接计算机各个部分的电路板)

![JAVA_REVIEW2.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW2.png?raw=true)

计算机的最核心的部分就是cpu 。 

他分为两部分：

运算器：完成数值运算(+-*/)和逻辑运算。 他对应着编程语言中的各种运算规则。 计算机无时不刻都在进行运算。

控制器：用于控制和协调其他组件完成动作 (命令中心)

![JAVA_REVIEW3.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW3.png?raw=true)

(这里的GHz 和内存大小的换算单位是一样的， 1G=1024M 1M=1024K  1K=1024)



3 内存

数据在被cpu执行前必须先被加载在内存中。  (与cpu 交互的只能是内存 ----原因： 读写速度)

我们写的代码也是需要先被加载在内存中。然后被cpu解析执行。



4 万维网

![JAVA_REVIEW4.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW4.png?raw=true)

5: 发展与提升

![JAVA_REVIEW5.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW5.png?raw=true)

![JAVA_REVIEW6.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW6.png?raw=true)

知识 变成 技能 (需要长时间的磨练)

# 二：基础

1： java的跨平台性

我们在widows 上写的java程序可以拿到Linux系统中执行。 

正常的应该程序都是运行在操作系统之上。 不同操作系统的指令集不同需要重新编译。 但是java可以一次编译，不同系统都可以运行， 这是因为java 在操作系统之上封装了一层，就是jvm。 java程序不是直接运行在操作系统之上，而是运行在jvm之上

![JAVA_REVIEW7.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW7.png?raw=true)

jvm运行在不同的操作系统之上。(不同的系统有不同的jvm)

![JAVA_REVIEW8.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW8.png?raw=true)

2: jdk ,jre,jvm

![JAVA_REVIEW9.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW9.png?raw=true)




![JAVA_REVIEW10.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW10.png?raw=true)
![JAVA_REVIEW11.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW11.png?raw=true)
![JAVA_REVIEW12.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW12.png?raw=true)
![JAVA_REVIEW13.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW13.png?raw=true)
![JAVA_REVIEW14.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW14.png?raw=true)
![JAVA_REVIEW15.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW15.png?raw=true)
![JAVA_REVIEW16.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW16.png?raw=true)
![JAVA_REVIEW17.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW17.png?raw=true)
![JAVA_REVIEW18.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW18.png?raw=true)
![JAVA_REVIEW19.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW19.png?raw=true)
![JAVA_REVIEW20.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW20.png?raw=true)
![JAVA_REVIEW21.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW21.png?raw=true)
![JAVA_REVIEW22.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW22.png?raw=true)
![JAVA_REVIEW23.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW23.png?raw=true)
![JAVA_REVIEW24.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW24.png?raw=true)
![JAVA_REVIEW25.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW25.png?raw=true)
![JAVA_REVIEW26.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW26.png?raw=true)
![JAVA_REVIEW27.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW27.png?raw=true)
![JAVA_REVIEW28.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW28.png?raw=true)
![JAVA_REVIEW29.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW29.png?raw=true)
![JAVA_REVIEW30.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW30.png?raw=true)
![JAVA_REVIEW31.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW31.png?raw=true)
![JAVA_REVIEW32.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW32.png?raw=true)
![JAVA_REVIEW33.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW33.png?raw=true)
![JAVA_REVIEW34.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW34.png?raw=true)
![JAVA_REVIEW35.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW35.png?raw=true)
![JAVA_REVIEW36.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW36.png?raw=true)
![JAVA_REVIEW37.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW37.png?raw=true)
![JAVA_REVIEW38.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW38.png?raw=true)
![JAVA_REVIEW39.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW39.png?raw=true)
![JAVA_REVIEW40.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW40.png?raw=true)
![JAVA_REVIEW41.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW41.png?raw=true)
![JAVA_REVIEW42.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW42.png?raw=true)
![JAVA_REVIEW43.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW43.png?raw=true)
![JAVA_REVIEW44.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW44.png?raw=true)
![JAVA_REVIEW45.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW45.png?raw=true)
![JAVA_REVIEW46.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW46.png?raw=true)
![JAVA_REVIEW47.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW47.png?raw=true)
![JAVA_REVIEW48.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW48.png?raw=true)
![JAVA_REVIEW49.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW49.png?raw=true)
![JAVA_REVIEW50.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/JAVA_REVIEW50.png?raw=true)

