import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Repository {
    public Map<Integer,Ticket> bookedList;
    public Map<Integer,Ticket> canceledList;
    public static Map<Integer,Ticket> waitingList;
    public Map<Integer,Integer> cancelHistory;
    private int[] seatCapacity;
    public  int seatAllocatedForWaitingList;
    static Repository instance=null;
    public Repository()
    {
        bookedList=new HashMap<>();
        canceledList=new HashMap<>();
        waitingList=new HashMap<>();
        cancelHistory=new HashMap<>();
        seatCapacity=new int[5];
        Arrays.fill(seatCapacity,8);
        seatAllocatedForWaitingList=0;
    }

    public void putTicketAsBooked(int pnr,Ticket temp)
    {
        bookedList.put(pnr,temp);
    }
    public void putTicketAsWaiting(int pnr,Ticket temp)
    {
        waitingList.put(pnr,temp);
    }
    public static Repository getInstance()
    {
        if(instance==null)
        {
            instance=new Repository();
        }
        return instance;
    }
    public Ticket getBookedTicket(int pnr)
    {
     //   System.out.println(bookedList.get(pnr));
        return bookedList.get(pnr);
    }
    public boolean isSeatAvailable(char source, char destination, int seats) {
        for(int i=source-'A';i<destination-'A';i++)
        {
            if(seatCapacity[i]<seats) return false;
        }
        return true;
    }

    public void decreaseSeatAvailability(char source, char destination, int seats) {
        for(int i=source-'A';i<destination-'A';i++)
        {
            seatCapacity[i]-=seats;
        }
        return;
    }
    public void increaseSeatAvailability(char source,char destination,int seats)
    {
        for(int i=source-'A';i<destination-'A';i++)
        {
            seatCapacity[i]+=seats;
        }
    }

    public void putCancelRecord(int pnr,Ticket temp)
    {
        canceledList.put(pnr,temp);
    }
    public void removeFromBookedList(int pnr)
    {
        bookedList.remove(pnr);
    }
    public void addCancelHistory(int pnr,int seats)
    {
        cancelHistory.put(pnr,seats);
    }
    public int getCancelHistory(int pnr)
    {
        Integer temp=cancelHistory.get(pnr);
        int seats=temp==null?0:temp;
        return seats;
    }

    public void removedFromWaitingList(int pnr) {
        waitingList.remove(pnr);
        return;
    }
    public void setSeatAllocatedForWaitingList(int a)
    {
        this.seatAllocatedForWaitingList=a;
    }
    public void removeFromWaitingList(int pnr)
    {
        waitingList.remove(pnr);
    }
    public void printAllList()
    {
        System.out.println("booked List  :");
        bookedList.values().forEach(System.out::println);
        System.out.println();
        System.out.println("waiting list  :");
        waitingList.values().forEach(System.out::println);
        System.out.println();
        System.out.println("the cancelled list  :");
        canceledList.values().forEach(System.out::println);
        System.out.println();
        System.out.println("seat availability  inr couch");
        System.out.println(Arrays.toString(seatCapacity));
        System.out.println();
        System.out.println();
        System.out.println("seat booked  :");
        System.out.println("\t1\t2\t3\t4\t5\t6\t7\t8");
        for(char c='A';c<='E';c++)
        {
            int times=8-seatCapacity[c-'A'];
            System.out.print(c);
            for(int i=0;i<times;i++)
            {
                System.out.print("\tX");
            }
            System.out.println();
        }
    }
}
