# StudentManagement

```
注意：这是一个Java EE 项目 仅仅是个人学习使用
```

### 技术可行

前端语言：layui框架（一款很符合中国审美风格的ui设计）

后端语言：servlet + Tomcat服务器 + java语言来进行开发

前端编辑器：HBuilderX

后端编辑器：Eclipse

数据库：采用sqlite3数据库设计

数据库可视化工具：采用navicat Premium 12

权限控制：采用的是layui的后台框架 + iframe配合的页面跳转

登录：session保存到服务器端

退出登录：采用的是session的设置无效

数据统计方面：采用的是echarts的图表统计



### 基本需求

1.教师录入学生名单、课程名称、学生成绩

2.教师可以查看学生成绩、成绩的统计信息

3.学生只能查看自己本人的成绩，自己本人的成绩统计信息

### 扩展需求

根据需求可以分为三种权限：

\1. admin管理员老师

1） admin管理员老师可以添加学生，添加教师，添加课程

2） admin管理员老师可以分配给老师教授某个班级的某个课程

3） admin管理员老师可以把学生组织到一个班级里面

\2. 授课教师

1） 授课老师可以录入学生的课程成绩

2） 授课老师可以添加学生到本班级

3） 授课老师可以查看所授班级的学生成绩和整个班级成绩统计信息

\3. 班级学生

1） 学生可以查看、修改自己的个人信息

2） 学生可以查看自己各科成绩和总体成绩统计