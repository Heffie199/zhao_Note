# mysql 基础回顾

# 一： 基础

1:MySQL服务的启动和停止
​	方式一：计算机——右击管理——服务
​	方式二：通过管理员身份运行
​	net start 服务名（启动服务）
​	net stop 服务名（停止服务）

MySQL服务的登录和退出   
​	方式一：通过mysql自带的客户端
​	只限于root用户

```
方式二：通过windows自带的客户端
登录：
mysql 【-h主机名 -P端口号 】-u用户名 -p密码

退出：
exit或ctrl+C
```



###MySQL的常见命令 

```
1.查看当前所有的数据库
show databases;
2.打开指定的库
use 库名
3.查看当前库的所有表
show tables;
4.查看其它库的所有表
show tables from 库名;
5.创建表
create table 表名(

	列名 列类型,
	列名 列类型，
	。。。
);
6.查看表结构
desc 表名;
```

```
7.查看服务器的版本
方式一：登录到mysql服务端
select version();
方式二：没有登录到mysql服务端
mysql --version
或
mysql --V
```



## 2 查询语句

查询执行顺序

![MYSQL_REVIEWAD59.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD59.png?raw=true)

①通过select查询完的结果 ，是一个虚拟的表格，不是真实存在
② 要查询的东西 可以是常量值、可以是表达式、可以是字段、可以是函数

![MYSQL_REVIEWAD1.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD1.png?raw=true)

去重：

![MYSQL_REVIEWAD2.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD2.png?raw=true)

+的作用： 

![MYSQL_REVIEWAD3.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD3.png?raw=true)

![MYSQL_REVIEWAD4.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD4.png?raw=true)

null 和任何字段拼接，他的结构都是null.

![MYSQL_REVIEWAD5.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD5.png?raw=true)



### 进阶2：条件查询

![MYSQL_REVIEWAD6.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD6.png?raw=true)

​	条件查询：根据条件过滤原始表的数据，查询到想要的数据

```
	语法：
	select 
		要查询的字段|表达式|常量值|函数  （3）
 	from 
		表 （1）
	where 
		条件 ;  （2）
# 查询语句的执行顺序是 先 （1） (2) (3) 
```



```
分类：
一、条件表达式
	示例：salary>10000
	条件运算符：
	> < >= <= = != <>

二、逻辑表达式
示例：salary>10000 && salary<20000

逻辑运算符：

	and（&&）:两个条件如果同时成立，结果为true，否则为false
	or(||)：两个条件只要有一个成立，结果为true，否则为false
	not(!)：如果条件成立，则not后为false，否则为true

三、模糊查询
示例：last_name like 'a%'

where 关键字：
where关键字表示多from 的原始表中的字段进行筛选。 所以where后面的字段必须是存在于原始表中的。
sql语句的执行顺序也是按照先from表，后where 条件。 后面无论是多复杂都是遵照的这个原则
```

案例：

![MYSQL_REVIEWAD7.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD7.png?raw=true)

![MYSQL_REVIEWAD8.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD8.png?raw=true)

![MYSQL_REVIEWAD9.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD9.png?raw=true)

![MYSQL_REVIEWAD10.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD10.png?raw=true)

![MYSQL_REVIEWAD11.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD11.png?raw=true)

![MYSQL_REVIEWAD20.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD20.png?raw=true)

![MYSQL_REVIEWAD12.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD12.png?raw=true)

![MYSQL_REVIEWAD13.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD13.png?raw=true)

![MYSQL_REVIEWAD14.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD14.png?raw=true)

![MYSQL_REVIEWAD15.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD15.png?raw=true)



### 进阶3：**排序查询**	

​	

```
语法：
select
	要查询的东西
from
	表
where 
	条件

order by 排序的字段|表达式|函数|别名 【asc|desc】
```

![MYSQL_REVIEWAD16.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD16.png?raw=true)

![MYSQL_REVIEWAD17.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD17.png?raw=true)

![MYSQL_REVIEWAD18.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD18.png?raw=true)

![MYSQL_REVIEWAD19.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD19.png?raw=true)

## 3 函数

### 进阶4：常见函数

![MYSQL_REVIEWAD21.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD21.png?raw=true)

