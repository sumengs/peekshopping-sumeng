## 畅购

#### 0. 端口规定

```
eureka-server-01：8761
eureka-server-02：8762
```



#### 1. 背景

 1. 电商行业分析
 2. 电商系统技术特点
 3. 主要电商模式
 
#### 2. 系统设计

 1. 前后端分离
 2. 技术架构
 3. 系统架构
 
#### 3. 框架搭建
 1. 环境准备
 2. 项目结构说明
    - 基础服务
        - eureka
        - auth
        - fescar
        - admin
        - mycat
        - config
        - bus
    - 公共模块
        - common
            - 通用pojo
            - 通过工具类
            - 通用常量配置
            - 通用属性配置
        - common-db
            - 数据库
    - 网关模块
        - 前后网关项目
    - 微服务模块
        - service
    - 微服务符API模块
        - service-api
    - 页面渲染模块
        - web
 3. 工程搭建
    - 规范
        - 名称（中横线，小写）
        - 端口规范
    - 依赖父工程约束，子工程使用
 
 #### 4. 通用Mapper的集成
 
 1. 导入依赖
 2. 启动类改造
 3. 实现DAO
    - 映射
        - 指定表名@Table(name = "表名")
        - 指定主键ID @Id
    - 实现功能
 
 #### 5. 通用Mapper的常用方法
 
> 严谨性思维
> - 对于存储的数据要设置默认值
> - 唯一值检查
> - 更新的数据是否存在
> - 更新数据字段是否有权限
> - 删除的数据是否有权限

 - selectAll：查询所有
 - selectByPrimaryKey：根据主键查询
 - insert：插入数据且插入空值
 - insertSelective：插入数据，不插入空值
 - update：更新所有字段，包括空值字段
 - updateSelective：只更新非空值的字段
 - deleteByPrimaryKey：通过主键删除
 
