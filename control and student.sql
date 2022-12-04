/*
 Navicat Premium Data Transfer

 Source Server         : sqliteWeb
 Source Server Type    : SQLite
 Source Server Version : 3017000
 Source Schema         : main

 Target Server Type    : SQLite
 Target Server Version : 3017000
 File Encoding         : 65001

 Date: 04/12/2022 16:38:22
*/

PRAGMA foreign_keys = false;

-- ----------------------------
-- Table structure for cyt_admin
-- ----------------------------
DROP TABLE IF EXISTS "cyt_admin";
CREATE TABLE "cyt_admin" (
  "id" INTEGER NOT NULL,
  "num" text NOT NULL,
  "password" TEXT NOT NULL DEFAULT 123,
  "del" integer NOT NULL DEFAULT 0,
  "memo" TEXT,
  "name" TEXT,
  "role" integer NOT NULL DEFAULT 0,
  PRIMARY KEY ("id")
);

-- ----------------------------
-- Records of "cyt_admin"
-- ----------------------------
INSERT INTO "cyt_admin" VALUES (1, 'admin', 123, 0, NULL, 'admin', 0);

-- ----------------------------
-- Table structure for cyt_doing_record
-- ----------------------------
DROP TABLE IF EXISTS "cyt_doing_record";
CREATE TABLE "cyt_doing_record" (
  "id" INTEGER NOT NULL,
  "user_id" INTEGER NOT NULL,
  "start_time" TEXT NOT NULL,
  "end_time" TEXT,
  "title" TEXT,
  "context" TEXT,
  "status" integer NOT NULL,
  "del" integer NOT NULL DEFAULT 0,
  "memo" TEXT,
  "day_time" TEXT,
  PRIMARY KEY ("id")
);

-- ----------------------------
-- Records of "cyt_doing_record"
-- ----------------------------
INSERT INTO "cyt_doing_record" VALUES (1, 1, 1577430749559, 1577430790000, '及时安排', '天有不测风云，人有祸夕旦福', 1, 0, NULL, 1577376000000);
INSERT INTO "cyt_doing_record" VALUES (2, 1, 1577430749559, 1577430790000, '现在开始', '简要的内容', 1, 0, NULL, 1577376000000);
INSERT INTO "cyt_doing_record" VALUES (3, 1, 1577455232836, 1577461428056, '安卓开发', '马上开始，进行安卓的开发webview', 1, 0, NULL, 1577376000000);
INSERT INTO "cyt_doing_record" VALUES (4, 1, 1577456511486, 1577461390589, '软硬件结构设计', '要设计一个公司考勤系统', 1, 0, NULL, 1577376000000);
INSERT INTO "cyt_doing_record" VALUES (5, 1, 1577461577234, 1577461723263, '游戏', '娱乐两把，休息休息', 1, 0, NULL, 1577376000000);
INSERT INTO "cyt_doing_record" VALUES (6, 1, 1577461744268, 1577461761221, '数据库', '数据库的实验做完', 1, 0, NULL, 1577376000000);
INSERT INTO "cyt_doing_record" VALUES (7, 1, 1577462707870, 1577463010932, '画图', '建筑模型的设计，幼儿园设计', 1, 0, NULL, 1577462400000);
INSERT INTO "cyt_doing_record" VALUES (8, 1, 1577463080371, 1577463144166, '建筑模型', '完善建筑模型结构', 1, 0, NULL, 1577462400000);
INSERT INTO "cyt_doing_record" VALUES (9, 1, 1577463157302, 1577463162520, '数据库', '数据库考试的复习，以及数据库实验九，十，十一', 1, 0, NULL, 1577462400000);
INSERT INTO "cyt_doing_record" VALUES (10, 1, 1577470518136, 1577470970975, 'CSDN', '在CSDN上面学东西，看js代码进行积累', 1, 0, NULL, 1577462400000);
INSERT INTO "cyt_doing_record" VALUES (11, 1, 1577470997570, 1577471088970, '测试', '测试一下可不可以，在一分钟内停止任务', 1, 0, NULL, 1577462400000);
INSERT INTO "cyt_doing_record" VALUES (12, 1, 1577471436693, 1577515300880, '2.30💤', '深夜两点半想睡觉💤💤💤', 1, 0, NULL, 1577462400000);
INSERT INTO "cyt_doing_record" VALUES (13, 1, 1577517374678, 1577517447835, '做模型', '魔法大楼', 1, 0, NULL, 1577462400000);
INSERT INTO "cyt_doing_record" VALUES (14, 1, 1577517554742, 1577517770671, '继续模型', '魔方🔮小可爱', 1, 0, NULL, 1577462400000);
INSERT INTO "cyt_doing_record" VALUES (15, 1, 1577517783731, 1577518377734, '测试', '测试🚾', 1, 0, NULL, 1577462400000);
INSERT INTO "cyt_doing_record" VALUES (16, 2, 1577517854116, 1577517963813, 'Android', 'andriod相关的开发', 1, 0, NULL, 1577462400000);
INSERT INTO "cyt_doing_record" VALUES (17, 1, 1577520048764, 1577520115970, '重新开始', '煎药内容，😂😁😃😉😄😊☺️😍😏😒😓😔😔😖😘😞', 1, 0, NULL, 1577462400000);
INSERT INTO "cyt_doing_record" VALUES (18, 1, 1577522308543, 1577522389209, '画图', '教学楼', 1, 0, NULL, 1577462400000);
INSERT INTO "cyt_doing_record" VALUES (19, 1, 1577539162041, 1577539227526, '画图', '建筑物理', 1, 0, NULL, 1577462400000);
INSERT INTO "cyt_doing_record" VALUES (20, 5, 1577539558027, NULL, '公司考勤', '公司考勤系统的设计', 0, 0, NULL, 1577462400000);
INSERT INTO "cyt_doing_record" VALUES (21, 7, 1577551667472, 1577552042592, '晚上', '学习时间', 1, 0, NULL, 1577548800000);
INSERT INTO "cyt_doing_record" VALUES (22, 1, 1577686618575, 1577686710519, 'web', '上课', 1, 0, NULL, 1577635200000);
INSERT INTO "cyt_doing_record" VALUES (23, 1, 1577686787019, 1577687128902, '睡觉💤', '少时诵诗书', 1, 0, NULL, 1577635200000);
INSERT INTO "cyt_doing_record" VALUES (24, 1, 1577864426857, 1577864593884, '今日份任务', '学习吧', 1, 0, NULL, 1577808000000);
INSERT INTO "cyt_doing_record" VALUES (25, 1, 1577864631215, 1577864731577, 1, 123, 1, 0, NULL, 1577808000000);
INSERT INTO "cyt_doing_record" VALUES (26, 1, 1577864746233, 1577864807723, 123111, 1111, 1, 0, NULL, 1577808000000);
INSERT INTO "cyt_doing_record" VALUES (27, 1, 1577864840972, 1577865268497, 123, 1111, 1, 0, NULL, 1577808000000);
INSERT INTO "cyt_doing_record" VALUES (28, 1, 1577898062301, 1577898522472, '开始', '开始吧 and 快睡吧', 1, 0, NULL, 1577894400000);
INSERT INTO "cyt_doing_record" VALUES (29, 1, 1577899729265, 1577899983812, '模型', '做模型😏', 1, 0, NULL, 1577894400000);
INSERT INTO "cyt_doing_record" VALUES (30, 7, 1577949543074, NULL, 123, 321, 0, 0, NULL, 1577894400000);
INSERT INTO "cyt_doing_record" VALUES (31, 9, 1577960013396, 1577960974759, '画图', '把景观画完今晚', 1, 0, NULL, 1577894400000);
INSERT INTO "cyt_doing_record" VALUES (32, 10, 1577962643096, NULL, '💤睡觉', '上床睡觉，朱一旦🐷😁', 0, 0, NULL, 1577894400000);
INSERT INTO "cyt_doing_record" VALUES (33, 1, 1577964397873, 1578027856968, 'Javaweb', 'web报告的整理与提交', 1, 0, NULL, 1577894400000);
INSERT INTO "cyt_doing_record" VALUES (34, 11, 1578025713192, 1578025853146, '游戏👿', '英雄联盟', 1, 0, NULL, 1577980800000);
INSERT INTO "cyt_doing_record" VALUES (35, 1, 1578026143171, 1578027856968, 'Android测试', '安卓课前测试', 1, 0, NULL, 1577980800000);
INSERT INTO "cyt_doing_record" VALUES (36, 1, 1578028807550, 1578029377448, '计划设定', '设定一些计划的事列，演示可用到🤔', 1, 0, NULL, 1577980800000);
INSERT INTO "cyt_doing_record" VALUES (37, 1, 1578033337643, 1578033626858, '计划', '回你😃', 1, 0, NULL, 1577980800000);
INSERT INTO "cyt_doing_record" VALUES (38, 1, 1578033722792, 1578033792486, '干dzy', '明天干😃😍😍😍😍😍😍😍', 1, 0, NULL, 1577980800000);
INSERT INTO "cyt_doing_record" VALUES (39, 1, 1618384637158, 1618384701926, '胡歌', '操了', 1, 0, NULL, 1618329600000);
INSERT INTO "cyt_doing_record" VALUES (40, 1, 1618546290893, 1618546455976, '0101', '01010', 1, 0, NULL, 1618502400000);
INSERT INTO "cyt_doing_record" VALUES (41, 1, 1618980429706, 1618980513689, '代码维护', '对于守住时间项目的代码维护', 1, 0, NULL, 1618934400000);
INSERT INTO "cyt_doing_record" VALUES (42, 1, 1618981751922, 1618982748999, '写报告', '对报告论文的整写😀', 1, 0, NULL, 1618934400000);
INSERT INTO "cyt_doing_record" VALUES (43, 1, 1618987488762, 1618987661713, '突出重点', '胡慈溪', 1, 0, NULL, 1618934400000);
INSERT INTO "cyt_doing_record" VALUES (44, 1, 1618988623436, 1618988708008, 'yanshi', '哈哈哈', 1, 0, NULL, 1618934400000);

