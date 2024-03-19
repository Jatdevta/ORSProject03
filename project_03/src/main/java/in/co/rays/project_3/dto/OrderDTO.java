package in.co.rays.project_3.dto;

public class OrderDTO extends BaseDTO {

	private String Payment;
	private String Reciept;

	public String getPayment() {
		return Payment;
	}

	public void setPayment(String payment) {
		Payment = payment;
	}

	public String getReciept() {
		return Reciept;
	}

	public void setReciept(String reciept) {
		Reciept = reciept;
	}

	@Override
	public String getKey() {

		return id + "";
	}

	@Override
	public String getValue() {

		return Payment + "";
	}

}
