class SegmentTree:
    def __init__(self, data):
        self.n = len(data)
        self.tree = [(0,0) for _ in range(2 * self.n)] 
        # 리프 노드에 초기 데이터 복사
        for i in range(self.n):
            self.tree[self.n + i] = data[i]
        # 내부 노드 계산
        for i in range(self.n - 1, 0, -1):
            self.tree[i] = min(self.tree[i * 2] , self.tree[i * 2 + 1])

    def update(self, index, value):
        # 리프 노드 업데이트
        pos = index + self.n
        self.tree[pos] = (value,index)
        # 부모 노드 업데이트
        while pos > 1:
            pos //= 2
            self.tree[pos] = min(self.tree[pos * 2] , self.tree[pos * 2 + 1])

    def query(self, left, right):
        # [left, right) 구간의 합을 구함
        left += self.n
        right += self.n
        sum = (100000000000000000000000,-1)
        while left < right:
            if left & 1:  # left가 홀수인 경우
                sum = min(sum,self.tree[left])
                left += 1
            if right & 1:  # right가 홀수인 경우
                right -= 1
                sum = min(sum,self.tree[right])
            left //= 2
            right //= 2
        return sum
import sys
input=sys.stdin.readline
a=int(input())
data=[]
index=0
for num in list(map(int,input().split())):
    data.append((num,index))
    index+=1
# 사용 예제
a=int(input())
seg_tree = SegmentTree(data)
for _ in range(a):
    d,e,f=map(int,input().split())
    if d==1:
        seg_tree.update(e-1, f)
    else:
        print(seg_tree.query(e-1, f)[1]+1)