-- ----------------------------
-- Table structure for cyt_user
-- ----------------------------
DROP TABLE IF EXISTS "cyt_user";
CREATE TABLE "cyt_user" (
  "id" INTEGER NOT NULL,
  "name" TEXT,
  "num" text NOT NULL,
  "password" TEXT NOT NULL DEFAULT 123,
  "create_time" TEXT,
  "del" integer DEFAULT 0,
  "memo" TEXT,
  "role" integer NOT NULL DEFAULT 1,
  PRIMARY KEY ("id")
);

-- ----------------------------
-- Records of "cyt_user"
-- ----------------------------
INSERT INTO "cyt_user" VALUES (1, '咚', 19551609368, 123, 0, 0, NULL, 1);
INSERT INTO "cyt_user" VALUES (2, '董', 15674004952, 123, 0, 0, NULL, 1);
INSERT INTO "cyt_user" VALUES (3, '胡哥', 15773738243, 123, 0, 0, NULL, 1);
INSERT INTO "cyt_user" VALUES (4, 'dzy', 1327293674, 123, 0, 0, NULL, 1);
INSERT INTO "cyt_user" VALUES (6, 'pcx', '1040953154@qq.com', 123, 1577538811582, 0, NULL, 1);
INSERT INTO "cyt_user" VALUES (7, '东', '1327293674@qq.com', '000', 1577551543016, 0, NULL, 1);
INSERT INTO "cyt_user" VALUES (8, '王嘉鑫', '1244705662@qq.com', '000', 1577687516767, 0, NULL, 1);
INSERT INTO "cyt_user" VALUES (9, '蘭蘭蘭', '1219240742@qq.com', 'peng0614', 1577959754652, 0, NULL, 1);
INSERT INTO "cyt_user" VALUES (10, '华哥', '530468207@qq.com', 123, 1577962445859, 0, NULL, 1);
INSERT INTO "cyt_user" VALUES (11, '邓志鑫', '2625629506@qq.com', 123, 1578025257353, 0, NULL, 1);
INSERT INTO "cyt_user" VALUES (12, 123, '1046505087@qq.com', 123, 1618981469107, 0, NULL, 1);

