package finance_producer;

public interface FinanceService {
	public void displayFinance();
	public void addFinanceDetails();
	public void showFinanceDetails();
	public void calculateTotalCollectedFees();
	void getTotalPaidForSubject(int subId);
	public int selectClassId();
}
