class SegmentTree:
    def __init__(self, data):
        self.n = len(data)
        self.tree = [0] * (2 * self.n)
        # 리프 노드에 초기 데이터 복사
        for i in range(self.n):
            self.tree[self.n + i] = data[i]
        # 내부 노드 계산
        for i in range(self.n - 1, 0, -1):
            self.tree[i] = min(self.tree[i * 2] , self.tree[i * 2 + 1])

    def query(self, left, right):
        # [left, right) 구간의 합을 구함
        left += self.n
        right += self.n
        sum = 100000000000000
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
class SegmentTreeMax:
    def __init__(self, data):
        self.n = len(data)
        self.tree = [0] * (2 * self.n)
        # 리프 노드에 초기 데이터 복사
        for i in range(self.n):
            self.tree[self.n + i] = data[i]
        # 내부 노드 계산
        for i in range(self.n - 1, 0, -1):
            self.tree[i] = max(self.tree[i * 2] , self.tree[i * 2 + 1])

    def query(self, left, right):
        # [left, right) 구간의 합을 구함
        left += self.n
        right += self.n
        sum = -1
        while left < right:
            if left & 1:  # left가 홀수인 경우
                sum = max(sum,self.tree[left])
                left += 1
            if right & 1:  # right가 홀수인 경우
                right -= 1
                sum = max(sum,self.tree[right])
            left //= 2
            right //= 2
        return sum

import sys
input=sys.stdin.readline
a,b=map(int,input().split())
data = []
for _ in range(a):
    data.append(int(input()))
seg_tree = SegmentTree(data)
seg_treeMax = SegmentTreeMax(data)
for _ in range(b):
    d,e=map(int,input().split())
    print(seg_tree.query(d-1, e),end=" ")
    print(seg_treeMax.query(d-1, e))