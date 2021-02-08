# 智能遍历器算法
#### 算法原理：
	遍历一个集合时，连续数次处理失败后，以指数级的形式跳过一段大概率会失败的数据，将这段数据保存起来，等会再以相同的形式遍历。
#### 算法形容图
	无
#### 意义：
	将处理成功率高的数据优先处理，快速收集存在的数据。（成功率高：指数据集中的某个子集）
#### 场景1，网页爬虫：
	当你只能通过序号遍历一批页面来收集数据，而中间存在大量被批量删除的页面时，可以快速跳过无意义的遍历。
	如：10000个页面中，第1500-9500页面已经被站点清除，
	而你事先不知道，从1开始遍历，就多执行了8000次无意义的处理，
	但是你采用智能遍历器，中间只需要 log2(9500-1500) 约13次无意义处理，就能先跳过大量不存在的页面，优先处理存在的页面。
	然而当你收集完真实存在的数据，再折回来收集跳过的段，以这种形式无限分隔下去，收集完剩下的数据。
	而大量基本不命中的数据，都将堆在整个应用生命周期的最后来处理。
	打印出不存在页面就报错的日志，当执行一段时间后你看到大量报错，你将微微一笑，剩下的页面都不存在，可以停应用了。
  	极限场景下：当网页序号是1-2000000，簇状存放，你可以快速收集到80%的数据拿来使用，而剩下的20%，就让应用慢慢运行吧！
  	（应对某些喜欢删帖的网站特别有用，你明白我说的是哪些）
  
#### 场景2,


## 以下内容为实验数据，相关雏形可从项目中下载尝试：
#### 当 9-23，35-54，83-87，93-98，这46个数据不存在时，遍历1-100的程序如下。
	我们可以从实验数据中看出，程序将原来的 54% 命中率，在运行前期（前2轮）提高到了70%左右。
	而后的15轮中，命中率惨不忍睹，但是这时我们已经快速获取了大批量数据，剩下的数据如果无所谓，就可以退出程序了。
	这种将命中率提前，在海量数据中极为有优势。
	比如程序总共运行4个小时，但是因为数据存放方式，运行了2个小时只收集了10%的数据就停电了。
	而采用智能遍历，2个小时可能已经收集了80%数据，那么停电也没有关系了。

当前处理分组为：[ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100 ]
当前数字：1，情况：正常处理
当前数字：2，情况：正常处理
当前数字：3，情况：正常处理
当前数字：4，情况：正常处理
当前数字：5，情况：正常处理
当前数字：6，情况：正常处理
当前数字：7，情况：正常处理
当前数字：8，情况：正常处理
当前数字：9，情况：处理失败--------------------------------
当前数字：10，情况：处理失败--------------------------------
保存跳过的分组：[11]
当前数字：12，情况：处理失败--------------------------------
保存跳过的分组：[13,14,15]
当前数字：16，情况：处理失败--------------------------------
保存跳过的分组：[17,18,19,20,21,22,23]
当前数字：24，情况：正常处理
当前数字：25，情况：正常处理
当前数字：26，情况：正常处理
当前数字：27，情况：正常处理
当前数字：28，情况：正常处理
当前数字：29，情况：正常处理
当前数字：30，情况：正常处理
当前数字：31，情况：正常处理
当前数字：32，情况：正常处理
当前数字：33，情况：正常处理
当前数字：34，情况：正常处理
当前数字：35，情况：处理失败--------------------------------
当前数字：36，情况：处理失败--------------------------------
保存跳过的分组：[37]
当前数字：38，情况：处理失败--------------------------------
保存跳过的分组：[39,40,41]
当前数字：42，情况：处理失败--------------------------------
保存跳过的分组：[43,44,45,46,47,48,49]
当前数字：50，情况：处理失败--------------------------------
保存跳过的分组：[51,52,53,54,55,56,57,58,59,60,61,62,63,64,65]
当前数字：66，情况：正常处理
当前数字：67，情况：正常处理
当前数字：68，情况：正常处理
当前数字：69，情况：正常处理
当前数字：70，情况：正常处理
当前数字：71，情况：正常处理
当前数字：72，情况：正常处理
当前数字：73，情况：正常处理
当前数字：74，情况：正常处理
当前数字：75，情况：正常处理
当前数字：76，情况：正常处理
当前数字：77，情况：正常处理
当前数字：78，情况：正常处理
当前数字：79，情况：正常处理
当前数字：80，情况：正常处理
当前数字：81，情况：正常处理
当前数字：82，情况：正常处理
当前数字：83，情况：处理失败--------------------------------
当前数字：84，情况：处理失败--------------------------------
保存跳过的分组：[85]
当前数字：86，情况：处理失败--------------------------------
保存跳过的分组：[87,88,89]
当前数字：90，情况：正常处理
当前数字：91，情况：正常处理
当前数字：92，情况：正常处理
当前数字：93，情况：处理失败--------------------------------
当前数字：94，情况：处理失败--------------------------------
保存跳过的分组：[95]
当前数字：96，情况：处理失败--------------------------------
保存跳过的分组：[97,98,99]
当前数字：100，情况：正常处理
一轮结束后，未处理的分组为：[ [ 11 ], [ 13, 14, 15 ], [ 17, 18, 19, 20, 21, 22, 23 ], [ 37 ], [ 39, 40, 41 ], [ 43, 44, 45, 46, 47, 48, 49 ], [ 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65 ], [ 85 ], [ 87, 88, 89 ], [ 95 ], [ 97, 98, 99 ] ]