```
一、单行函数
	1、字符函数
		concat拼接
		substr截取子串
		upper转换成大写
		lower转换成小写
		trim去前后指定的空格和字符
		ltrim去左边空格
		rtrim去右边空格
		replace替换
		lpad左填充
		rpad右填充
		ins 
		length 获取字节个数
```

![MYSQL_REVIEWAD22.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD22.png?raw=true)

```
2、数学函数
	round 四舍五入
	rand 随机数
	floor向下取整
	ceil向上取整
	mod取余
	truncate截断
```

![MYSQL_REVIEWAD23.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD23.png?raw=true)

```
3、日期函数
now当前系统日期+时间
curdate当前系统日期
curtime当前系统时间
str_to_date 将字符转换成日期
date_format将日期转换成字符
```

![MYSQL_REVIEWAD24.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD24.png?raw=true)

![MYSQL_REVIEWAD25.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD25.png?raw=true)

```
4、流程控制函数
	if 处理双分支
	case语句 处理多分支
		情况1：处理等值判断
		情况2：处理条件判断
```

![MYSQL_REVIEWAD26.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD26.png?raw=true)

![MYSQL_REVIEWAD27.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD27.png?raw=true)

![MYSQL_REVIEWAD28.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD28.png?raw=true)

![MYSQL_REVIEWAD29.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD29.png?raw=true)



```
5、其他函数
	version版本
	database当前库
	user当前连接用户
```





### 二、分组函数

```
	sum 求和
	max 最大值
	min 最小值
	avg 平均值
	count 计数

	特点：
	1、以上五个分组函数都忽略null值，除了count(*)
	2、sum和avg一般用于处理数值型
		max、min、count可以处理任何数据类型
    3、都可以搭配distinct使用，用于统计去重后的结果
	4、count的参数可以支持：
		字段、*、常量值，一般放1

	   建议使用 count(*) 来统计行数
	5  分组函数中的字段一般要求是group by 的字段
```



## 4 高级查询

### 进阶5：分组查询

```
	语法：
	select 查询的字段，分组函数
	from 表
	group by 分组的字段
	having  分组后过滤条件
```



```
特点：
1、可以按单个字段分组
2、和分组函数一同查询的字段最好是分组后的字段
3、分组筛选
		    数据源    	  位置			关键字
分组前筛选：	原始表		group by的前面		where
分组后筛选：	分组后的结果集	group by的后面		having
分组函数做条件肯定放在having字句中
能用分组前筛选，优先考虑使用分组前

4、可以按多个字段分组，字段之间用逗号隔开
5、可以支持排序
6、having后可以支持别名
```

![MYSQL_REVIEWAD30.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD30.png?raw=true)	

![MYSQL_REVIEWAD31.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD31.png?raw=true)

![MYSQL_REVIEWAD32.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD32.png?raw=true)

![MYSQL_REVIEWAD33.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD33.png?raw=true)

对于复杂的查询：我们先分解查询条件。

1： 去掉筛选条件查得子集

2： 加上筛选条件

![MYSQL_REVIEWAD34.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD34.png?raw=true)

![MYSQL_REVIEWAD35.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD35.png?raw=true)

![MYSQL_REVIEWAD36.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD36.png?raw=true)

按照多个字段分组

![MYSQL_REVIEWAD37.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD37.png?raw=true)



### 进阶6：多表连接查询

![MYSQL_REVIEWAD38.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD38.png?raw=true)

```
笛卡尔乘积：如果连接条件省略或无效则会出现
解决办法：添加上连接条件
```


![MYSQL_REVIEWAD39.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD39.png?raw=true)

![MYSQL_REVIEWAD40.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD40.png?raw=true) 

一、传统模式下的连接 ：等值连接——非等值连接

```
1.等值连接的结果 = 多个表的交集
2.n表连接，至少需要n-1个连接条件
3.多个表不分主次，没有顺序要求
4.一般为表起别名，提高阅读性和性能
```



二、通过join关键字实现连接

```
含义：1999年推出的sql语法
支持：
等值连接、非等值连接 （内连接--求集合交集） 
外连接 ： 一般来说是用来查询一个表中有(主表)，另外一个表中没有的数据(从表)
         主表中的每一条记录都会显示出来。 主要想查询谁，谁就是主表
全外连接 ： mysql 不支持全连接
交叉连接 ： 笛卡尔乘积，就类似“，” 号

语法：

select 字段，...
from 表1
【inner|left outer|right outer|cross】join 表2 on  连接条件
【inner|left outer|right outer|cross】join 表3 on  连接条件
【where 筛选条件】
【group by 分组字段】
【having 分组后的筛选条件】
【order by 排序的字段或表达式】

好处：语句上，连接条件和筛选条件实现了分离，简洁明了！
```

