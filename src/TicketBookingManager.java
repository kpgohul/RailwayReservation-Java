public class TicketBookingManager {
    private char source;
    private char destination;
    BookingStatus status;
    private int seats;

    public TicketBookingManager(char a, char b, int c)
    {
        source=a;
        destination=b;
        seats=c;
        status=BookingStatus.Bookead;
    }
    private void addToBookedList()
    {
        Repository instance=Repository.getInstance();
        if(instance.isSeatAvailable(source,destination,seats))
        {
            Ticket newTicket=new Ticket(source,destination,seats,status);
            int pnr= newTicket.getPnr();
            instance.putTicketAsBooked(pnr,newTicket);
            System.out.println("the pnr "+pnr+" is booked succesfully");
            instance.decreaseSeatAvailability(source,destination,seats);
        }
        else {
            if(instance.seatAllocatedForWaitingList+seats>2)
                System.out.println("Cannot add to the bookedList and WaitedList with pnr : ");
            else {
                TicketWaitingListManager waitList=new TicketWaitingListManager(source,destination,seats); 
                waitList.execute();
            }
        }
    }
    public void execute()
    {
        addToBookedList();
    }

}
