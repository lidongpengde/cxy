package com.cxy.classicProblems;

/**
 * Created by lidongpeng on 2018/4/21.
 */
public class LinkedTest {

    //Remove Duplicates from Sorted List,链表去重
    public ListNode  Duplicates(ListNode head){
        if (head==null || head.next==null){
            return head;
        }
        ListNode pre=head;
        ListNode p=head.next;
        while(p!=null){
            if (pre.val==p.val){
                pre.next=p.next;
                p=p.next;
            }else {
                pre=p;
                p=p.next;
            }

        }
        return head;
    }
    public ListNode  reverts(ListNode head){
        if (head==null || head.next==null){
            return head;
        }
        ListNode pre=head;
        ListNode aft=head.next;
       while (aft!=null){
           ListNode tep=aft.next;
           aft.next=pre;

           pre=aft;
           aft=tep;
       }
       return pre;
    }

}