![MYSQL_REVIEWAD42.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD42.png?raw=true)

![MYSQL_REVIEWAD41.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD41.png?raw=true)  

![MYSQL_REVIEWAD43.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD43.png?raw=true)

外连接原理：

![MYSQL_REVIEWAD44.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD44.png?raw=true)

![MYSQL_REVIEWAD45.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD45.png?raw=true)

总结：

![MYSQL_REVIEWAD46.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD46.png?raw=true)

![MYSQL_REVIEWAD47.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD47.png?raw=true)

![MYSQL_REVIEWAD48.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD48.png?raw=true)

三、自连接

案例：查询员工名和直接上级的名称

sql99

```
SELECT e.last_name,m.last_name
FROM employees e
JOIN employees m ON e.`manager_id`=m.`employee_id`;
```

sql92

```
SELECT e.last_name,m.last_name
FROM employees e,employees m 
WHERE e.`manager_id`=m.`employee_id`;
```

​	

### 进阶7：子查询



含义：

```
一条查询语句中又嵌套了另一条完整的select语句，其中被嵌套的select语句，称为子查询或内查询
在外面的查询语句，称为主查询或外查询
```

![MYSQL_REVIEWAD49.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD49.png?raw=true)

特点：

```
1、子查询都放在小括号内
2、子查询可以放在from后面、select后面、where后面、having后面，但一般放在条件的右侧
3、子查询优先于主查询执行，主查询使用了子查询的执行结果
4、子查询根据查询结果的行数不同分为以下两类：
① 单行子查询
	结果集只有一行
	一般搭配单行操作符使用：> < = <> >= <= 
	非法使用子查询的情况：
	a、子查询的结果为一组值
	b、子查询的结果为空
	
② 多行子查询
	结果集有多行
	一般搭配多行操作符使用：any、all、in、not in
	in： 属于子查询结果中的任意一个就行
	any和all往往可以用其他查询代替
```

![MYSQL_REVIEWAD50.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD50.png?raw=true)

![MYSQL_REVIEWAD51.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD51.png?raw=true)

![MYSQL_REVIEWAD52.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD52.png?raw=true)

![MYSQL_REVIEWAD53.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD53.png?raw=true)

![MYSQL_REVIEWAD54.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD54.png?raw=true)

any 一般情况下可以替换为min() ，in , all 替换为  max()

 

![MYSQL_REVIEWAD55.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD55.png?raw=true)

![MYSQL_REVIEWAD56.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD56.png?raw=true)

![MYSQL_REVIEWAD57.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD57.png?raw=true)

（如果我们将子查询当做一张表来关联，这个子查询必须取别名）

![MYSQL_REVIEWAD58.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD58.png?raw=true)

例题：

![MYSQL_REVIEWAD60.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD60.png?raw=true)

![MYSQL_REVIEWAD61.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD61.png?raw=true)

### 进阶8：分页查询

应用场景：

```
实际的web项目中需要根据用户的需求提交对应的分页查询的sql语句
```

语法：

```
select 字段|表达式,...
from 表
【where 条件】
【group by 分组字段】
【having 条件】
【order by 排序的字段】
limit 【起始的条目索引，起始条目索引从0开始】条目数;
```

特点：

```
1:起始条目索引从0开始 ，如果从第一条开始，offset 可以省略。
2.limit子句放在查询语句的最后，在执行顺序上也是最后执行。

假如:
每页显示条目数sizePerPage
要显示的页数 page
3.公式：select * from  表 limit （page-1）*sizePerPage,sizePerPage
```



### 进阶9：联合查询

![MYSQL_REVIEWAD62.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD62.png?raw=true)

![MYSQL_REVIEWAD63.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD63.png?raw=true)

引入：
​	union 联合、合并。 将多条查询语句的结果合并成一个结果。

语法：

```
select 字段|常量|表达式|函数 【from 表】 【where 条件】 union 【all】
select 字段|常量|表达式|函数 【from 表】 【where 条件】 union 【all】
select 字段|常量|表达式|函数 【from 表】 【where 条件】 union  【all】
.....
select 字段|常量|表达式|函数 【from 表】 【where 条件】
```