-- ----------------------------
-- Table structure for cyt_willing_example
-- ----------------------------
DROP TABLE IF EXISTS "cyt_willing_example";
CREATE TABLE "cyt_willing_example" (
  "id" INTEGER NOT NULL,
  "user_id" INTEGER NOT NULL,
  "start_time" TEXT NOT NULL,
  "end_time" TEXT NOT NULL,
  "title" TEXT NOT NULL,
  "context" TEXT,
  "del" integer DEFAULT 0,
  "memo" TEXT,
  PRIMARY KEY ("id")
);

-- ----------------------------
-- Records of "cyt_willing_example"
-- ----------------------------
INSERT INTO "cyt_willing_example" VALUES (1, 1, 58500, 62400, '双』英语😂', '六级的相关知识，作文，听力', 0, NULL);
INSERT INTO "cyt_willing_example" VALUES (2, 1, 28800, 36000, '双』数学', '高等数学，线性代数', 0, NULL);
INSERT INTO "cyt_willing_example" VALUES (3, 1, 36000, 41400, '双』数据结构', '链表的相关知识储备', 0, NULL);
INSERT INTO "cyt_willing_example" VALUES (4, 1, 54000, 57600, '单』Java', 'java语言的学习', 0, NULL);
INSERT INTO "cyt_willing_example" VALUES (5, 1, 68400, 75600, 'Andriod', '安卓的相关训练', 1, NULL);
INSERT INTO "cyt_willing_example" VALUES (6, 2, 18000, 25200, 'JavaWeb', '写一个网站，学生成绩管理系统', 0, NULL);
INSERT INTO "cyt_willing_example" VALUES (7, 2, 32400, 39600, '安卓', '写一个原生APP', 0, NULL);
INSERT INTO "cyt_willing_example" VALUES (8, 2, 45000, 51300, '休息💤', '睡觉💤是最美的事情', 0, NULL);
INSERT INTO "cyt_willing_example" VALUES (9, 3, 28800, 43200, '英雄联盟', '打游戏充实自己', 0, NULL);
INSERT INTO "cyt_willing_example" VALUES (10, 1, 25200, 32400, '单』尽快', '💤休息睡觉', 0, NULL);
INSERT INTO "cyt_willing_example" VALUES (11, 5, 76800, 82800, '软硬件😌', '软硬件设计作业', 0, NULL);
INSERT INTO "cyt_willing_example" VALUES (12, 7, 25200, 32400, '英语', '早起ﾉ☀的第一缕阳光预定是给英语的。', 0, NULL);
INSERT INTO "cyt_willing_example" VALUES (13, 7, 36000, 43200, '数学', '肯定要做数学的啊', 0, NULL);
INSERT INTO "cyt_willing_example" VALUES (14, 1, 28800, 34800, '双』安卓', '安卓课堂演示', 0, NULL);
INSERT INTO "cyt_willing_example" VALUES (15, 1, 36000, 43200, '单』画图', '画😂😂😃😄😏', 0, NULL);
INSERT INTO "cyt_willing_example" VALUES (16, 9, 27000, 37200, '把图画完', '哦耶', 0, NULL);
INSERT INTO "cyt_willing_example" VALUES (17, 11, 28800, 34800, '外国建筑史😂', '建筑史的复习', 0, NULL);
INSERT INTO "cyt_willing_example" VALUES (18, 11, 36000, 42000, '建筑结构', '结构力学', 0, NULL);
INSERT INTO "cyt_willing_example" VALUES (19, 11, 51000, 57600, '外国建筑史', '继续建筑史', 0, NULL);
INSERT INTO "cyt_willing_example" VALUES (20, 1, 57600, 63000, '双』计算机网络', '总体复习', 0, NULL);
INSERT INTO "cyt_willing_example" VALUES (21, 1, 28800, 34920, '周』算法+Java', '力扣算法的刷题', 0, NULL);
INSERT INTO "cyt_willing_example" VALUES (22, 1, 36000, 41400, '周』数学复习', '高等数学，线性代数🦘', 0, NULL);
INSERT INTO "cyt_willing_example" VALUES (23, 1, 52200, 57000, '周』Java', '毕向东Java的经典回顾', 1, NULL);
INSERT INTO "cyt_willing_example" VALUES (24, 1, 57900, 63900, '周』专业相关', '基础专业课:数据结构，操作系统大杂烩', 0, NULL);
INSERT INTO "cyt_willing_example" VALUES (25, 1, 67200, 74400, '周』英语🤪', '词汇+阅读📖，并且每周一套试卷', 0, NULL);

-- ----------------------------
-- Table structure for cyt_willing_plan
-- ----------------------------
DROP TABLE IF EXISTS "cyt_willing_plan";
CREATE TABLE "cyt_willing_plan" (
  "id" INTEGER NOT NULL,
  "user_id" INTEGER NOT NULL,
  "start_time" TEXT NOT NULL,
  "end_time" TEXT NOT NULL,
  "title" TEXT,
  "context" TEXT,
  "status" integer NOT NULL,
  "del" integer NOT NULL DEFAULT 0,
  "memo" TEXT,
  "import_time" TEXT NOT NULL,
  "day_time" TEXT,
  PRIMARY KEY ("id")
);

