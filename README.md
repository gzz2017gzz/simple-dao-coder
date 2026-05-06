
# 🛠️ SimpleDAO 代码生成器

纯粹的 Spring Boot 工程，内嵌 HTML 操作界面，启动即用。不需要 Webpack，不需要 `npm install`，只需要 JDK 和 Maven，就能一键生成全套前后端代码。

## ✨ 为什么选它？

-   🎯 **为 SimpleDAO 量身打造**：生成的代码直接基于 SimpleDAO 范式，单表继承空类即用，联表直写 SQL。
-   🗄️ **多数据库支持**：内置 MySQL、PostgreSQL、Oracle、SQL Server 四大主流数据库的元数据读取能力，一个配置项切换。
-   📋 **四套模板**：覆盖企业级开发最常见的四类场景，常规列表、树形结构、微服务、Spring JDBC，开箱即用。
-   📦 **白盒交付**：代码直接生成并打包下载，到手就是你的，随便改。没有黑盒，没有侵入。
-   ⚡ **极致轻量**：原生 Spring Boot + 内嵌 HTML，启动一个 jar，浏览器打开即用。

## 💻 环境要求

| 技术 | 版本 |
|------|------|
| JDK | 21 LTS |
| Maven | 3.9+ |
| Spring Boot | 3.5.x |
| 数据库 | MySQL 8.0+ / PostgreSQL 12+ / Oracle 11g+ / SQL Server 2016+ |

> 💡 **建议**：将生成器部署到开发服务器上，团队成员浏览器共同使用，共享同一个开发库。无需每人单独配置，节省资源。

## 🚀 快速启动

**1. 修改配置**

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/your_db
    username: root
    password: your_password
custom:
  meta-database: mysql   # 数据库类型，可选 mysql / postgresql / oracle / sqlserver
```

**2. 启动工程**

```bash
mvn spring-boot:run
```

**3. 打开界面**

浏览器访问 `http://localhost:9090`，即可进入代码生成器操作界面。

**4. 选择模板，生成代码**

-   选择数据库和表
-   勾选模板（常规表格、树型结构、微服务、Spring JDBC）
-   点击“生成代码”

## 📂 四套模板

目前阶段只提供 Java 版 SimpleDAO 的生产级模板，其他语言版本后续由社区共同贡献。

| 模板 | 场景 | 生成内容 | 预留扩展点 |
|------|------|---------|-----------|
| 📋 `element-list` | 常规分页列表 | Model、Cond、Dao、Service、Controller、VO、Excel、前端分页/列表组件、Mock 测试类 | 联表查询、自定义条件、导入导出 |
| 🌳 `element-tree` | 树型结构维护 | 同上，前端组件为树型+列表组合页面 | 节点增删改、层级管理、异步加载 |
| 🔗 `webapi` | 微服务 OpenFeign | IModelClient 接口、ModelAction 传输对象、ModelBusiness 业务类 | 熔断降级、远程调用链路、多服务编排 |
| 🗄️ `jdbc` | Spring JDBC | 同上，Dao 层为 Spring JDBC 实现 | 自定义 SQL、存储过程调用 |

## ✅ 操作要点

-   **选择数据库**：下拉框自动加载所有可用的数据库，选择一个即可。
-   **过滤表名**：支持按前缀过滤，快速定位目标表。
-   **设置参数**：作者名、公司名、项目名、模块名、微服务名，按需填写。
-   **选择模板**：勾选需要的模板，一个或多个都行。
-   **切换类名**：点击“切换类名”按钮，可以在建议类名和原表名之间切换。
-   **调整字段属性**：点击“设置”按钮，可以修改每个字段的 JAVA 类型、表单组件类型、是否在表格中显示等。
-   **生成代码**：选中表，点击“生成代码”，代码自动打包下载。
-   **修改模板**：所有模板文件都在 `resources/code/` 目录下，可直接修改适配自己的项目风格。

## ❓ 常见问题

**Q：生成器连不上数据库？**
A：确认 `application.yml` 中的数据库连接信息是否正确，确认数据库服务已启动且端口可达。

**Q：想修改生成的代码风格怎么办？**
A：直接修改 `resources/code/` 下的模板文件，模板语法使用 FreeMarker（.java 文件）和 Vue 模板（.vue 文件）。

**Q：支持哪些数据库？**
A：内置 MySQL、PostgreSQL、Oracle、SQL Server 四大主流数据库的元数据读取能力。在 `application.yml` 中通过 `custom.meta-database` 配置项指定即可，默认 `mysql`。字段类型映射会根据数据库类型自动调整。

**Q：想适配其他数据库怎么办？**
A：参考 `CodeDao.java` 中的 `switch` 分支结构，新增对应数据库的元数据查询 SQL 即可。表名查询、字段名查询、数据库列表查询、当前数据库名查询——四个方法各自增加一个分支。

**Q：国产数据库能用吗？**
A：很多国产数据库（如 TiDB、OceanBase、达梦）兼容 MySQL、Oracle 或 SQL Server 的元数据查询语法。将其配置为对应的 `meta-database` 值（如 TiDB 配置为 `mysql`），大概率可以直接使用。如果遇到兼容性问题，参考上一个问题的指引自行适配即可。