特点：

```
1、多条查询语句的查询的列数必须是一致的
2、多条查询语句的查询的每一列的 类型，和顺序 一致
3、union代表去重，union all代表不去重
```



##  5 DML（数据操作）语言 

![MYSQL_REVIEWAD64.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD64.png?raw=true)

### 插入

```
语法：
	insert into 表名(字段名，...)
	values(值1，...);
	
	
特点：
1、字段类型和值类型一致或兼容(隐式的转换)，而且一一对应
2、可以为空的字段，可以不用插入值，或用null填充
3、不可以为空的字段，必须插入值
4、字段个数和值的个数必须一致
5、字段可以省略，但默认所有字段，并且顺序和表中的存储顺序一致

```

![MYSQL_REVIEWAD65.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD65.png?raw=true)

![MYSQL_REVIEWAD66.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD66.png?raw=true)

![MYSQL_REVIEWAD67.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD67.png?raw=true)

### 修改

修改单表语法：

```
update 表名 set 字段=新值,字段=新值
【where 条件】
```

![MYSQL_REVIEWAD68.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD68.png?raw=true)

修改多表语法：

![MYSQL_REVIEWAD69.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD69.png?raw=true)

多表修改语法的本质： 就是将多张表连接成一个大的结果集，然后进行修改

```
update 表1 别名1,表2 别名2
set 字段=新值，字段=新值
where 连接条件
and 筛选条件
```

![MYSQL_REVIEWAD70.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD70.png?raw=true) 

### 删除

方式1：delete语句 

```
单表的删除： ★
	delete from 表名 【where 筛选条件】

多表的删除：
	delete 别名1，别名2
	from 表1 别名1，表2 别名2
	where 连接条件
	and 筛选条件;
```

方式2：truncate语句

```
truncate table 表名
```



两种方式的区别【面试题】	

```
#1.truncate不能加where条件，而delete可以加where条件

#2.truncate的效率高一丢丢

#3.truncate 删除带自增长的列的表后，如果再插入数据，数据从1开始
#delete 删除带自增长列的表后，如果再插入数据，数据从上一次的断点处开始

#4.truncate删除不能回滚，delete删除可以回滚
```




![MYSQL_REVIEWAD71.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD71.png?raw=true)
![MYSQL_REVIEWAD72.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD72.png?raw=true)
![MYSQL_REVIEWAD73.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD73.png?raw=true)
![MYSQL_REVIEWAD74.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD74.png?raw=true)
![MYSQL_REVIEWAD75.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD75.png?raw=true)
![MYSQL_REVIEWAD76.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD76.png?raw=true)
![MYSQL_REVIEWAD77.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD77.png?raw=true)
![MYSQL_REVIEWAD78.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD78.png?raw=true)
![MYSQL_REVIEWAD79.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD79.png?raw=true)
![MYSQL_REVIEWAD80.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD80.png?raw=true)



## 6 DDL语句

###库和表的管理
库的管理：

```
一、创建库
create database 库名
二、删除库
drop database 库名
```

表的管理：

```
#1.创建表
CREATE TABLE IF NOT EXISTS stuinfo(
	stuId INT,
	stuName VARCHAR(20),
	gender CHAR,
	bornDate DATETIME
	);

DESC studentinfo;
#2.修改表 alter
语法：ALTER TABLE 表名 ADD|MODIFY|DROP|CHANGE COLUMN 字段名 【字段类型】;

#①修改字段名
ALTER TABLE studentinfo CHANGE  COLUMN sex gender CHAR;

#②修改表名
ALTER TABLE stuinfo RENAME [TO]  studentinfo;
#③修改字段类型和列级约束
ALTER TABLE studentinfo MODIFY COLUMN borndate DATE ;

#④添加字段

ALTER TABLE studentinfo ADD COLUMN email VARCHAR(20) first;
#⑤删除字段
ALTER TABLE studentinfo DROP COLUMN email;
```

​	

```
#3.删除表

DROP TABLE [IF EXISTS] studentinfo;
```

​	

###常见类型

```
整型：
	
小数：
	浮点型
	定点型
字符型：
日期型：
Blob类型：
```



###常见约束

```
NOT NULL
DEFAULT
UNIQUE
CHECK
PRIMARY KEY
FOREIGN KEY
```