-- ----------------------------
-- Records of "cyt_willing_plan"
-- ----------------------------
INSERT INTO "cyt_willing_plan" VALUES (1, 1, 58500, 62400, '英语', '六级的相关知识，作文，听力等等', 0, 1, NULL, 1577424472040, 1577376000000);
INSERT INTO "cyt_willing_plan" VALUES (2, 1, 28800, 36000, '数学', '高等数学，线性代数', 0, 1, NULL, 1577424472148, 1577376000000);
INSERT INTO "cyt_willing_plan" VALUES (3, 1, 36000, 41400, '数据结构', '链表的相关知识储备', 0, 1, NULL, 1577424472246, 1577376000000);
INSERT INTO "cyt_willing_plan" VALUES (4, 1, 54000, 57600, 'Java', 'java语言的学习', 0, 1, NULL, 1577424472322, 1577376000000);
INSERT INTO "cyt_willing_plan" VALUES (5, 1, 68400, 75600, 'Andriod', '安卓的相关训练', 0, 1, NULL, 1577424472405, 1577376000000);
INSERT INTO "cyt_willing_plan" VALUES (6, 2, 18000, 25200, 'JavaWeb', '写一个网站，学生成绩管理系统', 0, 0, NULL, 1577424509223, 1577376000000);
INSERT INTO "cyt_willing_plan" VALUES (7, 2, 32400, 39600, '安卓', '写一个原生APP', 0, 0, NULL, 1577424509307, 1577376000000);
INSERT INTO "cyt_willing_plan" VALUES (8, 2, 45000, 51300, '休息💤', '睡觉💤是最美的事情', 0, 0, NULL, 1577424509408, 1577376000000);
INSERT INTO "cyt_willing_plan" VALUES (9, 1, 54000, 57600, 'Java', 'java语言的学习', 0, 1, NULL, 1577426609256, 1577376000000);
INSERT INTO "cyt_willing_plan" VALUES (10, 1, 68400, 75600, 'Andriod', '安卓的相关训练', 0, 1, NULL, 1577426609350, 1577376000000);
INSERT INTO "cyt_willing_plan" VALUES (11, 3, 28800, 43200, '英雄联盟', '打游戏充实自己', 0, 0, NULL, 1577426818555, 1577376000000);
INSERT INTO "cyt_willing_plan" VALUES (12, 1, 58500, 62400, '英语', '六级的相关知识，作文，听力等等', 1, 0, NULL, 1577430749327, 1577376000000);
INSERT INTO "cyt_willing_plan" VALUES (13, 1, 36000, 41400, '数据结构', '链表的相关知识储备', 0, 0, NULL, 1577430749432, 1577376000000);
INSERT INTO "cyt_willing_plan" VALUES (14, 1, 28800, 36000, '数学', '高等数学，线性代数', 1, 0, NULL, 1577430749559, 1577376000000);
INSERT INTO "cyt_willing_plan" VALUES (15, 1, 58500, 62400, '英语', '六级的相关知识，作文，听力等等', 0, 1, NULL, 1577463320157, 1577462400000);
INSERT INTO "cyt_willing_plan" VALUES (16, 1, 28800, 36000, '数学', '高等数学，线性代数', 0, 1, NULL, 1577463320284, 1577462400000);
INSERT INTO "cyt_willing_plan" VALUES (17, 1, 36000, 41400, '数据结构', '链表的相关知识储备', 1, 1, NULL, 1577463320378, 1577462400000);
INSERT INTO "cyt_willing_plan" VALUES (18, 1, 54000, 57600, 'Java', 'java语言的学习', 1, 1, NULL, 1577463320478, 1577462400000);
INSERT INTO "cyt_willing_plan" VALUES (19, 1, 68400, 75600, 'Andriod', '安卓的相关训练', 1, 1, NULL, 1577463320568, 1577462400000);
INSERT INTO "cyt_willing_plan" VALUES (20, 1, 68400, 75600, 'Andriod', '安卓的相关训练', 0, 1, NULL, 1577471362193, 1577462400000);
INSERT INTO "cyt_willing_plan" VALUES (21, 1, 58500, 62400, '英语', '六级的相关知识，作文，听力等等', 0, 1, NULL, 1577471369760, 1577462400000);
INSERT INTO "cyt_willing_plan" VALUES (22, 1, 28800, 36000, '数学', '高等数学，线性代数', 0, 1, NULL, 1577471369851, 1577462400000);
INSERT INTO "cyt_willing_plan" VALUES (23, 1, 36000, 41400, '数据结构', '链表的相关知识储备', 0, 1, NULL, 1577471369945, 1577462400000);
INSERT INTO "cyt_willing_plan" VALUES (24, 1, 54000, 57600, 'Java', 'java语言的学习', 0, 1, NULL, 1577471370043, 1577462400000);
INSERT INTO "cyt_willing_plan" VALUES (25, 1, 68400, 75600, 'Andriod', '安卓的相关训练', 1, 1, NULL, 1577471370143, 1577462400000);
INSERT INTO "cyt_willing_plan" VALUES (26, 1, 58500, 62400, '英语', '六级的相关知识，作文，听力等等', 1, 0, NULL, 1577517212906, 1577462400000);
INSERT INTO "cyt_willing_plan" VALUES (27, 1, 28800, 36000, '数学', '高等数学，线性代数', 0, 0, NULL, 1577517212995, 1577462400000);
INSERT INTO "cyt_willing_plan" VALUES (28, 1, 36000, 41400, '数据结构', '链表的相关知识储备', 1, 0, NULL, 1577517213095, 1577462400000);
INSERT INTO "cyt_willing_plan" VALUES (29, 2, 18000, 25200, 'JavaWeb', '写一个网站，学生成绩管理系统', 0, 0, NULL, 1577518077232, 1577462400000);
INSERT INTO "cyt_willing_plan" VALUES (30, 2, 32400, 39600, '安卓', '写一个原生APP', 0, 0, NULL, 1577518077331, 1577462400000);
INSERT INTO "cyt_willing_plan" VALUES (31, 2, 45000, 51300, '休息💤', '睡觉💤是最美的事情', 0, 0, NULL, 1577518077414, 1577462400000);
INSERT INTO "cyt_willing_plan" VALUES (32, 5, 76800, 82800, '软硬件😌', '软硬件设计作业', 1, 0, NULL, 1577539521965, 1577462400000);
INSERT INTO "cyt_willing_plan" VALUES (33, 7, 25200, 32400, '英语', '早起ﾉ☀的第一缕阳光预定是给英语的。', 1, 0, NULL, 1577551793353, 1577548800000);
INSERT INTO "cyt_willing_plan" VALUES (34, 7, 36000, 43200, '数学', '肯定要做数学的啊', 0, 0, NULL, 1577551793439, 1577548800000);
INSERT INTO "cyt_willing_plan" VALUES (35, 1, 25200, 32400, '💤', '💤', 0, 0, NULL, 1577590845696, 1577548800000);
INSERT INTO "cyt_willing_plan" VALUES (36, 1, 58500, 62400, '英语', '六级的相关知识，作文，听力等等', 1, 0, NULL, 1577686685351, 1577635200000);
INSERT INTO "cyt_willing_plan" VALUES (37, 1, 28800, 36000, '数学', '高等数学，线性代数', 1, 0, NULL, 1577686685449, 1577635200000);
INSERT INTO "cyt_willing_plan" VALUES (38, 1, 36000, 41400, '数据结构', '链表的相关知识储备', 0, 0, NULL, 1577686685553, 1577635200000);
INSERT INTO "cyt_willing_plan" VALUES (39, 1, 54000, 57600, 'Java', 'java语言的学习', 1, 0, NULL, 1577686685634, 1577635200000);
INSERT INTO "cyt_willing_plan" VALUES (40, 1, 25200, 32400, '💤', '💤', 1, 0, NULL, 1577686685725, 1577635200000);
INSERT INTO "cyt_willing_plan" VALUES (41, 1, 25200, 32400, '💤', '💤', 0, 0, NULL, 1577863102388, 1577808000000);
INSERT INTO "cyt_willing_plan" VALUES (42, 1, 28800, 36000, '数学', '高等数学，线性代数', 1, 0, NULL, 1577863102477, 1577808000000);
INSERT INTO "cyt_willing_plan" VALUES (43, 1, 36000, 41400, '数据结构', '链表的相关知识储备', 0, 1, NULL, 1577898310238, 1577894400000);
INSERT INTO "cyt_willing_plan" VALUES (44, 1, 54000, 57600, 'Java', 'java语言的学习', 1, 1, NULL, 1577898310342, 1577894400000);
INSERT INTO "cyt_willing_plan" VALUES (45, 1, 25200, 32400, '💤', '💤休息睡觉', 1, 1, NULL, 1577898310425, 1577894400000);
INSERT INTO "cyt_willing_plan" VALUES (46, 1, 28800, 34800, '安卓', '安卓课堂演示', 0, 1, NULL, 1577898310516, 1577894400000);
INSERT INTO "cyt_willing_plan" VALUES (47, 1, 36000, 43200, '画图', '画😂😂😃😄😏', 1, 1, NULL, 1577899651327, 1577894400000);
INSERT INTO "cyt_willing_plan" VALUES (48, 1, 54000, 57600, 'Java', 'java语言的学习', 1, 1, NULL, 1577899651429, 1577894400000);
INSERT INTO "cyt_willing_plan" VALUES (49, 1, 58500, 62400, '英语😂', '六级的相关知识，作文，听力等等', 1, 1, NULL, 1577899651539, 1577894400000);
INSERT INTO "cyt_willing_plan" VALUES (50, 1, 58500, 62400, 'Java', 'java语言学习', 1, 0, NULL, 1577721600000, 1577721600000);
INSERT INTO "cyt_willing_plan" VALUES (51, 9, 27000, 37200, '把图画完', '哦耶', 0, 0, NULL, 1577961010769, 1577894400000);
INSERT INTO "cyt_willing_plan" VALUES (52, 1, 58500, 62400, '双』英语😂', '六级的相关知识，作文，听力等等', 1, 0, NULL, 1577961795835, 1577894400000);
INSERT INTO "cyt_willing_plan" VALUES (53, 1, 28800, 36000, '双』数学', '高等数学，线性代数', 0, 0, NULL, 1577961795937, 1577894400000);
INSERT INTO "cyt_willing_plan" VALUES (54, 1, 36000, 41400, '双』数据结构', '链表的相关知识储备', 0, 0, NULL, 1577961796023, 1577894400000);
INSERT INTO "cyt_willing_plan" VALUES (55, 1, 28800, 34800, '双』安卓', '安卓课堂演示', 1, 0, NULL, 1577961796140, 1577894400000);
INSERT INTO "cyt_willing_plan" VALUES (56, 11, 28800, 34800, '外国建筑史😂', '建筑史的复习', 1, 0, NULL, 1578025522043, 1577980800000);
INSERT INTO "cyt_willing_plan" VALUES (57, 11, 36000, 42000, '建筑结构', '结构力学', 1, 0, NULL, 1578025522168, 1577980800000);
INSERT INTO "cyt_willing_plan" VALUES (58, 11, 51000, 57600, '外国建筑史', '继续建筑史', 0, 0, NULL, 1578025522262, 1577980800000);
INSERT INTO "cyt_willing_plan" VALUES (59, 1, 28800, 34800, '周』算法+Java', '力扣算法的刷题', 1, 0, NULL, 1578033289924, 1577980800000);
INSERT INTO "cyt_willing_plan" VALUES (60, 1, 36000, 41400, '周』数学复习', '高等数学，线性代数🦘', 1, 0, NULL, 1578033290070, 1577980800000);
INSERT INTO "cyt_willing_plan" VALUES (61, 1, 52200, 57000, '周』Java', '毕向东Java的经典回顾', 0, 0, NULL, 1578033290150, 1577980800000);
INSERT INTO "cyt_willing_plan" VALUES (62, 1, 57900, 63900, '周』专业相关', '基础专业课:数据结构，操作系统大杂烩', 1, 0, NULL, 1578033290243, 1577980800000);
INSERT INTO "cyt_willing_plan" VALUES (63, 1, 67200, 74400, '周』英语🤪', '词汇+阅读📖，并且每周一套试卷', 0, 0, NULL, 1578033290351, 1577980800000);
INSERT INTO "cyt_willing_plan" VALUES (64, 1, 58500, 62400, '双』英语😂', '六级的相关知识，作文，听力等等', 0, 0, NULL, 1581049220291, 1581004800000);
INSERT INTO "cyt_willing_plan" VALUES (65, 1, 28800, 36000, '双』数学', '高等数学，线性代数', 0, 0, NULL, 1581049220433, 1581004800000);
INSERT INTO "cyt_willing_plan" VALUES (66, 1, 36000, 41400, '双』数据结构', '链表的相关知识储备', 0, 0, NULL, 1581049220505, 1581004800000);
INSERT INTO "cyt_willing_plan" VALUES (67, 1, 54000, 57600, '单』Java', 'java语言的学习', 0, 0, NULL, 1581049220581, 1581004800000);
INSERT INTO "cyt_willing_plan" VALUES (68, 1, 25200, 32400, '单』💤', '💤休息睡觉', 0, 0, NULL, 1581049220654, 1581004800000);
INSERT INTO "cyt_willing_plan" VALUES (69, 1, 28800, 34800, '双』安卓', '安卓课堂演示', 0, 0, NULL, 1581049220729, 1581004800000);
INSERT INTO "cyt_willing_plan" VALUES (70, 1, 36000, 43200, '单』画图', '画😂😂😃😄😏', 0, 0, NULL, 1581049220813, 1581004800000);
INSERT INTO "cyt_willing_plan" VALUES (71, 1, 58500, 62400, '双』英语😂', '六级的相关知识，作文，听力等霸气', 1, 0, NULL, 1618980249415, 1618934400000);
INSERT INTO "cyt_willing_plan" VALUES (72, 1, 28800, 36000, '双』数学', '高等数学，线性代数', 1, 0, NULL, 1618980249537, 1618934400000);
INSERT INTO "cyt_willing_plan" VALUES (73, 1, 36000, 41400, '双』数据结构', '链表的相关知识储备', 1, 0, NULL, 1618980249619, 1618934400000);
INSERT INTO "cyt_willing_plan" VALUES (74, 1, 28800, 34800, '双』安卓', '安卓课堂演示', 1, 0, NULL, 1618980249703, 1618934400000);
INSERT INTO "cyt_willing_plan" VALUES (75, 1, 57600, 63000, '双』计算机网络', '总体复习', 0, 0, NULL, 1618980249786, 1618934400000);
INSERT INTO "cyt_willing_plan" VALUES (76, 1, 58500, 62400, '双』英语😂', '六级的相关知识，作文，听力', 0, 0, NULL, 1670142964912, 1670083200000);
INSERT INTO "cyt_willing_plan" VALUES (77, 1, 28800, 36000, '双』数学', '高等数学，线性代数', 0, 0, NULL, 1670142965071, 1670083200000);
INSERT INTO "cyt_willing_plan" VALUES (78, 1, 36000, 41400, '双』数据结构', '链表的相关知识储备', 0, 0, NULL, 1670142965187, 1670083200000);

