# GithubUsers

## 專案架構
分為 DI, Domain, Presentation 3 個 Layer

### DI Layer
處理依賴注入的設定，包含 `AppModule`, `RemoteModule`，也可加入 `LocalModule` 來處理 database or cache

### Domain Layer
處理與 Android Framework 無關的業務邏輯如 Entities, Repository Interface 等

### Presentation Layer
- 處理 App 與 UI 相關邏輯如 Views, ViewModels
- TODO: 加入 UI error Handling
- TODO: 抽出 components 如 `BadgeView`
- TODO: 加入 ViewModel Unit Test
- TODO: 加入 Integration Test

## Pattern
主要使用 MVVM 與 Repository Pattern

## What did you find most difficult?
- 主要是測試的部分花了較多時間學習，因為還不熟悉 Flow 和 MockK