##数据库事务
###含义
	通过一组逻辑操作单元（一组DML——sql语句），将数据从一种状态切换到另外一种状态

###特点
	（ACID）
	原子性：要么都执行，要么都回滚
	一致性：保证数据的状态操作前和操作后保持一致
	隔离性：多个事务同时操作相同数据库的同一个数据时，一个事务的执行不受另外一个事务的干扰
	持久性：一个事务一旦提交，则数据将持久化到本地，除非其他事务对其进行修改

相关步骤：

```
1、开启事务
2、编写事务的一组逻辑操作单元（多条sql语句）
3、提交事务或回滚事务
```

###事务的分类：

隐式事务，没有明显的开启和结束事务的标志

```
比如
insert、update、delete语句本身就是一个事务
```

显式事务，具有明显的开启和结束事务的标志

```
	1、开启事务
	取消自动提交事务的功能
	
	2、编写事务的一组逻辑操作单元（多条sql语句）
	insert
	update
	delete
	
	3、提交事务或回滚事务
```

###使用到的关键字

```
set autocommit=0;
start transaction;
commit;
rollback;

savepoint  断点
commit to 断点
rollback to 断点
```

###事务的隔离级别:

事务并发问题如何发生？

```
当多个事务同时操作同一个数据库的相同数据时
```

事务的并发问题有哪些？

```
脏读：一个事务读取到了另外一个事务未提交的数据
不可重复读：同一个事务中，多次读取到的数据不一致
幻读：一个事务读取数据时，另外一个事务进行更新，导致第一个事务读取到了没有更新的数据
```

如何避免事务的并发问题？

```
通过设置事务的隔离级别
1、READ UNCOMMITTED
2、READ COMMITTED 可以避免脏读
3、REPEATABLE READ 可以避免脏读、不可重复读和一部分幻读
4、SERIALIZABLE可以避免脏读、不可重复读和幻读
```

设置隔离级别：

```
set session|global  transaction isolation level 隔离级别名;
```

查看隔离级别：

```
select @@tx_isolation;
```



##视图
含义：理解成一张虚拟的表

视图和表的区别：
	

```
	使用方式	占用物理空间

视图	完全相同	不占用，仅仅保存的是sql逻辑

表	完全相同	占用
```

视图的好处：

```
1、sql语句提高重用性，效率高
2、和表实现了分离，提高了安全性
```

###视图的创建
	语法：
	CREATE VIEW  视图名
	AS
	查询语句;
###视图的增删改查
	1、查看视图的数据 ★
	

```
SELECT * FROM my_v4;
SELECT * FROM my_v1 WHERE last_name='Partners';

2、插入视图的数据
INSERT INTO my_v4(last_name,department_id) VALUES('虚竹',90);

3、修改视图的数据

UPDATE my_v4 SET last_name ='梦姑' WHERE last_name='虚竹';
```

​	

```
4、删除视图的数据
DELETE FROM my_v4;
```

###某些视图不能更新
	包含以下关键字的sql语句：分组函数、distinct、group  by、having、union或者union all
	常量视图
	Select中包含子查询
	join
	from一个不能更新的视图
	where子句的子查询引用了from子句中的表
###视图逻辑的更新
	#方式一：
	CREATE OR REPLACE VIEW test_v7
	AS
	SELECT last_name FROM employees
	WHERE employee_id>100;
	

```
#方式二:
ALTER VIEW test_v7
AS
SELECT employee_id FROM employees;

SELECT * FROM test_v7;
```

###视图的删除
	DROP VIEW test_v1,test_v2,test_v3;
###视图结构的查看	
	DESC test_v7;
	SHOW CREATE VIEW test_v7;
























