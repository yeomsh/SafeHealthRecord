# Safe Health Record

### 2020 건국대학교 Learning&Sharing 최종 산출물<br>(Parks Children팀 - 염상희 조원빈 천세진 최승연)

# 1. 기획 배경
###### 건강기록을 안전하게 보관하려면 어떻게 해야할까요?
###### 인터넷에 떠다니는 우리의 개인정보가 더 이상 나만의 것이 아니라고 생각해보신적 있으신가요?

##### 나의 개인정보가 온전히 나만의 것이 되어야 한다는 아주 기본적인 생각에서 본 프로젝트는 시작되었습니다!

# 2. 기능
Java GUI(Swing)을 이용하여, 고정 아이피 내에서 안전한 처방전 작성이 가능하다.
<br>작성된 처방전은 블록체인의 개념을 사용하여, 위변조 및 부인을 방지한다.
## 2.1 실행화면
<div>
<img src = "https://user-images.githubusercontent.com/39792772/86442075-2ab4ac00-bd48-11ea-9c8e-e8691345c446.JPG" width="200px"></img>
<img src = "https://user-images.githubusercontent.com/39792772/86441058-9138ca80-bd46-11ea-9fbb-f76c6ff39926.JPG" width="20%"></img>
</div>

## 2.2 처방전 정보 기록 및 저장
의료진과 진료자가 번갈아가며 서로 작성이 필요한 부분을 작성한다.
<br> 그 후 서로의 서명을 붙여 내용을 확인하면 계약서 작성이 끝나고, 블록체인에 올리기 위해 검증을 진행한다.
## 2.3 처방전 정보 조회
블록체인에 올라간 처방전이라면, 작성된 파일이 저장되고 처방전 정보 기록 화면과 같이 조회 가능하다.

# 3. 기술
* BlockChain
  * nonce = 5로 설정
* ECDSA - 전자 서명
* ECIES - 온라인 상에서 계약서 전달 암호 프로토콜
  * Curve25519
  * KDF2BytesGenerator (SHA256Digest)
  * hMacKey tag (Data integration validation)

# 4. 참고자료
* Satoshi Nakamoto, “Bitcoin: A Peer-to-Peer Electronic Cash System”, 2008
* ECIES - https://github.com/sjdonado/ecies
* Koblitz N, Elliptic Curve Cryptoystems Mathematics of Computation, 1987; 48:203-209
