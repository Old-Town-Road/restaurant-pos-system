package models;

/**
 * This class extends the Ticket class and inherits its properties.
 *
 * @author Sultan Albogami, Ian Wilhelmsen Last Updated: 2/19/2020
 * 
 * NOTES:
 * 2/19/2020 IMW
 * -> Changed the type of _ticketStatus to new enum created.
 * -> Verified the styling.
 */

import java.util.Date;

public class BarTicket extends Ticket {

    BarTicket(int _ticketId, Date _dateTime, int _userId, int _tableId, TicketStatus _ticketStatus) {
        super(_ticketId, _dateTime, _userId, _tableId, _ticketStatus);
    }

}