-- ----------------------------
-- Table structure for sqlite_sequence
-- ----------------------------
DROP TABLE IF EXISTS "sqlite_sequence";
CREATE TABLE "sqlite_sequence" (
  "name",
  "seq"
);

-- ----------------------------
-- Records of "sqlite_sequence"
-- ----------------------------
INSERT INTO "sqlite_sequence" VALUES ('user', 3);

-- ----------------------------
-- Table structure for st_academy_info
-- ----------------------------
DROP TABLE IF EXISTS "st_academy_info";
CREATE TABLE "st_academy_info" (
  "id" INTEGER NOT NULL,
  "name" TEXT NOT NULL,
  "memo" TEXT,
  "del" integer NOT NULL DEFAULT 0,
  PRIMARY KEY ("id")
);

-- ----------------------------
-- Records of "st_academy_info"
-- ----------------------------
INSERT INTO "st_academy_info" VALUES (1, '信息学院', NULL, 0);
INSERT INTO "st_academy_info" VALUES (2, '土建学院', NULL, 0);
INSERT INTO "st_academy_info" VALUES (3, '经管学院', '经济学', 0);
INSERT INTO "st_academy_info" VALUES (4, '外语学院', 123, 0);
INSERT INTO "st_academy_info" VALUES (5, 121, 11, 0);

