Claude 선생님과 KMP 배우기

정리글 : https://marchbreeze.tistory.com/tag/kmp

## 멀티모듈 의존성 그래프

```
┌─────────────┐     ┌─────────────┐     ┌─────────────┐
│  androidApp  │     │   webJsApp   │     │  webCmpApp   │
│  (Android)   │     │  (Kotlin/JS) │     │  (CMP/Wasm)  │
└──────┬───────┘     └──────┬───────┘     └──────┬───────┘
       │                    │                    │
       └────────────────────┼────────────────────┘
                            │
                            ▼
                    ┌───────────────┐
                    │    shared      │
                    │  (commonMain)  │
                    │               │
                    │ • Models/DTO  │
                    │ • Repository  │
                    │ • UseCase     │
                    │ • ViewModel   │
                    │ • API Client  │
                    └───────┬───────┘
                            │
              ┌─────────────┼─────────────┐
              ▼             ▼             ▼
        ┌──────────┐ ┌──────────┐ ┌──────────┐
        │ android  │ │   ios    │ │  js/wasm  │
        │  Main    │ │  Main    │ │   Main    │
        │ (OkHttp) │ │ (Darwin) │ │   (Js)   │
        └──────────┘ └──────────┘ └──────────┘

       ┌─────────────┐
       │   server     │──────────┐
       │  (Ktor/JVM)  │          │
       │              │          ▼
       │ • Routes     │  ┌───────────────┐
       │ • Plugins    │  │ shared (jvm)  │
       │ • Database   │  │   Models/DTO  │
       └──────────────┘  └───────────────┘

       ┌─────────────┐
       │   iosApp     │
       │  (SwiftUI)   │
       │              │
       │ shared (iOS  │
       │  Framework)  │
       └─────────────┘
```

### 모듈별 역할

| 모듈 | 타입 | 설명 |
|------|------|------|
| `shared` | KMP Library | 공유 코드 (Model, Repository, UseCase, ViewModel, API Client) |
| `server` | JVM Application | Ktor REST API 서버 (shared의 jvm 타겟 DTO 공유) |
| `androidApp` | Android App | Jetpack Compose UI |
| `iosApp` | iOS App (Xcode) | SwiftUI + shared Framework |
| `webJsApp` | Kotlin/JS App | HTML/CSS DOM 기반 웹 앱 |
| `webCmpApp` | CMP wasmJs App | Compose Multiplatform 웹 앱 |

## 실행 커맨드

### Server (Ktor)
```bash
./gradlew :server:run
```
- http://localhost:8080 에서 실행
- 클라이언트 앱 실행 전에 먼저 서버를 시작해야 함

### Android
Android Studio에서 `androidApp` 모듈 Run

### iOS
Xcode에서 `iosApp/iosApp.xcodeproj` 열고 Run

### Web (Kotlin/JS)
```bash
./gradlew :webJsApp:jsBrowserDevelopmentRun
```

### Web (CMP wasmJs)
```bash
./gradlew :webCmpApp:wasmJsBrowserDevelopmentRun
```
