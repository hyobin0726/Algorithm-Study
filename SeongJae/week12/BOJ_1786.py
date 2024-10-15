# KMP 알고리즘을 수행하기 전, 패턴을 처리하는 함수
# 테이블 생성 함수
def KMP_table(pattern):
    lp = len(pattern)
    tb = [0 for _ in range(lp)] # 테이블 리스트
    
    pidx = 0 # 테이블 값을 불러오거나 갱신 & 패턴의 인덱스 접근(접두사)
    for idx in range(1, lp): # 패턴의 인덱스 접근(접미사)
        # pidx가 0이 되거나, pidx번째 글자와 idx번째 글자가 같아질때까지 진행
        while pidx > 0 and pattern[pidx] != pattern[idx]:
            pidx = tb[pidx-1]
        
        # 값이 일치하는 경우, pidx 1 증가시키고 그 값을 tb에 저장
        if pattern[idx] == pattern[pidx] :
            pidx += 1
            tb[idx] = pidx
    
    return tb

def KMP(word, pattern):
    # KMP_table 통해 전처리된 테이블 불러오기
    table = KMP_table(pattern)
    
    results = [] # 결과를 만족하는 인덱스 시점을 기록하는 리스트
    pidx = 0
    
    for idx in range(len(word)):
        # 단어와 패턴이 일치하지 않는 경우, pidx를 table을 활용해 값 변경
        while pidx > 0 and word[idx] != pattern[pidx] :
            pidx = table[pidx-1]
        # 해당 인덱스에서 값이 일치한다면, pidx를 1 증가시킴
        # 만약 pidx가 패턴의 끝까지 도달하였다면, 시작 인덱스 값을 계산하여 추가 후 pidx 값 table의 인덱스에 접근하여 변경
        if word[idx] == pattern[pidx]:
            if pidx == len(pattern)-1 :
                results.append(idx-len(pattern)+2)
                pidx = table[pidx]
            else:
                pidx += 1
    print(len(results))
    print(' '.join(map(str,results)))
KMP(input(),input())
