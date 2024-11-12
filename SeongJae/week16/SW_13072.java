class UserSolution {
	static class Node {
		Node next;
		Node pre;
		int team;
		int num;

		public Node(int team, int num) {
			this.next = null;
			this.pre = null;
			this.team = team;
			this.num = num;
		}
	}

	static class LinkedList {
		Node[] nodeArr;

		public LinkedList() {
			nodeArr = new Node[1000001];
			for (int i = 0; i < 1000001; i++) {
				nodeArr[i] = new Node(0, i);
			}
		}
	}

	static LinkedList list = new LinkedList();
	static Node[][] teamArr = new Node[6][6];

	public void init() {
		for (int x = 0; x < 6; x++) {
			for (int y = 0; y < 6; y++) {
				teamArr[x][y] = null;
			}
		}
	}

	public void hire(int mID, int mTeam, int mScore) {
		list.nodeArr[mID].team = mTeam;
		Node head = teamArr[mTeam][mScore];
		Node solider = list.nodeArr[mID];
		if (head == null) {// 리스트가 비워있으면 자기 자신을 가르킴(nullException 처리)
			solider.next = solider;
			solider.pre = solider;
		} else {
			solider.next = head;
			solider.pre = head.pre;
			solider.pre.next = solider;
			solider.next.pre = solider;
		}
		teamArr[mTeam][mScore] = solider;
	}

	public void fire(int mID) {
		int tmpTeam = list.nodeArr[mID].team;
		Node solider = list.nodeArr[mID];
		for (int tmpScore = 5; tmpScore > 0; tmpScore--) {// 팀배열 작업
			if (teamArr[tmpTeam][tmpScore] == solider) {// 삭제할려는게 헤드일떄
				if (teamArr[tmpTeam][tmpScore].next == solider) {// 노드가 하나만 있음
					teamArr[tmpTeam][tmpScore] = null;
				} else {
					teamArr[tmpTeam][tmpScore] = solider.next;
				}
			}
		}

		// 삭제후 리스트 유지
		solider.pre.next = solider.next;
		solider.next.pre = solider.pre;

		// 초기화 작업
		solider.next = null;
		solider.pre = null;
		solider.team = 0;
	}

	public void updateSoldier(int mID, int mScore) {
		int mTeam = list.nodeArr[mID].team;
		fire(mID);
		hire(mID, mTeam, mScore);
	}

	public void updateTeam(int mTeam, int mChangeScore) {
		if (mChangeScore > 0) {// 양수일떄
			for (int start = 4; start > 0; start--) {
				int end = start + mChangeScore;
				if (end > 5) {
					end = 5;
				}
				LinkedStartToEnd(mTeam, start, end);
			}
		} else if (mChangeScore < 0) {
			for (int start = 2; start < 6; start++) {
				int end = start + mChangeScore;
				if (end < 1) {
					end = 1;
				}
				LinkedStartToEnd(mTeam, start, end);
			}
		}
	}

	public void LinkedStartToEnd(int mTeam, int start, int end) {
		if (teamArr[mTeam][start] == null) {// 비워있을때
			return;
		} else {
			if (teamArr[mTeam][end] == null) {// 0개일때
				teamArr[mTeam][end] = teamArr[mTeam][start];
				teamArr[mTeam][start] = null;
			} else {
				Node startHead = teamArr[mTeam][start];
				Node startTail = teamArr[mTeam][start].pre;
				Node endHead = teamArr[mTeam][end];
				Node endTail = teamArr[mTeam][end].pre;

				startTail.next = endHead;
				startHead.pre = endTail;
				endHead.pre = startTail;
				endTail.next = startHead;
				teamArr[mTeam][end] = startHead;
				teamArr[mTeam][start] = null;
			}
		}
	}

	public int bestSoldier(int mTeam) {
		for (int start = 5; start > 0; start--) {
			if (teamArr[mTeam][start] == null) {
				continue;
			} else {
				Node curNode = teamArr[mTeam][start];
				int result = curNode.num;
				int breakPoint = result;
				curNode = curNode.next;
				while (curNode.num != breakPoint) {
					if (curNode.num > result) {
						result = curNode.num;
					}
					curNode = curNode.next;
				}
				return result;
			}
		}
		return 0;
	}
}