开始下一轮分组处理
当前最大的被跳过分组为：[ 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65 ]
当前处理分组为：[ 65, 64, 63, 62, 61, 60, 59, 58, 57, 56, 55, 54, 53, 52, 51 ]
当前数字：65，情况：正常处理
当前数字：64，情况：正常处理
当前数字：63，情况：正常处理
当前数字：62，情况：正常处理
当前数字：61，情况：正常处理
当前数字：60，情况：正常处理
当前数字：59，情况：正常处理
当前数字：58，情况：正常处理
当前数字：57，情况：正常处理
当前数字：56，情况：正常处理
当前数字：55，情况：正常处理
当前数字：54，情况：处理失败--------------------------------
当前数字：53，情况：处理失败--------------------------------
保存跳过的分组：[52]
当前数字：51，情况：处理失败--------------------------------
一轮结束后，未处理的分组为：[ [ 17, 18, 19, 20, 21, 22, 23 ], [ 43, 44, 45, 46, 47, 48, 49 ], [ 13, 14, 15 ], [ 39, 40, 41 ], [ 87, 88, 89 ], [ 97, 98, 99 ], [ 11 ], [ 37 ], [ 85 ], [ 95 ], [ 52 ] ]


开始下一轮分组处理
当前最大的被跳过分组为：[ 17, 18, 19, 20, 21, 22, 23 ]
当前处理分组为：[ 23, 22, 21, 20, 19, 18, 17 ]
当前数字：23，情况：处理失败--------------------------------
当前数字：22，情况：处理失败--------------------------------
保存跳过的分组：[21]
当前数字：20，情况：处理失败--------------------------------
保存跳过的分组：[19,18]
当前数字：17，情况：处理失败--------------------------------
一轮结束后，未处理的分组为：[ [ 43, 44, 45, 46, 47, 48, 49 ], [ 13, 14, 15 ], [ 39, 40, 41 ], [ 87, 88, 89 ], [ 97, 98, 99 ], [ 11 ], [ 37 ], [ 85 ], [ 95 ], [ 52 ], [ 21 ], [ 19, 18 ] ]


开始下一轮分组处理
当前最大的被跳过分组为：[ 43, 44, 45, 46, 47, 48, 49 ]
当前处理分组为：[ 49, 48, 47, 46, 45, 44, 43 ]
当前数字：49，情况：处理失败--------------------------------
当前数字：48，情况：处理失败--------------------------------
保存跳过的分组：[47]
当前数字：46，情况：处理失败--------------------------------
保存跳过的分组：[45,44]
当前数字：43，情况：处理失败--------------------------------
一轮结束后，未处理的分组为：[ [ 13, 14, 15 ], [ 39, 40, 41 ], [ 87, 88, 89 ], [ 97, 98, 99 ], [ 19, 18 ], [ 11 ], [ 37 ], [ 85 ], [ 95 ], [ 52 ], [ 21 ], [ 47 ], [ 45, 44 ] ]


开始下一轮分组处理
当前最大的被跳过分组为：[ 13, 14, 15 ]
当前处理分组为：[ 15, 14, 13 ]
当前数字：15，情况：处理失败--------------------------------
当前数字：14，情况：处理失败--------------------------------
当前数字：13，情况：处理失败--------------------------------
一轮结束后，未处理的分组为：[ [ 39, 40, 41 ], [ 87, 88, 89 ], [ 97, 98, 99 ], [ 19, 18 ], [ 45, 44 ], [ 11 ], [ 37 ], [ 85 ], [ 95 ], [ 52 ], [ 21 ], [ 47 ] ]


