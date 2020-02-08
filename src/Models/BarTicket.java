package Models;

/**
 * This class extends the Ticket class and inherits its properties.
 *
 * @author Sultan Albogami Last Updated: 2/7/2020
 */

import java.util.Date;

public class BarTicket extends Ticket {

    BarTicket(int _ticketId, Date _dateTime, int _userId, int _tableId, TICKETSTATUS _ticketStatus) {
        super(_ticketId, _dateTime, _userId, _tableId, _ticketStatus);
    }
}