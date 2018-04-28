# elasticsearch6_demo
elasticsearch6的一个demo系统，包括创建，搜索索引（分组，分页，排序，高亮等）
1.先搭建好elasticsearch，搭建参考网上教程
2.创建es_test数据库，执行doc下的ss_ware_resource_info.sql
3.程序里面有两个运行类
    CreateESIndexService:创建索引类
    SearchESIndexService：搜索索引类
 分别执行即可
 
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
[main] INFO com.dayainfo.ssp.service.SearchESIndexService - 获取client对象耗时：9992毫秒
[main] INFO com.dayainfo.ssp.service.SearchESIndexService - 【year分组情况】
[main] INFO com.dayainfo.ssp.service.SearchESIndexService - 2014 6
[main] INFO com.dayainfo.ssp.service.SearchESIndexService - 2015 3
[main] INFO com.dayainfo.ssp.service.SearchESIndexService - 2016 3
[main] INFO com.dayainfo.ssp.service.SearchESIndexService - 
[main] INFO com.dayainfo.ssp.service.SearchESIndexService - 【basic_id_contact_simple分组情况】
[main] INFO com.dayainfo.ssp.service.SearchESIndexService - 海南大学 6
[main] INFO com.dayainfo.ssp.service.SearchESIndexService - 海南省人民政府 3
[main] INFO com.dayainfo.ssp.service.SearchESIndexService - 海南省人民政府办公厅 3
[main] INFO com.dayainfo.ssp.service.SearchESIndexService - 
[main] INFO com.dayainfo.ssp.service.SearchESIndexService - 【basic_id_creator_simple分组情况】
[main] INFO com.dayainfo.ssp.service.SearchESIndexService - 贺海艳 3
[main] INFO com.dayainfo.ssp.service.SearchESIndexService - 
[main] INFO com.dayainfo.ssp.service.SearchESIndexService - 【basic_id_keyword_simple分组情况】
[main] INFO com.dayainfo.ssp.service.SearchESIndexService - 五指山市 12
[main] INFO com.dayainfo.ssp.service.SearchESIndexService - 临高县 3
[main] INFO com.dayainfo.ssp.service.SearchESIndexService - 儋州市 3
[main] INFO com.dayainfo.ssp.service.SearchESIndexService - 关键期 3
[main] INFO com.dayainfo.ssp.service.SearchESIndexService - 发展战略 3
[main] INFO com.dayainfo.ssp.service.SearchESIndexService - 品德高尚 3
[main] INFO com.dayainfo.ssp.service.SearchESIndexService - 国际旅游岛 3
[main] INFO com.dayainfo.ssp.service.SearchESIndexService - 省情 3
[main] INFO com.dayainfo.ssp.service.SearchESIndexService - 经济发展方式 3
[main] INFO com.dayainfo.ssp.service.SearchESIndexService - 
[main] INFO com.dayainfo.ssp.service.SearchESIndexService - 搜索到记录条数：10
[main] INFO com.dayainfo.ssp.service.SearchESIndexService - 【分页搜索结果详情】
[main] INFO com.dayainfo.ssp.service.SearchESIndexService - IndexResourceInfoModel(unit_id=867, resource_id=c75a594a-5db4-42f6-b17d-45f375c7b4a7, res_ware_id_simple=9f3aacba-b5e3-4bc3-8a88-39d2085e1f49, title_IK=五指山市“乡村论坛”协商治理模式研究, basic_id_contact_IK=海南大学, basic_id_contact_simple=海南大学, basic_id_creator_simple=贺海艳, basic_id_keyword_IK=五指山市, basic_id_keyword_simple=五指山市, year=2016, basic_description_IK=近年来,国家的1号文件必定是关于农业、农村、农民的有关政策,解决“三农”问题一直是党和国家各项工作的重中之重。长期以来,中国农村一直处在某些精英的治理之下,中国古代由于“王权止于县政”的治理理念,中国的广大农村一直都处在乡绅的治理之下,这也是中国古代农村的精英阶层。近现代,中国部分国土沦丧,为了加强对广大的农村的治理,英法等资本主义国家通过加强与当地乡绅的联系,利用乡绅强化了对中国广大农村的治理。新中国成立以后,虽然经受过人民公社治理模式的治理,但是在改革开放以后,中国乡村开始出现了村民自治的乡政村治治理模式,这种模式下,乡村精英治理模式一直占据着相对主导的地位,在农村社会治理中处于强势阶层,乡村精英治理模式也对推动中国农村经济社会的发展产生了积极作用。然而,近年来,农村社会治理方面出现了“乌坎事件”等群体性突发事件。中国农村社会该如何有效治理成为一个亟待研究的课题。本文共分为七大部分,通过采用文献研究法、实地调研法和比较研究法等在对五指山市水满乡新村“乡村论坛”协商治理进行实地调研的基础上,运用公共管理学公共选择理论、政府失灵等有关理论,对比东部沿海地区乡村协商治理的成功经验,提出推进五指山市“乡村论坛”协商治理模式的对策与措施,为农村社会治理,特别是中西部地区农村社会治理提供一些有价值的借鉴。)
[main] INFO com.dayainfo.ssp.service.SearchESIndexService - IndexResourceInfoModel(unit_id=867, resource_id=ba33953b-5b16-4310-a463-e52da95ee100, res_ware_id_simple=d1d5c672-7615-43fe-b321-a7f3aa2b9aae, title_IK=五指山市“乡村论坛”协商治理模式研究, basic_id_contact_IK=海南大学, basic_id_contact_simple=海南大学, basic_id_creator_simple=贺海艳, basic_id_keyword_IK=五指山市, basic_id_keyword_simple=五指山市, year=2016, basic_description_IK=近年来,国家的1号文件必定是关于农业、农村、农民的有关政策,解决“三农”问题一直是党和国家各项工作的重中之重。长期以来,中国农村一直处在某些精英的治理之下,中国古代由于“王权止于县政”的治理理念,中国的广大农村一直都处在乡绅的治理之下,这也是中国古代农村的精英阶层。近现代,中国部分国土沦丧,为了加强对广大的农村的治理,英法等资本主义国家通过加强与当地乡绅的联系,利用乡绅强化了对中国广大农村的治理。新中国成立以后,虽然经受过人民公社治理模式的治理,但是在改革开放以后,中国乡村开始出现了村民自治的乡政村治治理模式,这种模式下,乡村精英治理模式一直占据着相对主导的地位,在农村社会治理中处于强势阶层,乡村精英治理模式也对推动中国农村经济社会的发展产生了积极作用。然而,近年来,农村社会治理方面出现了“乌坎事件”等群体性突发事件。中国农村社会该如何有效治理成为一个亟待研究的课题。本文共分为七大部分,通过采用文献研究法、实地调研法和比较研究法等在对五指山市水满乡新村“乡村论坛”协商治理进行实地调研的基础上,运用公共管理学公共选择理论、政府失灵等有关理论,对比东部沿海地区乡村协商治理的成功经验,提出推进五指山市“乡村论坛”协商治理模式的对策与措施,为农村社会治理,特别是中西部地区农村社会治理提供一些有价值的借鉴。)
[main] INFO com.dayainfo.ssp.service.SearchESIndexService - IndexResourceInfoModel(unit_id=1, resource_id=740fe1e7-a7c0-4d04-b2cd-9dfd159967b1, res_ware_id_simple=d5ec2020-51c9-4929-b517-432af64be519, title_IK=五指山市“乡村论坛”协商治理模式研究, basic_id_contact_IK=海南大学, basic_id_contact_simple=海南大学, basic_id_creator_simple=贺海艳, basic_id_keyword_IK=五指山市, basic_id_keyword_simple=五指山市, year=2016, basic_description_IK=近年来,国家的1号文件必定是关于农业、农村、农民的有关政策,解决“三农”问题一直是党和国家各项工作的重中之重。长期以来,中国农村一直处在某些精英的治理之下,中国古代由于“王权止于县政”的治理理念,中国的广大农村一直都处在乡绅的治理之下,这也是中国古代农村的精英阶层。近现代,中国部分国土沦丧,为了加强对广大的农村的治理,英法等资本主义国家通过加强与当地乡绅的联系,利用乡绅强化了对中国广大农村的治理。新中国成立以后,虽然经受过人民公社治理模式的治理,但是在改革开放以后,中国乡村开始出现了村民自治的乡政村治治理模式,这种模式下,乡村精英治理模式一直占据着相对主导的地位,在农村社会治理中处于强势阶层,乡村精英治理模式也对推动中国农村经济社会的发展产生了积极作用。然而,近年来,农村社会治理方面出现了“乌坎事件”等群体性突发事件。中国农村社会该如何有效治理成为一个亟待研究的课题。本文共分为七大部分,通过采用文献研究法、实地调研法和比较研究法等在对五指山市水满乡新村“乡村论坛”协商治理进行实地调研的基础上,运用公共管理学公共选择理论、政府失灵等有关理论,对比东部沿海地区乡村协商治理的成功经验,提出推进五指山市“乡村论坛”协商治理模式的对策与措施,为农村社会治理,特别是中西部地区农村社会治理提供一些有价值的借鉴。)
[main] INFO com.dayainfo.ssp.service.SearchESIndexService - IndexResourceInfoModel(unit_id=867, resource_id=18085b59-400f-412a-b187-688914dc1b08, res_ware_id_simple=9f3aacba-b5e3-4bc3-8a88-39d2085e1f49, title_IK=五指山市发展养生养老地产的基本构想, basic_id_contact_IK=海南大学, basic_id_contact_simple=海南大学, basic_id_creator_simple=, basic_id_keyword_IK=五指山市, basic_id_keyword_simple=五指山市, year=2015, basic_description_IK=随着房地产业转型诉求增大和中国老龄化程度深化，养生养老地产成为热门转型方向。五指山市以其得天独厚的区位优势成为养生养老地产发展的热门地区。为保证养老养生地产在五指山健康发展，本文对养生养老地产的发展提出基本构想。)
[main] INFO com.dayainfo.ssp.service.SearchESIndexService - IndexResourceInfoModel(unit_id=1, resource_id=e21e490b-a4a1-421b-94ea-e5866d3fc5a7, res_ware_id_simple=d5ec2020-51c9-4929-b517-432af64be519, title_IK=五指山市发展养生养老地产的基本构想, basic_id_contact_IK=海南大学, basic_id_contact_simple=海南大学, basic_id_creator_simple=, basic_id_keyword_IK=五指山市, basic_id_keyword_simple=五指山市, year=2015, basic_description_IK=随着房地产业转型诉求增大和中国老龄化程度深化，养生养老地产成为热门转型方向。五指山市以其得天独厚的区位优势成为养生养老地产发展的热门地区。为保证养老养生地产在五指山健康发展，本文对养生养老地产的发展提出基本构想。)
[main] INFO com.dayainfo.ssp.service.SearchESIndexService - IndexResourceInfoModel(unit_id=867, resource_id=fe6894f5-a487-44d3-9c1a-2c414b1d5a8e, res_ware_id_simple=d1d5c672-7615-43fe-b321-a7f3aa2b9aae, title_IK=五指山市发展养生养老地产的基本构想, basic_id_contact_IK=海南大学, basic_id_contact_simple=海南大学, basic_id_creator_simple=, basic_id_keyword_IK=五指山市, basic_id_keyword_simple=五指山市, year=2015, basic_description_IK=随着房地产业转型诉求增大和中国老龄化程度深化，养生养老地产成为热门转型方向。五指山市以其得天独厚的区位优势成为养生养老地产发展的热门地区。为保证养老养生地产在五指山健康发展，本文对养生养老地产的发展提出基本构想。)
[main] INFO com.dayainfo.ssp.service.SearchESIndexService - IndexResourceInfoModel(unit_id=867, resource_id=03f0dff2-52f4-4407-bef4-b6783e1cbdf5, res_ware_id_simple=d1d5c672-7615-43fe-b321-a7f3aa2b9aae, title_IK=海南省人民政府办公厅关于印发海南省“十三五”规划编制工作方案的通知, basic_id_contact_IK=海南省人民政府办公厅, basic_id_contact_simple=海南省人民政府办公厅, basic_id_creator_simple=null, basic_id_keyword_IK=关键期;国际旅游岛;经济发展方式;省情;五指山市, basic_id_keyword_simple=关键期;国际旅游岛;经济发展方式;省情;五指山市, year=2014, basic_description_IK=琼府办[2014]97号各市、县、自治县人民政府,省政府直属各单位:《海南省'十三五'规划编制工作方案》已经省政府同意,现印发给你们,请认真贯彻执行。海南省人民政府办公厅2014年7月17日(此件主动公开)海南省'十三五'规划编制工作方案即将进入的'十三五'时期,是全面建成小康社会、基本完成国际旅游岛建设主要任务的决胜期,是转变经济发展方式的关键期,是全面深化改革的攻坚期,编制和实施好'十三五'规划,对于加快科学发展、绿色崛起,争创中国特色社会主义实践范例,谱写美丽中国海南篇章具有重要意义。全省'十三五'规划编制工作覆盖范围广、涉及领域多、工作任务重、时序安排紧,为贯彻落实好全国'十三五'规划编制工作电视)
[main] INFO com.dayainfo.ssp.service.SearchESIndexService - IndexResourceInfoModel(unit_id=867, resource_id=5bc3366d-4e7e-49ef-8afc-f0978c4a562f, res_ware_id_simple=9f3aacba-b5e3-4bc3-8a88-39d2085e1f49, title_IK=海南省人民政府办公厅关于印发海南省“十三五”规划编制工作方案的通知, basic_id_contact_IK=海南省人民政府办公厅, basic_id_contact_simple=海南省人民政府办公厅, basic_id_creator_simple=null, basic_id_keyword_IK=关键期;国际旅游岛;经济发展方式;省情;五指山市, basic_id_keyword_simple=关键期;国际旅游岛;经济发展方式;省情;五指山市, year=2014, basic_description_IK=琼府办[2014]97号各市、县、自治县人民政府,省政府直属各单位:《海南省'十三五'规划编制工作方案》已经省政府同意,现印发给你们,请认真贯彻执行。海南省人民政府办公厅2014年7月17日(此件主动公开)海南省'十三五'规划编制工作方案即将进入的'十三五'时期,是全面建成小康社会、基本完成国际旅游岛建设主要任务的决胜期,是转变经济发展方式的关键期,是全面深化改革的攻坚期,编制和实施好'十三五'规划,对于加快科学发展、绿色崛起,争创中国特色社会主义实践范例,谱写美丽中国海南篇章具有重要意义。全省'十三五'规划编制工作覆盖范围广、涉及领域多、工作任务重、时序安排紧,为贯彻落实好全国'十三五'规划编制工作电视)
[main] INFO com.dayainfo.ssp.service.SearchESIndexService - IndexResourceInfoModel(unit_id=867, resource_id=ad981f67-1a22-4957-b8b7-efe89df65bbb, res_ware_id_simple=9f3aacba-b5e3-4bc3-8a88-39d2085e1f49, title_IK=海南省人民政府关于表彰海南省劳动模范和先进工作者的决定, basic_id_contact_IK=海南省人民政府, basic_id_contact_simple=海南省人民政府, basic_id_creator_simple=null, basic_id_keyword_IK=儋州市;发展战略;临高县;品德高尚;五指山市, basic_id_keyword_simple=儋州市;发展战略;临高县;品德高尚;五指山市, year=2014, basic_description_IK=琼府[2014]67号各市、县、自治县人民政府,省政府直属各单位:自2009年海南省第五次劳动模范和先进工作者表彰大会以来,全省各族人民在省委、省政府的正确领导下,高举中国特色社会主义伟大旗帜,紧紧围绕海南'科学发展、绿色崛起'的发展战略,焕发劳动热情、释放创造潜能、勇于拼搏奉献,各行各业、各条战线涌现出一大批品德高尚、奋发有为、业绩显著、贡献突出的先进模范人物,他们是坚守信念、追求梦想的)
[main] INFO com.dayainfo.ssp.service.SearchESIndexService - IndexResourceInfoModel(unit_id=867, resource_id=4cce39e0-d1b0-462d-ac33-7261547ae8b1, res_ware_id_simple=d1d5c672-7615-43fe-b321-a7f3aa2b9aae, title_IK=海南省人民政府关于表彰海南省劳动模范和先进工作者的决定, basic_id_contact_IK=海南省人民政府, basic_id_contact_simple=海南省人民政府, basic_id_creator_simple=null, basic_id_keyword_IK=儋州市;发展战略;临高县;品德高尚;五指山市, basic_id_keyword_simple=儋州市;发展战略;临高县;品德高尚;五指山市, year=2014, basic_description_IK=琼府[2014]67号各市、县、自治县人民政府,省政府直属各单位:自2009年海南省第五次劳动模范和先进工作者表彰大会以来,全省各族人民在省委、省政府的正确领导下,高举中国特色社会主义伟大旗帜,紧紧围绕海南'科学发展、绿色崛起'的发展战略,焕发劳动热情、释放创造潜能、勇于拼搏奉献,各行各业、各条战线涌现出一大批品德高尚、奋发有为、业绩显著、贡献突出的先进模范人物,他们是坚守信念、追求梦想的)
[main] INFO com.dayainfo.ssp.service.SearchESIndexService - 搜索耗时：280毫秒