开始下一轮分组处理
当前最大的被跳过分组为：[ 39, 40, 41 ]
当前处理分组为：[ 41, 40, 39 ]
当前数字：41，情况：处理失败--------------------------------
当前数字：40，情况：处理失败--------------------------------
当前数字：39，情况：处理失败--------------------------------
一轮结束后，未处理的分组为：[ [ 87, 88, 89 ], [ 97, 98, 99 ], [ 19, 18 ], [ 45, 44 ], [ 11 ], [ 37 ], [ 85 ], [ 95 ], [ 52 ], [ 21 ], [ 47 ] ]


开始下一轮分组处理
当前最大的被跳过分组为：[ 87, 88, 89 ]
当前处理分组为：[ 89, 88, 87 ]
当前数字：89，情况：正常处理
当前数字：88，情况：正常处理
当前数字：87，情况：处理失败--------------------------------
一轮结束后，未处理的分组为：[ [ 97, 98, 99 ], [ 19, 18 ], [ 45, 44 ], [ 11 ], [ 37 ], [ 85 ], [ 95 ], [ 52 ], [ 21 ], [ 47 ] ]


开始下一轮分组处理
当前最大的被跳过分组为：[ 97, 98, 99 ]
当前处理分组为：[ 99, 98, 97 ]
当前数字：99，情况：正常处理
当前数字：98，情况：处理失败--------------------------------
当前数字：97，情况：处理失败--------------------------------
一轮结束后，未处理的分组为：[ [ 19, 18 ], [ 45, 44 ], [ 11 ], [ 37 ], [ 85 ], [ 95 ], [ 52 ], [ 21 ], [ 47 ] ]


开始下一轮分组处理
当前最大的被跳过分组为：[ 19, 18 ]
当前处理分组为：[ 19, 18 ]
当前数字：19，情况：处理失败--------------------------------
当前数字：18，情况：处理失败--------------------------------
一轮结束后，未处理的分组为：[ [ 45, 44 ], [ 11 ], [ 37 ], [ 85 ], [ 95 ], [ 52 ], [ 21 ], [ 47 ] ]


开始下一轮分组处理
当前最大的被跳过分组为：[ 45, 44 ]
当前处理分组为：[ 45, 44 ]
当前数字：45，情况：处理失败--------------------------------
当前数字：44，情况：处理失败--------------------------------
一轮结束后，未处理的分组为：[ [ 11 ], [ 37 ], [ 85 ], [ 95 ], [ 52 ], [ 21 ], [ 47 ] ]


开始下一轮分组处理
当前最大的被跳过分组为：[ 11 ]
当前处理分组为：[ 11 ]
当前数字：11，情况：处理失败--------------------------------
一轮结束后，未处理的分组为：[ [ 37 ], [ 85 ], [ 95 ], [ 52 ], [ 21 ], [ 47 ] ]


开始下一轮分组处理
当前最大的被跳过分组为：[ 37 ]
当前处理分组为：[ 37 ]
当前数字：37，情况：处理失败--------------------------------
一轮结束后，未处理的分组为：[ [ 85 ], [ 95 ], [ 52 ], [ 21 ], [ 47 ] ]


开始下一轮分组处理
当前最大的被跳过分组为：[ 85 ]
当前处理分组为：[ 85 ]
当前数字：85，情况：处理失败--------------------------------
一轮结束后，未处理的分组为：[ [ 95 ], [ 52 ], [ 21 ], [ 47 ] ]


开始下一轮分组处理
当前最大的被跳过分组为：[ 95 ]
当前处理分组为：[ 95 ]
当前数字：95，情况：处理失败--------------------------------
一轮结束后，未处理的分组为：[ [ 52 ], [ 21 ], [ 47 ] ]


开始下一轮分组处理
当前最大的被跳过分组为：[ 52 ]
当前处理分组为：[ 52 ]
当前数字：52，情况：处理失败--------------------------------
一轮结束后，未处理的分组为：[ [ 21 ], [ 47 ] ]


开始下一轮分组处理
当前最大的被跳过分组为：[ 21 ]
当前处理分组为：[ 21 ]
当前数字：21，情况：处理失败--------------------------------
一轮结束后，未处理的分组为：[ [ 47 ] ]


开始下一轮分组处理
当前最大的被跳过分组为：[ 47 ]
当前处理分组为：[ 47 ]
当前数字：47，情况：处理失败--------------------------------
一轮结束后，未处理的分组为：[ ]
处理结束
