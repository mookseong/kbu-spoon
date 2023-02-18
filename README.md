# kbu-spoon

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

kbu-spoon은 KBU 정보를 불러올 수 있게 도와주는 라이브러리입니다. 정보를 얻기 위해 공지사항, 도서관, 인트라넷 정보를 kbu-spoon을 통해 손쉽게 가져와 데이터 추출 및 조작을 위해 편하게 만들어진 API를 제공합니다.

kbu-spoon Java로 새롭게 만들어진 라이브러입니다. 
+ 도서관 책 정보 추출(Naver 검색API 연동 가능)
+ 인트라넷 정보 추출
+ 공지사항 정보 추출


## **Installation**

1. build.gradle에 repositories를 등록해야합니다.

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

2. dependency에 추가해주세요

```groovy
dependencies {
    ...
    implementation 'com.github.mookseong:kbu-spoon:0.1.0'
}
```

## **Requirements**

- `jsoup`
    - html에서 데이터를 추출하기 위해 사용합니다.
- `jackson-core`
- `jackson-databind`
- `jackson-annotations`
    - `jackson`은 NaverAPI를 사용시 Response을 매칭 하기 위해 `ObjectMapper` 사용합니다.

`Jsoup`은 KBU 홈페에지에서 가져와 위해 필수로 사용되는 라이브러리입니다. `jackson`은 NaverAPI를 사용하지 않는다면 설치하지 않으셔도 됩니다.

## License
[MIT License](License).
