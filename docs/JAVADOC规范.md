# Javadoc 规范

## 1. 目标
- 保证公开 API 的可读性、可维护性和可检索性。
- 保证 `./gradlew javadoc` 可稳定通过，作为文档质量门禁。

## 2. 必写范围
- 所有 `public` 类、接口、枚举。
- 所有 `public` 方法（包括控制器接口、服务接口、工具类静态方法）。
- 对外可见的常量字段建议补充说明。

## 3. 注释结构
- 首行一句话总结职责，使用第三人称描述。
- 必须使用规范标签：
  - `@param`：每个参数都要有。
  - `@return`：非 `void` 方法必须写。
  - `@throws`：会抛出受检/业务异常时必须写。
- 时间、线程安全、幂等性、边界条件建议在正文补充。

## 4. 编码与格式
- 文件编码统一 UTF-8（无 BOM）。
- 注释中避免乱码字符和未闭合标签。
- 避免无信息量描述（如“设置值”“获取值”）。

## 5. 验收命令
```bash
./gradlew clean javadoc
./gradlew clean build
```

## 6. 当前项目落地范围
- 控制器：`FormController`、`DataController`、`WorkflowController`、`ReportController`
- 服务层：`FormService*`、`DataService*`、`WorkflowService*`、`ReportService*`、`ValidatorService*`
- 关键模型与 DTO：表单/流程模型、请求/响应 DTO、`ApiResponse`
- 工具与启动入口：`IdGenerator`、`Application`