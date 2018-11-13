Ethereum address checksum application
====================================

* 입력한 주소의 Checksum을 구한다.
* 입력한 주소가 Checksum 보정한 주소와 일치하는지 확인한다.

Checksum address encoding
--------------------------
* [EIP-55][eip55] [eip55]: https://github.com/ethereum/EIPs/blob/master/EIPS/eip-55.md
1. 이더리움 주소의 해시값을 구한다.
2. 이더리움 주소 값이 문자열`(abcdef)`인 경우,
3. 해시의 같은 자리 값이 8 이상이면 대문자로 변환한다.

__해시값은 이더리움 주소의 16진수 소문자 String을 사용해 구한다.__
~~16진수 byte array로 삽질하지 말자~~