-- ----------------------------
-- Table structure for st_admin
-- ----------------------------
DROP TABLE IF EXISTS "st_admin";
CREATE TABLE "st_admin" (
  "id" INTEGER NOT NULL,
  "role" TEXT NOT NULL DEFAULT 1,
  "num" text NOT NULL,
  "password" TEXT NOT NULL,
  "name" TEXT,
  "del" integer NOT NULL DEFAULT 0,
  PRIMARY KEY ("id")
);

-- ----------------------------
-- Records of "st_admin"
-- ----------------------------
INSERT INTO "st_admin" VALUES (1, 1, 'admin', 123, '管理员', 0);

-- ----------------------------
-- Table structure for st_class_info
-- ----------------------------
DROP TABLE IF EXISTS "st_class_info";
CREATE TABLE "st_class_info" (
  "id" INTEGER NOT NULL,
  "class_name" TEXT NOT NULL,
  "del" integer NOT NULL DEFAULT 0,
  "memo" TEXT,
  "academy_id" INTEGER NOT NULL,
  PRIMARY KEY ("id")
);

-- ----------------------------
-- Records of "st_class_info"
-- ----------------------------
INSERT INTO "st_class_info" VALUES (1, '软件17-1BF', 0, NULL, 1);
INSERT INTO "st_class_info" VALUES (2, '软件17-2BF', 0, NULL, 1);
INSERT INTO "st_class_info" VALUES (3, '建筑学17-1BF', 0, NULL, 2);
INSERT INTO "st_class_info" VALUES (4, '土木工程17-1BF', 0, NULL, 2);
INSERT INTO "st_class_info" VALUES (5, '土木工程17-2BF', 0, NULL, 2);
INSERT INTO "st_class_info" VALUES (6, '计算机科学与技术17-1BF', 0, NULL, 1);
INSERT INTO "st_class_info" VALUES (7, '计算机科学与技术17-2BF', 0, NULL, 1);

