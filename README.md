elasticsearch6的一个demo系统，包括创建，搜索索引（分组，分页，排序，高亮等） 

1.先搭建好elasticsearch，搭建参考网上教程 
2.创建es_test数据库，执行doc下的ss_ware_resource_info.sql 
3.开发工具idea安装lombok插件，程序里面有两个运行类 CreateESIndexService:创建索引类 SearchESIndexService：搜索索引类 分别执行即可

创建截图如下：
[main] INFO com.dayainfo.ssp.service.CreateESIndexService - 一共163416条记录
[main] INFO com.dayainfo.ssp.service.CreateESIndexService - 已索引10000条记录
[main] INFO com.dayainfo.ssp.service.CreateESIndexService - 已索引20000条记录
[main] INFO com.dayainfo.ssp.service.CreateESIndexService - 已索引30000条记录
[main] INFO com.dayainfo.ssp.service.CreateESIndexService - 已索引40000条记录
[main] INFO com.dayainfo.ssp.service.CreateESIndexService - 已索引50000条记录
[main] INFO com.dayainfo.ssp.service.CreateESIndexService - 已索引60000条记录
[main] INFO com.dayainfo.ssp.service.CreateESIndexService - 已索引70000条记录
[main] INFO com.dayainfo.ssp.service.CreateESIndexService - 已索引80000条记录
[main] INFO com.dayainfo.ssp.service.CreateESIndexService - 已索引90000条记录
[main] INFO com.dayainfo.ssp.service.CreateESIndexService - 已索引100000条记录
[main] INFO com.dayainfo.ssp.service.CreateESIndexService - 已索引110000条记录
[main] INFO com.dayainfo.ssp.service.CreateESIndexService - 已索引120000条记录
[main] INFO com.dayainfo.ssp.service.CreateESIndexService - 已索引130000条记录
[main] INFO com.dayainfo.ssp.service.CreateESIndexService - 已索引140000条记录
[main] INFO com.dayainfo.ssp.service.CreateESIndexService - 已索引150000条记录
[main] INFO com.dayainfo.ssp.service.CreateESIndexService - 已索引160000条记录
[main] INFO com.dayainfo.ssp.service.CreateESIndexService - 已索引163416条记录
[main] INFO com.dayainfo.ssp.service.CreateESIndexService - 索引创建结束
[main] INFO com.dayainfo.ssp.service.CreateESIndexService - 一共耗时：94秒

搜索截图如下：
[main]  获取client对象耗时：9992毫秒
[main]  【year分组情况】
[main]  2014 6
[main]  2015 3
[main]  2016 3
[main]  
[main]  【basic_id_contact_simple分组情况】
[main]  海南大学 6
[main]  海南省人民政府 3
[main]  海南省人民政府办公厅 3
[main]  
[main]  【basic_id_creator_simple分组情况】
[main]  贺海艳 3
[main]  
[main]  【basic_id_keyword_simple分组情况】
[main]  五指山市 12
[main]  临高县 3
[main]  儋州市 3
[main]  关键期 3
[main]  发展战略 3
[main]  品德高尚 3
[main]  国际旅游岛 3
[main]  省情 3
[main]  经济发展方式 3
[main]  
[main]  搜索到记录条数：10
[main]  【分页搜索结果详情】
[main]  IndexResourceInfoModel(...title_IK=五指山市“乡村论坛”协商治理模式研究, basic_id_contact_IK=海南大学...
[main]  IndexResourceInfoModel(...title_IK=五指山市“乡村论坛”协商治理模式研究, basic_id_contact_IK=海南大学...
[main]  IndexResourceInfoModel(...title_IK=五指山市“乡村论坛”协商治理模式研究, basic_id_contact_IK=海南大学...
[main]  IndexResourceInfoModel(...title_IK=五指山市发展养生养老地产的基本构想, basic_id_contact_IK=海南大学...
[main]  IndexResourceInfoModel(...title_IK=五指山市发展养生养老地产的基本构想, basic_id_contact_IK=海南大学...
[main]  IndexResourceInfoModel(...title_IK=五指山市发展养生养老地产的基本构想, basic_id_contact_IK=海南大学...
[main]  IndexResourceInfoModel(...title_IK=海南省人民政府办公厅关于印发海南省“十三五”规划编制工作方案的通知...
[main]  IndexResourceInfoModel(...title_IK=海南省人民政府办公厅关于印发海南省“十三五”规划编制工作方案的通知...
[main]  IndexResourceInfoModel(...title_IK=海南省人民政府关于表彰海南省劳动模范和先进工作者的决定...
[main]  IndexResourceInfoModel(...title_IK=海南省人民政府关于表彰海南省劳动模范和先进工作者的决定...
[main]  搜索耗时：280毫秒
