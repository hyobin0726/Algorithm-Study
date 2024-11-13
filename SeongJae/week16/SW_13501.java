
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;



public class Solution{
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static int Node_MAX=2000;
  static class Node{
    int data;
    Node next;
    public Node(int data) {
      this.data = data;
    }
  }

  static class LinkedList{
    Node head;
    Node[] nodeArr;
    int nodeCnt;

    public LinkedList() {
      head=null;
      nodeArr=new Node[Node_MAX];
      nodeCnt=0;
    }

    Node getNewNode(int data){
      nodeArr[nodeCnt]=new Node(data);
      return nodeArr[nodeCnt++];
    }

    void insert(int idx,int data){
      if (idx==0){
        Node newNode = getNewNode(data);
        newNode.next=head;
        head=newNode;
        return;
      }
      Node cur=head;
      for (int i = 1; i < idx; i++) {
        cur=cur.next;
      }
      Node newNode = getNewNode(data);
      newNode.next=cur.next;
      cur.next=newNode;
    }
    void delete(int idx){
      if (idx==0){
        head=head.next;
        return;
      }
      Node cur=head;
      for (int i = 1; i < idx; i++) {
        cur=cur.next;
      }
      cur.next=cur.next.next;
      nodeCnt--;
    }
    void change(int idx,int data){
      Node cur=head;
      for (int i = 0; i < idx; i++) {
        cur=cur.next;
      }
      cur.data=data;
    }
    void print(int idx) throws IOException{
      if (idx>=nodeCnt){
        bw.write("-1\n");
        return;
      }
      Node cur=head;
      for (int i = 0; i < idx; i++) {
        cur=cur.next;
      }
      bw.write(cur.data+"\n");
    }

    
  }
  public static void main(String[] args) throws  Exception{
    
    int TestCase =Integer.parseInt(br.readLine());
    StringTokenizer st;
    for (int TC = 1; TC <= TestCase; TC++) {
      st=new StringTokenizer(br.readLine());
      LinkedList list=new LinkedList();
      int n=Integer.parseInt(st.nextToken());
      int m=Integer.parseInt(st.nextToken());
      int l=Integer.parseInt(st.nextToken());
      st=new StringTokenizer(br.readLine());
      for (int i = 0; i < n; i++) {
        list.insert(i, Integer.parseInt(st.nextToken()));
      }
      for (int i = 0; i < m; i++) {
        st=new StringTokenizer(br.readLine());
        char cmd=st.nextToken().charAt(0);
        if(cmd=='I'){
          int idx=Integer.parseInt(st.nextToken());
          int data=Integer.parseInt(st.nextToken());
          list.insert(idx, data);
        }
        else if(cmd=='D'){
          int idx=Integer.parseInt(st.nextToken());
          list.delete(idx);
        }
        else{
          int idx=Integer.parseInt(st.nextToken());
          int data=Integer.parseInt(st.nextToken());
          list.change(idx, data);
        }
      }
      bw.write("#"+TC+" ");
      list.print(l);
    }
    bw.flush();
    bw.close();
    br.close();
  }
}