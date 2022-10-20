# 专业化车队项目开发文档

##  1、整体目录结构

```
com.dlzx     
├── dlzx-ui              // 前端框架 [80]
├── dlzx-gateway         // 网关模块 [8089]
├── dlzx-auth            // 认证中心 [10025]
├── dlzx-api             // 接口模块
│       └── dlzx-api-system                          // 系统接口
├── dlzx-common          // 通用模块
│       └── dlzx-common-core                         // 核心模块
│       └── dlzx-common-datascope                    // 权限范围
│       └── dlzx-common-log                          // 日志记录
│       └── dlzx-common-redis                        // 缓存服务
│       └── dlzx-common-security                     // 安全模块
│       └── dlzx-common-swagger                      // 系统接口
├── dlzx-modules         // 业务模块
│       └── dlzx-system                              // 系统模块 [9201]
│       └── dlzx-gen                                 // 代码生成 [9202]
│       └── dlzx-job                                 // 定时任务 [9203]
│       └── dlzx-pfserver-car                        // 汽车模块 [9300]
│       └── dlzx-pfserver-parts                      // 统计模块 [9400]
│       └── dlzx-pfserver-person                     // 人员模块 [9500]
├── dlzx-visual          // 图形化管理模块
│       └── dlzx-visual-monitor                      // 监控中心 [9100]
├──pom.xml                // 公共依赖
```

	实际开发中需要涉及到的微服务为`dlzx-system`、`dlzx-pfserver-car`、`dlzx-pfserver-parts`、`dlzx-pfserver-person`

配置文件在Nacos配置中心，地址：`http://192.168.1.103:8848/nacos/` 账号：`nacos`密码：`nacos`
本地开发时需要在

	前端代码在`dlzx-ui`内，使用vue框架开发。

---
### 后端运行及开发

后端相关服务以在服务器中运行，本地只需要运行自己将要开发的服务。启动项目前需要在项目的配置文件中加上`register-enabled: false`，让本地文件本地服务不注册到nacos里，避免影响其他人开发。

![mark](http://img.sarsblog.cn/blog/20201118/cP31L7TLy2L1.png)

开发示例可参考system内已有框架模块作为参考。



状态码在`com.dlzx.common.core.constant.HttpStatus`内可以参考


---
### 前端运行及开发

```bash
# 安装依赖
npm install

# 强烈建议不要用直接使用 cnpm 安装，会有各种诡异的 bug，可以通过重新指定 registry 来解决 npm 安装速度慢的问题。
npm install --registry=https://registry.npm.taobao.org

# 本地开发 启动项目
npm run dev
```

前端开发时可对照项目原型，原型对应相关页面上会标注本页面所需用到的接口，及命名规范，可根据接口名对照接口文档进行开发。

## 2、开发规范
1.开发人员要按照原型中页面编号来定义接口名称（页面编号+接口名称 例：B01list 接口名称：列表List 删除Delete 新增Add 编辑页面数据：Edit 编辑保存：EditSave）
2.接口入参需要使用强类型(DTO实体类)接收数据，不能使用字典、map等泛用属性，如有业务需要，提前报备。
3.入参必须做空校验和数据格式校验-校验可以在实体类中校验。
4.变量名或者方法名使用驼峰命名法，不能出现无意义命名变量（例如 abc、a1、b1等）。
5.尽量避免循环中调用数据库查询、新增、更新等操作。
6.Controller中不允许出现公共方法
7.类和方法、参数需要有明确的中文注释

请求方式定义：
    新增、编辑使用POST请求
    详情、删除使用GET请求
    
    
    
    

## 3、实用方法
1、DTO实体类校验
在需要检验的方法上添加 @Validated 属性标注，该接口需要校验，例：public RspDTO save(@RequestBody @Validated UserDTO userDTO)

在需要验证的字段上面添加以下字节
@NotNull(message=提示内容)	被注解的元素必须不为null(判断int lang date 等任意类型)
@NotBlank(message=提示内容)   只能作用在String上，不能为null，而且调用trim()后，长度必须大于0
@Range(min=最小值, max=最大值, message=提示内容)  验证注解的元素值在最小值和最大值之间(BigDecimal,BigInteger,CharSequence, byte, short, int, long等原子类型和包装类型)
@Size(min=下限, max=上限)	字符串、Collection、Map、数组等	验证注解的元素值的在min和max（包含）指定区间之内，如字符长度、集合大小
参考文档：https://www.jianshu.com/p/67d3637493c7







