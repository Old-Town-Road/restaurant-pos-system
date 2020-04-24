package models;

/**
 * This class is just to keep track of enumerations and 
 * constants across the class for model relations.
 * @author Ian Wilhelmsen last updated 4/22/2020
 *
 */
public class ModelConstants {

	//These enumerations are Menu types.
	public static int MENU_TYPE_PIZZAS = 1;
	public static int MENU_TYPE_DRINKS = 2;
	public static int MENU_TYPE_SIDES = 3;

	//These enumerations are check types.
	public static int CHECK_TYPE_OPEN;
	public static int CHECK_TYPE_CLOSED;

	//These enumerations are payment types.
	public static int PAYMENT_TYPE_CASH = 1;
	public static int PAYMENT_TYPE_CARD = 2;
	public static int PAYMENT_TYPE_CHECK = 3;
	public static int PAYMENT_TYPE_GIFTCARD = 4;

	//These enumerations are payment statuses.
	public static int PAYMENT_STATUS_OPEN = 1;
	public static int PAYMENT_STATUS_PAID = 2;
	public static int PAYMENT_STATUS_COMP = 3;
	public static int PAYMEMT_STATUS_VOID = 4;

	//These enumerations are role types.
	public static int ROLE_TYPE_SERVER = 1;
	public static int ROLE_TYPE_COOK = 2;
	public static int ROLE_TYPE_BARTENDER = 3;
	public static int ROLE_TYPE_MANAGER = 4;

	//These enumerations are ticket types.
	public static int TICKET_TYPE_KITCHEN = 1;
	public static int TICKET_TYPE_BAR = 2;

	//These enumerations are ticket statuses.
	public static int TICKET_STATUS_OPEN = 1;
	public static int TICKET_STATUS_SENT = 2;
	public static int TICKET_STATUS_WORKING =3;
	public static int TICKET_STATUS_CLOSED = 4;

	//These enumerations are store types.
	public static int STORE_TYPE_POS = 1;
}