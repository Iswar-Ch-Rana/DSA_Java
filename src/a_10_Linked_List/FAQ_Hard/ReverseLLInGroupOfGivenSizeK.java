package a_10_Linked_List.FAQ_Hard;

import a_10_Linked_List.CustomLinkedList.ListNode;

public class ReverseLLInGroupOfGivenSizeK {
    public static void main(String[] args) {
        ListNode head = ListNode.arrayToLinkedList(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        head = reverseKGroup(head, 3);
        ListNode.printLinkedList(head);
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode temp = head, prevNode = null;
        while (temp != null) {
            // get k th node
            ListNode kThNode = getKthNode(temp, k);

            if (kThNode == null) {
                if (prevNode != null)
                    prevNode.next = temp;
                break;
            }

            ListNode nextKthNode = kThNode.next;
            kThNode.next = null;

            // reverse the temp
            reverseNode(temp);
            if (temp == head) {
                head = kThNode;
            } else {
                prevNode.next = kThNode;
            }
            prevNode = temp;
            temp = nextKthNode;
        }
        return head;
    }

    private static void reverseNode(ListNode temp) {
        ListNode prev = null;
        while (temp != null) {
            ListNode nextNode = temp.next;
            temp.next = prev;
            prev = temp;
            temp = nextNode;
        }
    }

    private static ListNode getKthNode(ListNode temp, int k) {
        int count = 1;
        while (count < k && temp != null) {
            count++;
            temp = temp.next;
        }
        return temp;
    }
}