![MYSQL_REVIEWAD81.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD81.png?raw=true)
![MYSQL_REVIEWAD82.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD82.png?raw=true)
![MYSQL_REVIEWAD83.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD83.png?raw=true)
![MYSQL_REVIEWAD84.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD84.png?raw=true)
![MYSQL_REVIEWAD85.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD85.png?raw=true)
![MYSQL_REVIEWAD86.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD86.png?raw=true)
![MYSQL_REVIEWAD87.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD87.png?raw=true)
![MYSQL_REVIEWAD88.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD88.png?raw=true)
![MYSQL_REVIEWAD89.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD89.png?raw=true)
![MYSQL_REVIEWAD90.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD90.png?raw=true)
![MYSQL_REVIEWAD91.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD91.png?raw=true)
![MYSQL_REVIEWAD92.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD92.png?raw=true)
![MYSQL_REVIEWAD93.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD93.png?raw=true)
![MYSQL_REVIEWAD94.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD94.png?raw=true)
![MYSQL_REVIEWAD95.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD95.png?raw=true)
![MYSQL_REVIEWAD96.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD96.png?raw=true)
![MYSQL_REVIEWAD97.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD97.png?raw=true)
![MYSQL_REVIEWAD98.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD98.png?raw=true)
![MYSQL_REVIEWAD99.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD99.png?raw=true)
![MYSQL_REVIEWAD100.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD100.png?raw=true)
![MYSQL_REVIEWAD101.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD101.png?raw=true)
![MYSQL_REVIEWAD102.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD102.png?raw=true)
![MYSQL_REVIEWAD103.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD103.png?raw=true)
![MYSQL_REVIEWAD104.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD104.png?raw=true)
![MYSQL_REVIEWAD105.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD105.png?raw=true)
![MYSQL_REVIEWAD106.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD106.png?raw=true)
![MYSQL_REVIEWAD107.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD107.png?raw=true)
![MYSQL_REVIEWAD108.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD108.png?raw=true)
![MYSQL_REVIEWAD109.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD109.png?raw=true)
![MYSQL_REVIEWAD110.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD110.png?raw=true)
![MYSQL_REVIEWAD111.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD111.png?raw=true)
![MYSQL_REVIEWAD112.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD112.png?raw=true)
![MYSQL_REVIEWAD113.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD113.png?raw=true)
![MYSQL_REVIEWAD114.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD114.png?raw=true)
![MYSQL_REVIEWAD115.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD115.png?raw=true)
![MYSQL_REVIEWAD116.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD116.png?raw=true)
![MYSQL_REVIEWAD117.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD117.png?raw=true)
![MYSQL_REVIEWAD118.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD118.png?raw=true)
![MYSQL_REVIEWAD119.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD119.png?raw=true)
![MYSQL_REVIEWAD120.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD120.png?raw=true)
![MYSQL_REVIEWAD121.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD121.png?raw=true)
![MYSQL_REVIEWAD122.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD122.png?raw=true)
![MYSQL_REVIEWAD123.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD123.png?raw=true)
![MYSQL_REVIEWAD124.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD124.png?raw=true)
![MYSQL_REVIEWAD125.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD125.png?raw=true)
![MYSQL_REVIEWAD126.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD126.png?raw=true)
![MYSQL_REVIEWAD127.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD127.png?raw=true)
![MYSQL_REVIEWAD128.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD128.png?raw=true)
![MYSQL_REVIEWAD129.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD129.png?raw=true)
![MYSQL_REVIEWAD130.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD130.png?raw=true)
![MYSQL_REVIEWAD131.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD131.png?raw=true)
![MYSQL_REVIEWAD132.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD132.png?raw=true)
![MYSQL_REVIEWAD133.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD133.png?raw=true)
![MYSQL_REVIEWAD134.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD134.png?raw=true)
![MYSQL_REVIEWAD135.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD135.png?raw=true)
![MYSQL_REVIEWAD136.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD136.png?raw=true)
![MYSQL_REVIEWAD137.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD137.png?raw=true)
![MYSQL_REVIEWAD138.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD138.png?raw=true)
![MYSQL_REVIEWAD139.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD139.png?raw=true)
![MYSQL_REVIEWAD140.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD140.png?raw=true)
![MYSQL_REVIEWAD141.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD141.png?raw=true)
![MYSQL_REVIEWAD142.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD142.png?raw=true)
![MYSQL_REVIEWAD143.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD143.png?raw=true)
![MYSQL_REVIEWAD144.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD144.png?raw=true)
![MYSQL_REVIEWAD145.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD145.png?raw=true)
![MYSQL_REVIEWAD146.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD146.png?raw=true)
![MYSQL_REVIEWAD147.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD147.png?raw=true)
![MYSQL_REVIEWAD148.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD148.png?raw=true)
![MYSQL_REVIEWAD149.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD149.png?raw=true)
![MYSQL_REVIEWAD150.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD150.png?raw=true)





　
