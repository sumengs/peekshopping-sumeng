## 畅购

#### 0. 端口规定

```
eureka-server-01：8761
eureka-server-02：8762

goods-service：8000
system-service：8020

gateway：8101

```



#### 1. 行业背景

 1. 电商行业分析
 2. 电商系统技术特点
 3. 主要电商模式

#### 2. 系统分析和系统设计

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

#### 4. 商品微服务-品牌增删改查

 ###### 通用Mapper的集成

 1. 导入依赖
 2. 启动类改造
 3. 实现DAO
    - 映射
        - 指定表名@Table(name = "表名")
        - 指定主键ID @Id
    - 实现功能

 ###### 通用Mapper的常用方法

> 严谨性思维【重点】
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
 - selectByExample：通过example条件查询
    - example设置代码
 - PageHelper.startPage：分页查询

#### 5. 公共异常处理

###### 统一异常处理

>目的：是增加接口的访问体检

```java
/**
 * 统一异常处理类
 */
@ControllerAdvice //声明该类是一个增强类
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR,"当前系统繁忙,请您稍后重试");
    }

    @ExceptionHandler(value = {NullPointerException.class,ClassCastException.class})
    @ResponseBody
    public Result error2(Exception e){
        e.printStackTrace();
        return new Result(false, StatusCode.ACCESSERROR,"当前系统繁忙,请您稍后重试");
    }

}
```

#### 6. 跨域及解决方案

#### 7. 通用Mapper自定义方法

#### 8. fastDFS 文件上传

#### 9. 微服务网关Gateway

###### 网关的好处

- 提示安全性
- 易于监控
- 方便统一管理(授权，认证，日志)
- 统一前端访问入口，进而简化了前端的复杂性

###### Gateway的跨域配置

```yml
spring:
  cloud:
    gateway:
      globalcors:
        cors-configurations:
          '[/**]': # 匹配所有请求
            allowedOrigins: "*" #跨域处理 允许所有的域
            allowedMethods: # 支持的方法
              - GET
              - POST
              - PUT
              - DELETE
```

#### 10. BCrypt 密码加密

###### 令牌桶算法

- 令牌的生成器：按照一定的速度生成令牌，并放到桶中
    - 生成两天的速度固定
    - 桶满时，生成的令牌直接丢弃
- 令牌的分发器：当有请求时，从桶里拿一个令牌，分给请求
    - 有请求时，就分配，无请求不处理
    - 当桶无令牌时，直接返回请求流量被限制
- 分桶的策略
    - ip
    - 浏览器的头信息
    - 登录用户id
    - uri
- 分桶策略设置
```java
@Bean
public KeyResolver ipKeyResolver(){
    return new KeyResolver() {
        @Override
        public Mono<String> resolve(ServerWebExchange exchange) {
            return Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
        }
    };
}
```
- 桶大小设置
- 令牌生成速率设置
```yml
 - name: RequestRateLimiter #请求数限流 名字不能随便写
              args:
                key-resolver: "#{@ipKeyResolver}"
                redis-rate-limiter.replenishRate: 1 #令牌桶每秒填充平均速率
                redis-rate-limiter.burstCapacity: 1 #令牌桶总容量
```

###### BCrypt
- 盐(29)
- 密文(31)
- API
    - 生成盐
    - 用盐加密密码
    - 对比密码：先加密后对比加密后的字符串

###### 常用算法的分类

- 可逆算法
    >- 可以加密，可以解密
    >- 可以自定义秘钥key
    - 对称：加密和解密使用的key
    - 场景
        - token的认证
        - 敏感数据的存储（省份证，手机号，银行卡号）
    - 非对称：有1对key，既公钥和私钥，私钥用于服务端，公钥用于客户端
        - 场景
            - token的认证
            - 传输数据的加密
        - RSA
- 不可逆算法
    >- 可加密，但不可解密
    - 散列算法
    - 摘要算法：可加密，不可解密，加密结果长度固定
        - 场景
            - 密码
            - 通信数据的签名
        - MD5、Bcrypt、SHA
    
- 编码
    - Base64：是字节转成可方面阅读和传递的工具

#### 11. JWT 实现网关鉴权

###### 什么是JWT

- 定义：是一个用在客户端与服务端之间传输用户登录信息的安全规范协议
- 组成：
    - 头信息（Header）：存放的是类型、加密算法等信息
    - 载荷（Playload）：用户自定义的信息内容
    - 签名（signature）：防止内容被篡改的签名信息

###### JJWT工具的API【熟练】

