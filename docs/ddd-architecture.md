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

兼容适配层保留在 `service/*`，用于承接旧接口并委托到应用服务。

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

## 后续演进

1. 将内存仓储逐步替换为 MySQL/MongoDB 仓储适配实现。
2. 按有界上下文补齐领域事件与事件发布机制。
3. 将接口层逐步直接面向应用服务，最终移除兼容适配层。
