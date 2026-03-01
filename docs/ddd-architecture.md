# DDD 架构落地说明

## 目标分层

项目按 DDD 采用四层组织：

1. `interfaces`：对外接口层（REST Controller、消息入口）。
2. `application`：应用服务层（用例编排、事务边界）。
3. `domain`：领域层（实体、值对象、领域服务、仓储端口）。
4. `infrastructure`：基础设施层（仓储实现、外部系统适配）。

## 当前落地状态

已完成以下模块的 DDD 化改造（均具备 `application/domain/infrastructure` 分层）：

1. `metadata`
2. `data`
3. `workflow`
4. `report`
5. `auth`
6. `file`

第二阶段已完成：接口层（controller）直接依赖 `application service`，原业务模块 `service/*` 兼容层已移除。

## 推荐目录规范

```text
com.jiandaoyun
├── metadata
│   ├── interfaces
│   ├── application
│   ├── domain
│   └── infrastructure
├── data
│   ├── interfaces
│   ├── application
│   ├── domain
│   └── infrastructure
├── workflow
│   ├── interfaces
│   ├── application
│   ├── domain
│   └── infrastructure
└── report
    ├── interfaces
    ├── application
    ├── domain
    └── infrastructure
```

## 第三阶段进展

1. 已完成 `service/core` 到 `shared-kernel` 的迁移（校验与事件发布能力）。
2. 已完成领域事件发布链路：
   - `metadata.form.created`
   - `data.record.submitted`
   - `workflow.instance.started`
   - `workflow.instance.approved`
3. 应用层通过 `DomainEventPublisher` 发布事件，保持跨上下文解耦。
4. 已接入 `OutboxService` 与 `DomainEventOutboxListener`，领域事件自动写入出箱。
5. Outbox 支持双实现：
   - `memory`：默认模式，适配本地开发与 CI。
   - `jdbc`：数据库模式，依赖 `outbox_message` 表（见 `sql/upgrade/V20260301__outbox_message.sql`）。
6. 已接入 Outbox 调度投递器（批处理、重试、失败标记）：
   - `OutboxDispatcher`
   - `OutboxDeliveryGateway`
   - `PENDING/PROCESSED/FAILED` 状态流转

## 后续演进

1. 将内存仓储逐步替换为 MySQL/MongoDB 仓储适配实现（当前默认内存实现保证本地与 CI 可运行）。
2. 增加事件监听器与 Outbox 持久化，支持可靠投递。