- 生成

  - 指定默认的字段id、subject、issued
  - 自定内容claim
  - 设置过期时间expiration

  ```java
   //生成jwt令牌
  JwtBuilder jwtBuilder = Jwts.builder()
          .setId("66")//设置jwt编码
          .setSubject("黑马程序员")//设置jwt主题
          .setIssuedAt(new Date())//设置jwt签发日期
          //.setExpiration(date)//设置jwt的过期时间
          .claim("roles","admin")
          .claim("company","itheima")
          .signWith(SignatureAlgorithm.HS256, "itheima");

  //生成jwt
  String jwtToken = jwtBuilder.compact();
  System.out.println(jwtToken);

  //解析jwt,得到其内部的数据
  Claims claims = Jwts.parser().setSigningKey("itheima").parseClaimsJws(jwtToken).getBody();
  System.out.println(claims);
  ```

#### 12. 分布式ID生成解决方案

1. 作用：是解决分库分表场景的主键唯一
2. 方案：
    - UUID：生成简单，单对于数据库主键索引的查询效率影响大
    - redis/ZK：支持原子性，有走网络，性能会出现波动的情况
    - snowFlake：生成简单且高效，最大支持1024个集群的节点使用
    ```java
    @Value("${workerId}")
    private Integer workerId;
    @Value("${datacenterId}")
    private Integer datacenterId;
    @Bean
    public IdWorker idWorker(){
       return new IdWorker(workerId,datacenterId);
    }
    ```

#### 13. 商品管理

###### 商品的添加

> - spu（产品）：一组商品的集合
> - sku（商品）：一个可以直接购买的产品

通过对表每个字段的值进行梳理，来实现我们最终的代码：

- id：自动生成，要使用idwork

- sn：前端直接填写

- name：SKU名称=SPU名称+空格+SKU规格

  ```java
  //设置sku规格数据
  if (StringUtils.isEmpty(sku.getSpec())){
      sku.setSpec("{}");
  }
  //设置sku名称(spu名称+规格)
  String name = spu.getName();
  //将规格json转换为map,将map中的value进行名称的拼接
  Map<String,String> specMap = JSON.parseObject(sku.getSpec(), Map.class);
  if (specMap != null && specMap.size()>0){
      for (String value : specMap.values()) {
          name+=" "+value;
      }
  }
  sku.setName(name);
  ```

- create_time:后端自动生成，new Date()

- category_id：spu表只会存储第三级分类的信息

- category_name：冗余字段,需要查询获取三级分类名称

- brand_name：冗余字段

- sale_num:后台填写，不能为负数，且初始为0

###### 依据SPU ID查询商品信息

- 修改SPU
- 建立索引
- 生成商品详情页



###### 修改SPU和SKU

- SKU可以先删除后增加，达到修改的目的

- 修SPU信息时一定需要注意哪些字段可以修改，建议使用一下方式

  ```java
  //先从数据库中查询数据，然后对可以修改的值做填充，然后对查询的对象做修改，避免修改前端传入的SPU对象，以提升安全
  Spu spu = goods.getSpu();
  Spu temp = spuMapper.selectByPrimaryKey(spu.getId());
  temp.setName(spu.getName());
  temp.setCaption(spu.getCaption());
  。。。
  spuMapper.updateByPrimaryKey(temp);
  ```



###### 商品审核

> 审核商品，需要校验是否是被删除的商品，如果未删除则修改审核状态为1，并自动上架

- 条件：
  - 不能是逻辑删除的数据
  - 数据必须是未审核的状态
- 动作：
  - 修改上架状态
  - 修改审核状态

###### 商品下架

> 商品下架后，修改成setIsMarketable为0

- 条件：
  - 不能是逻辑删除的数据
  - 不能上架未审核的数据
  - 数据必须是上架状态
- 动作：
  - 修改为下架状态（1->0）

###### 商品上架

> 商品上架后，修改成setIsMarketable为1

- 条件：
  - 不能是逻辑删除的数据
  - 不能上架未审核的数据
  - 数据必须是下架状态
- 动作：
  - 修改为上架状态（0->1）

###### 商品逻辑删除

> 逻辑删除只能删除下架状态，并删除后的商品变未审核状态

- 条件：
  - 数据必须是下架状态
  - 数据不是已删除状态
- 动作：
  - 修改为未审核状态（1->0）
  - 修改逻辑删除字段（0->1）

###### 商品还原

> 商品还原逻辑删除的商品，还原为未审核状态

- 条件：
  - 数据必须是逻辑删除状态
- 动作：
  - 修改为未审核状态（1->0）[可选]
  - 修改逻辑删除字段（1->0）

###### 商品删除

> 物理删除已经逻辑删除的商品

- 条件：
  - 数据必须是逻辑删除状态
- 动作：
  - 物理删除SPU
  - 物理删除SKU
  
 
#### 14. Lua


#### 15. nginx + lua + redis 实现广告缓存

###### Nginx 

- 负载均衡
    - 轮间(加权)
    - IP Hash
- 反向代理
- 静态资源服务器





































