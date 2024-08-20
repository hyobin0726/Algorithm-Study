코드
![캡처](https://github.com/user-attachments/assets/edf209a4-063f-4835-8f1c-e3f526b95785)
1. 테스트 케이스 개수를 입력받는다.
 *  2. 각 테스트 케이스마다,
 *      2-1. 필름의 이용권 가격을 입력받는다.
 *      2-2. 월간 이용 일수를 입력 받는다.
 *
 *  3. DP식을 작성한다.
 *      [현재 Depth의 상태에서 3가지 상태 - 가지를 뻗어나간다.]
 *      3-1. DP = min (    전월 가격+ min (해당 달 이용 일 수*일일 이용가격 , 월간 이용권 가격)    , 3개월전 가격 + 3개월 이용권 )
 *      3-2. DP에서 12월까지의  가격과 연간 이용권과 가격을 비교한다.
![1](https://github.com/user-attachments/assets/b9809762-c8d3-45e9-b4f0-a14afe757a07)
![2](https://github.com/user-attachments/assets/012f66d3-8f0b-47e9-9067-5a6a4f328549)
