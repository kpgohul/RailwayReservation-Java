import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//     TicketBookingManager b1=new TicketBookingManager('A','D',8);
//     b1.execute();
//     TicketWaitingListManager b2 =new TicketWaitingListManager('A','D',2);
//     b2.execute();
//     BookingCancelation b3=new BookingCancelation(2,2);
//     b3.execute();
//     Repository temp=Repository.getInstance();
//        System.out.println();
//        System.out.println("booked"+temp.bookedList);
//        System.out.println("cancelled"+temp.canceledList);
//        System.out.println("waiting list"+temp.waitingList);
//        System.out.println("cancelled history "+temp.cancelHistory);

        Scanner s=new Scanner(System.in);
        Repository repository=Repository.getInstance();
        repository.printAllList();
        while(true)
        {
            System.out.println("1.book");
            System.out.println("2.cancel");
            System.out.println("3.print List");
            System.out.println("4.exit");
            int choice=s.nextInt();
            switch(choice)
            {
                case 1 ->
                {
                    System.out.println("enter source:");
                    char source=s.next().charAt(0);
                    System.out.println("enter the destination :");
                    char destination=s.next().charAt(0);
                    System.out.println("enter seats :");
                    int seats=s.nextInt();
                    TicketBookingManager ticket=new TicketBookingManager(source,destination,seats);
                    ticket.execute();
                    break;
                }
                case 2->
                {
                    System.out.println("enter pnr :");
                    int pnr= s.nextInt();
                    System.out.println("enter no seats :1" +
                            "");
                    int seats= s.nextInt();
                    BookingCancelation cancel=new BookingCancelation(pnr,seats);
                    cancel.execute();
                    break;
                }
                case 3->
                {
                    
                    repository.printAllList();
                    break;
                }
                case 4->
                {
                    System.exit(0);

                }
                default ->
                {
                    System.out.println("enter crt choice!!!!");
                }
            }

        }
    }
}