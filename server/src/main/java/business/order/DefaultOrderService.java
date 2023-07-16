package business.order;

import api.ApiException;
import business.BookstoreDbException;
import business.JdbcUtils;
import business.book.Book;
import business.book.BookDao;
import business.cart.ShoppingCart;
import business.cart.ShoppingCartItem;
import business.customer.Customer;
import business.customer.CustomerDao;
import business.customer.CustomerForm;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class DefaultOrderService implements OrderService {

	private BookDao bookDao;
	private OrderDao orderDao;

	private LineItemDao lineItemDao;
	private CustomerDao customerDao;

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	public void setLineItemDao(LineItemDao lineItemDao) {this.lineItemDao = lineItemDao;}
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public OrderDetails getOrderDetails(long orderId) {
		Order order = orderDao.findByOrderId(orderId);
		Customer customer = customerDao.findByCustomerId(order.getCustomerId());
		List<LineItem> lineItems = lineItemDao.findByOrderId(orderId);
		List<Book> books = lineItems
				.stream()
				.map(lineItem -> bookDao.findByBookId(lineItem.getBookId()))
				.collect(Collectors.toList());
		return new OrderDetails(order, customer, lineItems, books);
	}


	@Override
    public long placeOrder(CustomerForm customerForm, ShoppingCart cart) {

		validateCustomer(customerForm);
		validateCart(cart);
		try (Connection connection = JdbcUtils.getConnection()) {
			Date date = getDate(
					customerForm.getCcExpiryMonth(),
					customerForm.getCcExpiryYear());
			return performPlaceOrderTransaction(
					customerForm.getName(),
					customerForm.getAddress(),
					customerForm.getPhone(),
					customerForm.getEmail(),
					customerForm.getCcNumber(),
					date, cart, connection);
		} catch (SQLException e) {
			throw new BookstoreDbException("Error during close connection for customer order", e);
		}
	}

	private Date getDate(String monthString, String yearString) {
		try{
			if(monthString.length() == 2){
				String str_date = monthString+"-"+yearString;
				DateFormat formatter = new SimpleDateFormat("MM-yy");
				Date date = formatter.parse(str_date);
				return date;
			}else{
				String str_date = monthString+"-"+yearString;
				DateFormat formatter = new SimpleDateFormat("M-yy");
				Date date = formatter.parse(str_date);
				return date;
			}

		}catch (java.text.ParseException e) {
			e.printStackTrace();
		}

		return new Date(); // TODO Implement this correctly
	}
	private long performPlaceOrderTransaction(
			String name, String address, String phone,
			String email, String ccNumber, Date date,
			ShoppingCart cart, Connection connection) {
		try {
			connection.setAutoCommit(false);
			long customerId = customerDao.create(
					connection, name, address, phone, email,
					ccNumber, date);
			long customerOrderId = orderDao.create(
					connection,
					cart.getComputedSubtotal() + cart.getSurcharge(),
					generateConfirmationNumber(), customerId);
			for (ShoppingCartItem item : cart.getItems()) {
				lineItemDao.create(connection, customerOrderId,item.getBookId(),
						item.getQuantity());
			}
			connection.commit();
			return customerOrderId;
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new BookstoreDbException("Failed to roll back transaction", e1);
			}
			return 0;
		}
	}

	private int generateConfirmationNumber() {
		Random random = new Random();
		return random.nextInt(999999999); // TODO GENERATE A RANDOM NUMBER
	}


	private void validateCustomer(CustomerForm customerForm) {

    	String name = customerForm.getName();

		if (name == null || name.trim().equals("") || name.length() > 45 || name.length() < 4) {
			throw new ApiException.InvalidParameter("Invalid name field");
		}
		String address = customerForm.getAddress();

		if (address == null || address.trim().equals("") ||address.length() > 45 || address.length() < 4) {
			throw new ApiException.InvalidParameter("Invalid address field");
		}
		String phone = customerForm.getPhone();
//		String pattern = "^(\\+\\d{1,2}\\s?)?1?\\-?\\.?\\s?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$";
//		Pattern pat = Pattern.compile(pattern);
//		Matcher mat = pat.matcher(phone);
		String trimedPhone = phone.replaceAll("[()\\s-]+", "");
		if(trimedPhone.equals("")||trimedPhone.length() !=10){
			throw new ApiException.InvalidParameter("Invalid phone field");
		}
		// TODO: Validation checks for address, phone, email, ccNumber
		String email = customerForm.getEmail();;
		if(email == null || email.equals("") || !isValidEmailAddress(email)){
			throw new ApiException.InvalidParameter("Invalid email field");
		}
		String ccNumber = customerForm.getCcNumber().replaceAll("[()\\s-]+", "");;
		if(ccNumber.length() >16 || ccNumber.length() < 14){
			throw new ApiException.InvalidParameter("Invalid ccNumber field");
		}
		if (customerForm.getCcExpiryMonth() == null || customerForm.getCcExpiryMonth().equals("") || customerForm.getCcExpiryYear() == null || customerForm.getCcExpiryYear().equals("") ||expiryDateIsInvalid(customerForm.getCcExpiryMonth(), customerForm.getCcExpiryYear())) {
			throw new ApiException.InvalidParameter("Invalid expiry date");

		}
	}

	private boolean expiryDateIsInvalid(String ccExpiryMonth, String ccExpiryYear) {
		YearMonth ym = YearMonth.now();
		long month =  ym.get(ChronoField.MONTH_OF_YEAR);
		long year =  ym.get(ChronoField.YEAR);
		if(Integer.parseInt(ccExpiryYear) < year){
			return true;
		}
		if(Integer.parseInt(ccExpiryMonth)>12 || Integer.parseInt(ccExpiryMonth) < 1){
			return true;
		}
		return Integer.parseInt(ccExpiryMonth) < month && Integer.parseInt(ccExpiryYear) == year;
		// TODO: return true when the provided month/year is before the current month/yeaR
		// HINT: Use Integer.parseInt and the YearMonth class

	}

	private void validateCart(ShoppingCart cart) {

		if (cart.getItems().size() <= 0) {
			throw new ApiException.InvalidParameter("Cart is empty.");
		}

		cart.getItems().forEach(item-> {
			if (item.getQuantity() < 0 || item.getQuantity() > 99) {
				throw new ApiException.InvalidParameter("Invalid quantity");
			}
			Book databaseBook = bookDao.findByBookId(item.getBookId());
			// TODO: complete the required validations

			if(databaseBook.getPrice() !=item.getBookForm().getPrice()){
				throw new ApiException.InvalidParameter("Invalid price");
			}
			if(databaseBook.getCategoryId() !=item.getBookForm().getCategoryId()){
				throw new ApiException.InvalidParameter("Invalid categoryID");
			}
		});
	}
	public boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}

}
