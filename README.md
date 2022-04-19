# oh-ho-server

## 모듈 역할

📂 ohho-storage   
entity + repository 관련 모듈  
📂 ohho-core   
service 관련 모듈  
📂 ohho-api  
controller 관련 모듈

## 프로젝트 구조

```
📦 
├─ .gitignore
├─ README.md
├─ build.gradle
├─ gradle.properties
├─ gradle
│  └─ wrapper
│     ├─ gradle-wrapper.jar
│     └─ gradle-wrapper.properties
├─ gradlew
├─ gradlew.bat
├─ ohho-api
│  ├─ build.gradle
│  └─ src
│     └─ main
│        └─ java
│           └─ kr
│              └─ ohho
│                 ├─ OhhoApiApplication.java
│                 ├─ common
│                 │  ├─ ApiControllerAdvice.java
│                 │  └─ ApiResponse.java
│                 └─ ui
│                    ├─ HelloController.java
│                    ├─ member
│                    │  └─ MemberController.java
│                    ├─ notification
│                    │  └─ NotificationController.java
│                    └─ partner
│                       └─ PartnerController.java
├─ ohho-core
│  ├─ build.gradle
│  └─ src
│     └─ main
│        ├─ java
│        │  └─ kr
│        │     └─ ohho
│        │        ├─ OhhoDoaminApplication.java
│        │        ├─ application
│        │        │  ├─ member
│        │        │  │  ├─ MemberService.java
│        │        │  │  ├─ MemberServiceImpl.java
│        │        │  │  ├─ exception
│        │        │  │  │  ├─ MemberEmailDuplicatedException.java
│        │        │  │  │  ├─ MemberNicknameAlreadyExistException.java
│        │        │  │  │  ├─ MemberNotFoundException.java
│        │        │  │  │  └─ MemberSignUpRequestInvalidException.java
│        │        │  │  └─ model
│        │        │  │     ├─ request
│        │        │  │     │  ├─ SignInRequestDto.java
│        │        │  │     │  ├─ SignInWithGoogleRequestDto.java
│        │        │  │     │  ├─ SignUpRequestDto.java
│        │        │  │     │  └─ SignUpWithGoogleRequestDto.java
│        │        │  │     └─ response
│        │        │  │        ├─ GetMemberByNicknameResponseDto.java
│        │        │  │        ├─ GetMyInfoResponseDto.java
│        │        │  │        ├─ SignInResponseDto.java
│        │        │  │        └─ SignUpResponseDto.java
│        │        │  ├─ notification
│        │        │  │  ├─ NotificationService.java
│        │        │  │  ├─ NotificationServiceImpl.java
│        │        │  │  └─ model
│        │        │  │     └─ response
│        │        │  │        └─ NotificationResponseDto.java
│        │        │  └─ partner
│        │        │     ├─ PartnerService.java
│        │        │     ├─ PartnerServiceImpl.java
│        │        │     ├─ exception
│        │        │     │  └─ PartnerNotFoundException.java
│        │        │     └─ model
│        │        │        └─ request
│        │        │           ├─ AcceptPartnerRequestDto.java
│        │        │           ├─ RequestPartnerRequestDto.java
│        │        │           └─ UpdateDdayAndGoalRequestDto.java
│        │        ├─ common
│        │        │  ├─ ApiResponse.java
│        │        │  ├─ ResultCode.java
│        │        │  ├─ exception
│        │        │  │  ├─ BadRequestException.java
│        │        │  │  ├─ ForbiddenException.java
│        │        │  │  ├─ InternalServerErrorException.java
│        │        │  │  ├─ NotFoundException.java
│        │        │  │  ├─ OhhoServerException.java
│        │        │  │  ├─ ServiceUnavailableException.java
│        │        │  │  └─ UnauthorizedException.java
│        │        │  └─ utils
│        │        │     └─ LocalDateTimeToArray.java
│        │        └─ config
│        │           ├─ PasswordEncoderConfig.java
│        │           ├─ jwt
│        │           │  └─ JwtService.java
│        │           └─ security
│        │              ├─ PreAuthTokenProvider.java
│        │              ├─ SecurityConfig.java
│        │              ├─ TokenPreAuthFilter.java
│        │              └─ exception
│        │                 └─ TokenMissingException.java
│        └─ resources
│           └─ application.yml
├─ ohho-storage
│  ├─ build.gradle
│  └─ src
│     ├─ main
│     │  ├─ java
│     │  │  └─ kr
│     │  │     └─ ohho
│     │  │        ├─ common
│     │  │        │  └─ entities
│     │  │        │     └─ BaseEntity.java
│     │  │        ├─ config
│     │  │        │  ├─ JasyptConfig.java
│     │  │        │  └─ JpaConfig.java
│     │  │        ├─ domain
│     │  │        │  ├─ exercise
│     │  │        │  │  ├─ Exercise.java
│     │  │        │  │  ├─ Part.java
│     │  │        │  │  └─ converter
│     │  │        │  │     └─ PartConverter.java
│     │  │        │  ├─ exerciseHistory
│     │  │        │  │  └─ ExerciseHistory.java
│     │  │        │  ├─ member
│     │  │        │  │  ├─ Age.java
│     │  │        │  │  ├─ Gender.java
│     │  │        │  │  ├─ Member.java
│     │  │        │  │  ├─ converter
│     │  │        │  │  │  ├─ AgeConverter.java
│     │  │        │  │  │  └─ GenderConverter.java
│     │  │        │  │  └─ repository
│     │  │        │  │     └─ MemberRepository.java
│     │  │        │  ├─ notification
│     │  │        │  │  ├─ Notification.java
│     │  │        │  │  └─ repository
│     │  │        │  │     ├─ EmitterRepository.java
│     │  │        │  │     └─ NotificationRepository.java
│     │  │        │  ├─ organization
│     │  │        │  │  └─ Organization.java
│     │  │        │  └─ partner
│     │  │        │     ├─ Partner.java
│     │  │        │     └─ repository
│     │  │        │        ├─ PartnerCustomRepository.java
│     │  │        │        ├─ PartnerCustomRepositoryImpl.java
│     │  │        │        └─ PartnerRepository.java
│     │  │        └─ seed
│     │  │           └─ R__001_SEED_exercise.sql
│     │  └─ resources
│     │     ├─ application.yml
│     │     └─ flyway_seed.conf
│     └─ test
│        └─ java
│           └─ kr
│              └─ ohho
│                 ├─ TestConfiguration.java
│                 └─ config
│                    └─ JasyptConfigTest.java
└─ settings.gradle
```