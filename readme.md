# ssm-crud

1.bean包以及dao包中的内容都是通过mybatis的逆向工程自动生成，我只是进行了一些简单的改造，让他能进行联合查询，但是好像还是存在一点问题，查询出来的结果会根据不同的部门名分层。

2.页面采用的是bootstrap框架搭建的，主页面是index.html，通过ajax拿到后端的数据，采用的是Rest风格的uri

3.采用了JSR303校验技术对非法输入进行校验
