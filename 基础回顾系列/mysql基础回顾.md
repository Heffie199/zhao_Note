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
​	语法：
​	select 
​		要查询的字段|表达式|常量值|函数
​	from 
​		表
​	where 
​		条件 ;

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
		instr返回子串第一次出现的索引
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





## 二、分组函数

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

![MYSQL_REVIEWAD30.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD30.png?raw=true)

### 进阶5：分组查询

​	语法：
​	select 查询的字段，分组函数
​	from 表
​	group by 分组的字段
​	
​	

```
特点：
1、可以按单个字段分组
2、和分组函数一同查询的字段最好是分组后的字段
3、分组筛选
		针对的表	位置			关键字
分组前筛选：	原始表		group by的前面		where
分组后筛选：	分组后的结果集	group by的后面		having

4、可以按多个字段分组，字段之间用逗号隔开
5、可以支持排序
6、having后可以支持别名
```

##进阶6：多表连接查询

```
笛卡尔乘积：如果连接条件省略或无效则会出现
解决办法：添加上连接条件
```

一、传统模式下的连接 ：等值连接——非等值连接

```
1.等值连接的结果 = 多个表的交集
2.n表连接，至少需要n-1个连接条件
3.多个表不分主次，没有顺序要求
4.一般为表起别名，提高阅读性和性能
```

二、sql99语法：通过join关键字实现连接

```
含义：1999年推出的sql语法
支持：
等值连接、非等值连接 （内连接）
外连接
交叉连接

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

​	
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

##进阶7：子查询

​	














![MYSQL_REVIEWAD31.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD31.png?raw=true)
![MYSQL_REVIEWAD32.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD32.png?raw=true)
![MYSQL_REVIEWAD33.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD33.png?raw=true)
![MYSQL_REVIEWAD34.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD34.png?raw=true)
![MYSQL_REVIEWAD35.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD35.png?raw=true)
![MYSQL_REVIEWAD36.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD36.png?raw=true)
![MYSQL_REVIEWAD37.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD37.png?raw=true)
![MYSQL_REVIEWAD38.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD38.png?raw=true)
![MYSQL_REVIEWAD39.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD39.png?raw=true)
![MYSQL_REVIEWAD40.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD40.png?raw=true)
![MYSQL_REVIEWAD41.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD41.png?raw=true)
![MYSQL_REVIEWAD42.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD42.png?raw=true)
![MYSQL_REVIEWAD43.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD43.png?raw=true)
![MYSQL_REVIEWAD44.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD44.png?raw=true)
![MYSQL_REVIEWAD45.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD45.png?raw=true)
![MYSQL_REVIEWAD46.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD46.png?raw=true)
![MYSQL_REVIEWAD47.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD47.png?raw=true)
![MYSQL_REVIEWAD48.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD48.png?raw=true)
![MYSQL_REVIEWAD49.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD49.png?raw=true)
![MYSQL_REVIEWAD50.png](https://github.com/zhaodahan/zhao_Note/blob/master/wiki_img/MYSQL_REVIEWAD50.png?raw=true)



　
