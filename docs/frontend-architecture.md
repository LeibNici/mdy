# 前后端分离架构说明

## 目录
- 后端：`/src/main/java`（Spring Boot API）
- 前端：`/frontend`（Vue 3 + Vite）

## 前端技术选型
- Vue 3 + TypeScript
- Vite 构建
- Vue Router 路由
- Pinia 状态管理
- Axios HTTP 客户端

## 本地开发
1. 启动后端（8080）
2. 进入 `frontend` 执行 `npm install && npm run dev`（5173）
3. 前端通过 Vite 代理访问 `/api/*` 到后端

## 跨域策略
- 后端 `WebConfig` 放行 `http://localhost:5173`
- 可通过配置 `app.cors.allowed-origins` 调整

## CI
- 后端 Job：`./gradlew test`
- 前端 Job：`npm ci && npm run build`