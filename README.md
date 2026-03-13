Claude 선생님과 KMP 배우기

정리글 : https://marchbreeze.tistory.com/tag/kmp

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
