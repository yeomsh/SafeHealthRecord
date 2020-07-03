# Safe Healt hRecord

### 2020 건국대학교 러닝앤쉐어링 최종 산출물<br>(Parks Children팀 - 염상희 조원빈 천세진 최승연)

## 1. 기획 배경
###### 건강기록을 안전하게 보관하려면 어떻게 해야할까요?
###### 인터넷에 떠다니는 우리의 개인정보가 더 이상 나만의 것이 아니라고 생각해보신적 있으신가요?

##### 나의 개인정보가 온전히 나만의 것이 되어야 한다는 아주 기본적인 생각에서 본 프로젝트는 시작되었습니다!

## 2. 기능
Java GUI(Swing)을 이용하여, 고정 아이피 내에서 안전한 처방전 작성이 가능하다.
<br>작성된 처방전은 블록체인의 개념을 사용하여, 위변조 및 부인을 방지한다.
## 2.1 처방전 정보 기록 및 저장

## 2.2 처방전 정보 조회


## 3. 기술
* BlockChain
  * nonce = 5로 설정
* ECDSA - 전자 서명
* ECIES - 온라인 상에서 계약서 전달 암호 프로토콜
  * Curve25519
  * KDF2BytesGenerator (SHA256Digest)
  * hMacKey tag (Data integration validation)

## 4. 참고자료
* Satoshi Nakamoto, “Bitcoin: A Peer-to-Peer Electronic Cash System”, 2008
* ECIES - https://github.com/sjdonado/ecies
* Koblitz N, Elliptic Curve Cryptoystems Mathematics of Computation, 1987; 48:203-209
