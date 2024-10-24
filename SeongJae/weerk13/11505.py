class SegmentTree:
    def __init__(self, data):
        self.n = len(data)
        self.tree = [0] * (2 * self.n)
        # 리프 노드에 초기 데이터 복사
        for i in range(self.n):
            self.tree[self.n + i] = data[i]
        # 내부 노드 계산
        for i in range(self.n - 1, 0, -1):
            self.tree[i] = self.tree[i * 2] * self.tree[i * 2 + 1]%1000000007

    def update(self, index, value):
        # 리프 노드 업데이트
        pos = index + self.n
        self.tree[pos] = value
        # 부모 노드 업데이트
        while pos > 1:
            pos //= 2
            self.tree[pos] = self.tree[pos * 2] * self.tree[pos * 2 + 1]%1000000007

    def query(self, left, right):
        # [left, right) 구간의 합을 구함
        left += self.n
        right += self.n
        sum = 1
        while left < right:
            if left & 1:  # left가 홀수인 경우
                sum *= self.tree[left]%1000000007
                left += 1
            if right & 1:  # right가 홀수인 경우
                right -= 1
                sum *= self.tree[right]%1000000007
            left //= 2
            right //= 2
        return sum
import sys
input=sys.stdin.readline
a,b,c=map(int,input().split())
# 사용 예제
data = []
for _ in range(a):
    data.append(int(input()))
seg_tree = SegmentTree(data)
for _ in range(b+c):
    d,e,f=map(int,input().split())
    if d==1:
        seg_tree.update(e-1, f)
    else:
        print(seg_tree.query(e-1, f)%1000000007)