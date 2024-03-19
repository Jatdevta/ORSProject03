package in.co.rays.project_3.dto;

public class LaptopDTO extends BaseDTO {
	private String CustomerName;
	private String Price;
	private String Quantity;
	private String OrderStatus;


	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		Price = price;
	}

	public String getQuantity() {
		return Quantity;
	}

	public void setQuantity(String quantity) {
		Quantity = quantity;
	}

	public String getOrderStatus() {
		return OrderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		OrderStatus = orderStatus;
	}

	@Override
	public String getKey() {
		return id +"";
	}

	@Override
	public String getValue() {
		
		return CustomerName +"";
	}

}
