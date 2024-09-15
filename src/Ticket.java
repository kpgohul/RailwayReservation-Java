public class Ticket {
    private static int pnrGenerator=1;
    private final int pnr=pnrGenerator++;
    private char source;
    private char destination;
    BookingStatus status;
    private int seats;
    public Ticket(char a,char b,int c,BookingStatus temp)
    {
        source=a;
        destination=b;
        seats=c;
        status=temp;
    }

    public char getDestination() {
        return destination;
    }
    public char getSource() {
        return source;
    }
    public int getSeats() {
        return seats;
    }

    public int getPnr() {
        return pnr;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "pnr=" + pnr +
                ", source=" + source +
                ", destination=" + destination +
                ", status=" + status +
                ", seats=" + seats +
                '}';
    }

    public void setStatus(BookingStatus temp)
   {
       status=temp;
   }
    public void setSeats(int seats) {
        this.seats = seats;
    }
}
