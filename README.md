# 🛠️ SimpleDAO Code Generator

A pure Spring Boot project with an embedded HTML UI — start and use instantly. No Webpack, no `npm install`. With just JDK and Maven, you can generate complete front-end and back-end code in one click.

## ✨ Why Choose It?

- 🎯 **Purpose-built for SimpleDAO**: Generated code follows the SQL-First paradigm directly — single-table operations require only an empty inherited class; multi-table queries are written in native SQL.
- 🗄️ **Multi-database Support**: Built-in metadata reading for four major databases — MySQL, PostgreSQL, Oracle, and SQL Server. Switch with a single configuration item.
- 📋 **Four Templates**: Covers the four most common enterprise development scenarios — standard list, tree structure, microservices, and Spring JDBC — ready to use out of the box.
- 📦 **White‑box Delivery**: Code is generated and packaged for download — it’s yours to modify freely. No black box, no intrusion.
- ⚡ **Extremely Lightweight**: Native Spring Boot + embedded HTML. Start a single jar, open your browser, and you're ready to go.

## 💻 Environment Requirements

| Technology | Version |
|------------|---------|
| JDK        | 21 LTS |
| Maven      | 3.9+    |
| Spring Boot| 3.5.x   |
| Database   | MySQL 8.0+ / PostgreSQL 12+ / Oracle 11g+ / SQL Server 2016+ |

> 💡 **Tip**: Deploy the generator on a development server so team members can use it via browser, sharing the same development database. No individual setup needed — saves resources.

## 🚀 Quick Start

**1. Edit Configuration**

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/your_db
    username: root
    password: your_password
custom:
  meta-database: mysql   # Database type; options: mysql / postgresql / oracle / sqlserver
```

**2. Start the Application**

```bash
mvn spring-boot:run
```

**3. Open the UI**

Visit `http://localhost:9090` in your browser to access the code generator interface.

**4. Choose a Template and Generate**

- Select the database and table(s)
- Choose the template(s) (Standard Table, Tree, Microservices, Spring JDBC)
- Click “Generate Code”

## 📂 The Four Templates

Currently only production‑grade Java (SimpleDAO) templates are available. Templates for other languages will be contributed by the community in the future.

| Template | Scenario | Generated Content | Extension Points |
|----------|----------|-------------------|------------------|
| 📋 `element-list` | Standard paginated list | Model, Cond, Dao, Service, Controller, VO, Excel, front-end pagination/list components, Mock test class | Multi-table queries, custom conditions, import/export |
| 🌳 `element-tree` | Tree structure maintenance | Same as above; front‑end component is a tree + list combination page | Node CRUD, hierarchy management, asynchronous loading |
| 🔗 `webapi` | Microservices (OpenFeign) | IModelClient interface, ModelAction transport object, ModelBusiness service class | Circuit breaker & fallback, remote call tracing, multi‑service orchestration |
| 🗄️ `jdbc` | Spring JDBC | Same as above; DAO layer implemented with Spring JDBC | Custom SQL, stored procedure calls |

## ✅ Key Operations

- **Select Database**: The dropdown automatically loads all available databases. Pick one.
- **Filter Tables**: Supports filtering by prefix to quickly find your target table.
- **Set Parameters**: Author, company, project, module, microservice name — fill in as needed.
- **Choose Templates**: Select one or multiple templates.
- **Toggle Class Name**: Click the “Toggle Class Name” button to switch between the suggested class name and the original table name.
- **Adjust Field Properties**: Click “Settings” to modify each field’s Java type, form component type, whether it appears in the table, etc.
- **Generate Code**: Select the table(s), click “Generate Code”, and the code is automatically packaged for download.
- **Modify Templates**: All template files are located in the `resources/code/` directory; edit them directly to match your project’s style.

## ❓ FAQ

**Q: The generator can’t connect to the database?**  
A: Check whether the database connection details in `application.yml` are correct, and confirm that the database service is running and accessible on the network port.

**Q: How can I change the generated code style?**  
A: Directly modify the template files under `resources/code/`. The template syntax uses FreeMarker (for `.java` files) and Vue templates (for `.vue` files).

**Q: Which databases are supported?**  
A: Built‑in metadata reading supports MySQL, PostgreSQL, Oracle, and SQL Server. Specify the database type via the `custom.meta-database` property in `application.yml` (default: `mysql`). Field type mappings are automatically adjusted based on the database type.

**Q: How can I adapt the generator to other databases?**  
A: Refer to the `switch` branches in `CodeDao.java`. Add the corresponding metadata query SQL for the new database. There are four queries to extend: table list, column list, database list, and current database name — add a new branch to each.

**Q: Can it work with domestic (Chinese) databases?**  
A: Many domestic databases (e.g., TiDB, OceanBase, Dameng) are compatible with MySQL, Oracle, or SQL Server metadata query syntax. Configure the corresponding `meta-database` value (e.g., set TiDB to `mysql`), and it will likely work out of the box. If you encounter compatibility issues, follow the guidance in the previous question to adapt it yourself.