-- ----------------------------
-- Table structure for st_course_info
-- ----------------------------
DROP TABLE IF EXISTS "st_course_info";
CREATE TABLE "st_course_info" (
  "id" integer NOT NULL,
  "title" TEXT NOT NULL,
  "credit" TEXT NOT NULL,
  "class_time" integer NOT NULL,
  "del" integer NOT NULL DEFAULT 0,
  "memo" TEXT,
  PRIMARY KEY ("id")
);

-- ----------------------------
-- Records of "st_course_info"
-- ----------------------------
INSERT INTO "st_course_info" VALUES (1, '软件工程', 5, 48, 0, NULL);
INSERT INTO "st_course_info" VALUES (2, 'Web应用与开发', 4, 36, 0, NULL);
INSERT INTO "st_course_info" VALUES (3, '大学英语1', 6, 54, 0, '');
INSERT INTO "st_course_info" VALUES (4, '大学英语2', 5, 38, 0, '');
INSERT INTO "st_course_info" VALUES (5, '大学英语3', 4, 36, 0, '六级加油');
INSERT INTO "st_course_info" VALUES (6, '大学英语4', 3, 38, 0, '六级冲冲冲');
INSERT INTO "st_course_info" VALUES (7, '计算机网络原理', 4, 45, 0, '');
INSERT INTO "st_course_info" VALUES (8, '建筑力学', 4, 24, 0, '');
INSERT INTO "st_course_info" VALUES (9, '画法几何', 5, 30, 0, '');
INSERT INTO "st_course_info" VALUES (10, '建筑结构', 3, 36, 0, '');
INSERT INTO "st_course_info" VALUES (11, 'Linux操作系统', 4, 38, 0, '操作系统 shell');
INSERT INTO "st_course_info" VALUES (12, '大数据结构', 2, 48, 0, '人工智能走起');
INSERT INTO "st_course_info" VALUES (13, '云计算技术', 4, 44, 0, 'null');
INSERT INTO "st_course_info" VALUES (14, '专业英语（软工）', 3, 42, 0, '专业性的英语');

-- ----------------------------
-- Table structure for st_student_info
-- ----------------------------
DROP TABLE IF EXISTS "st_student_info";
CREATE TABLE "st_student_info" (
  "id" INTEGER NOT NULL,
  "student_id" text NOT NULL,
  "name" TEXT,
  "sex" integer DEFAULT 2,
  "tel" integer,
  "class_id" integer NOT NULL,
  "role" TEXT NOT NULL DEFAULT 3,
  "password" TEXT DEFAULT 123,
  "del" integer NOT NULL DEFAULT 0,
  "memo" TEXT,
  PRIMARY KEY ("id")
);

-- ----------------------------
-- Records of "st_student_info"
-- ----------------------------
INSERT INTO "st_student_info" VALUES (1, 15173700662, '董政宇', 1, 13341306480, 1, 3, 123, 0, '长风破浪会有时，直挂云帆济沧海');
INSERT INTO "st_student_info" VALUES (2, 15173700663, '华仁杰', 1, 13341306550, 3, 3, 123, 0, 0);
INSERT INTO "st_student_info" VALUES (3, 14174803994, '毛俊斌', 1, 18779556270, 1, 3, 123, 0, '');
INSERT INTO "st_student_info" VALUES (4, 14174803995, '疾风', 1, 15674004953, 2, 3, 123, 0, 0.0);
INSERT INTO "st_student_info" VALUES (5, 15173700664, '测试一', 1, 13341306480, 1, 3, 123, 0, '');
INSERT INTO "st_student_info" VALUES (6, 15173700665, '测试二', 0, 13341306480, 3, 3, 123, 0, '测试');
INSERT INTO "st_student_info" VALUES (7, 15173700666, '邱志国', 1, 13341306480, 1, 3, 123, 0, 1);
INSERT INTO "st_student_info" VALUES (8, 15173700667, '测试三', 1, 13341306480, 1, 3, 123, 0, '');
INSERT INTO "st_student_info" VALUES (9, 15173700668, '朵', 0, 13341306480, 1, 3, 123, 0, '测试');

-- ----------------------------
-- Table structure for st_student_score_relation
-- ----------------------------
DROP TABLE IF EXISTS "st_student_score_relation";
CREATE TABLE "st_student_score_relation" (
  "id" INTEGER NOT NULL,
  "student_id" INTEGER NOT NULL,
  "teacher_course_id" INTEGER NOT NULL,
  "score" TEXT,
  "del" integer NOT NULL DEFAULT 0,
  "memo" TEXT,
  PRIMARY KEY ("id")
);

-- ----------------------------
-- Records of "st_student_score_relation"
-- ----------------------------
INSERT INTO "st_student_score_relation" VALUES (1, 1, 1, 85, 0, NULL);
INSERT INTO "st_student_score_relation" VALUES (2, 1, 3, 85, 0, NULL);
INSERT INTO "st_student_score_relation" VALUES (3, 3, 1, 89, 0, NULL);
INSERT INTO "st_student_score_relation" VALUES (4, 3, 3, 93, 0, NULL);
INSERT INTO "st_student_score_relation" VALUES (5, 5, 1, 88, 0, NULL);
INSERT INTO "st_student_score_relation" VALUES (6, 5, 3, 66, 0, NULL);
INSERT INTO "st_student_score_relation" VALUES (7, 4, 2, 89.9, 0, NULL);
INSERT INTO "st_student_score_relation" VALUES (8, 4, 4, 96, 0, NULL);
INSERT INTO "st_student_score_relation" VALUES (9, 0, 0, NULL, 0, NULL);
INSERT INTO "st_student_score_relation" VALUES (10, 7, 1, 79, 0, NULL);
INSERT INTO "st_student_score_relation" VALUES (11, 7, 3, 84, 0, NULL);
INSERT INTO "st_student_score_relation" VALUES (12, 8, 1, 99, 0, NULL);
INSERT INTO "st_student_score_relation" VALUES (13, 8, 3, 88, 0, NULL);
INSERT INTO "st_student_score_relation" VALUES (14, 1, 5, 69, 0, NULL);
INSERT INTO "st_student_score_relation" VALUES (15, 1, 6, 93, 0, NULL);
INSERT INTO "st_student_score_relation" VALUES (16, 1, 7, 81, 0, NULL);
INSERT INTO "st_student_score_relation" VALUES (17, 1, 8, 66, 0, NULL);
INSERT INTO "st_student_score_relation" VALUES (18, 1, 9, 74, 0, NULL);
INSERT INTO "st_student_score_relation" VALUES (19, 9, 1, 97, 0, NULL);
INSERT INTO "st_student_score_relation" VALUES (20, 9, 3, 87, 0, NULL);
INSERT INTO "st_student_score_relation" VALUES (21, 9, 5, 54, 0, NULL);
INSERT INTO "st_student_score_relation" VALUES (22, 9, 6, 81, 0, NULL);
INSERT INTO "st_student_score_relation" VALUES (23, 9, 7, 63, 0, NULL);
INSERT INTO "st_student_score_relation" VALUES (24, 9, 8, 91, 0, NULL);
INSERT INTO "st_student_score_relation" VALUES (25, 9, 9, 99, 0, NULL);

