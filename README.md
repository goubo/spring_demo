# spring_demo

spring 功能demo

## TODO

- [x] 公共配置
- [x] mybatis-puls
- [x] mybatis-puls generanor
- [x] druid
- [x] druid 监控
- [x] spring cloud alibaba
- [x] swagger
- [x] nacos 配置
- [x] nacos 注册中心
- [x] openfeign 调用
- [x] Sentinel 监控
- [x] gateway
- [ ] sharding-jdbc
- [ ] druid 多数据源
- [ ] 登录权限
- [ ] sso

## 启动说明

1. 启动 Sentinel 控制台
2. 启动 nacos
3. 修改 bootstrap.yaml 中 Sentinel和nacos 地址
4. 导入 nacos 的配置文件
5. 修改 bootstrap.yaml 中 nacos 的group , namespace 信息
6. 启动 所有服务

## 其他

### diea service 管理spring boot启动类

如果不显示,打开项目根目录下 `.idea/workspace.xml` 文件 替换以下节点,然后重新打开窗口.

```xml 

<component name="RunDashboard">
  <option name="configurationTypes">
    <set>
      <option value="SpringBootApplicationConfigurationType"/>
    </set>
  </option>
</component>
```