public class BookingCancelation {
    private int pnr;
    private int seatsToBeCancel;
    private Repository repository;

    public BookingCancelation(int pnr,int seatsToBeCancel) {
        this.pnr=pnr;
        this.seatsToBeCancel = seatsToBeCancel;

        repository=Repository.getInstance();
    }
    private void cancelBooking()
    {

        Ticket ticket=repository.getBookedTicket(this.pnr);

        if(ticket!=null)
        {

            int allocatedSeats= ticket.getSeats();
          //  System.out.println(allocatedSeats);
            if(allocatedSeats==seatsToBeCancel)
            {
                ticket.setStatus(BookingStatus.Canceled);
                ticket.setSeats(ticket.getSeats()+ repository.getCancelHistory(pnr));
                repository.putCancelRecord(pnr, ticket);
                repository.removeFromBookedList(pnr);
                System.out.println("The booked record is cancelled succesfully!!! with pnr  "+pnr);
            }
            else if(allocatedSeats>seatsToBeCancel)
            {
                ticket.setSeats(allocatedSeats-seatsToBeCancel);
                repository.addCancelHistory(pnr,seatsToBeCancel);
                System.out.println("the booked record is partially cancelled!!! with pnr "+pnr);
            }
            else
            {
                System.out.println("Your have given more number of seats than booked seats!!!");
                return;
            }
            repository.increaseSeatAvailability(ticket.getSource(),ticket.getDestination(), ticket.getSeats());
            TicketWaitingListManager.processWaitingList();


        }
        else {
            System.out.println("the given pnr not exist int the list");
        }
    }
    public void execute()
    {
        cancelBooking();
    }
}