-- ----------------------------
-- Table structure for st_teacher_class_course_relation
-- ----------------------------
DROP TABLE IF EXISTS "st_teacher_class_course_relation";
CREATE TABLE "st_teacher_class_course_relation" (
  "id" INTEGER NOT NULL,
  "teacher_id" INTEGER NOT NULL,
  "class_id" INTEGER NOT NULL,
  "del" integer NOT NULL DEFAULT 0,
  "course_id" INTEGER NOT NULL,
  "memo" TEXT,
  PRIMARY KEY ("id")
);

-- ----------------------------
-- Records of "st_teacher_class_course_relation"
-- ----------------------------
INSERT INTO "st_teacher_class_course_relation" VALUES (1, 1, 1, 0, 1, NULL);
INSERT INTO "st_teacher_class_course_relation" VALUES (2, 1, 2, 0, 1, NULL);
INSERT INTO "st_teacher_class_course_relation" VALUES (3, 1, 1, 0, 2, NULL);
INSERT INTO "st_teacher_class_course_relation" VALUES (4, 1, 2, 0, 2, NULL);
INSERT INTO "st_teacher_class_course_relation" VALUES (5, 1, 1, 0, 3, NULL);
INSERT INTO "st_teacher_class_course_relation" VALUES (6, 1, 1, 0, 4, NULL);
INSERT INTO "st_teacher_class_course_relation" VALUES (7, 1, 1, 0, 5, NULL);
INSERT INTO "st_teacher_class_course_relation" VALUES (8, 1, 1, 0, 6, NULL);
INSERT INTO "st_teacher_class_course_relation" VALUES (9, 1, 1, 0, 7, NULL);

-- ----------------------------
-- Table structure for st_teacher_info
-- ----------------------------
DROP TABLE IF EXISTS "st_teacher_info";
CREATE TABLE "st_teacher_info" (
  "id" integer NOT NULL,
  "name" text,
  "sex" integer,
  "tel" text NOT NULL,
  "password" TEXT DEFAULT 123,
  "role" TEXT NOT NULL DEFAULT 2,
  "del" integer NOT NULL DEFAULT 0,
  "memo" TEXT,
  "academy_id" INTEGER NOT NULL,
  PRIMARY KEY ("id")
);

-- ----------------------------
-- Records of "st_teacher_info"
-- ----------------------------
INSERT INTO "st_teacher_info" VALUES (1, '毛老师', 0, 15674004952, 123, 2, 0, 'mao', 1);
INSERT INTO "st_teacher_info" VALUES (2, '董政宇', 1, 13341306480, 123, 2, 0, '董老师', 1);
INSERT INTO "st_teacher_info" VALUES (3, '王嘉鑫', 1, 18751703821, 123, 2, 0, '王老师', 1);
INSERT INTO "st_teacher_info" VALUES (4, '邓志鑫', 1, 19976907244, 123, 2, 0, '邓123', 2);
INSERT INTO "st_teacher_info" VALUES (5, '金添翼', 1, 18974093924, 123, 2, 0, '金老师', 2);
INSERT INTO "st_teacher_info" VALUES (6, '华仁杰', 0, 15261600672, 123, 2, 0, '华老师.', 2);
INSERT INTO "st_teacher_info" VALUES (7, '董政宇', 0, 18260705621, 123, 2, 0, 'ppp', 2);
INSERT INTO "st_teacher_info" VALUES (8, '毛俊斌', 0, 13341300001, 123, 2, 0, '0001', 1);
INSERT INTO "st_teacher_info" VALUES (9, '赵老师', 0, 13341300002, 123, 2, 0, '0002', 1);
INSERT INTO "st_teacher_info" VALUES (10, '钱老师', 1, 13341300003, 123, 2, 0, '0003', 1);
INSERT INTO "st_teacher_info" VALUES (11, '孙老师', 0, 13341300004, 123, 2, 0, '0004', 2);
INSERT INTO "st_teacher_info" VALUES (12, '李老师', 0, 13341300005, 123, 2, 1, '0005', 1);
INSERT INTO "st_teacher_info" VALUES (13, '周老师', 1, 13341300006, 123, 2, 1, '0006', 1);
INSERT INTO "st_teacher_info" VALUES (14, '吴老师', 0, 13341300007, 123, 2, 1, '0007', 2);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS "user";
CREATE TABLE "user" (
  "id" INTEGER PRIMARY KEY AUTOINCREMENT,
  "name" TEXT NOT NULL,
  "pwd" TEXT NOT NULL DEFAULT 'abc',
  "roles" TEXT,
  UNIQUE ("name" ASC)
);

-- ----------------------------
-- Records of "user"
-- ----------------------------
INSERT INTO "user" VALUES (1, 'a', 123, '1,2');
INSERT INTO "user" VALUES (2, 'b', 123, '2,3');
INSERT INTO "user" VALUES (3, 'admin', '000', '1,2,3');

-- ----------------------------
-- Auto increment value for user
-- ----------------------------
UPDATE "sqlite_sequence" SET seq = 3 WHERE name = 'user';

PRAGMA foreign_keys = true;
