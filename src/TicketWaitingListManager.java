public class TicketWaitingListManager {
    private char source;
    private char destination;
    private int seats;
    private BookingStatus status;
    private static Repository repository=null;
    public TicketWaitingListManager(char a,char b,int c)
    {
        source=a;
        destination=b;
        seats=c;
        status=BookingStatus.WaitingList;
        repository=Repository.getInstance();
    }

    public static void processWaitingList() {
        for(Ticket ticket:Repository.waitingList.values())
        {
            char source= ticket.getSource(),destination= ticket.getDestination();
            int seats=ticket.getSeats();
            if(repository.isSeatAvailable(source,destination,seats))
            {
                repository.decreaseSeatAvailability(source,destination,seats);
                repository.setSeatAllocatedForWaitingList(repository.seatAllocatedForWaitingList);
                ticket.setStatus(BookingStatus.Bookead);
                repository.putTicketAsBooked(ticket.getPnr(),ticket);
                System.out.println("the ticket in waiting is set as booked for pnr  "+ticket.getPnr());
                repository.removedFromWaitingList(ticket.getPnr());

            }
        }
    }

    private void addToWaitingList()
    {
        Ticket newTicket=new Ticket(source,destination,seats,status);
        int pnr=newTicket.getPnr();
        repository.putTicketAsWaiting(pnr,newTicket);
        repository.seatAllocatedForWaitingList+=seats;
        System.out.println("you booking is added to the waitingList with pnr "+pnr);
    }
    public void execute(){addToWaitingList();}

}
